<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutus.css">
</head>
<%@ include file="../client/client_template/header.jsp" %>
<main>
      <section class="about-section">
        <h2>ABOUT US</h2>

        <div class="about-content">
          <!-- TEXT -->
          <div class="about-text">
            <h3>Who We Are</h3>
            <p>
              Villare Rentals is a modern platform designed to simplify the
              process of finding and renting homes. We aim to provide users with
              a seamless and reliable experience when searching for rental
              properties.
            </p>

            <h3>Our Mission</h3>
            <p>
              Our mission is to connect tenants and property owners through a
              clean, easy-to-use platform. We focus on convenience,
              transparency, and accessibility for everyone.
            </p>

            <h3>Why Choose Us</h3>
            <ul>
              <li><i class="fa-solid fa-check"></i> Easy property search</li>
              <li><i class="fa-solid fa-check"></i> Trusted listings</li>
              <li><i class="fa-solid fa-check"></i> User-friendly interface</li>
              <li><i class="fa-solid fa-check"></i> Fast communication</li>
            </ul>
          </div>

          <!-- IMAGE -->
          <div class="about-image">
            <img src="${pageContext.request.contextPath}/image/site-logo.png" alt="About Image"/>
          </div>
        </div>
      </section>
    </main>
    <section class="team-section">
      <h2>OUR TEAM</h2>

      <div class="slider-container">
        <!-- LEFT BUTTON -->
        <button class="slider-btn left" onclick="slideLeft()">
          <i class="fa-solid fa-chevron-left"></i>
        </button>

        <!-- CARDS -->
        <div class="team-slider" id="teamSlider">
          <!-- MEMBER -->
          <div class="team-card">
            <img src="${pageContext.request.contextPath}/image/perso.png" />

			  <h3>Prasim Basnet</h3>
			  <p class="role">Co-Founder</p>
			
			  <p class="description">
			    Passionate learner from Kathmandu exploring software development,
			    UI/UX design, and modern web technologies.
			  </p>
			
			  <div class="socials">
			    <a href="https://www.instagram.com/assassin_e96/"><i class="fa-brands fa-instagram"></i></a>
			    <a href="https://www.linkedin.com/in/prasimbasnet096/"><i class="fa-brands fa-linkedin"></i></a>
			    <a href="https://www.facebook.com/prasim.basnet/"><i class="fa-brands fa-facebook"></i></a>
			    <a href="https://www.github.com/prasiss"><i class="fa-brands fa-github"></i></a>
			  </div>
          </div>
          <div class="team-card">
            <img src="${pageContext.request.contextPath}/image/perso.png" />

			  <h3>Aarish Duwal</h3>
			  <p class="role">Co-Founder</p>
			
			  <p class="description">
			    Passionate learner from Kathmandu exploring software development,
			    UI/UX design, and modern web technologies.
			  </p>
			
			  <div class="socials">
			    <a href="#"><i class="fa-brands fa-instagram"></i></a>
			    <a href="#"><i class="fa-brands fa-linkedin"></i></a>
			    <a href="#"><i class="fa-brands fa-facebook"></i></a>
			     <a href="https://github.com/AarishDuwal"><i class="fa-brands fa-github"></i></a>
			  </div>
          </div>
          <div class="team-card">
            <img src="${pageContext.request.contextPath}/image/perso.png" />

			  <h3>Riplik Bade</h3>
			  <p class="role">Founder</p>
			
			  <p class="description">
			    Passionate learner from Kathmandu exploring software development,
			    UI/UX design, and modern web technologies.
			  </p>
			
			  <div class="socials">
			    <a href="#"><i class="fa-brands fa-instagram"></i></a>
			    <a href="#"><i class="fa-brands fa-linkedin"></i></a>
			    <a href="#"><i class="fa-brands fa-facebook"></i></a>
			     <a href="https://github.com/replic005"><i class="fa-brands fa-github"></i></a>
			  </div>
          </div>
          <div class="team-card">
            <img src="${pageContext.request.contextPath}/image/perso.png" />

			  <h3>Swopnil Katuwal</h3>
			  <p class="role">Founder</p>
			
			  <p class="description">
			    Passionate learner from Kathmandu exploring software development,
			    UI/UX design, and modern web technologies.
			  </p>
			
			  <div class="socials">
			    <a href="#"><i class="fa-brands fa-instagram"></i></a>
			    <a href="#"><i class="fa-brands fa-linkedin"></i></a>
			    <a href="#"><i class="fa-brands fa-facebook"></i></a>
			     <a href="https://github.com/SwapnilKatuwal"><i class="fa-brands fa-github"></i></a>
			  </div>
          </div>
          <div class="team-card">
            <img src="${pageContext.request.contextPath}/image/perso.png" />

			  <h3>Aschin Prajapati</h3>
			  <p class="role">Co-Founder</p>
			
			  <p class="description">
			    Passionate learner from Kathmandu exploring software development,
			    UI/UX design, and modern web technologies.
			  </p>
			
			  <div class="socials">
			    <a href="#"><i class="fa-brands fa-instagram"></i></a>
			    <a href="#"><i class="fa-brands fa-linkedin"></i></a>
			    <a href="#"><i class="fa-brands fa-facebook"></i></a>
			     <a href="https://github.com/X33N1C"><i class="fa-brands fa-github"></i></a>
			  </div>
          </div>
          <div class="team-card">
            <img src="${pageContext.request.contextPath}/image/perso.png" />

			  <h3>Sujan Man Sakhya</h3>
			  <p class="role">Member</p>
			
			  <p class="description">
			    Passionate learner from Kathmandu exploring software development,
			    UI/UX design, and modern web technologies.
			  </p>
			
			  <div class="socials">
			    <a href="#"><i class="fa-brands fa-instagram"></i></a>
			    <a href="#"><i class="fa-brands fa-linkedin"></i></a>
			    <a href="#"><i class="fa-brands fa-facebook"></i></a>
			     <a href="https://www.github.com/prasiss"><i class="fa-brands fa-github"></i></a>
			  </div>
          </div>

        </div>

        <!-- RIGHT BUTTON -->
        <button class="slider-btn right" onclick="slideRight()">
          <i class="fa-solid fa-chevron-right"></i>
        </button>
      </div>
    </section>
    <script  src="${pageContext.request.contextPath}/js/main.js"> </script>
<%@ include file="../client/client_template/footer.jsp" %>