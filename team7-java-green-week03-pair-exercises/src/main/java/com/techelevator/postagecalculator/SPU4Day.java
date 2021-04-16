package com.techelevator.postagecalculator;

public class SPU4Day implements DeliveryDriver {

	@Override
	public double calculateRate(int distance, double weight) {
	
		 double rate= (weight * 0.0050) * distance; 
			
		
		return rate;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
//If "4-Day Ground" then rate = (weight * 0.0050) * distance.
