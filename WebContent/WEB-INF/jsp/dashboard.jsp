<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DashBoard</title>
</head>
<body >
<div align="center">
	<h1>welcome  <c:out value="${user }"></c:out>    </h1>
	<h3>Products</h3>

	<h4 ><a href="viewCart">Cart </a>
		 &nbsp; &nbsp;&nbsp;&nbsp;<a href="logout">Logout</a></h4>
					
	<c:forEach items="${model.products}" var="prod">
		<div>

			<br>
			<c:out value="${prod.name}" />
			<br>
			<i>&#x20b9;<c:out value="${prod.price}" /></i><br> 
			Available Stock:
			<c:out value="${prod.quantity }"></c:out><br>
	
		
			<a href="/EcommerceV2/add?id=<c:out value="${prod.id}" />">Add
				to Cart</a> <a
				href="/EcommerceV2/product?id=<c:out value="${prod.id}" />">View
				More</a> <br>
		</div>
	</c:forEach>
	</div>
</body>
</html>