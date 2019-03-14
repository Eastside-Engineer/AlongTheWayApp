<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Yelp Details</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<body>

<%@include file="partials/header.jsp"%>

<div class = "container">
<h1>${result.name}</h1>
<table class = "table table-dark"> 
	<tr><th>Address</th><th>Categories</th><th>Price</th><th>Rating</th><th>URL</th><th>Add to Route</th></tr>
	<tr>
		<td>${result.location.address1}, ${result.location.address2} ${result.location.city}, ${result.location.state} ${result.location.zipCode}</td>
		<td><c:forEach var = "cats" items="${result.categories}">${cats.title}<br></c:forEach></td>
		<td>${result.price}</td>
		<td>${result.rating}</td>
		<td><a class = "btn btn-primary" href = "${result.url}" target = "_blank">Yelp page</a></td>
		<td><a class = "btn btn-primary">Add</a></td>
	</tr>
</table>

<div>
<img src = "${result.imageUrl}" id = "img">
</div>

</div>
</body>
</html>