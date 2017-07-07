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
    $(document).on('click', '#save', function(e) {
        e.preventDefault();
        var foodID = $(this).parent().parent().data('fid');
        var fname = $(this).parent().siblings().find('.fname').val();
        var des = $(this).parent().siblings().find('.des').val();
        var price = $(this).parent().siblings().find('.price').val();
       if(fname==""){
    	   alert("Food Name cannot be empty");
           return false;
           }
       else if(des==""){
    	   alert("Description cannot be empty");
           return false;
           }
       else if(price==""){
    	   alert("Price cannot be empty");
    	   return false;
           }
       else if(price<1){
    	   alert("Price cannot be less than 1");
    	   return false;
           }
       else{
            $.ajax({
                url: 'editFood',                
                contentType: "application/json; charset=utf-8",
                data: { 'foodID': foodID, 'fname' : fname, 'des' : des, 'price' : price },
                type: 'GET',
                cache: false,
                success: function (response) {
                	alert(response);     
                	                                      
                }
            });
       }
    });
});
</script>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/hotel/">Go Back</a><br/>
<form>
<a href="${contextPath}/hotel/menu.pdf">View Menu in PDF</a>
<h1>Edit FoodItem Menu</h1><br>
<table border="1">            
            <thead>
                <tr>
                	
                    <th>Food Item</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Action</th>
                    
                </tr>                
            </thead>
            <tbody>
            <c:forEach items="${list}" var="food">
                <tr data-fid="${food.foodId}">
               
                    <td><input type="text" class="fname" value="${food.foodName}" readonly ></td>
                    <td><input type="text" class="des" value="${food.description}" readonly></td>
                    <td><input type="text" class="price" value="${food.price}" required="required" pattern="[0-9]*\.?[0-9]*" ></td>
                    <td><a href="#" id="save">Save</a></td>
                    
                </tr>                
            </c:forEach>
            </tbody>
            
        </table>
</form>
<br><br>

<br>

</body>
</html>