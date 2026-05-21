<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>

<link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
/>

<link
  rel="stylesheet"
  href="${pageContext.request.contextPath}/css/footer.css"
/>

<footer>
  <div class="footer-top">
    <div class="footer-brand">
      <div class="footer-image">
        <img
          src="${pageContext.request.contextPath}/image/site-logo.png"
          alt="Villare Logo"
        />
      </div>

      <h2>Villare Rentals</h2>

      <div class="footer-social">
        <a href="#"><i class="fab fa-facebook-f"></i></a>
        <a href="#"><i class="fab fa-instagram"></i></a>
        <a href="#"><i class="fab fa-x-twitter"></i></a>
        <a href="#"><i class="fab fa-linkedin-in"></i></a>
      </div>
    </div>

    <div class="footer-links">
      <h4>Quick Links</h4>

      <a href="${pageContext.request.contextPath}/home">Home</a>
      <a href="${pageContext.request.contextPath}/homedescription">Rentals</a>
      <a href="${pageContext.request.contextPath}/aboutus">About Us</a>
      <a href="${pageContext.request.contextPath}/contactus">Contact</a>
    </div>

    <div class="footer-contact">
      <h4>Contact Us</h4>

      <p>
        <i class="fas fa-envelope"></i>

        <a href="mailto:VillareRentals@gmail.com"> VillareRentals@gmail.com </a>
      </p>

      <p>
        <i class="fas fa-phone"></i>

        <a href="tel:9741688808"> 9741688808 </a>
      </p>

      <p>
        <i class="fas fa-location-dot"></i>
        Kathmandu, Nepal
      </p>
    </div>
  </div>

  <div class="footer-bottom">
    <p>&copy; 2026 Villare Rentals. All Rights Reserved.</p>
  </div>
</footer>
