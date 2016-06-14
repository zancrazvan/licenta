package com.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.licena.dessagragation.SwitchingTimeExtractor;
import com.licenta.entity.Device;
import com.licenta.entity.SwitchingTime;
import com.licenta.entity.TimeSlot;
import com.licenta.test.TimeSlotGenerator;
import com.licenta.utilityData.DeviceGenerator;

public class Main {

	public static void main(String[] args) {
		
		 
		
		List<Device> devices = DeviceGenerator.generateDevices();
		
		
		List<TimeSlot> slots = TimeSlotGenerator.generateRandomDevices(DeviceGenerator.generateDevicesWithStartingTimes(),120);
		for(int i=0;i<slots.size();i++){
			System.out.println(slots.get(i).getTime()+","+slots.get(i).getPower());
		}
		SwitchingTimeExtractor t = new SwitchingTimeExtractor();
		List<SwitchingTime> sws = t.extractSwitchingTimes(slots );
		
		Chromosome best = null;
		Chromosome c1 = GeneticAlgorithm.geneticSolution(devices, sws, 2000, 100, 50);
		c1.calculateError();
		best = c1;
		 
		 
			 
			 
		 
	 
		 
		System.out.println(best.toString());

	}

}
