package finalproject.alongtheway.model;

import java.util.List;

public class BusinessSearchResponse {

	private List<Businesses> businesses;
	private Region region;
	
	public List<Businesses> getBusinesses() {
		return businesses;
	}
	public void setBusinesses(List<Businesses> businesses) {
		this.businesses = businesses;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}	
	
}
