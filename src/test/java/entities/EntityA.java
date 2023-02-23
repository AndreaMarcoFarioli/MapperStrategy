package entities;

public class EntityA {
	private String name;
	private String surname;

	public EntityA(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "EntityA{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
