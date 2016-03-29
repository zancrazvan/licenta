package com.licenta.document;

import java.util.List;
import java.util.Map;

public class House {

	private int id;

	private Map<Float, List<Aparat>> aparate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Float, List<Aparat>> getAparate() {
		return aparate;
	}

	public void setAparate(Map<Float, List<Aparat>> aparate) {
		this.aparate = aparate;
	}

}
