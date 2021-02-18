package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.domain.VenueSpace;

public interface VenueSpaceDAO {
	public List<VenueSpace>getAllSpaces();
	
	public VenueSpace getVenueSpacebyID(long ID);
	
	
	

}
