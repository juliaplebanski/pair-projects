package com.techelevator.tollboothcalculator;

public class Car implements Vehicle {

	
	private boolean hasTrailer;
	
	
	
	public Car (boolean hasTrailer) {
		this.hasTrailer = hasTrailer;
	}
	
	
	
	
	@Override
	public double calculateToll(int distance) {
		double toll = distance * 0.02;
		
		if (hasTrailer) {
			toll= toll + 1.00;
		}
		return toll;
	}

	@Override
	public String getType() {
		return null;
	}

	public boolean isHasTrailer() {
		return hasTrailer;
	}

	
}
