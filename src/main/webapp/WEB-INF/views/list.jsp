<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/list.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
</style>
</head>

<body>
	<div class="top-div">
		<div class="top-left-div">
			<span>${loginId}</span>
		</div>
		<div class="top-right-div">
			<button style="margin: 5px" onclick="location.href='logout'">로그아웃</button>
		</div>
	</div>
	
	<div class="subject-div">
		<h3>게시판</h3>
	</div>

	<div class="button-div">
		<div class="button-left-div">
			<select name="pagePerNum" style="height:30px">
				<!-- <option val="5">5</option> -->
				<option val="10">10</option>
				<option val="15">15</option>
				<option val="20">20</option>
			</select>
		</div>
		<div class="button-right-div">
			<button style="margin: 5px" onclick="location.href='write.go'">글 작성</button>
			<button onclick="delBbsPhoto()" style="margin: 5px">삭제</button>
		</div>
	</div>

	<div class="table-div">
		<table>
			<thead>
				<tr>
					<th>img</th>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>등록일</th>
					<th><input type="checkbox" name="all" /></th>
				</tr>
			</thead>
			<tbody id="bbsList">
				<!-- 
				<c:if test="${list.size() < 1}">
					<tr>
						<td colspan="7">작성된 게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>
							<c:if test="${item.img_cnt == 0}">
								<img name="img" src="resources/img/no_image.png" />
							</c:if> 
							<c:if test="${item.img_cnt != 0}">
								<img name="img" src="resources/img/image.png" />
							</c:if>
						</td>
						<td>${item.idx}</td>
						<td>${item.subject}</td>
						<td>${item.user_name}</td>
						<td>${item.bHit}</td>
						<td>${item.reg_date}</td>
						<td><input type="checkbox" name="del" /></td>
					</tr>
				</c:forEach>
				-->
			</tbody>
			<tbody id="page">				
			</tbody>
		</table>
	</div>
</body>

<script>
	var msg = '${msg}';
	if (msg != '') {
		alert(msg);
	}
	
	listCall();
	
	// 리스트 출력
	function listCall() {
		$.ajax({
			type: 'get',
			url: './list.ajax',
			//url: './list.ajax.external',
			data: {},
			dataType: 'JSON',
			success: function(data) {
				drawList(data);
			}, error: function(error) {
				console.log(error);
			}
		});
	}
	// 리스트 그리기
	function drawList(data){
		var content = '';
		
		if (data.list == null) {
			content += '<tr><td colspan="7">작성된 게시글이 없습니다.</td></tr>';
		}
		
		for(item of data.list) {
			content += '<tr>';
			content += '<td>'
			var img_path = item.img_cnt == 0? 'no_image.png':'image.png';
			content += '<img name="img" src="resources/img/'+img_path+'"/>';
			content += '</td>';
			content += '<td>'+item.idx+'</td>';
			content += '<td><a href="detail?idx='+item.idx+'">'+item.subject+'</a></td>';
			content += '<td>'+item.user_name+'</td>';
			content += '<td>'+item.bHit+'</td>';
			content += '<td>'+item.reg_date+'</td>';
			content += '<td><input type="checkbox" name="del" value="'+item.idx+'"/></td>';
			content += '</tr>';
		}
		
		$('#bbsList').html(content);
	}
	
	// 삭제 전체 선택
	$('input[name="all"]').on('click', function() {
		var $chk = $('input[name="del"]');
		if ($(this).is(':checked')) {	
			$chk.attr('checked',true);	
		} else {
			$chk.attr('checked',false);	
		}
	});
	// 삭제
	function delBbsPhoto() {
		var delArr = [];
		
		$('input[name="del"]').each(function(index, item){
			if ($(this).is(":checked")) {
				var val = $(this).val();
				delArr.push(val);
			}
		});
		
		$.ajax({
			type: 'get',
			url: './del.ajax',
			data: {'delArr': delArr},
			dataType: 'JSON',
			success: function(data) {
				alert(data.cnt+'개의 글이 삭제 되었습니다.');
				$('#bbsList').empty();
				listCall();
			}, error: function(error) {
				
			}
		});
	}
	

	// 페이지 만들기
	// 총 페이지 수 가져오기
	
/* 	<a href="">First</a>
	<a href="">Prev</a>
	<a href="">first</a>
	<a href="">Next</a>
	<a href="">Last</a> */
	
</script>
</html>