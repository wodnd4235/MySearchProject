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
	<form method="post" action="/member/sign/up">
		<label for="id">아이디</label>
		<input  type="text" name="username" id="id" placeholder="아이디" value="${userDto.username}" required /><br/>
		<span style="color:red;">${valid_username}</span><br/>
		<label for="pass">비밀번호</label>
		<input type="password" name="password" id="pass" placeholder="비밀번호" value="${userDto.password}" required /><br/>
		<span style="color:red;">${valid_password}</span><br/>
		<input type="submit" value="가입하기" />
	</form>
</div>
</body>
</html>