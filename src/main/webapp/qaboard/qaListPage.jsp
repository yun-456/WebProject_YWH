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
		<h2>Q&A게시판- 목록보기(List)</h2>
		<!-- 검색 폼 -->
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
				<th width="30%">제목</th>
				<th width="10%">작성자</th>
				<th width="10%">조회수</th>
				<th width="20%">작성일</th>
				<th width="25%">답변 상태</th>
			</tr>
			<c:choose>
				<c:when test="${ empty boardLists }">
					<tr>
						<td colspan="6" align="center">등록된 게시물이 없습니다^^*</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ boardLists }" var="row" varStatus="loop">
						<tr align="center">
							<td>${map.totalCount - (((map.pageNum-1)* map.pageSize) + loop.index )}</td>
							<td align="left"><a
								href="../qaboard/qaview.do?idx=${ row.idx }">${row.title}</a></td>
							<td>${row.id}</td>
							<td>${row.visitcount}</td>
							<td>${row.postdate}</td>
							<td><c:choose>
									<c:when test="${row.anser_status.toString() == 'Y'}">답변 완료</c:when>
									<c:otherwise>답변 대기</c:otherwise>
								</c:choose></td>

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
					<button type="button"
						onclick="location.href='../qaboard/qawrite.do';">글쓰기</button>
				</td>
			</tr>
		</table>
		<!-- 메인 페이지로 돌아가기 버튼을 테이블 아래에 배치 -->
		<div style="text-align: center; margin-top: 20px;">
			<button type="button" onclick="location.href='../index.jsp';">메인
				페이지로 돌아가기</button>
		</div>
	</div>
</body>
</html>
