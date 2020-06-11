<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/summer.jsp"></c:import>
</head>
<body>

<c:import url="../template/nav.jsp"></c:import>

<div class="container">
	<h2>${board} ${path}</h2>
	<c:import url="../template/springForm.jsp"></c:import>
</div>


<script type="text/javascript">

	$(document).ready(function() {
	  $('#summernote').summernote({
				height: 500
		  });
	});

</script>

<script type="text/javascript">

	var board ='${path}';

	if(board=='Write'){
		$("#num").remove(); //내용지우기	
	}

	$("#add").click(function(){
		
		$("#f").append('<input type="file" name="files">');
		
	});
	
</script>

</body>
</html>