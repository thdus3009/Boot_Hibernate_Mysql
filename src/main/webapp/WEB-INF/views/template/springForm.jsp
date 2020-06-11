<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<form:form modelAttribute="boardVO"  action="${board}${path}" method="post" enctype="multipart/form-data"><!-- notice write -->
	
	  <form:input path="num" type="hidden" name="num" id="num" />
	
	  <div class="form-group">
	    <label for="title">Title:</label>
	    <form:input path="title" type="text" class="form-control" id="title" name="title"/>
	    <form:errors path="title" cssClass="r"></form:errors>
	  </div>
	  
	  <div class="form-group">
	    <label for="writer">Writer:</label>
	    <form:input path="writer" type="text" class="form-control" id="writer" name="writer" readonly="readonly"/>
	  </div>
	  
	  <div class="form-group">
	    <label for="contents">Contents:</label>
	    <form:textarea path="contents" rows="" cols="" class="form-control" id="summernote"></form:textarea>
	  </div>

	  <input type="button" class="btn btn-info" id="add" value="FileAdd">
	  <div class="form-group" id="f"></div>

	  <button type="submit" class="btn btn-default">Submit</button>
	
	</form:form>
