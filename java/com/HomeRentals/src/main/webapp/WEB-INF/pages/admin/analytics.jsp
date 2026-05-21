<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Analytics - HomeRental</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/analytics.css">
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/pages/admin/admin_template/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/admin/admin_template/header.jsp"/>

        <%-- ── Download toolbar — EL/JSTL only, zero scriptlets ── --%>
        <div class="an-download-bar">
            <span class="an-download-label">&#128196; Export Report:</span>
            <a class="an-btn-download an-btn-csv"
               href="${pageContext.request.contextPath}/admin/analytics/download?format=csv">
                &#8595; Download CSV
            </a>
            <a class="an-btn-download an-btn-pdf"
               href="${pageContext.request.contextPath}/admin/analytics/download?format=pdf"
               target="_blank">
                &#128438; Save as PDF
            </a>
        </div>

        <div class="stats-grid an-grid-7">
            <div class="stat-card">
                <div class="stat-number">
                    <fmt:formatNumber value="${totalRevenue}" type="currency" currencySymbol="$" maxFractionDigits="0"/>
                </div>
                <div class="stat-label">Total Revenue</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">
                    <fmt:formatNumber value="${avgRevenue}" type="currency" currencySymbol="$" maxFractionDigits="0"/>
                </div>
                <div class="stat-label">Avg. Per Booking</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${totalUsers}</div>
                <div class="stat-label">Total Users</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${totalDealers}</div>
                <div class="stat-label">Active Dealers</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${activeProperties}</div>
                <div class="stat-label">Active Properties</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${totalBookings}</div>
                <div class="stat-label">Total Bookings</div>
            </div>
            <div class="stat-card">
                <div class="stat-number">${completedBookings}</div>
                <div class="stat-label">Completed Bookings</div>
            </div>
        </div>

  
        <div class="an-row-2">
            <div class="card an-chart-card">
                <div class="card-header">
                    <h2>Revenue Over Time</h2>
                    <span class="an-subtitle">Last 12 months (completed bookings)</span>
                </div>
                <c:choose>
                    <c:when test="${empty monthlyRevenue}">
                        <p class="no-data">No revenue data available yet.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="bar-chart">
                            <c:forEach items="${monthlyRevenue}" var="row">
                                <div class="bar-row">
                                    <span class="bar-label">${row[0]}</span>
                                    <div class="bar-track">
                                        <div class="bar-fill bar-fill-primary" style="width:${row[2]}%"></div>
                                    </div>
                                    <span class="bar-value">
                                        $<fmt:formatNumber value="${row[1]}" pattern="#,##0"/>
                                    </span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <%-- Monthly Bookings Bar Chart --%>
            <div class="card an-chart-card">
                <div class="card-header">
                    <h2>Bookings Over Time</h2>
                    <span class="an-subtitle">Last 12 months (all bookings)</span>
                </div>
                <c:choose>
                    <c:when test="${empty monthlyBookings}">
                        <p class="no-data">No booking data available yet.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="bar-chart">
                            <c:forEach items="${monthlyBookings}" var="row">
                                <div class="bar-row">
                                    <span class="bar-label">${row[0]}</span>
                                    <div class="bar-track">
                                        <div class="bar-fill bar-fill-success" style="width:${row[2]}%"></div>
                                    </div>
                                    <span class="bar-value">${row[1]}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="an-row-2">
            <div class="card an-chart-card">
                <div class="card-header">
                    <h2>Users Joined Over Time</h2>
                    <span class="an-subtitle">Last 12 months</span>
                </div>
                <c:choose>
                    <c:when test="${empty monthlyUsers}">
                        <p class="no-data">No user registration data available yet.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="bar-chart">
                            <c:forEach items="${monthlyUsers}" var="row">
                                <div class="bar-row">
                                    <span class="bar-label">${row[0]}</span>
                                    <div class="bar-track">
                                        <div class="bar-fill bar-fill-info" style="width:${row[2]}%"></div>
                                    </div>
                                    <span class="bar-value">${row[1]}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card an-chart-card">
                <div class="card-header">
                    <h2>Properties Listed Over Time</h2>
                    <span class="an-subtitle">Last 12 months</span>
                </div>
                <c:choose>
                    <c:when test="${empty monthlyProperties}">
                        <p class="no-data">No property data available yet.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="bar-chart">
                            <c:forEach items="${monthlyProperties}" var="row">
                                <div class="bar-row">
                                    <span class="bar-label">${row[0]}</span>
                                    <div class="bar-track">
                                        <div class="bar-fill bar-fill-warning" style="width:${row[2]}%"></div>
                                    </div>
                                    <span class="bar-value">${row[1]}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="an-row-3">

            <%-- Booking Status Breakdown --%>
            <div class="card">
                <div class="card-header">
                    <h2>Booking Status</h2>
                </div>
                <c:choose>
                    <c:when test="${empty bookingStatus}">
                        <p class="no-data">No booking records found.</p>
                    </c:when>
                    <c:otherwise>
                        <c:set var="bookingTotal" value="0"/>
                        <c:forEach items="${bookingStatus}" var="row">
                            <c:set var="bookingTotal" value="${bookingTotal + row[1]}"/>
                        </c:forEach>
                        <div class="breakdown-list">
                            <c:forEach items="${bookingStatus}" var="row">
                                <div class="breakdown-row">
                                    <div class="breakdown-top">
                                        <span class="breakdown-label
                                            <c:choose>
                                                <c:when test="${row[0] == 'COMPLETED'}">bd-success</c:when>
                                                <c:when test="${row[0] == 'CANCELLED'}">bd-danger</c:when>
                                                <c:otherwise>bd-warning</c:otherwise>
                                            </c:choose>
                                        ">${row[0]}</span>
                                        <span class="breakdown-count">${row[1]} &nbsp;
                                            <span class="breakdown-pct">(<fmt:formatNumber value="${row[1] * 100 / (bookingTotal > 0 ? bookingTotal : 1)}" pattern="#0.0"/>%)</span>
                                        </span>
                                    </div>
                                    <div class="bar-track-sm">
                                        <div class="bar-fill
                                            <c:choose>
                                                <c:when test="${row[0] == 'COMPLETED'}">bar-fill-success</c:when>
                                                <c:when test="${row[0] == 'CANCELLED'}">bar-fill-danger</c:when>
                                                <c:otherwise>bar-fill-warning</c:otherwise>
                                            </c:choose>
                                        " style="width:${row[2]}%"></div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="breakdown-total">Total: ${bookingTotal} bookings</div>
                    </c:otherwise>
                </c:choose>
            </div>

            <%-- Property Approval Breakdown --%>
            <div class="card">
                <div class="card-header">
                    <h2>Property Approvals</h2>
                </div>
                <c:choose>
                    <c:when test="${empty propertyStatus}">
                        <p class="no-data">No property records found.</p>
                    </c:when>
                    <c:otherwise>
                        <c:set var="propTotal" value="0"/>
                        <c:forEach items="${propertyStatus}" var="row">
                            <c:set var="propTotal" value="${propTotal + row[1]}"/>
                        </c:forEach>
                        <div class="breakdown-list">
                            <c:forEach items="${propertyStatus}" var="row">
                                <div class="breakdown-row">
                                    <div class="breakdown-top">
                                        <span class="breakdown-label
                                            <c:choose>
                                                <c:when test="${row[0] == 'APPROVED'}">bd-success</c:when>
                                                <c:when test="${row[0] == 'REJECTED'}">bd-danger</c:when>
                                                <c:otherwise>bd-warning</c:otherwise>
                                            </c:choose>
                                        ">${row[0]}</span>
                                        <span class="breakdown-count">${row[1]} &nbsp;
                                            <span class="breakdown-pct">(<fmt:formatNumber value="${row[1] * 100 / (propTotal > 0 ? propTotal : 1)}" pattern="#0.0"/>%)</span>
                                        </span>
                                    </div>
                                    <div class="bar-track-sm">
                                        <div class="bar-fill
                                            <c:choose>
                                                <c:when test="${row[0] == 'APPROVED'}">bar-fill-success</c:when>
                                                <c:when test="${row[0] == 'REJECTED'}">bar-fill-danger</c:when>
                                                <c:otherwise>bar-fill-warning</c:otherwise>
                                            </c:choose>
                                        " style="width:${row[2]}%"></div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="breakdown-total">Total: ${propTotal} properties</div>
                    </c:otherwise>
                </c:choose>
            </div>

            <%-- Top Locations --%>
            <div class="card">
                <div class="card-header">
                    <h2>Top Locations</h2>
                    <span class="an-subtitle">By approved listings</span>
                </div>
                <c:choose>
                    <c:when test="${empty topLocations}">
                        <p class="no-data">No location data available.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="bar-chart">
                            <c:forEach items="${topLocations}" var="loc" varStatus="st">
                                <div class="bar-row">
                                    <span class="bar-label loc-label">
                                        <span class="loc-rank">${st.index + 1}</span>
                                        ${loc[0]}
                                    </span>
                                    <div class="bar-track">
                                        <div class="bar-fill bar-fill-primary" style="width:${loc[2]}%"></div>
                                    </div>
                                    <span class="bar-value">${loc[1]}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="card an-user-mix">
            <div class="card-header">
                <h2>User Mix</h2>
                <span class="an-subtitle">Registered accounts by role</span>
            </div>
            <div class="user-mix-grid">
                <div class="mix-item">
                    <div class="mix-circle mix-circle-user">${totalRegularUsers}</div>
                    <div class="mix-label">Regular Users</div>
                </div>
                <div class="mix-item">
                    <div class="mix-circle mix-circle-dealer">${totalDealers}</div>
                    <div class="mix-label">Dealers</div>
                </div>
                <div class="mix-item">
                    <div class="mix-circle mix-circle-total">${totalUsers}</div>
                    <div class="mix-label">All Accounts</div>
                </div>
                <%-- Simple visual ratio bar --%>
                <c:if test="${totalUsers > 0}">
                    <div class="mix-bar-wrap">
                        <div class="mix-bar-track">
                            <div class="mix-bar-users"  style="width:${totalRegularUsers * 100 / totalUsers}%"></div>
                            <div class="mix-bar-dealers" style="width:${totalDealers     * 100 / totalUsers}%"></div>
                        </div>
                        <div class="mix-legend">
                            <span class="legend-dot legend-dot-user"></span> Users &nbsp;
                            <span class="legend-dot legend-dot-dealer"></span> Dealers
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <jsp:include page="/WEB-INF/pages/admin/admin_template/footer.jsp"/>
    </div>
</div>
</body>
</html>
