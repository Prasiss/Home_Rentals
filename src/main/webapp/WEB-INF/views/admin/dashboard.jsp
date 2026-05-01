<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    request.setAttribute("activePage", "dashboard");
    request.setAttribute("pageTitle", "Dashboard");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/views/admin/header.jsp"/>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-number"><fmt:formatNumber value="${stats.totalRevenue}" type="currency" currencySymbol="$"/></div>
                <div class="stat-label">Total Revenue</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${stats.activeProperties}</div>
                <div class="stat-label">Active Properties</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${stats.totalUsers}</div>
                <div class="stat-label">Total Users</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${stats.activeDealers}</div>
                <div class="stat-label">Active Dealers</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${stats.pendingUsers}</div>
                <div class="stat-label">Pending Approvals</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${stats.pendingProperties}</div>
                <div class="stat-label">Pending Properties</div>
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <h2>Recent Users</h2>
                <a href="${pageContext.request.contextPath}/admin/users">View All</a>
            </div>
            <table class="data-table">
                <thead>
                    <tr><th>Name</th><th>Email</th><th>Phone</th><th>Bookings</th><th>Status</th></tr>
                </thead>
                <tbody>
                    <c:forEach items="${recentUsers}" var="user">
                    <tr>
                        <td><c:out value="${user.fullName}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.phone}"/></td>
                        <td><c:out value="${user.totalBookings}"/></td>
                        <td><span class="badge ${user.status == 'ACTIVE' ? 'badge-success' : 'badge-warning'}"><c:out value="${user.status}"/></span></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="card">
            <div class="card-header">
                <h2>Pending Property Approvals</h2>
                <a href="${pageContext.request.contextPath}/admin/properties">View All</a>
            </div>
            <table class="data-table">
                <thead>
                    <tr><th>Property</th><th>Owner</th><th>Price/Month</th><th>Location</th><th>Action</th></tr>
                </thead>
                <tbody>
                    <c:forEach items="${pendingProperties}" var="prop">
                    <tr>
                        <td><c:out value="${prop.title}"/></td>
                        <td><c:out value="${prop.ownerName}"/></td>
                        <td><fmt:formatNumber value="${prop.pricePerMonth}" type="currency" currencySymbol="$"/></td>
                        <td><c:out value="${prop.location}"/></td>
                        <td>
                            <form method="post" style="display:inline;">
                                <input type="hidden" name="propertyId" value="${prop.propertyId}">
                                <button type="submit" name="action" value="approveProperty" class="btn btn-success">Approve</button>
                                <button type="submit" name="action" value="rejectProperty" class="btn btn-danger">Reject</button>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <jsp:include page="/WEB-INF/views/admin/footer.jsp"/>
    </div>
</div>
</body>
</html>