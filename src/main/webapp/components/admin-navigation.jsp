<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navigation-sidebar">
    <div class="brand-section">
        <div class="brand-name">HomeRentals</div>
        <div class="brand-tagline">Administration Panel</div>
    </div>
    <ul class="navigation-list">
        <li class="navigation-item"><a href="${pageContext.request.contextPath}/admin/dashboard" class="navigation-link ${param.activePage == 'dashboard' ? 'active' : ''}">Dashboard</a></li>
        <li class="navigation-item"><a href="${pageContext.request.contextPath}/admin/users" class="navigation-link ${param.activePage == 'users' ? 'active' : ''}">Manage Users</a></li>
        <li class="navigation-item"><a href="${pageContext.request.contextPath}/admin/dealers" class="navigation-link ${param.activePage == 'dealers' ? 'active' : ''}">Manage Dealers</a></li>
        <li class="navigation-item"><a href="${pageContext.request.contextPath}/admin/properties" class="navigation-link ${param.activePage == 'properties' ? 'active' : ''}">Property Approvals</a></li>
        <li class="navigation-item"><a href="${pageContext.request.contextPath}/admin/applications" class="navigation-link ${param.activePage == 'applications' ? 'active' : ''}">Dealer Applications</a></li>
    </ul>
    <div class="navigation-footer"><a href="${pageContext.request.contextPath}/logout" class="logout-button">Sign Out</a></div>
</div>