<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Your Photos</h2>
<br>
<c:choose>
	<c:when test="${!empty list}">
<table><tr>
<c:forEach var="photo" items="${list}">
<td> <img height="150" width="150" src="${photo.filename}" /></td>
</c:forEach>
</tr>
</table>
</c:when>
	 <c:otherwise>
       <br>   <h1>  <c:out value="No Photos have been uploaded yet" /></h1>
        </c:otherwise>
        </c:choose>
</body>
</html>