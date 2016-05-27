package com.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		 
		
		List<Device> devices = DeviceGenerator.generateDevices();
		List<SwitchingTime> sws = SwitchingTimeGenerator.generateSwitchingTimes();
		
		Chromosome best = null;
		Chromosome c1 = GeneticAlgorithm.geneticSolution(devices, sws, 2000, 250, 50);
		c1.calculateError();
		best = c1;
		 
		 
			 
			 
		 
	 
		 
		System.out.println(best.toString());

	}

}
