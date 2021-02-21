package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBC.JDBCVenueDAO;
import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.Venue;

public class VenueDAOTest extends DAOIntegrationTest {

	
	
	private static final long TEST_ID = 4000;
	private static final String TEST_NAME = "JJ hotel";
	

	
	private JDBCVenueDAO dao;


	@Before
	public void setup() {
		String sqlInsertVenue = "INSERT INTO VENUE (id, name, city_id, description) VALUES (?, ?, 3, 'The best hotel in the entire world')";
		String sqlInsertCategoryVenue = "INSERT INTO category_venue (venue_id, category_id) VALUES(?, 1)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sqlInsertVenue, TEST_ID, TEST_NAME);
		jdbcTemplate.update(sqlInsertCategoryVenue, TEST_ID);
		dao = new JDBCVenueDAO(getDataSource());
	}

	@Test
	public void return_list_of_venues() {
		List<Venue> results = dao.getAllVenues();
		assertNotNull(results);
		assertTrue(results.size() >= 1);
	}

	@Test
	public void return_venue_details_by_id() {
		
		List<Venue> results = dao.getVenueDetails(TEST_ID);

		assertNotNull(results);
		assertEquals(1, results.size());
		Venue Venue = results.get(0);
		assertVenuesAreEqual(Venue, Venue);

	}
	/*public void test_getVenueNameByID() {
		String venue = dao.getVenueNameByID(TEST_ID);
		assertNotNull(venue );
		assertTrue(venue.getName() == TEST_NAME );
		
	}*/

	private void assertVenuesAreEqual(Venue expected, Venue actual) {
		assertEquals(expected.getCategory(), actual.getCategory());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getLocation(), actual.getLocation());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getVenueID(), actual.getVenueID());
	}

}
