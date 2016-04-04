package com;
import java.util.*;

public class Tester {
	public static double Test(BPNeuralNetwork bPNN, List<Data> testList) {
		Iterator<Data> iterator = testList.iterator();
		Data data;
		double output;
		double diff;
		int correctCount = 0;
		while (iterator.hasNext()) {
			data = iterator.next();
			output = bPNN.forwardPass(data);
//			System.out.println("output: " + output);
			diff = Math.abs(data.getOutput() - output);
			
			System.out.println("data.getOutput(): " + data.getOutput());
			System.out.println("output: " + output);
			System.out.println("diff: " + diff);
			System.out.println();
			if (diff <= 0.5) {
				correctCount++;
			}
		}
		
		return ((double)correctCount) / ((double)testList.size());
	}
}
