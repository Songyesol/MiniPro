<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<jsp:include page="../common/menu.jsp"/>
<body>
	<div align="center">
		<div><h2>${vo.mName }님 회원가입에 성공하였습니다.</h2></div>
		<div>
			<button type="button" onclick="location.href='loginForm.do'">로그인 화면으로 이동</button>
		</div>
	</div>
</body>
</html>