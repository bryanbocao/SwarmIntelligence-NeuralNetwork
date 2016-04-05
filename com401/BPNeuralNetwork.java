package com401;

public class BPNeuralNetwork {
	
	//parameters
	private int inputLayerSize;
//	private int hiddenLayerSize;
	private int outputLayerSize;
	
	//learning rate
	private double learningRate;
	
	//weights
	private double[] weightsL0toL1;
//	private double[] weightsL1toL2;
	
	//input is the sum of all inputs of a neuron, inputL1 is the array of the sums to Layer1
	//output is the output of a neuron after executing an activation function
	private double[] inputL1;
	private double outputL1, output;
	
	private double deltaL1;
	private double[] deltaL0;
	
	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	//initialize parameters
	public BPNeuralNetwork(){
		
		this.inputLayerSize = 4;
		this.outputLayerSize = 1;
		
		this.learningRate = 0.000000001;
		
		weightsL0toL1 = new double[inputLayerSize];
		
		//initialize weights
		for (int i = 0; i < inputLayerSize; i++) {
				//initialize weights from -1 to 1 
				weightsL0toL1[i] = Math.random()*2 - 1;
				System.out.println("initializing weightsL0toL1[i][j]: " + weightsL0toL1[i]);
		}
		
		inputL1 = new double[4];
		outputL1 = 0.0;
		
		deltaL1 = 0.0;
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
		
		for (int i = 0; i < 4; i++) {
			this.inputL1[i] = 0.0;
			this.inputL1[i] += this.weightsL0toL1[0] * data.getLeftWeight();
//			System.out.println("this.weightsL0toL1[0][i]: " + this.weightsL0toL1[0][i]);
			this.inputL1[i] += this.weightsL0toL1[1] * data.getLeftDistance();
			this.inputL1[i] += this.weightsL0toL1[2] * data.getRightWeight();
			this.inputL1[i] += this.weightsL0toL1[3] * data.getRightDistance();
			
//			System.out.println("this.inputL1[i]: " + this.inputL1[i]);
			this.outputL1 = this.sigmoid(this.inputL1[i]);
//			System.out.println("this.outputL1[i]: " + this.outputL1[i]);
//			this.outputL1[i] = this.binary(this.outputL1[i]);

		}
		
		this.output = outputL1;
		return this.output;
	}
	
	public void reversePass(Data data) {
		int target = data.getOutput();
//		System.out.println(target);
		
		double output = forwardPass(data);
		
		//calculate errors of output neuron
		this.deltaL1 = output * (1 - output) * (target - output);
		
		//change weights from L0 to L1
		for (int i = 0; i < 4; i++) {
			this.weightsL0toL1[i] += learningRate * deltaL1 * output;
		}
		
		
//		this.printWeights();
		
	}
	
	public void printWeights() {
		for (int i = 0; i < 4; i++) {
				System.out.println("weightsL0toL1 " + this.weightsL0toL1[i]);
				System.out.println();
		}
		
	}
	
					
}
