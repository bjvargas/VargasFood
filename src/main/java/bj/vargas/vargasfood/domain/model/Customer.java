package bj.vargas.vargasfood.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	private String name;
	private String mail;
	private String phone;
	private boolean active = false;
	private Long id;

	public Customer(String name, String mail, String phone) {
		this.name = name;
		this.mail = mail;
		this.phone = phone;
	}

	public Customer() {

	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setActive(final boolean active) {
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

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	public Long getId() {
		return id;
	}
}
