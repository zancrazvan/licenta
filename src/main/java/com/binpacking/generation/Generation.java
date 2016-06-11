package com.binpacking.generation;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeFitness;
import com.binpacking.element.Element;

public class Generation implements Comparable<Generation> {

	private int id;
	private List<Chromosome> population = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Chromosome> getPopulation() {
		return population;
	}

	public void setPopulation(List<Chromosome> population) {
		this.population = population;
	}

	@Override
	public String toString() {

		String string = new String();

		int nrOfElem = 0;
		string = string + "Generation id: " + this.id + " => NR OF CHROMOSOMS: " + population.size() + " => ";

		for (Chromosome chromosome : population) {
			string = string + " CHROMOSOM --->NR OF BINS: " + chromosome.getBins().size() + " --->";
			for (Bin bin : chromosome.getBins()) {

				string = string + "[ " + bin.getCapacity() + ":: " + bin.getId() + " :: ";
				for (Element element : bin.getElements()) {

					nrOfElem++;
					string = string + (int) element.getValue() + " ";
				}
				string = string /* + ":" + (int) bin.getCapacity() */ + "]";
			}

			string = string + "<--- END CHROMOSOM --- NR OF ELEM: " + nrOfElem;

		}

		return string;

	}

	@Override
	public int compareTo(Generation o) {

		ChromosomeFitness chromosomeFitness = new ChromosomeFitness();

		double thisFitness = 0;
		double paramFitness = 0;

		for (Chromosome chromosome : this.getPopulation()) {
			thisFitness = thisFitness + chromosomeFitness.computeChromosomeFitness(chromosome);
		}

		for (Chromosome chromosome : o.getPopulation()) {
			paramFitness = paramFitness + chromosomeFitness.computeChromosomeFitness(chromosome);
		}

		if (thisFitness < paramFitness) {
			return -1;
		}
		if (thisFitness > paramFitness) {
			return 1;
		}

		return 0;
	}

}
