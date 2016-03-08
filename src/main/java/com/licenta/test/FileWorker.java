/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licenta.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class FileWorker {

	public void write(String filename, double[] instance) {

		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter writer = new BufferedWriter(fileWritter);

			for (int i = 0; i < instance.length; i++) {

				writer.write(Double.toString(instance[i]));
				writer.write("\r\n");

			}

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
