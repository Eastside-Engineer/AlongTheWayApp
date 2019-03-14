package finalproject.alongtheway.waypoints;

import java.util.List;

public class Waypoints {
	
	private List<Legs> legs;

	public List<Legs> getLegs() {
		return legs;
	}

	public void setLegs(List<Legs> legs) {
		this.legs = legs;
	}

	@Override
	public String toString() {
		return "Waypoints [legs=" + legs + "]";
	}
	
	
}
