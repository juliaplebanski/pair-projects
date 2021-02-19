package com.techelevator.model.JDBC;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.dao.ReservationDAO;

import com.techelevator.model.domain.Reservation;


public class JDBCReservationDAO {

	public class JDBCReservationAO implements ReservationDAO {

		private JdbcTemplate jdbcTemplate;

		public void JDBCReservationDAO(DataSource dataSource) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

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
		
        /**This is going to be a fun SQL statement so save for last.*/
		@Override
		public List<Reservation> getAvailiableReservations(Date startDate, Date endDate, long spaceID) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void createReservation(Reservation reservation) {
		
			String insertSql = "INSERT INTO reservation(space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES(?, ?, ?, ?, ?)";
			jdbcTemplate.update(insertSql, reservation.getSpaceID(), reservation.getStartDate(),reservation.getEndDate(),reservation.getName(), reservation.getNumOfAttendees());
		}

		
		/**This method searches for a reservation for a reservation by reservationID.*/	
		@Override
		public Reservation getReservation(long reservationID) {
			Reservation reservation = null;
			String selectSql = "SELECT * FROM reservation WHERE reservation_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, reservationID);
			while(results.next()) {
				reservation = mapRowToReservation(results);
			}
			return reservation;
		}
			

	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation reservation = new Reservation();
		reservation.setEndDate(results.getDate("start_date"));
		reservation.setName(results.getString("name"));
		reservation.setReservationID(results.getLong("reservation_id"));
		reservation.setSpaceID(results.getLong("space_id"));
		reservation.setStartDate(results.getDate("end_date"));

		return reservation;
	}

}
}	
