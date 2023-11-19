<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- 세션에 유저객체 존재여부에 따라 바뀌는 설정 -->
<c:set var="loginId" value="${empty sessionScope.user ? '' : sessionScope.user.id}"/>
<c:set var="loginOutText" value="${empty loginId ? 'Login' : 'LogOut' }"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/getLogin' : '/login/logout'  }"/>
<c:set var="registerText" value="${empty loginId ? 'Register' : 'MyPage' }"/>
<c:set var="registerLink" value="${empty loginId ? '/register/getRegister' : '' }"/>
<c:set var="CartLink" value="${empty loginId ?  '' : '/cart/getCart'  }"/>
<c:set var="CartText" value="${empty loginId ?  '' : 'Cart'  }"/>
<c:set var="AdminText" value="${sessionScope.user.admincheck == 1 ? 'Register Product' : '' }"/>
<c:set var="AdminLink" value="${sessionScope.user.admincheck == 1 ? '/admin/registerProductForm' : '' }"/>

<%-- <%@ page session="false" %> --%>
<html>
<head>
	<title>메인 페이지</title>
	    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    		
</head>
<body>
<div id="main">
	<ul>
	    <li id="logo">shop main</li>
	    <li><a href="<c:url value='/'/>">Home</a></li>
	    <li><a href="<c:url value='${AdminLink}'/>">${AdminText}</a></li> 
	    <li><a href="<c:url value='${loginOutLink}'/>">${loginOutText}</a></li>    
	    <li><a href="<c:url value='${registerLink}'/>">${registerText}</a></li>
	    <li><a href="<c:url value='${CartLink}'/>">${CartText}</a></li>
	    <li><a href="<c:url value='/product/productList'/>">Product List</a></li>
	</ul> 
</div>
<!-- for commit22   -->
</body>
</html>
