package com.techelevator.model.domain;

public class Venue {

	private long venueID;
	private String name;
	private String location;
	private long cityID;
	private String category;
	private String description;

	public long getVenueID() {
		return venueID;
	}

	public void setVenueID(long venueID) {
		this.venueID = venueID;
	}

	public void setID(long iD) {
		this.venueID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
	}
	

	// May need a toString method later.

}
