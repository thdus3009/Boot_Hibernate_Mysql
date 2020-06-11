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
		<div class="row">
			<h1>"My Page"</h1>
			<form id="form" name="form" method="post" action="./memberUpdate">
			<!-- Session에서 정보꺼내기 / EL사용 -->
			<img alt="" src="../upload/member/${member.memberFileVO.fileName}">
			<h3>Id : <input type="text" value="${sessionScope.member.id}" readonly="readonly"></h3>
			<h3>Pw : <input type="text" value="${member.pw}" ></h3>
			<h3>Name : <input type="text" value="${member.name}" ></h3>
			<h3>Email : <input type="text" value="${member.email}" ></h3>
			<h3>Phone : <input type="text" value="${member.phone}" ></h3>

	
			<br>
			<br>
			<button type="submit" class="btn btn-primary" id="d2">Update</button>
			<button type="button" class="btn btn-danger" id="d1">Delete</button>
			</form>
			<!-- 탈퇴버튼(한번더 확인해주어야함) -->
			<br>
			<br>
		</div>
	</div>


<script type="text/javascript">
$("#d1").click(function() {
	
	var result = confirm("탈퇴 하시겠습니까?");
	if(result){//result자체값이 true이기때문에 result==true라고 쓸 필요 없다.
	location.href="./memberDelete";
		
	}
});
</script>
</body>
</html>