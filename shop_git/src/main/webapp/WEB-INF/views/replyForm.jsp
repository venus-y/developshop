<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>답글 작성</title>

    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        h1 {
            margin-bottom: 20px; /* 폼과 헤더 사이에 간격 추가 */
        }

        form {
            width: 600px;
            height: 700px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input,
        textarea,
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
		
		 textarea {
            height: 350px; /* 원하는 높이로 조절 */
            resize: vertical; /* 사용자가 수직으로만 조절할 수 있도록 설정 */
        }
		
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/index.jsp" />
<h1>답글 작성</h1>

<form id="inquiryForm" action='<c:url value="/question/postReply"/>' method="post" >
    <label for="title">제목:</label>
    <input type="text" id="title" name="title" required value="RE: ">
	
	<!-- 받아왔던 원글번호를 pbno로 넘겨준다.-->
	<input type="hidden" name="pbno" value="${qanda.pbno}">
	
    <label for="content">내용:</label>
    <textarea id="content"  name="content" required></textarea>

    <label for="inquirytype">문의 유형:</label>
    <select id="inquirytype" name="inquirytype" required >
        <option value="${qanda.inquirytype}">${qanda.inquirytype}</option>
    </select>

    <label for="writer">작성자:</label>
    <input type="text" id="writer" name="writer" value="${sessionScope.user.id}" required readonly="readonly">

    <button type="submit">답글 등록</button>
</form>

<script>
    function submitInquiry() {
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const inquiryType = document.getElementById('inquiryType').value;
        const writer = document.getElementById('writer').value;

        alert(`제목: ${title}\n내용: ${content}\n문의 유형: ${inquiryType}\n작성자: ${writer}`);
        document.getElementById('inquiryForm').reset();
    }
</script>

</body>
</html>