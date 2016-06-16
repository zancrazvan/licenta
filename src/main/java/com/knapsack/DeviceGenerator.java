package com.knapsack;

import java.util.ArrayList;
import java.util.List;

import com.licenta.entity.Device;

public class DeviceGenerator {

	public static List<Device> generateDevices(int number){
		List<Device> devices = new ArrayList<>();
		 for(int i=0;i<number;i++){
			 devices.add(new Device(i,i*6,false));
		 }
		
		return devices;
		
	}
}
