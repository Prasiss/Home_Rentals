<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Dealers - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin.css">
    <style>
        .section-divider { display:flex; align-items:center; gap:12px; margin:32px 0 16px; color:#6c757d; font-size:13px; text-transform:uppercase; letter-spacing:0.05em; }
        .section-divider::before, .section-divider::after { content:''; flex:1; border-top:1px solid #dee2e6; }
        .badge-request { background:#fff3cd; color:#856404; border:1px solid #ffc107; padding:3px 10px; border-radius:12px; font-size:12px; font-weight:600; }
        .request-badge-count { display:inline-block; background:#dc3545; color:white; border-radius:50%; width:20px; height:20px; font-size:11px; line-height:20px; text-align:center; margin-left:6px; font-weight:bold; }
    </style>
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

        <%-- Pending Applications --%>
        <div class="card">
            <div class="card-header">
                <h2>Dealer Applications
                    <c:if test="${not empty dealerRequests}">
                        <span class="request-badge-count">${fn:length(dealerRequests)}</span>
                    </c:if>
                </h2>
                <span style="font-size:13px;color:#6c757d;">Users who applied to become dealers</span>
            </div>

            <c:choose>
                <c:when test="${not empty dealerRequests}">
                    <table class="data-table">
                        <thead>
                            <tr><th>Name</th><th>Email</th><th>Phone</th><th>Actions</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dealerRequests}" var="req">
                            <tr>
                                <td><c:out value="${req.fullName}"/></td>
                                <td><c:out value="${req.email}"/></td>
                                <td><c:out value="${req.number}"/></td>
                                <td>—</td>
                                <td>—</td>
                                <td>—</td>
                                <td>
                                    <%-- Approve --%>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/dealers"
                                          style="display:inline;">
                                        <input type="hidden" name="userId" value="${req.userId}">
                                        <button type="submit" name="action" value="approveDealerRequest"
                                                class="btn btn-success">&#10003; Approve</button>
                                    </form>
                                    <%-- Reject --%>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/dealers"
                                          style="display:inline;">
                                        <input type="hidden" name="userId" value="${req.userId}">
                                        <button type="submit" name="action" value="rejectDealerRequest"
                                                class="btn btn-danger">&#10007; Reject</button>
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No pending dealer applications.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <%--Active Dealers --%>
        <div class="section-divider">Active Dealers</div>
        <div class="card">
            <div class="card-header"><h2>All Registered Dealers</h2></div>
            <c:choose>
                <c:when test="${not empty dealerList}">
                    <table class="data-table">
                        <thead>
                            <tr><th>Name</th><th>Email</th><th>Phone</th><th>Status</th><th>Actions</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dealerList}" var="dealer">
                            <tr>
                                <td><c:out value="${dealer.fullName}"/></td>
                                <td><c:out value="${dealer.email}"/></td>
                                <td><c:out value="${dealer.number}"/></td>
                                <td>—</td>
                                <td><span class="badge ${dealer.isApproved == 1 ? 'badge-success' : 'badge-warning'}">${dealer.isApproved == 1 ? 'Active' : 'Pending'}</span></td>
                                <td>
                                    <c:if test="${dealer.isApproved == 0}">
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/admin/dealers"
                                              style="display:inline;">
                                            <input type="hidden" name="userId" value="${dealer.userId}">
                                            <button type="submit" name="action" value="approveDealer"
                                                    class="btn btn-success">Activate</button>
                                        </form>
                                    </c:if>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admin/dealers"
                                          style="display:inline;">
                                        <input type="hidden" name="userId" value="${dealer.userId}">
                                        <button type="submit" name="action" value="deleteDealer"
                                                class="btn btn-danger">Remove</button>
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No dealers found.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <jsp:include page="/WEB-INF/pages/admin/admin_template/footer.jsp"/>
    </div>
</div>
</body>
</html>
