<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 실패시 반환받는 메세지를 디코딩 하기 위한 설정 -->    
<%@ page import="java.net.URLDecoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
</head>
<body>
	<jsp:include page="/WEB-INF/views/index.jsp" />
	<!--로그인 폼 -->
	<form action="<c:url value="/login/postLogin"/>" method="post">
		<h3 id="title">Login</h3>
		<!-- 로그인 실패시 메세지 출력 -->
		<c:if test="${not empty param.msg }">
			<span class="msg">${URLDecoder.decode(param.msg)}</span>
		</c:if>
		<input class="login-Input" type="text" name="id" value="${cookie.id.value ? '' : cookie.id.value }" placeholder="Enter ID">
		<input class="login-Input" type="password" name="password" placeholder="Enter PassWord">
		<!-- 아이디 기억 -->
		<div class="rememberIdDiv">
			<input class="rememberId-check" id="rememberId" type="checkbox" name="rememberId" value="on" ${empty cookie.id.value ? "" : "checked" }>
			<label for="rememberId" class="rememberId-label">아이디 기억</label>
		</div>
		<input type="hidden" name="toURL" value="${param.toURL}">
		<button>로그인</button>
	</form>
</body>
</html>