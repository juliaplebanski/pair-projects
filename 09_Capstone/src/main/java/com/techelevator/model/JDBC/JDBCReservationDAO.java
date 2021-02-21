package com.techelevator.model.JDBC;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.dao.ReservationDAO;

import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.VenueSpace;

	
	public class JDBCReservationDAO implements ReservationDAO {

		private JdbcTemplate jdbcTemplate;
		
		

		public JDBCReservationDAO(DataSource dataSource) {
			
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

		
        //used to get the first 5 available venue spaces by date
		@Override 
		public List<VenueSpace> getAvaliableVenueSpacesByDate(long id, LocalDate startDate, LocalDate endDate) {
			List<VenueSpace> venueSpace = new ArrayList<>();
			String selectSQL = "SELECT venue_id, space.id, space.open_from, space.open_to, space.name, max_occupancy, is_accessible, CAST(space.daily_rate AS decimal) "
					+ "FROM space JOIN venue on space.venue_id = venue.id where space.id = ? and space.id "
					+ "not in(SELECT space.id FROM reservation WHERE (?, ?) overlaps (start_date, end_date) group by space.id) LIMIT 5";
			SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL, id, startDate, endDate.plusDays(1));

			while(results.next()) {
				venueSpace.add(mapRowToVenueSpace(results));
			}
			return venueSpace;
		}
		
		//used to create a reservation
		@Override
		public Reservation createReservation(Reservation reservation) {
		
			String insertSql = "INSERT INTO reservation(space_id, number_of_attendees, start_date, end_date, reserved_for)"
					+ " VALUES(?,?,?,?,?)";
			reservation.setReservationID(getNextReservationID());
			jdbcTemplate.update(insertSql, reservation.getSpaceID(),reservation.getNumOfAttendees(), reservation.getStartDate(),reservation.getEndDate(), reservation.getName());
		  return reservation;
		}

		
		//used to search for a reservation for a reservation by reservationID	
		@Override
		public Reservation getByReservationID(long reservationID) {
			Reservation reservation = null;
			String selectSql = "SELECT * FROM reservation WHERE reservation_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, reservationID);
			while(results.next()) {
				reservation = mapRowToReservation(results);
			}
			return reservation;
		}
		
		//used to get a list of all of the reservations
		@Override
		public List<Reservation> getAllReservations() {
			List<Reservation> reservationList = new ArrayList<Reservation>();
			String selectSQL = "SELECT * FROM reservation";
			SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL);
			while (results.next()) {
				Reservation reservation = mapRowToReservation(results);
				reservationList.add(reservation);
			}
			return reservationList;
			
		}
		
		//used to get the next reservationId
		public long getNextReservationID() {
			SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
			if(results.next()) {
				long id = results.getLong(1); //get the id at index 1
				return id;
			}
			else {
				throw new RuntimeErrorException(null, "Unable to retrieve reservation id!");
			}
			
		}
		
		//used to save a servation
		@Override
		public void saveReservation(Reservation newReservation) 
		{
			String sqlNewReservation = "UPDATE reservation SET reserved_for = ? WHERE reservation_id = ?";
			jdbcTemplate.update(sqlNewReservation, newReservation.getName(), newReservation.getReservationID());
		}
		
		//used to map the results row to properties of the venueSpace class
		private VenueSpace mapRowToVenueSpace(SqlRowSet results) {
			VenueSpace venueSpace = new VenueSpace();
			venueSpace.setSpaceID(results.getLong("id"));
			venueSpace.setVenueID(results.getLong("venue_id"));
			venueSpace.setOpen(results.getString("open_from"));
			venueSpace.setClosed(results.getString("open_to"));
			venueSpace.setName(results.getString("name"));
			venueSpace.setAccessible(results.getBoolean("is_accessible"));
			venueSpace.setDaily_rate(results.getDouble("daily_rate"));
			venueSpace.setMaxOccupancy(results.getLong("max_occupancy"));
			
			return venueSpace;
			
		}
		//used to map the results row to properties of the Reservation class
		private Reservation mapRowToReservation(SqlRowSet results) {
			Reservation reservation = new Reservation();
			reservation.setStartDate(results.getDate("start_date").toLocalDate());
			reservation.setEndDate(results.getDate("end_date").toLocalDate());
			reservation.setName(results.getString("reserved_for"));
			reservation.setReservationID(results.getLong("reservation_id"));
			reservation.setSpaceID(results.getLong("space_id"));
			return reservation;
		}



		

	
	
}	
