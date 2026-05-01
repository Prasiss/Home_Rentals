<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String active = (String) request.getAttribute("activePage");
    if (active == null) active = "dashboard";
%>
<div class="sidebar">
    <div class="sidebar-brand">
        <h3>HomeRentals</h3>
        <p>Admin Panel</p>
    </div>
    <ul class="sidebar-nav">
        <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="<%= "dashboard".equals(active) ? "active" : "" %>">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/users" class="<%= "users".equals(active) ? "active" : "" %>">Manage Users</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/dealers" class="<%= "dealers".equals(active) ? "active" : "" %>">Manage Dealers</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/properties" class="<%= "properties".equals(active) ? "active" : "" %>">Property Approvals</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/profile" class="<%= "profile".equals(active) ? "active" : "" %>">Edit Profile</a></li>
    </ul>
    <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/login">Sign Out</a>
    </div>
</div>