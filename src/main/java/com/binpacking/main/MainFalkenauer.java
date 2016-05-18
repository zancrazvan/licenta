package com.binpacking.main;

import java.util.List;

import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeFitness;
import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;
import com.binpacking.generation.GenerationFitness;
import com.binpacking.util.FileHandler;

public class MainFalkenauer {

	public static void main(String[] args) {

		List<Element> elements = FileHandler.readFile("./src/main/resources/Falkenauer_u500_00.txt");

		Generation generation = GenerationDAO.initializeFirstGeneration(elements, 100);
		generation.setId(0);
		System.out.println(generation.getPopulation().size() + " " + generation.toString());
		System.out.println("PRIM: " + GenerationFitness.computeGenerationFitness(generation));

		Generation bestGeneration = new Generation();
		bestGeneration = generation;

		Chromosome bestChromosome = new Chromosome();
		bestChromosome = GenerationDAO.getBestChromosome(generation);

		String info = new String();
		info = info + generation.getId() + "," + GenerationFitness.computeGenerationFitness(generation) + ","
				+ ChromosomeFitness.computeChromosomeFitness(GenerationDAO.getBestChromosome(generation)) + "\r\n";

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
			double bestGen = ChromosomeFitness.computeChromosomeFitness(GenerationDAO.getBestChromosome(generation));

			if (bestGen > bestCh) {
				//System.out.println("PLM " + i);
				bestChromosome = GenerationDAO.getBestChromosome(generation);
			}

			info = info + generation.getId() + "," + GenerationFitness.computeGenerationFitness(generation) + ","
					+ ChromosomeFitness.computeChromosomeFitness(GenerationDAO.getBestChromosome(generation)) + "\r\n";
		}

		info = info + "----- BEST GENERATION ------ " + "," + GenerationFitness.computeGenerationFitness(bestGeneration)
				+ "," + ChromosomeFitness.computeChromosomeFitness(GenerationDAO.getBestChromosome(bestGeneration))
				+ "\r\n";

		info = info + "----- BEST CHROMOSOME ------ " + "," + ChromosomeFitness.computeChromosomeFitness(bestChromosome)
				+ "\r\n";

		FileHandler fileHandler = new FileHandler();
		fileHandler.write(info, "Results_Falkenauer_u500_00.csv");

	}

}
