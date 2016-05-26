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

			/*
			 * System.out.println("PONT CHR 1 " + pointChromosome1.getY() +
			 * " POINT CHR 2 " + pointChromosome2.getX());
			 * 
			 * System.out.println("CROSSOVER INTRE: " + chromosome1.toString() +
			 * " SI " + chromosome2.toString());
			 */

			if (pointChromosome1 != null && pointChromosome2 != null) {

				List<Bin> bins1 = ChromosomeDAO.genBinsByDivision(chromosome1, pointChromosome1);
				List<Bin> bins2 = ChromosomeDAO.genBinsByDivision(chromosome2, pointChromosome2);

				ChromosomeDAO.insertBinsOnPos(bins2, chromosome1, pointChromosome1.getY());
				ChromosomeDAO.insertBinsOnPos(bins1, chromosome2, pointChromosome2.getX());

				ChromosomeDAO.deleteDuplicatesByBins(bins2, chromosome1);
				// System.out.println("---");
				ChromosomeDAO.deleteDuplicatesByBins(bins1, chromosome2);
			}

			// System.out.println("DUPA CROSSOVER: " + chromosome1.toString() +
			// " SI " + chromosome2.toString());

			/*
			 * ChromosomeDAO.addFreeItems(chromosome1);
			 * ChromosomeDAO.addFreeItems(chromosome2);
			 */
		}
	}

}
