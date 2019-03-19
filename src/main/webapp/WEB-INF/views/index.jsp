<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
</head>
<body
	background="https://images.pexels.com/photos/696680/pexels-photo-696680.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260">

	<%@include file="partials/header.jsp"%>

	<div class="container" id="indexpage">
		<br>
		<h1>Along The Way</h1>
		<br>
		<h4>
			Where are <i>you</i> traveling?
		</h4>

		<form action="/submitform">
			<div class="form-group">
				<p>
				<h5>Start</h5>
				<input placeholder="Ex: Detroit, MI" required
					pattern="[A-Za-z]+[ ]*[A-Za-z]+,+[ ]*[A-Za-z]{2}" type="text"
					name="location1" />
				
				<p>
				<h5>End</h5>
				<input placeholder="Ex: Seattle, WA" required
					pattern="[A-Za-z]+[ ]*[A-Za-z]+,+[ ]*[A-Za-z]{2}" type="text"
					name="location2" />
				
				<select name="category">
					<option>Select a
						category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
					<option value="restaurants">Restaurants</option>
					<option value="landmarks">Landmarks</option>
				</select>
				
				<p class = "text-light"> Minimum Yelp Rating
				<select name ="minrating">
					<option value = 5.0>5.0</option>
					<option value = 4.5>4.5</option>
					<option value = 4.0 selected>4.0</option>
					<option value = 3.5>3.5</option>
					<option value = 3.0>3.0</option>
					<option value = 2.5>2.5</option>
					<option value = 2.0>2.0</option>
				</select> <br>
				<br>
				<button type="submit" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Search!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
				
			</div>
		</form>
	</div>
</body>
</html>