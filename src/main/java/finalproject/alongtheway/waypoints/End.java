package finalproject.alongtheway.waypoints;

import com.fasterxml.jackson.annotation.JsonProperty;

public class End {

	@JsonProperty("lat")
	private Long endLat;
	@JsonProperty("lng")
	private Long endLong;

	public Long getEndLat() {
		return endLat;
	}

	public void setEndLat(Long endLat) {
		this.endLat = endLat;
	}

	public Long getEndLong() {
		return endLong;
	}

	public void setEndLong(Long endLong) {
		this.endLong = endLong;
	}

}
