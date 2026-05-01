<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - HomeRentals</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<main>
  <section class="login-section">
    <div class="login-container">
      <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
        <h1>Login</h1>
        <c:if test="${not empty error}">
          <p style="color:red;">${error}</p>
        </c:if>

        <div class="field">
          <i class="fa-regular fa-user"></i>
          <input type="text" name="username" placeholder="Username" />
        </div>

        <div class="field">
          <i class="fa-solid fa-lock"></i>
          <input type="password" name="password" placeholder="Password" />
        </div>

        <button class="btn-login">Login</button>

        <div class="register-link">
          <p>OR</p>
          <p>Don't have an account?<br />
            <a href="${pageContext.request.contextPath}/register">Register Here</a>
          </p>
        </div>
      </form>

      <div class="login-illustration">
        <img src="${pageContext.request.contextPath}/image/login-image.jpg" alt="Login Image"/>
      </div>
    </div>
  </section>
</main>

</body>
</html>