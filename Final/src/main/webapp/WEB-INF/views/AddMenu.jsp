<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function() {
    $(document).on('click', '#submit', function() {        
        var rate =$("#price").val();

        if(rate == "" ) {
            alert("Price cannot be empty");
            return false;
        } else if (rate < 1) {
            alert("Price cannot be less than 1");    
            return false;
        } else {
			return true;
        }
         
        
    });
});
</script>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/hotel/">Go Back</a><br/>
	
	
			<c:out value="${message}"></c:out>
		
	</h1>

	<h2>Add Food Items</h2>

	<form:form action="${contextPath}/hotel/addFood" commandName="foodItem"
		method="post">
		
		<table>
			<tr>
				<td>Food Item:</td>
				<td><form:input path="foodName" size="30" pattern="^[_A-z ]{1,}$" required="required" />
					</td>
			</tr>

			<tr>
				<td>Description :</td>
				<td><form:input path="description" size="30" pattern="^[_A-z ]{1,}$" required="required" />
					</td>
			</tr>


			<tr>
				<td>Price:</td>
				<td><form:input path="price" id="price" size="30" pattern="[0-9]*\.?[0-9]*" required="required" />
					</td>
			</tr>

			
			<tr>
				<td colspan="2"><input type="submit" id="submit" value="Add" /></td>
				<td colspan="2"><input type="reset" value="Reset" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>