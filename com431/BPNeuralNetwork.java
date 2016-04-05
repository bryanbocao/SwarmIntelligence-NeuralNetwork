package com431;

public class BPNeuralNetwork {
	
	//parameters
	private int inputLayerSize;
	private int hiddenLayerSize;
	private int outputLayerSize;
	
	//learning rate
	private double learningRate;
	
	//weights
	private double[][] weightsL0toL1;
	private double[] weightsL1toL2;
	
	//input is the sum of all inputs of a neuron, inputL1 is the array of the sums to Layer1
	//output is the output of a neuron after executing an activation function
	private double[] inputL1, outputL1;
	private double inputL2, outputL2, output;
	
	private double deltaL2;
	private double[] deltaL1, deltaL0;
	
	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	//initialize parameters
	public BPNeuralNetwork(){
		
		this.inputLayerSize = 4;
		this.hiddenLayerSize = 3;
		this.outputLayerSize = 1;
		
		this.learningRate = 0.0001;
		
		weightsL0toL1 = new double[inputLayerSize][hiddenLayerSize];
		weightsL1toL2 = new double[hiddenLayerSize];
		
		//initialize weights
		for (int i = 0; i < inputLayerSize; i++) {
			for (int j = 0; j < hiddenLayerSize; j++) {
				//initialize weights from -1 to 1 
				weightsL0toL1[i][j] = Math.random()*2 - 1;
				System.out.println("initializing weightsL0toL1[i][j]: " + weightsL0toL1[i][j]);
			}
		}
		
		for (int i = 0; i < hiddenLayerSize; i++) {
			//initialize weights from -1 to 1 
			weightsL1toL2[i] = Math.random()*2 - 1;
			System.out.println("initializing weightsL1toL2[i]: " + weightsL1toL2[i]);
		}
		
		inputL1 = new double[hiddenLayerSize];
		outputL1 = new double[hiddenLayerSize];
		
		deltaL2 = 0;
		deltaL1 = new double[hiddenLayerSize];
		deltaL0 = new double[inputLayerSize];
		
	}
	
	public static double sigmoid(double x) {
		return (1 / (1 + Math.pow(Math.E, (-1*x)))) * 2 - 1;
	}
	
	public static int binary(double x) {
		if (x <= 0)
			return 0;
//		else if (x > 0)
//			return 1;
		else
			return 1;
	}
	
	public double forwardPass(Data data) {
		
		for (int i = 0; i < hiddenLayerSize; i++) {
			this.inputL1[i] = 0.0;
			this.inputL1[i] += this.weightsL0toL1[0][i] * data.getLeftWeight();
//			System.out.println("this.weightsL0toL1[0][i]: " + this.weightsL0toL1[0][i]);
			this.inputL1[i] += this.weightsL0toL1[1][i] * data.getLeftDistance();
			this.inputL1[i] += this.weightsL0toL1[2][i] * data.getRightWeight();
			this.inputL1[i] += this.weightsL0toL1[3][i] * data.getRightDistance();
			
//			System.out.println("this.inputL1[i]: " + this.inputL1[i]);
			this.outputL1[i] = this.sigmoid(this.inputL1[i]);
//			System.out.println("this.outputL1[i]: " + this.outputL1[i]);
//			this.outputL1[i] = this.binary(this.outputL1[i]);

		}
		
		this.inputL2 = 0.0;
		for (int i = 0; i < hiddenLayerSize; i++) {
			this.inputL2 += this.weightsL1toL2[i] * outputL1[i];
		}
		this.outputL2 = this.sigmoid(this.inputL2);
//		this.outputL2 = this.binary(this.inputL2);
		this.output = outputL2;
//		System.out.println(this.output);
		return this.output;
	}
	
	public void reversePass(Data data) {
		int target = data.getOutput();
//		System.out.println(target);
		
		double output = forwardPass(data);
		
		//calculate errors of output neuron
		this.deltaL2 = output * (1 - output) * (target - output);
//		System.out.println("deltaL2: " + this.deltaL2);
		
		//change weights from L1 to L2
		for (int i = 0; i < hiddenLayerSize; i++) {
//			this.weightsL1toL2[i] = this.weightsL1toL2[i] + learningRate * deltaL2 * output;
			this.weightsL1toL2[i] += learningRate * deltaL2 * output;
		}
		
		//calculate (back-propagate) hidden layer errors
		for (int i = 0; i < hiddenLayerSize; i++) {
			this.deltaL1[i] = outputL1[i] * (1 - outputL1[i]) * deltaL2 * weightsL1toL2[i];
//			System.out.println("deltaL1[i]: " + this.deltaL1[i]);
		}
		
		//change weights from L0 to L1
		for (int i = 0; i < hiddenLayerSize; i++) {
//			this.weightsL0toL1[0][i] = weightsL0toL1[0][i] + learningRate * deltaL1[i] * data.getLeftWeight();
//			this.weightsL0toL1[1][i] = weightsL0toL1[1][i] + learningRate * deltaL1[i] * data.getLeftDistance();
//			this.weightsL0toL1[2][i] = weightsL0toL1[2][i] + learningRate * deltaL1[i] * data.getRightWeight();
//			this.weightsL0toL1[3][i] = weightsL0toL1[3][i] + learningRate * deltaL1[i] * data.getRightDistance();
			this.weightsL0toL1[0][i] += learningRate * deltaL1[i] * data.getLeftWeight();
			this.weightsL0toL1[1][i] += learningRate * deltaL1[i] * data.getLeftDistance();
			this.weightsL0toL1[2][i] += learningRate * deltaL1[i] * data.getRightWeight();
			this.weightsL0toL1[3][i] += learningRate * deltaL1[i] * data.getRightDistance();
		}
		
//		this.printWeights();
		
	}
	
	public void printWeights() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.println("weightsL0toL1 " + this.weightsL0toL1[i][j]);
				System.out.println();
			}
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.println("weightsL1toL2 " + this.weightsL1toL2[i]);
		}
	}
	
					
}
