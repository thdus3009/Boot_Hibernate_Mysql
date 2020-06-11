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
		<div class="jumbotron">
			<h1>Member Login</h1>
			<p>Bootstrap is the most popular HTML, CSS, and JS framework for
				developing responsive, mobile-first projects on the web.</p>
		</div>

	</div>


	<div class="container" style="margin-left: 32%">
		<div class="row">
			<form class="form-horizontal" action="./memberLogin" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="id">ID:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="id"
							placeholder="Enter ID" name="id" style="width: 300px">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="pw">Password:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pw"
							placeholder="Enter Password" name="pw" style="width: 300px">
					</div>
				</div>


				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value="Submit">
					</div>
				</div>
			</form>

		</div>
	</div>

</body>
</html>