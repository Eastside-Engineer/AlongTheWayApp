package finalproject.alongtheway;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import finalproject.alongtheway.matrixbeans.Element;
import finalproject.alongtheway.matrixbeans.Routes;
import finalproject.alongtheway.waypointsbeans.Legs;
import finalproject.alongtheway.waypointsbeans.Steps;
import finalproject.alongtheway.waypointsbeans.WaypointResponse;

@Component
public class GoogleApiService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${googleapi.key}")
	private String googlekey;

	// get TIME and DISTANCE
	public Element findDistanceAndDuration(String location1, String location2) {

		// regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		// regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2}

		String[] splitStr = location1.split("\\s+");
		if (!splitStr[1].isEmpty()) {
			location1 = splitStr[0] + "+" + splitStr[1];
		}

		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + location1
				+ "&destinations=" + location2 + "&key=" + googlekey;
		Routes apiResponse = restTemplate.getForObject(url, Routes.class);
		return (Element) apiResponse.getRows().get(0).getElements().get(0);
	}

	// get WAYPOINTS
	public List<Steps> getWaypoints(String location1, String location2) {

		// regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		// regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2}

		String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + location1 + "&destination="
				+ location2 + "&departure_time=now" + "&key=" + googlekey;
		WaypointResponse apiResponse = restTemplate.getForObject(url, WaypointResponse.class);
		return apiResponse.getRoutes().get(0).getLegs().get(0).getSteps();
	}

	public Legs getNewWaypoints(String location1, String location2) {

		// regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		// regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2}

		// String latLong2 = "%7Cvia:" + latitude + "%2C" + longitude;

		// %7C if multiple latLongs
		String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + location1 + "&destination="
				+ location2 + "&key=" + googlekey;

		WaypointResponse apiResponse = restTemplate.getForObject(url, WaypointResponse.class);

		return apiResponse.getRoutes().get(0).getLegs().get(0);

	}

}
