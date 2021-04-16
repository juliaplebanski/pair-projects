package com.techelevator.postagecalculator;

public interface DeliveryDriver {

	public double calculateRate(int distance, double weight);
	
	String getName();
	
}
