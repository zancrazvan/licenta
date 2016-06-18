package com.binpacking.element;

public class ElementComparator {

	public int elementComparator(Element firstElement, Element secondElement) {

		if (firstElement.getValue() < secondElement.getValue()) {
			return -1;
		}
		if (firstElement.getValue() > secondElement.getValue()) {
			return 1;
		}

		if (firstElement.getId() == secondElement.getId() && firstElement.getName().equals(secondElement.getName())) {
			return 0;
		}

		return 0;

	}

}
