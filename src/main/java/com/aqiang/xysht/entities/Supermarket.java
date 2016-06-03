package com.aqiang.xysht.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

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
	private String serviceArea;
	private String description;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "activity_picture")
	private Picture activityPicture;
	@ManyToOne
	@JoinColumn(name = "icon")
	private Picture icon;
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

	public String getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Picture getActivityPicture() {
		return activityPicture;
	}

	public void setActivityPicture(Picture activityPicture) {
		this.activityPicture = activityPicture;
	}

	@Override
	public String toString() {
		return "Supermarket [id=" + id + ", number=" + number + ", name=" + name + ", shopKeeper=" + shopKeeper
				+ ", address=" + address + ", qq=" + qq + ", introduction=" + introduction + ", beginSendPrice="
				+ beginSendPrice + ", despatchMoney=" + despatchMoney + ", serviceArea=" + serviceArea
				+ ", description=" + description + ", icon=" + icon + "]";
	}

}
