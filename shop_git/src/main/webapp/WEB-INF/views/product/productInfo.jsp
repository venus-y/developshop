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
	<link rel="stylesheet" href="<c:url value='/css/productInfo.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>	
	<jsp:include page="/WEB-INF/views/index.jsp" />
	<div class="product_info">
	    <table>	
		
		<tr>
			<td colspan="2"><img src="/shop/upload${productInfo.product_thumbimage}">
			</td>
		</tr>
		
		<tr>
			<td>상품번호</td>
			<td class="product_id">${productInfo.product_id}</td>
		</tr>
		
		<tr>
			<td>상품명</td>
			<td>${productInfo.product_name}</td>
		</tr>
		
		<tr>
			<td>가격</td>
			<td>
				<!-- 정가 -->
				<span class="original_price">${productInfo.price}</span>
				<span class="discounted_price"></span>원
			</td>    
		</tr>
		
		<tr>
			<td>할인율</td>
			<td>${productInfo.discount}%</td>
		</tr>
		
		<tr>
			<td>적립포인트</td>
			<td class="savepoint"></td>
		</tr>
		<tr>
			<td colspan="2">			
				<button type="button" onclick="location.href='/shop/product/productList?page=${param.page}&pageSize=${param.pageSize}'">상품 목록</button>
				
				<!-- 유저가 관리자일 경우에만 상품 수정버튼이 보인다. -->
			<c:if test="${sessionScope.user.admincheck == 1 }">
			  <button type="button" onclick="location.href='/shop/admin/updateProduct?product_id=${productInfo.product_id}&page=${param.page}&pageSize=${param.pageSize}'">상품 수정</button>
			</c:if>
			<!-- 유저가 관리자일 경우에만 상품 수정버튼이 보인다. -->
			<c:if test="${sessionScope.user.admincheck == 1 }">
			  <button class="removeProduct_Btn" type="button">상품 삭제</button>
			</c:if>
				
			</td>
		</tr>
		</table>	
	</div>
		
</body>
	<script type="text/javascript">
		$(document).ready(function () {
			
			// 상품 할인율 
			let discountRate = ${productInfo.discount};
			// 원래 가격
			let originalPrice = parseFloat($(".original_price").text());
			// 할인가
			let discountedPrice = $(".discounted_price");
			
			// 할인율을 구한다.
			let originalValue = parseFloat(originalPrice)
			// 할인가 = 정가 - (정가 * (할인률/100))
			let discountedValue = originalValue - (originalValue * (discountRate/100));
			// 구한 가격을 적용해서 표시한다.
			discountedPrice.text(discountedValue.toFixed(2));		
			
			
			// 적립포인트는 세일가의 1%만큼 적립된다.
			let savepoint = $(".savepoint");
			
			let savepoint_value = discountedValue * 0.01;
			
			savepoint.text(savepoint_value);
		});
		
		$(".removeProduct_Btn").on("click", function () {
			// 삭제하시겠습니까 확인시 계속 진행 아니오 누르면 return
			if(!confirm("정말로 삭제하시겠습니까?"))return;
			
			let product_id = $(".product_id").text();
			
			//ajax 요청으로 처리
			$.ajax({
				type: "POST",
				url : "<c:url value='/admin/deleteProduct'/>" ,
				data : { product_id : product_id},
				success: function (data) {
					alert("삭제 성공");
					
					 window.location.href = '<c:url value="/product/productList"/>';
				}, error: function (error) {
					alert("삭제 중 오류가 발생했습니다.")
				}
			})
			
			
		})
		
		
	</script>

</html>