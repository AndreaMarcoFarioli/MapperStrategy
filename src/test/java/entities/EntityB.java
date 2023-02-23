package entities;

public class EntityB {
	private String surname;
	public EntityB(){}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "EntityB{" +
				"surname='" + surname + '\'' +
				'}';
	}
}
