<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
  </head>
 <%@include file="../client/client_template/header.jsp"%>
      <section class="register-section">
        <div class="register">

          <form class="register-form" action="${pageContext.request.contextPath}/register" method="post">
            <h2>REGISTER</h2>
            <c:if test="${not empty error}">
              <p style="color: red">${error}</p>
            </c:if>
            <div class="field">
              <i class="fa-regular fa-user"></i>
              <input type="text" name="fullname" placeholder="Full Name" />
            </div>

            <div class="field">
              <i class="fa-regular fa-user"></i>
              <input type="text" name="username" placeholder="Username" />
            </div>

            <div class="field">
              <i class="fa-regular fa-envelope"></i>
              <input type="email" name="email" placeholder="Email" />
            </div>

            <div class="field">
              <i class="fa-solid fa-phone"></i>
              <input type="text" name="phonenumber" placeholder="Phone Number" />
            </div>

            <div class="field">
              <i class="fa-solid fa-lock"></i>
              <input type="password" name="password" placeholder="Password" />
            </div>

            <div class="field">
              <i class="fa-solid fa-lock"></i>
              <input type="password" name="confirmpassword" placeholder="Confirm Password" />
            </div>

            <button class="btn-register">Register</button>

            <div class="login-link">
              <p>OR</p>
              <p>
                Already have account?<br /><a href="${pageContext.request.contextPath}/login">Login</a>
              </p>
            </div>
          </form>


          <div class="register-illustration">
            <img src="${pageContext.request.contextPath}/image/login-image.jpg" />
          </div>
        </div>
      </section>
<%@ include file="../client/client_template/footer.jsp" %>