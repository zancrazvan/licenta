package com.binpacking.chromosome;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.element.Element;

public class Chromosome implements Comparable<Chromosome> {

	private int id;

	private List<Bin> bins = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Bin> getBins() {
		return bins;
	}

	public void setBins(List<Bin> bins) {
		this.bins = bins;
	}

	@Override
	public int compareTo(Chromosome arg0) {

		if (ChromosomeFitness.computeChromosomeFitness(this) < ChromosomeFitness.computeChromosomeFitness(arg0)) {
			return 1;
		}
		if (ChromosomeFitness.computeChromosomeFitness(this) > ChromosomeFitness.computeChromosomeFitness(arg0)) {
			return -1;
		}
		if (ChromosomeFitness.computeChromosomeFitness(this)
				- ChromosomeFitness.computeChromosomeFitness(arg0) < .000000001) {
			return 0;
		}
		return 0;
	}

	@Override
	public String toString() {

		String string = new String();
		string = string + "CHROMOSOME ---> NR OF BINS " + bins.size() + " --->";

		int nrOfElements = 0;
		for (Bin bin : bins) {

			nrOfElements = nrOfElements + bin.getElements().size();
		}

		string = string + "NR OF ELEMENTS: " + nrOfElements + "--->";

		for (Bin bin : bins) {
			string = string + "[";
			for (Element element : bin.getElements()) {
				string = string + (int) element.getValue() + " ";
			}
			string = string + "]";
		}

		return string;

	}

}
