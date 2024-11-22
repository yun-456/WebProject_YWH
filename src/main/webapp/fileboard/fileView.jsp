<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</style>
</head>
<body>
<h2>자료형게시판 - 상세 보기</h2>

<table border="1" width="90%">
    <colgroup>
        <col width="15%"/> <col width="35%"/>
        <col width="15%"/> <col width="*"/>
    </colgroup> 
    <!-- 
    Controller에서 인출한 레코드를 저장한 DTO객체를 출력해준다.
    EL을 사용하면 멤버변수명 만으로 getter를 호출하여 내용을 출력할 수 있다.
     -->
    <tr>
        <td>번호</td> <td>${ dto.idx }</td>
        <td>작성자</td> <td>${ dto.name }</td>
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
        
        <!-- 첨부파일이 이미지인 경우 -->
        <c:if test="${not empty dto.ofile and (fn:substringAfter(dto.ofile, '.') == 'png' or fn:substringAfter(dto.ofile, '.') == 'jpg' or fn:substringAfter(dto.ofile, '.') == 'jpeg' or fn:substringAfter(dto.ofile, '.') == 'gif')}">
            <br><img src="../Uploads/${dto.sfile}" style="max-width:100%;" />
        </c:if>

        <!-- 첨부파일이 음원인 경우 -->
        <c:if test="${not empty dto.ofile and (fn:substringAfter(dto.ofile, '.') == 'mp3' or fn:substringAfter(dto.ofile, '.') == 'wav')}">
            <br><audio controls>
                <source src="../Uploads/${dto.sfile}" type="audio/${fn:substringAfter(dto.ofile, '.')}" />
                Your browser does not support the audio element.
            </audio>
        </c:if>

        <!-- 첨부파일이 동영상인 경우 -->
        <c:if test="${not empty dto.ofile and (fn:substringAfter(dto.ofile, '.') == 'mp4' or fn:substringAfter(dto.ofile, '.') == 'avi' or fn:substringAfter(dto.ofile, '.') == 'wmv')}">
            <br><video width="100%" controls>
                <source src="../Uploads/${dto.sfile}" type="video/${fn:substringAfter(dto.ofile, '.')}" />
                Your browser does not support the video tag.
            </video>
        </c:if>
    </td>
</tr> 


    
    <!-- 하단 메뉴(버튼) --> 
    <tr>
        <td colspan="4" align="center">
            <button type="button" 
            onclick="location.href='../fileboard/fileedit.do?idx=${ param.idx }';">수정하기</button>
            <button type="button" 
            onclick="location.href='../fileboard/filedelete.do?idx=${ param.idx }';">삭제하기</button>
            <button type="button" 
            onclick="location.href='../fileboard/filelistPage.do';">
                목록 바로가기
            </button>
        </td>
    </tr>
</table>
</body>
</html>


<script>
function deleteConfirm(idx){
   let c = confirm("게시물을 삭제하시겠습니까?");
   if(c==true){
      location.href="../fileboard/filedelete.do?idx="+ idx;
   }
}
</script>
<button type="button"
onclick="deleteConfirm(${ param.idx });" style="background-color: pink;">
       삭제하기(confirm)
</button>
<%--
   //Java코드를 통해 접근할때..
   String userId = session.getAttribute("UserId").toString();
   ==> EL을 통해 접근할때..
      ${ UserId }
--%>
