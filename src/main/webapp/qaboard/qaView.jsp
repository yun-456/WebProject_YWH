<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        width: 50%;
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
        padding: 10px;
        text-align: center;
    }
    th {
        background-color: #0073e6;
        color: white;
    }
    button {
        background-color: #0073e6;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    button:hover {
        background-color: #005bb5;
    }
    a {
        text-decoration: none;
        color: #0073e6;
    }
    a:hover {
        text-decoration: underline;
    }
    .comment-content {
        text-align: left;
    }
</style>
</head>
<body>
<h2>Q&A게시판 - 상세 보기</h2>

<table border="1" width="90%">
    <colgroup>
        <col width="15%"/> <col width="35%"/>
        <col width="15%"/> <col width="*"/>
    </colgroup> 

    <tr>
        <td>번호</td>
        <td colspan="3" style="text-align: center;">${dto.idx}</td>
    </tr>
    <tr>
        <td>작성일</td> <td>${ dto.postdate }</td>
        <td>조회수</td> <td>${ dto.visitcount }</td>
    </tr>
    <tr>
        <td>제목</td>
        <td colspan="3">${ dto.title }</td>
    </tr>
    <tr>
        <td>내용</td>
        <td colspan="3" height="100">
            <c:if test="${not empty dto.content}">
                ${dto.content}
            </c:if>
        </td>
    </tr> 

    <!-- 하단 메뉴(버튼) --> 
    <tr>
        <td colspan="4" align="center">
            <button type="button" 
            onclick="location.href='../qaboard/qaedit.do?idx=${ param.idx }';">수정하기</button>
            <button type="button" 
            onclick="location.href='../qaboard/qadelete.do?idx=${ param.idx }';">삭제하기</button>
            <button type="button" 
            onclick="location.href='../qaboard/qalistPage.do';">
                목록 바로가기
            </button>
        </td>
    </tr>
</table>

<h3>댓글</h3>
<!-- 댓글 목록 출력 -->
<table border="1" width="90%">
    <tr>
        <th>작성자</th>
        <th>내용</th>
        <th>작성일</th>
        <th>관리</th>
    </tr>
    <c:forEach var="comment" items="${commentList}">
        <tr>
            <td>${comment.userId}</td>
            <td class="comment-content">${comment.content}</td>
            <td>${comment.postDate}</td>
            <td>
                <c:if test="${comment.userId == sessionScope.UserId}">
                    <button type="button" onclick="location.href='../qaboard/qacommentEdit.do?commentId=${comment.commentId}&boardIdx=${dto.idx}';">수정</button>
                    <button type="button" onclick="deleteComment(${comment.commentId}, ${dto.idx});">삭제</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- 댓글 작성 폼 -->
<form method="post" action="../qaboard/qacomment.do">
    <input type="hidden" name="boardIdx" value="${dto.idx}" />
    <table width="90%">
        <tr>
            <td><textarea name="content" style="width: 100%; height: 60px;" placeholder="댓글을 입력하세요"></textarea></td>
        </tr>
        <tr>
            <td align="right">
                <button type="submit">댓글 작성</button>
            </td>
        </tr>
    </table>
</form>

<script>
function deleteConfirm(idx){
   let c = confirm("게시물을 삭제하시겠습니까?");
   if(c==true){
      location.href="../qaboard/qadelete.do?idx="+ idx;
   }
}

function deleteComment(commentId, boardIdx) {
   let c = confirm("댓글을 삭제하시겠습니까?");
   if(c == true) {
      location.href = "../qaboard/qacommentdelete.do?commentId=" + commentId + "&boardIdx=" + boardIdx;
   }
}
</script>
<button type="button"
onclick="deleteConfirm(${ param.idx });" style="background-color: pink;">
       삭제하기(confirm)
</button>
</body>
</html>
