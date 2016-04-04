package com;

import java.util.*;

public class Trainer {
	public static BPNeuralNetwork train(BPNeuralNetwork bPNN, List<Data> trainingList) {
		
		Iterator<Data> iterator;
		
		for (int i = 0; i < 50; i++){
			iterator = trainingList.iterator();
			Data data;
			while (iterator.hasNext()) {
				data = iterator.next();
				bPNN.forwardPass(data);
				//System.out.println("output: " + bPNN.forwardPass(data));
				bPNN.reversePass(data);
			}
		}
		
		return bPNN;
	}
}
