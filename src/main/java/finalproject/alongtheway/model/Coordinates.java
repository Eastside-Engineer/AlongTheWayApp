package finalproject.alongtheway.model;

public class Coordinates {

	private Double latitude;
	private Double longitude;

	public Coordinates() {
	}

	public Coordinates(Double latitude, Double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
	
}
