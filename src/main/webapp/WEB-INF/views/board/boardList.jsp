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

		<form class="form-inline" action="./${board}List">
			<div class="input-group input-group-sm col-xs-2">
				<select value="key" class="form-control col-xs-2" id="sel1"
					name="kind">
					<option value="all">전체</option>
					<option value="title">Title</option>
					<option value="contents">Contents</option>
					<option value="writer">Wtiter</option>
				</select>
			</div>
		


		<div class="input-group input-group-sm col-xs-4">
			<input type="text" class="form-control" placeholder="Search" name="search">
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

		<c:forEach items="${list}" var="vo">
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
		<ul class="pagination">
			<c:if test="${pager.curBlock gt 1}">
				<li><a
					href="./${board}List?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">

				<li><a
					href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.curBlock lt pager.totalBlock}">
				<li><a
					href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
			</c:if>
		</ul>

	</div>

	<a href="./${board}Write" class="btn btn-danger">Write</a>
	</div>

</body>
</html>