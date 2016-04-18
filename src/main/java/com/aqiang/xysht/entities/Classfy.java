package com.aqiang.xysht.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "classfies")
public class Classfy {
	@Id
	@GeneratedValue
	private Integer id;
	private String number;
	private String name;
	@Column(length = 15)
	private String level;
	@ManyToOne
	@JoinColumn(name = "supermarekt_id")
	private Supermarket supermarket;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Classfy parent;

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

	public Supermarket getSupermarket() {
		return supermarket;
	}

	public void setSupermarket(Supermarket supermarket) {
		this.supermarket = supermarket;
	}

	public Classfy getParent() {
		return parent;
	}

	public void setParent(Classfy parent) {
		this.parent = parent;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Classfy [id=" + id + ", number=" + number + ", name=" + name
				+ ", level=" + level + ", supermarket=" + supermarket
				+ ", parent=" + parent + "]";
	}

}
