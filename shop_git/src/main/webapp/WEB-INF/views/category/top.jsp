<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/link-rel.jsp" />
<link rel="stylesheet" href="<c:url value='/css/category/category.css'/>">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
  	<jsp:include page="/WEB-INF/views/header.jsp" />
	<!-- 받아온 상품 정보를 목록에 표시한다. -->
	<table border="1">
	<!-- status 변수의 인덱스 % 5가 될 때마다 한 행을 추가하고, 인덱스 % 5가 4 또는 마지막 status값일 경우 행을 닫는다. -->
		<c:forEach var="product" items="${productList}" varStatus="status">
				<%-- <c:if test="${status.index % 5 eq 0 }">
					<tr>
				</c:if> --%>
				
				<td>
					<div>
					<a href='<c:url value='/product/productInfo?product_id=${product.product_id}&page=${ph.csc.page}&pageSize=${ph.csc.pageSize}'/>'>
							<img  src="/shop/upload/${product.product_thumbimage}">
					</a>	
						<br>
						[${product.product_name}]<br>
						${product.price}원<br>
				<c:if test="${product.stock == 0}">
					[품절]
				</c:if>
				<c:if test="${product.stock < 5 && product.stock > 0  }">
					[품절 임박!!]
				</c:if>
				<c:if test="${product.stock >= 5}">
					[판매중]
				</c:if>
					</div>
				</td>
			<%-- 	<c:if test="${status.index % 5 eq 4 or status.last }">
				</c:if>		 --%>	
		</c:forEach>	
	</table>
	
	<div id="pageDiv">
	<!--  페이징 영역 -->
	<c:if test="${ph.prevPage}">
		<a href="<c:url value="/product/categorySet${ph.csc.getQyeryString(ph.startPage-1)}"/>">&lt</a>
	</c:if>

	<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
		<a href="<c:url value="/product/categorySet${ph.csc.getQueryString(i)}"/>">${i}</a>
	</c:forEach>
	
	<c:if test="${ph.nextPage}">
		<a href="<c:url value="/product/categorySet${ph.csc.getQueryString(ph.endPage+1)}"/>">&gt</a>
	</c:if>
	<!-- 검색창 -->		
		<c:set var="product" value="${productList[0]}"/>
		<!-- 카테고리 코드 참고용 변수 -->
		<form action='<c:url value="/product/categorySet"/>' method="get">
			<input type="text" name="keyword" class="search-input" placeholder="찾고 계신 상품명을 입력해주세요">
			<input type="hidden" name="category_code" value="${param.category_code}">
			<input type="hidden" name="viewName" value="${param.viewName}">		
			<input type="submit" class="search-button" value="검색">		
		</form>		
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
	
</body>
	 <jsp:include page="/WEB-INF/views/script.jsp" />	
</html>
	