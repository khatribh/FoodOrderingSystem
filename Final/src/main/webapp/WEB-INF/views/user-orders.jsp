<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/user/">Go Back</a><br/>
<h1>Order History</h1>
    <c:choose>
        <c:when test="${!empty hashmap}">
            <c:forEach items="${hashmap}" var="entry">
            <c:set var="total" value="${0}" />
            <c:out value="Order ID: ${entry.key}" />
            <br/>
            <table id="table" border="1" cellpadding="5">
                <tr>
                    <th>Food Item</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Date</th>
                    <th>Hotel Name</th>
                    
                </tr>
                
                <c:forEach var="order" items="${entry.value}">            
                    <tr id="row-${order.orderID}" data-id="${order.orderID}">
                        <td>${order.foodItem.foodName}</td>
                        <td>${order.quantity}</td>
                        <td><c:set var="totalPrice" value="${order.quantity * order.foodItem.price}" />${order.quantity * order.foodItem.price}</td>
                        <td><fmt:formatDate type="date" value="${order.date}" /></td>
                        <td>${order.foodItem.hotel.hotelName}</td>
                        
                        <c:set var="total" value="${total+totalPrice}" />
                        
                    </tr>
                </c:forEach>
            </table>
            <h3>Total Price: $<c:out value="${total}" /></h3>
            <br/>
            </c:forEach>
        </c:when>
        <c:otherwise>
         <br>  <h1> <c:out value="No Orders" /></h1>
        </c:otherwise>
    </c:choose>
</body>
</html>
