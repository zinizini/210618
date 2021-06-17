<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function idoverlap() {
		var inputId = document.getElementById('mid').value;
		var checkResult = document.getElementById('checkresult');
		console.log(inputId);
		$.ajax({
			type: 'post', 
			url: 'idcheck', 
			data: {'mid': inputId}, 
			dataType: 'text', 
			success: function(data){
				console.log('제대로 돌고 있음.');
				console.log('리턴값'+data);
				if(data=="ok"){
					checkResult.style.color = 'green';
					checkResult.innerHTML = '사용가능한 아이디입니다.';
				} else {
					checkResult.style.color = 'red';
					checkResult.innerHTML = '이미 사용중인 아이디입니다.';
				}
			},
			error: function(){ 
				console.log('제대로 안돌고 있음.');
			}
		});
		
		
	}
</script>
</head>
<body>
	<h2> 회원가입 </h2>
	<form action="memberjoin" method="post" enctype="multipart/form-data">
		아이디 : <input type="text" name="mid" id="mid" onkeyup="idoverlap()">
					<span id="checkresult"></span><br>
		비밀번호 : <input type="text" name="mpassword"><br>
		이름 : <input type="text" name="mname"><br>
		전화번호 : <input type="text" name="mphone"><br>
		이메일 : <input type="text" name="memail"><br>
		프로필 사진 : <input type="file" name="mfile"><br>
		<input type="submit" value="회원가입">
	</form>

</body>
</html>