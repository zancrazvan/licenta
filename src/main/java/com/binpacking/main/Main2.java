package com.binpacking.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.binpacking.bin.Bin;
import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeDAO;
import com.binpacking.chromosome.ChromosomeFitness;
import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;
import com.binpacking.generation.GenerationFitness;
import com.binpacking.util.DeviceGenerator;
import com.binpacking.util.FileHandler;
import com.binpacking.util.Randomizer;

public class Main2 {

	public static void main(String[] args) {/*

		DeviceGenerator deviceGenerator = new DeviceGenerator();
		List<Element> list = new ArrayList<>();
		list = deviceGenerator.generateDevices();

		GenerationFitness generationFitness = new GenerationFitness();
		GenerationDAO generationDAO = new GenerationDAO();
		ChromosomeDAO chromosomeDAO = new ChromosomeDAO();

		Generation generation = generationDAO.initializeFirstGeneration(list, 100);

		String info = new String();
		String info2 = new String();

		info = info + "0 " + "," + generationFitness.computeGenerationFitness(generation) / 100 + "," + "\r\n";
		info2 = info2 + generation.toString() + "\r\n";
		for (int i = 1; i <= 60; i++) {

			generation.setId(i);
			generation = generationDAO.selection(generation);
			generation = generationDAO.generateNextGeneration(generation, list);

			generation = generationDAO.mutate(generation);

			info = info + generation.getId() + "," + generationFitness.computeGenerationFitness(generation) / 100 + ","
					+ "\r\n";
			info2 = info2 + generation.toString() + "\r\n";
			System.out.println(generation.toString());

		}

		FileHandler fileHandler = new FileHandler();
		fileHandler.write(info, "binPackingRes.csv");

		FileHandler fileHandler2 = new FileHandler();
		fileHandler2.write(info2, "binPackingLog.txt");

	*/}

}
