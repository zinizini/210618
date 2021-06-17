<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		table,tr,th,td {
			border: 1px solid black;
			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<a href="mypage"> 마이페이지 </a><br>
	<h2>boardlist.jsp</h2>
	<table>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.bnumber}</td>
				<td>${board.bwriter}</td>
				<td>
					<a href="boardview?bnumber=${board.bnumber}">${board.btitle}</a>
				</td>
				<td>${board.bdate}</td>
				<td>${board.bhits}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="writepage"> 글쓰기 </a><br>
	<a href="paging">페이징목록</a><br>
	<a href="./"> 맨 처음으로 </a>



</body>
</html>