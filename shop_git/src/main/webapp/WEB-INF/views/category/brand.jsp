<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Brand List</title>
  <link rel="stylesheet" href="/shop/css/category/brand.css">
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
  <header>
    <h1>Brand List</h1>
  </header>
    <div id="alphabet-list">
   	 <!-- 알파벳 영역 -->
    <%!
      String[] alphabets = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
     %>
  	<c:forEach var="alphabet" items= "<%= alphabets %>">
  		<!-- 버튼 누를 경우 해당 알파벳으로 시작하는 브랜드들만 표시 -->
		<a href="#" class="alphaLink" data-alphabet="${alphabet}">${alphabet}</a>
  	</c:forEach>
    </div>

  <div class="kd-brand-list" id="brandList">
  
  	<!--  -->
  	
  	<!-- 브랜드 영역 -->
  
    <c:set var="initialCheck" value=""/>	
    <c:forEach var="brandInfo" items="${brandList}">
    <!-- 이니셜이 한번 출력됐는지 확인-->
        	<c:if test="${fn:trim(initialCheck) != fn:trim(brandInfo.category_name.substring(0,1).toUpperCase())}">
<%--     			<ul data-alphabet="${brandInfo.category_name.substring(0,1).toUpperCase()}">
 --%>     		</c:if>
    		<li>
    		<!-- 한번 출력된 적이 있는지 검사 후 출력됐다면 더 이상 이니셜을 출력하지 않는다. -->
    		<c:if test="${fn:trim(initialCheck) != fn:trim(brandInfo.category_name.substring(0,1).toUpperCase())}">
    			<h3>${brandInfo.category_name.substring(0,1).toUpperCase()}</h3>
	    		<c:set var="initialCheck" value="${brandInfo.category_name.substring(0,1).toUpperCase()}" />
    		</c:if>
    			<a href="#">
    				<strong>${brandInfo.category_name}</strong>
    				<span>${brandInfo.category_name }</span>
    			</a>
    		</li>
    		<c:if test="${!empty initialCheck}">
	    		
    		</c:if>
    	
    </c:forEach>
  </div>
</body>
	<script>
		$(document).ready(function () {
			// 선택된 알파벳에 해당하는 브랜드들을 화면에 표시하도록 이벤트 등록
			$(".alphaLink").on("click", function () {
				// 선택된 알파벳의 정보를 받아온다.
				let selectedAlphabet = $(this).data("alphabet");	
				// 화면 갱신
				showBrandsByAlphabet(selectedAlphabet);
			});
			
			// 페이지가 처음 로드됐을 경우 모든 브랜드를 표시
			/* showBrandsByAlphabet(''); */
			
			// 선택한 이니셜로 시작하는 브랜드들을 표시하는 함수
			function showBrandsByAlphabet(alphabet) {
				// 일단 브랜드들을 전부 숨긴다.
				$(".kd-brand-list ul").css("display", "none");
				// 선택한 이니셜과 일치한 브랜드 그룹을 화면에 표시
				$(".kd-brand-list ul[data-alphabet='" + alphabet + "']").css("display","block");
			}
		});
	</script>
</html>