<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>

<style type="text/css">
.pagination {
	margin-left: 40%;
}
</style>

</head>

<body>
	<c:import url="../template/nav.jsp"></c:import>

	<div class="container">
		<h2>${board}List</h2>

	<!-- .............................................................................................. -->

		<form id="frm" class="form-inline" action="./${board}List">
			<input type="hidden" name="page" id="p">
			<div class="input-group input-group-sm col-xs-2">
				<select class="form-control" id="sel1" name="kind">
					<option id="title" value="title">title</option>
					<option id="writer" value="writer">writer</option>
					<option id="contents" value="contents">contents</option>
				</select> 
			</div>
			
			<div class="input-group input-group-sm col-xs-4">
				<input type="text" class="form-control" placeholder="Search" name="search" value="${param.search}">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
	
	<br>

	<!-- .............................................................................................. -->

	<table class="table talbe-hover">
		<tr>
			<td>Num</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Date</td>
			<td>Hit</td>
		</tr>

		<!-- page.contents : arraylist / 한 페이지당 글 몇개씩 꾸려주는 지 정해지는 부분-->
		<c:forEach items="${page.content}" var="vo">
			<tr>
				<td>${vo.num}</td>
				<td><a href="${board}Select?num=${vo.num}">${vo.title}</a></td>
				<td>${vo.writer}</td>
				<td>${vo.regDate}</td>
				<td>${vo.hit}</td>
			</tr>

		</c:forEach>

	</table>

	<!-- .............................................................................................. -->

	<div>
		<!-- 토탈 페이지 수 조회-->
		<c:forEach begin="1" end="${page.totalPages}" var="i">
			${i}
		</c:forEach>	
	
	<hr>
	
			<span><a href="#" class="custompager" title="0">&lt;&lt;</a></span>
			<span><a href="#" class="custompager" title="${page.number-1}"> &lt;</a></span>
			<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
				
				<c:if test="${i lt page.totalPages}">
					<a href="#" class="custompager" title="${i}">${i+1}</a>
				</c:if>
			</c:forEach>
			<span><a href="#" class="custompager" title="${page.number+1}">&gt;</a></span>
			<span><a href="#" class="custompager" title="${page.totalPages-1}">&gt;&gt;</a></span>
		
	</div>
	
	<div>
	
		<hr>
		<!-- 이전페이지 -->
		<c:if test="${page.isFirst() ne true}">
		<a href="./${board}List?page=${page.getNumber()-1}">[이전]</a>
		</c:if>
		
		<!-- 현재페이지 표시 방법 2가지 -->
		<span>${param.page+1}</span>
		<%-- <span>${page.getNumber()+1}</span> --%>
		
		<!-- 다음페이지 -->
		<c:if test="${not page.isLast()}">
		<a href="./${board}List?page=${page.getNumber()+1}">[다음]</a>
		</c:if>
		
		
	</div>

	<hr>
	<a href="./${board}Write" class="btn btn-danger">Write</a>
	</div>
	
	<script type="text/javascript">
			$(".custompager").click(function(){
				var page=$(this).attr("title");
				$("#p").val(page);
				$("#frm").submit();
			});
		
		
			var kind = '${param.kind}';
			if(kind == ''){
				$("#title").prop("selected", true);
			}else {
				$("#"+kind).prop("selected", true);
			}
			
		
		
		
		
			var result = '${result}';
			if(result != '') {
				if(result == 1) {
					alert("게시글 쓰기 성공");
				} else {
					alert("게시글 쓰기 실패");
				}
			}
	</script>

</body>
</html>