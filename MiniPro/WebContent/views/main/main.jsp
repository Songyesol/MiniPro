<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<script>
 function goPage(page){
	 location.href='${pageContext.request.contextPath}/paging.do?pageNo='+page;
 }
</script>
</head>
<jsp:include page="../common/menu.jsp" />
<body>
<div align="center">
	<h1>여기는 처음으로 접속했을때 보여주는 페이지 입니다.</h1>
	<p>${vo}</p>
	<c:forEach var="vo" items="${list }">
		<p>${vo.employeeId } / ${vo.firstName } / ${vo.lastName } / ${vo.salary} / ${vo.email } / ${vo.hireDate }</p>
	</c:forEach>
<jsp:include page="../common/paging.jsp" />
</div>
</body>
</html>