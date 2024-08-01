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
<jsp:include page="/WEB-INF/views/link-rel.jsp" />
<jsp:include page="/WEB-INF/views/reviewLink-rel.jsp" />
<title>상품 목록</title>
	<link rel="stylesheet" href="<c:url value='/css/productInfo.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">	
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    	
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>	
  <jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="product_info">
	    <table>	
		
		<tr>
			<td colspan="4">
				<img style="width:500px;" src="/shop/upload${productInfo.product_thumbimage}">
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
			<td class="savepoint"><fmt:formatNumber value="${productInfo.savepoint}" pattern="#,### 포인트" /><td>
		</tr>
		<tr>
			
		
			<td colspan="2">			
			<span>주문수량</span>	
			<!-- 상품 수량 조절 -->	
			 <input type="number" min="1" class="quantity_input" value="1">					
			<span>
				<!-- button class="plus_Btn">+</button>
				<button class="minus_Btn">-</button> -->
			<!-- 장바구니 영역 -->				
			<!-- 로그인 했을 경우에 장바구니를 사용할 수 있다.  -->				
				<button class="btn cart_Btn">장바구니 담기</button>
			<!--  즉시 구매, form에 필요한 정보를 담아 요청을 보낸다. -->				
				<button class="btn buyNow_Btn" type="button">즉시 구매</button>
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
						
			<button class="btn" type="button" onclick="location.href='/shop/product/productList?page=${param.page}&pageSize=${param.pageSize}'">상품 목록</button>
				<!-- 유저가 관리자일 경우에만 상품 수정버튼이 보인다. -->
			<c:if test="${sessionScope.user.admincheck == 1 }">
			  <button class="btn" type="button" onclick="location.href='/shop/admin/updateProduct?product_id=${productInfo.product_id}&page=${param.page}&pageSize=${param.pageSize}'">상품 수정</button>
			</c:if>
			<!-- 유저가 관리자일 경우에만 상품 수정버튼이 보인다. -->
			<c:if test="${sessionScope.user.admincheck == 1 }">
			  <button class="btn removeProduct_Btn" type="button">상품 삭제</button>
			</c:if>				
			</td>
		</tr>
		</table>	
		</div>
			<!-- 리뷰 작성 영역  -->.
			<div class="bottom_div">
				<div class="review_title">
					<h2 style="text-align: center; margin-bottom: 10px;">상품 리뷰</h2>					
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
					<!-- <li>
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
					</li> -->
				</ul>
				<!-- 리뷰 페이징 영역 -->		
				<div class="review_pageInfo_div">
					<ul class="pageMaker">
						<!-- <li class="pageMaker_Btn_Prev">
								<a>이전</a>
						</li>
						<li class="pageMaker_Btn">
								<a>1</a>
						</li>
						<li class="pageMaker_Btn">
								<a>2</a>
						</li>
						<li class="pageMaker_Btn Active">
								<a>3</a>
						</li>
						<li class="pageMaker_Btn Next">
								<a>다음</a>
						</li>
						 -->
					</ul>
				</div>			
			</div>
			<jsp:include page="/WEB-INF/views/footer.jsp" />		
</body>
  <jsp:include page="/WEB-INF/views/script.jsp" />
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
		
		// 리뷰 가져오는데 사용될 변수
		let product_id = $(".product_id").text();
	
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
			
			// 할인된 가격을 형식화하여 표시한다.
			let formattedDiscountedPrice = new Intl.NumberFormat().format(discountedValue);

			
			// 구한 가격을 적용해서 표시한다.
			discountedPrice.text(formattedDiscountedPrice);		
			
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
				
				
			if(${empty sessionScope.user}){
				
				let currentUrl = encodeURIComponent(window.location.href);
				alert("로그인 후 이용해주세요, 로그인 화면으로 이동합니다.");
				location.href = '/shop/login/getLogin?toURL='+currentUrl;
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
							
							let same = $.trim(result);
							if(same == "same product already exists"){
								alert(result);
								return;
							}
							
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
			
			if(${sessionScope.user == null}){
				alert("로그인 후 이용해주세요.");
				return false;
			}
			
			Form_quantity.val(quantity); 
			form.submit();

		});	
			
		
			// 리뷰 목록 출력
			$.getJSON("/shop/review/reviewList", {product_id, product_id} , function(obj) {
				makeReviewContent(obj);
			
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
		
		
		
		// 리뷰 등록
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
		
		$(".reviewList").on("click", function () {
			getReviewList();
		});
		
		

		
		// 리뷰 페이지 정보
		let data = {
			product_id : product_id,
			page : 1,
			pageSize : 5
		}
		
		// 리뷰 동적 생성 메서드
		let reviewListInit = function() {
			$.getJSON("/shop/review/reviewList", data , function(obj) {
				makeReviewContent(obj);
			});
		}
		
		// 리뷰 페이지 내에 있는 이전, 다음, 페이지번호가 눌렸을 때의 처리
		$(document).on('click', '.pageMaker_Btn a', function(e) {
			// a 태그가 눌렸을 때의 기본동작을 제어
			e.preventDefault();
			
			// a 태그 내에 href와 value에서 페이지, 페이지사이즈 값을 가져온다.
			let page = $(this).attr("href");
			let pageSize = $(this).data('value');
			
			data.page = page;
			data.pageSize = pageSize;
			
			reviewListInit();
		});
		
		// 리뷰 수정
		$(document).on("click", ".update_Btn", function(e) {			
			let product_id = ${productInfo.product_id};
			let user_id = $(this).data('value');
			
			
			
			let popUp = "/shop/review/reviewUpdate/" + user_id
					+ "?product_id=" + product_id;
			let popOption = "width = 700px, height=490px, top=300px, left=300px, scrollbars=yes";
			
			window.open(popUp,"리뷰 수정",popOption);
		});
		
		// 리뷰 삭제
		$(document).on("click", ".delete_Btn", function(e) {			
			// 정말 삭제할껀지 물어본 후 이후의 처리를 진행한다.
			if(!confirm("정말로 삭제하시겠습니까?"))return;
			
			
			let product_id = ${productInfo.product_id};
			let user_id = $(this).data('value');
			
			let form = {
				product_id : product_id,
				user_id : user_id
			}
			
			$.ajax({
				url : '/shop/review/reviewDelete',
				type : 'POST',
				data : form,
				success : function (result) {
					window.close();
					alert("리뷰 삭제 완료!");
					location.reload(true);
				}
			})
		});
		
		 // 리뷰를 동적으로 생성하는 메서드
		 function makeReviewContent(obj)	 {
			// 작성된 리뷰가 없을 경우			
				if(obj.reviewList.length === 0){
					$(".review_not_div").html('<span>작성된 리뷰가 없습니다.</span>');
					$(".review_content_ul").html('');
					$(".pageMaker").html('');
					$(".review_content_ul").css("background-color","white");
				} else{
					$(".review_not_div").html('');
					
					let list = obj.reviewList;
					let rph = obj.rph;
					let user_id = '${sessionScope.user.id}'; 
					
					let review_list = '';
					/* 작성자 이미지 */
					let userImagePath = '<c:url value="/imgUpload/unknown.jpg"/>';

					// 서버에서 받아온 리뷰 목록을 브라우저 화면 상에 출력한다.
					$(list).each(function (i, obj) {
						review_list += '<li>';
						review_list += '<div class="review_div">';
						review_list += '<div class="review_top">';
						// 작성자 아이디, 작성일, 평점 등을 담는다.
						review_list += '<img src="' + userImagePath + '" alt="User Image" class="user_image">';
						review_list += '<span class="user_id_span">' + '작성자:' + obj.user_id +'</span>';
						review_list += '<span class="date_span">' + '작성일자:' + obj.regdate + '</span>';
						/* 평점의 수치만틈 별점으로 표시한다. */
						let reviewRate = obj.rating;
						let input_reviewRate = convertRatingToStars(reviewRate);						
						
						
						review_list += '<span class="rating_span"><span class="rating_value_span">' + input_reviewRate + '</span></span>';
						// 접속한 유저의 아이디 == 리뷰 작성자 아이디 -> 수정, 삭제 버튼 노출
						if(user_id === obj.user_id){
							review_list += '<button data-value="' + obj.user_id + '" class="update_Btn">수정</button>'
							review_list += '<button data-value="' + obj.user_id + '" class="delete_Btn">삭제</button>'

						}
						review_list += '</div>';
						review_list += '<div class="review_bottom">';
						review_list += '<div class="review_bottom_txt">' + obj.content + '</div>';
						review_list += '</div>';
						review_list += '</li>';
					});
					
					$(".review_content_ul").html(review_list);
					
					/* 페이징 영역 */
					
					let review_pageMaker = '';
					
					// a 태그에 담아줄 페이지 사이즈 변수
					let pageSize = rph.pageSize;
					
					// 이전으로 이동 가능여부 검사
					if(rph.prevPage){
						let prev_num = rph.startPage - 1;
						review_pageMaker += '<li class="pageMaker_Btn_Prev">';
						review_pageMaker += '<a href="' + prev_num + '" data-value = "' + pageSize + '">이전</a>';
						review_pageMaker += '</li>';
					}
					// 페이지 번호 -> 끝 페이지 + 1까지 i가 증가해야 끝페이지도 화면에 출력된다.
					for(let i = rph.startPage; i < rph.endPage+1; i++){
						review_pageMaker += '<li class="pageMaker_Btn ';
						// 확인할 코드
						if(rph.page === i){
							review_pageMaker += 'active';
						}
						review_pageMaker += '">';
						review_pageMaker += 
							'<a href="'+ i +'" data-value = "' + pageSize + '" >['+i+']</a>';
						review_pageMaker += '</li>';
					}
					 // 다음으로 이동 가능 여부 검사
					 if(rph.nextPage){
						 let next_num = rph.endPage + 1;
						 review_pageMaker += '<li class="pageMaker_Btn_Next">';
						 review_pageMaker += 
							 '<a href="' + next_num + '" data-value= "' + pageSize + '">다음</a>';
						 review_pageMaker += '</li>';						 
					 }
					 
					 $(".pageMaker").html(review_pageMaker);
				}
		}
		
		 // DB로부터 읽어온 평점 수치를 별점으로 변환
		 function convertRatingToStars(rating) {
			let stars = '';
			// rating값에 floor를 적용했을 때 나온 수치만큼만 왕별로 취급한다.
			let fullStars = Math.floor(rating);
			// rating값이 1로 나누어떨어지지 않을 경우 반별을 추가한다.
			let halfStar = (rating % 1 !== 0);
			// 왕별과 반별을 채우고 남은 부분은 빈 별로 채운다.
			let emptyStars = Math.floor(5-parseFloat(rating));
			
			// stars에 왕별 추가
			for(let i=0; i<fullStars; i++){
/* 		        stars += '<i class="rating__icon rating__icon--star fa fa-star"></i>';
 */		        stars += '<i class="fas fa-star"></i>';

			}
			
			// 반별을 추가해야되는지 확인 후 추가
			if(halfStar){
		        stars += '<i class="fas fa-star-half-alt"></i>';
			}
			if(emptyStars > 0.5){
				for(let i=0; i<emptyStars; i++){
			        stars += '<i class="far fa-star"></i>';
				}
			}
			
			return stars;
		}
		
	</script>
</html>