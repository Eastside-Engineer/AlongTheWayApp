package finalproject.alongtheway.waypointsbeans;

import java.util.List;

public class Legs {

	List<Steps> steps;

	private Distance distance;
	private Duration duration;

	public Distance getDistance() {
		return distance;
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
