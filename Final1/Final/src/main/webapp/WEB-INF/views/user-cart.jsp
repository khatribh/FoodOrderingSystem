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
    $(document).on('click', '#remove', function(e) {
        e.preventDefault();
        var foodID = $(this).parent().parent().data('fid');
        var tp = $("#tp").text();
        var price = $(this).parent().parent().find('.price').text();
            $.ajax({
                url: 'removeCart',                
                contentType: "application/json; charset=utf-8",
                data: { 'fid' : foodID },
                type: 'GET',
                cache: false,
                success: function (response) {
                	alert(response);     
                	$("#rem-"+foodID).hide();
                	tp = (Math.abs(tp)) - (Math.abs(price));
                    $("#tp").text(tp);                                       
                }
            });
        });
    });


</script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/user/">Go Back</a><br/>
 <c:choose>
	<c:when test="${!empty list}">
 <table border="1">            
            <thead>
                <tr>
                	
                    <th>Food Item</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    
                    <th>Total Price</th>
                    <th>Action</th>
                    
                </tr>                
            </thead>
            <c:set var="totalPrice" value="${0}" />
                <c:forEach items="${list}" var="food">
                <tr id="rem-${food.foodItem.foodId}" data-fid="${food.foodItem.foodId}">
               
                    <td>${food.foodItem.foodName}</td>
                    <td>${food.quantity}</td>
                    <td>${food.foodItem.price}</td>
                    <td class="price"><c:set var="total" value="${food.quantity * food.foodItem.price}" />${food.quantity*food.foodItem.price}</td>
                    <c:set var="totalPrice" value="${totalPrice+total}"/>
                    <td><a href="#" id="remove">Remove</a></td>
                    
                </tr> 
                               
            </c:forEach> 
            	            
            
            </tbody>
            
        </table>
        <label id="tp">Total Price<c:out value="${totalPrice }"/></label>
       <a href="${contextPath }/user/orderPlaced">Place Order</a>
        </c:when>
	 <c:otherwise>
          <br><h1> <c:out value="No Items Present in the Cart" /></h1> 
        </c:otherwise>
        </c:choose>
</body>
</html>