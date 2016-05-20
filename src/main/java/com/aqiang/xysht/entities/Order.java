package com.aqiang.xysht.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String number;
	private Date date;
	private Double price;
	private String description;
	private String payWay;
	private String orderStatus;
	@ManyToOne
	@JoinColumn(name = "supermarket_id")
	private Supermarket supermarket;
	@Version
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void initPrice(List<OrderItem> orderItems) {
		price = 0D;
		for (OrderItem orderItem : orderItems) {
			price += orderItem.getGood().getPrice() * orderItem.getCount();
		}
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Supermarket getSupermarket() {
		return supermarket;
	}

	public void setSupermarket(Supermarket supermarket) {
		this.supermarket = supermarket;
	}

}
