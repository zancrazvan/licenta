package com.binpacking.chromosome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.bin.BinDAO;
import com.binpacking.element.Element;
import com.binpacking.util.Point;
import com.binpacking.util.Randomizer;

public class ChromosomeDAO {

	public static void addFreeItems(List<Element> elements, Chromosome chromosome) {

		Collections.sort(elements, Collections.reverseOrder());
		firstFitElement(elements, chromosome);
	}

	public static void insertRandom(List<Element> elements, Chromosome chromosome) {

		Collections.shuffle(elements);
		firstFitElement(elements, chromosome);
	}

	private static void firstFitElement(List<Element> elements, Chromosome chromosome) {

		if (chromosome.getBins().size() == 0) {
			chromosome.getBins().add(new Bin());
		}

		for (Element element : elements) {

			boolean added = false;
			for (Bin bin : chromosome.getBins()) {

				Bin test = new Bin();
				List<Element> elements2 = new ArrayList<>();
				elements2.add(element);
				test.setElements(elements2);

				if ((BinDAO.getFilled(test) <= bin.getCapacity() - BinDAO.getFilled(bin)) && added == false) {

					bin.getElements().add(element);
					added = true;
				}

			}
			if (added == false) {

				Bin bin = new Bin();
				bin.getElements().add(element);
				chromosome.getBins().add(bin);
			}

		}

	}

	public static Point computeCrossoverPoints(int delta, Chromosome chromosome) {

		Point point = new Point();

		if (chromosome.getBins().size() == 0) {

			return null;
		} else {

			point.setX(Randomizer.generate(0, chromosome.getBins().size() - 1));

			if (point.getX() == (chromosome.getBins().size() - 1)) {

				point.setY(chromosome.getBins().size());
			} else {

				point.setY(Randomizer.generate(point.getX(), point.getX() + delta));
			}

		}
		return point;

	}

	public static List<Bin> genBinsByDivision(Chromosome chromosome, Point point) {

		List<Bin> bins = new ArrayList<>();
		for (int i = point.getX(); i < point.getY(); i++) {

			bins.add(chromosome.getBins().get(i));
		}

		return bins;

	}

	public static void insertBinsOnPos(List<Bin> bins, Chromosome chromosome, int y) {

		if (y >= chromosome.getBins().size()) {
			y = chromosome.getBins().size();
		}
		chromosome.getBins().addAll(y, bins);

		// System.out.println("adaugate binuri " + chromosome.toString());
	}

	public static void deleteDuplicatesByBins(List<Bin> bins, Chromosome chromosome) {

		for (Bin bin : chromosome.getBins()) {

			List<Element> toRemove = new ArrayList<>();
			for (Bin bin2 : bins) {
				if (!bin.equals(bin2)) {

					for (Element element : bin.getElements()) {
						for (Element element2 : bin2.getElements()) {

							if (element.getId() == element2.getId() && element.getValue() == element2.getValue()) {

								bin.setMarkedForDelete(true);
								toRemove.add(element);
							}

						}
					}

				} else {
					bin.setMarkedForDelete(true);

				}

			}
			bin.getElements().removeAll(toRemove);

		}

		List<Element> freeElements = new ArrayList<>();
		List<Bin> newBins = new ArrayList<>();

		for (Bin bin : chromosome.getBins()) {
			if (bin.isMarkedForDelete()) {
				freeElements.addAll(bin.getElements());
			} else {
				newBins.add(bin);
			}

		}
		chromosome.setBins(newBins);
		ChromosomeDAO.addFreeItems(freeElements, chromosome);

	}

	public static boolean contains(List<Element> elements, Element element) {

		for (Element element2 : elements) {
			if (element2.getId() == element.getId() && element2.getValue() == element.getValue()) {

				return true;
			}
		}
		return false;
	}

}
