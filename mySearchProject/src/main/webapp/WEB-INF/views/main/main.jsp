<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>카카오 사전과제 메인화면</title>
</head>
<body>

<c:if test="${!isLogin}">
	<div style="float:left; margin-right:10px;">
		<h1>로그인후 사용 가능합니다.</h1>
	</div>
</c:if>	
	<div style="float:left; margin-right:10px;">
		<c:choose>
			<c:when test="${!isLogin}">
					<button type="button" onclick="location.href='/login/form'">로그인</button>
					<button type="button" onclick="location.href='/member/join'">회원가입</button>
			</c:when>
			<c:when test="${isLogin}">
				<button type="button" onclick="location.href='/search'">장소검색하러 가기</button>
				<button type="button" onclick="location.href='/logout'">로그아웃</button>
			</c:when>
		</c:choose>
</div>
</body>
</html>