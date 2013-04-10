import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

//Namita Bhargava
public class PhoneBookImpl implements PhoneBook {
	public static List<Person> people;

	@Override
	// add a newPerson to the database
	public void addPerson(Person newPerson) {
		// TODO: write this method
		Connection conn = null;
		Statement stm = null;
		try {
			conn = DatabaseUtil.getConnection();
			stm = conn.createStatement();
			stm.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES("
					+ "'"
					+ newPerson.getName()
					+ "'"
					+ ","
					+ "'"
					+ newPerson.getPhoneNumber()
					+ "'"
					+ ","
					+ "'"
					+ newPerson.getAddress() + "'" + ")");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	// Looks for a person in the Database
	//Alternate could be to iterate over the List and find person using the getters
	public Person findPerson(String firstName, String lastName) {
		// TODO: write this method
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		Person newPerson = null;
		try {
			conn = DatabaseUtil.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM PHONEBOOK WHERE name=" + "'"
					+ firstName + " " + lastName + "'");
			while (rs.next()) {
					newPerson = new Person(rs.getString(1), rs.getString(2),
						rs.getString(3));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newPerson;
	}

	public static void main(String[] args) throws ClassNotFoundException,
	SQLException {
		PhoneBookImpl pbImpl = new PhoneBookImpl();
		Person pers = null;
		;
		DatabaseUtil.initDB(); // You should not remove this line, it creates
		// the in-memory database

		/*
		 * TODO: create person objects and put them in the PhoneBook and
		 * database John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		 */
		// Create the above mentioned Person objects
		Person person1 = new Person("John Smith", "(248) 123-4567",
				"1234 Sand Hill Dr, Royal Oak, MI");
		Person person2 = new Person("Cynthia Smith", "(824) 128-8758",
				"875 Main St, Ann Arbor, MI");

		// Add them to the database
		pbImpl.addPerson(person1);
		pbImpl.addPerson(person2);

		// Add them to the phonebook
		people = new ArrayList<Person>();
		people.add(person1);
		people.add(person2);

		// TODO: print the phone book out to System.out
		// TODO: find Cynthia Smith and print out just her entry
		// TODO: insert the new person objects into the database
		// Print out the phonebook
		printPhoneBK();

		// Find Cynthia Smith in the database
		pers = pbImpl.findPerson("Cynthia", "Smith");

		// Print the details of the found person
		System.out.println("Person Found: " + pers.getName() + " "
				+ pers.phoneNumber + " " + pers.getAddress());
	}

	private static void printPhoneBK() {
		for (Person p : people) {
			System.out.println("Details: " + p.getName() + " "
					+ p.getPhoneNumber() + " " + p.getAddress());
		}
	}
}
