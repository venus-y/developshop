<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/category/category.css'/>">
</head>
<body>
	<jsp:include page="/WEB-INF/views/index.jsp" />
	<!-- 받아온 상품 정보를 목록에 표시한다. -->
	<table border="1">
		<c:forEach var="product" items="${productList}" varStatus="status">
				<!-- 가로 4 , 세로 2 형식으로 상품을 보여주기 위해서 요소를 4번째 순회했을 경우 tr 태그를 닫는다.  -->
				<c:if test="${status.index % 4 eq 0}">
					<tr>
				</c:if> 
				<td>
					<div>
					<a href='<c:url value='/product/productInfo?product_id=${product.product_id}&page=${ph.bsc.page}&pageSize=${ph.bsc.pageSize}'/>'>
							<img  src="/shop/upload/${product.product_thumbimage}">
					</a>	
						<br>
						[${product.product_name}]<br>
						${product.price}원<br>
				<c:if test="${product.stock == 0}">
					[품절]
				</c:if>
				<c:if test="${product.stock < 5 && product.stock > 0 }">
					[품절 임박!!]
				</c:if>
				<c:if test="${product.stock >= 5}">
					[판매중]
				</c:if>
					</div>
				</td>
				<c:if test="${status.index % 4 eq 3 or status.last }">
				</c:if>			
		</c:forEach>	
	</table>
	<!-- 페이지 영역 -->
	<div id="pageDiv">
		<c:if test="${ph.prevPage}">
			<a href="<c:url value="/product/brandPage${ph.bsc.getQueryString(ph.startPage-1)}"/>">&lt</a>
		</c:if>
		<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
			<a href="<c:url value="/product/brandPage${ph.bsc.getQueryString(i)}"/>">${i}</a>			
		</c:forEach>
		<c:if test="${ph.nextPage}">
			<a href="<c:url value="/product/brandPage${ph.bsc.getQueryString(ph.endPage+1)}"/>">&gt</a>
		</c:if>
		<!-- 검색창 -->		
		<c:set var="product" value="${productList[0]}"/>
		<!-- 카테고리 코드 참고용 변수 -->
		<form action='<c:url value="/product/brandPage"/>' method="get">
			<input type="text" name="keyword" class="search-input" placeholder="찾고 계신 상품명을 입력해주세요">
			<input type="hidden" name="category_code" value="${(product.category_code/100).intValue()}">
			<input type="submit" class="search-button" value="검색">		
		</form>			
	</div>
		
		
	
</body>
</html>