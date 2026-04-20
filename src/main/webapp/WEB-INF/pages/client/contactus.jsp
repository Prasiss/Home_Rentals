<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/contactus.css">
</head>
<%@ include file="../client/client_template/header.jsp" %>
<main>
      <section class="contact-section">
        <h2>CONTACT US</h2>

        <form class="contact-form">
          <!-- Name -->
          <div class="field-wrap">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="Full Name" />
          </div>

          <!-- Email -->
          <div class="field-wrap">
            <i class="fas fa-envelope"></i>
            <input type="email" placeholder="Email" />
          </div>

          <!-- Message -->
          <div class="field-wrap textarea-wrap">
            <i class="fas fa-comment-dots"></i>
            <textarea placeholder="Write your Message here"></textarea>
          </div>

          <button class="btn-send">Send Message</button>
        </form>
      </section>
    </main>
<%@ include file="../client/client_template/footer.jsp" %>