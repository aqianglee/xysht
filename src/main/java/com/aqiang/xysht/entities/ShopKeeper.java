package com.aqiang.xysht.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "shopkeepers")
public class ShopKeeper {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 12)
	private String compellation;
	@Column(length = 50)
	private String username;
	@Column(length = 50)
	private String password;
	@Transient
	private String repassword;
	@Column(length = 11)
	private String phone;
	@Column(length = 50)
	private String address;
	@Column(length = 50)
	private String email;
	@ManyToOne
	@JoinColumn(name = "picture_id")
	private Picture logo;
	@ManyToOne
	@JoinColumn(name = "icon_id")
	private Picture icon;

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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
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

	public Picture getLogo() {
		return logo;
	}

	public void setLogo(Picture logo) {
		this.logo = logo;
	}

	public Picture getIcon() {
		return icon;
	}

	public void setIcon(Picture icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "ShopKeeper [id=" + id + ", compellation=" + compellation + ", username=" + username + ", password="
				+ password + ", repassword=" + repassword + ", phone=" + phone + ", address=" + address + ", email="
				+ email + ", logo=" + logo + ", icon=" + icon + "]";
	}

}
