<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/list.css" type="text/css">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
<style>
</style>
</head>

<body>
	<div class="top-div"></div>

	<div class="subject-div">
		<h3>게시판</h3>
	</div>

	<div class="button-div">
		<div class="button-left-div">
			<select id="pagePerNum" style="height: 30px">
				<option val="5">5</option>
				<option val="10">10</option>
				<option val="15">15</option>
				<option val="20">20</option>
			</select>
		</div>
		<div class="button-right-div">
			<button style="margin: 5px">삭제</button>
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
			<tr>
				<td colspan="7">
					<div class="container">
						<nav aria-label="Page navigation" style="text-align: center">
							<ul class="pagination" id="pagination"></ul>
						</nav>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>

<script>
	var msg = '${msg}';
	if (msg != '') {
		alert(msg);
	}
	
	var firstPage = 1;
	var showPage = 0;
	
	listCall(firstPage);
	
	$('#pagePerNum').on('change', function() {
		$('#pagination').twbsPagination('destroy');
		listCall(showPage);
	});
	
	// 리스트 출력
	function listCall(page) {
		$.ajax({
			type: 'get',
			url: './list.ajax',
			data: {
				'currPage': page,
				'pagePerNum': $('#pagePerNum').val()
			},
			dataType: 'JSON',
			success: function(data) {
				drawList(data);
				
				showPage = data.currPage >data.totalPages ? data.totalPages : data.currPage;

				$('#pagination').twbsPagination({
		        	  startPage: showPage,		// 시작페이지
		        	  totalPages:data.totalPages, 	// 총 페이지 갯수
		        	  visiblePages:5,	// 보여줄 페이지 수 [1][2][3][4][5]
		        	  onPageClick:function(evt, pg){ // 페이지 클릭시 실행 함수
		        		  console.log(evt);//이벤트 객체
		        		  console.log(pg);//클릭한 페이지 번호
		        		  showPage = pg;
		        		  listCall(pg);
		        	  }
		          }); 
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
			content += '<td>'+item.subject+'</td>';
			content += '<td>'+item.user_name+'</td>';
			content += '<td>'+item.bHit+'</td>';
			content += '<td>'+item.reg_date+'</td>';
			content += '<td><input type="checkbox" name="del" /></td>';
			content += '</tr>';
		}
		
		$('#bbsList').html(content);
	}
	
	// 삭제 전체 선택
	$('input[name="all"]').on('click', function() {
		if ($(this).is(':checked')) {	
			$('input[name="del"]').attr('checked',true);	
		} else {
			$('input[name="del"]').attr('checked',false);	
		}
	});
	
</script>
</html>