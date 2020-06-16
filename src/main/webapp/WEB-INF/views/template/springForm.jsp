<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- update의 경우 controller에서 qnaService.getSelectOne으로 정보를 가져와서 form:form의  modelAttribute로 받아온다.
	 write 경우 controller에서  new QnaVO()로 새로호출한다. / form태그에서 path는 get의 역할을 해서 
	 form:input에서 path="title"은 boardVO.getTitle과 같은 의미이다.-->

	<form:form modelAttribute="boardVO"  action="${board}${path}" method="post" enctype="multipart/form-data"><!-- notice write -->
	
	  <form:input  path="num" type="hidden" name="num" id="num" />
	
	  <div class="form-group">
	    <label for="title">Title:</label>
	    <form:input path="title" type="text" class="form-control" id="title" name="title"/>
	    <form:errors path="title" cssClass="r"></form:errors>
	  </div>
	  
	  <div class="form-group">
	    <label for="writer">Writer:</label>
<<<<<<< HEAD
	    <form:input path="writer" type="text" class="form-control" id="writer" name="writer"/>
	    
=======
	    <form:input path="writer" type="text" class="form-control" id="writer" name="writer" readonly="readonly"/>
>>>>>>> parent of a1b4d62... interceptor
	  </div>
	  
	  <div class="form-group">
	    <label for="contents">Contents:</label>
	    <form:textarea path="contents" rows="" cols="" class="form-control" id="summernote"></form:textarea>
	  </div>

	  <input type="button" class="btn btn-info" id="add" value="FileAdd">
	  <div class="form-group" id="f"></div>

	  <button type="submit" class="btn btn-default">Submit</button>
	
	</form:form>
