<%@page import="java.text.SimpleDateFormat"%>
<%@page import = "java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 시간의 서식 지정(시:분:초)
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 세션 생성 시간
    long creationTime = session.getCreationTime();
    String creationTimeStr = dateFormat.format(new Date(creationTime));

    // 세션에 마지막으로 접근한 시간
    long lastTime = session.getLastAccessedTime();
    String lastTimeStr = dateFormat.format(new Date(lastTime));

    // 세션 유지 시간 설정: 초 단위 (예: 20분)
    session.setMaxInactiveInterval(60 * 20);

    // 사용자 이름 가져오기 (세션에 저장된 사용자 정보 활용)
    String userName = (String) session.getAttribute("Name");
    if (userName == null) {
        // 로그인이 되어있지 않다면 로그인 페이지로 리다이렉트
        response.sendRedirect("LoginForm.jsp");
        return;
    }
%>
<html>
<head>
    <title>Session 설정 확인</title>
</head>
<body>
    <h2>환영합니다, <%= userName %>님!</h2>
    <p>로그인되었습니다. 아래 세션 정보를 확인하세요:</p>
    <ul>
        <li>세션 유지 시간: <%= session.getMaxInactiveInterval() %>초</li>
        <li>세션 아이디: <%= session.getId() %></li>
        <li>최초 요청 시각: <%= creationTimeStr %></li>
        <li>마지막 요청 시각: <%= lastTimeStr %></li>
    </ul>

    <!-- 메인 페이지로 이동할 수 있는 링크 추가 -->
    <a href="<%= request.getContextPath() %>/index.jsp">메인 페이지로 돌아가기</a>
    <br>
    <!-- 로그아웃 링크 추가 -->
    <a href="<%= request.getContextPath() %>/login_info/Logout.jsp">로그아웃</a>
</body>
</html>
