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
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function() {
	
    $(document).on('click', '#submit', function() {  
    	 if($('#rname').prop("checked")==false && $('#cui').prop("checked")==false && $('#rate').prop("checked")==false && $('#loc').prop("checked")==false){
 			alert("Select a value to search");
 			return false;

 }       
    	if ($('#rname').is(':checked')) {
    		var rtext =$("#rtext").val();
    		if(rtext==""){
        		alert("Enter Hotel Name to Search");

        		return false;
        		}
        	}
    	else if ($('#rate').is(':checked')) {
    		var ratetext =$("#ratetext").val();
    		if(ratetext==""){
        		alert("Enter the rate to search");

        		return false;
        		}
    		if(rate<1){
				alert("Rate cannot be less than 1");
				return false;
        		}
        	}
    	else if ($('#loc').is(':checked')) {
    		var loctext =$("#loctext").val();
    		if(loctext==""){
        		alert("Enter the location to search");

        		return false;
        		}
    		
        	}

        else {
			return true;
        }
         
        
    });
});
</script>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/user/">Go Back</a><br/>
<h1>Search Restaurants</h1>
        <form  method="post" action="${contextPath}/user/searchresult">
            
            <input type="checkbox" name="search" value="hotelName" id="rname" checked>Restaurant Name &nbsp;&nbsp;
            <input type="text" name="hotelName" id="rtext" pattern="^[_A-z0-9]{1,}$"></br>
			<input type="checkbox" name="search" id="cui" value="cuisines">Cuisines &nbsp;&nbsp;&nbsp;&nbsp;
			<select name="foodtype">
			 	  <option disabled>Select</option>
				  <option value="italian">Italian</option>
				  <option value="american">American</option>
				  <option value="mexican">Mexican</option>
				  <option value="thai">Thai</option>
				  <option value="indian">Indian</option>
            </select></br>
             <input type="checkbox" name="search" id="rate" value="rate" pattern="^[_A-z0-9]{1,}$">Rate for 2 people &nbsp;&nbsp;
             <input type="text" id="ratetext" name="rate"></br>
             <input type="checkbox" id="loc" name="search" value="location" >Area &nbsp;&nbsp;&nbsp;&nbsp;
             <input type="text" id="loctext" name="area"></br>
             
            <input type="submit" id="submit" value="Search"/><br><br>
            <br><br>
           
        </form>
</body>
</html>