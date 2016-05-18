package com.binpacking.main;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeFitness;
import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;
import com.binpacking.generation.GenerationFitness;
import com.binpacking.util.FileHandler;

public class MainFalkenauer10 {

	public static void main(String[] args) {

		List<Element> elements = FileHandler.readFile("./src/main/resources/Falkenauer_u500_00.txt");

		List<Generation> bestGenerations = new ArrayList<>();
		List<Chromosome> bestChromosoms = new ArrayList<>();

		String info = new String();

		for (int j = 0; j < 10; j++) {

			Generation generation = GenerationDAO.initializeFirstGeneration(elements, 100);
			generation.setId(0);

			Generation bestGeneration = new Generation();
			bestGeneration = generation;

			Chromosome bestChromosome = new Chromosome();
			bestChromosome = GenerationDAO.getBestChromosome(generation);

			for (int i = 1; i < 100; i++) {

				generation.setId(i);
				generation = GenerationDAO.selection(generation);
				generation = GenerationDAO.generateNextGeneration(generation);
				generation = GenerationDAO.mutate(generation);

				if (GenerationFitness.computeGenerationFitness(generation) > GenerationFitness
						.computeGenerationFitness(bestGeneration)) {

					bestGeneration = generation;
				}

				double bestCh = ChromosomeFitness.computeChromosomeFitness(bestChromosome);
				double bestGen = ChromosomeFitness
						.computeChromosomeFitness(GenerationDAO.getBestChromosome(generation));

				if (bestGen > bestCh) {
					bestChromosome = GenerationDAO.getBestChromosome(generation);
				}

			}
			bestGenerations.add(bestGeneration);
			bestChromosoms.add(bestChromosome);

		}

		double avgGen = 0;
		info = info + "----BEST GENERATIONS ----" + "," + "\r\n";
		for (Generation generation : bestGenerations) {
			info = info + GenerationFitness.computeGenerationFitness(generation) + "," + "\r\n";
			avgGen = avgGen + GenerationFitness.computeGenerationFitness(generation);
		}

		double avgChr = 0;
		info = info + "----BEST CHROMOSOMS ---- " + "," + "\r\n";
		for (Chromosome chromosome : bestChromosoms) {
			info = info + ChromosomeFitness.computeChromosomeFitness(chromosome) + "," + "\r\n";
			avgChr = avgChr + ChromosomeFitness.computeChromosomeFitness(chromosome);
		}

		info = info + "----AVERAGE GENERATION ---- " + "," + avgGen / bestGenerations.size() + "," + "\r\n";
		info = info + "----AVERAGE CHROMOSOM ---- " + "," + avgChr / bestChromosoms.size() + "," + "\r\n";
		FileHandler fileHandler = new FileHandler();
		fileHandler.write(info, "10Results_Falkenauer_u500_00.csv");

	}

}
