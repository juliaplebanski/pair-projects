package com.techelevator.model.dao;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.VenueSpace;

public interface ReservationDAO {
	
    List<Reservation>getAllReservations();
    Reservation createReservation(Reservation reservation);
	Reservation getByReservationID(long reservationID);
	void saveReservation(Reservation newReservation);
	List<VenueSpace> getAvaliableVenueSpacesByDate(long id, LocalDate startDate, LocalDate endDate);
	
	
	
	

}
