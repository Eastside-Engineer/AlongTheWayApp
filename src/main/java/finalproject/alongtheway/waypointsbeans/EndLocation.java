package finalproject.alongtheway.waypointsbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndLocation {

	@JsonProperty("lat")
	private Double latitude;
	@JsonProperty("lng")
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
