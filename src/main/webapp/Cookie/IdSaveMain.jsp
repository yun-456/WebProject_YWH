<%@ page import="utils.CookieManage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 페이지에 진입하면 loginId라는 쿠키가 있는지 확인한다.
String loginId = CookieManage.readCookie(request, "loginId");

// 이미 생성된 쿠키가 있다면 체크박스가 체크된 상태로 페이지를 로드한다.
String cookieCheck = "";
if (!loginId.equals("")) {
    // 이를 위해 checked 속성값을 추가한다.
    cookieCheck = "checked";
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>로그인 페이지 - 아이디 저장하기</title>
</head>
<body>
    <h2>로그인 페이지</h2>
    <form action="IdSaveProcess.jsp" method="post">
        <!-- 쿠키에 저장된 아이디가 있다면 value 속성에 설정한다. -->
        아이디: <input type="text" name="user_id" value="<%= loginId %>" tabindex="1" />  
        <!-- 저장된 쿠키가 있다면 체크된 상태로 페이지를 로드한다. -->
        <input type="checkbox" name="save_check" value="Y" <%= cookieCheck %> tabindex="3" /> 아이디 저장하기
        <br />
        패스워드: <input type="password" name="password" tabindex="2" />
        <br />
        <input type="submit" value="로그인하기" />
    </form>
</body>
</html>
