<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/summer.jsp"></c:import>

<style type="text/css">
.r {
	color: red;
}
</style>


</head>
<body>
	<c:import url="../template/nav.jsp"></c:import>

	<div class="container">
		<h1>Member Join</h1>

		<form:form modelAttribute="memberVO" action="memberJoin" method="post"
			enctype="multipart/form-data">
			<!-- notice write -->


			<div class="form-group">
				<label for="title">id:</label>
				<form:input path="id" type="text" class="form-control" id="id" />
				<form:errors path="id" cssClass="r"></form:errors>
			</div>

			<div class="form-group">
				<label for="pw">pw:</label>
				<form:input path="pw" type="text" class="form-control" id="pw" />
				<form:errors path="pw" cssClass="r"></form:errors>
			</div>

			<div class="form-group">
				<label for="pwCheck">pwCheck:</label>
				<form:input path="pwCheck" type="text" class="form-control"
					id="pwCheck" />
				<form:errors path="pwCheck" cssClass="r"></form:errors>
			</div>

			<div class="form-group">
				<label for="name">name:</label>
				<form:input path="name" type="text" class="form-control" id="name" />
			</div>

			<div class="form-group">
				<label for="phone">phone:</label>
				<form:input path="phone" type="text" class="form-control" id="phone" />
			</div>


			<div class="form-group">
				<label for="email">email:</label>
				<form:input path="email" type="text" class="form-control" id="email" />
				<form:errors path="email" cssClass="r"></form:errors>
			</div>

			<input type="file" name="files">
<!-- 
			<input type="button" class="btn btn-info" id="add" value="FileAdd">
			<div class="form-group" id="f"></div>
 -->

			<br>
			<button type="submit" class="btn btn-default">Submit</button>



		</form:form>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#summernote').summernote({
				height : 500
			});
		});
	</script>

	<script type="text/javascript">
		var board = '${path}';

		if (board == 'Write') {
			$("#num").remove(); //내용지우기	
		}

		$("#add").click(function() {

			$("#f").append('<input type="file" name="files">');
		});
	</script>

</body>
</html>