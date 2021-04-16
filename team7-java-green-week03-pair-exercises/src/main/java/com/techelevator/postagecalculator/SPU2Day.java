package com.techelevator.postagecalculator;

public class SPU2Day implements DeliveryDriver {

	@Override
	public double calculateRate(int distance, double weight) {
		
		 double rate= (weight * 0.050) * distance; 
			
			
			return rate;
	
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
