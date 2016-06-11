package com.binpacking.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeCrossover;
import com.binpacking.chromosome.ChromosomeDAO;
import com.binpacking.chromosome.ChromosomeFitness;
import com.binpacking.chromosome.ChromosomeMutator;
import com.binpacking.element.Element;
import com.binpacking.util.DSOCurveGenerator;
import com.binpacking.util.Randomizer;

public class GenerationDAO {

	public Generation initializeFirstGeneration(List<Element> elements, int size) {

		Generation generation = new Generation();
		ChromosomeDAO chromosomeDAO = new ChromosomeDAO();
		DSOCurveGenerator curveGenerator = new DSOCurveGenerator();
		List<Bin> bins = curveGenerator.generateBinCurve();

		for (int i = 0; i < size; i++) {

			Chromosome chromosome = new Chromosome();
			chromosomeDAO.insertRandom(elements, bins, chromosome);
			generation.getPopulation().add(chromosome);

		}
		System.out.println("First GEN: " + generation.toString());
		return generation;
	}

	public Generation selection(Generation generation) {

		Collections.sort(generation.getPopulation(), Collections.reverseOrder());

		int limit = 100;

		if (generation.getPopulation().size() < 100) {
			limit = generation.getPopulation().size();
		} else {
			generation.setPopulation(generation.getPopulation().subList(0, limit));
		}

		return generation;
	}

	public Generation mutate(Generation generation) {

		ChromosomeMutator chromosomeMutator = new ChromosomeMutator();
		for (Chromosome chromosome : generation.getPopulation()) {

			chromosomeMutator.mutate(chromosome, 10, 1);
		}

		return generation;
	}

	public Chromosome getChromosome(Generation generation, int position) {

		return generation.getPopulation().get(position);
	}

	public Generation generateNextGeneration(Generation generation, List<Element> elements) {

		ChromosomeCrossover chromosomeCrossover = new ChromosomeCrossover();

		Generation nextGeneration = new Generation();
		nextGeneration.setId(generation.getId());
		nextGeneration.setPopulation(generation.getPopulation());

		Collections.sort(nextGeneration.getPopulation(), Collections.reverseOrder());
		List<Chromosome> tournament = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			tournament.add(nextGeneration.getPopulation().get(i));
		}

		Collections.sort(tournament, Collections.reverseOrder());

		Chromosome chromosome1 = tournament.get(0);
		Chromosome chromosome2 = tournament.get(1);

		Chromosome copyChromosome1 = chromosome1;
		Chromosome copyChromosome2 = chromosome2;

		chromosomeCrossover.crossover(copyChromosome1, copyChromosome2, 80);

		Collections.sort(nextGeneration.getPopulation());

		nextGeneration.getPopulation().get(0).setBins(copyChromosome1.getBins());
		nextGeneration.getPopulation().get(1).setBins(copyChromosome2.getBins());

		return nextGeneration;

	}

	public Chromosome getBestChromosome(Generation generation) {

		ChromosomeFitness chromosomeFitness = new ChromosomeFitness();
		Chromosome bestChromosome = new Chromosome();
		bestChromosome = generation.getPopulation().get(0);

		for (Chromosome chromosome : generation.getPopulation()) {

			if (chromosomeFitness.computeChromosomeFitness(chromosome) > chromosomeFitness
					.computeChromosomeFitness(bestChromosome)) {
				bestChromosome = chromosome;
			}

		}

		return bestChromosome;

	}
}