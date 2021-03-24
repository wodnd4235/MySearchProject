<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style="float:left; margin-right:10px;">
	<form method="post" action="/login">
		<label for="id">아이디</label>
		<input  type="text" name="username" id="id" placeholder="아이디" required />
		<label for="pass">비밀번호</label>
		<input type="password" name="password" id="pass" placeholder="비밀번호" required />
		<input type="submit" value="로그인" />
	</form>
</div>
<div style="float:left;">
	<button type="button" onclick="location.href='/member/join'">회원가입</button>
</div>
</body>
</html>