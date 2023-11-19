<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 실패시 반환받는 메세지를 디코딩 하기 위한 설정 -->    
<%@ page import="java.net.URLDecoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/index.jsp" />
    
    <div class="cart-container">
        <h2>장바구니</h2>
        <!-- 전체 체크박스 -->
        <div>
	        <input type="checkbox" class="all_cart_checkbox" checked="checked"><span class="all_check_span">전체선택</span>
        </div>
        
        <c:if test="${not empty cartList}">
            <table>
                <thead>
                    <tr>
                        <th>상품 이미지</th>
                        <th>상품명</th>
                        <th>가격</th>
                        <th>수량</th>
                        <th>합계</th>
                        <th>할인가격</th>
                        <th>총적립포인트</th>
                    </tr>
                </thead>
                <!--  주문목록에 추가할 상품을 정하기 위해서 장바구니 상품 목록 위에 체크박스를 만든다.-->
                
                <tbody>
                    <c:forEach var="cart" items="${cartList}">
                        <tr class="cart_info">
                        	<td><input type="checkbox" class="cart_checkbox" name="selectedItems" value="${cart.cart_id}" ></td>
                            <td><img src= "/shop/upload${cart.product_thumbimage}"></td>
                            <td>
    	                        <input type="hidden" class="cart_no" value="${cart.cart_id}">
	                            <input type="hidden" class="price" value="${cart.price}">
                            	<input type="hidden" class="quantity" value="${cart.quantity}">
                            	<input type="hidden" class="total_price" value="${cart.totalprice}">
                            	<input type="hidden" class="sale_price" value="${cart.saleprice}"> 
                            	<input type="hidden" class="total_save_point" value="${cart.totalsavepoint}">                                                       
                            </td>
                            <!-- 장바구니 상품 수량 변경 및 삭제 시 제출할 폼 양식 -->
                            <td>
	                  		<form action="/shop/cart/update" method="post" id="cart_update_delete" class="cart_update_delete_form">
			              		<input type="hidden" name="cart_id" class="update_delete_cart_id">
			              		<input type="hidden" name="quantity" class="update_quantity">
			        		    <input type="hidden" name="user_id" class="user_id" value="${sessionScope.user.id}">		              		
			              	</form>
                            </td>
                            <td>
                                <form action="<c:url value='/cart/remove'/>" method="post">                                   
                                </form>
                            </td>
                        </tr>
                        <tr>
                        <!-- 상품의 정보 -->
                        	<td>제품명 : ${cart.product_name}</td>
                       		<td>
                       			<del>정가 : <fmt:formatNumber value="${cart.price}" pattern="#,### 원" /></del><br>
                       			판매가 : <span><fmt:formatNumber value="${cart.saleprice}" pattern="#,### 원" /></span><br>
                       			적립포인트 : <fmt:formatNumber value="${cart.savepoint}" pattern="#,### 원"/>
                       		</td>
                       		<td class ="quantity_info">
                       			<div class="quantity_div">
                       			<input type="number" value="${cart.quantity}" class="quantity_input">
                       			<button class="quantity_plus" id="plusBtn">+</button>
                       			<button class="quantity_minus" id="minusBtn">-</button>
                       			<button type="button" class="quantity_modify" id="modifyBtn" value="${cart.cart_id}">수량 변경</button>
                       			<button>삭제</button>
                       			<!-- 삭제하기 전 alert으로 물어봐야함  -->
                       			</div>
                       		</td>	
                        </tr>
                    
                    </c:forEach>                   
                	  <!-- 총 결제돼야 할 상품 금액 및 총 적립될 포인트 정보 -->
		                    <tr>
			                    <td>총 상품가격 :<span class="totalprice_span"></span>원</td>
			                    <br>
			                    <td>총 주문 수량 :<span class="totalquantity_span"></span>개</td>
		    	                <td>총 적립 예상 포인트 :<span class="totalsavepoint_span"></span>포인트</td>
                	    		<td>배송비 :<span class="deliverycost_span"></span>원</td>
                    			<td>주문 예상 금액 : <span class="finaltotalprice_span"></span>원</td>
		                    </tr>		              
                </tbody>
            </table>
            
           
        </c:if>
        
        <c:if test="${empty cartList}">
            <p>장바구니가 비어있습니다.</p>
        </c:if>
    </div>
    
    <!-- 여기에 필요한 스크립트 등을 추가할 수 있습니다. -->
</body>
	<script type="text/javascript">
	
		$(document).ready(function () {
			// 로컬스토리지에서 체크상태 배열 가져오기
			let checkboxStatusArray = JSON.parse(localStorage.getItem("checkboxStatusArray"));
			
			// 각 체크박스의 상태에 따라 체크여부를 결정한다
			$(".cart_checkbox").each(function (index) {
				$(this).prop("checked", checkboxStatusArray[index] || false);
				// 체크박스배열의 값이 true일 경우 체크상태, 아닐 경우 false로 체크되지 않은 상태가 된다.
			});
			
			// 상품정보 갱신
			setInfo();
			
		});
	
		// 장바구니 수량 추가,감소 버튼 눌렀을 때 
			//1. 추가
		$(".quantity_plus").on("click", function () {
			let quantityVal = $(this).siblings(".quantity_input").val();
			$(this).siblings(".quantity_input").val(++quantityVal);
			// this: 현재 이벤트가 발생한 곳 -> 수량 증가 버튼
			// siblings로 현재 선택된 버튼의 형제 중에서 class가 quantity_input인 것의 값을 가져온다.
		});
			//2. 감소
		$(".quantity_minus").on("click", function () {
			let quantityVal = $(this).siblings(".quantity_input").val();
			// 최소수량은 1이기 때문에 최소수량보다 적게 설정할 수 없게 if문으로 검사
			if(quantityVal > 1){
				$(this).siblings(".quantity_input").val(--quantityVal);			
			}
		});
		// 변경된 장바구니 내용을 적용한다.
		$(".quantity_modify").on("click", function (e) {
			 // 폼 제출 전에 기본 동작을 막기
		    e.preventDefault();

			// 장바구니 번호와 수량을 가져온다.
			/* let cart_id = $(this).val(); */
			// 현재 선택된 폼에서 장바구니 번호와 상품수량을 가져온다.
			let cart_id = $(this).val();
			//let cart_id = form.find(".update_delete_cart_id").val();
			let quantity = $(this).siblings(".quantity_input").val(); 
			//let quantity = form.find(".update_quantity").val();
				
			// 비동기 요청으로 처리한다.
			$.ajax({
				type: "POST",
				url: "/shop/cart/update",
				headers: {"content-type" : "application/json"},
				// JSON 문자열로 변환해서 보낸다.
				data: JSON.stringify({
					cart_id : cart_id,
					quantity : quantity,
					user_id : "${sessionScope.user.id}"
				}),
				// 성공시 장바구니 수정 완료 메시지를 띄운다.
				success : function (data) {
					alert("장바구니 수정 완료");
					// 수정 후 상품 금액 정보 최신화
					// 페이지 새로고침 
					location.reload(true);
					
				},
				error : function (error) {
					alert("장바구니 수정 중 오류발생");
				}
			
			})
			
		});
			
			
		// 상품에 달린 체크박스 설정이 변경됐을 때
		$(".cart_checkbox").on("change", function () {
			// 결제할 상품가격 및 적립포인트를 갱신시켜 보여준다.
			setInfo();
			checkBoxStatus();
		});
		
		// 상품정보 갱신 함수
		function setInfo() {
		   // 최종가격, 제품수량 총 합계, 총 적립포인트, 배송료, 배송비+최종가격
		   let totalprice = 0;
		   let totalquantity = 0;
		   let totalsavepoint = 0;
		   let deliverycost = 0;
		   let finaltotalprice = 0;
		   
		   // 장바구니 상품들의 데이터를 바탕으로 위 선언한 변수들에 값을 세팅해준다.
		   // 반복문을 통해 체크돼있는 칸의 상품들에 한해서만 계산을 한다.
		   $(".cart_info").each(function (index, element) {
			if($(element).find(".cart_checkbox").is(":checked")===true){
				// 위에 선언한 hidden 속성 태그의 최종가격들을 전부 불러와 합산한다.
				totalprice += parseInt($(element).find(".total_price").val());
				// 각 상품의 수량을 전부 더한다.
				totalquantity += parseInt($(element).find(".quantity").val());
				// 각 상품의 총 적립포인트를 더한다.
				totalsavepoint += parseInt($(element).find(".total_save_point").val());
			}
		});
		   
		   // 배송료 책정을 한다. 총 주문금액이 5만원이 넘어갈 경우 배송비를 무료로 한다.
		   if(totalprice >= 50000){
			   deliverycost = 0;
		   }else{
			   deliverycost = 3000;
		   }
		 	
		   // 최종가격은 합산한 금액 + 배송료로 한다.
		   
		   finaltotalprice = deliverycost + totalprice;
		   
		   // 총 결제정보를 업데이트 해준다.
		   $(".totalprice_span").text(totalprice.toLocaleString());
		   $(".totalquantity_span").text(totalquantity.toLocaleString());
		   $(".totalsavepoint_span").text(totalsavepoint.toLocaleString());
		   $(".deliverycost_span").text(deliverycost.toLocaleString());
		   $(".finaltotalprice_span").text(finaltotalprice.toLocaleString());
		   
		  /*  checkBoxStatus(); */
		}
		
		
		// 체크박스 상태를 로컬 스토리지에 저장 후 다시 페이지가 로드됐을 때 체크상태를 유지시키기 위한 함수
		function checkBoxStatus() {
			// 각 체크박스의 체크 여부를 배열에 저장한다.
			// map 함수는 각 요소들에 함수를 실행해 요소의 체크여부를 알아낸다.
			let checkboxStatusArray = $(".cart_checkbox").map(function () {
				// 체크 여부를 반환한다.
				return this.checked;
			}).get();
			// get() : jQuery 객체를 일반 배열로 변환한다.
			
			// 배열을 로컬 스토리지에 저장, JSON 문자열로 변환해서 저장한다.
			localStorage.setItem("checkboxStatusArray", JSON.stringify(checkboxStatusArray));
		}
		
		// 페이지가 새로고침됐을 때 기존에 체크돼있던 항목들의 체크여부를 유지하기 위한 함수
		/* function checkBoxStatus() {
			// 체크박스 상태 가져오기
			let isChecked = $(".cart_checkbox").is(":checked");
			
			// 로컬 스토리지에 체크 상태를 저장한다.
			if(isChecked){
				localStorage.setItem("isChecked", "true");
			}else{
				localStorage.removeItem("isChecked");
			}
		} */
		
	</script>
</html>
	

</html>