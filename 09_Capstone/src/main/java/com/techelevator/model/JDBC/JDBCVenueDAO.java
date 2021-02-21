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
	
	
	//includes query that is used to get a list of all of the venues ordered alphabetically from the database
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

	// need this method here to check if categories are null. this is why we keep getting the java.lang.NullPointerException
	//i reached out in slack and Byron helped me with this a bit tonight but I was not able to get it to work. I think this is more on the right track though to get rid of the error we keep getting
	public void checkIfCategoryIsNull(String string) {
		if (venue.getCategory() == null) {
			venue.setCategory("");
			if (venue.getCategory() != null) {
				venue.setCategory("categories");
			}
		}
	}

	//includes query that is used to get the venue details from the database
	public List<Venue> getVenueDetails(long venueID) {

		List<Venue> venueDetails = new ArrayList<Venue>();
		//checkIfCategoryIsNull("categories"); // need to call the method from above to check if the categories are null
		String selectSQL = "SELECT venue.id, venue.name, (city.name, city.state_abbreviation) AS location_id, (category.name) AS categories, venue.description FROM venue\n"
				+ "LEFT category_venue ON category_venue.venue_id = venue.id\n"
				+ "JOIN category ON category_venue.category_id = category.id\n"
				+ "JOIN city ON venue.city_id = city.id\n" + "WHERE venue.id = ?";
		// how to print multiple categories a venue has on the same line instead of two separate lines
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL, venueID);
		while (results.next()) {
			Venue venue = mapRowToVenue(results);
			venueDetails.add(venue);
		}
		return venueDetails;
	}
	//used to map the results row to properties of the Venue class
	private Venue mapRowToVenue(SqlRowSet results) {
		Venue venue = new Venue();
		//venue.setCategory(checkIfCategoryIsnull(results.getString("categories")); // need to call method made from above here
		venue.setDescription(results.getString("description"));
		venue.setVenueID((long) results.getInt("id"));
		// venue.setCityID(results.getLong("city_id"));
		// venue.setLocation(results.getString("location_id")); // pulls in city name
		// and state of venue
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
