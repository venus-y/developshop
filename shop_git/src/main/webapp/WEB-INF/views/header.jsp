<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<!-- 세션에 유저객체 존재여부에 따라 바뀌는 설정 -->
<c:set var="loginId" value="${empty sessionScope.user ? '' : sessionScope.user.id}"/>
<c:set var="loginOutText" value="${empty loginId ? 'Login' : 'LogOut' }"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/getLogin' : '/login/logout'  }"/>
<c:set var="registerText" value="${empty loginId ? 'Register' : 'MyPage' }"/>
<c:set var="registerLink" value="${empty loginId ? '/register/getRegister' : '/user/myPage' }"/>
<c:set var="CartLink" value="${empty loginId ?  '/login/getLogin' : '/cart/getCart'  }"/>
<c:set var="CartText" value="${empty loginId ?  '' : 'Cart'  }"/>
<c:set var="registerProductText" value="${sessionScope.user.admincheck == 1 ? 'Register Product' : '' }"/>
<c:set var="registerProductLink" value="${sessionScope.user.admincheck == 1 ? '/admin/registerProductForm' : '' }"/>
<c:set var="adminText" value="${sessionScope.user.admincheck == 1 ? 'Admin Menu' : '' }"/>
<c:set var="OrderInfoText" value="${sessionScope.user.admincheck == 1 ? 'OrderInfo Manage' : '' }"/>
<c:set var="OrderInfoLink" value="${sessionScope.user.admincheck == 1 ? '/admin/getOrderList' : '' }"/>
<c:set var="QuestionListLink" value="${sessionScope.user!= null  ? '/question/getList' : '' }"/>
<c:set var="QuestionListText" value="${sessionScope.user!= null  ? 'Q&A' : '' }"/>			
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
	
<!-- Header -->
	<header class="header-v2">
		<!-- Header desktop -->
		<div class="container-menu-desktop trans-03">
			<div class="wrap-menu-desktop">
				<nav class="limiter-menu-desktop p-l-45">
					
					<!-- Logo desktop -->		
					<a href="<c:url value='/'/>" class="logo">
						<img src="<c:url value='/imgUpload/fashion-flicks.png'/>" alt="IMG-LOGO">
					</a>

					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li class="active-menu">
								<a href="<c:url value ='/product/sortBrand'/>">Brand</a>
							</li>
								
							<li class="active-menu">
								<a href="<c:url value ='/product/categorySet?viewName=top&category_code=1'/>">Top</a>
								<ul class="sub-menu">
									<li><a href="<c:url value ='/product/categorySet?viewName=top&detail=shirt&category_code=11'/>">셔츠</a></li>
									<li><a href="<c:url value ='/product/categorySet?viewName=top&detail=t-shirt&category_code=13'/>">티셔츠</a></li>
								</ul>
							</li>
							<li class="active-menu">
								<a href="<c:url value ='/product/categorySet?viewName=bottom&category_code=2'/>">Bottom</a>
								<ul class="sub-menu">
									<li><a href="<c:url value ='/product/categorySet?viewName=bottom&detail=jeans&category_code=22'/>">청바지</a></li>
									<li><a href="<c:url value ='/product/categorySet?viewName=bottom&detail=shorts&category_code=23'/>">반바지</a></li>
								</ul>
							</li>
							<li class="active-menu">
								<a href="<c:url value ='/product/categorySet?viewName=shoes&category_code=3'/>">Shoes</a>
								<ul class="sub-menu">
									<li><a href="<c:url value ='/product/categorySet?viewName=shoes&detail=shoes&category_code=33'/>">구두</a></li> 
									<li><a href="<c:url value ='/product/categorySet?viewName=shoes&detail=sneakers&category_code=34'/>">스니커즈</a></li>
								</ul>
							</li>
							<li class="active-menu">
								<a href="<c:url value ='/product/categorySet?viewName=accessory&category_code=4'/>">Acc</a>
								<ul class="sub-menu">
									<li><a href="<c:url value ='/product/categorySet?viewName=accessory&detail=earrings&category_code=43'/>">귀걸이</a></li>
									<li><a href="<c:url value ='/product/categorySet?viewName=accessory&detail=ring&category_code=44'/>">반지</a></li>
								</ul>
							</li>
							
							<!-- 관리자에게만 보이는 메뉴 -->
							<c:if test="${sessionScope.user.admincheck == 1}">
							<li class="active-menu" data-label1="hot">
								<a href="#">Admin</a>
								<ul class="sub-menu">
									<li><a href="<c:url value="${OrderInfoLink}"/>">${OrderInfoText}</a></li>
									<li><a href="<c:url value="${registerProductLink}"/>">${registerProductText}</a></li>									
								</ul>
							</li>
							</c:if>
						</ul>
					</div>	
					
					<!-- Icon header -->
					<div class="wrap-icon-header flex-w flex-r-m h-full">
							
						<div class="flex-c-m h-full p-l-18 p-r-25 bor5">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart" data-notify="2">
									<a href="<c:url value='${CartLink}'/>">
										<i class="zmdi zmdi-shopping-cart">
										</i>
									</a>
							</div>
						</div>
							
						<div class="flex-c-m h-full p-lr-19">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-sidebar">
								<i class="zmdi zmdi-menu"></i>
							</div>
						</div>
					</div>					
				</nav>
			</div>	
		</div>
	  </header>
	  
	  	<!-- Sidebar -->
	<aside class="wrap-sidebar js-sidebar">
		<div class="s-full js-hide-sidebar"></div>

		<div class="sidebar flex-col-l p-t-22 p-b-25">
			<div class="flex-r w-full p-b-30 p-r-27">
				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-sidebar">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>

			<div class="sidebar-content flex-w w-full p-lr-65 js-pscroll">
				<ul class="sidebar-link w-full">
					<li class="p-b-13">
						<a href="<c:url value='/'/>" class="stext-102 cl2 hov-cl1 trans-04">
							Home
						</a>
					</li>
					<li class="p-b-13">
						<a class="stext-102 cl2 hov-cl1 trans-04" href="<c:url value='${loginOutLink}'/>">
							${loginOutText}
						</a>
					</li>
					<li class="p-b-13">
						<a href="<c:url value='${registerLink}'/>" class="stext-102 cl2 hov-cl1 trans-04">
							${registerText}
						</a>
					</li>					
					<li class="p-b-13">
						<a href="<c:url value='${QuestionListLink}'/>" class="stext-102 cl2 hov-cl1 trans-04">
							${QuestionListText}
						</a>
					</li>
				</ul>

				<div class="sidebar-gallery w-full p-tb-30">
					<span class="mtext-101 cl5">
						@ Fashion Flicks
					</span>
					<c:if test="${sessionScope.user.snscheck == 1 }">
				    <div>
				    	<a href="/shop/kakao/unlink">카카오 계정 연결 끊기</a>				    	
			    	</div>
			    	</c:if>
				
					<!-- <div class="flex-w flex-sb p-t-36 gallery-lb">
						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-01.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-01.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-02.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-02.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-03.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-03.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-04.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-04.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-05.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-05.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-06.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-06.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-07.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-07.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-08.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-08.jpg');"></a>
						</div>

						item gallery sidebar
						<div class="wrap-item-gallery m-b-10">
							<a class="item-gallery bg-img1" href="images/gallery-09.jpg" data-lightbox="gallery" 
							style="background-image: url('images/gallery-09.jpg');"></a>
						</div>flicks
					</div> -->
				</div>

				<div class="sidebar-gallery w-full">
					<span class="mtext-101 cl5">
						About Us
					</span>

					<p class="stext-108 cl6 p-t-27">	
			    FashionFlicks는 다양한 브랜드들의 상품을 한 곳에서 볼 수 있는 온라인 편집샵입니다.			
				</div>
			</div>
		</div>
	</aside>
</body>
</html>