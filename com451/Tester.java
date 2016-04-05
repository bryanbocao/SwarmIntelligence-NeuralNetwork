package com451;
import java.util.*;

public class Tester {
	public static double Test(BPNeuralNetwork bPNN, List<Data> testList) {
		Iterator<Data> iterator = testList.iterator();
		Data data;
		double bPNNOutput;
//		double diff;
		int correctCount = 0;
		while (iterator.hasNext()) {
			data = iterator.next();
			bPNNOutput = bPNN.forwardPass(data);
//			System.out.println("output: " + output);
//			diff = Math.abs(data.getOutput() - output);
			
			System.out.println("data.getOutput(): " + data.getOutput());
			System.out.println("bPNN output: " + bPNNOutput);
//			System.out.println();
//			if (diff <= 1) {
//				correctCount++;
//			}
			if ((data.getOutput() < 0 && bPNNOutput < 0) ||
					(data.getOutput() > 0 && bPNNOutput > 0)){
						correctCount++;
			} else if (data.getOutput() == 0) {
				if (Math.abs(data.getOutput() - bPNNOutput) < 0.1 ) {
					correctCount++;
				}
			}
		}
		
		return ((double)correctCount) / ((double)testList.size());
	}
}
