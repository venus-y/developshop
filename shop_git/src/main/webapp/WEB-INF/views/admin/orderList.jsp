<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 정보</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        .order-container {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            max-width: 400px; /* 페이지에 맞게 최대 너비 설정 */
            margin-left: auto;
            margin-right: auto;
        }

        .order-details {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .order-details li {
            margin-bottom: 10px;
            font-size: 14px;
        }
        
        /* 구분선 스타일 추가 */
        .order-separator {
            border-top: 1px solid black; /* 구분선의 색상 명시 */
            margin-top: 10px;
            padding-top: 5px;
        }
        
        .order-container:not(:last-child) {
            border-bottom: 1px solid black;
        }
    </style>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>   
</head>
<body>

<div class="order-container">
    <h2>주문 정보</h2>
    <c:forEach var="orderInfo" items="${oiList}" varStatus="status">
        <ul class="order-details">
            <li class="order_id"><strong>주문 ID:</strong> ${orderInfo.order_id}</li>
            <li class="user_id"><strong>사용자 ID:</strong> ${orderInfo.user_id}</li>
            <li class="order_date"><strong>주문 일자:</strong> ${orderInfo.order_date}</li>
            <li class="total_amount"><strong>총 수량:</strong> ${orderInfo.total_amount}</li>
            <li class="delivery_cost"><strong>배송비 :</strong> ${orderInfo.delivery_cost}</li>
            <li class="status"><strong>배송 상태:</strong> ${orderInfo.status}</li>
        </ul>
        
        <button class="ship-button" value="${orderInfo.order_id}">상품 발송</button>
        
        
          <%-- 구분선 추가 (마지막 주문 정보 컨테이너 제외) --%>
        <c:if test="${!status.last}">
            <div class="order-separator"></div>
        </c:if>
    </c:forEach>
</div>
</body>
<script>
	// 상품 발송 버튼을 누르면 배송정보가 생성되고 주문정보의 배송상태가 '배송준비' -> '배송중으로 변경'
	$(".ship-button").on("click", function () {
		// 변경해줄 주문정보의 주문번호
		let order_id = $(this).val(); 
		
		//발송처리할 것인지 물어본 후 이후의 처리를 진행한다.
		if(!confirm("상품을 발송하시겠습니까?"))return;
		
		// 비동기로 처리한다.
		$.ajax({
			type: "POST",
			url: "/shop/admin/deliveryInfo",
			contentType : "application/json; charset=utf-8",
			data: JSON.stringify({
				order_id : order_id
			}),
			success: function (result) {
				alert("result:"+result);
				alert("배송정보 등록을 완료했습니다.");	
				location.reload(true);
			}, error: function (error) {
				alert("배송정보 등록 중 오류가 발생했습니다.");
			}
		})
		
	})
	
	
</script>
</html>
