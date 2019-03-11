package finalproject.alongtheway.model;

public class Coordinates {

	private Long latitude;
	private Long longitude;

	public Coordinates() {
	}

	public Coordinates(Long latitude, Long longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
	
}
