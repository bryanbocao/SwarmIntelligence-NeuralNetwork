package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataReader {
	
	public static List<Data> readData(String fileName) {
		List<Data> dataList = new ArrayList<Data>();
		FileReader fileReader;
		File file = new File(fileName);
		//System.out.println(file.getAbsolutePath());
		String line = null;
		String[] strs;
		int[] dataInt;
		try{
			fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				strs = new String[5];
				strs = line.split(",");
				dataInt = new int[5];
				for (int i = 0; i < 5; i++) {
					if (strs[i].equals("B")) {
						dataInt[i] = 0;
					} else if (strs[i].equals("L")) {
						dataInt[i] = -1;
					} else if (strs[i].equals("R")) {
						dataInt[i] = 1;
					} else {
						dataInt[i] = Integer.parseInt(strs[i]);
					}
				}
				
				int output = dataInt[0];
				int leftWeight = dataInt[1],
						leftDistance = dataInt[2],
						rightWeight = dataInt[3],
						rightDistance = dataInt[4];
				Data dataInstance = new Data(leftWeight, leftDistance, rightWeight, rightDistance, output);
				dataList.add(dataInstance);
//				System.out.println(output);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dataList;
	}

	public static void main(String[] args) {

//		FileReader fileReader;
//		String fileName = "src/com/balance-scale.data";
//		File file = new File(fileName);
//		//System.out.println(file.getAbsolutePath());
//		String line = null;
//		String[] strs;
//		int[] dataInt;
//		try{
//			fileReader = new FileReader(fileName);
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			
//			while((line = bufferedReader.readLine()) != null) {
//				//System.out.println(line);
//				strs = new String[5];
//				strs = line.split(",");
//				dataInt = new int[5];
//				for (int i = 0; i < 5; i++) {
//					if (strs[i].equals("B")) {
//						dataInt[i] = 0;
//					} else if (strs[i].equals("L")) {
//						dataInt[i] = -1;
//					} else if (strs[i].equals("R")) {
//						dataInt[i] = 1;
//					} else {
//						dataInt[i] = Integer.parseInt(strs[i]);
//					}
//				}
//				
//				for (int i = 0; i < 5; i++) {
//					System.out.print(dataInt[i]);
//				}
//				System.out.println();
//				
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}

}
