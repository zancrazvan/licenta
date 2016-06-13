package com.binpacking.util;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.element.Element;

public class DeviceGenerator {

	public List<Element> generateDevices() {

		System.out.println("--- GENERATING DEVICES ---");
		
		List<Element> elements = new ArrayList<>();
		for (int i = 0; i < 700; i++) {

			Element element = new Element();
			element.setId(i);
			element.setValue(Randomizer.generate(150, 1600));
			elements.add(element);
		}
		return elements;
	}

}
