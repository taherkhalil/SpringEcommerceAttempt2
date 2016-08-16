<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product | Clothing Mart</title>
</head>
<body>
	<a href="home">Home</a>
	<a href="logout">Logout</a>

	<p>
		<a href="cart/view">Cart <span id="cartNumber"><c:out
					value="${cartSize}" /></span></a>
	</p>
	<p id="cartMessage"></p>
	<h3>
		<c:out value="${model.product.name}" />
	</h3>

	<div>
	
		Name:
		<c:out value="${model.product.name}" />
		<br> Size:
		<c:out value="${model.product.size}" />
		<br> Color:
		<c:out value="${model.product.color}" />
		<br> <i>&#x20b9;<c:out value="${model.product.price}" /></i><br>
		<a href="<c:out value="${model.product.id}" />" id="cartAdd">Add
			to Cart</a> <br>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function($) {
			setCartSize();
			function setCartSize() {
				$.get("cart/size", function(data) {
					$("#cartNumber").html(data);
					console.log(data);
				});
			}
			$("body").on("click", "#cartAdd", function(e) {
				e.preventDefault();
				console.log($(this).attr("href"));
				var url = "cart/add?id=";
				url += $(this).attr("href");

				$.ajax({
					url : url,
					type : "GET",
					success : function(data) {
						console.log("Success");
						$("#cartNumber").text(data);
						$("#cartMessage").text("Item Added Successfully");
						setTimeout(function() {
							if ($('#cartMessage').length > 0) {
								$('#cartMessage').text("");
							}
						}, 5000)

					},
					error : function(xhr, status, msg) {
						console.log(xhr.responseText);
						$("#cartMessage").text("Oops! Something Went Wrong!");
					}
				});
			});
		});
	</script>
</body>
</html>
</body>
</html>