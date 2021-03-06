package com.licenta.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.licenta.component.Converter;
import com.licenta.document.Aparat;
import com.licenta.mockObject.DeviceMock;

 
public class ListFilesUtil {
 
	FileParser f;
	 
	Converter c;

	/**
	 * List all the files and folders from a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public void listFilesAndFolders(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			System.out.println(file.getName());
		}
	}

	/**
	 * List all the files under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public void listFiles(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println(file.getName());
			}
		}
	}

	/**
	 * List all the folder under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public void listFolders(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
				System.out.println(file.getName());
			}
		}
	}

	/**
	 * List all files from a directory and its subdirectories
	 * 
	 * @param directoryName
	 *            to be listed
	 */
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
				String p = file.getParent();
				String some[] = p.split(Pattern.quote("\\"));

				aparate.add(m);
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}

		return aparate;
	}
}