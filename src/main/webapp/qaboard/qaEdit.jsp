<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
        text-align: left;
    }
    input[type="text"], textarea {
        width: calc(100% - 20px);
        padding: 8px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
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
</style>
<script type="text/javascript">
    function validateForm(form) {
        if (form.title.value == "") {
            alert("제목을 입력하세요.");
            form.title.focus();
            return false;
        }
        if (form.content.value == "") {
            alert("내용을 입력하세요.");
            form.content.focus();
            return false;
        }
    }
</script>
</head>
<h2>자료형게시판 - 수정하기(Edit)</h2>
<form name="writeFrm" method="post" action="../qaboard/qaedit.do" onsubmit="return validateForm(this);">

    <!-- 수정할 게시물의 일련번호 -->
    <input type="hidden" name="idx" value="${dto.idx}"/>
    <!-- 게시물의 작성자 아이디 -->
    <input type="hidden" name="id" value="${dto.id}"/>

    <table border="1" width="90%">
        <tr>
            <td>제목</td>
            <td>
                <input type="text" name="title" style="width:90%;" value="${dto.title}" />
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <textarea name="content" style="width:90%;height:100px;">${dto.content}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">작성 완료</button>
                <button type="reset">RESET</button>
                <button type="button" onclick="location.href='../qaboard/qalist.do';">
                    목록 바로가기
                </button>
            </td>
        </tr>
    </table>    
</form>
</body>
</html>
