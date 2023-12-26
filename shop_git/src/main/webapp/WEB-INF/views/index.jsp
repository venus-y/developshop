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
<c:set var="registerProductText" value="${sessionScope.user.admincheck == 1 ? 'Register Product' : '' }"/>
<c:set var="registerProductLink" value="${sessionScope.user.admincheck == 1 ? '/admin/registerProductForm' : '' }"/>
<c:set var="adminText" value="${sessionScope.user.admincheck == 1 ? 'Admin Menu' : '' }"/>
<c:set var="OrderInfoText" value="${sessionScope.user.admincheck == 1 ? 'OrderInfo Manage' : '' }"/>
<c:set var="OrderInfoLink" value="${sessionScope.user.admincheck == 1 ? '/admin/getOrderList' : '' }"/>
<c:set var="QuestionListLink" value="${sessionScope.user!= null  ? '/question/getList' : '' }"/>
<c:set var="QuestionListText" value="${sessionScope.user!= null  ? 'Q&A' : '' }"/>




<%-- <%@ page session="false" %> --%>
<html>
<head>
	<title>메인 페이지</title>
<!-- 		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
 -->	    <link rel="stylesheet" href="<c:url value='/css/index.css'/>">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    		
	<style type="text/css">
		.jumbotron{
					background-image: url("imgUpload/main3.jpg");
					background-size: cover; /* 이미지를 화면에 맞게 조절합니다 */
					text-align: center; /* 텍스트를 가운데 정렬합니다 */
					background-size: 120% 120%; /* 이미지를 상하좌우로 20%씩 늘립니다 
					background-position: center center; /* 이미지를 가운데로 정렬합니다 */ 
					text-shadow: black 0.2em 0.2em 0.2em;
					color: white;
					padding: 100px 0; /* 상하 여백을 조절합니다 */
					height: 750px; /*높이 조절*/
					width: 2150px;/*넓이 조절*/ */
				}
		.jumbotron h2 {
		font-size: 3em; /* 헤더 텍스트 크기를 조절합니다 */
		margin-bottom: 0px; /* 헤더와 문단 사이의 간격을 조절합니다 */
	}

	.jumbotron p {
		font-size: 1.5em; /* 문단 텍스트 크기를 조절합니다 */
		margin-bottom: 10px; /* 간격을 줄임 */
	}
	</style>
</head>
<body>
<div id="main">
	<ul class="ul_index">
	    <li id="logo">shop main</li>
	    <li><a href="<c:url value ='/product/sortBrand'/>">Brand</a></li>
   		<li class="m1"><a href="<c:url value='/product/categorySet?category_code=1&viewName=top'/>">Top</a>
   			<ul class="m2">
   					<a href="<c:url value='/product/categorySet?category_code=11&viewName=top&detail=shirt'/>">
   					셔츠
   					</a>
   					<a href="<c:url value='/product/categorySet?category_code=13&viewName=top&detail=t-shirt'/>">
   					티셔츠
   					</a>
   			</ul>
   		</li>
    	<li class="m1"><a href="<c:url value='/product/categorySet?category_code=2&viewName=bottom'/>">Bottom</a>
    		<ul class="m2">
   					<a href="<c:url value='/product/categorySet?category_code=23&viewName=bottom&detail=shorts'/>">
   					반바지
   					</a>
   					<a href="<c:url value='/product/categorySet?category_code=22&viewName=bottom&detail=jeans'/>">
   					청바지
   					</a>
   			</ul>
    	<li class="m1"><a href="<c:url value='/product/categorySet?category_code=3&viewName=shoes'/>">Shoes</a>
    		<ul class="m2">
   					<a href="<c:url value='/product/categorySet?category_code=34&viewName=shoes&detail=sneakers'/>">
   						스니커즈
   					</a>
   					<a href="<c:url value='/product/categorySet?category_code=33&viewName=shoes&detail=shoes'/>">
   						구두
   					</a>
   				
   			</ul>
    	</li>
    	<li class="m1"><a href="<c:url value='/product/categorySet?category_code=4&viewName=accessory'/>">Acc</a>
    		<ul class="m2">
    			
   					<a href="<c:url value='/product/categorySet?category_code=43&viewName=accessory&detail=earrings'/>">
   						귀걸이
   					</a>
   				
   				
   					<a href="<c:url value='/product/categorySet?category_code=44&viewName=accessory&detail=ring'/>">
   						반지
   					</a> 				
   			</ul>
    	</li>   	
	   <%--  <li><a href="<c:url value='${CartLink}'/>">${CartText}</a></li> --%>
	   	
	   	
	   	<!-- 관리자용 드롭다운 메뉴 추가 -->
	   	<c:if test="${sessionScope.user.admincheck == 1 }">
            <li class="m1">
              <a class="dropdown-toggle" href=""
                role="button" id="menuDropdown" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                ${adminText}
              </a>
              <ul class="m2">
                <a href="<c:url value='${OrderInfoLink}'/>">${OrderInfoText}</a>
	    		<a href="<c:url value='${registerProductLink}'/>">${registerProductText}</a>                     
              </ul>
            </li>
        </c:if>
	    <!-- 회원용 드롭다운 메뉴 추가 -->	    	
            <li class="m1">
              <a class="dropdown-toggle" href="#" role="button" id="menuDropdown" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                Menu
              </a>
              <ul class="m2">
                <a class="dropdown-item" href="<c:url value='/'/>">Home</a>
                <a class="dropdown-item" href="<c:url value='${CartLink}'/>">${CartText}</a>
                <a class="dropdown-item" href="<c:url value='${loginOutLink}'/>">${loginOutText}</a>
                <a class="dropdown-item" href="<c:url value='${registerLink}'/>">${registerText}</a>                       
                <a class="dropdown-item" href="<c:url value='${QuestionListLink}'/>">${QuestionListText}</a>                                                              
              </ul>
            </li>
            
            <!-- ... 나머지 메뉴 항목 ... -->
	    <%-- <li><a href="<c:url value='${loginOutLink}'/>">${loginOutText}</a></li>    
	    <li><a href="<c:url value='${registerLink}'/>">${registerText}</a></li> --%>
	</ul> 
</div>
<script>
	/*  주문 처리 후 나타나는 메세지*/
	let msg = "${msg}";
	
	if(msg == "ORDER_SUCCESS"){
		alert("주문이 성공적으로 처리됐습니다!");
	}else if(msg == "ORDER_FAILED"){
		alert("주문 처리 중 오류가 발생했습니다!");
	}
</script>
<!-- for commit22   -->
<div class="container">
	<!-- 점보트론 -->
	<div class="jumbotron">
		<h2 class="text-center" style="height: 250px;">Entrance
			<br>
			Entrance는 다양한 브랜드들의 상품을 한 곳에서 볼 수 있는 온라인 편집샵입니다.
		</h2>
		<!-- <p class="text-center" style="height:200px;"></p> -->
	</div>
</div>
</body>
</html>
