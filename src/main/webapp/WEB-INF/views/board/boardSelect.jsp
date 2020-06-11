<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>

<body>
<c:import url="../template/nav.jsp"></c:import>
<div class="container">
	
	<h3>Title : ${vo.title}</h3>
	<h3>Writer : ${vo.writer}</h3>
	<h3>Contents : ${vo.contents}</h3>
	<br>
	
	<a href="${board}Update?num=${vo.num}" class="btn btn-primary">Update</a>
	<a href="${board}Delete?num=${vo.num}" class="btn btn-danger">Delete</a>
	
	<!-- notice일때는 reply가 보이면 안된다. -->
		<br><br>
		
		<c:if test="${board ne 'notice'}">
		<a href="./${board}Reply?num=${vo.num}" class="btn btn-default" >Reply</a>
		</c:if>
</div>
</body>
</html>