package com.techelevator.model.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.dao.VenueSpaceDAO;
import com.techelevator.model.domain.VenueSpace;

public class JDBCVenueSpaceDAO implements VenueSpaceDAO {
	private JdbcTemplate jdbcTemplate;
	public JDBCVenueSpaceDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		
	}

	/**This method returns a list of Venue Spaces.*/
	@Override
	public List<VenueSpace> getAllSpaces() {
		// TODO Auto-generated method stub
		List<VenueSpace>listOfSpaces = new ArrayList<VenueSpace>();
		String selectStatement = "SELECT * FROM space";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectStatement);
		while(results.next()) {
			VenueSpace venueSpace = mapRowToVenueSpace(results);
			listOfSpaces.add(venueSpace);
		}
		return listOfSpaces;
	}
	/**This method searches for a Venue Space by spaceID.*/
	@Override
	public VenueSpace getVenueSpacebyID(long ID) {
		VenueSpace venueSpace = null;
		String selectSql = "SELECT * FROM space WHERE venue_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, ID);
		while(results.next()) {
			venueSpace = mapRowToVenueSpace(results);
		}
		return venueSpace;
	}
		
	/**This method maps the results row to properties of the venueSpace class.*/
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

}