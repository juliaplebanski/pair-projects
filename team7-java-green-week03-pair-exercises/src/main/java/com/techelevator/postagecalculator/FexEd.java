package com.techelevator.postagecalculator;

public class FexEd implements DeliveryDriver {

	
	
	
	@Override
	public double calculateRate(int distance, double weight) {
		int rate= 20;
		
		if (distance > 500 && weight > 48) {
			rate= rate + 8;
			return rate;
		}
		if (distance > 500) {
			rate= rate+ 5;
			return rate;
		}
		if (weight > 48) {
			rate= rate +3;
			return rate;
		}
		
		else {
			return rate;
		}
	}
	//// command line program notes on .ignorecase

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}


/*rate = 20.00
If distance > 500 miles then rate = rate + 5.00
If weight > 48 ounces then rate = rate + 3.00
*/
