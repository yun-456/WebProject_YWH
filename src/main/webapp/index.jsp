<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>WebProject_YWH | Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<!-- Header -->
	<header>
		<div class="header-area header-transparent">
			<div class="main-header">
				<div class="container-fluid">
					<div class="row align-items-center">
						<!-- Logo -->
						<div class="col-xl-2 col-lg-2">
							<div class="logo">
								<a href="<%= request.getContextPath() %>/index.jsp"><img
									src="assets/img/logo/logo.png" alt="Logo"></a>
							</div>
						</div>
						<!-- Navigation -->
						<div class="col-xl-10 col-lg-10">
							<div class="menu-wrapper">
								<nav>
									<ul id="navigation">
										<li class="active"><a
											href="<%= request.getContextPath() %>/index.jsp">Home</a></li>
										<li><a href="<%= request.getContextPath() %>/courses.jsp">Courses</a></li>
										<li><a href="<%= request.getContextPath() %>/about.jsp">About</a></li>
										<li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
										<%-- 로그인 상태 확인 --%>
										<% if (session.getAttribute("UserId") == null) { %>
											<li class="button-header"><a
												href="<%= request.getContextPath() %>/login_info/LoginForm.jsp"
												class="btn btn3">Log In</a></li>
											<li class="button-header"><a
												href="<%= request.getContextPath() %>/login_info/sign.jsp"
												class="btn btn3">Sign Up</a></li>
										<% } else { %>
											<li class="button-header"><a
												href="<%= request.getContextPath() %>/login_info/mypage.jsp"
												class="btn btn3">My Page</a></li>
											<li class="button-header"><a
												href="<%= request.getContextPath() %>/login_info/Logout.jsp"
												class="btn btn3">Log Out</a></li>
										<% } %>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<main>
		<div class="slider-area">
			<div class="slider-active">
				<!-- Single Slider -->
				<div class="single-slider slider-height d-flex align-items-center">
					<div class="container">
						<div class="row">
							<div class="col-xl-6 col-lg-7 col-md-12">
								<div class="hero__caption">
									<h1>Welcome to WebProject_YWH</h1>
									<p>Your platform for online learning and personal growth.</p>
									<a href="<%= (session.getAttribute("UserId") != null) ? request.getContextPath() + "/mvcboard1/list.do" : request.getContextPath() + "/login_info/LoginForm.jsp" %>"
										class="btn hero-btn">자유게시판</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<section class="services-area">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-6">
						<div class="single-services">
							<h3>Wide Range of Courses</h3>
							<p>Explore a variety of subjects to enhance your skills.</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="single-services">
							<h3>Expert Instructors</h3>
							<p>Learn from the best in the field.</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="single-services">
							<h3>Lifetime Access</h3>
							<p>Access your courses anytime, anywhere.</p>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>

	<!-- Footer -->
	<footer>
		<div class="footer-area footer-padding">
			<div class="container">
				<div class="row justify-content-between">
					<div class="col-xl-4 col-lg-4">
						<div class="footer-logo">
							<a href="<%= request.getContextPath() %>/index.jsp"><img
								src="assets/img/logo/logo2_footer.png" alt="Logo"></a>
						</div>
						<p>Start your journey with us today.</p>
					</div>
					<div class="col-xl-4 col-lg-4">
						<ul>
							<li><a href="<%= request.getContextPath() %>/about.jsp">About
									Us</a></li>
							<li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
							<li><a href="<%= request.getContextPath() %>/courses.jsp">Courses</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- Scripts -->
	<script src="assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>
