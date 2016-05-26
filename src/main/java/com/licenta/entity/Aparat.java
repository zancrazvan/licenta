package com.licenta.entity;

import org.springframework.data.annotation.Id;

@Entity
public class Aparat {

	@Id
	private int id;

	private float putere;
	
	private int runningTime;
	
	private boolean always;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPutere() {
		return putere;
	}

	public void setPutere(float putere) {
		this.putere = putere;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public boolean isAlways() {
		return always;
	}

	public void setAlways(boolean always) {
		this.always = always;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
