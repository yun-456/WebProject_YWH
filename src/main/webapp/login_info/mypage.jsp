<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="membership.MemberDAO"%>
<%@ page import="membership.MemberDTO"%>
<%
    // 세션에서 현재 로그인한 사용자 아이디 가져오기
    String userId = (String) session.getAttribute("UserId");

    // 로그인된 사용자가 아니면 로그인 페이지로 이동
    if (userId == null) {
        response.sendRedirect(request.getContextPath() + "/login_info/LoginForm.jsp");
        return;
    }

    // application 내장 객체를 이용해 web.xml에서 접속 정보를 가져온다.
    String oracleDriver = application.getInitParameter("OracleDriver");
    String oracleURL = application.getInitParameter("OracleURL");
    String oracleId = application.getInitParameter("OracleId");
    String oraclePwd = application.getInitParameter("OraclePwd");

    // DB 접속을 위한 DAO 객체 생성
    MemberDAO memberDAO = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
    MemberDTO memberDTO = memberDAO.UserUpdate(userId);

    // DB 연결 종료
    memberDAO.close();

    if (memberDTO == null) {
        out.println("<script>alert('회원 정보를 불러올 수 없습니다.'); location.href='" + request.getContextPath() + "/index.jsp';</script>");
        return;
    }
%>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<style>
    body {
        background-color: #f0f8ff;
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .form-container {
        width: 400px;
        padding: 30px;
        background-color: #ffffff;
        border: 2px solid #007bff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        position: relative;
    }
    .form-container h2 {
        text-align: center;
        color: #007bff;
        margin-bottom: 20px;
        font-weight: bold;
    }
    .form-container label {
        margin-bottom: 5px;
        font-weight: bold;
    }
    .form-container input, .form-container select {
        width: 100%;
        border-radius: 5px;
        border: 1px solid #ccc;
        padding: 10px;
        margin-bottom: 15px;
    }
    .form-container button {
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        padding: 10px;
        font-size: 16px;
        width: 100%;
        cursor: pointer;
        margin-bottom: 15px;
    }
    .form-container button:hover {
        background-color: #0056b3;
    }
    .form-container a {
        text-decoration: none;
        color: black;
        text-align: center;
        display: block;
        margin-top: 20px;
    }
    .form-container a:hover {
        text-decoration: underline;
    }
</style>
<body>
    <div class="form-container">
        <h2>회원 정보 수정</h2>
        <form action="<%= request.getContextPath() %>/login_info/mypageProcess.jsp" method="post">
            <div>
                <label for="user_id">아이디:</label>
                <input type="text" id="user_id" name="user_id" value="<%= memberDTO.getId() %>" readonly>
            </div>
            <div>
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" value="<%= memberDTO.getPass() %>" required>
            </div>
            <div>
                <label for="name">이름:</label>
                <input type="text" id="name" name="name" value="<%= memberDTO.getName() %>" required>
            </div>
            <div>
                <label for="email">이메일:</label>
                <input type="email" id="email" name="email" value="<%= memberDTO.getEmail() %>" required>
            </div>
            <div>
                <label for="birth_date">생년월일:</label>
                <input type="date" id="birth_date" name="birth_date" value="<%= memberDTO.getBirthDate() %>" required>
            </div>
            <div>
                <label for="gender">성별:</label>
                <select name="gender" id="gender" required>
                    <option value="M" <%= "M".equals(memberDTO.getGender()) ? "selected" : "" %>>남성</option>
                    <option value="F" <%= "F".equals(memberDTO.getGender()) ? "selected" : "" %>>여성</option>
                </select>
            </div>
            <div>
                <label for="phone">전화번호:</label>
                <input type="text" id="phone" name="phone" value="<%= memberDTO.getPhone() %>" required>
            </div>
            <button type="submit">회원 정보 수정</button>
        </form>
        <a href="<%= request.getContextPath() %>/index.jsp">메인 페이지로 돌아가기</a>
    </div>
</body>
</html>
