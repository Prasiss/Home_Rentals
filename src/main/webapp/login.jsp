<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <h1>HomeRentals</h1>
                <p>Administration Panel</p>
            </div>
            
            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>
            
            <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" name="email" placeholder="Enter your email address" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" placeholder="Enter your password" required>
                </div>
                <div class="remember-row">
                    <label><input type="checkbox"> Remember me</label>
                    <a href="#">Forgot password?</a>
                </div>
                <button type="submit" class="login-button">Sign In</button>
            </form>
            
            <div class="demo-section">
                <h3>Demo Credentials</h3>
                <div class="demo-item">
                    <span class="demo-role">Admin</span>
                    <span class="demo-email">admin@homerental.com</span>
                    <span class="demo-pass">Admin@123</span>
                </div>
                <div class="demo-item">
                    <span class="demo-role">Dealer</span>
                    <span class="demo-email">arjun@homerental.com</span>
                    <span class="demo-pass">Dealer@123</span>
                </div>
                <div class="demo-item">
                    <span class="demo-role">User</span>
                    <span class="demo-email">bikash@gmail.com</span>
                    <span class="demo-pass">User@123</span>
                </div>
            </div>
            
            <div class="register-link">
                Don't have an account? <a href="#">Register here</a>
            </div>
        </div>
    </div>
</body>
</html>