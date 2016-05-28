package com.binpacking.chromosome;

import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.util.Point;
import com.binpacking.util.Randomizer;

public class ChromosomeCrossover {

	public static void crossover(Chromosome chromosome1, Chromosome chromosome2, int probability) {

		if (Randomizer.generate(0, 100) < probability) {
			Point pointChromosome1 = ChromosomeDAO.computeCrossoverPoints(2, chromosome1);
			Point pointChromosome2 = ChromosomeDAO.computeCrossoverPoints(2, chromosome2);

			if (pointChromosome1 != null && pointChromosome2 != null) {

				int crossoverPoint = ChromosomeDAO.getCrossoverPoint(chromosome1);

				System.out.println("CROSS INTRE: " + chromosome1.toString() + " SI " + chromosome2.toString()
						+ " LA POINT: " + crossoverPoint);
				List<Bin> bins1 = ChromosomeDAO.genBinsByDivision1(chromosome1, crossoverPoint);
				List<Bin> bins2 = ChromosomeDAO.genBinsByDivision2(chromosome2, crossoverPoint);

				ChromosomeDAO.insertBinsOnPos2(bins2, chromosome1, crossoverPoint);
				ChromosomeDAO.insertBinsOnPos1(bins1, chromosome2, crossoverPoint);

				//ChromosomeDAO.deleteDuplicatesByBins(bins2, chromosome1);
				//ChromosomeDAO.deleteDuplicatesByBins(bins1, chromosome2);
			}

		}
	}

}
