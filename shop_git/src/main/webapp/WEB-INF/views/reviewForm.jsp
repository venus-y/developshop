<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/shop/css/reviewForm.css">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<style>
	.rating_div fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
.rating_div fieldset legend{
    text-align: right;
}
.rating_div input[type=radio]{
    display: none;
}
.rating_div label{
    font-size: 3em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
.rating_div label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.rating_div label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.rating_div input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#reviewContents {
    width: 100%;
    height: 150px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
}
</style>
</head>
<body>	
   <div class="wrapper_div">
	<div class="title_div">
		리뷰 등록
	</div>
 		<div class="review_div">
 			<!-- 상품명 -->
 			<div class="product_name_div">
 				<h2>${productInfo.product_name}</h2>
 			</div>
 			<!-- 평점 선택  -->
 			<div class="rating_div">
 				<!-- 별점 -->
 				<fieldset>
				    <span class="text-bold">별점을 선택해주세요</span>
					<input type="radio" name="rating" value="5" id="val1"><label for="val1">★</label>
					<input type="radio" name="rating" value="4" id="val2"><label for="val2">★</label>
					<input type="radio" name="rating" value="3" id="val3"><label for="val3">★</label>
					<input type="radio" name="rating" value="2" id="val4"><label for="val4">★</label>
					<input type="radio" name="rating" value="1" id="val5"><label for="val5">★</label>
 				</fieldset>
 			</div>			
 			<!-- 리뷰 작성 영역 -->
 			<div class="content_div">
 				<h4>리뷰</h4>
 				<textarea name="content"></textarea>
 			</div>
 			<!-- 리뷰 등록 버튼 -->
 			<div class="btn_Area">
 				<a class="write_Btn">등록</a><a class="cancel_Btn">취소</a>
 			</div>
 		</div>	  
 	 </div>
 	 
 	 
</body>
<script type="text/javascript">
    /* 등록 취소 버튼 눌렀을 경우 */
	$(".cancel_Btn").on("click", function () {
		window.close();
	});
    
    /* 등록 */
    $(".write_Btn").on("click", function () {
		// 폼에 전송할 정보를 담는다.
		const user_id = '${user_id}';
		const product_id = '${productInfo.product_id}';
		const rating = $("input[name='rating']:checked").val();
		const content = $("textarea[name='content']").val();
		
		
		const form = {
			user_id : user_id,
			product_id : product_id,
			rating : rating,
			content : content
		}
			
		// 리뷰 창 공백 여부 체크
		if(content == ""){
			alert("리뷰를 작성해주세요.")
			return;
		}
		
		// 유저가 상품에 리뷰를 작성한 적이 있는지 검사해서 있을 경우 작성 못하게 해야함
		
		
		
		$.ajax({
			url : '/shop/review/writeReview',
			type : 'POST',
			data : form,
			success : function (result) {
				window.close();
				alert("리뷰 등록 완료!");
			}
		
		})
    
    });
    
</script>
</html>