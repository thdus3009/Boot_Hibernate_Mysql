<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
		<h1>Member Update Form</h1>
		
		<form class="form-horizontal" action="memberUpdate" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">ID:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="name" name="id" value="${member.id}"
						readonly="readonly">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="email">PW:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="email"
						placeholder="name" name="pw" value="${member.pw}"
						readonly="readonly">
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="num" name="name" value="${member.name}">
				</div>
			</div>



			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="kor" name="email" value="${member.email}">
				</div>
			</div>



			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Phone:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="engl" name="phone" value="${member.phone}">
				</div>
			</div>



			<div class="form-group">
				<label class="control-label col-sm-2" for="image">Image:</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" id="files" name="files"
						value="" />
				</div>
				<%-- <form:errors path="file" cssClass="r"></form:errors> --%>
			</div>
			
			
			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-danger" id="update">Update</button>
		    </div>
			</div>
			
			
		</form>

			
	<script type="text/javascript">
		$("#d1").click(function() {

			var result = confirm("탈퇴 하시겠습니까?");
			if (result) {//result자체값이 true이기때문에 result==true라고 쓸 필요 없다.
				location.href = "./memberDelete";

			}
		});
	</script>
</body>
</html>