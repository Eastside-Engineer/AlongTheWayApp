<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<body>

	<%@include file="partials/header.jsp"%>

	<div class="container">

		<h1>Current Route</h1>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>City, State</th>
			</tr>
			<tr>
				<td>Starting location</td>
				<td>${location1}</td>
			</tr>
			<c:if test="${stops != null}">
				<c:forEach items="${stops}" var="stop">
					<tr>
						<td>${stop.name}</td>
						<td>${stop.city},${stop.state}</td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td>End location</td>
				<td>${location2}</td>
			</tr>
		</table>

		<form action="/saveroute">
			<c:if test="${stops != null}">
				<input type="hidden" name="stops" value="${stops}" />
			</c:if>
			<input type="hidden" name="location1" value="${location1}" /> <input
				type="hidden" name="location2" value="${location2}" />
			<button type="submit" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Save
				this
				route&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
		</form>
		<br>
		<h3>Time and Distance of Routes</h3>

		<table class="table table-hover">
			<tr>
				<th>${location1}to ${location2}</th>
				<th>Amended</th>
			</tr>
			<tr>
				<td>Time: ${duration}</td>
				<td>Time: ${durationNew}</td>
			</tr>
			<tr>
				<td>Distance: ${distance}</td>
				<td>Distance: ${distanceNew}</td>
			</tr>
		</table>


		<iframe id="iframe" width="600" height="450"
			src="https://www.google.com/maps/embed/v1/directions?origin=${loc1}&destination=${loc2}${waypoint}
		<c:forEach var="stop" items="${waypointsURL}">${stop.name }</c:forEach>
		&key=AIzaSyBF6NVoNSyPvZ9PWq3J1WVh3Yup75hSM84">
		</iframe>

		<iframe id="iframe" width="600" height="450"
			src="https://www.google.com/maps/embed/v1/directions?origin=${loc1}&destination=${loc2}&key=AIzaSyBF6NVoNSyPvZ9PWq3J1WVh3Yup75hSM84">
		</iframe>

		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>City, State</th>
				<th>Details</th>
				<th>URL Link</th>
				<th>Add to Route</th>
			</tr>
			<c:forEach items="${results}" var="result">
				<tr>
					<td>${result.name}</td>
					<td>${result.price}</td>
					<td>${result.location.city},${result.location.state}</td>
					<td><a class="btn btn-primary" type="submit"
						href="/details/${result.id}">Details</a></td>
					<td><a class="btn btn-primary" href="${result.url}"
						target="_blank">Yelp page</a></td>

					<td><form action="/add">
							<input type="hidden" name="latitude"
								value="${result.coordinates.latitude}" /> <input type="hidden"
								name="longitude" value="${result.coordinates.longitude}" /> <input
								type="hidden" name="yelpid" value="${result.id}" /> <input
								type="hidden" name="location1" value="${location1}" /> <input
								type="hidden" name="location2" value="${location2}" /> <input
								type="hidden" name="category" value="${category}" />
							<button type="submit" class="btn btn-primary">Add</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>