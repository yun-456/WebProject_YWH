<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
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
</style>
</head>
<body>
<h2>댓글 수정</h2>

<div class="container">
    <form method="post" action="../qaboard/qacommentEdit.do">
        <input type="hidden" name="commentId" value="${dto.commentId}" />
        <table width="100%">
            <tr>
                <td><textarea name="content" style="width: 100%; height: 100px;">${dto.content}</textarea></td>
            </tr>
            <tr>
                <td align="right">
                    <button type="submit">댓글 수정 완료</button>
                    <button type="button" onclick="location.href='../qaboard/qaview.do?idx=${dto.boardIdx}';">취소</button>

                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
