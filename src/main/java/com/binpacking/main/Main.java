package com.binpacking.main;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;

public class Main {

	public static void main(String[] args) {

		int elements[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		List<Element> list = new ArrayList<>();

		for (int i = 0; i < elements.length; i++) {

			Element element = new Element();
			element.setId(i);
			element.setValue(elements[i]);

			list.add(element);

		}

		Generation generation = GenerationDAO.initializeFirstGeneration(list, 100);
		System.out.println(generation.getPopulation().size() + " " + generation.toString());

		for (int i = 0; i < 100; i++) {

			generation.setId(i);
			generation = GenerationDAO.selection(generation);
			generation = GenerationDAO.generateNextGeneration(generation);
			generation = GenerationDAO.mutate(generation);

			System.out.println(generation.getPopulation().size() + " " + generation.toString());
		}

	}

}
