package com.aqiang.xysht.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "supermatkets")
public class Supermarket {

	@Id
	@GeneratedValue
	private Integer id;
	private String number;
	private String name;
	@ManyToOne
	@JoinColumn(name = "shopkeeper_id")
	private ShopKeeper shopKeeper;
	private String address;
	private String qq;
	private String introduction;
	private Double beginSendPrice;
	private Double despatchMoney;
	private Integer serviceArea;
	private String description;
	@ManyToOne
	@JoinColumn(name = "icon_id")
	private Picture icon;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShopKeeper getShopKeeper() {
		return shopKeeper;
	}

	public void setShopKeeper(ShopKeeper shopKeeper) {
		this.shopKeeper = shopKeeper;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Double getBeginSendPrice() {
		return beginSendPrice;
	}

	public void setBeginSendPrice(Double beginSendPrice) {
		this.beginSendPrice = beginSendPrice;
	}

	public Double getDespatchMoney() {
		return despatchMoney;
	}

	public void setDespatchMoney(Double despatchMoney) {
		this.despatchMoney = despatchMoney;
	}

	public Integer getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(Integer serviceArea) {
		this.serviceArea = serviceArea;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Picture getIcon() {
		return icon;
	}

	public void setIcon(Picture icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Supermarket [id=" + id + ", number=" + number + ", name=" + name + ", shopKeeper=" + shopKeeper
				+ ", address=" + address + ", qq=" + qq + ", introduction=" + introduction + ", beginSendPrice="
				+ beginSendPrice + ", despatchMoney=" + despatchMoney + ", serviceArea=" + serviceArea
				+ ", description=" + description + ", icon=" + icon + "]";
	}

}
