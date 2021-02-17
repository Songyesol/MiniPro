<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 항상 매 페이지마다 넣어야함. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#topMenu {height: 30px; display:table;}

	#topMenu ul li {
		list-style: none;
		color: white;
		background-color: #2d2d2d;
		float: left;
		line-height: 30px;
		vertical-align: middle;
		text-align: center;
	}

	#topMenu .menuLink {
		text-decoration: none;
		color: white;
		display: block;
		width: 150px;
		font-size: 12px;
		font-weight: bold;
		font-family: "Trebuchet MS";
	}

	#topMenu .menuLink:hover {
		color: red;
		background-color: #4d4d4d;
	}
</style>
</head>
<body>
<div align = "center">
	<nav id="topMenu" >
		<ul>
			<li><a class="menuLink" href="main.do">Home</a></li>
			<li>|</li>
			<c:if test="${mid eq null }"> <!-- 아이디가 null 이면 login블락보여주기 -->
				<li><a class="menuLink" href="loginForm.do">로그인</a></li>
				<li>|</li> 
				<li><a class="menuLink" href="memberJoinForm.do">회원가입</a></li>
				<li>|</li>
			</c:if>
			<li><a class="menuLink" href="#">자유게시판</a></li>
			<li>|</li>
			<li><a class="menuLink" href="#">공지사항</a></li>
			<c:if test="${mid ne null }"> <!-- 아이디가 null이 아니면 logout블락보여주기 -->
			<li>|</li>
			<li><a class="menuLink" href="logOut.do">로그아웃</a></li>
			</c:if>
		</ul>
	</nav>
</div>
</body>
</html>
