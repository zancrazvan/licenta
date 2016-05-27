package com.knapsack;

public class Device {

	private int id;
	private int power;
	private boolean alwaysOn;

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

}
