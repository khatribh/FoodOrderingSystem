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
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Go Back</a><br/>
	

<a href="${contextPath}/user/album">Your Album</a>
<br>
	

	<form:form action="${contextPath}/user/addPhotos" commandName="user"
		method="post"  enctype="multipart/form-data">
		
		<table>
			



			<tr>
				<td>Upload More Photos:</td>
				<td><form:input path="photo" type="file" size="30"
						required="required" multiple="multiple" /></td>
			</tr>

			
			<tr>
				<td colspan="2"><input type="submit" value="Upload"/></td>
			</tr>
		</table>

	</form:form>


</body>
</html>