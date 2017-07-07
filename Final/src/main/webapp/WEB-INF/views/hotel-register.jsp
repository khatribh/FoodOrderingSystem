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
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function() {
    $(document).on('click', '#submit', function() {        
        var rate =$("#rate").val();

        if(rate == "" ) {
            alert("Rate cannot be empty");
            return false;
        } else if (rate < 1) {
            alert("Rate cannot be less than 1");    
            return false;
        } else {
			return true;
        }
         
        
    });
});
</script>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Register a New Hotel</h2>

	<form:form action="${contextPath}/hotel/register" commandName="hotel"
		method="post">

		<table>
			<tr>
				<td>Hotel Name:</td>
				<td><form:input path="hotelName" size="30" pattern="^[_A-z ]{1,}$" maxlength="15" required="required" />
					<font color="red"><form:errors path="hotelName" /></font></td>
			
				<td>Cuisines:</td>
				<td><form:input path="cuisines" size="30" pattern="^[_A-z ]{1,}$" maxlength="15"  required="required" />
					<font color="red"><form:errors path="cuisines" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" pattern="^[_A-z0-9]{1,}$" maxlength="15" required="required" />
					<font color="red"><form:errors path="username" /></font></td>
			
				<td>Password:</td>
				<td><form:password path="password" size="30" data-minlength="6" pattern="[_A-z0-9]{1,}$"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>
			
			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30"
						type="email" required="required" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			
				<td>Phone No:</td>
				<td><form:input path="phoneNo.phoneNo" size="30" type="text" pattern="[123456789][0-9]{9}" maxlength="10" 
						required="required" /> <font color="red"><form:errors
							path="phoneNo.phoneNo" /></font></td>
			</tr>
			<tr>
				<td>Street Name:</td>
				<td><form:input path="address.streetName" size="30" pattern="^[_A-z0-9 ]{1,}$"
						required="required" /> <font color="red"><form:errors
							path="address.streetName" /></font></td>
			
				<td>City:</td>
				<td><form:input path="address.city" size="30" pattern="^[_A-z ]{1,}$"
						required="required" /> <font color="red"><form:errors
							path="address.city" /></font></td>
			</tr><tr>
				<td>State:</td>
				<td><form:input path="address.state" size="30" pattern="^[_A-z ]{1,}$"
						required="required" /> <font color="red"><form:errors
							path="address.state" /></font></td>
			
				<td>Zipcode:</td>
				<td><form:input path="address.zipcode" size="30" pattern="^[_0-9]{5,}$" maxlength="5"
						required="required" /> <font color="red"><form:errors
							path="address.zipcode" /></font></td>
			</tr>
			<tr>
				<td>Rate for 2:</td>
				<td><form:input path="rate" size="30"  id="rate" pattern="[0-9]*\.?[0-9]*"
						required="required" /> <font color="red"><form:errors
							path="rate" /></font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" id="submit" name="registerhotel" value="Register Hotel" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>