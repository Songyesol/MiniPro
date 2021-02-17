<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
.table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 1000px;
  
}

.table td, .table th {
  border: 1px solid #ddd;
  padding: 8px;
  text-align:center;
}


.table tr:hover {background-color: #ddd;}

.table th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
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
		<div>
			<table class="table">
				<tr>
					<th>사원번호</th>
					<th>성</th>
					<th>이름</th>
					<th>급여</th>
					<th>이메일</th>
					<th>입사일</th>
				</tr>
				<c:if test="${not empty list }">
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>${vo.employeeId }</td>
							<td>${vo.firstName }</td>
							<td>${vo.lastName }</td>
							<td>${vo.salary}</td>
							<td> ${vo.email }</td>
							<td>${vo.hireDate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
<jsp:include page="../common/paging.jsp" />
</div>
</body>
</html>