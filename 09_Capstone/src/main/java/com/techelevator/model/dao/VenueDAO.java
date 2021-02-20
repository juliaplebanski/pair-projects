package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.domain.Venue;

public interface VenueDAO {
	
	List<Venue>getAllVenues();
    List<Venue> getVenueDetails();
}
