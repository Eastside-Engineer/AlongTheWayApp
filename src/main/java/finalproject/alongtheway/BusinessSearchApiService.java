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

	@Value("${yelpapi.key}")
	private String yelpkey;

	public List<Businesses> getAllResultsByLocation(String location) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + yelpkey);
		String url = "https://api.yelp.com/v3/businesses/search?location=" + location;
		BusinessSearchResponse apiResponse = restTemplate
				.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BusinessSearchResponse.class).getBody();
		return apiResponse.getBusinesses();
	}

	public List<Businesses> getAllResultsByCoord(Long longitude, Long latitude) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + yelpkey);
		String url = "https://api.yelp.com/v3/businesses/search?longitude=" + longitude + "&latitude=" + latitude;
		BusinessSearchResponse apiResponse = restTemplate
				.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BusinessSearchResponse.class).getBody();
		return apiResponse.getBusinesses();
	}

}
