<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function memberviewAjax(mid) {
		console.log(mid);
		$.ajax({
			type: 'post',
			url: 'memberviewajax',
			data: {'mid': mid},
			dataType: 'json',
			success: function(result){
				console.log(result);
				console.log(result.mid);
				console.log(result.mpassword);
				console.log(result.mname);
				console.log(result.mphone);
				console.log(result.memail);
				
				var output = "<table>";
				output += "<tr><th>ID</th> <th>PASSWORD</th> <th>NAME</th>";
				output += "<th>EMAIL</th></tr>";
				output += "<tr>";
				output += "<td>"+result.mid+"</td>";
				output += "<td>"+result.mpassword+"</td>";
				output += "<td>"+result.mname+"</td>";
				output += "<td>"+result.mphone+"</td>";
				output += "<td>"+result.memail+"</td>";
				output += "</tr>";
				output += "</table>";
				
				document.getElementById('memberviewdiv').innerHTML = output; 
				
			},
			error: function(){
				console.log('문제발생');
			}
			
			
			
		});
	}
</script>
<style>
table, tr, td, th {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h2>memberlist.jsp</h2>

	로그인아이디: ${sessionScope.loginMember}<br>

	<table>
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>상세조회</th>
			<th>삭제</th>
			<th>상세조회(ajax)</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.mid}</td>
				<td>${member.mpassword}</td>
				<td>${member.mname}</td>
				<td>${member.mphone}</td>
				<td>${member.memail}</td>
				<td><a href="memberview?mid=${member1.mid}"> 조회 </a>
				
				<td><button onclick="deletefn('${member1.mid}')"> 회원 삭제 </button>
				<td><button onclick="memberviewAjax('${member1.mid}')"> 회원조회(ajax) </button>
			</tr>
		</c:forEach>
	</table>
	
	<div id="memberviewdiv"></div>

	<script>
		function deletefn(id) {
			console.log('삭제할아이디' + id);
			location.href = "memberdelete?mid=" + id;
		}
	</script>

</body>
</html>
