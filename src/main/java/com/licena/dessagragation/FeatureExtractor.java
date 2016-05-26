package com.licena.dessagragation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.licenta.document.Aparat;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Component
public class FeatureExtractor {

	public List<Aparat> extractFeatures(DeviceMock m) {
		List<Aparat> aparate = new ArrayList<Aparat>();

		List<TimeSeriesBit> currentTrain = new ArrayList<TimeSeriesBit>();
		List<TimeSeriesBit> consumptionPattern = m.getCurba();
		Collections.sort(consumptionPattern);
		consumptionPattern = destroySpikes(consumptionPattern);
		boolean start = true;
		for (int i = 1; i < consumptionPattern.size(); i++) {
			if (consumptionPattern.get(i - 1).getValue() == 0
					&& consumptionPattern.get(i).getValue() != 0) {
				start = true;

			}
			if (consumptionPattern.get(i - 1).getValue() != 0
					&& consumptionPattern.get(i).getValue() == 0) {
				start = false;
				Aparat a = new Aparat();
				Collections.sort(currentTrain);
				Date st = currentTrain.get(0).getDate();
				Date end = currentTrain.get(currentTrain.size()-1).getDate();
				a.setRunningTime((int) ((end.getTime() - st.getTime()) / 60000));
				long pwr = 0;
				for(TimeSeriesBit t : currentTrain ){
					pwr+=t.getValue();
				}
				a.setPutere(pwr/currentTrain.size());
				aparate.add(a);
				currentTrain = new ArrayList<TimeSeriesBit>();
			}

			if (start) {
				currentTrain.add(consumptionPattern.get(i));
			}

		}
		System.out.println(aparate.size());
		return aparate;

	}

	private List<TimeSeriesBit> destroySpikes(
			List<TimeSeriesBit> consumptionPattern) {

		List<TimeSeriesBit> newPattern = new ArrayList<TimeSeriesBit>();
		Collections.sort(consumptionPattern);
		for (int i = 0; i < 20; i++) {
			TimeSeriesBit t = consumptionPattern.get(i);
			t.setValue(0);
			newPattern.add(t);
		}

		for (int i = 20; i < consumptionPattern.size() - 10; i++) {
			if (consumptionPattern.get(i).getValue() != 0
					&& prev10zero(consumptionPattern, i)
					&& next10zero(consumptionPattern, i)) {
				TimeSeriesBit t = consumptionPattern.get(i);
				t.setValue(0);
			}
			newPattern.add(consumptionPattern.get(i));
		}
		return newPattern;
	}

	private boolean next10zero(List<TimeSeriesBit> consumptionPattern, int i) {
		int ctr = 0;
		for (int j = i; j < i + 10; j++) {
			if (consumptionPattern.get(j).getValue() == 0) {
				ctr++;
			}
		}

		if (ctr > 7) {
			return true;
		} else
			return false;
	}

	private boolean prev10zero(List<TimeSeriesBit> consumptionPattern, int i) {
		int ctr = 0;
		for (int j = i - 9; j < i; j++) {
			if (consumptionPattern.get(j).getValue() == 0) {
				ctr++;
			}
		}

		if (ctr > 7) {
			return true;
		} else
			return false;
	}
}
