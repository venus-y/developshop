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
    	                        <input type="hidden" class="cart_id" value="${cart.cart_id}">
    	                        <input type="hidden" class="product_id" value="${cart.product_id}">  
    	                        <input type="hidden" class="product_name" value="${cart.product_name}">   	                      	                     
	                            <input type="hidden" class="price" value="${cart.price}">
                            	<input type="hidden" class="quantity" value="${cart.quantity}">
                            	<input type="hidden" class="discount" value="${cart.discount}">
                            	<input type="hidden" class="savepoint" value="${cart.savepoint}">
                            	<input type="hidden" class="total_price" value="${cart.totalprice}">
                            	<input type="hidden" class="sale_price" value="${cart.saleprice}"> 
                            	<input type="hidden" class="total_save_point" value="${cart.totalsavepoint}">
                            	<input type="hidden" class="product_thumbimage" value="${cart.product_thumbimage}">                                                      
                            </td>
                            <!-- 장바구니 상품 수량 변경 및 삭제 시 제출할 폼 양식 -->
                            <td>
	                  		<form action="/shop/cart/update" method="post" id="cart_update_delete" class="cart_update_delete_form">
			              		<input type="hidden" name="cart_id" class="update_delete_cart_id">
			              		<input type="hidden" name="quantity" class="update_quantity">
			        		    <input type="hidden" name="user_id" class="user_id" value="${sessionScope.user.id}">		              		
			              	</form>
                            </td>
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
                       			<button type="button" class="remove_cart" id="removeBtn" value="${cart.cart_id}">삭제</button>
                       			<!-- 삭제하기 전 alert으로 물어봐야함  -->
                       			</div>
                       		</td>	
                        </tr>
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
		                    	<!-- 주문 form -->
		                    	<form action="/shop/order/getOrder/${sessionScope.user.id}" method="get" class="order_form">
		                    	</form>
		                    	<!-- 주문 버튼 -->
		                    	<td>
			                    	<button type="button" class="orderBtn">주문 페이지로 이동</button>
		                    	</td>
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
			// 페이지가 처음 로드됐을 때 전체 체크박스의 상태가 체크돼있을 경우 하위 체크박스도 체크 상태로 바꿔준다.
			 let isAllChecked = $(".all_cart_checkbox").prop("checked");
			
			// 전체 체크박스의 상태에 따라 하위 체크박스들을 활성/비활성화 처리.
			$(".cart_checkbox").prop("checked", isAllChecked); 
			
			
			setInfo();
			// 로컬스토리지에서 체크상태 배열 가져오기
			/* let checkboxStatusArray = JSON.parse(localStorage.getItem("checkboxStatusArray"));
			// 로컬스토리지에서 전체체크여부를 받아온다.
			let allcheckboxStatus = localStorage.getItem("allCheckBoxStatus");
			
			// 각 체크박스의 상태에 따라 체크여부를 결정한다
			$(".cart_checkbox").each(function (index) {
				$(this).prop("checked", checkboxStatusArray[index] || false);
				// 체크박스배열의 값이 true일 경우 체크상태, 아닐 경우 false로 체크되지 않은 상태가 된다.
			});
			
			// 전체 체크박스여부가 체크였을 경우 체크, 아닐 경우 체크해제 상태로 유지한다.
			if(allcheckboxStatus === "checked"){
				$(".all_cart_checkbox").prop("checked", true);
			}else{
				$(".all_cart_checkbox").prop("checked", false);
			} */
			
			// 상품정보 갱신
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
			
			// 장바구니 삭제
		$(".remove_cart").on("click", function () {
			
			// 장바구니 번호를 가져온다.
			let cart_id = $(this).val();
						
			// 정말 삭제할껀지 물어본 후 이후의 처리를 진행한다.
			if(!confirm("정말로 삭제하시겠습니까?"))return;
			
			// ajax 요청으로 삭제 처리한다.
			$.ajax({
				type: "POST",
				url : "/shop/cart/remove/" + cart_id,
				headers: {"content-type" : "application/json"},
				// 성공 시 삭제 완료 메시지를 띄운다.
				success: function (result) {
					alert("해당 상품을 장바구니에서 삭제하였습니다.");	
				}, error: function (error) {
					alert("삭제 중 오류가 발생했습니다.");
				}
			})
			
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
					// 페이지 새로고침 
					location.reload(true);
					
				},
				error : function (error) {
					alert("장바구니 수정 중 오류발생");
				}
			
			})
			
		});
		
		// 주문 페이지로 이동 버튼이 눌렸을 때
		$(".orderBtn").on("click", function () {
			
			// 주문 폼에 넣을 값을 담아주는 변수
			let order_form = '';
			// instant_id는 상품정보를 배열에 담을 때마다 1씩 증가시킨다.
			let instant_id = 0;
			// 체크돼있던 상품들을 배열에 담는다.
			$(".cart_info").each(function (index, element) {
				
				if($(element).find(".cart_checkbox").is(":checked") === true){
					
					// 주문할 상품 정보를 담을 InstantOrderProduct 객체에 담을 값을 만들어준다.
					// 넣어줄 값들을 받아와 input 형식의 데이터로 만들어준다.
					
					let instant_id_input = "<input name='orderProducts[" + instant_id + "].instant_id' type='hidden' value='" + instant_id +"'>";
					
					// 인스턴트 id
					order_form += instant_id_input;
						
					let product_id = $(element).find(".product_id").val();
					
					let product_id_input = "<input name='orderProducts[" + instant_id +"].product_id' type='hidden' value='" + product_id +"'>"; 
					
					// 상품 id
					order_form += product_id_input;
					
					let quantity = $(element).find(".quantity").val();
					
					let quantity_input = "<input name='orderProducts[" + instant_id + "].quantity' type='hidden' value='" + quantity + "'>";
					
					// 상품 수량
					order_form += quantity_input;
					
					let product_name = $(element).find(".product_name").val();
					
					let product_name_input = "<input name='orderProducts[" + instant_id + "].product_name' type='hidden' value='" + product_name + "'>";
					
					// 상품명
					order_form += product_name_input;
					
					let discount = $(element).find(".discount").val();
					
					let discount_input = "<input name='orderProducts[" + instant_id + "].discount' type='hidden' value='" + discount + "'>";
					
					// 할인률
					order_form += discount_input;
					
					let price = $(element).find(".price").val();
					
					let price_input = "<input name='orderProducts[" + instant_id + "].price' type='hidden' value='" + price + "'>";
					
					// 가격
					order_form += price_input;
					
					let saleprice = $(element).find(".sale_price").val();
					
					let saleprice_input = "<input name='orderProducts[" + instant_id + "].saleprice' type='hidden' value = '" + saleprice + "'>";
					
					// 세일가
					order_form += saleprice_input;
					
					let totalprice = $(element).find(".total_price").val();
					
					let totalprice_input = "<input name='orderProducts[" + instant_id + "].totalprice' type='hidden' value = '" + totalprice + "'>";
					
					// 총 가격
					order_form += totalprice;
			
					let savepoint = $(element).find(".savepoint").val();
					
					let savepoint_input = "<input name='orderProducts[" + instant_id + "].savepoint' type='hidden' value = '" + savepoint + "'>"; 
					
					// 적립포인트
					order_form += savepoint_input;
					
					let totalsavepoint = $(element).find(".total_save_point").val();
					
					let totalsavepoint_input = "<input name='orderProducts[" + instant_id + "].totalsavepoint' type='hidden' value = '" + totalsavepoint + "'>";
					
					// 총 적립포인트
					order_form += totalsavepoint_input;
					
					let product_thumbimage = $(element).find(".product_thumbimage").val(); 
					
					let product_thumbimage_input = "<input name='orderProducts[" + instant_id + "].product_thumbimage' type='hidden' value = '" + product_thumbimage + "'>";
					
					// 썸네일 이미지
					order_form += product_thumbimage_input;
					
					// 만들어준 값들을 input 타입으로 폼에 추가해준다.
						 
						 
						 
			               /*  <input type="hidden" name="orderProducts[${instant_id}].product_id" value="${product_id}">
			                <input type="hidden" name="orderProducts[${instant_id}].quantity" value="${quantity}">
			                <input type="hidden" name="orderProducts[${instant_id}].productName" value="${product_name}">
			                <input type="hidden" name="orderProducts[${instant_id}].discount" value="${discount}">
			                <input type="hidden" name="orderProducts[${instant_id}].price" value="${price}">
			                <input type="hidden" name="orderProducts[${instant_id}].saleprice" value="${saleprice}">
			                <input type="hidden" name="orderProducts[${instant_id}].totalprice" value="${totalprice}">
			                <input type="hidden" name="orderProducts[${instant_id}].savepoint" value="${savepoint}">
			                <input type="hidden" name="orderProducts[${instant_id}].totalsavepoint" value="${totalsavepoint}">
			                <input type="hidden" name="orderProducts[${instant_id}].productThumbimage" value="${product_thumbimage}"> */
			            
					
			        // 인스턴트id를 하나 증가시킨다.
			        instant_id++; 
			            
			        // 생성한 order_form의 값들을 html 내에 만들어준 주문 폼에 넣어준다.
					$(".order_form").html(order_form);
					$(".order_form").submit();
				}
				
			})
		});
		
			
		// 상품에 달린 체크박스 설정이 변경됐을 때
		$(".cart_checkbox").on("click", function () {
			// 결제할 상품가격 및 적립포인트를 갱신시켜 보여준다.
			
			// 체크돼있는 항목 수와 총 체크박스의 수가 다를 경우 전체체크박스의 체크를 해제한다.
			// 하위 항목이 체크해제 됐을 경우 전체 체크박스가 비활성화 되는 것을 의미한다.
			$(".all_cart_checkbox").prop("checked", $(".cart_checkbox:checked").length === $(".cart_checkbox").length);
			setInfo();
		});
		
		// 전체 체크박스가 클릭 됐을 경우
		$(".all_cart_checkbox").on("click", function () {
			// 모든 상품 체크박스들을 체크시키고, 체크 해제했을 경우 모든 상품의 체크가 해제
			if($(".all_cart_checkbox").prop("checked")){
				$(".cart_checkbox").prop("checked", true);
			}else{
				$(".cart_checkbox").prop("checked", false);
			}
			// 결제 정보를 업데이트하고 체크박스 선택여부를 저장한다.
			setInfo();
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
		/* function checkBoxStatus() {
			// 각 체크박스의 체크 여부를 배열에 저장한다.
			// map 함수는 각 요소들에 함수를 실행해 요소의 체크여부를 알아낸다.
			let checkboxStatusArray = $(".cart_checkbox").map(function () {
				// 체크 여부를 반환한다.
				return this.checked;
			}).get();
			// get() : jQuery 객체를 일반 배열로 변환한다.
			
			// 전체 체크박스의 체크 여부도 추가한다.
			// unshift : 배열의 맨 앞에 요소를 추가
			
			// 배열을 로컬 스토리지에 저장, JSON 문자열로 변환해서 저장한다.
			localStorage.setItem("checkboxStatusArray", JSON.stringify(checkboxStatusArray));
		} */
		
		// 전체 체크박스 상태를 로컬스토리지에 저장한다.
		/* function saveAllCheckBoxStatus() {
			// 전체체크박스의 체크 여부를 받아와 저장한다.
			let allCheckBoxStatus = $(".all_cart_checkbox").prop("checked") ? "checked" : "unchecked";
			localStorage.setItem("allCheckBoxStatus", allCheckBoxStatus);
		} */
		
		
		
		
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