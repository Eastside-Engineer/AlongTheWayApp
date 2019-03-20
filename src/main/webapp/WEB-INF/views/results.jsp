<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Along the Way - Search Results</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<body>

	<%@include file="partials/header.jsp"%>

	<div class="container">

		<h1 class="display-2">Current Route</h1>

		<table class="table table-striped twoequalcolumns">
			<tr>
				<th>${location1} to ${location2}</th>
					<th>Time/Distance</th>
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


		<table class="table twoequalcolumns">
			<tr>
				<th>Added Stop(s)</th>
				<th>City/State</th>
				<th></th>
				<th></th>
			</tr>

			<c:if test="${stops != null}">
				<c:forEach items="${stops}" var="stop">
					<tr>
						<td>${stop.name}</td>
						<td>${stop.city},${stop.state}</td>
						<td>
							<a class="btn btn-secondary" href="/details/${stop.yelpId}">Details</a>
						</td>
						<td>
							<form action="/deleteStop">
								<input type="hidden" name="stopToRemove" value="${stop.yelpId}" />
								<button type="submit" class="btn btn-secondary">Remove</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:if>

		</table>

		<form action="/saveroute">
			<c:if test="${stops != null}">
				<input type="hidden" name="stops" value="${stops}" />
			</c:if>
			<input type="hidden" name="location1" value="${location1}" /> <input
				type="hidden" name="location2" value="${location2}" />
			<button type="submit" class="btn btn-secondary">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Save this route
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</button>  
			<p class="message">${message}</p>
		</form>
	
		<br>


		<iframe id="iframe" width="600" height="450"
			src="https://www.google.com/maps/embed/v1/directions?origin=${loc1}&destination=${loc2}${waypointsUrlPart}&key=AIzaSyBF6NVoNSyPvZ9PWq3J1WVh3Yup75hSM84">
		</iframe>


		<table class="table table-striped">
		 
			<h3>Results for ${category} search</h3>
		<!--
			<c:if test = "${category == 'landmarks'}">
				<a>Search for restaurants</a>
			</c:if>
			<c:if test = "${category == 'restaurants'}">
				<a>Search for landmarks</a>
			</c:if>
		-->
			<tr>
				<th>Name</th>
				<th>City, State</th>
				<th>Details</th>
				<th>URL Link</th>
				<th>Add to Route</th>
			</tr>

			<c:forEach items="${results}" var="result">
				<tr>
					<td>${result.name}</td>
					<td>${result.location.city},${result.location.state}</td>
					<td><a class="btn btn-secondary" href="/details/${result.id}">Details</a></td>
					<td><a class="btn btn-secondary" href="${result.url}"
						target="_blank">Yelp</a></td>

					<td><form action="/add">
							<input type="hidden" name="latitude"
								value="${result.coordinates.latitude}" /> <input type="hidden"
								name="longitude" value="${result.coordinates.longitude}" /> <input
								type="hidden" name="yelpid" value="${result.id}" /> <input
								type="hidden" name="location1" value="${location1}" /> <input
								type="hidden" name="location2" value="${location2}" /> <input
								type="hidden" name="category" value="${category}" />
							<button type="submit" class="btn btn-secondary">Add</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>