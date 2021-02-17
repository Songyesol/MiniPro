<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>

<script type="text/javascript">

	function formCheck() {
		var frm = document.frm;
		
		if( frm.mPassword.value != frm.mPassword1.value){ //패스워드 확인
			alert("비밀번호가 일치하지 않습니다.");
			frm.mPassword.value=null;
			frm.mPassword1.value=null;
			frm.mPassword.value.focus();
			return false;
		}
		
		return true;
	}
	
	function idCheck(str){
		var url ="idCheck.do?mid="+str;
		if(str==""){
			alert("아이디를 입력하세요.");
			frm.mId.focus();
		}else{
			window.open(url,"아이디중복체크","width=400, height=400,top=100,left=100");			
		}
		
	}
	
</script>
</head>
	<jsp:include page="../common/menu.jsp"/>
<body>
	<div align="center">
		<div><h2>회원가입</h2></div>
		<div>
			<form name="frm" id="frm" onsubmit="return formCheck()" action="memberJoin.do" method="post">
				<div>
					<table border=1>
						<tr>
							<th>아  이  디</th>
							<td><input type="text" name="mId" id="mId" required="required">&nbsp;<button type="button" onclick="idCheck(mId.value)">중복체크</button></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="mPassword" id="mPassword" required="required"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="mPassword1" id="mPassword1" required="required"></td>
						</tr>
						<tr>
							<th>이      름</th>
							<td><input type="text" name="mName" id="mName" required="required"></td>
						</tr>
					</table>
				</div><br/>
				<div>
					<input type="submit" value="회원가입"> &nbsp;&nbsp;
					<input type="reset" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>