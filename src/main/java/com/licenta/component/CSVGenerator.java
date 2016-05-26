package com.licenta.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Component
public class CSVGenerator {

	public void writeToCsv(String filename, DeviceMock m) {
		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), false);
			BufferedWriter writer = new BufferedWriter(fileWritter);
			writer.write("sep=,");
			writer.write("\r\n");
			List<TimeSeriesBit> train = m.getCurba();
			Collections.sort(train);
			for (TimeSeriesBit t : train) {

				writer.write(t.getDate().toGMTString() + "," + t.getValue());

				writer.write("\r\n");

			}

			writer.close();
		} catch (IOException ex) {
			// report
		}
	}
}
