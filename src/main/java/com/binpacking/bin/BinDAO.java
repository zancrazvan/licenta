package com.binpacking.bin;

import java.util.List;

import com.binpacking.element.Element;

public class BinDAO {

	public static Element getElementByIndex(int index, Bin bin) {

		return bin.getElements().get(index);
	}

	public static void addElement(Element element, Bin bin) {

		bin.getElements().add(element);
	}

	public static void replaceAllElements(List<Element> elements, Bin bin) {

		bin.getElements().clear();
		bin.setElements(elements);
	}

	public static void removeElement(int index, Bin bin) {

		bin.getElements().remove(index);
	}

	public static void clearElements(Bin bin) {
		bin.getElements().clear();
	}

	public static double getFilled(Bin bin) {

		double filled = 0;

		for (Element element : bin.getElements()) {

			filled = filled + element.getValue();
		}
		return filled;

	}

}
