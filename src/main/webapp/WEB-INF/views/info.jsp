<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Info</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
</head>
<body>

<div class = "container">

<form action="results">
What would you like to do on your trip?
<br>

<select>
<option value="options">Pick One</option>
  <option value="restaurant">Restaurants</option>
  <option value="Landmarks">Landmarks</option>
</select>
   <br> <input href = "/results" type="submit" value="Submit">
</form>


</div>

</body>
</html>