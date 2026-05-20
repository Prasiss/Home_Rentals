<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<c:set var="active" value="${param.active}" />

<c:if test="${empty active}">
    <c:set var="active" value="${activePage}" />
</c:if>

<c:if test="${empty active}">
    <c:set var="active" value="dashboard" />
</c:if>

<div class="sidebar">

    <div class="sidebar-brand">
        <h3><i class="fa-solid fa-house"></i> HomeRental</h3>
        <p>Admin Panel</p>
    </div>

    <ul class="sidebar-nav">

        <li>
            <a href="${pageContext.request.contextPath}/admindashboard"
               class="${active == 'dashboard' ? 'active' : ''}">
                <i class="fa-solid fa-chart-line"></i>
                <span>Dashboard</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/users"
               class="${active == 'users' ? 'active' : ''}">
                <i class="fa-solid fa-users"></i>
                <span>Manage Users</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/dealers"
               class="${active == 'dealers' ? 'active' : ''}">
                <i class="fa-solid fa-user-tie"></i>
                <span>Manage Dealers</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/properties"
               class="${active == 'properties' ? 'active' : ''}">
                <i class="fa-solid fa-building"></i>
                <span>Property Approvals</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/analytics"
               class="${active == 'analytics' ? 'active' : ''}">
                <i class="fa-solid fa-chart-bar"></i>
                <span>Analytics</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/profile"
               class="${active == 'profile' ? 'active' : ''}">
                <i class="fa-solid fa-user-gear"></i>
                <span>Edit Profile</span>
            </a>
        </li>

    </ul>

    <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/logout">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Sign Out</span>
        </a>
    </div>

</div>