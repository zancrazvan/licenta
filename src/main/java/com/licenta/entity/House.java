package com.licenta.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class House {

	@Id
	@GeneratedValue
	private int id;

	private String address;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Device> devices;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
