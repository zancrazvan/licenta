package com.licenta.test;

import java.util.List;

import com.knapsack.SwitchingTime;
import com.licena.dessagragation.SwitchingTimeExtractor;
import com.licenta.entity.TimeSlot;

public class SwitchingTimeDetectionTest {
	public static void main(String[] args) {
		
		List<TimeSlot> times  = TimeSlotGenerator.generateDemo();
		SwitchingTimeExtractor s = new SwitchingTimeExtractor();
		List<SwitchingTime> sws = s.extractSwitchingTimes(times);
		
		for(SwitchingTime sss: sws){
			System.out.println(sss.getAbsolutePower()+", "+sss.getRelativePower()+", st: "+sss.getTime()+", rt: "+sss.getRunningTime());
		}
	}
}
