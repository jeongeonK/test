<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/basic.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
</style>
</head>

<body>
	<div class="top-div"></div>
	
	<div class="subject-div">
		<h3>로그인 페이지</h3>
	</div>
	
	<div class="table-div">
		<form action="login.do" method="post">
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<th>PW</th>
					<td><input type="text" name="pw" /></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="button" onclick="login()" value="로그인">
						<input type="button" onclick="join()" value="회원가입">
					</th>
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

	function login() {
		$('form').submit();
	}
	
	function join() {
		location.href="join.go";
	}
	
	
</script>
</html>