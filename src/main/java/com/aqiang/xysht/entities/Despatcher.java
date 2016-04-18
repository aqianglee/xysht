package com.aqiang.xysht.entities;

public class Despatcher {
	private Integer id;
	private String compellation;
	private Supermarket supermarket;
	private String username;
	private String password;
	private String phone;
	private String address;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompellation() {
		return compellation;
	}

	public void setCompellation(String compellation) {
		this.compellation = compellation;
	}

	public Supermarket getSuperMarket() {
		return supermarket;
	}

	public void setSuperMarket(Supermarket supermarket) {
		this.supermarket = supermarket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
