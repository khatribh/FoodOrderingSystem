
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Login</h2>
	
			
<form action="login-nav.htm" method="post">
	
	  <input type="radio" name="type" value="Admin"  checked>Admin<br>	
	  <input type="radio" name="type" value="User">User<br>
	  <input type="radio" name="type" value="Hotel">Hotel<br>
	  
	  <label>UserName: </label><input type="text" name="username" required><br> 
	  <label>Password: </label><input type="password" name="password" required><br>
	
	<input type="submit" value="Login" />	

	</form>

			
</body>
</html>