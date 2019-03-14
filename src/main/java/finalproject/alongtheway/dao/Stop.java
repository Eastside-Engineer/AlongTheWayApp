package finalproject.alongtheway.dao;

import finalproject.alongtheway.yelpbeans.Businesses;

public class Stop {

	private String yelpId;
	private Businesses business;
	private Double longitude;
	private Double latitude;
	
	public Stop() {}
	
	public Stop(String yelpId, Double longitude, Double latitude) {
		setYelpId(yelpId);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	public String getYelpId() {
		return yelpId;
	}
	public void setYelpId(String yelpId) {
		this.yelpId = yelpId;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Businesses getBusiness() {
		return business;
	}
	public void setBusiness(Businesses business) {
		this.business = business;
	}
	@Override
	public String toString() {
		return "Stop [yelpId=" + yelpId + ", business=" + business + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}
}
