package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import com.techelevator.model.JDBC.JDBCReservationDAO;
import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.VenueSpace;



public class ReservationDAOTest extends DAOIntegrationTest {
	private static final long TEST_ID = 2400;
	private JDBCReservationDAO dao;
	
	@Before
	public void setup() {
		String sqlInsertReservation = "INSERT INTO reservation (reservation_id, space_id, start_date, end_date, reserved_for) VALUES (?, 1, '2021-03-11', '2021-03-15','The Dawson Family')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sqlInsertReservation,TEST_ID);
		dao = new JDBCReservationDAO(getDataSource());
	}
	
	@Test
	public void test_get_All_Reservations() {
		List<Reservation>reservationList =dao.getAllReservations();
		assertNotNull(reservationList);
		assertTrue(reservationList.size() >= 1);
	}
	@Test
	public void test_createReservation() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(5);
		Reservation reservation = getReservation(TEST_ID, 1, LocalDate.of(2021, 03, 11), LocalDate.of(2021, 03, 15), "The Dawson Family");
		dao.saveReservation(reservation);
		Reservation createdReservation = dao.getByReservationID(reservation.getReservationID());
		assertNotEquals(null, reservation.getReservationID());
		assertReservationAreEqual(reservation, createdReservation);
	
	}
	@Test
	public void test_getAvaliableVenueSpacesByDate() {
		List<VenueSpace> results = dao.getAvaliableVenueSpacesByDate(1, LocalDate.of(2021, 06, 21), LocalDate.of(2021, 06, 30));
		assertNotNull(results);
		assertTrue(results.size() >= 1);
	}
	@Test
	public void test_get_Reservation_by_id() {
		Reservation reservation = dao.getByReservationID(TEST_ID);
		assertNotNull(reservation );
		assertTrue(reservation.getReservationID() == 2400);
	}	
	
	private Reservation getReservation(long id, long space_id, LocalDate start_date,  LocalDate end_date, String reserved_for ) {
		Reservation reservation = new Reservation();
		reservation.setReservationID(id);
		reservation.setSpaceID(space_id);
		reservation.setStartDate(start_date);
		reservation.setEndDate(end_date);
		reservation.setName(reserved_for);
		return reservation;	
	}
	
	private void assertReservationAreEqual(Reservation expected, Reservation actual) {
		assertEquals(expected.getReservationID(), actual.getReservationID());
		assertEquals(expected.getSpaceID(),actual.getSpaceID());
		assertEquals(expected.getNumOfAttendees(), actual.getNumOfAttendees());
		assertEquals(expected.getStartDate(), actual.getStartDate());
		assertEquals(expected.getEndDate(), actual.getEndDate());
		assertEquals(expected.getName(), actual.getName());
	}

	

	
	

}
