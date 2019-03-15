<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
</head>
<body>


<div1>
<nav class="navbar">
    <ul class ="nav">

      <li class="nav-item"><a href="/" class="nav-link" >New Route</a></li>
      <li class="nav-item"><a href="/matrix" class="nav-link">Saved Routes</a></li>
      <li class="nav-item"><a href="/contacts" class="nav-link">About The Team</a></li>

      	<li class="nav-item"><a href="/" class="nav-link">New Route</a></li>
      	<li class="nav-item"><a href="/matrix" class="nav-link">Saved Routes</a></li>
      	<li class="nav-item"><a href="/contacts" class="nav-link">About The Team</a></li>
		<c:if test = "${location1 != null}">
      		<li class="nav-item"><a href="/results" class="nav-link">Search Results</a></li>
		</c:if>

    </ul>
</nav>
</div1>


</body>
</html>