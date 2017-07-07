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
<%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            String role = (String) session.getAttribute("role");
            if (role == "user") {
    %>
	<h1>Hi, ${user.firstName}</h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a href="${contextPath}/logout.htm">Logout</a></br></br>
<a href="${contextPath}/user/search" >Search Hotels</a> <br />
<a href="${contextPath}/user/orders" >Orders</a> <br />
<a href="${contextPath}/user/photos" >Photos Upload</a> <br />

<a href="${contextPath}/user/updateInfo" >Update Info</a> <br />
<%
}
%>
</body>
</html>