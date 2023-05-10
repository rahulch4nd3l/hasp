<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
<%@ page import="com.rahul.service.userService" %>	
	<c:forEach var = "serv" items = "${serviceData}" >
		${serv.service}
        ${serv.techName}
        ${serv.date}
        ${serv.city}
		
	
	</c:forEach>
	
    
</body>
</html>