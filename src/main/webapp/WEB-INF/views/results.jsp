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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<body>

	<%@include file="partials/header.jsp"%>

	<div class="container">

<%-- 	<!-- !!!!!!!!    The map will not work because the google API key is hidden. -->
	<iframe width="600" height="450" frameborder="0" style="border: 0"
		src="https://www.google.com/maps/embed/v1/directions?origin=${loc1}&destination=${loc2}&key=${googlekey}"
		allowfullscreen> </iframe> --%>



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

					<td><a class="btn btn-primary" type="submit">Add</a></td>

				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>