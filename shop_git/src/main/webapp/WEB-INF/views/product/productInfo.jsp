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
				<!-- 재고 정보 -->
				<input type="hidden" class="stock_input" value="${productInfo.stock }">
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
			
			<span>주문수량</span>
			<input type="number" class="quantity_input" value="1">			
			<!-- 상품 수량 조절 -->	
			<span>
				<button class="plus_Btn">+</button>
				<button class="minus_Btn">-</button>
			<!-- 장바구니 영역 -->				
			<!-- 로그인 했을 경우에 장바구니를 사용할 수 있다.  -->				
				<button class="cart_Btn">장바구니 담기</button>
			<!--  즉시 구매, form에 필요한 정보를 담아 요청을 보낸다. -->				
				<button class="buyNow_Btn" type="button">즉시 구매</button>
			</span>
			<form id="order_form" action="<c:url value="/order/postOrder"/>" method="post">
				<!-- 상품 주문에 필요한 데이터(유저 아이디, 상품 수량, 상품 아이디)  -->
				<input type="hidden" class="id" name="id" value="${sessionScope.user.id}">
				<input type="hidden" class="quantity" name="quantity" value="">
				<input type="hidden" class="product_id" name="product_id" value="${productInfo.product_id}">				
			</form>
			
						
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
			
			
			
			// 선택 수량과 재고를 비교하기 위해 재고정보를 가져온다.
			let stock = $(".stock_input").val();
			
			// 선택 수량 정보를 가져온다.
			let quantity = $(".quantity_input").val();
			// +, - 버튼 클릭시 이벤트를 추가해준다.
			
			
			
			$(".plus_Btn").on("click", function () {
				$(".quantity_input").val(++quantity);
			});
			
			$(".minus_Btn").on("click", function () {
				// 선택수량이 1보다 작을 수 없다.
				if(quantity > 1){
					$(".quantity_input").val(--quantity);
				}
			});
			
			// Cart 컨트롤러로 전송해줄 데이터
			let form = {
				user_id : '${sessionScope.user.id}',
				product_id : '${productInfo.product_id}',
				quantity : '',
				product_name : '${productInfo.product_name}',
				discount : '${productInfo.discount}',
				savepoint : '${productInfo.savepoint}',
				price : '${productInfo.price}'
				
				
			};
			
			

			
			$(".cart_Btn").on("click", function () {
				form.quantity = $(".quantity_input").val();
				
			// 선택한 상품수량과 상품재고를 비교해서 선택수량이 상품재고보다 많을 시 알림 메시지를 띄운다.
				
			if(${empty sessionScope.user}){
				
				// 로그인에 성공 후 현재 페이지로 다시 돌아오기 위해 현재 URL을 저장한다.
				let currentUrl = encodeURIComponent(window.location.href);
				alert("로그인 후 이용해주세요, 로그인 화면으로 이동합니다.");
				location.href = '/shop/login/getLogin?toURL='+currentUrl;
				// 로그인 후 원래 있던 페이지로 이동하는 것이 자연스럽다.
				return false;
			}
			
			if(stock < form.quantity){
					alert("선택된 수량이 상품의 재고보다 많습니다.");
					return false;
				}else{
					$.ajax({
						url : '/shop/cart/register',
						type : 'POST',
						data : form,
						success : function (result) {
							alert(result)
							// 사용자에게 장바구니로 이동할껀지 묻는다.				
							if(confirm("장바구니로 이동하시겠습니까?")){
								location.href='/shop/cart/getCart';	
								
							}
							
						}
					})
				}
			})
			
		// 즉시주문 버튼 클릭 시
		$(".buyNow_Btn").on("click", function () {
			// form으로 전송할 선택수량 정보를 셋팅해줘야 한다.
			let form = $("#order_form");
			let quantity = $(".quantity_input").val();
			let Form_quantity = $(".quantity");
			
			Form_quantity.val(quantity);
			form.submit();
			
			/* 요청은 잘 전달된다! */
		});	
			
			
			
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