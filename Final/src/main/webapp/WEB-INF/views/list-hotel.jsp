<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    $(document).on('click', '#deactive', function(e) {
        e.preventDefault();
        var hotelID = $(this).parent().parent().data('hotelid');
        alert("HotelID"+hotelID);
            $.ajax({
                url: 'deactivateHotel',                
                contentType: "application/json; charset=utf-8",
                data: {'hotelID' : hotelID },
                type: 'GET',
                cache: false,
                success: function (response) {
                	alert(response);     
                	$("#act").val();                          
                }
            });
   	});
});
$(document).ready(function() {
    $(document).on('click', '#active', function(e) {
        e.preventDefault();
        var hotelID = $(this).parent().parent().data('hotelid');
        alert("HotelID"+hotelID);
            $.ajax({
                url: 'activateHotel',                
                contentType: "application/json; charset=utf-8",
                data: {'hotelID' : hotelID },
                type: 'GET',
                cache: false,
                success: function (response) {
                	alert(response);     
                	$("#id-"+hotelID).val("Active");                          
                }
            });
   	});
});
</script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/admin/">Go Back</a><br/>
<h1>List of Hotels</h1>
<c:choose>
	<c:when test="${!empty list}">
<table border="1">
<thead>
<tr>
		
		
		<th>Hotel Name</th>
		<th>Cuisines</th>
		<th>Email</th>
		<th>Phone No</th>
		
		
		</tr>
<thead>
<tbody>
            <c:forEach items="${list}" var="hotel">
                <tr id="id-${hotel.hotelID}" data-hotelid="${hotel.hotelID}">
                    
                    <td><input type="text" name="firstName" value="${hotel.hotelName}" readonly/></td>
                    <td><input type="text" name="lastName" value="${hotel.cuisines}" readonly/></td>
                    <td><input type="text" name="email" value="${hotel.email.emailAddress}" readonly/></td>
                    <td><input type="text" name="phoneNo" value="${hotel.phoneNo.phoneNo}" readonly/></td>
                    
                    
            </tr>
            </c:forEach>	
            </tbody>
	</table>
	</c:when>
	 <c:otherwise>
          <br><h1> <c:out value="No Hotels Found" /></h1> 
        </c:otherwise>
        </c:choose>
</body>
</html>