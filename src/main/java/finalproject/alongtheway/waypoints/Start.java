package finalproject.alongtheway.waypoints;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Start {

	@JsonProperty("lat")
	private Long startLat;
	@JsonProperty("lng")
	private Long startLong;

	public Long getStartLat() {
		return startLat;
	}

	public void setStartLat(Long startLat) {
		this.startLat = startLat;
	}

	public Long getStartLong() {
		return startLong;
	}

	public void setStartLong(Long startLong) {
		this.startLong = startLong;
	}

}
