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
<body background="https://images.pexels.com/photos/696680/pexels-photo-696680.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260">

<%@include file="partials/header.jsp"%>

	<div class="container" id = "indexpage">
		<h1>Along The Way</h1>
		<h4>Where are you traveling?</h4>

		<form action="/submitform">
			<div class="form-group">
				<p>
					<input placeholder="Starting Location"
						required pattern="[A-Za-z]+[ ]*[A-Za-z]+,[ ]*[A-Za-z]{2}"
						oninvalid="('Please enter: City,State (ex:Detroit,MI)')"
						type="text" name="location1" />
				</p>

				<p>
					<input placeholder="Ending Location"
						required pattern="[A-Za-z]+[ ]*[A-Za-z]+,[ ]*[A-Za-z]{2}"
						oninvalid="('Please enter: City,State (ex:Detroit,MI)')"
						type="text" name="location2" />
				</p>
				<br> 
				<select name="category">
					<option  value="restaurants">Restaurants</option>
					<option value="landmarks">Landmarks</option>
				</select>
				<button type="submit" class="btn btn-primary">Search!</button>
			</div>
		</form>
	</div>
</body>
</html>