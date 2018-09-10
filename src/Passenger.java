
public class Passenger implements Comparable<Passenger> {
	private String name;
	private int age;
	private char gender;
	
	Passenger(String name, int age, char gender) {
		setName(name);
		setAge(age);
		setGender(gender);
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public char getGender() { return gender; }

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public int compareTo(Passenger o) {
		return name.compareTo(o.name);
	}
	
}
