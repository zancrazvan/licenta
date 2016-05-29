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

	public static Generation initializeFirstGeneration(List<Element> elements, int size) {

		Generation generation = new Generation();

		for (int i = 0; i < size; i++) {

			List<Bin> bins = new ArrayList<>();
			bins = DSOCurveGenerator.generateBinCurve();

			 
			/*
			 * Bin bin = new Bin(); bin.setCapacity(10); Bin bin2 = new Bin();
			 * bin2.setCapacity(10); Bin bin3 = new Bin(); bin3.setCapacity(10);
			 * Bin bin4 = new Bin(); bin4.setCapacity(10); Bin bin5 = new Bin();
			 * bin5.setCapacity(10); Bin bin6 = new Bin(); bin6.setCapacity(10);
			 * Bin bin7 = new Bin(); bin7.setCapacity(10);
			 * 
			 * bins.add(bin); bins.add(bin2); bins.add(bin3); bins.add(bin4);
			 * bins.add(bin5); bins.add(bin6); //bins.add(bin7);
			 */
			Chromosome chromosome = new Chromosome();
			chromosome.setBins(bins);
			ChromosomeDAO.insertRandom(elements, chromosome);
			generation.getPopulation().add(chromosome);

			// System.out.println("First GEN: " + generation.toString());
		}

		return generation;

	} 

	public static Generation selection(Generation generation) {

		Collections.sort(generation.getPopulation(), Collections.reverseOrder());

		int limit = 100;

		if (generation.getPopulation().size() < 100) {
			limit = generation.getPopulation().size();
		} else {
			generation.setPopulation(generation.getPopulation().subList(0, limit));
		}

		return generation;
	}

	public static Generation mutate(Generation generation) {

		for (Chromosome chromosome : generation.getPopulation()) {

			ChromosomeMutator.mutate(chromosome, 20, 2);
		}

		return generation;
	}

	public Chromosome getChromosome(Generation generation, int position) {

		return generation.getPopulation().get(position);
	}

	public static Generation generateNextGeneration(Generation generation) {

		Generation nextGeneration = new Generation();
		nextGeneration.setId(generation.getId());
		nextGeneration.setPopulation(generation.getPopulation());

		List<Chromosome> tournament = new ArrayList<>();

		int i = 0;
		while (i < 10) {

			Chromosome chromosome = nextGeneration.getPopulation()
					.get(Randomizer.generate(0, generation.getPopulation().size() - 1));

			if (!tournament.contains(chromosome)) {
				tournament.add(chromosome);
				i++;
			}

		}

		Collections.sort(tournament, Collections.reverseOrder());

		Chromosome chromosome1 = tournament.get(0);
		Chromosome chromosome2 = tournament.get(1);

		Chromosome copyChromosome1 = chromosome1;
		Chromosome copyChromosome2 = chromosome2;

		ChromosomeCrossover.crossover(copyChromosome1, copyChromosome2, 80);

		chromosome1 = copyChromosome1;
		chromosome2 = copyChromosome2;

		return nextGeneration;

	}

	public static Chromosome getBestChromosome(Generation generation) {

		Chromosome bestChromosome = new Chromosome();
		bestChromosome = generation.getPopulation().get(0);

		for (Chromosome chromosome : generation.getPopulation()) {

			if (ChromosomeFitness.computeChromosomeFitness(chromosome) > ChromosomeFitness
					.computeChromosomeFitness(bestChromosome)) {
				bestChromosome = chromosome;
			}

		}

		return bestChromosome;

	}
}