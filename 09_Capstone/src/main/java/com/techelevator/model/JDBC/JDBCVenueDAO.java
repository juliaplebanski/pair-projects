package com.techelevator.model.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.domain.Venue;

public class JDBCVenueDAO implements VenueDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCVenueDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Venue> getAllVenues() {
		List<Venue> venueList = new ArrayList<Venue>();
		String selectSQL = "SELECT * FROM venue ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL);
		while (results.next()) {
			Venue venue = mapRowToVenue(results);
			venueList.add(venue);

		}
		return venueList;
	}

	public List<Venue> getVenueDetails(long venueID) {

		List<Venue> venueDetails = new ArrayList<Venue>();
		String selectSQL = "SELECT venue.id, venue.name, (city.name, city.state_abbreviation) AS location_id, (category.name) AS categories, venue.description FROM venue\n"
				+ "JOIN category_venue ON category_venue.venue_id = venue.id\n"
				+ "JOIN category ON category_venue.category_id = category.id\n"
				+ "JOIN city ON venue.city_id = city.id\n" + "WHERE venue.id = ?";
		// how to print multiple categories a venue has on the same line instead of two
		// separate
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL, venueID);
		while (results.next()) {
			Venue venue = mapRowToVenue(results);
			venueDetails.add(venue);
		}
		return venueDetails;
	}

	private Venue mapRowToVenue(SqlRowSet results) {
		Venue venue = new Venue();
		//venue.setCategory(results.getString("categories"));
		venue.setDescription(results.getString("description"));
		venue.setVenueID((long) results.getInt("id"));
	//	venue.setCityID(results.getLong("city_id"));
	//	venue.setLocation(results.getString("location_id")); // pulls in city name and state of venue
		venue.setName(results.getString("name"));

		return venue;
	}

	public void save(Venue venue) { // may need to come back to this method

		String insertSql = "INSERT INTO venue(id, name, city_id, description) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertSql, venue.getVenueID(), venue.getName(), venue.getCityID(), venue.getDescription());

	}

	@Override
	public List<Venue> getVenueDetails() {
		// TODO Auto-generated method stub
		return null;
	}


}