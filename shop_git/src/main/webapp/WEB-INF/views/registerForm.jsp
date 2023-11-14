<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page session="false" %>
<html>
<head>
	<title>회원가입 페이지</title>
	<!-- css 설정 -->
	<link rel="stylesheet" href="<c:url value='/css/register.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
	<form id="registerForm" method="post">
		<span class="title">Register</span>
		<!-- 아이디 입력 -->
		<label for="id">아이디</label>
			<input class="input-field" type="text" name="id" placeholder="영문자 또는 숫자 6~20자로 조합">
			<!-- 아이디 형식 체크 메시지 -->
			<span class="idRegCheck1">올바른 형식의 id입니다.</span>
			<span class="idRegCheck2">올바르지 않은 형식의 id입니다.</span>
			<!-- 공백 여부 검사 메시지 -->
			<span class="idEmpty">아이디를 입력해주세요.</span>
			
		<!-- 아이디 중복체크 -->
			<button type="button" id="idCheck">아이디 중복체크</button>
		<!-- 아이디 중복체크결과 메시지 -->
			<font id="idCheck-font"></font>
			<!-- 공백 여부 검사 메시지 -->
			<span class="idCheckEmpty">아이디중복체크를 해주세요.</span>
			
		<label for="password">비밀번호</label>		
		<!-- 비밀번호 입력 -->
			<input class="input-field" type="password" id="password" name="password" placeholder="8~16자 영문, 숫자, 특수문자를 최소 한가지씩 조합">
			
		<!-- 비밀번호 형식 체크 메시지 -->
		<span class="passwordRegCheck1">올바른 형식의 비밀번호입니다.</span>
		<span class="passwordRegCheck2">올바르지 않은 형식의 비밀번호입니다.</span>
		<!-- 공백 여부 검사 메시지 -->
			<span class="passwordEmpty">비밀번호를 입력해주세요.</span>
		
		
		<label for="password">비밀번호 확인</label>	
		<!-- 비밀번호 확인 -->
		<input class="input-field" type="password" id="passwordSame" name="passwordSame">
			<span class="passwordSame1">비밀번호가 일치합니다.</span>
			<span class="passwordSame2">비밀번호가 일치하지 않습니다.</span>		
			<!-- 공백 여부 검사 메시지 -->
			<span class="passwordSameEmpty">비밀번호확인을 입력해주세요.</span>
				
		<!-- 이름 입력 -->
		<label for="name">이름</label>
			<input class="input-field" type="text" name="name" placeholder="홍길동">
			<!-- 공백 여부 검사 메시지 -->
			<span class="nameEmpty">이름을 입력해주세요.</span>
			
		<!-- 나이 입력 -->
		<label for="age">나이</label>
			<input class="input-field" type="number" name="age" placeholder="나이를 입력" maxlength="2">
			<!-- 공백 여부 검사 메시지 -->
			<span class="ageEmpty">나이를 입력해주세요.</span>
			
		<!-- 성별 입력 -->
			<label for="sex">성별</label>
		<div class="input-radio">
			<input  type="radio" value="0" name="sex" >남
			<input  type="radio" value="1" name="sex" >여
		</div>
			<!-- 공백 여부 검사 메시지 -->
			<span class="sexEmpty">성별을 선택해주세요.</span>
		
		<!-- 주소 입력-->
		<label for="address">주소</label>
			<input class="input-field" type="text" id="address1" name="address" placeholder="우편변호" readonly="readonly">
			<input class="input-field" type="text" id="address2" name="address" placeholder="도로명주소" readonly="readonly">
			<input class="input-field" type="text" id="address3" name="address" placeholder="상세주소" readonly="readonly">
			<!-- 공백 여부 검사 메시지 -->
			<span class="addressEmpty">주소를 입력해주세요.</span>
		<!-- 다음 주소 API-->
			<button type="button" class="addressSearch" id="addressSearch" >주소 찾기</button>	
		
		<!--  생일 입력-->
		<label for="birth">생일</label>
			<input class="input-field" type="date" name="birth">
			<!-- 공백 여부 검사 메시지 -->
			<span class="birthEmpty">생일을 입력해주세요.</span>
			
		<!--  좋아하는 색깔 입력-->
		<label for="color">좋아하는 색상</label>
			<input class="input-field" type="color" name="color">
			<!-- 공백 여부 검사 메시지 -->
			<span class="colorEmpty">색깔을 선택해주세요.</span>
			
		<!--  전화번호 입력-->
		<label for="tel">전화번호</label>
			<input class="input-field" id="tel" type="tel" name="tel" placeholder="010-1111-1111">
			<!-- 공백 여부 검사 메시지 -->
			<span class="telEmpty">전화번호를 입력해주세요.</span>
			
		<!--  전화번호 정규식 검사 메시지-->
			<span class="telReg1">올바른 형식의 전화번호입니다.</span>
			<span class="telReg2">올바르지 않은 형식의 전화번호입니다.</span>
		
		<!--  이메일 입력-->
		<label for="email">이메일</label>
			<input class="input-field" type="text" id="email" name="email" placeholder="example@naver.com">
			<!-- 공백 여부 검사 메시지 -->
			<span class="emailEmpty">이메일을 입력해주세요.</span>
			
		<!-- 이메일 정규식 검사 체크 메시지 -->
			<span class="emailReg1">올바른 형식의 이메일입니다.</span>	
			<span class="emailReg2">이메일 형식이 올바르지 않습니다.</span>
		
		<!-- 이메일 인증번호 입력란, 인증번호 전송 버튼 -->
			<button type="button" class="emailNumBtn">인증번호 전송</button>
			<label for="emailNum">인증번호 입력</label>
			<input class="input-field" type="text" id="emailNum" name="emailNum" disabled="disabled">
			<!-- 공백 여부 검사 메시지 -->
			<span class="emailNumSameEmpty">인증번호를 입력해주세요.</span>
			
		<!-- 인증번호 일치여부 검사 메시지 -->
			<span class="emailNumSame1">인증번호가 일치합니다.</span>
			<span class="emailNumSame2">인증번호가 일치하지 않습니다.</span>
				
		<!-- 회원가입 요청 -->
		<button type="button" class="register-Btn" id="register-Btn">회원가입</button>
	</form>
</body>
	<!-- 다음 Api 활용 -->
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	
	<script type="text/javascript">
	
		/* 이메일 인증번호 담기 위한 변수 */
		let code = "";
		
		// 유효성검사 항목 변수 선언
		// id공백
		var idCheck = false;
		
		// id중복
		var idCkCheck = false;
		
		// 비밀번호 공백
		var passwordCheck = false;
		
		// 비밀번호 일치 공백
		var passwordSameCheck = false;
		
		// 비밀번호 일치 확인
		var passwordSameCkCheck = false;
		
		// 이름 공백
		var nameCheck = false;
		
		// 전화번호 공백 확인
		var telCheck = false;
		
		// 나이 공백 확인		
		var ageCheck = false;
		
		// 생일 공백 확인
		var birthCheck = false;
		
		// 성별 공백 확인
		var sexCheck = false;
		
		// 색깔 공백 확인
		var colorCheck = false;
		
		// 이메일 공백 확인
		var emailCheck = false;
		
		// 이메일 인증번호 일치확인
		var emailNumCheck = false;
		
		// 주소 공백 확인
		var addressCheck = false;
		
		
		// document.ready -> 문서가 준비됐을 때 내부에 있는 함수들을 실행시킨다.
		
		$(document).ready(function () {
			$(".register-Btn").on("click", function () {
				
				// 유효성검사를 위해 입력값들을 가져온다.
				var id = $("input[name=id]").val();
				
				var password = $("input[name=password]").val();
				
				var passwordSame = $("input[name=passwordSame]").val();
				
				var name = $("input[name=name]").val();
				
				var tel = $("input[name=tel]").val();
				
				var age = $("input[name=age]").val();
				
				var birth = $("input[name=birth]").val();
				
				var sex = $("input[name=sex]").val();
				
				var color = $("input[name=color]").val();
				
				var email = $("input[name=email]").val();
				
				var emailNum = $("input[name=emailNum]").val();
				
				var address = $("input[name=address]").val();
				
			// id 공백체크
			if(id == ""){
				$(".idEmpty").css("display","block");
				idCheck = false;
			}else{
				$(".idEmpty").css("display","none");
				idCheck = true;
			}
			
			// 비밀번호 공백체크
			if(password == ""){
				$(".passwordEmpty").css("display","block");
				passwordCheck = false;
			}else{
				$(".passwordEmpty").css("display","none");
				passwordCheck = true;
			}
			
			// 비밀번호 확인 공백체크
			if(passwordSame == ""){
				$(".passwordSameEmpty").css("display","block");
				passwordSameCheck = false;
			}else{
				$(".passwordSameEmpty").css("display","none");
				passwordSameCheck = true;
			}
			
			// 이름 공백체크
			if(name == ""){
				$(".nameEmpty").css("display","block");
				nameCheck = false;
			}else{
				$(".nameEmpty").css("display","none");
				nameCheck = true;
			}
			
			// 전화번호 공백체크
			if(tel == ""){
				$(".telEmpty").css("display","block");
				telCheck = false;
			}else{
				$(".telEmpty").css("display","none");
				telCheck = true;
			}
			
			
			
			// 나이 공백체크
			if(age == ""){
				$(".ageEmpty").css("display","block");
				ageCheck = false;
			}else{
				$(".ageEmpty").css("display","none");
				ageCheck = true;
			}
			
			// 성별 공백체크
			if(sex == ""){
				$(".sexEmpty").css("display","block");
				sexCheck = false;
			}else{
				$(".ageEmpty").css("display","none");
				sexCheck = true;
			}
			
			// 생일 공백체크
			if(birth == ""){
				$(".birthEmpty").css("display","block");
				birthCheck = false;
			}else{
				$(".birthEmpty").css("display","none");
				birthCheck = true;
			}
			
			// 색깔 공백체크
			if(color == ""){
				$(".colorEmpty").css("display","block");
				colorCheck = false;
			}else{
				$(".colorEmpty").css("display","none");
				colorCheck = true;
			}
			
			// 이메일 공백체크
			if(email == ""){
				$(".emailEmpty").css("display","block");
				emailCheck = false;
			}else{
				$(".emailEmpty").css("display","none");
				emailCheck = true;
			}
			
			// 주소 공백체크
			if(address == ""){
				$(".addressEmpty").css("display","block");
				addressCheck = false;
			}else{
				$(".addressEmpty").css("display","none");
				addressCheck = true;
			}
			
			console.log("idCheck: " + idCheck);
			console.log("idCkCheck: " + idCkCheck);
			console.log("passwordCheck: " + passwordCheck);
			console.log("passwordSameCheck: " + passwordSameCheck);
			console.log("passwordSameCkCheck: " + passwordSameCkCheck);
			console.log("nameCheck: " + nameCheck);
			console.log("telCheck: " + telCheck);
			console.log("ageCheck: " + ageCheck);
			console.log("birthCheck: " + birthCheck);
			console.log("sexCheck: " + sexCheck);
			console.log("colorCheck: " + colorCheck);
			console.log("emailCheck: " + emailCheck);
			console.log("emailNumCheck: " + emailNumCheck);
			console.log("addressCheck: " + addressCheck);
			
			
			// 유효성 검사 모두 통과하면 회원가입처리
			if(idCheck && idCkCheck && passwordCheck && passwordSameCheck && passwordSameCkCheck && nameCheck && telCheck && ageCheck &&
			birthCheck && sexCheck && emailCheck && emailNumCheck && addressCheck  ){
				
				$("#registerForm").attr("action", "/shop/register/postRegister");
				$("#registerForm").submit();
			}
			
				
				
			})
			
			
		});
		
		$("#idCheck").on("click", function () {
			// 요청을 보내기 위한 폼을 생성
			let form = $("#form");
			// 아이디 입력창에 있는 값을 가져온다.
			let id = $("input[name=id]").val();
			// 아이디 정규식 검사를 위한 변수
			let idReg1 = $(".idRegCheck1");
			let idReg2 = $(".idRegCheck2");
			/* 아이디 정규식 통과 못할 시 false로 메서드 종료 */
			if(checkId(id)){
				// 정규식 통과 시 통과했단 메세지 출력
				idReg1.css('display','block');
				idReg2.css('display','none');
			}else{
				// 통과 못할 시
				idReg2.css('display','block');
				idReg1.css('display','none');
				return false;
			}
			
			let user = { id : id};
			$.ajax({
				//요청 형식
				type : 'POST',
				//요청 주소
				url : '/shop/register/checkId',
				// 데이터 형식
				headers : {"content-type" : "application/json"},
				// json으로 변환해서 보낸다.
				data : JSON.stringify(user),
				// 성공, 실패에 따라 다르게 처리
				success : function(result){
					if(result==1){
						$("#idCheck-font").html("이미 존재하는 아이디입니다.");
						$("#idCheck-font").attr('color', 'red');
						//통과 못할 시						
						idCkCheck = false;
					}else{
						$("#idCheck-font").html("사용 가능한 아이디입니다.");
						$("#idCheck-font").attr('color', 'green');
						//아이디 중복체크 통과
						idCkCheck = true;
					}
				}
			})
		});
		
		// 비밀번호 형식 체크
		// 입력이 될 때마다 실행이 되는 메서드
		$("#password").on("propertychange change keyup paste input", function(){
	        //입력한 비밀번호 값을 가져온다.
	        let pw = $("input[name=password]").val();
	        //비밀번호 정규식 메세지를 표시해주기 위한 변수
	        let pwReg1 = $(".passwordRegCheck1");
	        let pwReg2 = $(".passwordRegCheck2");	   	        
	        // 비밀번호 정규식 검사
	        if(checkPw(pw)){
	        	// 통과시 통과메시지 출력
	        	pwReg1.css('display','block');
	        	pwReg2.css('display','none');
	        	
	        	// 비밀번호 형식이 올바를 때만 "비밀번호 확인" 이벤트 핸들러 활성화
	            enablePasswordSameValidation();
	        }else{
	            // 비밀번호 형식이 올바르지 않을 때 "비밀번호 확인" 이벤트 핸들러 비활성화
	        	disablePasswordSameValidation();
	        	// 통과 못할 시 false 반환으로 함수 종료	
	        	pwReg2.css('display','block');
	        	pwReg1.css('display','none');
	        	return false;
	        }           
		});  
		
		// 이메일 형식 체크
		$("#email").on("propertychange change keyup paste input", function(){
	        // 입력한 이메일 값을 가져온다.
	        let email = $("input[name=email]").val();
	        // 이메일 정규식 메세지 출력 위한 변수 선언
	        let emailReg1 = $(".emailReg1");
	        let emailReg2 = $(".emailReg2");
	      	
	        //이메일 정규식 통과시 게속해서 진행, 통과 못할 시 false반환
	        if(checkEmail(email)){
	        	emailReg1.css('display','block');
	        	emailReg2.css('display','none');
	        }else{
	        	emailReg2.css('display','block');
	        	emailReg1.css('display','none');
	        }
	                   
		});
		
		// 전화번호 형식 체크
		$("#tel").on("propertychange change keyup paste input", function(){
	        // 입력한 이메일 값을 가져온다.
	        let tel = $("input[name=tel]").val();
	        // 이메일 정규식 메세지 출력 위한 변수 선언
	        let telReg1 = $(".telReg1");
	        let telReg2 = $(".telReg2");
	      	
	        //이메일 정규식 통과시 게속해서 진행, 통과 못할 시 false반환
	        if(checkTel(tel)){
	        	telReg1.css('display','block');
	        	telReg2.css('display','none');
	        }else{
	        	telReg2.css('display','block');
	        	telReg1.css('display','none');
	        }
	                   
		});
		
		// 이메일 인증번호 전송
		$(".emailNumBtn").on("click", function () {
			// 입력한 이메일 주소
			let email = $("input[name=email]").val();
			
			// ajax로 비동기 요청을 보낸다 -> 인증번호만 받아서 입력하면 되기 때문에 굳이 페이지 전환이 필요없음.
			$.ajax({
				// 요청 방식
				type : 'GET',
				// 전송 주소
				url : "mailNum?email=" + email,
				// 성공 시 인증번호를 받아온다.
				success:function(data){
					// 인증번호 입력필드 활성화
					alert(data);
					$("input[name=emailNum]").prop("disabled",false);
					// 선언한 code 변수에 컨트롤러로부터 받아온 data(인증번호)를 담아준다.
					code = data;
				}				
			})
			
		});
		
		// 인증번호 일치여부 검사
		$("#emailNum").on("propertychange change keyup paste input", function() {
			// 입력한 코드
			let inputNum = $("input[name=emailNum]").val();
			/* 인증번호 일치여부 검사 메시지 출력을 위한 선언  */
			let emailNumSame1 = $(".emailNumSame1");
			let emailNumSame2 = $(".emailNumSame2");
			// 인증번호와 입력한 값이 일치할 경우와 불일치할 경우에 대해 처리
			if(inputNum == code){
				emailNumSame1.css("display","block");
				emailNumSame2.css("display","none");
				// 같을 시 
				emailNumCheck = true;
			}else{
				emailNumSame2.css("display","block");
				emailNumSame1.css("display","none");
				// 다를 시
				emailNumCheck = false;
			}
			
		});
		
		
		function enablePasswordSameValidation() {
			$("#passwordSame").on("propertychange change keyup paste input", function(){
				//입력한 비밀번호 값을 가져온다.
		        let pw = $("input[name=password]").val();
				// 비밀번호 확인 입력값 검사
		        let pwSame = $("#passwordSame").val();
		        // 비밀번호 일치여부 검사용 변수
		        let pwSame1 = $(".passwordSame1");
		        let pwSame2 = $(".passwordSame2");
		        
		        // 비밀번호와 동일할 시 일치, 일치하지 않을 시 메세지 출력 후 false 반환
		        if(pw == pwSame){
		        	pwSame1.css('display', 'block');
		        	pwSame2.css('display', 'none');
		        	//같을 시
		        	passwordSameCkCheck = true;
		        }else{
		        	pwSame2.css('display', 'block');
		        	pwSame1.css('display', 'none');
		        	return false;
		        	//다를 시
		        	passwordSameCkCheck = false;
		        }
	        }); 
		}
		
		function disablePasswordSameValidation() {
		    // "비밀번호 확인" 이벤트 핸들러 비활성화
		    $("#passwordSame").off("propertychange change keyup paste input");
		}
		 
		
		// 아이디 정규식 체크
		function checkId(id) {
			var idReg = /^[a-z][a-z0-9]{5,19}$/;
			
			return idReg.test(id);
		}
		
		// 비밀번호 정규식 체크
		function checkPw(pw) {
			var pwReg = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
			
			return pwReg.test(pw); 
		}
		
		// 이메일 정규식 체크
		function checkEmail(email) {
			var emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;	
		
			return emailReg.test(email);
		}
		
		// 전화번호 정규식 체크
		function checkTel(tel) {
			var telReg = /^01[0-9]-\d{4}-\d{4}$/;
			
			return telReg.test(tel);
		}
		
		// 버튼 클릭시 주소 API 실행
		$(".addressSearch").on("click", function () {
			execution_daum_address();
		})
		
		// 다음 주소 API
		function execution_daum_address(){			
			new daum.Postcode({
				oncomplete: function(data) {
					 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                   	addr += extraAddr;
	                
	                } else {
	                   addr += ' ';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                $("#address1").val(data.zonecode);
	                $("#address2").val(addr);
	                // 커서를 상세주소 필드로 이동한다.
	                $("#address3").attr("readonly", false);
	                $("#address3").focus();
				}
			}).open();
		}
	</script>
</html>
