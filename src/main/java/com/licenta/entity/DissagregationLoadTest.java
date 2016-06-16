package com.licenta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DissagregationLoadTest {

	@Id
	@GeneratedValue
	private int id;
	
	private int numberOfDevices;
	private int numberOfSwitchingTimes;
	private long time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfDevices() {
		return numberOfDevices;
	}
	public void setNumberOfDevices(int numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
	}
	public int getNumberOfSwitchingTimes() {
		return numberOfSwitchingTimes;
	}
	public void setNumberOfSwitchingTimes(int numberOfSwitchingTimes) {
		this.numberOfSwitchingTimes = numberOfSwitchingTimes;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	
}
