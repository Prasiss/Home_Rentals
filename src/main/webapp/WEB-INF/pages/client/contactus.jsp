<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<head>
  <link
    rel="stylesheet"
    href="${pageContext.request.contextPath}/css/contactus.css"
  />
</head>
<%@ include file="../client/client_template/header.jsp" %>
<main>
  <section class="contact-section">
    <h2>CONTACT US</h2>

    <div class="contact-wrapper">
      <form
        class="contact-form"
        action="${pageContext.request.contextPath}/contactus"
        method="post"
      >
        <div class="field-wrap">
          <i class="fas fa-user"></i>
          <input type="text" placeholder="Full Name" name="fullname" />
        </div>

        <div class="field-wrap">
          <i class="fas fa-envelope"></i>
          <input type="email" name="email" placeholder="Email" />
        </div>

        <div class="field-wrap textarea-wrap">
          <i class="fas fa-comment-dots"></i>
          <textarea
            placeholder="Write your Message here"
            name="message"
          ></textarea>
        </div>

        <button class="btn-send">Send Message</button>
      </form>

      <div class="contact-info">
        <div class="info-box">
          <i class="fab fa-whatsapp"></i>
          <h3>WhatsApp</h3>
          <p>+977 9741688808</p>
          <a href="https://whatsapp.com/" target="_blank">Chat Now</a>
        </div>

        <div class="info-box">
          <i class="fas fa-location-dot"></i>
          <h3>Location</h3>
          <p>Kathmandu, Nepal</p>
          <a href="https://maps.google.com" target="_blank">View Map</a>
        </div>
      </div>
    </div>
  </section>
</main>
<%@ include file="../client/client_template/footer.jsp" %>
