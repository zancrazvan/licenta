package com.binpacking.chromosome;

import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.util.Point;
import com.binpacking.util.Randomizer;

public class ChromosomeCrossover {

	public void crossover(Chromosome chromosome1, Chromosome chromosome2, int probability) {

		ChromosomeDAO chromosomeDAO = new ChromosomeDAO();

		if (Randomizer.generate(0, 100) < probability) {
			Point pointChromosome1 = chromosomeDAO.computeCrossoverPoints(2, chromosome1);
			Point pointChromosome2 = chromosomeDAO.computeCrossoverPoints(2, chromosome2);

			if (pointChromosome1 != null && pointChromosome2 != null) {

				List<Bin> bins1 = chromosomeDAO.genBinsByDivision1(chromosome1, pointChromosome1);
				List<Bin> bins2 = chromosomeDAO.genBinsByDivision1(chromosome2, pointChromosome1);

				chromosomeDAO.insertBinsOnPos1(bins2, chromosome1, pointChromosome1);
				chromosomeDAO.insertBinsOnPos1(bins1, chromosome2, pointChromosome1);

				System.out.println("CROSSOVER LA PCT: " + pointChromosome1.getX() + "," + pointChromosome1.getY());

			}

		}
	}

}
