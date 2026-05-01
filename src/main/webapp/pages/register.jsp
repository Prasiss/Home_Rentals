<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - HomeRentals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<section class="register-section">
  <div class="register">
    <form class="register-form" action="${pageContext.request.contextPath}/register" method="post">
      <h2>REGISTER</h2>
      <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
      </c:if>

      <div class="field"><i class="fa-regular fa-user"></i><input type="text" name="fullname" placeholder="Full Name" /></div>
      <div class="field"><i class="fa-regular fa-user"></i><input type="text" name="username" placeholder="Username" /></div>
      <div class="field"><i class="fa-regular fa-envelope"></i><input type="email" name="email" placeholder="Email" /></div>
      <div class="field"><i class="fa-solid fa-phone"></i><input type="text" name="number" placeholder="Phone Number" /></div>
      <div class="field"><i class="fa-solid fa-lock"></i><input type="password" name="password" placeholder="Password" /></div>
      <div class="field"><i class="fa-solid fa-lock"></i><input type="password" name="confirmpassword" placeholder="Confirm Password" /></div>

      <button class="btn-register">Register</button>

      <div class="login-link">
        <p>OR</p>
        <p>Already have account?<br /><a href="${pageContext.request.contextPath}/login">Login</a></p>
      </div>
    </form>

    <div class="register-illustration">
      <img src="${pageContext.request.contextPath}/image/login-image.jpg" />
    </div>
  </div>
</section>

</body>
</html>