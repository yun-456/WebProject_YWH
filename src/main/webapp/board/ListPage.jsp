<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL사용을 위한 Taglib 지시어 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<style>
body {
	background-color: #f0f8ff;
	font-family: Arial, sans-serif;
}

.container {
	width: 70%;
	margin: 50px auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #ffffff;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	color: #0073e6;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 15px;
	text-align: center;
}

th {
	background-color: #0073e6;
	color: white;
}

input[type="text"], select {
	width: calc(100% - 20px);
	padding: 8px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 5px;
}

input[type="submit"], button {
	background-color: #0073e6;
	color: white;
	padding: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover, button:hover {
	background-color: #005bb5;
}

a {
	text-decoration: none;
	color: #0073e6;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>

	<div class="container">
		<h2>자유게시판- 목록보기(List)</h2>
		<!-- 검색 폼 -->
		<!-- 검색어를 입력 후 '검색' 버튼을 누르면 현재 페이지로 입력한 폼값이 전송된다.
    action속성을 별도로 추가하지 않으면 현재페이지가 된다. -->
		<form method="get">
			<table>
				<tr>
					<td align="center"><select name="searchField">
							<option value="title">제목</option>
							<option value="content">내용</option>
					</select> <input type="text" name="searchWord" placeholder="검색어를 입력하세요" />
						<input type="submit" value="검색하기" /></td>
				</tr>
			</table>
		</form>
		<!-- 목록 테이블 -->
		<table>
			<tr>
				<th width="10%">번호</th>
				<th width="40%">제목</th>
				<th width="15%">작성자</th>
				<th width="10%">조회수</th>
				<th width="20%">작성일</th>
			</tr>
			<c:choose>
				<c:when test="${ empty boardLists }">
					<!-- List에 저장된 값이 없다면 등록된 게시물이 없거나, 검색된 게시물이 없는 경우. -->
					<tr>
						<td colspan="5" align="center">등록된 게시물이 없습니다^^*</td>
					</tr>
				</c:when>
				<c:otherwise>
					<!-- List에 저장된 데이터가 있다면, 크기만큼 반복하여 출력한다.
          해당 루프의 데이터를 인출하여 변수 row에 할당한다. -->
					<c:forEach items="${ boardLists }" var="row" varStatus="loop">
						<tr align="center">
							<td>${map.totalCount - (((map.pageNum-1)* map.pageSize) + loop.index )}</td>
							<!-- 게시물의 갯수를 저장한 totalCount에서 인출되는 인스턴스의 인덱스를 차감해서 순차적인 번호를 출력 -->
							<td align="left">
								<!-- 제목 클릭 시 '열람' 페이지로 이동해야 하므로 게시물의 일련번호를 파라미터로 전달한다. --> <a
								href="../board/view.do?idx=${ row.idx }">${row.title}</a>
							</td>
							<!-- 현재 루프에서 row는 MVCBoardDTO를 의미하므로 각 멤버변수의 getter()를 통해 저장된 값을 출력한다. -->
							<td>${row.id}</td>
							<td>${row.visitcount}</td>
							<td>${row.postdate}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<!-- 하단 메뉴 (바로가기, 글쓰기) -->
		<table>
			<tr align="center">
				<td>${ map.pagingImg }</td>
				<td width="100">
					<button type="button" onclick="location.href='../board/write.do';">글쓰기</button>
				</td>
			</tr>
		</table>
		</table>
		<!-- 메인 페이지로 돌아가기 버튼을 테이블 아래에 배치 -->
		<div style="text-align: center; margin-top: 20px;">
			<button type="button" onclick="location.href='../index.jsp';">메인
				페이지로 돌아가기</button>
		</div>
	</div>
</body>
</html>