<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/shop/css/reviewForm.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>	
   <div class="wrapper_div">
	<div class="title_div">
		리뷰 수정
	</div>
 		<div class="review_div">
 			<!-- 상품명 -->
 			<div class="product_name_div">
 				<h2>${productInfo.product_name}</h2>
 			</div>
 			<!-- 평점 선택  -->
 			<!-- <div class="rating_div">
 				별점
 				<fieldset>
				    <span class="text-bold">별점을 선택해주세요</span>
					<input type="radio" name="rating" value="5" id="val1"><label for="val1">★</label>
					<input type="radio" name="rating" value="4" id="val2"><label for="val2">★</label>
					<input type="radio" name="rating" value="3" id="val3"><label for="val3">★</label>
					<input type="radio" name="rating" value="2" id="val4"><label for="val4">★</label>
					<input type="radio" name="rating" value="1" id="val5"><label for="val5">★</label>
 				</fieldset>
 			</div> -->
 			<!-- 별점 선택 -->
 			<div id="half-stars-example">
    <div class="rating-group">
        <input class="rating__input rating__input--none" checked name="rating" id="rating-0" value="0" type="radio">
        <label aria-label="0 stars" class="rating__label" for="rating-0">&nbsp;</label>
        <label aria-label="0.5 stars" class="rating__label rating__label--half" for="rating-05"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
        <input class="rating__input" name="rating" id="rating-05" value="0.5" type="radio">
        <label aria-label="1 star" class="rating__label" for="rating-10"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
        <input class="rating__input" name="rating" id="rating-10" value="1" type="radio">
        <label aria-label="1.5 stars" class="rating__label rating__label--half" for="rating-15"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
        <input class="rating__input" name="rating" id="rating-15" value="1.5" type="radio">
        <label aria-label="2 stars" class="rating__label" for="rating-20"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
        <input class="rating__input" name="rating" id="rating-20" value="2" type="radio">
        <label aria-label="2.5 stars" class="rating__label rating__label--half" for="rating-25"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
        <input class="rating__input" name="rating" id="rating-25" value="2.5" type="radio" checked>
        <label aria-label="3 stars" class="rating__label" for="rating-30"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
        <input class="rating__input" name="rating" id="rating-30" value="3" type="radio">
        <label aria-label="3.5 stars" class="rating__label rating__label--half" for="rating-35"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
        <input class="rating__input" name="rating" id="rating-35" value="3.5" type="radio">
        <label aria-label="4 stars" class="rating__label" for="rating-40"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
        <input class="rating__input" name="rating" id="rating-40" value="4" type="radio">
        <label aria-label="4.5 stars" class="rating__label rating__label--half" for="rating-45"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
        <input class="rating__input" name="rating" id="rating-45" value="4.5" type="radio">
        <label aria-label="5 stars" class="rating__label" for="rating-50"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
        <input class="rating__input" name="rating" id="rating-50" value="5" type="radio">
    	<p class="desc" style="margin-bottom: 2rem; font-family: sans-serif; font-size:0.9rem">별점을 선택해주세요.</p> 
    </div>
  
</div>
 						
 			<!-- 리뷰 작성 영역 -->
 			<div class="content_div">
 				<h4>리뷰</h4>
 				<textarea name="content">${reviewInfo.content}</textarea>
 			</div>
 			<!-- 리뷰 등록 버튼 -->
 			<div class="btn_Area">
 				<a class="update_Btn">수정</a><a class="cancel_Btn">취소</a>
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
    $(".update_Btn").on("click", function () {
		// 폼에 전송할 정보를 담는다.
		const user_id = '${reviewInfo.user_id}';
		const product_id = '${reviewInfo.product_id}';
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
		
		
		
		
		
		$.ajax({
			url : '/shop/review/reviewUpdate',
			type : 'POST',
			data : form,
			success : function (result) {
				window.close();
				alert("리뷰 수정 완료!");
				location.reload(true);
			}
		
		})
    
    });
    
</script>
</html>