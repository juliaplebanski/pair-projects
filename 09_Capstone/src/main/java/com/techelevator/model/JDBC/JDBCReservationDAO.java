package com.techelevator.model.JDBC;

import java.time.LocalDate;
import java.util.ArrayList;
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

		@Override
		public List<Reservation> getAvailiableReservations(LocalDate startDate, LocalDate endDate, long spaceID) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void createReservation(Reservation reservation) {
			// TODO Auto-generated method stub

		}

		@Override
		public Reservation getReservation(long reservationID) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation reservation = new Reservation();
		// local date not working? reservation.setEndDate(results.getLocalDate);
		reservation.setName(results.getString("name"));
		reservation.setReservationID(results.getLong("reservation_id"));
		reservation.setSpaceID(results.getLong("space_id"));
		// local date not working? reservation.setStartDate(results.getLocalDate));

		return reservation;
	}
}
