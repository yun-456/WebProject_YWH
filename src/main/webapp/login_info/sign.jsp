<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<style>
    body {
        background-color: #f0f8ff;
        font-family: Arial, sans-serif;
    }
    .form-container {
        margin: 50px auto;
        padding: 30px;
        max-width: 400px;
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border: 1px solid #dcdcdc;
    }
    .form-container h2 {
        text-align: center;
        color: #007bff;
        margin-bottom: 20px;
        font-weight: bold;
    }
    .form-container .form-control {
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
    }
    .form-container button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>회원가입</h2>
            <!-- 회원가입 폼 -->
            <form action="<%=request.getContextPath()%>/login_info/signProcess.jsp" method="post" novalidate>
                <div class="form-group">
                    <label for="user_id">아이디:</label>
                    <input type="text" name="user_id" id="user_id" class="form-control" placeholder="아이디를 입력하세요" pattern="[a-zA-Z0-9]{4,20}" title="영문 또는 숫자로 4~20자로 입력하세요" required>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호:</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력하세요" pattern=".{6,}" title="6자 이상 입력하세요" required>
                </div>
                <div class="form-group">
                    <label for="name">이름:</label>
                    <input type="text" name="name" id="name" class="form-control" placeholder="이름을 입력하세요" required>
                </div>
                <div class="form-group">
                    <label for="phone">전화번호:</label>
                    <input type="text" name="phone" id="phone" class="form-control" placeholder="전화번호를 입력하세요" pattern="[0-9]{10,15}" title="10~15자리 숫자만 입력하세요" required>
                </div>
                <div class="form-group">
                    <label for="birth_date">생년월일:</label>
                    <input type="date" name="birth_date" id="birth_date" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="gender">성별:</label>
                    <select name="gender" id="gender" class="form-control" required>
                        <option value="" disabled selected>성별을 선택하세요</option>
                        <option value="M">남성</option>
                        <option value="F">여성</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="email">이메일:</label>
                    <input type="email" name="email" id="email" class="form-control" placeholder="이메일을 입력하세요" required>
                </div>
                <button type="submit">회원가입</button>
            </form>
        </div>
    </div>
</body>
</html>
