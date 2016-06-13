package com.binpacking.generation;

import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeFitness;

public class GenerationFitness {

	public double computeGenerationFitness(Generation generation) {

		ChromosomeFitness chromosomeFitness = new ChromosomeFitness();
		double fitness = 0;

		for (Chromosome chromosome : generation.getPopulation()) {

			fitness = fitness + chromosomeFitness.computeChromosomeFitness(chromosome);
		}

		return fitness;

	}
}
