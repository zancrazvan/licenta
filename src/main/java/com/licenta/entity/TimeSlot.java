package com.licenta.entity;

import java.util.Date;

public class TimeSlot implements Comparable<TimeSlot>{

	private int id;
	private int time;
	private int power;
	private Date referenceDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public TimeSlot(int id, int time, int power) {
		super();
		this.id = id;
		this.time = time;
		this.power = power;
	}
	
	public TimeSlot(int id, int time, int power, Date d) {
		super();
		this.id = id;
		this.time = time;
		this.power = power;
		this.referenceDate = d;
	}
	public TimeSlot() {
		super();
		 
	}
	public Date getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
	}
	public String toString(){
		String s = new String();
		s+=referenceDate.toString()+", "+time+", "+power;
		return s;
	}
	@Override
	public int compareTo(TimeSlot o) {
		 
		return this.referenceDate.compareTo(o.getReferenceDate());
	}
	
	
}
