package com.licenta.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.licenta.entity.TimeSlot;

public class TimeSlotGenerator {

	public static List<TimeSlot> generateDemo(){
		List<TimeSlot> times = new ArrayList<TimeSlot>();
		Date d = new Date();
		for(int i=0;i<30;i++){
			
			times.add(new TimeSlot(i, i, 0,new Date(d.getTime()+i*1000*60*10)));
			
		}
		
		times.add(new TimeSlot(30, 30, 200,new Date(d.getTime()+30*1000*60*10)));
		times.add(new TimeSlot(31, 31, 500,new Date(d.getTime()+31*1000*60*10)));
		times.add(new TimeSlot(32, 32, 300,new Date(d.getTime()+32*1000*60*10)));
		for(int i=33;i<120;i++){
			times.add(new TimeSlot(i, i, 0,new Date(d.getTime()+i*1000*60*10)));
		}
		
		
		
		return times;
	}
}
