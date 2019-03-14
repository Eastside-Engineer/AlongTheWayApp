package finalproject.alongtheway.waypointsbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Start {

	@JsonProperty("lat")
	private Double startLat;
	@JsonProperty("lng")
	private Double startLong;

	public Double getStartLat() {
		return startLat;
	}

	public void setStartLat(Double startLat) {
		this.startLat = startLat;
	}

	public Double getStartLong() {
		return startLong;
	}

	public void setStartLong(Double startLong) {
		this.startLong = startLong;
	}

	@Override
	public String toString() {
		return "Start [startLat=" + startLat + ", startLong=" + startLong + "]";
	}

	
}
