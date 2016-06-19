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

	public void addFreeItems(List<Element> elements, Chromosome chromosome) {

		Collections.sort(elements, Collections.reverseOrder());
		firstFitElement(elements, chromosome);
	}

	public void insertRandom(List<Element> elements, List<Bin> bins, Chromosome chromosome) {

		Collections.shuffle(elements);
		firstFitElement(elements, bins, chromosome);
	}

	public void firstFitElement(List<Element> elements, List<Bin> bins, Chromosome chromosome) {

		BinDAO binDAO = new BinDAO();

		chromosome.getBins().clear();

		for (int i = 0; i < bins.size(); i++) {
			Bin bin2 = new Bin();
			bin2.setId(i);
			bin2.setCapacity(bins.get(i).getCapacity());
			chromosome.getBins().add(bin2);
		}

		for (Element element : elements) {

			boolean added = false;
			for (Bin bin : chromosome.getBins()) {

				Bin test = new Bin();
				test.setCapacity(bin.getCapacity());
				List<Element> elements2 = new ArrayList<>();
				elements2.add(element);
				test.setElements(elements2);

				if ((binDAO.getFilled(test) <= bin.getCapacity() - binDAO.getFilled(bin)) && added == false
						&& !bin.getElements().contains(elements2) && !existsInBin(bin, element)) {

					bin.getElements().add(element);
					added = true;
				}

			}

		}

	}

	public void firstFitElement(List<Element> elements, Chromosome chromosome) {

		BinDAO binDAO = new BinDAO();

		for (Element element : elements) {

			boolean added = false;
			for (Bin bin : chromosome.getBins()) {

				Bin test = new Bin();
				test.setCapacity(bin.getCapacity());
				List<Element> elements2 = new ArrayList<>();
				elements2.add(element);
				test.setElements(elements2);

				if ((binDAO.getFilled(test) <= bin.getCapacity() - binDAO.getFilled(bin)) && added == false
						&& !bin.getElements().contains(elements2) && !existsInBin(bin, element)) {

					bin.getElements().add(element);
					added = true;
				}

			}

		}

	}

	public Point computeCrossoverPoints(int delta, Chromosome chromosome) {

		Point point = new Point();

		if (chromosome.getBins().size() == 0) {

			return null;
		} else {

			point.setX(Randomizer.generate(0, chromosome.getBins().size() - 1));

			if (point.getX() == (chromosome.getBins().size() - 1)) {

				point.setY(chromosome.getBins().size());
			} else {

				point.setY(Randomizer.generate(point.getX(), chromosome.getBins().size()));
			}

		}
		return point;

	}

	public int getCrossoverPoint(Chromosome chromosome) {
		return Randomizer.generate(1, chromosome.getBins().size() - 1);
	}

	public List<Bin> genBinsByDivision1(Chromosome chromosome, Point point) {

		List<Bin> bins = new ArrayList<>();
		for (int i = point.getX(); i < point.getY(); i++) {

			bins.add(chromosome.getBins().get(i));
		}

		return bins;

	}

	public List<Bin> genBinsByDivision2(Chromosome chromosome, int point) {

		List<Bin> bins = new ArrayList<>();
		for (int i = point; i < chromosome.getBins().size(); i++) {

			bins.add(chromosome.getBins().get(i));
		}

		return bins;

	}

	public void insertBinsOnPos1(List<Bin> bins, Chromosome chromosome, Point point) {

		List<Element> elements = new ArrayList<>();
		List<Element> oldElements = new ArrayList<>();
		BinDAO binDAO = new BinDAO();

		for (Bin bin : chromosome.getBins()) {
			for (Bin bin2 : bins) {

				if (bin.getId() == bin2.getId()) {
					if (binDAO.getFitness(bin2) > binDAO.getFitness(bin)) {
						oldElements.addAll(bin.getElements());
						bin.setElements(bin2.getElements());
					}
				}
			}
		}

		for (Bin bin : chromosome.getBins()) {
			for (Bin bin2 : bins) {
				if (!bin.equals(bin2) && (binDAO.getFitness(bin2) < binDAO.getFitness(bin))) {

					List<Element> toRemove = new ArrayList<>();

					for (Element element : bin.getElements()) {
						for (Element element2 : bin2.getElements()) {

							if ((element.getId() == element2.getId()) && (element.getValue() == element2.getValue())) {
								toRemove.add(element);
								bin.setMarkedForDelete(true);
							}
						}
					}
					bin.getElements().removeAll(toRemove);

				}
			}
		}

		for (Bin bin : chromosome.getBins()) {
			if (bin.isMarkedForDelete()) {
				elements.addAll(bin.getElements());
				bin.getElements().clear();
			}
		}

		addFreeItems(elements, chromosome);
		addFreeItems(oldElements, chromosome);

	}

	public void insertBinsOnPos2(List<Bin> bins, Chromosome chromosome, int point) {

		List<Bin> toRemove = new ArrayList<>();
		List<Element> elements = new ArrayList<>();
		for (int i = point; i < chromosome.getBins().size(); i++) {

			toRemove.add(chromosome.getBins().get(i));
			elements.addAll(chromosome.getBins().get(i).getElements());
		}
		chromosome.getBins().removeAll(toRemove);
		chromosome.getBins().addAll(chromosome.getBins().size(), bins);

		for (Bin bin : chromosome.getBins()) {
			for (Bin bin2 : bins) {

				if (!bin.equals(bin2)) {

					for (Element element : bin.getElements()) {
						if (!elements.contains(element)) {

							elements.add(element);
						}
					}

				}

			}
			bin.getElements().clear();
		}

		List<Bin> newBins = new ArrayList<>();

		for (Bin bin : chromosome.getBins()) {
			if (bin.isMarkedForDelete()) {
				elements.addAll(bin.getElements());
				bin.getElements().clear();
			} else {
				// newBins.add(bin);
			}

		}
		// chromosome.setBins(newBins);
		addFreeItems(elements, chromosome);

	}

	public void deleteDuplicatesByBins(List<Bin> bins, Chromosome chromosome) {

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
		addFreeItems(freeElements, chromosome);

	}

	public boolean contains(List<Bin> bins, Element element) {

		for (Bin bin : bins) {

			for (Element element2 : bin.getElements()) {
				if (element2.getId() == element.getId() && element2.getValue() == element.getValue()) {

					return true;
				}
			}
		}
		return false;
	}

	public boolean existsInBin(Bin bin, Element element) {

		for (Element element2 : bin.getElements()) {

			if (element.getName() != null || element2.getName() != null) {
				if (element2.getName().equals(element.getName())) {
					return true;
				}
			}
		}
		return false;
	}

}
