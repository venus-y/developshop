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
    <title>주문 페이지</title>
    <jsp:include page="/WEB-INF/views/index.jsp"></jsp:include>
    <link rel="stylesheet" href="<c:url value='/css/orderPage.css'/>">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	
</head>
<body>
    <header>
        
    </header>
   <div class="order-container">
     <h2 class="order_h2">주문 페이지</h2>
        <div class="address_div">
	        <div class="address_btn_div">
				<button type="button" class="address_Btn1" onclick="showAddress('1')">기본 주소</button>        		
				<button type="button" class="address_Btn2" onclick="showAddress('2')">주소지 변경</button>
    	    </div>
        	<div class="addressInfo_div">
        		<div class="addressInfo_input_1">
        			<!-- 기존 주소지 -->
        			<table>
        				<tr>
        					<th>주소 : </th>
        					<td>
        						${orderUser.address}
        					</td>
        				</tr>
        			</table>
        		</div>
        		<div class="addressInfo_input_2">
        			<!-- 신규 주소 -->
        			<table>
        				<tr>
        					<tr>
        						<td> 신규 주소 : 
        						<input class="newAddress1" readonly="readonly"><button type="button" class="address_search_Btn">주소 찾기</button>
        						</td>
        					</tr>
        					<tr>
        						<td>
        						<input class="newAddress2" readonly="readonly">
        						</td>
	        				</tr>        					
        					<tr>
	        					<td> 상세 주소 : 
        						<input class="newAddress3" readonly="readonly">
	        					</td>
        					</tr>
        				</tr>
        			</table>
        		</div>
        	</div>
        </div>
      	<table>
      		<!-- 받아온 상품정보를 페이지에 뿌려준다. -->
      		<c:forEach items="${instantList}" var="instantInfo">
      			<tr>
      				<th>상품 이미지</th>
                    <th>상품명</th>
                    <th>수량</th>
                    <th>적립포인트</th>
                    <th>할인금액</th>
                    <th>주문금액</th>
      			</tr>
      			<tr class="order_info">
      				<td>
	      			<!-- 이미지 영역 -->
						<img alt="이미지가 없습니다." src="/shop/upload${instantInfo.product_thumbimage}">      					
      					<!-- 상품 아이디 참조하기 위해 위치시킴 -->
      					<input class="product_id" type="hidden" value="${instantInfo.product_id}">
      					<!-- 상품 정가 참조용-->
      					<input class="price" type="hidden" value="${instantInfo.price}">
      					<!-- 유저 아이디 참조 -->
      					<input class="user_id" type="hidden" value="${orderUser.id}">
      					<!-- 상품 수량 참조 -->
      					<input class="check_quantity" type="hidden" value="${instantInfo.quantity}">
      				</td>      	
      				<td class="product_name">${instantInfo.product_name}</td>
      				<td class="quantity">${instantInfo.quantity}</td>
      				<td class="totalsavepoint">${instantInfo.totalsavepoint}</td>
      				<!-- 자바코드로 값을 계산하고 해당 값을 출력해야 한다. -->
      				<td><c:out value="${instantInfo.price * instantInfo.quantity - instantInfo.totalprice}"/></td>
      				<td class="totalprice" data-value="${instantInfo.totalprice}">
      					<del><c:out value="${instantInfo.price * instantInfo.quantity}"/></del>
      					<br>
      					${instantInfo.totalprice}
      				</td>
      			</tr>
      		</c:forEach>    			
      	</table>
      	<!-- 최종 결제 정보 -->
      	<table>
      			<tr>
      				<td>총 상품가격 : <span class="totalprice_span"></span>원
      				</td>
      			</tr>
      			<tr>
      				<td>배송비 : <span class="deliverycost_span"></span>원
      				</td>
      			</tr>
      			<tr>
      				<td>
      					예상 적립금 : <span class="totalsavepoint_span"></span>포인트
      				</td>
      			</tr>
      			<tr> 
      				<td>
	   			 		결제하실 금액 : <span class="finaltotalprice_span"></span>원  
      				</td>  
      			</tr>
      			<tr>     					 	     			 		
   			 		<td class="savepoint_field">      					
   			 		<!-- 적립 포인트 사용 -->
   			 			<span>포인트 사용 : </span>
   			 			<input type="text" class="point_zone">
   			 			<button class="allpoint_Btn">포인트 전부 사용</button>	
   			 			<button class="cancelpoint_Btn">사용 취소</button>
   					</td>				     				
      			</tr> 	
      			<tr>    				
      				<!-- 결제버튼  -->
      				<td>
      				<!-- 주문 폼 -->
      					<form id="order_form" class="order_form" action="/shop/order/postOrder2" method="post">
      					</form>
      					<button type="button" class="order_Btn">주문하기</button>	
      				</td>	
      			</tr>
      	</table>        
    </div>																					
    
    <!-- 여기에 필요한 스크립트 등을 추가할 수 있습니다. -->
</body>							

    <script>
    	$(document).ready(function () {
			setOrderInfo();
		})	
    
        function placeOrder() {
            // 주문 정보를 서버로 전송하는 로직을 추가할 수 있습니다.
            alert("주문이 완료되었습니다!");
        }
        
        // 주문정보 갱신
        function  setOrderInfo() {
			// 결제페이지에 띄워야 할 항목들에 대한 값을 가져온다.
			let totalprice = 0;
			let totalsavepoint = 0;
			let deliverycost = 0;
			let finaltotalprice = 0;
			
			// 현재 페이지에 존재하는 상품들을 반복문으로 하나씩 순회해서 정보를 받아온다.
			$(".order_info").each(function (index, element) {
				totalprice += parseInt($(element).find(".totalprice").data("value"));
				
				totalsavepoint += parseInt($(element).find(".totalsavepoint").text());
			})
			
			// 상품금액이 5만원 이하면 배송비 3천원 , 이상일 경우 배송비 무료
			if(totalprice >= 50000){
				deliverycost = 0;
			}else{
				deliverycost = 3000;
			}
			
			// 최종결제금액은 상품금액 + 배송비의 합
			finaltotalprice = totalprice + deliverycost;
			
			// 결제 정보를 화면에 띄운다.
			$(".totalprice_span").text(totalprice);
			$(".totalsavepoint_span").text(totalsavepoint);
        	$(".deliverycost_span").text(deliverycost);
        	$(".finaltotalprice_span").text(finaltotalprice);
        }
        
        // 주소 입력란 버튼 동작
        function showAddress(address_class) {
			// 버튼이 눌린 쪽의 영역에 속한 값만 페이지에 보이게 한다.
			$("[class^='addressInfo_input_']").css("display", "none");
			
			$(".addressInfo_input_" + address_class).css("display", "block");
			
			
			 $("[class^='address_Btn']").css("backgroundColor", "grey");
			// 선택된 영역의 버튼 색깔을 변경한다.
			$(".address_Btn"+address_class).css("backgroundColor", "black"); 
        }
        
        
        // 주소 변경
        $(".address_search_Btn").on("click", function () {
			daum_address();
		})
        
		// 포인트 전부 사용 버튼 눌렀을 때
		$(".allpoint_Btn").on("click", function () {
			// 포인트 정보를 표시해줄 영역
			let point_zone = $(".point_zone");
			// 유저의 보유 포인트를 가져온다.
			let user_point = parseInt(${orderUser.point});
			
			// 포인트 정보를 화면에 띄운다.
			point_zone.val(user_point);
			
			// 사용 취소 버튼을 활성화시키고 포인트 전부 사용 버튼을 비활성화시킨다.
		    $(".allpoint_Btn").css("display", "none");
			$(".cancelpoint_Btn").css("display","inline");
			
			// 결제정보에서 포인트 금액 만큼 차감
			let FinalTotalPrice1 = parseInt($(".finaltotalprice_span").text());
			let FinalTotalPrice2 = FinalTotalPrice1 - user_point;
			// 화면에 업데이트
			$(".finaltotalprice_span").text(FinalTotalPrice2);
			
		})
		
		// 포인트 사용 취소 버튼 눌렀을 때
		$(".cancelpoint_Btn").on("click", function () {
			let point_zone = $(".point_zone");
						
			// 포인트 칸을 비운다.
			point_zone.val("");
			
			// 사용 취소 버튼을 비활성화시키고 포인트 전부 사용 버튼을 활성화시킨다.
			$(".cancelpoint_Btn").css("display", "none");
			$(".allpoint_Btn").css("display", "inline");
			
			// 사용했던 포인트 만큼 다시 금액에 더한다.
			let user_point = parseInt(${orderUser.point});
			let FinalTotalPrice1 = parseInt($(".finaltotalprice_span").text());
			let FinalTotalPrice2 = FinalTotalPrice1 + user_point;
			// 다시 화면에 업데이트
			$(".finaltotalprice_span").text(FinalTotalPrice2);
			
		})
		
		// 최종적으로 재고 검사를 실시
		$(".order_Btn").on("click", function () {
			
			// 주문 폼에 넣어줄 정보를 담을 변수
			let order_form = '';
			
			// 재고체크를 통과해야 할 횟수
			let totalChecked = $(".order_info").length;
			// 재고체크 통과할 때마다 증가하는 변수
			let checkedCount = 0;
			
			// 배열의 카운트
			let arrayCount = 0;
			
		 	/* 주문처리컨트롤러로 넘겨주는 폼 데이터 만들어주는 것 부터 */
			
				$(".order_info").each(function (index, element) {
					// 넣어줄 값들을 받아와 input 형식의 데이터로 만들어준다.				
					
					// 재고가 상품 수량보다 부족하면 false;
					let stock_check = false;
					
					// 주문정보 테이블 들어갈 데이터 
					let user_id = $(element).find(".user_id").val();
					
					let total_amount = $(element).find(".quantity").val();
					
					let delivery_cost = $(".deliverycost_span").text();
					
					// 주문_상품 테이블에 들어갈 데이터
					
					let product_id = $(element).find(".product_id").val();
					
					let quantity = $(element).find(".quantity").val();
					
					// ajax 요청으로 상품의 재고정보를 받아온 후 재고가 넉넉할 경우에만 진행시키고 아닐 경우 return으로 종료
					$.ajax({
						type : "GET",
						url : "/shop/cart/check/"+product_id,
						success : function (response) {
							let quantity = parseInt($(element).find(".check_quantity").val());
		
							let stock = response;
							
/* 							 alert("재고를 검사해야 할 횟수 " + totalChecked);
 */							
							 if(quantity > stock) {
								stock_check = false;
								// alert을 위에 두면 아래 코드가 실행되기를 기다리지 않고 이후의 처리를 진행하므로 순서에 유의
								alert("요청수량이 재고보다 많음 -> 현재 통과된 횟수: " + checkedCount);
						        alert("주문form 제출가능여부: " + stock_check);						       
								alert($(element).find(".product_name").val() + "의 재고가 부족합니다.");		       
							}else if(quantity <= stock){
								// 통과할 경우 카운트 증가
								checkedCount ++;
								alert("재고가 충분함 -> 현재 통과된 횟수: " + checkedCount);
						        alert("주문form 제출가능여부: " + stock_check);								
							}
 
							// 재고 체크가 다 끝나고 통과할 경우 제출	
							 if(checkedCount === totalChecked){	
								 stock_check = true;
								 if(stock_check){
								 alert("다 통과됐을 때 실행");
								 $(".order_form").submit();	        										 
								 } 
						     } 
							
						
						}, error : function () {
							alert("재고 정보를 가져오는 도중 오류가 발생했습니다.");
							return false;
						}
					});
							
			})
			
			 
		})
		
        
     // 다음 주소 API
		function daum_address(){
 		console.log("동작");
	   new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            
	        	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
 
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
 
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                 	// 추가해야할 코드
                    // 주소변수 문자열과 참고항목 문자열 합치기
                      addr += extraAddr;
                
                } else {
                	addr += ' ';
                }
 
             	// 제거해야할 코드
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $(".newAddress1").val(data.zonecode);
                $(".newAddress2").val(addr);				
                // 커서를 상세주소 필드로 이동한다.
                $(".newAddress3").attr("readonly", false);
                $(".newAddress3").focus();	 
	            
	            
	        }
	    }).open();  		
	}
 
    </script>
</body>
</html>