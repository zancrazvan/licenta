package com.binpacking.bin;

import java.util.List;

import com.binpacking.element.Element;

public class BinDAO {

	public Element getElementByIndex(int index, Bin bin) {

		return bin.getElements().get(index);
	}

	public void addElement(Element element, Bin bin) {

		bin.getElements().add(element);
	}

	public void replaceAllElements(List<Element> elements, Bin bin) {

		bin.getElements().clear();
		bin.setElements(elements);
	}

	public void removeElement(int index, Bin bin) {

		bin.getElements().remove(index);
	}

	public void clearElements(Bin bin) {
		bin.getElements().clear();
	}

	public double getFilled(Bin bin) {

		double filled = 0;

		for (Element element : bin.getElements()) {

			filled = filled + element.getValue();
		}
		return filled;

	}

	public double getFitness(Bin bin) {
		
		double fitness = 0;
		int k = 2;

		double fill = getFilled(bin);
		double capacity = bin.getCapacity();

		fitness = Math.pow(fill / capacity, k);

		return fitness;
	}

}
