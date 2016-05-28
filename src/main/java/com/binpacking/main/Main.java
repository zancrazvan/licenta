package com.binpacking.main;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;

public class Main {

	public static void main(String[] args) {

		int elements[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		List<Element> list = new ArrayList<>();
		List<Bin> bins = new ArrayList<>();

		Bin bin = new Bin();
		bin.setCapacity(10);
		Bin bin2 = new Bin();
		bin2.setCapacity(10);
		Bin bin3 = new Bin();
		bin3.setCapacity(10);
		Bin bin4 = new Bin();
		bin4.setCapacity(10);
		Bin bin5 = new Bin();
		bin5.setCapacity(0);

		bins.add(bin);
		bins.add(bin2);
		bins.add(bin3);
		bins.add(bin4);
		bins.add(bin5);

		for (int i = 0; i < elements.length; i++) {

			Element element = new Element();
			element.setId(i);
			element.setValue(elements[i]);

			list.add(element);

		}

		Generation generation = GenerationDAO.initializeFirstGeneration(list, 100);
		System.out.println(generation.getPopulation().size() + " " + generation.toString());

		for (int i = 1; i <= 100; i++) {

			generation.setId(i);
			generation = GenerationDAO.selection(generation);
			generation = GenerationDAO.generateNextGeneration(generation);
			System.out.println("AICI");
			generation = GenerationDAO.mutate(generation);

			System.out.println(generation.getPopulation().size() + " " + generation.toString());
		}

	}

}
