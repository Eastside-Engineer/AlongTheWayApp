package finalproject.alongtheway.waypointsbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Steps {

	@JsonProperty("end_location")
	private End endLocation;

	private Distance distance;

	@JsonProperty("start_location")
	private Start startLocation;

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public End getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(End endLocation) {
		this.endLocation = endLocation;
	}

	public Start getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Start startLocation) {
		this.startLocation = startLocation;
	}

	@Override
	public String toString() {
		return "Steps [endLocation=" + endLocation + ", startLocation=" + startLocation + "]";
	}

}
