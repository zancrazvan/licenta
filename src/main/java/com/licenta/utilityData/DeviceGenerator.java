package com.licenta.utilityData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.licenta.entity.Device;
import com.licenta.repository.DeviceRepository;

@Service
public class DeviceGenerator {

	@Autowired
	private DeviceRepository deviceRepository;

	public static List<Device> generateDevices() {
		List<Device> devices = new ArrayList<Device>();

		Device d = new Device(1, 250, false);
		d.setName("bec 1");
		devices.add(d);

		d = new Device(2, 750, false);
		d.setName("cuptor microunde");
		devices.add(d);

		d = new Device(3, 2500, false);
		d.setName("masina spalat");
		devices.add(d);

		d = new Device(4, 1300, false);
		d.setName("uscator haine");
		devices.add(d);

		d = new Device(5, 500, false);
		d.setName("frigider");
		devices.add(d);
		int i = 5;

		i++;
		d = new Device(i, 2300, false);
		d.setName("masina spalat vase");
		devices.add(d);

		i++;
		d = new Device(i, 3000, false);
		d.setName("cupor electric");
		devices.add(d);

		i++;
		d = new Device(i, 60, false);
		d.setName("bec 2");
		devices.add(d);

		i++;
		d = new Device(i, 75, false);
		d.setName("bec 3");
		devices.add(d);

		return devices;
	}

	public static Map<Device, Integer> generateDevicesWithStartingTimes() {
		Map<Device, Integer> devi = new TreeMap<Device, Integer>();
		List<Device> devices = DeviceGenerator.generateDevices();

		for (Device d : devices) {
			devi.put(d, devices.indexOf(d) * 2 + 1);
		}

		return devi;
	}

	public void populateDB() {
		List<Device> devices = new ArrayList<Device>();

		Device d = new Device(1, 250, false);
		d.setName("bec 1");
		d.setPicturePath("resources/devicePictures/bec.jpg");
		devices.add(d);

		d = new Device(2, 750, false);
		d.setName("cuptor microunde");
		d.setPicturePath("resources/devicePictures/cuptor_microunde.jpg");
		devices.add(d);

		d = new Device(3, 2500, false);
		d.setName("masina spalat");
		d.setPicturePath("resources/devicePictures/masina_spalat.jpg");
		devices.add(d);

		d = new Device(4, 1300, false);
		d.setName("uscator haine");
		d.setPicturePath("resources/devicePictures/uscator_haine.jpg");
		devices.add(d);

		d = new Device(5, 500, false);
		d.setName("frigider");
		d.setPicturePath("resources/devicePictures/frigider.jpg");
		devices.add(d);
		int i = 5;

		i++;
		d = new Device(i, 2300, false);
		d.setName("masina spalat vase");
		d.setPicturePath("resources/devicePictures/masina_spalat_vase.jpg");
		devices.add(d);

		i++;
		d = new Device(i, 3000, false);
		d.setName("cupor electric");
		d.setPicturePath("resources/devicePictures/cuptor_electric.jpg");
		devices.add(d);

		i++;
		d = new Device(i, 60, false);
		d.setName("bec 2");
		d.setPicturePath("resources/devicePictures/bec.jpg");
		devices.add(d);

		i++;
		d = new Device(i, 75, false);
		d.setName("bec 3");
		d.setPicturePath("resources/devicePictures/bec.jpg");
		devices.add(d);

		i++;
		d = new Device(i, 24, false);
		d.setName("neon");

		devices.add(d);
		if (deviceRepository.findAll().size() != 0) {
			List<Device> dd = deviceRepository.findAll();
			for (Device dev : dd) {
				deviceRepository.delete(dev);
			}

		}
		for (Device device : devices) {
			deviceRepository.save(device);
		}

	}
}
