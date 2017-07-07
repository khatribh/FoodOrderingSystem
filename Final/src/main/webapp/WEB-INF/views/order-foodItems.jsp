<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function() {
    $(document).on('click', '#addToCart', function(e) {
        e.preventDefault();
        var foodID = $(this).parent().parent().data('fid');
        var qty = $(this).parent().siblings().find('.qty').val();
       

        if(qty == "" ) {
            alert("Quantity cannot be empty");
        } else if (qty < 1) {
            alert("Quantity cannot be less than 1");    
        } 
         else {
            $.ajax({
                url: 'addtoCart',                
                contentType: "application/json; charset=utf-8",
                data: { 'qty': qty, 'fid' : foodID },
                type: 'GET',
                cache: false,
                success: function (response) {
                	alert(response);     
                	$('.qty').val("");                                       
                }
            });
        }
    });
});
</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/user/">Go Back</a><br/>
	
	 <h1>Food Items</h1>
	 <c:choose>
	<c:when test="${!empty foodList}">
        <table border="1">            
            <thead>
                <tr>
                	
                    <th>Food Item</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th></th>
                    
                </tr>                
            </thead>
            <tbody>
            <c:forEach items="${foodList}" var="food">
                <tr data-fid="${food.foodId}">
               
                    <td>${food.foodName}</td>
                    <td>${food.description}</td>
                    <td>${food.price}</td>
                    <td><input class="qty" type="number" min="1" name="quantity"/></td>
                    <td><a href="#" id="addToCart">Add to Cart</a></td>
                    
                </tr>                
            </c:forEach>
            </tbody>
            
        </table>
        <a href="${contextPath}/user/cart">Cart</a>
        </c:when>
	 <c:otherwise>
          <br><h1> <c:out value="No FoodItem Found" /></h1> 
        </c:otherwise>
        </c:choose>
</body>
</html>