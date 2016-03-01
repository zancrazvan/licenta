package com.licenta.mockObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Device {

	private Map<Date, Integer> curba;
 

	public Map<Date, Integer> getCurba() {
		return curba;
	}

	public void setCurba(Map<Date, Integer> curba) {
		this.curba = curba;
	}

	@Override
	public String toString() {
		String toRet = new String();
		for (Entry<Date, Integer> e : this.curba.entrySet()) {
			toRet += e.getKey().toGMTString() + "     " + e.getValue() + "\n";
		}
		return toRet;
	}

}
