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
	private Venue venue;

	public JDBCVenueDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// includes query that is used to get a list of all of the venues ordered
	// alphabetically from the database

	public List<Venue> getAllVenues() {
		List<Venue> venueList = new ArrayList<Venue>();
		String selectSQL = "SELECT venue.id, venue.name, (city.name, city.state_abbreviation) AS location_id, venue.description, city.id AS city_id, (category.name) AS categories FROM venue\n"
				+ "JOIN category_venue ON category_venue.venue_id = venue.id\n"
				+ "JOIN category ON category_venue.category_id = category.id\n"
				+ "JOIN city ON venue.city_id = city.id\n" + "ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL);
		while (results.next()) {
			Venue venue = mapRowToVenue(results);
			venueList.add(venue);

		}
		return venueList;
	}

	
	

	// includes query that is used to get the venue details from the database

	public List<Venue> getVenueDetails(long venueID) {
		List<Venue> venueDetails = new ArrayList<Venue>();
		String selectSQL = "SELECT venue.id, venue.name, (city.name, city.state_abbreviation) AS location_id, venue.description, venue.city_id AS city_id, (category.name) AS categories FROM venue\n"
				+ "JOIN category_venue ON category_venue.venue_id = venue.id\n"
				+ "JOIN category ON category_venue.category_id = category.id\n"
				+ "JOIN city ON venue.city_id = city.id\n" + "WHERE venue.id = ?";
		// how to print multiple categories a venue has on the same line instead of two
		// separate lines
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL, venueID);
		while (results.next()) {
			Venue venue = mapRowToVenue(results);
			venueDetails.add(venue);
		}
		return venueDetails;
	}

	// used to map the results row to properties of the Venue class
	private Venue mapRowToVenue(SqlRowSet results) {
		Venue venue = new Venue();
		venue.setCategory(results.getString("categories"));
		venue.setDescription(results.getString("description"));
		venue.setVenueID((long) results.getInt("id"));
		venue.setCityID(results.getLong("city_id"));
		venue.setLocation(results.getString("location_id"));// and state of venue
		venue.setName(results.getString("name"));

		return venue;
	}

	public void save(Venue venue) { // may need to come back to this method

		String insertSql = "INSERT INTO venue(id, name, city_id, description) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertSql, venue.getVenueID(), venue.getName(), venue.getCityID(), venue.getDescription());

	}

	@Override
	public Venue getVenueNameByID(Long selectedVenueID) {
		Venue venue = new Venue();
		String selectSQL = "SELECT venue.id, venue.name, (city.name, city.state_abbreviation) AS location_id, venue.description, venue.city_id AS city_id, (category.name) AS categories FROM venue\n"
				+ "JOIN category_venue ON category_venue.venue_id = venue.id\n"
				+ "JOIN category ON category_venue.category_id = category.id\n"
				+ "JOIN city ON venue.city_id = city.id\n" + "WHERE venue.id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL, selectedVenueID);
		results.next();
		venue = mapRowToVenue(results);

		return venue;

	}

}
