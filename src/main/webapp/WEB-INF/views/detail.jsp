<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/detail.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
</style>
</head>

<body>
	<div class="top-div"></div>

	<div class="subject-div">
		<h3>${item.subject}</h3>
	</div>

	<div class="button-div">
		<div class="button-left-div">
			<button style="margin: 5px" onclick="backList()">뒤로가기</button>
		</div>
		<div class="button-right-div">
			<button style="margin: 5px">수정</button>
			<button style="margin: 5px">삭제</button>
		</div>
	</div>

	<div class="table-div">
		<table>
			<tr>
				<th>글번호</th>
				<td>${item.idx}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${item.user_name}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${item.bHit}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${item.reg_date}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<c:forEach items="${photos}" var="photo">
						<img src="/photo/${photo.new_filename}" name="bbsphoto"/>
						<br />
					</c:forEach> 
				<br/>${item.content}</td>
			</tr>
		</table>
	</div>
</body>

<script>
	var msg = '${msg}';
	if (msg != '') {
		alert(msg);
	}
	
	function backList() {
		console.log("클릭");
		location.href="list";
	}
</script>
</html>