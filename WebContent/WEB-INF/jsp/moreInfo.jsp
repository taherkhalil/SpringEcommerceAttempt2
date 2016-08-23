<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product | Shoe Sale</title>
</head>
<body>
	<h3>
		<a href="logout">Logout</a>
	</h3>
	<h3 align="center">
		<a href="dashboard">DashBoard</a><br> 
		<a href="viewCart">Cart:<c:out value="${model.cartSize }"></c:out>
		</a>
	</h3>

	<div style="font-size: large;" align="center">

		Name:
		<c:out value="${model.product.name}" />
		
		<br> Size:
		<c:out value="${model.product.size}" />

		<br> Color:
		<c:out value="${model.product.color}" />
		
		<br> <i>&#x20b9;<c:out value="${model.product.price}" /></i><br>
		<br> Available Stock:
		<c:out value="${model.product.quantity}" />
		<br> <a
			href="/EcommerceV2/add?id=<c:out value="${model.product.id}" />">Add
			to Cart</a> <br>
	</div>

</body>
</html>
