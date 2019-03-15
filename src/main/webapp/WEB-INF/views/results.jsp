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

<a href="/dt">testies</a>

	<%@include file="partials/header.jsp"%>

	<div class="container">

	<h1>Current Route</h1>
	<table class="table table-striped">
	<tr>
		<th>City, State</th>
		<th>Name</th>
	</tr>
	<tr>
		<td>${location1}</td>
		<td>Starting location</td>
	</tr>
	<c:if test = "${stops != null}">
		<c:forEach items="${stops}" var="stop">
			<tr>
				<td>${stop.business.location.city}, ${stop.business.location.state}</td>
				<td>${stop.business.name}</td>
			</tr>
		</c:forEach>
	</c:if>	
	<tr>
		<td>${location2}</td>
		<td>End location</td>
	</table>

	<iframe width="600" height="450" frameborder="0" style="border: 0"
		src="https://www.google.com/maps/embed/v1/directions?origin=${loc1}&destination=${loc2}&key=AIzaSyBF6NVoNSyPvZ9PWq3J1WVh3Yup75hSM84"
		allowfullscreen> </iframe>

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
							<input type="hidden" name="latitude" value="${result.coordinates.latitude}"/>
							<input type="hidden" name="longitude" value="${result.coordinates.longitude}"/>
							<input type="hidden" name="yelpid" value="${result.id}"/>
							<input type="hidden" name="location1" value="${location1}"/>
							<input type="hidden" name="location2" value="${location2}"/>
							<input type="hidden" name="category" value="${category}"/>
							<button type="submit">Add</button>
						</form>
					</td>
					
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>