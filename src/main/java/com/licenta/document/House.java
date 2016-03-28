package com.licenta.document;

import java.util.List;

public class House {

	private int id;

	private List<Aparat> aparate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Aparat> getAparate() {
		return aparate;
	}

	public void setAparate(List<Aparat> aparate) {
		this.aparate = aparate;
	}

}
