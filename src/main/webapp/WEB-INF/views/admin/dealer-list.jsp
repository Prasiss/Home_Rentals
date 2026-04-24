<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Dealers - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/administration.css">
</head>
<body>
<div class="administration-wrapper">
    <c:set var="activePage" value="dealers" scope="request"/>
    <jsp:include page="/components/admin-navigation.jsp"/>
    <div class="main-content-area">
        <c:set var="pageTitle" value="Manage Dealers" scope="request"/>
        <jsp:include page="/components/admin-header.jsp"/>
        
        <div class="statistics-grid">
            <div class="statistic-card"><div class="statistic-value">${dealerList.size()}</div><div class="statistic-label">Total Dealers</div></div>
        </div>
        
        <div class="content-card">
            <div class="card-header-section">
                <h2 class="card-title">All Registered Dealers</h2>
            </div>
            
            <c:choose>
                <c:when test="${not empty dealerList}">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Dealer ID</th>
                                <th>Full Name</th>
                                <th>Username</th>
                                <th>Email Address</th>
                                <th>Phone Number</th>
                                <th>Properties Listed</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dealerList}" var="dealer">
                                <tr>
                                    <td>#<c:out value="${dealer.userNo}"/></td>
                                    <td><strong><c:out value="${dealer.fullName}"/></strong></td>
                                    <td><c:out value="${dealer.username}"/></td>
                                    <td><c:out value="${dealer.email}"/></td>
                                    <td><c:out value="${dealer.phone}"/></td>
                                    <td><c:out value="${dealer.totalBookings}"/></td>
                                    <td><span class="status-indicator active">Active</span></td>
                                    <td>
                                        <form method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to suspend this dealer?');">
                                            <input type="hidden" name="userNo" value="${dealer.userNo}">
                                            <input type="hidden" name="currentFilter" value="${currentFilter}">
                                            <button type="submit" name="action" value="suspendDealer" class="action-button danger">Suspend</button>
                                        </form>
                                        <form method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this dealer?');">
                                            <input type="hidden" name="userNo" value="${dealer.userNo}">
                                            <input type="hidden" name="currentFilter" value="${currentFilter}">
                                            <button type="submit" name="action" value="deleteDealer" class="action-button" style="background:#dc3545;color:#fff;border-color:#dc3545;">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p style="text-align:center;padding:40px;color:#777;">No dealers found in the system.</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <jsp:include page="/components/admin-footer.jsp"/>
    </div>
</div>
</body>
</html>