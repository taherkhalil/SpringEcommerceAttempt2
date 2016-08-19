<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout | Clothing Mart</title>
</head>
<body>
	<h1>Checkout</h1>
<h3 align="justify"><a href="logout">Logout</a><br>
	<a href="dashboard">DashBoard</a>
		 <a href="place">Place Order</a></h3>
		 <h3 align="center" style="font: bolder;color: navy;">
    <c:forEach items="${model.orders }" var="order"> <c:out value="${order }"></c:out><br>   </c:forEach>
    <br>
    </h3>
    <h2 align="left"  style="color: red;"><b>Total: <c:out value="${model.total}"/></b></h2>
	
</body>
</html>