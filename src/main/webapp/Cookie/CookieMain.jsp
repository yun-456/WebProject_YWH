<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Cookie</title>
</head>
<body>
<%
    // 게시물 ID 가져오기 (예시로 게시물 ID가 123이라고 가정)
    String postId = "123"; // 실제로는 파라미터나 세션에서 가져와야 합니다.

    // 쿠키 배열에서 해당 게시물 조회 쿠키가 있는지 확인
    Cookie[] cookies = request.getCookies();
    boolean hasViewed = false;

    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("viewed_" + postId)) {
                hasViewed = true; // 이미 조회한 적이 있는 경우
                break;
            }
        }
    }

    // 조회수가 증가되지 않은 경우에만 증가 처리
    if (!hasViewed) {
        // 게시물 조회수 증가 로직 (여기에 실제 조회수 증가 SQL 로직을 작성)
        // 예: UPDATE board SET visitcount = visitcount + 1 WHERE idx = ?

        // 새로운 쿠키 생성 (게시물 ID를 이용하여 쿠키 이름 설정)
        Cookie newCookie = new Cookie("viewed_" + postId, "true");
        newCookie.setMaxAge(60 * 60 * 24); // 쿠키 유효기간을 1일로 설정
        newCookie.setPath(request.getContextPath()); // 경로 설정
        response.addCookie(newCookie); // 클라이언트에 쿠키 전송

        out.println("조회수를 증가시켰습니다.<br/>");
    } else {
        out.println("오늘 이미 조회하셨습니다.<br/>");
    }
%>

<h2>게시물 내용</h2>
<p>여기 게시물 내용이 출력됩니다.</p>

</body>
</html>