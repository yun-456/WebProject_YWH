<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL사용을 위한 Taglib 지시어 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 - 목록보기</title>
<style>
a {
    text-decoration: none;
}
</style>
</head>
<body>

    <h2>자유게시판 - 목록보기</h2>
    
    <!-- 목록 테이블 -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="25%">내용</th>
            <th width="15%">작성일</th>
        </tr>
        <c:choose>
            <c:when test="${ empty boardLists }">
                <!-- List에 저장된 값이 없다면 등록된 게시물이 없거나, 검색된 게시물이 없는 경우 -->
                <tr>
                    <td colspan="5" align="center">등록된 게시물이 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <!-- List에 저장된 데이터가 있다면, 크기만큼 반복하여 출력 -->
                <c:forEach items="${ boardLists }" var="row" varStatus="loop">
                    <tr align="center">
                        <td>${totalCount - loop.index}</td>
                        <!-- 게시물의 갯수를 저장한 totalCount에서 인출되는 인스턴스의 인덱스를 차감해서 순차적인 번호를 출력 -->
                        <td align="left">
                            <a href="<c:url value='/mvcboard1/view.do' />?idx=${row.idx}">${row.title}</a>
                        </td>
                        <!-- 현재 루프에서 row는 MVCBoardDTO를 의미하므로 각 멤버변수의 getter()를 통해 저장된 값을 출력 -->
                        <td>${row.id}</td>
                        <td align="left">${row.content}</td>
                        <td>${row.postdate}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>

    <!-- 하단 메뉴 (글쓰기 버튼) -->
    <table border="1" width="90%">
        <tr align="center">
            <td>
                <button type="button" onclick="location.href='<c:url value='/mvcboard1/write.do' />';">글쓰기</button>
            </td>
        </tr>
    </table>
</body>
</html>

