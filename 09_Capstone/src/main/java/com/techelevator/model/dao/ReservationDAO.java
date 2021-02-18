package com.techelevator.model.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.domain.Reservation;

public interface ReservationDAO {
	
	
	public List<Reservation>getAllReservations();
	
	//The following spaces are available based on your needs
	public List<Reservation>getAvailiableReservations(LocalDate startDate, LocalDate endDate, long spaceID);
	public void createReservation(Reservation reservation);
	public Reservation getReservation(long reservationID);
	
	
	
	

}