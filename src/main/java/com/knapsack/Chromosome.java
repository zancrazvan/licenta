package com.knapsack;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.licenta.entity.Device;
import com.licenta.entity.SwitchingTime;

public class Chromosome implements Comparable<Chromosome> {

	private Map<SwitchingTime, Device> solution;
	private int absoluteError;

	@Override
	public int compareTo(Chromosome o) {
		Integer crtError = this.absoluteError;
		Integer otherError = o.getAbsoluteError();
		return crtError.compareTo(otherError);
	}

	public int getAbsoluteError() {
		return absoluteError;
	}

	public void setAbsoluteError(int absoluteError) {
		this.absoluteError = absoluteError;
	}

	public Map<SwitchingTime, Device> getSolution() {
		return solution;
	}

	public void setSolution(Map<SwitchingTime, Device> solution) {
		this.solution = solution;
	}

	public void calculateError() {
		int error = 0;
		for (SwitchingTime s : this.solution.keySet()) {
			error += Math.abs(s.getRelativePower()
					- this.solution.get(s).getPower());
		}
		this.absoluteError = error;
	}

	public String toString() {
		String pri = new String();

		for (SwitchingTime s : this.solution.keySet()) {
			Device d = this.solution.get(s);
			pri += s.getTime() + " pwr: " + s.getRelativePower()
					+ "  chosenDevice: " + d.getPower() + " " + d.getName()
					+ " st: " + s.getTime() + " rt: " + s.getRunningTime()
					+ " \n";
		}
		return pri;
	}

	public void sortSolution() {
		 this.sortByValue(solution);
	}

	private void sortByValue(Map<SwitchingTime, Device> map) {
		List<Map.Entry<SwitchingTime, Device>> list = new LinkedList<Map.Entry<SwitchingTime, Device>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<SwitchingTime, Device>>() {
			public int compare(Map.Entry<SwitchingTime, Device> o1, Map.Entry<SwitchingTime, Device> o2) {
				Integer i1 = o1.getKey().getTime();
				Integer i2 = o2.getKey().getTime();
				return (i1.compareTo(i2));
			}
		});

		Map<SwitchingTime, Device> result = new LinkedHashMap<SwitchingTime, Device>();
		for (Map.Entry<SwitchingTime, Device> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		this.solution=result;

	}

}
