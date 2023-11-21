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
</head>
<body>
    <header>
        
    </header>
   <div class="order-container">
        <h2 class="order_h2">주문 페이지</h2>
        <table class="user_info">
        	<tr>
        		<td>배송 정보</td>
        	</tr>
      		<tr>
        		<td> 배송지    기본배송지  | <button>주소지 변경</button></td>
        	</tr>
        	<tr>
        		<td> 이름/연락처 : ${orderUser.name} | ${orderUser.tel}</td>
      		</tr>
      		<tr>
      			<td> 주소 : ${orderUser.address}</td>
      			
      		</tr>
        	
        </table>
      	<table>
      		<!-- 받아온 상품정보를 페이지에 뿌려준다. -->
      		<c:forEach items="${instantList}" var="instantInfo">
      			<tr>
      				<th>상품 이미지</th>
                    <th>상품명</th>
                    <th>수량</th>
                    <th>적립금</th>
                    <th>할인금액</th>
                    <th>주문금액</th>
      			</tr>
      			<tr class="order_info">
      				<td>
	      			<!-- 이미지 영역 -->
						<img alt="이미지가 없습니다." src="/shop/upload${instantInfo.product_thumbimage}">      					
      				</td>
      				<td>${instantInfo.product_name}</td>
      				<td>${instantInfo.quantity}</td>
      				<td>${instantInfo.totalsavepoint}</td>
      				<!-- 자바코드로 값을 계산하고 해당 값을 출력해야 한다. -->
      				<td><c:out value="${instantInfo.price * instantInfo.quantity - instantInfo.totalprice}"/></td>
      				<td>${instantInfo.totalprice}</td>
      			</tr>
      		</c:forEach>
      	</table>
        
    </div>																					
    
    <!-- 여기에 필요한 스크립트 등을 추가할 수 있습니다. -->
</body>							

    <script>
        function placeOrder() {
            // 주문 정보를 서버로 전송하는 로직을 추가할 수 있습니다.
            alert("주문이 완료되었습니다!");
        }
    </script>
</body>
</html>