package com.licenta.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.knapsack.Device;

public class DeviceGenerator {

	public static List<Device> generateDevices() {
		List<Device> devices = new ArrayList<Device>();
		
		Device d = new Device(1,250,false);
		d.setName("bec");
		devices.add(d);
		
		d = new Device(2,750,false);
		d.setName("cuptor microunde");
		devices.add(d);
		
		d = new Device(3,2500,false);
		d.setName("masina spalat");
		devices.add(d);
		
		d = new Device(4,1300,false);
		d.setName("uscator haine");
		devices.add(d);
		
		d = new Device(5,500,false);
		d.setName("frigider");
		devices.add(d);
		int i=5;
		
		i++;
		d = new Device(i,2300,false);
		d.setName("masina spalat vase");
		devices.add(d);
		
		i++;
		d = new Device(i,3000,false);
		d.setName("cupor electric");
		devices.add(d);
		
		i++;
		d = new Device(i,60,false);
		d.setName("bec");
		devices.add(d);
		
		
		return devices;
	}
	
	public static Map<Device,Integer> generateDevicesWithStartingTimes(){
		Map<Device,Integer> devi = new TreeMap<Device, Integer>();
		List<Device> devices = DeviceGenerator.generateDevices();
		
		for(Device d: devices){
			devi.put(d, devices.indexOf(d)*2+1);
		}
		
		
		return devi;
	}
}
