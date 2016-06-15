package com.licenta.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RazvanDTO {

	@Id
	@GeneratedValue
	private int id;

	@OneToMany(fetch = FetchType.EAGER)
	private List<DeviceDTO> devices;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<DeviceDTO> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceDTO> devices) {
		this.devices = devices;
	}

}
