<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function update() {
		updateform.submit();
	}
	function bcancel() {
		location.href='boardview?bnumber='+${board.bnumber};
	}
</script>
</head>
<body>
	<h2>boardupdate.jsp</h2>
	<form action="bupdateprocess" method="post" name="updateform" enctype="multipart/form-data">
		글번호 : <input type="hidden" name="bnumber" value="${boardUpdate.bnumber}" readonly>${boardUpdate.bnumber}<br>
		제목 : <input type="text" name="btitle" value="${boardUpdate.btitle}"><br>
		작성자 : <input type="hidden" name="bwriter" value="${boardUpdate.bwriter}" readonly>${boardUpdate.bwriter}<br>
		내용 : <textarea rows="5" name="bcontents">${boardUpdate.bcontents}</textarea> <br>
		파일 : <input type="file" name="bfile"><br>
		<input type="submit" onclick="update()" value="수정">
		<button onclick="bcancel()">수정취소</button>
	</form>
	
	
</body>
</html>