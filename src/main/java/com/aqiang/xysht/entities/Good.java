package com.aqiang.xysht.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Good {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String number;
	private String specification;
	private Double price;
	private Integer stockNumber;
	@ManyToOne
	@JoinColumn(name = "picture_id")
	private Picture picture;
	private String description;
	private String tags;
	@ManyToOne
	@JoinColumn(name = "classfy_id")
	private Classfy classfy;
	private Boolean offLine = false;

	public Boolean getOffLine() {
		return offLine;
	}

	public void setOffLine(Boolean offLine) {
		this.offLine = offLine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Classfy getClassfy() {
		return classfy;
	}

	public void setClassfy(Classfy classfy) {
		this.classfy = classfy;
	}

}
