package com.licenta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Device implements Comparable<Device> {

	@Id
	private int id;
	private int power;
	private boolean alwaysOn;
	
	private String picturePath;

	private String name;

	public Device() {
		// TODO Auto-generated constructor stub
	}

	public Device(int id, int power, boolean alwaysOn) {
		this.id = id;
		this.power = power;
		this.alwaysOn = alwaysOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public boolean isAlwaysOn() {
		return alwaysOn;
	}

	public void setAlwaysOn(boolean alwaysOn) {
		this.alwaysOn = alwaysOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Device o) {
		Integer i1 = this.id;
		Integer i2 = o.getId();
		return i1.compareTo(i2);
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}
