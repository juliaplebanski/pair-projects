package com.techelevator.tollboothcalculator;

public class Truck implements Vehicle {

	private int numberOfAxels;
	
	public Truck (int numberOfAxels) {
		this.numberOfAxels = numberOfAxels;
	}
	
	@Override
	public double calculateToll(int distance) {
		double ratePerMileFour = .04;
		double ratePerMileSix = .045;
		double ratePerMileEight = .048;
		double toll = distance; 
		
		if (numberOfAxels == 4 ) {
			toll = toll * ratePerMileFour;
		}
		if (numberOfAxels == 6) {
			toll = toll * ratePerMileSix;
		}
		else{
			toll = toll * ratePerMileEight;
		}
		return toll;
	}

	@Override
	public String getType() {
		
		return null;
	}

	public int getNumberOfAxels() {
		return numberOfAxels;
	}
	
	

}
