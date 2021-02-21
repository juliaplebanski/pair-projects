package com.techelevator.model.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;


public class Reservation {
	
	private long reservationID;
	private String name;
	private long spaceID;
	private LocalDate startDate;
	private LocalDate endDate;
	private int numOfAttendees;
	private int lengthOfStay;
	
	
	public int getLengthOfStay() {
		return lengthOfStay;
	}
	public void setLengthOfStay(int lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}
	public int getNumOfAttendees() {
		return numOfAttendees;
	}
	public void setNumOfAttendees(int numOfAttendees) {
		this.numOfAttendees = numOfAttendees;
	}
	public long getReservationID() {
		return reservationID;
	}
	public void setReservationID(long reservationID) {
		this.reservationID = reservationID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSpaceID() {
		return spaceID;
	}
	public void setSpaceID(long spaceID) {
		this.spaceID = spaceID;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate start_date) {
		this.startDate = start_date;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate end_date) {
		this.endDate = end_date;
	}
	
	
	

}
