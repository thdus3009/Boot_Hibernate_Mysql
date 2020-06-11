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
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<!--  <th>Age</th>-->
					<th>Image</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${member.id}</td>
					<td>${member.name}</td>
					<td>${member.email}</td>
					<td>${member.phone}</td>
					<td><img alt=""
						src="../resources/upload/member/${member.memberFileVO.fileName}" /></td>



				</tr>
				<td><a href="./fileDelete">FileDelete</a></td>

			</tbody>
		</table>
		<img alt="" src="../upload/member/${member.memberFileVO.fileName}" />


		<button class="btn btn-primary" id="up">Update</button>
		<button class="btn btn-danger" id="del">Delete</button>



	</div>




	</div>
	<script type="text/javascript">
		$("#del").click(function() {
			var result = confirm("탈퇴하시겠습니까?");
			console.log(result);
			//true면 삭제, false면 취소
			//서버로 요청해야함    --- 4가지) 주소창, a태그, 폼, location
			if (result) {
				location.href = "./memberDelete";
			}
		});

		$("#up").click(function() {
			location.href = "./memberUpdate";
		});

		/* $("#up").on("click", function(){
			location.href="./memberUpdate"
		}); */
	</script>
</body>
</html>