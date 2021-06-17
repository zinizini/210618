<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function logout() {
		location.href = "logout";
	}

	function update() {
		location.href = "memberupdate";
	}
</script>
</head>
<body>
	<h2>마이페이지</h2>
	
	<h2>${sessionScope.loginMember} 님 환영합니다^^</h2>
	<button onclick="update()"> 회원정보수정 </button>
	<button onclick="logout()"> 로그아웃 </button> 


	<div style="background-color: #FF1493;">
		<c:if test="${sessionScope.loginMember eq 'memberadmin'}">
			<h2>관리자 계정으로 접속하셨네요!</h2>
			<a href="memberlist"> 회원목록조회 </a><br>
			<a href="memberview?mid=${member.mid}"> 조회 </a><br>
		</c:if>
	</div>
	
</body>
</html>