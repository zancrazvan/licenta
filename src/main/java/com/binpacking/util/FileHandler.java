package com.binpacking.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.binpacking.element.Element;

public class FileHandler {

	public static List<Element> readFile(String filename) {

		List<Element> elements = new ArrayList<>();

		try {

			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = null;

			String[] data = new String[2];
			for (int i = 0; i < 2; i++) {
				data[i] = br.readLine();
			}
			String dimension = data[0];

			int numberOfElements = Integer.parseInt(dimension.trim());

			for (int i = 0; i < numberOfElements; i++) {

				line = br.readLine();

				Element element = new Element();
				element.setId(i);
				element.setValue(Integer.parseInt(line.trim()));

				elements.add(element);

			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return elements;
	}

	public void write(String text, String filename) {

		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), false);
			BufferedWriter writer = new BufferedWriter(fileWritter);

			writer.write(text);
			writer.write("\r\n");

			writer.close();
		} catch (IOException ex) {
			// report
		} finally {
			try {

			} catch (Exception ex) {/* ignore */

			}
		}
	}
}
