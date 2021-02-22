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

	/**used to return a list of all venues spaces inside of a particular venue */
	@Override
	public List<VenueSpace> getAllSpaces() {
		// TODO Auto-generated method stub
		List<VenueSpace>listOfSpaces = new ArrayList<VenueSpace>();
		String selectStatement = "SELECT venue_id,id, name, is_accessible, open_from, open_to, daily_rate::numeric::integer, max_occupancy FROM space";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectStatement);
		while(results.next()) {
			VenueSpace venueSpace = mapRowToVenueSpace(results);
			listOfSpaces.add(venueSpace);
		}
		return listOfSpaces;
	}
	/**used to search for a venue space by spaceID */
	@Override
	public VenueSpace getVenueSpacebyID(long ID) {
		VenueSpace venueSpace = null;
		String selectSql = "SELECT venue_id, id, name, is_accessible, open_from, open_to, daily_rate::numeric::integer, max_occupancy FROM space WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, ID);
		while(results.next()) {
			venueSpace = mapRowToVenueSpace(results);
		}
		return venueSpace;
	}
	
		
	/**used to map the results row to properties of the venueSpace class */
	private VenueSpace mapRowToVenueSpace(SqlRowSet results) {
		VenueSpace venueSpace = new VenueSpace();
		venueSpace.setSpaceID(results.getLong("id"));
		venueSpace.setVenueID(results.getLong("venue_id"));
		venueSpace.setOpen(results.getString("open_from"));
		venueSpace.setClosed(results.getString("open_to"));
		venueSpace.setName(results.getString("name"));
		venueSpace.setAccessible(results.getBoolean("is_accessible"));
		venueSpace.setDaily_rate(results.getInt("daily_rate"));
		venueSpace.setMaxOccupancy(results.getLong("max_occupancy"));
		
		return venueSpace;
		
	}

}
