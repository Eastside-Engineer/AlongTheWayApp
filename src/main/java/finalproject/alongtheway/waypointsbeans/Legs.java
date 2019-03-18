package finalproject.alongtheway.waypointsbeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Legs {

	List<Steps> steps;
	@JsonProperty("via_waypoint")
	List<Way> viaWaypoint;

	private Distance distance;
	private Duration duration;

	public Distance getDistance() {
		return distance;
	}

	public List<Way> getViaWaypoint() {
		return viaWaypoint;
	}

	public void setViaWaypoint(List<Way> viaWaypoint) {
		this.viaWaypoint = viaWaypoint;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public List<Steps> getSteps() {
		return steps;
	}

	public void setSteps(List<Steps> steps) {
		this.steps = steps;
	}

}
