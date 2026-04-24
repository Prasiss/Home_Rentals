<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/administration.css">
</head>
<body>
<div class="administration-wrapper">
    <c:set var="activePage" value="users" scope="request"/>
    <jsp:include page="/components/admin-navigation.jsp"/>
    <div class="main-content-area">
        <c:set var="pageTitle" value="Manage Users" scope="request"/>
        <jsp:include page="/components/admin-header.jsp"/>
        
        <div class="statistics-grid">
            <div class="statistic-card"><div class="statistic-value">${userList.size()}</div><div class="statistic-label">Total Users</div></div>
        </div>
        
        <div class="content-card">
            <div class="card-header-section">
                <h2 class="card-title">All Registered Users</h2>
            </div>
            
            <c:choose>
                <c:when test="${not empty userList}">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Username</th>
                                <th>Email Address</th>
                                <th>Phone Number</th>
                                <th>Role</th>
                                <th>Bookings</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userList}" var="user">
                                <tr>
                                    <td>#<c:out value="${user.userNo}"/></td>
                                    <td><strong><c:out value="${user.fullName}"/></strong></td>
                                    <td><c:out value="${user.username}"/></td>
                                    <td><c:out value="${user.email}"/></td>
                                    <td><c:out value="${user.phone}"/></td>
                                    <td><span class="status-indicator active"><c:out value="${user.role}"/></span></td>
                                    <td><c:out value="${user.totalBookings}"/></td>
                                    <td>
                                        <form method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to suspend this user?');">
                                            <input type="hidden" name="userNo" value="${user.userNo}">
                                            <input type="hidden" name="currentFilter" value="${currentFilter}">
                                            <button type="submit" name="action" value="suspendUser" class="action-button danger">Suspend</button>
                                        </form>
                                        <form method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this user? This cannot be undone.');">
                                            <input type="hidden" name="userNo" value="${user.userNo}">
                                            <input type="hidden" name="currentFilter" value="${currentFilter}">
                                            <button type="submit" name="action" value="deleteUser" class="action-button" style="background:#dc3545;color:#fff;border-color:#dc3545;">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p style="text-align:center;padding:40px;color:#777;">No users found in the system.</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <jsp:include page="/components/admin-footer.jsp"/>
    </div>
</div>
</body>
</html>