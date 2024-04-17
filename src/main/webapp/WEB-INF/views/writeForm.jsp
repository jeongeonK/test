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
			<button style="margin: 5px" onclick="location.href='list'">뒤로가기</button>
		</div>
		<div class="button-right-div">
		</div>
	</div>

	<div class="table-div">
		<form action="write.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" name="subject" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="hidden" name="user_name" value="${loginId}"/>${loginId}</td>
				</tr>
				<tr>
					<th>파일</th>
					<td><input type="file" name="photos" multiple="multiple"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><input type="text" name="content" style="width:100%"/></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="업로드" style="margin: 5px"/></th>
				</tr>
			</table>
		</form>
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