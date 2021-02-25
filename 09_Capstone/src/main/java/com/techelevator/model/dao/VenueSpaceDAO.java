package com.techelevator.model.dao;

import java.util.List;


import com.techelevator.model.domain.VenueSpace;

public interface VenueSpaceDAO {
	 List<VenueSpace>getAllSpacesForVenue(long ID);
	 VenueSpace getVenueSpacebyID(long ID);
	
	
	

}
