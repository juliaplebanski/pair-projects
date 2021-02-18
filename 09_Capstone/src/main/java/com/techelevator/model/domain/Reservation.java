package com.techelevator.model.domain;

import java.time.LocalDate;

public class Reservation {
	
	private long reservationID;
	private String name;
	private long spaceID;
	private LocalDate startDate;
	private LocalDate endDate;
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
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	

}
