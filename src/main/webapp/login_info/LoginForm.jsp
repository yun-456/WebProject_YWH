<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/assets/img/favicon.ico">
    <!-- CSS here -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
    <!-- Preloader Start -->
    <div id="preloader-active">
        <div class="preloader d-flex align-items-center justify-content-center">
            <div class="preloader-inner position-relative">
                <div class="preloader-circle"></div>
                <div class="preloader-img pere-text">
                    <img src="<%= request.getContextPath() %>/assets/img/logo/loder.png" alt="Logo">
                </div>
            </div>
        </div>
    </div>
    <!-- Preloader End -->

    <!-- Main Content -->
    <main class="login-body" data-vide-bg="<%= request.getContextPath() %>/assets/img/login-bg.mp4">
        <h2 class="text-center">웅희 프로젝트 로그인 페이지</h2>
        <form class="form-default" action="<%= request.getContextPath() %>/login_info/LoginProcess.jsp" method="post">
            <div class="login-form">
                <!-- Logo -->
                <div class="logo-login">
                    <a href="<%= request.getContextPath() %>/index.jsp">
                        <img src="<%= request.getContextPath() %>/assets/img/logo/loder.png" alt="Logo">
                    </a>
                </div>
                <h2>Login Here</h2>
                <div class="form-input">
                    <label for="user_id">아이디</label>
                    <input type="text" id="user_id" name="user_id" placeholder="아이디" required>
                </div>
                <div class="form-input">
                    <label for="user_pw">패스워드</label>
                    <input type="password" id="user_pw" name="password" placeholder="패스워드" required>
                </div>
                <div class="form-input pt-30">
                    <input type="submit" value="로그인하기" class="btn btn-primary">
                </div>
                <!-- Additional Links -->
                <a href="#" class="forget">비밀번호 찾기</a>
                <a href="<%= request.getContextPath() %>/login_info/sign.jsp" class="registration">회원가입</a>
            </div>
        </form>
    </main>

    <!-- JavaScript Files -->
    <script src="<%= request.getContextPath() %>/assets/js/vendor/jquery-1.12.4.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/main.js"></script>
</body>
</html>
