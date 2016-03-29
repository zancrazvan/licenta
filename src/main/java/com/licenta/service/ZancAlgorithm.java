package com.licenta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.licenta.document.Aparat;
import com.licenta.document.House;

public class ZancAlgorithm {

	public void zancAlgorithm(House house, Map<Date, Float> dso) {

		Map<Date, List<Aparat>> plan = new HashMap<>();

		for (Date date : dso.keySet()) {

			float putereDSO = dso.get(date);
			List<Aparat> aparate = getAparatByPutere(putereDSO, house);

			for (Aparat aparat : aparate) {

			}

		}

	}  

	public static List<Aparat> getAparatByPutere(float putere, House house) {

		if (house.getAparate().containsKey(putere)) {

			return house.getAparate().get(putere);
		}

		return null;
	}

	public static List<Aparat> getAparateCuPutereMaiMica(float putere, House house) {

		// toate permutarile de aparate a caror suma puterilor < @putere

		return null;
	}

}
