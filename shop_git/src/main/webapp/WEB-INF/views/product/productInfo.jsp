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
			<td class="savepoint">${productInfo.savepoint}</td>
		</tr>
		<tr>
			
		
			<td colspan="2">			
				<button type="button" onclick="location.href='/shop/product/productList?page=${param.page}&pageSize=${param.pageSize}'">상품 목록</button>
			
			<span>주문수량</span>
			<input type="number" min="1" class="quantity_input" value="1">			
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
			<form id="order_form" action="/shop/order/getOrder/${sessionScope.user.id}" method="get">
				<!-- 상품 주문에 필요한 데이터(유저 아이디, 상품 수량, 상품 아이디)  -->
				<input type="hidden" class="quantity" id="user_id" value="${sessionScope.user.id}">				
				<input type="hidden" class="quantity" name="orderProducts[0].quantity" value="">
				<input type="hidden" class="product_id" name="orderProducts[0].product_id" value="${productInfo.product_id}">				
				<input type="hidden" class="product_name" name="orderProducts[0].product_name" value="${productInfo.product_name}">
				<input type="hidden" class="discount" name="orderProducts[0].discount" value="${productInfo.discount}">
				<input type="hidden" class="price" name="orderProducts[0].price" value="${productInfo.price}">
				<input type="hidden" class="savepoint" name="orderProducts[0].savepoint" value="${productInfo.savepoint}">
				<input type="hidden" class="product_image" name="orderProducts[0].product_image" value="${productInfo.product_image}">
				<input type="hidden" class="product_thumbimage" name="orderProducts[0].product_thumbimage" value="${productInfo.product_thumbimage}">
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
			<!-- 리뷰 작성 영역  -->.
			<div class="bottom_div">
				<div class="review_title">
					<h2>리뷰</h2>					
				</div>
				
			<!-- 구매 이력이 있는 사람에 한해서 리뷰 작성 가능 -->	
			<c:if test="${orderHistoryCheck == 1 }">
				<div class="review_Btn_div">
				<button type="button" class="review_Btn">리뷰 작성</button>
				</div>
			</c:if>
				
				<!-- 리뷰 없을 시 나타나는 메세지 영역 -->
				<div class="review_not_div">
				
				</div>
				<!-- 리뷰 내용 출력 영역 -->
				<ul class="review_content_ul">
					<li>
						<div class="comment_div">
							<div class="review_top">
							  	<span class="user_id_span">testUser</span>
							  	<span class="date_span">2021-1111</span>
							  	<span class="rating_span">평점 : <span class="rating_value_span">4</span>점</span>
							  	<a class="update_review_Btn">수정</a><a class="delete_review_Btn">삭제</a>	 										
							</div>
							<div class="review_bottom">
								<div class="review_bottom_txt">
									teststestset
								</div>
							</div>
						</div>					
					</li>
				</ul>
				<!-- 리뷰 페이징 영역 -->		
				<div>
				
				</div>			
			</div>	
</body>
	<script type="text/javascript">
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
			
			
		/* 	// 적립포인트는 세일가의 1%만큼 적립된다.
			let savepoint = $(".savepoint");
			
			let savepoint_value = discountedValue * 0.01;
			
			savepoint.text(savepoint_value); */
			
			
			
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
			
			if(parseInt(stock) < parseInt(form.quantity)){
					alert("선택 수량:"+ form.quantity);
					alert("선택된 수량이 상품의 재고보다 많습니다.");
					return false;
				}else{
					$.ajax({
						url : '/shop/cart/register',
						type : 'POST',
						data : form,
						success : function (result) {	
							
							// 동일한 상품이 장바구니에 존재할 경우						
							let same = $.trim(result);
							if(same == "same product already exists"){
								alert(result);
								return;
							}
							
							// 잘 등록됐을 경우 등록 메세지 출력
							alert(result)
							
							// 사용자에게 장바구니로 이동할껀지 묻는다.	
							if(confirm("장바구니로 이동하시겠습니까?")){
								location.href='/shop/cart/getCart';	
							}
						}
							
						
					})
				}
			});
			
		// 즉시주문 버튼 클릭 시
		$(".buyNow_Btn").on("click", function () {
			// form으로 전송할 선택수량 정보를 셋팅해줘야 한다.
			let form = $("#order_form");
			
			let quantity = $(".quantity_input").val();
			let Form_quantity = $(".quantity"); 

			Form_quantity.val(quantity); 
			form.submit();

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
		});
		
		// 리류 등록
		$(".review_Btn").on("click", function () {
		  let user_id = $("#user_id").val();
		  let product_id = $(".product_id").text();
		  
		// 유저가 상품에 리뷰를 작성한 적이 있는지 검사해서 있을 경우 작성 못하게 해야함
			$.ajax({
				type: "POST",
				url: "/shop/review/checkHistory",
				contentType : "application/json; charset=utf-8",
				// JSON 문자열로 변환해서 보낸다.
				data: JSON.stringify({
					product_id : product_id,
					user_id : user_id
				}),
				// 반환값이 1일 경우 리뷰가 존재하는 상태 
				success : function (response) {
					// trim을 사용해 불필요한 공백들 제거
					let result = $.trim(response);
					if(result == "1"){
						alert("등록된 리뷰가 이미 존재합니다.");
						return false;
					}
					
				// 등록되지 않았을 경우 리뷰 작성창을 연다.
				  let popUp = "/shop/review/getWrite/" + user_id + "?product_id=" + product_id; 
				  
				  let popOption = "width = 700px, height=490px, top=300px, left=300px, scrollbars=yes";
					
				  window.open(popUp,"리뷰 작성",popOption);
					
				}
			})
		  
		  
		})
		
		
	</script>

</html>