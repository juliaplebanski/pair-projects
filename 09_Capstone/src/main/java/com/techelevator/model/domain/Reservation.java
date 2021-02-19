package com.techelevator.model.domain;

import java.sql.Date;


public class Reservation {
	
	private long reservationID;
	private String name;
	private long spaceID;
	private Date startDate;
	private Date endDate;
	private int numOfAttendees;
	
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date date) {
		this.endDate = date;
	}
	
	
	

}
