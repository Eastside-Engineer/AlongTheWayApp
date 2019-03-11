package finalproject.alongtheway;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import finalproject.alongtheway.entity.Element;
import finalproject.alongtheway.entity.Routes;
import finalproject.alongtheway.model.BusinessSearchResponse;
import finalproject.alongtheway.model.Businesses;
import finalproject.alongtheway.waypoints.Steps;
import finalproject.alongtheway.waypoints.WaypointResponse;

@Component
public class BusinessSearchApiService {

	private RestTemplate restTemplate = new RestTemplate();

	private RestTemplate restTemplateWithUserAgent;

	// This is an instance initialization block. It runs when a new instance of the
	// class is created--right before the constructor.
	{
		// This configures the restTemplateWithUserAgent to include a User-Agent header
		// with every HTTP request.
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
			return execution.execute(request, body);
		};
		restTemplateWithUserAgent = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	// api key values stored in application.properties
	@Value("${myapi.key}")
	private String apikey;

	@Value("${api.key}")
	private String key;

	public List<Businesses> getAllResultsByLocation(String location) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + apikey);
		String url = "https://api.yelp.com/v3/businesses/search?location=" + location;
		BusinessSearchResponse apiResponse = restTemplate
				.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BusinessSearchResponse.class).getBody();
		return apiResponse.getBusinesses();
	}

	public Element findDistanceAndDuration(String location1, String location2) {

		location1 = "Detroit,MI";
		location2 = "Chicago,IL";
		
		//regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		//regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2} 

		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + location1
				+ "&destinations=" + location2 + "&key=" + key;
		Routes apiResponse = restTemplate.getForObject(url, Routes.class);
		return (Element) apiResponse.getRows().get(0).getElements().get(0);
	}

	public List<Businesses> getAllResultsByCoord(Long longitude, Long latitude) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + apikey);
		String url = "https://api.yelp.com/v3/businesses/search?longitude=" + longitude + "&latitude=" + latitude;
		BusinessSearchResponse apiResponse = restTemplate
				.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BusinessSearchResponse.class).getBody();
		return apiResponse.getBusinesses();
	}
	
	public List<Steps> getWaypoints(String location1, String location2) {

		location1 = "Detroit,MI";
		location2 = "Chicago,IL";
		
		//regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		//regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2} 

		String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
				+ location1 + "&destination=" + location2 + "&departure_time=now" + "&key=" + key;
		WaypointResponse apiResponse = restTemplate.getForObject(url, WaypointResponse.class);
		return apiResponse.getRoutes().get(0).getLegs().get(0).getSteps();
	
	}
}
