<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%
    // 로그인 폼에서 전송된 폼 값을 받는다.
    String userId = request.getParameter("user_id");
    String userPwd = request.getParameter("password");

    // application 내장 객체를 이용해서 web.xml에 등록된 접속 정보를 읽어온다.
    String oracleDriver = application.getInitParameter("OracleDriver");
    String oracleURL = application.getInitParameter("OracleURL");
    String oracleId = application.getInitParameter("OracleId");
    String oraclePwd = application.getInitParameter("OraclePwd");

    // DB 연결
    MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
    MemberDTO memberDTO = null;

    try {
        // 아이디와 비밀번호를 이용해 회원 정보를 가져온다.
        memberDTO = dao.getMemberDTO(userId, userPwd);

        if (memberDTO != null && memberDTO.getId() != null) {
            // 로그인 성공
            session.setAttribute("UserId", memberDTO.getId());
            session.setAttribute("UserName", memberDTO.getName());
            out.println("<script>alert('환영합니다, " + memberDTO.getName() + "님!'); location.href='" + request.getContextPath() + "/index.jsp';</script>");
        } else {
            // 로그인 실패 시 메시지 출력 및 로그인 폼으로 이동
            out.println("<script>alert('로그인 실패. 아이디 또는 비밀번호를 확인해주세요.'); history.back();</script>");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('로그인 중 오류 발생: 관리자에게 문의하세요.'); history.back();</script>");
    } finally {
        dao.close();
    }
%>
