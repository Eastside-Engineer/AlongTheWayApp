<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
</head>
<body>

<div class = "container">

<h1>Along The Way</h1>

<form action="info">
  What to See: <br> <input type="text" name=""><br>
  What to Eat: <br> <input type="text" name="">
   <br> <input href = "/info" type="submit" value="Submit">
</form>

<a href="/matrix">Distance + Time Example</a>
				
<form action="/results">
	<div class = "form-group">
 	 	<p>Starting Location: <input type="text" name="location"></p>
  		<p>Ending Location: <input type="text" name="endlocation"></p>
    	<button type="submit" class = "btn btn-primary">Search!</button>
	</div>
</form>	

</div>
</body>
</html>