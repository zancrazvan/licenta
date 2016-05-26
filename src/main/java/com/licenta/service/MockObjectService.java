package com.licenta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Service
public class MockObjectService {

	public DeviceMock thin(DeviceMock m){
		
		List<TimeSeriesBit> original = m.getCurba();
		List<TimeSeriesBit> thinnedOut = new ArrayList<TimeSeriesBit>();
		//max 70 values size/70 -> increment
		int increment = original.size()/70;
		for(int i=0;i<original.size();i+=increment){
			thinnedOut.add(original.get(i));
		}
		DeviceMock nw = new DeviceMock();
		nw.setCurba(thinnedOut);
		
		return nw;
	}
}
