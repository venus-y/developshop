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
<title>상품 목록</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
	<link rel="stylesheet" href="<c:url value='/css/productList.css'/>">
</head>
<body>
	<jsp:include page="/WEB-INF/views/index.jsp" />
	<!-- 받아온 상품 정보를 목록에 표시한다. -->
	<table border="1">
	<!-- status 변수의 인덱스 % 5가 될 때마다 한 행을 추가하고, 인덱스 % 5가 4 또는 마지막 status값일 경우 행을 닫는다. -->
		<c:forEach var="product" items="${list}" varStatus="status">
				<c:if test="${status.index % 5 eq 0 }">
					<tr>
				</c:if>
				
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
					</div>
				</td>
				<c:if test="${status.index % 5 eq 4 or status.last }">
				</c:if>			
		</c:forEach>	
	</table>
	<!-- 페이징 영역 -->
	<div id="pageDiv">
	<c:if test="${ph.prevPage}">
		<!-- prevPage가 true이면 이전으로 이동 가능 -->
			<a href="<c:url value="/product/productList?page=${ph.startPage-1}&pageSize=${ph.pageSize}"/>">&lt</a>
	</c:if>
	<!-- 현재 페이지의 시작페이지부터 끝페이지까지 출력 -->
	<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
		<a href="<c:url value="/product/productList?page=${i}&pageSize=${ph.pageSize}"/>">${i}</a>
	</c:forEach>	
	<c:if test="${ph.nextPage}">
		<!-- nextPage가 true일 경우 다음으로 이동 가능 -->
			<a href="<c:url value="/product/productList?page=${ph.endPage+1}&pageSize=${ph.pageSize}"/>">&gt</a>
	</c:if>		
	</div>
</body>
	<script type="text/javascript">
		
	</script>

</html>