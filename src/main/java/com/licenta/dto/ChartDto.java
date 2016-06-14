package com.licenta.dto;

import java.util.List;

import com.licenta.entity.Device;
import com.licenta.entity.SwitchingTime;
import com.licenta.entity.TimeSlot;

public class ChartDto {

	private List<Device> devices;
	private List<SwitchingTime> switches;
	private List<TimeSlot> timeSlots;

	public List<SwitchingTime> getSwitches() {
		return switches;
	}

	public void setSwitches(List<SwitchingTime> switches) {
		this.switches = switches;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
}
