package com.techelevator.model.domain;

public class VenueSpace {

	private long spaceID;
	private long venueID;
	private double daily_rate;
	private String open;
	private String closed;
	private boolean isAccessible;
	private long maxOccupancy;
	private String name;

	public long getSpaceID() {
		return spaceID;
	}

	public void setSpaceID(long spaceID) {
		this.spaceID = spaceID;
	}

	public long getVenueID() {
		return venueID;
	}

	public void setVenueID(long venueID) {
		this.venueID = venueID;
	}

	public double getDaily_rate() {
		return daily_rate;
	}

	public void setDaily_rate(double daily_rate) {
		this.daily_rate = daily_rate;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public boolean isAccessible() {
		return isAccessible;
	}

	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	public long getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// May need toString method

}
