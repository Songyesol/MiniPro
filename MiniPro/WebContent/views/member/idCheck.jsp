<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복체크</title>
<script type="text/javascript">

	function formClose(){
		if(${check }){
			window.opener.document.getElementById("mPassword").focus();
			//opener 부모창!!
		} else {
			window.opener.document.getElementById("mId").value="";
			window.opener.document.getElementById("mId").focus();
		}
		window.close();  
	}
</script>
</head>
<body>
	<div align="center">
		<h2>아이디 중복조회<br>----------------------------</h2>
		
		<h4>${msg }</h4>
		
		<button type="button" onclick="formClose()">확인</button>
	</div>
</body>
</html>