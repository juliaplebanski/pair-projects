package com.techelevator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.JDBC.JDBCVenueSpaceDAO;
import com.techelevator.model.domain.VenueSpace;


public class VenueSpaceDAOTest extends DAOIntegrationTest {
	
	private static final long TEST_VENUE_ID = 1;
	private static final long TEST_SPACE_ID = 2700;
	private JDBCVenueSpaceDAO dao;
	
	
	@Before
	public void setup() {
		String sqlInsertVenue = "INSERT INTO space (venue_id,id, name, is_accessible, open_from, open_to, daily_rate, max_occupancy) VALUES (?,?,'Bora Bora Room','true', 8, 10, 280, 200.00)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sqlInsertVenue,TEST_VENUE_ID, TEST_SPACE_ID);
		dao = new JDBCVenueSpaceDAO(getDataSource());
	}
	
	@Test
	public void test_get_all_spaces() {
		List<VenueSpace>venueSpaceList = dao.getAllSpacesForVenue( TEST_VENUE_ID);
		assertNotNull(venueSpaceList);
		assertTrue(venueSpaceList.size() >= 1);
	}

	@Test
	public void test_get_venueSpace_by_id() {
		VenueSpace venueSpace = dao.getVenueSpacebyID(TEST_SPACE_ID);
		assertNotNull(venueSpace);
		assertTrue(venueSpace.getSpaceID() == 2700);
	}
	

}
