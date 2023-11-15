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
	<link rel="stylesheet" href="<c:url value='/css/updateProduct.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>	
	<jsp:include page="/WEB-INF/views/index.jsp" />
	<div class="product_info">
		<form action="<c:url value="/admin/updateProduct"/>" method="post" enctype="multipart/form-data">
	    <table>		
		<tr>	
			
			<td colspan="2">
				<div class="product_image">
				<!-- 이미지 수정 안할 경우 기존 이미지 정보를 넘겨줘야 한다. -->
			<input class="hidden-field" type="hidden" name="existing_thumbimage" value="${productInfo.product_thumbimage}">
    		<input class="hidden-field" type="hidden" name="existing_image" value="${productInfo.product_image}">
			<!--  -->
			<input class="hidden-field" type="hidden" name="product_id" value="${productInfo.product_id}">
			<input type="file" name="file" id="product_image">
				<img src="/shop/upload${productInfo.product_thumbimage}">
				</div>
			</td>
		</tr>
		<tr>
			<td>상품명</td>
			<td><input type="text" name="product_name" value="${productInfo.product_name}"></td>					
		</tr>
		
		<tr>
			<td>가격</td>
			<td>
				<!-- 정가 -->
				<input type="number" name="price" value="${productInfo.price}">원
			</td>    
		</tr>
		
		<tr>
			<td>할인율</td>
			<td><input type="number" name="discount" value="${productInfo.discount}">  %</td>
		</tr>
		
		<tr>
			<td>적립포인트</td>
			<td><input type="number" name="savepoint" value="${productInfo.savepoint}">포인트</td>
		</tr>
		<tr>
			<td colspan="2">			
				<button type="button" onclick="location.href='/shop/product/productList?page=${param.page}&pageSize=${param.pageSize}'">상품 목록</button>
				<button type="submit">수정 적용</button>
			</td>
		</tr>
		</table>	
		</form>
	</div>
	
		
</body>
	<script type="text/javascript">
		
	//이미지 파일 변경 함수	
	$("#product_image").change(function () {
		//파일 존재하는지 확인
		if(this.files && this.files[0]){
			// FileReader 클래스 객체 만든다.
			var fileReader = new FileReader();
			// 리더기가 파일을 다 읽어오면 함수가 실행된다.
			fileReader.onload = function (data) {
				//이미지 영역에  데이터를 전달한다.
			  $(".product_image img").attr("src", data.target.result);
			}
			fileReader.readAsDataURL(this.files[0]);
		}
	})	
	</script>

</html>