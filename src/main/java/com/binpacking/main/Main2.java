package com.binpacking.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.binpacking.bin.Bin;
import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;
import com.knapsack.Device;
import com.knapsack.DeviceGenerator;
import com.knapsack.GeneticAlgorithm;
import com.knapsack.SwitchingTime;
import com.knapsack.SwitchingTimeGenerator;

public class Main2 {

	public static void main(String[] args) {

		// int elements[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		List<Element> list = new ArrayList<>();
		Map<SwitchingTime, Device> map = GeneticAlgorithm.geneticSolution(DeviceGenerator.generateDevices(),
				SwitchingTimeGenerator.generateSwitchingTimes(), 1000, 150, 100).getSolution();

		int k = 0;
		for (SwitchingTime switchingTime : map.keySet()) {

			Device device = map.get(switchingTime);
			int runsInBins = switchingTime.getRunningTime();

			for (int j = 0; j < runsInBins; j++) {
				System.out.println("MATAAAA");
				Element element = new Element();
				element.setId(k);
				element.setValue(device.getPower());
				k++;
				list.add(element);

			}

		}

		/*
		 * for (int i = 0; i < elements.length; i++) {
		 * 
		 * Element element = new Element(); element.setId(i);
		 * element.setValue(elements[i]);
		 * 
		 * list.add(element);
		 * 
		 * }
		 */

		Generation generation = GenerationDAO.initializeFirstGeneration(list, 100);
		System.out.println(generation.getPopulation().size() + " " + generation.toString());

		for (int i = 1; i <= 100; i++) {

			generation.setId(i);
			generation = GenerationDAO.selection(generation);
			generation = GenerationDAO.generateNextGeneration(generation);
			System.out.println("AICI");
			System.out.println("BAAAAAA");
			generation = GenerationDAO.mutate(generation);

			System.out.println(generation.getPopulation().size() + " " + generation.toString());
		}

	}

}
