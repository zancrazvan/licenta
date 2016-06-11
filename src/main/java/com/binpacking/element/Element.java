package com.binpacking.element;

public class Element implements Comparable<Element> {

	private int id;

	private double value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int compareTo(Element o) {

		ElementComparator elementComparator = new ElementComparator();
		return elementComparator.elementComparator(this, o);
	}

	@Override
	public String toString() {
		return "Element [value=" + value + "]";
	}

}
