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
	<h3>commit한 내용을 취소하고 싶다면</h3>
	<p>1. 브랜치 초기화</p>
	<p>2.reverse commit</p>
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