<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<title>Matrix</title>
</head>
<body>

<%@include file="partials/header.jsp"%>

	<div class="container">

		<h1>The Routes</h1>

		<table class="table table-striped">

			<tr>
				<th>Starting Location</th>
				<th>Number of Stops</th>
				<th>Time and Duration</th>
				<th>Ending Location</th>
			</tr>

			<c:forEach items="${amend}" var="amended">
				<tr>
					<td>${amended.location1}</td>
					<td></td>
					<td></td>
					<td>${amended.location2}</td>
					<td><a href="/delete?id=${amended.id}"
						class=" btn btn-secondary">Delete Route</a></td>

				</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>