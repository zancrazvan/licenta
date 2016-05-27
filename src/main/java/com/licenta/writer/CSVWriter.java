package com.licenta.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.licenta.mockObject.TimeSeriesBit;

public class CSVWriter {

	public void trainToCsv(String filename, List<TimeSeriesBit> train) {
		System.out.println("writnig");
		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter writer = new BufferedWriter(fileWritter);
			writer.write("sep=,");
			System.out.println(train.size());
			for (int i = 0; i < train.size(); i++) {
				writer.write(train.get(i).getDate() + ","
						+ train.get(i).getValue());
				 System.out.println(train.get(i).getDate() + ","
							+ train.get(i).getValue());
				writer.newLine();
			}

			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {

			} catch (Exception ex) {/* ignore */
				ex.printStackTrace();
			}
		}
	}
}
