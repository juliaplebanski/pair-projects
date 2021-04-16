package com.techelevator.postagecalculator;

public class PostalServiceFirstClass implements DeliveryDriver {
	

	
	

	@Override
	public double calculateRate(int distance, double weight) {
		//rate = per mile rate * distance
		double rate= distance * weight;
		
		double twoOz= 0.035;
		double eightOz=0.04;
		double fifteenOz= 0.047 ;
		double oneLb= 0.195;
		double eightLb=0.45;
		double overEight= 0.5;
		
		// if "o"
		
		
		if (weight <= 2) {
			rate= distance * twoOz;
			return rate;
		}
		if (weight > 2 && weight < 8) {
			rate= distance * eightOz;
			return rate;
		}
		if (weight > 8 && weight <15) {
			rate= distance * fifteenOz;
			return rate;
		}
		
		
		//////////////////
		/* if (weight <= 1) {
		 * rate= distance * oneLb;
		 * return rate;
		 * }
		 * 
		 * if (weight > 1 && weight < 8) {
		 * rate= distance * eightLb; 
		 * }
		 * 
		 * if (weight > 8) {
		 * rate= distance * overEight;
		 * return rate;
		 */
		
		return rate;
	}
	
	
////command line program notes on .ignorecase

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
