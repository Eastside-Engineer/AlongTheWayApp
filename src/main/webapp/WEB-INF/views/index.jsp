<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
</head>
<body>


<%@include file="partials/header.jsp"%>


<div class = "container">

<h1>Along The Way</h1>

<h3>Welcome</h3>

<h4>Where are you traveling?</h4>

<a href="/matrix">Distance + Time Example</a>
<br>
<a href="/embedded">Maps</a>
<br>
<a href="/directions">directions</a>
				
<form action="/results">
	<div class = "form-group">
 	 	<p>Starting Location: <input pattern="[A-Z][a-zA-Z]+,[ ]?[A-Z]{2}" type="text" name="location1"></p>
  		<p>Ending Location: <input pattern="[A-Z][a-zA-Z]+,[ ]?[A-Z]{2}" type="text" name="location2"></p>
    	<button type="submit" class = "btn btn-primary">Search!</button>
	</div>
</form>	

<footer>
<a href="/contacts">Contact</a>
</footer>
</div>

</body>
</html>