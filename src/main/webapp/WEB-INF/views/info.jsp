<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Info</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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