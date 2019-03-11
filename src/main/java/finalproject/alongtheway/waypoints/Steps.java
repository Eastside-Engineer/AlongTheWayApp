package finalproject.alongtheway.waypoints;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Steps {

	@JsonProperty("end_location")
	private End endLocation;
	
	@JsonProperty("start_location")
	private Start startLocation;

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
	
}
