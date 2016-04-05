package com451;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	int leftWeight,
		leftDistance,
		rightWeight,
		rightDistance,
		output;
	
	public Data(int leftWeight, int leftDistance, int rightWeight, int rightDistance, int output) {
		this.leftWeight = leftWeight;
		this.leftDistance = leftDistance;
		this.rightWeight = rightWeight;
		this.rightDistance = rightDistance;
		this.output = output;
	}

	public int getLeftWeight() {
		return leftWeight;
	}

	public void setLeftWeight(int leftWeight) {
		this.leftWeight = leftWeight;
	}

	public int getLeftDistance() {
		return leftDistance;
	}

	public void setLeftDistance(int leftDistance) {
		this.leftDistance = leftDistance;
	}

	public int getRightWeight() {
		return rightWeight;
	}

	public void setRightWeight(int rightWeight) {
		this.rightWeight = rightWeight;
	}

	public int getRightDistance() {
		return rightDistance;
	}

	public void setRightDistance(int rightDistance) {
		this.rightDistance = rightDistance;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}
}
