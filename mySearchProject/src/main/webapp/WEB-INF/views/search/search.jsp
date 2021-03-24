<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script type="text/javascript" src="/js/search.js"></script>
 <csrf disabled="true" />
<meta charset="UTF-8">
<title>장소검색</title>
</head>
<script>
	

</script>
<body>
<div class="green_window" style="text-align:center;">
	<form method="post"  name="major" onclick="return false">
		<input  type="text" name="keyword" style="width:300px;"  maxlength="100" value="${keyword}" placeholder="검색어를 입력해 주세요." required/><br/>
		<span style="color:red;">${valid_keyword}</span>
		<input type="submit" value="검색" style="color:blue; background-color:white;" class="search"/>
		<input type="button" value="내 검색내역 보기"  onclick="location.href='/my/search'" style="color:blue; background-color:white;"/>
 </form> 	
	<div class="green_window" style="float:right; margin-right: 400px">
		<h4>검색어 TOP10</h4>
		<c:choose>
			<c:when test="${not empty searchTop}">
				<c:forEach items="${searchTop}" var="top">
					<p><strong>검색어: </strong> ${top.keyword}, <strong>검색 횟수: </strong>  ${top.cnt} </p>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>-</p>
			</c:otherwise>
		</c:choose>
		
	</div>
</div>
<div class="green_window" id="searchList" style="text-align:center; margin-top: 200px; display:none;">
	<h1>검색내역</h1>
</div>
</body>
</html>