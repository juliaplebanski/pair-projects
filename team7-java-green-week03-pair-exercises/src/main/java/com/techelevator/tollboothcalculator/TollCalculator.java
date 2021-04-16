package com.techelevator.tollboothcalculator;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class TollCalculator implements Vehicle{

	public static void main(String[] args) {
		TollCalculator app = new TollCalculator();
		app.run();
	}

	private int totalMilesTraveled;
	private double totalToolboothRevenue;
	
	public void run() {
		Vehicle carWithNoTrailer = new Car(false);
		Vehicle carWithTrailer = new Car(true);
		
		Vehicle truckFour = new Truck(4);
		Vehicle truckSix = new Truck(6);
		Vehicle truckEight = new Truck(8);
		
		Vehicle tank = new Tank();
		
		
		
//		public List<Vehicle> retrieveListOfVehicles(){
//		List<Vehicle> vehicles = new ArrayList<>();
//		for(Vehicle vehicle: vehicles) {
//			System.out.println(getType());}
//		}
	}
		
		
		
//		
//	public List<Vehicle> retrieveListOfVehicles() {
//		
//		List<Vehicle> vehicles = new ArrayList<>();
//		
//		for(Vehicle vehicle: vehicles) {
//			System.out.println(getType());
//			
//		}
//
//		return vehicles;
//	}
			
		

	@Override
	public double calculateToll(int distance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
		
		
	}

