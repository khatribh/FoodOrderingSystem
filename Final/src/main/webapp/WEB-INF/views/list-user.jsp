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

	<a href="${contextPath}/admin/">Go Back</a><br/>
<h1>List of Users</h1>
<c:choose>
	<c:when test="${!empty list}">
<table border="1">
<thead>
<tr>
		
		
		<th>FirstName</th>
		<th>LastName</th>
		<th>Email</th>
		<th>Phone No</th>
		
	
		
		</tr>
<thead>
<tbody>
	
	
            <c:forEach items="${list}" var="user">
                <tr>
                   
                    <td><input type="text" name="firstName" value="${user.firstName}" readonly/></td>
                    <td><input type="text" name="lastName" value="${user.lastName}" readonly/></td>
                    <td><input type="text" name="email" value="${user.email.emailAddress}" readonly/></td>
                    <td><input type="text" name="phoneNo" value="${user.phoneNo.phoneNo}" readonly/></td>
            
            </tr>
            </c:forEach>
           	
            </tbody>
	</table>
	</c:when>
	 <c:otherwise>
        <br><h1>    <c:out value="No Users Found" /></h1>
        </c:otherwise>
        </c:choose>
</body>
</html>