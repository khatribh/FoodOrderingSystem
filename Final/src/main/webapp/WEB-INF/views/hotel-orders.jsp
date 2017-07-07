<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/hotel/">Go Back</a><br/>
<h1>Order History</h1>
<c:choose>
	<c:when test="${!empty list}">
<table border="1">
<thead>
<tr>
		
		<th>Order No</th>
		<th>User Name</th>
		<th>Food Item</th>
		<th>Quantity</th>
		<th>Date</th>
		
	
		
		</tr>
<thead>
<tbody>
	
	
            <c:forEach items="${list}" var="order">
                <tr>
                   
                    <td>${order.orderID}</td>
                    <td>${order.user.firstName }</td>
                    <td>${order.foodItem.foodName }</td>
                    <td>${order.quantity}</td>
                     <td>${order.date}</td>
            
            </tr>
            </c:forEach>
           	
            </tbody>
	</table>
	</c:when>
	 <c:otherwise>
        <br><h1>    <c:out value="No Order History" /></h1>
        </c:otherwise>
        </c:choose>

</body>
</html>