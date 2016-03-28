package com.licenta.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.licenta.document.Aparat;
import com.licenta.document.House;

public class ZancAlgorithm {

	public void zancAlgorithm(House house, Map<Date, Float> dso) {

		Map<Float, List<Aparat>> plan;

		for (Date date : dso.keySet()) {

			float putereDSO = dso.get(date);
			List<Aparat> aparate = getAparatByPutere(putereDSO, house);
			
			

		}

	}

	public static List<Aparat> getAparatByPutere(float putere, House house) {

		return house.getAparate().get(putere);
	}

}
