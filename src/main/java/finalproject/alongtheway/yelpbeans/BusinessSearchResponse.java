package finalproject.alongtheway.yelpbeans;

import java.util.List;

// when doing a business/search/ request, fills in with the json response
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
