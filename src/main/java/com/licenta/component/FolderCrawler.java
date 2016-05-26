package com.licenta.component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.licenta.document.Aparat;
import com.licenta.mockObject.DeviceMock;
import com.licenta.service.MockObjectService;

@Component
public class FolderCrawler {
	@Autowired
	private FileParser f;
	@Autowired
	private Converter c;
	@Autowired
	private MockObjectService mockObjectService;

	public void listFilesAndFilesSubDirectories(String directoryName) {
		File directory = new File(directoryName);

		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				DeviceMock m = f.readFile(file.getAbsolutePath());
				String p = file.getParent();
				String some[] = p.split(Pattern.quote("\\"));

				c.convert(some[some.length - 1], m);
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}
	}

	public List<Aparat> findAll(String directoryName) {
		File directory = new File(directoryName);
		List<Aparat> aparate = new ArrayList<Aparat>();

		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				DeviceMock m = f.readFile(file.getAbsolutePath());
				String p = file.getParent();
				String some[] = p.split(Pattern.quote("\\"));

				aparate.add(c.convert(some[some.length - 1], m));
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}

		return aparate;
	}

	public List<DeviceMock> findAllDevices(String directoryName) {
		File directory = new File(directoryName);
		List<DeviceMock> aparate = new ArrayList<DeviceMock>();

		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				DeviceMock m = f.readFile(file.getAbsolutePath());

				aparate.add(m);
			} else if (file.isDirectory()) {
				findAllDevices(file.getAbsolutePath());
			}
		}

		return aparate;
	}

	public List<DeviceMock> findAllDevicesThin(String directoryName) {
		System.out.println("in there");
		File directory = new File(directoryName);
		List<DeviceMock> aparate = new ArrayList<DeviceMock>();

		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				DeviceMock m = mockObjectService.thin(f.readFileThin(file
						.getAbsolutePath()));
				System.out.println(file.getName());
				aparate.add(m);
				return aparate;
			} else if (file.isDirectory()) {
				aparate.addAll(findAllDevicesThin(file.getAbsolutePath()));
			}
		}

		return aparate;
	}
}
