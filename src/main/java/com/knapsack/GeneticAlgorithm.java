package com.knapsack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.binpacking.util.Randomizer;

public class GeneticAlgorithm {

	private static List<Device> devices;
	private static int knapsackCapacity;
	private static int elite;
	private static int populationSize;
	private static double mutationProbability = .01;
	private static double elitePercentage = 10;

	private static double beta = 0.9;
	private static int k = 10;

	public static Chromosome geneticSolution(List<Device> items,
			List<SwitchingTime> switchingTimes, int populationSize,
			int maxGenerations, int knapsackCapacity) {

		GeneticAlgorithm.populationSize = populationSize;
		GeneticAlgorithm.devices = items;
		GeneticAlgorithm.knapsackCapacity = knapsackCapacity;

		elite = (int) Math.round(populationSize
				* ((double) elitePercentage / 100.0));
		int crtGeneration = 0;
		int chromosomeSize = switchingTimes.size();
		// generate initial population
		List<Chromosome> population = generateInitialPopulation(populationSize,
				chromosomeSize, switchingTimes);
		int initialError = 0;
		for (int gens = 0; gens < maxGenerations; gens++) {

			List<Chromosome> offspring = new ArrayList<Chromosome>();
			Collections.sort(population);

			// selecting elite parents
			List<Chromosome> elites = new ArrayList<Chromosome>();
			for (int i = 0; i < elite; i++) {
				elites.add(population.get(i));
			}
			// crossing over parents to obtain new Generation
			List<Chromosome> crossovers = crossover(population);
			double mutationP = ununiformMutation(gens,
					GeneticAlgorithm.mutationProbability, GeneticAlgorithm.beta);

			// System.out.println(mutationP);
			population = new ArrayList<Chromosome>();
			population.addAll(crossovers);
			population.addAll(elites);
			Collections.sort(population);
			population = population.subList(0, populationSize);

			for (Chromosome c : population) {
				c.calculateError();
			}
			Collections.sort(population);
			Chromosome best = population.get(0);
			/*
			 * if (best.getAbsoluteError() < 50) {
			 * 
			 * return best; }
			 */

			if (gens == 0) {
				initialError = population.get(0).getAbsoluteError();
			}

			int fitness = (int) (generetionFitness(population) / populationSize);
			double mappedFitness = Range.mapRange(0, initialError, 1, 0,
					population.get(0).getAbsoluteError());
			//System.out.println(gens + "," + mappedFitness+","+population.get(0).getAbsoluteError());
		}
		
		/*System.out.println(population.get(0).getAbsoluteError()
				+ "   and mapped: "
				+ Range.mapRange(0, initialError, 1, 0, population.get(0)
						.getAbsoluteError()));*/
		Collections.sort(population);
		return population.get(0);
	}

	private static void muation(List<Chromosome> crossovers, double probability) {
		for (Chromosome c : crossovers) {
			Map<SwitchingTime, Device> solution = c.getSolution();
			for (SwitchingTime s : solution.keySet()) {
				double rand = Math.random();
				if (rand < probability) {
					Device d = solution.get(s);
					d = GeneticAlgorithm.devices.get(Randomizer.generate(0,
							GeneticAlgorithm.devices.size() - 1));
				}

			}
		}

	}

	/**
	 * Crooses over population using binary tournament
	 * 
	 * @param population
	 * @return offspring
	 */
	private static List<Chromosome> crossover(List<Chromosome> parents) {
		List<Chromosome> offsprings = new ArrayList<>();
		for (int i = 0; i < populationSize - (populationSize / 3); i++) {
			Chromosome parent1 = kTournament(parents, 5);
			Chromosome parent2 = kTournament(parents, 5);
			while (parent1 == parent2) {
				parent2 = kTournament(parents, 5);
			}

			offsprings.addAll(oneCutCrossover(parent1, parent2));
		}
		return offsprings;
	}

	private static List<Chromosome> oneCutCrossover(Chromosome parent1,
			Chromosome parent2) {
		List<Chromosome> kids = new ArrayList<>();

		int cutpoint = Randomizer.generate(0, parent1.getSolution().size() - 1);
		Map<SwitchingTime, Device> solution1 = new HashMap<SwitchingTime, Device>();
		Map<SwitchingTime, Device> solution2 = new HashMap<SwitchingTime, Device>();

		Map<SwitchingTime, Device> parent1solution = parent1.getSolution();
		Map<SwitchingTime, Device> parent2solution = parent2.getSolution();

		List<SwitchingTime> times = new ArrayList<>();

		for (SwitchingTime s : parent1solution.keySet()) {
			times.add(s);
		}
		Collections.sort(times);
		for (int i = 0; i < parent1.getSolution().size(); i++) {
			if (i < cutpoint) {
				if (Math.random() < mutationProbability) {
					solution1.put(times.get(i), devices.get(Randomizer
							.generate(0, devices.size() - 1)));
				} else {
					solution1.put(times.get(i),
							parent1solution.get(times.get(i)));
				}
				if (Math.random() < mutationProbability) {
					solution2.put(times.get(i), devices.get(Randomizer
							.generate(0, devices.size() - 1)));
				} else {
					solution2.put(times.get(i),
							parent2solution.get(times.get(i)));
				}

			} else {
				if (Math.random() < mutationProbability) {
					solution1.put(times.get(i), devices.get(Randomizer
							.generate(0, devices.size() - 1)));
				} else {
					solution1.put(times.get(i),
							parent2solution.get(times.get(i)));
				}
				if (Math.random() < mutationProbability) {
					solution2.put(times.get(i), devices.get(Randomizer
							.generate(0, devices.size() - 1)));
				} else {
					solution2.put(times.get(i),
							parent1solution.get(times.get(i)));
				}
			}
		}
		Chromosome kid1 = new Chromosome();
		kid1.setSolution(solution1);
		kid1.calculateError();

		Chromosome kid2 = new Chromosome();
		kid2.setSolution(solution2);
		kid2.calculateError();

		kids.add(kid2);
		kids.add(kid1);
		return kids;
	}

	private static List<Chromosome> generateInitialPopulation(
			int populationSize2, int chromosomeSize,
			List<SwitchingTime> switchingTimes) {

		List<Chromosome> initialPopulation = new ArrayList<>();
		for (int i = 0; i < populationSize2; i++) {
			// generate chromosomes here
			Chromosome c = new Chromosome();
			Map<SwitchingTime, Device> chromosomeSolution = new HashMap<SwitchingTime, Device>();

			for (SwitchingTime s : switchingTimes) {
				Device d = devices.get(Randomizer.generate(0,
						devices.size() - 1));
				while (chromosomeSolution.containsValue(d)) {
					d = devices.get(Randomizer.generate(0, devices.size() - 1));
				}
				chromosomeSolution.put(s, d);
			}
			c.setSolution(chromosomeSolution);
			c.calculateError();
			initialPopulation.add(c);
		}
		return initialPopulation;
	}

	private static Chromosome kTournament(List<Chromosome> newPopulation, int k2) {
		List<Chromosome> contestants = new ArrayList<>();
		contestants.add(newPopulation.get(Randomizer.generate(0,
				newPopulation.size() - 1)));
		for (int i = 1; i < k; i++) {
			int p1 = Randomizer.generate(0, populationSize - 1);
			while (contestants.contains(newPopulation.get(p1))) {
				p1 = Randomizer.generate(0, populationSize - 1);
			}
			Chromosome contestant = newPopulation.get(p1);
			contestant.calculateError();
			contestants.add(contestant);
		}
		Collections.sort(contestants);
		return contestants.get(0);
	}

	private static Chromosome binaryTournament(List<Chromosome> newPopulation) {
		int p1 = Randomizer.generate(0, populationSize - 1);
		int p2 = Randomizer.generate(0, populationSize - 1);
		while (p1 == p2) {
			p2 = Randomizer.generate(0, populationSize - 1);
		}

		Chromosome parent1 = newPopulation.get(p1);
		Chromosome parent2 = newPopulation.get(p2);

		parent1.calculateError();
		parent2.calculateError();
		if (parent1.getAbsoluteError() < parent2.getAbsoluteError()) {
			return parent1;
		} else {
			return parent2;
		}

	}

	private static int flip(int i) {
		if (i == 0) {
			return 1;
		} else {
			return 0;
		}
	}

	private static int generetionFitness(List<Chromosome> population) {
		int f = 0;
		for (Chromosome c : population) {
			c.calculateError();
			f += c.getAbsoluteError();
		}
		return f;
	}

	private static double ununiformMutation(int generation,
			double initialMutationProbability, double beta) {
		double rer = initialMutationProbability
				* (Math.exp((1 - generation - 1) * beta));

		return rer;
	}

}
