<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>hey brother</h1>
  <h3>Products</h3>
    <c:forEach items="${model.products}" var="prod">
    	<div>
			
			 <br>
			 <c:out value="${prod.name}"/> 
			 <br><i>&#x20b9;<c:out value="${prod.price}"/></i><br>
			 <a href="<c:out value="${prod.id}" />" id="cartAdd">Add to Cart</a>
			 <a href="/EcommerceV2/product?id=<c:out value="${prod.id}" />" >View More</a>
			 <br>
      	</div>
    </c:forEach>
</body>
</html>