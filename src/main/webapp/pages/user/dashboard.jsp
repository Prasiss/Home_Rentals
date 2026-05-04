<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - HomeRentals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>
<jsp:include page="/pages/includes/sidebar.jsp"/>
<div class="main-content">
    <jsp:include page="/pages/includes/header.jsp"/>

    <div class="welcome-banner">
        <h1>Welcome back, ${user.fullName}</h1>
        <p>Ready to find your next home? Browse our latest properties.</p>
    </div>

    <c:if test="${not empty sessionScope.dealerSuccess}">
        <div class="alert success">${sessionScope.dealerSuccess}</div>
        <c:remove var="dealerSuccess" scope="session"/>
    </c:if>

    <div class="stats-grid">
        <div class="stat-card">
            <div class="stat-number">${totalBookings}</div>
            <div class="stat-label">Total Bookings</div>
        </div>
        <div class="stat-card">
            <div class="stat-number">${activeStays}</div>
            <div class="stat-label">Active Stays</div>
        </div>
        <div class="stat-card">
            <div class="stat-number">${completedStays}</div>
            <div class="stat-label">Completed Stays</div>
        </div>
        <div class="stat-card">
            <div class="stat-number">${wishlistCount}</div>
            <div class="stat-label">Saved Properties</div>
        </div>
    </div>

    <div class="quick-actions">
        <a href="${pageContext.request.contextPath}/explore" class="btn btn-primary">Explore Properties</a>
        <a href="${pageContext.request.contextPath}/bookings" class="btn btn-primary">My Bookings</a>
        <a href="${pageContext.request.contextPath}/wishlist" class="btn btn-primary">Wishlist</a>
    </div>

    <jsp:include page="/pages/includes/footer.jsp"/>
</div>
</body>
</html>