package finalproject.alongtheway.waypointsbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class End {

	@JsonProperty("lat")
	private Double endLat;
	@JsonProperty("lng")
	private Double endLong;

	public Double getEndLat() {
		return endLat;
	}

	public void setEndLat(Double endLat) {
		this.endLat = endLat;
	}

	public Double getEndLong() {
		return endLong;
	}

	public void setEndLong(Double endLong) {
		this.endLong = endLong;
	}

	@Override
	public String toString() {
		return "End [endLat=" + endLat + ", endLong=" + endLong + "]";
	}

}
