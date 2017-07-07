<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">

		<table>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" />
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>
			<tr>
				<td>User Type:</td>
				<td><form:input path="userType" size="30"
						required="required" /> <font color="red"><form:errors
							path="userType" /></font></td>
			</tr>
			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30"
						type="email" required="required" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>
			<tr>
				<td>Phone No:</td>
				<td><form:input path="phoneNo.phoneNo" size="30"
						required="required" /> <font color="red"><form:errors
							path="phoneNo.phoneNo" /></font></td>
			</tr>
			<tr>
				<td>Street No:</td>
				<td><form:input path="address.streetNo" size="30"
						required="required" /> <font color="red"><form:errors
							path="address.streetNo" /></font></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><form:input path="address.city" size="30"
						required="required" /> <font color="red"><form:errors
							path="address.city" /></font></td>
			</tr><tr>
				<td>State:</td>
				<td><form:input path="address.state" size="30"
						required="required" /> <font color="red"><form:errors
							path="address.state" /></font></td>
			</tr><tr>
				<td>Zipcode:</td>
				<td><form:input path="address.zipcode" size="30"
						required="required" /> <font color="red"><form:errors
							path="address.zipcode" /></font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="registeruser" value="Register User" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>