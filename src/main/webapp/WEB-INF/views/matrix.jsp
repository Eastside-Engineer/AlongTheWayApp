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
				<th>Along The Way</th>
				<th>Ending Location</th>
				<th></th>
				<th></th>
			</tr>

			<c:forEach items="${amend}" var="amended">
				<tr>
					<td>${amended.location1}</td>
					<td>
						<div class="panel-group">
						  <div class="panel panel-default">
						    <div class="panel-heading">
						      <p class="panel-title">
						        <a data-toggle="collapse" href="#collapse${amended.id}"> View ${ amended.stops.size() } Stops</a>
						      </p>
						    </div>
						    <div id="collapse${amended.id}" class="panel-collapse collapse">
						      <c:forEach items = "${amended.stops}" var = "stop">
						      	<div class="panel-body">${stop.name}</div>
						      </c:forEach>
						    </div>
						  </div>
						  </div>
  					</td>
					<td>${amended.location2}</td>

					<td><a href="/delete?id=${amended.id}" class="btn btn-primary">Delete Route</a></td>
					<td><a href="/edit?id=${amended.id}" class = "btn btn-primary">View/Edit Route</a></td>

				</tr>
			</c:forEach>

		</table>
		
		
</div>
	
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>