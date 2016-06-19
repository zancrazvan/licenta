package com.binpacking.element;

public class ElementComparator {

	public int elementComparator(Element firstElement, Element secondElement) {

		if (firstElement.getValue() < secondElement.getValue()) {
			return -1;
		}
		if (firstElement.getValue() > secondElement.getValue()) {
			return 1;
		}

		if (firstElement.getName() != null || secondElement.getName() != null) {
			if (firstElement.getId() == secondElement.getId()
					&& firstElement.getName().equals(secondElement.getName())) {
				return 0;
			}
		}

		if (firstElement.getName() != null || secondElement.getName() != null) {
			if (firstElement.getName().equals(secondElement.getName())) {
				return 0;
			}
		}

		if (firstElement.getId() == secondElement.getId()) {
			return 0;
		}

		return 0;

	}

}
