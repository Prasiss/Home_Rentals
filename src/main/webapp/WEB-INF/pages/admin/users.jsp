<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Users - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin.css">
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/pages/admin/admin_template/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/admin/admin_template/header.jsp"/>

        <c:if test="${not empty successMsg}">
            <div style="background:#d4edda;color:#155724;padding:10px;border-radius:4px;margin-bottom:12px;">${successMsg}</div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div style="background:#f8d7da;color:#721c24;padding:10px;border-radius:4px;margin-bottom:12px;">${errorMsg}</div>
        </c:if>

        <div class="card">
            <div class="card-header"><h2>All Registered Users</h2></div>
            <c:choose>
                <c:when test="${not empty userList}">
                    <table class="data-table">
                        <thead>
                            <tr><th>Name</th><th>Email</th><th>Phone</th><th>Status</th><th>Actions</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userList}" var="user">
                            <tr>
                                <td><c:out value="${user.fullName}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.number}"/></td>
                                <td><span class="badge ${user.isApproved == 1 ? 'badge-success' : 'badge-warning'}">${user.isApproved == 1 ? 'Active' : 'Pending'}</span></td>
                                <td>—</td>
                                <td>
                                    <%-- Approve — only shown when PENDING --%>
                                    <c:if test="${user.isApproved == 0}">
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/admin/users"
                                              style="display:inline;">
                                            <input type="hidden" name="userId" value="${user.userId}">
                                            <button type="submit" name="action" value="approveUser"
                                                    class="btn btn-success">Approve</button>
                                        </form>
                                    </c:if>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/users"
                                          style="display:inline;">
                                        <input type="hidden" name="userId" value="${user.userId}">
                                        <button type="submit" name="action" value="deleteUser"
                                                class="btn btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No users found.</p>
                </c:otherwise>
            </c:choose>
        </div>
        <jsp:include page="/WEB-INF/pages/admin/admin_template/footer.jsp"/>
    </div>
</div>
</body>
</html>
