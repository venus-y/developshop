<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>File Upload</title>
<title>상품 등록 페이지</title>
  	  <jsp:include page="/WEB-INF/views/link-rel.jsp" />
	    <link rel="stylesheet" href="<c:url value='/css/registerProductForm.css'/>">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    	
		<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
		
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />	
	<div class="registerProduct_div">
	<form id="form" method="post" enctype="multipart/form-data">
		<h3>상품 등록 페이지</h3>
		<!-- 상품명 -->
		<label>상품명</label>
			<input class="f-input product-Input" type="text" name="product_name" id="product_name">
		<!-- 상품명 공백 시 출력 메시지 -->
			<span class="product_name_empty">상품명을 입력해주세요.</span>
			
		<!-- 상품가격 -->
		<label>상품가격</label>
			<input class="f-input product-Input" type="text" name="price" id="price">
		<!-- 상품가격 공백 시 출력 메시지 -->
			<span class="price_empty">가격을 입력해주세요.</span>	
		
		<!-- 상품 할인률 -->
		<label>할인률</label>
			<input class="f-input product-Input" type="number" name="discount" id="discount" min="1" max="99">
		<!-- 할인률 공백 시 출력 메시지 -->
			<span class="discount_empty">상품명을 입력해주세요.</span>
		
		<!-- 상품 재고 -->
		<label>재고</label>
			<input class="f-input product-Input" type="number" min="1" name="stock" id="stock">
		<!-- 상품명 공백 시 출력 메시지 -->
			<span class="stock_empty">재고를 입력해주세요.</span>
		
		<!-- 상품 카테고리 코드 -->
		<label>분류</label>
			<span>브랜드</span>
			<select class="cate1" id="brand">
				<option selected value="none">선택</option>							
			</select>
		<!-- 브랜드 공백 시 출력 메시지 -->
			<span class="brand_empty">브랜드를 지정해주세요.</span>	
				
			<span>의류</span>
			<select class="cate2" id="clothes">
				<option selected value="none">선택</option>						
			</select>
		<!-- 의류 공백 시 출력 메시지 -->
			<span class="clothes_empty">의류종류를 입력해주세요.</span>	
			
			
			<span>세부 분류</span>
			<!-- 세부 분류의 카테고리 분류코드를 컨트롤러로 전달 -->
			<select class="cate3" name="category_code" id="sort">
				<option selected value="none">선택</option>		
			</select>
		<!-- 세부 분류 공백 시 출력 메시지 -->
			<span class="sort_empty">세부분류를 선택해주세요.</span>	
				
				
				
				
		<!-- 상품 적립포인트 -->
		<label>적립포인트</label>
			<input class="f-input product-Input" type="text" name="savepoint" id="savepoint">
		<!-- 적립포인트 공백 시 출력 메시지 -->
			<span class="savepoint_empty">적립포인트를 입력해주세요.</span>
		
		<!-- 이미지 파일 영역-->	
			<div class="product_image">	
				<span class="product_image_empty">이미지파일을 등록해주세요.</span>	
				<img src="" alt="이미지를 등록해주세요.">
				<label class="file_Btn" for="product_image">
				  업로드
				</label>			
				<input type="file" name="file" id="product_image">
		<!-- 이미지 파일 등록 -->				
			</div>
		<!-- 파일 공백 시 출력 메시지 -->
			
		<!-- 등록 버튼 -->
			<button type="button" class="register_btn">상품 등록</button>		
	</form>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />	
</body>
  <jsp:include page="/WEB-INF/views/script.jsp" />
	<script type="text/javascript">
	
	$(document).ready(function () {
		console.log('${cateList}'); 
		
		// 카테고리 정보를 받아온다. JSON.parse로 자바스크립트 객체로 변환
		let cateList = JSON.parse('${cateList}');

		// 카테고리 정보를 담을 배열
		let cate1Array = new Array();
		let cate2Array = new Array();
		let cate3Array = new Array();
		
		// 카테고리 정보를 담을 변수
		let cate1Obj = new Object();
		let cate2Obj = new Object();
		let cate3Obj = new Object();
		
		// 카테고리 분류코드를 선택할 수 있는 옵션 클래스를 선택자로 참조
		let cateSelect1 = $(".cate1");
		let cateSelect2 = $(".cate2");
		let cateSelect3 = $(".cate3");


		// 옵션에 카테고리 분류코드를 추가해주기 위한 변수	
		let cate1 = $(".cate1");
		let cate2 = $(".cate2");
		let cate3 = $(".cate3");
		 
		
		// 테스트
		makeCategoryTierList(cateList, cate1Array, cate2Array, cate3Array);
		
		/* // 대분류가 선택되면 기존에 있던 중분류 데이터가 사라지면서 새로운 중분류 옵션이 추가된다. */
		$(".cate1").on("change", function () {
				// 대분류에서 선택된 데이터를 가져온다.
				 let selectVal1 = $(this).find("option:selected").val();
				
				// 기존에 설정돼있었거나 선택되었던던 소분류,중분류 데이터를 지워준다.
				
				let cate2 = $(".cate2");
				let cate3 = $(".cate3");
				
				cate2.empty();
				cate3.empty();
				// 위에 코드는 사실 지금 크게 의미가 없어서 지우는 것도 고려
				
				//  기존에 있던 분류코드 데이터를 제외하고 option을 추가
				cate2.append("<option value='none'>선택</option>");
				cate3.append("<option value='none'>선택</option>");
				
				
				// 선택된 cate1의 카테고리 분류코드와 cate2의 부모카테고리코드가 일치할 경우 cate2의 카테고리를 추가
				for(let i = 0; i < cate2Array.length; i++){
					if(selectVal1 === cate2Array[i].category_parent_code.toString()){
				        console.log('부모 코드: ' + cate2Array[i].category_parent_code + ', 분류 코드: ' + cate2Array[i].category_code);
						cate2.append("<option value='"+cate2Array[i].category_code+"'>" + cate2Array[i].category_name + "</option>");
					}
				}
		})
		
		// 중분류가 선택되면 기존에 있던 소분류 데이터가 사라지면서 새로운 소분류 옵션이 추가된다.
		$(".cate2").on("change", function () {
				// 대분류에서 선택된 데이터를 가져온다.
				 let selectVal2 = $(this).find("option:selected").val();
				// 중분류에 추가된 데이터를 지워준다.
				cate3.empty();
				
				
				// option을 추가
				cate3.append("<option value='none'>선택</option>");
				// 선택된 cate2의 카테고리 분류코드와 cate3의 부모카테고리코드가 일치할 경우 cate3의 카테고리를 추가
				 for(let i = 0; i < cate3Array.length; i++){
					if(selectVal2 === cate3Array[i].category_parent_code.toString()){
				        console.log('부모 코드: ' + cate3Array[i].category_parent_code + ', 분류 코드: ' + cate3Array[i].category_code);
						cate3.append("<option value='" +cate3Array[i].category_code+ "'>" + cate3Array[i].category_name + "</option>");
					}
				} 
		})
		
		
		// 넘겨줘야 할 값 : 찾을 분류코드, 비교할 분류코드리스트, 분류코드 정보를 담아줄 객체, 분류코드를 저장할 배열
		/* makeCategoryTierList(1, cateList, cate1Array, 1);
		makeCategoryTierList(2, cateList, cate1Array, 1);
		makeCategoryTierList(3, cateList, cate1Array, 1);
		makeCategoryTierList(4, cateList, cate1Array, 1);
		
		makeCategoryTierList(11, cateList, cate2Array, 2);
		makeCategoryTierList(22, cateList, cate2Array, 2);
		makeCategoryTierList(33, cateList, cate2Array, 2);
		makeCategoryTierList(44, cateList, cate2Array, 2);
		
		makeCategoryTierList(111, cateList, cate3Array, 3);
		makeCategoryTierList(222, cateList, cate3Array, 3);
		makeCategoryTierList(333, cateList, cate3Array, 3);
		makeCategoryTierList(444, cateList, cate3Array, 3); */
		// 중복 코드 


		
		// option value에 카테고리 분류코드를 담아준다.
		for(let i = 0; i < cate1Array.length; i++){
			cate1.append("<option value='"+cate1Array[i].category_code+"'>" + cate1Array[i].category_name + "</option>");
		}
		
		for(let i = 0; i < cate2Array.length; i++){
			cate2.append("<option value='"+cate2Array[i].category_code+"'>" + cate2Array[i].category_name + "</option>");
		}
		
		for(let i = 0; i < cate3Array.length; i++){
			cate3.append("<option value='"+cate3Array[i].category_code+"'>" + cate3Array[i].category_name + "</option>");
		}
		
		
		// 상품 등록 버튼 클릭 시 실행되는 이벤트
		$(".register_btn").on("click", function () {
			
			/* 공백 유효성 검사를 위한 변수 */

			let product_name = $("#product_name").val();
			
			let price = $("#price").val();
			
			let discount = $("#discount").val();
			
			let release_date = $("#release_date").val();
			
			let stock = $("#stock").val();
			
			let category_code = $("#category_code").val();
			
			let savepoint = $("#savepoint").val();
			
			let brand = $("#brand").val();
			
			let clothes = $("#clothes").val();
			
			let sort = $("#sort").val();
			
			let product_image = $("#product_image").val();
			
			// 공백 여부가 통과되면 true로 바뀐다
			
			let product_name_check = false;

			let price_check = false;
			
			let discount_check = false;			
			
			let stock_check = false;
			
			let category_code_check = false;
			
			let savepoint_check = false;
			
			let brand_check = false;
			
			let clothes_check = false;
			
			let sort_check = false;
			
			let product_image_check = false;
			
			
			// 상품 등록 시 입력해야 될 값들에 대해 유효성 검사를 실시하고 통과할 경우 상품 등록요청이 전송된다.
			// 입력하지 않았을 경우 입력요청 메시지 출력
			if(product_name == ""){
				$(".product_name_empty").css("display", "block");
				product_name_check = false;
			}else{
				$(".product_name_empty").css("display", "none");
				product_name_check = true;
			}
			
			if(price == ""){
				$(".price_empty").css("display", "block");
				price_check = false;
			}else{
				$(".price_empty").css("display", "none");
				price_check = true;
			}
			
			if(discount == ""){
				$(".discount_empty").css("display", "block");
				discount_check = false;
			}else{
				$(".discount_empty").css("display", "none");
				discount_check = true;
			}		
			
			if(stock == ""){
				$(".stock_empty").css("display", "block");
				stock_check = false;
			}else{
				$(".stock_empty").css("display", "none");
				stock_check = true;
			}
			
			if(brand == "none"){
				$(".brand_empty").css("display", "block");
				brand_check = false;
			}else{
				$(".brand_empty").css("display", "none");
				brand_check = true;
			}
			
			if(clothes == "none"){
				$(".clothes_empty").css("display", "block");
				clothes_check = false;
			}else{
				$(".clothes_empty").css("display", "none");
				clothes_check = true;
			}
			
			if(sort == "none"){
				$(".sort_empty").css("display", "block");
				sort_check = false;
			}else{
				$(".sort_empty").css("display", "none");
				sort_check = true;
			}
			
			if(savepoint == ""){
				$(".savepoint_empty").css("display", "block");
				savepoint_check = false;
			}else{
				$(".savepoint_empty").css("display", "none");
				savepoint_check = true;
			}
			
			if(product_image == ""){
				$(".product_image_empty").css("display", "block");
				product_image_check = false;
			}else{
				$(".product_image_empty").css("display", "none");
				product_image_check = true;
			}
			
			console.log("옷 체크" + clothes_check);
			console.log("브랜드 체크" + brand_check);
			console.log("분류 체크" + sort_check);
			
			
			alert("카테고리 코드 확인:" + $("#sort").val());
			
			if(product_name_check && price_check && discount_check && stock_check
				&& brand_check && clothes_check && sort_check && savepoint_check && product_image_check) {		
				$("#form").attr("action", "/shop/admin/registerProduct");
				$("#form").submit();
			}
		})
		
	});
	
	
	
	
	function makeCategoryTierList(cateList, cate1Array, cate2Array, cate3Array) {
		
		
		for(let i=0; i<cateList.length; i++){
			// 코드의 길이의 따라 다른 배열에 넣어준다.
			let categoryCode = String(cateList[i].category_code);
			
			let  cateObj = {
					category_name : cateList[i].category_name,
					category_code : cateList[i].category_code,
					category_parent_code : cateList[i].category_parent_code
			};
			
			
			if(categoryCode.length === 1){
				
				cate1Array.push(cateObj);
			}else if(categoryCode.length === 2){
				
				cate2Array.push(cateObj);
			}else if(categoryCode.length === 3){
				
				cate3Array.push(cateObj);
			}
		}
	}
	
	
	
	
	 
	 
	// 카테고리 분류코드가 n인 데이터들을 찾는다
	// 필요한 값 : 찾을 분류코드, 비교할 분류코드리스트, 분류코드 정보를 담아줄 객체, 분류코드를 저장할 배열, 분류코드길이
	/* function makeCategoryTierList(cateCode, cateList,cateArray, codeLength) {
		for(let i = 0; i < cateList.length; i++){z
			if(cateCode === cateList[i].category_code &&  cateCode.toString().length === codeLength){
				// 찾을 분류코드 값과 순회중인 요소의 분류코드 값이 같고 지정한 분류코드의 길이와 같을 경우 객체에 담아준다.
				let  cateObj = {
						category_name : cateList[i].category_name,
						category_code : cateList[i].category_code,
						category_parent_code : cateList[i].category_parent_code
				};
				// 분류코드 객체를 배열에 담아준다.
				
				cateArray.push(cateObj);
			}
		}
		
		/* cate1의 코드와 일치하는 cate2의 코드 추가 해주는 것까지 했어 여기서부터 다시 ㄱㄱ */
		
	 
	
	//테스트
	/* function makeCategoryTierList(cateList, categoryArrays) {
	    for (let i = 0; i < cateList.length; i++) {
	        const catCodeLength = cateList[i].category_code.toString().length;

	        if (catCodeLength <= categoryArrays.length) {
	            categoryArrays[catCodeLength - 1].push({
	                category_name: cateList[i].category_name,
	                category_code: cateList[i].category_code,
	                category_parent_code: cateList[i].category_parent_code
	            });
	        }
	    }
	} */

	
	
	// 이미지 파일을 등록하려하면 실행되는 함수
	$("#product_image").change(function () {
	   // 선택한 파일이 존재하는지 확인한다.
	   if(this.files && this.files[0]){
		   // 1. FileReader 클래스 객체를 생성
	   	  var fileReader = new FileReader();
		   // 3. 리더기가 파일을 다 읽으면 실행되는 함수
		   fileReader.onload = function (data) {
			   // 이미지파일영역의 src 속성에 읽어들인 이미지의 주소를 전달한다.
			$(".product_image img").attr("src", data.target.result);
			/* $(".product_image img").attr("src", data.target.result).width(500); */
			
			
		}//
			// 2. 선택된 파일을 리더기로 읽어들인다.
	   		fileReader.readAsDataURL(this.files[0]);
	   }
	})
	
		
	
	
	
		 /* for(let i = 0; i < cate2Array.length; i++){
					cate2.append("<option value='"+cate2Array[i].category_code+"'>" + cate2Array[i].category_name + "</option>");
				} */
		 
	/* 	 오늘 한 것 로그인  성공화면 회원가입이 마이페이지로 바뀜
		 관리자로 로그인 시 상품등록 페이지 보임
		상품 분류코드에 따라 목록 결과가  달라지게 sql 작성. 
		 상품등록 페이지에서 카테고리 분류 어떻게 할 지 고민중 */
		 

		 
	
		
		
		
		
		
		// for문을 사용해서 매개변수로 주어진 tier와 같은 카테고리를 찾는다.
		/* function makeCategoryTierList(obj, array, categoryList, tier_no) {
			for(let i = 0; i < cateList.length; i++){
				if(cateList[i].tier_no === tier_no)
					// 자바 스크립트에서 === : 타입과 값이 모두 일치
				obj.category_name = cateList[i].category_name;
				obj.category_code = cateList[i].category_code;
				obj.category_parent_code = cateList[i].category_parent_code;
							
				// 매개변수로 주어진 tier와 동일한 카테고리들을 cateObj 변수에 담아준 뒤 배열에 추가해준다.
				array.push(obj);
			}
			
		} */
		
		</script>
</html>