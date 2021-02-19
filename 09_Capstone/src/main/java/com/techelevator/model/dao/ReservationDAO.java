package com.techelevator.model.dao;


import java.util.Date;
import java.util.List;

import com.techelevator.model.domain.Reservation;

public interface ReservationDAO {
	
	
	public List<Reservation>getAllReservations();
	
	//The following spaces are available based on your needs
	public List<Reservation>getAvailiableReservations(Date startDate, Date endDate, long spaceID);
	public void createReservation(Reservation reservation);
	public Reservation getReservation(long reservationID);
	
	
	
	

}
