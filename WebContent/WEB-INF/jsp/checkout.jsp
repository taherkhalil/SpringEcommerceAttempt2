<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout | Clothing Mart</title>
</head>
<body>
	<h1>Checkout</h1>
<h3><pre><a href="logout">Logout</a>
	<a href="dashboard">DashBoard</a>
		 <a href="place">Place Order</a></pre></h3>
	<c:forEach items="${model.products}" var="prod">
    	<div>
			<c:out value="${prod.value.name}"/> &nbsp; &nbsp;&nbsp;&nbsp; <i>&#x20b9;<c:out value="${prod.value.price}"/></i>
			<br>
      	</div>
    </c:forEach>
    <br>
    Total: <c:out value="${model.total}"/>
	
</body>
</html>