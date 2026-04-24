<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="top-header-bar">
    <div class="page-title-section">
        <h1>${pageTitle}</h1>
        <div class="breadcrumb-trail"><a href="${pageContext.request.contextPath}/admin/dashboard">Home</a> / ${pageTitle}</div>
    </div>
    <div class="admin-profile-section">
        <div class="profile-information">
            <div class="profile-name">${sessionScope.adminName}</div>
            <div class="profile-role">${sessionScope.adminRole}</div>
        </div>
        <div class="profile-avatar">${sessionScope.adminInitial}</div>
    </div>
</div>