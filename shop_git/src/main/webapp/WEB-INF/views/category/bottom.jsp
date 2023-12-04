<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/category/category.css'/>">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/index.jsp" />
	<!-- 받아온 상품 정보를 목록에 표시한다. -->
	<table border="1">
	<!-- status 변수의 인덱스 % 5가 될 때마다 한 행을 추가하고, 인덱스 % 5가 4 또는 마지막 status값일 경우 행을 닫는다. -->
		<c:forEach var="product" items="${productList}" varStatus="status">
				<%-- <c:if test="${status.index % 5 eq 0 }">
					<tr>
				</c:if> --%>
				
				<td>
					<div>
					<a href='<c:url value='/product/productInfo?product_id=${product.product_id}&page=${ph.page}&pageSize=${ph.pageSize}'/>'>
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
		<a href="<c:url value="/product/categorySet?category_code=2&viewName=bottom&page=${ph.startPage-1}&pageSize=${ph.pageSize}"/>">&lt</a>
	</c:if>

	<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
		<a href="<c:url value="/product/categorySet?category_code=2&viewName=bottom&page=${i}&pageSize=${ph.pageSize}"/>">${i}</a>
	</c:forEach>
	
	<c:if test="${ph.nextPage}">
		<a href="<c:url value="/product/categorySet?category_code=2&viewName=bottom&page=${ph.endPage+1}&pageSize=${ph.pageSize}"/>">&gt</a>
	</c:if>
	</div>
</body>
</html>