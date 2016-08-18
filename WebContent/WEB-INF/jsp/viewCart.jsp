<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Cart</h1>
	<h3><pre><a href="logout">Logout</a>
	 <a href="dashboard">DashBoard</a>
 	 <a href="checkout">Checkout</a></pre></h3>

	<h3>Products</h3>


	<c:forEach items="${model.products}" var="prod">
		<div>

			<br>
			<c:out value="${prod.value.name}" />
			<br>
			<i>&#x20b9;<c:out value="${prod.value.price}" /></i><br> <a
				href="/EcommerceV2/remove?id=<c:out value="${prod.key}" />"
				id="cartRemove">Remove From Cart</a> <br>
		</div>
	</c:forEach>
</body>
</html>