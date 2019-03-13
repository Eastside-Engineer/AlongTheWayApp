<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
</head>
<body>


	<%@include file="partials/header.jsp"%>


	<div class="container">

		<h1>Along The Way</h1>

		<h3>Welcome</h3>

		<h4>Where are you traveling?</h4>

		<form action="/results">
			<div class="form-group">
				<p>
					<input required placeholder="Starting Location"
						pattern="[A-Za-z]+[ ]*[A-Za-z]+,[A-Z]{2}"
						oninvalid="('Please enter: City,State (ex:Detroit,MI)')"
						type="text" name="location1" />
				</p>

				<p>
					<input required placeholder="Ending Location"
						pattern="[A-Za-z]+[ ]*[A-Za-z]+,[A-Z]{2}"
						oninvalid="('Please enter: City,State (ex:Detroit,MI)')"
						type="text" name="location2" />
				</p>
				<button type="submit" class="btn btn-primary">Search!</button>
			</div>
		</form>

		<footer>

			<a href="/contacts">Meet AlongTheWay</a>


		</footer>
		</div>
</body>
</html>