<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의 게시판</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 60%;
            margin: auto;
            padding: 20px;
            text-align: center;
        }

        .post {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
        }

        .post h2 {
            font-size: 18px;
            margin-bottom: 10px;
        }

        .post p {
            font-size: 14px;
            margin-bottom: 5px; /* 여백 조정 */
        }

        .post .details {
            font-size: 12px;
            color: #555;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .page-link,
        .pagination a.prev-next {
            padding: 8px 12px;
            margin: 0 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            color: #333;
        }

        .page-link.active,
        .pagination a.prev-next.active {
            background-color: #4CAF50;
            color: white;
        }

        .write-Btn {
            background-color: #4CAF50;
            color: white;
            border-radius: 4px;
            margin-top: 10px; /* 여백 조정 */
            float: right; /* 오른쪽 정렬 추가 */           
        }

        .title_h1 {
            text-align: center;
            margin-bottom: 20px; /* 여백 조정 */
        }

        .question_div table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px; /* 여백 조정 */
        }

        .question_div table, .question_div td {
            border: 1px solid #ccc;
        }

        .question_div td {
            padding: 8px;
        }
        /* 답글 제목 설정 */
        .title_style{
        	color: #1e90ff; /* 적당히 강조되는 파란색 */
        	font-weight: bold;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/index.jsp" />
<div class="container">
	<h1 class="title_h1">문의 게시판</h1>                                                                              	
    <!-- 글 목록 시작 -->
    <div class="post">
        <div class="question_div">
        	<table>
        		<tr>        			
        			<td>글번호</td>
        			<td>제목</td>
        			<td>문의유형</td>
					<td>작성자</td>
					<td>작성일</td>
        		</tr>
        	<c:forEach var="qna" items="${questionList}">        		
        		<tr>
	        		<td>${qna.bno }</td>
        				<!-- 답글의 경우 제목의 글씨체를 강조 -->
        			<c:choose>
		        		<c:when test="${qna.bno != qna.pbno }">
	        				<td><a class="title_style" href="<c:url value="/question/getInfo?bno=${qna.bno}"/>">${qna.title}</a></td>
		        		</c:when>
        				<c:otherwise>
        				  <td><a href="<c:url value="/question/getInfo?bno=${qna.bno}"/>">${qna.title}</a></td>       					
        				</c:otherwise>
        			</c:choose>
        			<td>${qna.inquirytype }</td>
        			<td>${qna.writer}</td>
        			<td>${qna.regdate}</td>
        		</tr>
        	</c:forEach>
        		<tr>
	        		<td colspan="5">
						<button class="write-Btn" type="button" onclick="location.href='getWrite'">게시글 작성</button>
    	    		</td>
        		</tr>
        	</table>
        </div>
    </div>
    <!-- 글 작성 버튼 -->
   <!--  <div class="post">
        <h2>글 제목 2</h2>
        <p>글 내용이 여기에 들어갑니다. 글 내용이 많으면 일정 길이 이상은 생략되어 표시됩니다.</p>
        <p class="details">작성자: 작성자명 | 작성일: 2023-01-02</p>
    </div> -->
    <!-- 추가 글 목록은 이어서 작성 -->

    <!-- 페이지네이션 -->
    <div class="pagination">
        <!-- 필요한 만큼 페이지 링크 추가 -->
        <c:if test="${ph.prevPage}">
        	<a class="prev-next" href="<c:url value="/question/getList?page=${ph.startPage-1}&pageSize=${ph.pageSize}"/>">이전</a>
        </c:if>
        <c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
        	<!-- 현재 선택된 페이지가 ph의 페이지와 일치할 경우 active 효과 on -->
        	<c:choose>
			   <c:when test="${ph.page == i}">
			       <a class="page-link active" href="">${i}</a>
			   </c:when>
			   <c:otherwise>
			       <a class="page-link" href="<c:url value="/question/getList?page=${i}&pageSize=${ph.pageSize}"/>">${i}</a>
			   </c:otherwise>
			</c:choose>
        </c:forEach>
        <c:if test="${ph.nextPage}">
        	<a class="prev-next" href="<c:url value="/question/getList?page=${ph.endPage+1}&pageSize=${ph.pageSize}"/>">다음</a>       
        </c:if> 
    </div>
</div>

</body>
</html>