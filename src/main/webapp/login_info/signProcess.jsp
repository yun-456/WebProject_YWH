<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="membership.MemberDAO"%>
<%@ page import="membership.MemberDTO"%>
<%
    // 입력된 회원가입 폼 값을 받는다.
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    String birthDate = request.getParameter("birth_date");
    String gender = request.getParameter("gender");
    String phone = request.getParameter("phone");
    String email = request.getParameter("email");

    // 입력값 검증
    if (userId == null || password == null || name == null || birthDate == null || gender == null || phone == null || email == null ||
        userId.isEmpty() || password.isEmpty() || name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || phone.isEmpty() || email.isEmpty()) {
        out.println("<script>alert('모든 필드를 입력해주세요.'); history.back();</script>");
        return;
    }

    // application 내장 객체를 이용해 web.xml에서 접속 정보를 가져온다.
    String oracleDriver = application.getInitParameter("OracleDriver");
    String oracleURL = application.getInitParameter("OracleURL");
    String oracleId = application.getInitParameter("OracleId");
    String oraclePwd = application.getInitParameter("OraclePwd");

    // DB 접속을 위한 DAO 객체 생성
    MemberDAO memberDAO = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);

    // 회원 정보를 담은 DTO 객체 생성
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setId(userId);
    memberDTO.setPass(password);
    memberDTO.setName(name);
    memberDTO.setBirthDate(birthDate);
    memberDTO.setPhone(phone);
    memberDTO.setGender(gender);
    memberDTO.setEmail(email);

    // 회원 등록 처리
    boolean isRegistered = memberDAO.registerMember(memberDTO);

    if (isRegistered) {
        // 회원가입 성공 시 로그인 페이지로 이동
        out.println("<script>alert('회원가입 성공!'); location.href='" + request.getContextPath() + "/login_info/LoginForm.jsp';</script>");
    } else {
        // 회원가입 실패
        out.println("<script>alert('회원가입 실패. 다시 시도해주세요.'); history.back();</script>");
    }

    // DB 연결 종료
    memberDAO.close();
%>
