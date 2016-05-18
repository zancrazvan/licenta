package com.binpacking.generation;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.chromosome.Chromosome;
import com.binpacking.element.Element;

public class Generation {

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

		string = string + "Generation id: " + this.id + " => ";

		for (Chromosome chromosome : population) {
			string = string + " CHROMOSOM --->NR OF BINS: " + chromosome.getBins().size() + " --->";
			for (Bin bin : chromosome.getBins()) {

				string = string + "[";
				for (Element element : bin.getElements()) {

					string = string + (int) element.getValue() + " ";
				}
				string = string + "]";
			}

			string = string + "<--- END CHROMOSOM";

		}

		return string;

	}

}
