<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="UTF-8">
<title>나의 검색 리스트</title>
</head>
<body>
<div class="green_window" style="text-align:center; margin-top: 200px;">
	<c:choose>
		<c:when test="${not empty mySerachList}">
			<c:forEach items="${mySerachList}" var="search" varStatus="status">	
				<h4>${status.count}) 검색어: ${search.keyword}</h4> <h4>검색일: <fmt:parseDate value="${search.createdDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
				<fmt:formatDate value="${parsedDate }" pattern="yyyy-MM-dd"/> 
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h4>검색 내역이 없습니다.</h4>
		</c:otherwise>
	</c:choose>
	
</div>
 

</body>
</html>