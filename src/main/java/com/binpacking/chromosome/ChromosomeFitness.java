package com.binpacking.chromosome;

import com.binpacking.bin.Bin;
import com.binpacking.bin.BinDAO;

public class ChromosomeFitness {

	public double computeChromosomeFitness(Chromosome chromosome) {

		BinDAO binDAO = new BinDAO();
		double fitness = 0;
		double sum = 0;
		int k = 2;

		for (Bin bin : chromosome.getBins()) {

			double fill = binDAO.getFilled(bin);
			double capacity = bin.getCapacity();
			sum = sum + Math.pow(fill / capacity, k);
		}

		fitness = sum / chromosome.getBins().size();

		return fitness;

	}

}
