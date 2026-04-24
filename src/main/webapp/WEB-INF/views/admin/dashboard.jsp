<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/administration.css">
</head>
<body>
<div class="administration-wrapper">
    <c:set var="activePage" value="dashboard" scope="request"/>
    <jsp:include page="/components/admin-navigation.jsp"/>
    <div class="main-content-area">
        <c:set var="pageTitle" value="Dashboard" scope="request"/>
        <jsp:include page="/components/admin-header.jsp"/>
        
        <div class="statistics-grid">
            <div class="statistic-card"><div class="statistic-value"><fmt:formatNumber value="${dashboardSummary.totalRevenue}" type="currency" currencySymbol="$"/></div><div class="statistic-label">Total Revenue</div></div>
            <div class="statistic-card"><div class="statistic-value">${dashboardSummary.activeProperties}</div><div class="statistic-label">Active Properties</div></div>
            <div class="statistic-card"><div class="statistic-value">${dashboardSummary.totalUsers}</div><div class="statistic-label">Total Users</div></div>
            <div class="statistic-card"><div class="statistic-value">${dashboardSummary.activeDealers}</div><div class="statistic-label">Active Dealers</div></div>
            <div class="statistic-card"><div class="statistic-value">${dashboardSummary.pendingApplications}</div><div class="statistic-label">Pending Applications</div></div>
            <div class="statistic-card"><div class="statistic-value">${dashboardSummary.pendingProperties}</div><div class="statistic-label">Pending Properties</div></div>
        </div>
        
        <div class="content-card">
            <div class="card-header-section"><h2 class="card-title">Recent Users</h2><a href="${pageContext.request.contextPath}/admin/users" class="view-all-link">View All</a></div>
            <table class="data-table"><thead><tr><th>Name</th><th>Email</th><th>Phone</th><th>Bookings</th></tr></thead><tbody>
            <c:forEach items="${recentUsersList}" var="u"><tr><td><c:out value="${u.fullName}"/></td><td><c:out value="${u.email}"/></td><td><c:out value="${u.phone}"/></td><td><c:out value="${u.totalBookings}"/></td></tr></c:forEach>
            </tbody></table>
        </div>
        
        <div class="content-card">
            <div class="card-header-section"><h2 class="card-title">Recent Dealers</h2><a href="${pageContext.request.contextPath}/admin/dealers" class="view-all-link">View All</a></div>
            <table class="data-table"><thead><tr><th>Name</th><th>Email</th><th>Phone</th><th>Properties</th></tr></thead><tbody>
            <c:forEach items="${recentDealersList}" var="d"><tr><td><c:out value="${d.fullName}"/></td><td><c:out value="${d.email}"/></td><td><c:out value="${d.phone}"/></td><td><c:out value="${d.totalBookings}"/></td></tr></c:forEach>
            </tbody></table>
        </div>
        
        <div class="content-card">
            <div class="card-header-section"><h2 class="card-title">Pending Properties</h2><a href="${pageContext.request.contextPath}/admin/properties" class="view-all-link">View All</a></div>
            <table class="data-table"><thead><tr><th>Title</th><th>Dealer</th><th>Price</th><th>Location</th><th>Action</th></tr></thead><tbody>
            <c:forEach items="${pendingPropertiesList}" var="p"><tr><td><c:out value="${p.title}"/></td><td><c:out value="${p.dealerName}"/></td><td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/></td><td><c:out value="${p.location}"/></td><td><form method="post"><input type="hidden" name="propertyNo" value="${p.propertyNo}"><button type="submit" name="action" value="approveProperty" class="action-button success">Approve</button><button type="submit" name="action" value="rejectProperty" class="action-button danger">Reject</button></form></td></tr></c:forEach>
            </tbody></table>
        </div>
        
        <jsp:include page="/components/admin-footer.jsp"/>
    </div>
</div>
</body>
</html>