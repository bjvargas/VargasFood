package bj.vargas.vargasfood.model;

public class Customer {

	private String name;
	private String mail;
	private String phone;
	private boolean active = false;

	public Customer(String name, String mail, String phone, boolean active) {
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isActive() {
		return active;
	}

	public void active() {
		this.active = true;
	}

}
