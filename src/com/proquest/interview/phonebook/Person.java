//Namita Bhargava
public class Person {
	public String name;
	public String phoneNumber;
	public String address;
	//Constructor
	public Person(String name,String phoneNumber, String address ){
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.address=address;
	}
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
