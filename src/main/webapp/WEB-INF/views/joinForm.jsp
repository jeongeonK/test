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
		<h3>글 작성</h3>
	</div>
	
	<div class="table-div">
		<form action="join.do" method="post">
			<table>
				<tr>
					<th>ID</th>
					<td>
						<input type="text" name="id"/>
						<button type="button">중복체크</button>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="text" id="pwChk"/><br/>
						<span id=pwChkMsg></span>
					</td>
				</tr>
				<tr>
					<th>관리자</th>
					<td><input type="checkbox" name="admin"/></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="text" name="age" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value="남자"/>남자
						<input type="radio" name="gender" value="여자"/>여자
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<th colspan="2">
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
	
	var $id = $('input[name="id"]');
	var $pw = $('input[name="pw"]');
	// var $admin = $('input[name="admin"]');
	var $name = $('input[name="name"]');
	var $age = $('input[name="age"]');
	// var $gender = $('input[name="gender"]');
	var $email = $('input[name="email"]');
	
	var idDupliChk = false;
	
	// 아이디 중복 체크
	$('button[type="button"]').on('click', function() {
		$.ajax({
			type: 'post',
			url: './dupliChk',
			data: {'id': $id.val()},
			dataType: 'JSON',
			success: function(data){
				if (data.use == 0) {
					alert("["+$id.val()+"] 이미 존재하는 아이디 입니다.");
					$id.val('');
				} else {
					alert("["+$id.val()+"] 사용 가능한 아이디 입니다.");
					idDupliChk = true;
				}
			},
			error: function(error){
				
			}
		});
	});
	
	// 비밀번호 확인
	$('#pwChk').on('keyup', function() {
		if ($(this).val() == $('input[name=pw]').val()) {
			$('#pwChkMsg').html('비밀번호가 일치 합니다.');
			$('#pwChkMsg').css({ 'color':'green'});
		} else {
			$('#pwChkMsg').html('비밀번호가 일치하지 않습니다.');
			$('#pwChkMsg').css({ 'color':'red'});
		}
	});
	
	function join() {
		
		var $gender = $('input[name="gender"]:checked');
		
		if (idDupliChk == false) {
			alert('아이디 중복 체크를 해 주세요!');
			$id.focus();
		} else if ($id.val() == '') {
			alert('아이디를 입력 해 주세요!');
			$id.focus();
		} else if ($pw.val() == '') {
			alert('비밀번호를 입력 해 주세요!');
			$pw.focus();
		} else if ($name.val() == '') {
			alert('아이디를 입력 해 주세요!');
			$name.focus();
		} else if ($age.val() == '') {
			alert('나이 입력 해 주세요!');
			$age.focus();
		} else if($gender.val() == null) {
			alert("성별을 체크 해 주세요!");
		} else if($email.val()=='') {
			alert("이메일을 입력 해 주세요!");
			$email.focus();
		} else {
			var regExp = new RegExp('[a-zA-Zㄱ-ㅎ가-핳]');
			var match = regExp.test($age.val());
			if (match == false) {
				$('form').submit();
			} else {
				alert('나이는 숫자만 입력 해 주세요!');
				$age.val('');
				$age.focus();
			}
		}
	}
	
	
</script>
</html>