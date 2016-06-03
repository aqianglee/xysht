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
	private Date submitedTime;
	private Date checkedTime;
	private Date forwordTime;
	private Date receivedTime;
	private Date remarkedTime;
	private Date refundTime;
	private Double price;
	private String description;
	private String payWay;
	private String orderStatus;
	@ManyToOne
	@JoinColumn(name = "receive_address_id")
	private ReceiveAddress receiveAddress;
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

	public Date getSubmitedTime() {
		return submitedTime;
	}

	public void setSubmitedTime(Date submitedTime) {
		this.submitedTime = submitedTime;
	}

	public Date getCheckedTime() {
		return checkedTime;
	}

	public void setCheckedTime(Date checkedTime) {
		this.checkedTime = checkedTime;
	}

	public Date getForwordTime() {
		return forwordTime;
	}

	public void setForwordTime(Date forwordTime) {
		this.forwordTime = forwordTime;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public Date getRemarkedTime() {
		return remarkedTime;
	}

	public void setRemarkedTime(Date remarkedTime) {
		this.remarkedTime = remarkedTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public ReceiveAddress getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(ReceiveAddress receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

}
