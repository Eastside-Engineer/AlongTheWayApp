<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<title>Matrix</title>
</head>
<body>

	<div class="container">

		<h1>The Routes</h1>


		<table class="table table-dark">

			<th>Starting Location</th>
			<th>Ending Location</th>
			<th>Something</th>
			<th>Something</th>

			<c:forEach items="${amend}" var="amended">
				<tr>
					<td>${amended.location1}</td>
					<td>${amended.location2}</td>
					<td></td>
					<td></td>
					<td><a href="/delete?id=${amended.id}" class="btn btn-secondary">Delete</a></td>

				</tr>
			</c:forEach>

		</table>

		<a href="/" class="btn btn-secondary">Return to Homepage</a>
</body>
</html>