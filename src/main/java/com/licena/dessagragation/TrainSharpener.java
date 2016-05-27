package com.licena.dessagragation;

import java.util.ArrayList;
import java.util.List;

import com.licenta.mockObject.TimeSeriesBit;

public class TrainSharpener {

	public List<TimeSeriesBit> sharpen(List<TimeSeriesBit> train){
		List<TimeSeriesBit> newTrain = new ArrayList<TimeSeriesBit>();
		
		for(int i=0;i<train.size();i+=60){
			newTrain.add(train.get(i));
		}
		System.out.println(newTrain.size());
		return newTrain;
	}
}
