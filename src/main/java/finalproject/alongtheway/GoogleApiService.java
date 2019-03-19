package finalproject.alongtheway;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import finalproject.alongtheway.dao.Stop;
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

		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + location1
				+ "&destinations=" + location2 + "&key=" + googlekey;
		Routes apiResponse = restTemplate.getForObject(url, Routes.class);
		return (Element) apiResponse.getRows().get(0).getElements().get(0);
	}

	// get WAYPOINTS
	public List<Steps> getWaypoints(String location1, String location2) {

		// regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		// regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2}

		location1 = splitter(location1);
		location2 = splitter(location2);

		String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + location1 + "&destination="
				+ location2 + "&departure_time=now" + "&key=" + googlekey;
		WaypointResponse apiResponse = restTemplate.getForObject(url, WaypointResponse.class);
		return apiResponse.getRoutes().get(0).getLegs().get(0).getSteps();
	}

	// get BASIC directions
	public Legs getBasicDirections(String location1, String location2) {

		// regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		// regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2}

		location1 = splitter(location1);
		location2 = splitter(location2);

		// String latLong2 = "via:" + latitude + "%2C" + longitude;
		// %7C if multiple latLongs
		String url = "https://maps.googleapis.com/maps/api/directions/json?units=imperial&origin=" + location1
				+ "&destination=" + location2 + "&key=" + googlekey;

		WaypointResponse apiResponse = restTemplate.getForObject(url, WaypointResponse.class);

		return apiResponse.getRoutes().get(0).getLegs().get(0);

	}

	// get AMENDED directions
	public List<Legs> getAmendedDirections(String location1, String location2, List<Stop> stops) {

		// regex(no space between comma): [A-Z][a-zA-Z]+,[ ]?[A-Z]{2}
		// regex(one space between comma): [A-Z][a-zA-Z]+,[ ]{1}?[A-Z]{2}

		location1 = splitter(location1);
		location2 = splitter(location2);
		String waypoints = "";

		for (int i = 0; i < stops.size(); i++) {
			if (i > 0) {
				waypoints = waypoints + "|" + citySplit(stops.get(i).getCity()) + "," + stops.get(i).getState();
			} else {
				waypoints = citySplit(stops.get(i).getCity()) + "," + stops.get(i).getState();
			}
		}

		String url = "https://maps.googleapis.com/maps/api/directions/json?units=imperial&origin=" + location1
				+ "&destination=" + location2 + "&waypoints=optimize:true|" + waypoints + "&key=" + googlekey;

		System.out.println(url);

		WaypointResponse apiResponse = restTemplate.getForObject(url, WaypointResponse.class);

		System.out.println(apiResponse.toString());

		return apiResponse.getRoutes().get(0).getLegs();

	}

	// fix user input by eliminating spaces and adding + between city names
	public String splitter(String str1) {

		String[] str2 = str1.split(",");
		String str = str2[1].trim();
		String str3 = str2[0];

		if (str3.contains(" ")) {
			String[] str4 = str3.split("\\s");
			String str5 = str4[0] + "+" + str4[1] + "," + str;
			return str5;
		} else {
			return str3 + "," + str;
		}
	}

	// replace space with + in city name
	public String citySplit(String str1) {

		if (str1.contains(" ")) {
			String[] str4 = str1.split("\\s");
			String str5 = str4[0] + "+" + str4[1];
			return str5;
		} else {
			return str1;
		}
	}

}
