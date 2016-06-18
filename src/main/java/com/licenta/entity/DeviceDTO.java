package com.licenta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DeviceDTO {

	@Id
	@GeneratedValue
	private int id;

	private int idOriginal;
	private int putere;
	private String pathPoza;
	private int numarBeanuri;
	private String nume;

	public void createDTO(Device d, SwitchingTime s) {
		this.idOriginal = d.getId();
		this.putere = d.getPower();
		this.pathPoza = d.getPicturePath();
		this.numarBeanuri = s.getRunningTime();
		this.nume = d.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(int idOriginal) {
		this.idOriginal = idOriginal;
	}

	public int getPutere() {
		return putere;
	}

	public void setPutere(int putere) {
		this.putere = putere;
	}

	public int getNumarBeanuri() {
		return numarBeanuri;
	}

	public void setNumarBeanuri(int numarBeanuri) {
		this.numarBeanuri = numarBeanuri;
	}

	public String getPathPoza() {
		return pathPoza;
	}

	public void setPathPoza(String pathPoza) {
		this.pathPoza = pathPoza;
	}

	@Override
	public String toString() {
		return "DeviceDTO [id=" + id + ", idOriginal=" + idOriginal
				+ ", putere=" + putere + ", pathPoza=" + pathPoza
				+ ", numarBeanuri=" + numarBeanuri + "]";
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}
	

}
