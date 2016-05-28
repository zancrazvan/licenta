package com.binpacking.bin;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.element.Element;

public class Bin {

	private int id;

	private double capacity;

	private List<Element> elements = new ArrayList<>();

	private boolean markedForDelete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public boolean isMarkedForDelete() {
		return markedForDelete;
	}

	public void setMarkedForDelete(boolean markedForDelete) {
		this.markedForDelete = markedForDelete;
	}

	@Override
	public String toString() {

		String string = new String();
		for (Element element : elements) {
			string = string + element.toString();
		}

		return string;
	}

}
