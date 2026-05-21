<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="activePage" value="dashboard" scope="request"/>
<c:set var="pageTitle"  value="Dashboard"  scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - HomeRental Dealer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dealer.css">
</head>
<body>
<div class="dealer-wrapper">

    <jsp:include page="/WEB-INF/pages/dealer/dealer_template/sidebar.jsp"/>

    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/header.jsp"/>

        <c:if test="${not empty errorMessage}">
            <div class="alert-error">
                <i class="fa-solid fa-triangle-exclamation"></i> ${errorMessage}
            </div>
        </c:if>

        <div class="stats-grid">
            <div class="stat-card s-purple">
                <div class="stat-number">${stats.totalProperties}</div>
                <div class="stat-label">Total Properties</div>
            </div>
            <div class="stat-card s-green">
                <div class="stat-number">${stats.activeProperties}</div>
                <div class="stat-label">Active Listings</div>
            </div>
            <div class="stat-card s-orange">
                <div class="stat-number">${stats.pendingProperties}</div>
                <div class="stat-label">Pending Approval</div>
            </div>
            <div class="stat-card s-blue">
                <div class="stat-number">${stats.totalBookings}</div>
                <div class="stat-label">Total Bookings</div>
            </div>
            <div class="stat-card s-teal">
                <div class="stat-number">${stats.confirmedBookings}</div>
                <div class="stat-label">Confirmed</div>
            </div>
            <div class="stat-card s-green">
                <div class="stat-number">$${stats.totalRevenue}</div>
                <div class="stat-label">Total Revenue</div>
            </div>
        </div>

        <div class="section-grid">

            <div class="panel">
                <div class="panel-header">
                    <span class="panel-title">Recent Properties</span>
                    <a href="${pageContext.request.contextPath}/dealer/properties" class="panel-link">View All</a>
                </div>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Property</th>
                            <th>Price/mo</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty recentProperties}">
                                <tr><td colspan="3" style="text-align:center;color:var(--text-gray);padding:20px;">No properties yet.</td></tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="prop" items="${recentProperties}">
                                    <tr>
                                        <td>
                                            <div class="prop-title">${prop.title}</div>
                                            <div class="prop-location"><i class="fa-solid fa-location-dot"></i> ${prop.location}</div>
                                        </td>
                                        <td class="price">$${prop.pricePerMonth}</td>
                                        <td>
                                            <span class="badge ${prop.approvalStatus == 'APPROVED' ? 'badge-success' :
                                                                 prop.approvalStatus == 'PENDING'  ? 'badge-warning' : 'badge-danger'}">
                                                ${prop.approvalStatus}
                                            </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <div class="panel">
                <div class="panel-header">
                    <span class="panel-title">Recent Bookings</span>
                </div>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Property</th>
                            <th>Tenant</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty recentBookings}">
                                <tr><td colspan="3" style="text-align:center;color:var(--text-gray);padding:20px;">No bookings yet.</td></tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="bk" items="${recentBookings}">
                                    <tr>
                                        <td><strong>${bk.propertyTitle}</strong></td>
                                        <td>${bk.tenantName}</td>
                                        <td>
                                            <span class="badge ${bk.status == 'CONFIRMED' ? 'badge-success' :
                                                                 bk.status == 'PENDING'   ? 'badge-warning' : 'badge-danger'}">
                                                ${bk.status}
                                            </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

        </div>

        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/footer.jsp"/>
    </div>

</div>
</body>
</html>
