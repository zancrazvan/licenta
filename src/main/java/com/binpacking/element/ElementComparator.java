package com.binpacking.element;

public class ElementComparator {

	public int elementComparator(Element firstElement, Element secondElement) {

		if (firstElement.getId() == secondElement.getId()) {

			return 0;
		} else {
			if (firstElement.getValue() == secondElement.getValue()) {

				return 0;
			} else {
				return (int) (firstElement.getValue() - secondElement.getValue());
			}
		}

	}

}
