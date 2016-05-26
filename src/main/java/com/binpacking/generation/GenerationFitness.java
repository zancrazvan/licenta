package com.binpacking.generation;

import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeFitness;

public class GenerationFitness {

	public static double computeGenerationFitness(Generation generation) {

		double fitness = 0;

		for (Chromosome chromosome : generation.getPopulation()) {

			fitness = fitness + ChromosomeFitness.computeChromosomeFitness(chromosome);
		}

		return fitness;

	}
}
