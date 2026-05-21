<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<%@ include file="/WEB-INF/pages/client/client_template/header.jsp" %>
<main>
	<section class="hero">

		<div class="hero-content">
			<h1>Find Your Perfect Rental Home</h1>

			<p>
				Discover premium houses, apartments, and rooms at the best prices.
				Simple booking. Trusted listings. Comfortable living.
			</p>

			<a href="" class="hero-btn">
				<i class="fa-solid fa-magnifying-glass"></i>
				Book Now
			</a>
		</div>

	</section>
	<div class="layout">
		<section class="main-content">
			<h2>Available For Rent</h2>
			<div class="cards">
				<c:forEach var="home" items="${homes}">
					<a href="${pageContext.request.contextPath}/homedescription?id=${home.id}" 
					style="text-decoration:none; color:inherit; display:block;">
						<div class="card">
							<img src="${pageContext.request.contextPath}/getimage?id=${home.id}">
							<div class="card-body">
								<h3>${home.name}</h3>
								<p>${home.location}</p>
								<p>
									<i class="fa-solid fa-money-bill-wave"></i>
									NPR ${home.price}
								</p>
								<div class="stars">⭐⭐⭐⭐</div>
				
								<p class="desc">${home.description}</p>
								<div class="card-actions">
									
									<form action="${pageContext.request.contextPath}/addtocart" method="POST">
				
										<input type="hidden" name="id" value="${home.id}">
				
										<button type="submit" class="cart-btn">
											<i class="fa-solid fa-cart-shopping"></i>
											Book Now
										</button>
									</form>
									<form action="${pageContext.request.contextPath}/wishlist" method="POST">
									<input type="hidden" name="id" value="${home.id}">
									<input type="hidden" name="action" value="add">
									
									<button type="submit" class="wishlist-btn">
											<i class="fa-solid fa-heart"></i>
											Wishlist
										</button>
									</form>
								</div>
							</div>
						</div>			
					</a>			
				</c:forEach>
				<c:if test="${empty homes}">
					<p>No properties available.</p>
				</c:if>
			</div>
	</div>
</main>

<%@ include file="/WEB-INF/pages/client/client_template/footer.jsp" %>