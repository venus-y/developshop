<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 실패시 반환받는 메세지를 디코딩 하기 위한 설정 -->    
<%@ page import="java.net.URLDecoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 이력</title>
    <jsp:include page="/WEB-INF/views/link-rel.jsp" />   
    <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<link rel="stylesheet" href="<c:url value='/css/purchaseHistory.css'/>">

		
</head>
<body>
	 <jsp:include page="/WEB-INF/views/header.jsp" />
	 <!-- 구매 목록 -->
	 	<div class="container">
			<!-- <div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart"> -->
							<table class="table-shopping-cart">
								<!-- 주문 이력 출력 -->
								<c:forEach var="purchase" items="${phList}">
								<tr class="table_head">
									<th class="column-1">상품이미지</th>
									<th class="column-1">주문번호</th>
									<th class="column-1">상품번호</th>
									<th class="column-1">상품명</th>
									<th class="column-1">배송상태</th>
									<th class="column-1">결제 수단</th>
									<th class="column-1">가격</th>
									<th class="column-1">수량</th>
									<th class="column-1">적립 포인트</th>
									<th class="column-1">주문일자</th>
								</tr>
								<!--  -->
								<tr class="table_row">
									<td class="column-1"><img src="/shop/upload/${purchase.product_thumbimage}" alt="IMG"></td>
									<td class="column-1">${purchase.order_id}</td>
									<td class="column-1">${purchase.product_id}</td>
									<td class="column-1">${purchase.product_name}</td>
									<td class="column-1">${purchase.status}</td>							
									<td class="column-1">${purchase.payment_method}</td>
									<td class="column-1">${purchase.price}</td>
									<td class="column-1">${purchase.quantity}</td>
									<td class="column-1">${purchase.savepoint}</td>
									<td class="column-1">${purchase.order_date}</td>
								<!--  -->
								</tr>
							</c:forEach>
							</table>
						<!-- </div>
					</div>
				</div>
			</div> -->
		</div>
	 <!-- 페이징 영역 -->		 
	 <div class="page_div">
	 	<c:if test="${ph.prevPage}">
	 		<a href="<c:url value="/order/purchase_History?user_id=${sessionScope.user.id}&page=${ph.startPage-1}&pageSize=${ph.pageSize}"/>">이전</a>
	 	</c:if>
	 	<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
	 		<a href="<c:url value="/order/purchase_History?user_id=${sessionScope.user.id}&page=${i}&pageSize=${ph.pageSize}"/>">${i}</a>
	 	</c:forEach>
	 	<c:if test="${ph.nextPage}">
	 		<a href="<c:url value="/order/purchase_History?user_id=${sessionScope.user.id}&page=${ph.endPage+1}&pageSize=${ph.pageSize}"/>">다음</a>
	 	</c:if>
	 </div>
        
   																	
    <jsp:include page="/WEB-INF/views/footer.jsp" />    
    <!-- 여기에 필요한 스크립트 등을 추가할 수 있습니다. -->
</body>							
	<jsp:include page="/WEB-INF/views/script.jsp" />	
    
</body>
</html>