<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <%@ page session="false" %> --%>
<html>
<head>
	<title>메인 페이지</title>
				<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<jsp:include page="/WEB-INF/views/link-rel.jsp" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    		
	<style type="text/css">
		.jumbotron{
					background-image: url("imgUpload/main4.jpg");
					background-size: cover; /* 이미지를 화면에 맞게 조절합니다 */
					text-align: center; /* 텍스트를 가운데 정렬합니다 */
					background-size: 120% 120%; /* 이미지를 상하좌우로 20%씩 늘립니다 
					background-position: center center; /* 이미지를 가운데로 정렬합니다 */ 
					text-shadow: black 0.2em 0.2em 0.2em;
					color: white;
					padding: 100px 0; /* 상하 여백을 조절합니다 */
					height: 100%; /* 화면 크기에 따라 높이를 조절 */
       			    width: 100%; /* 너비를 100%로 설정  
				}
		.jumbotron h2 {
		font-size: 3em; /* 헤더 텍스트 크기를 조절합니다 */
		margin-bottom: 0px; /* 헤더와 문단 사이의 간격을 조절합니다 */
	}

	.jumbotron p {
		font-size: 1.5em; /* 문단 텍스트 크기를 조절합니다 */
		margin-bottom: 10px; /* 간격을 줄임 */
	}
	</style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/header.jsp" />
  	  
<script>
	/*  주문 처리 후 나타나는 메세지*/
	let msg = "${msg}";
	
	if(msg == "ORDER_SUCCESS"){
		alert("주문이 성공적으로 처리됐습니다!");
	}else if(msg == "ORDER_FAILED"){
		alert("주문 처리 중 오류가 발생했습니다!");
	}
</script>
<!-- for commit22   -->
<div class="container">
	<!-- 점보트론 -->
	<div class="jumbotron">
		<!-- <h2 class="text-center" style="height: 250px;">FashionFlicks
			<br>
			FashionFlicks는 다양한 브랜드들의 상품을 한 곳에서 볼 수 있는 온라인 편집샵입니다.
		</h2> -->
		<!-- <p class="text-center" style="height:200px;"></p> -->
	</div>
</div>	
    <jsp:include page="/WEB-INF/views/footer.jsp" />					
</body>
  <jsp:include page="/WEB-INF/views/script.jsp" />

</html>
