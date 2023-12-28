<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
  <title>Brand List</title>
   <meta charset="UTF-8">
  	  <jsp:include page="/WEB-INF/views/link-rel.jsp" />
  <link rel="stylesheet" href="/shop/css/category/brand.css">
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/header.jsp" />
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
		<h1 class="alphabetLink"><a href="#" class="alphaLink" data-alphabet="${alphabet}">${alphabet}</a></h1>
  	</c:forEach>
    </div>

  <div class="kd-brand-list" id="brandList">
    	<!-- 알파벳 이니셜을 저장하는 변수 --> 
  			<table>
  			<c:set var="initialCheck" value= ""/>
  			<c:forEach var="brandInfo" items="${brandList}">
  			<!-- 이니셜체크와 현재 요소를 비교해서 이니셜이 다르고 이니셜이 공백이 아닐 경우 열려있던 tr태그를 닫는다.  -->
  			<c:if test="${!empty initialCheck}">
  				 <c:if test="${initialCheck != brandInfo.category_name.substring(0,1).toUpperCase()}">
  				 	</tr>
  				 </c:if>  				
  			</c:if>
  			<!-- 여기서 tr 태그를 닫는 의미는 현재와 이전 이니셜이 다르므로 이전목록과 현재목록을 구분하는 것 -->
  			<!-- 이니셜이 겹치치 않을 경우에만 tr 태그를 연다 -->
  			<c:if test="${initialCheck != brandInfo.category_name.substring(0,1).toUpperCase()}">
  				<tr data-alphabet = "${brandInfo.category_name.substring(0,1).toUpperCase()}">
  			</c:if><!-- 이니셜이 겹치치 않을 경우에만 h3 태그 추가한다 --> 			
  			<c:if test="${initialCheck != brandInfo.category_name.substring(0,1).toUpperCase()}">
  				<td><h2 id="${brandInfo.category_name.substring(0,1).toUpperCase()}">${brandInfo.category_name.substring(0,1).toUpperCase()}</h2></td>
  				<!-- h2 태그를 출력한 뒤에 이니셜 체크에 현재 카테고리 이름의 첫 이니셜을 저장 -->
  				<c:set var="initialCheck" value="${brandInfo.category_name.substring(0,1).toUpperCase()}"/>
  				<!-- 만약 다음에 오는 요소가 이니셜 체크와 이니셜이 같으면 tr 태그 및 h2(알파벳)을 출력하지 않음, 다를 경우에만 출력 -->
  			</c:if>
  			<td><a href="<c:url value='/product/brandPage?category_code=${brandInfo.category_code}'/>">${brandInfo.category_name}</a></td> 			
  			</c:forEach>
  			<!-- 반복문이 종료되면 tr태그를 닫지 않고 끝내게 되기 때문에 마지막으로 한번 더 닫아준다.  -->	
  					</tr>
  			</table>   	  
  </div>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
  			
  				
</body>
  <jsp:include page="/WEB-INF/views/script.jsp" />
	<script>
		$(document).ready(function () {

						
		});
		
		// 선택된 알파벳에 해당하는 브랜드들을 화면에 표시하도록 이벤트 등록
		$(".alphaLink").on("click", function () {
			// 선택된 알파벳의 정보를 받아온다.
			let selectedAlphabet = $(this).data("alphabet");	
			
			// 선택된 알파벳 이니셜만 화면에 보이게 해준다.
	       	showBrandsByAlphabet(selectedAlphabet);
							
		});
		
		function showBrandsByAlphabet(alphabet) {
			 /* 일단 브랜드들을 전부 숨긴다. */
				$(".kd-brand-list table tr").css("display", "none");
/* 				 선택한 이니셜과 일치한 브랜드 그룹을 화면에 표시
*/				$(".kd-brand-list table tr[data-alphabet='" + alphabet + "']").css("display","block");
			 
		}
		
	</script>
</html>