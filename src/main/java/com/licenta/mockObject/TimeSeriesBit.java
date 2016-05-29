package com.licenta.mockObject;

import java.security.Timestamp;
import java.util.Date;

public class TimeSeriesBit implements Comparable<TimeSeriesBit> {

	private Date date;
	private int value;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public int compareTo(TimeSeriesBit o) {
		 
		return this.getDate().compareTo(o.getDate());
	}
	@Override
	public String toString() {
		return "TimeSeriesBit [date=" + date + ", value=" + value + "]";
	}
	
	
}
