package com451;

import java.util.Iterator;
import java.util.*;

public class Run {

	public static void main(String[] args) {
		
		BPNeuralNetwork bPNN = new BPNeuralNetwork();
		
		double[] accuracyRate = new double[10];
		List<Data> dataList = DataReader.readData("src/com451/balance-scale.data");
		int[][] dataListToArray = new int[dataList.size()][5];
//		System.out.println(dataList.size());
		
		//10-fold cross validation
		List<Data> trainingList,
				testList = new ArrayList<Data>();
		
		int testListSize = dataList.size() / 10;
//		System.out.println("testListSize: " + testListSize);
		
		//10 iterations for training and testing
		{
		int testListFirstIndex = 0, testListLastIndex = testListSize - 1;
		Iterator<Data> iterator;
		for (int i = 0; i < 10; i++) {
			iterator = dataList.iterator();
			trainingList = new ArrayList<Data>();
			testList = new ArrayList<Data>(); 
			int index = 0;
			Data data;
//			System.out.println("testListFirstIndex: " + testListFirstIndex);
//			System.out.println("testListLastIndex: " + testListLastIndex);
			while (iterator.hasNext()) {
				data = iterator.next();
//				System.out.println("testListFirstIndex: " + testListFirstIndex);
//				System.out.println("testListLastIndex" + testListLastIndex);
				if (index >= testListFirstIndex && index < testListLastIndex) {
					testList.add(data);
				} else {
					trainingList.add(data);
				}
				
				index++;
				
			}

			Trainer.train(bPNN, trainingList);
			accuracyRate[i] = Tester.Test(bPNN, testList);

//			accuracyRate[i] = Tester.Test(bPNN, trainingList);
			
//			System.out.println("trainingListSize: " +trainingList.size());
//			System.out.println("testListSize: " + testList.size());
			
			testListFirstIndex += testListSize;
			testListLastIndex += testListSize;
//			printList(testList);
//			System.out.println("end ====");
		}
		}
		
		printResult(accuracyRate);
		
	}

	public static void printList(List<Data> dataList) {
		Iterator<Data> iterator = dataList.iterator();
		Data data;
		while (iterator.hasNext()) {
			data = iterator.next();
			System.out.print(data.getOutput() + " ");
		}
	}
	
	public static void printResult(double[] accuracyRate) {
		double sumOfAccuracyRate = 0;
		for (int i = 0; i < accuracyRate.length; i++) {
			sumOfAccuracyRate += accuracyRate[i];
			System.out.println();
			System.out.print("Iteration " + (i + 1) + " Accuracy rate: " + (accuracyRate[i] * 100) + "%");
		}
		System.out.println();
		System.out.print("Average Accuracy Rate: " + ((sumOfAccuracyRate / 10) * 100) + "%");
	}
	
//	public static void printResult(double[] accuracyRate) {
//		for (int i = accuracyRate.length - 1; i >= 0; i--) {
//			System.out.println();
//			System.out.print("Iteration " + (10 - i) + " Accuracy rate: " + (accuracyRate[i] * 100) + "%");
//		}
//	}
}
