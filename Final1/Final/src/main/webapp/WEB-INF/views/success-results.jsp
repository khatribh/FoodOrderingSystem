<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/user/">Go Back</a><br/>
	 
	 <form method="post" action="${contextPath}/user/orderItems">
	<c:choose>
	<c:when test="${!empty hotelList}">
	<h1>Here are the search results</h1>
        <table border="1">            
            <thead>
                <tr>
                	<th></th>
                    <th>Hotel Name</th>
                    <th>Cuisine</th>
                    <th>Rate for 2</th>
                    <th colspan="2">Action</th>
                    
                    
                </tr>                
            </thead>
            <tbody>
            <c:set var="radio" value="true" />
            <c:forEach items="${hotelList}" var="hotel">
                <tr>
                <td>
                <c:choose>
                <c:when test="${radio == true }"><input class="radio" type="radio" name="hotelId" value="${hotel.hotelID}" checked></c:when>
                <c:otherwise><input type="radio" name="hotelId" value="${hotel.hotelID}"></c:otherwise></c:choose></td>
                    <td>${hotel.hotelName}</td>
                    <td>${hotel.cuisines}</td>
                    <td>${hotel.rate}</td>
                    <td><input type="submit" value="Order Now"></td>
                    <td><a href="${contextPath}/user/menu.pdf?hotelid=${hotel.hotelID}">View Menu</a></td>
                    <c:set var="radio" value="false" />
                    
                </tr>                
            </c:forEach>
            </tbody>
            
        </table>
        </c:when>
	 <c:otherwise>
       <br>   <h1>  <c:out value="No Search Results Found" /></h1>
        </c:otherwise>
        </c:choose>
	</form>
</body>
</html>