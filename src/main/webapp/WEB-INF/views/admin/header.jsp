<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.HomeRentals.model.UserModel" %>
<%
    String pageTitle = (String) request.getAttribute("pageTitle");
    if (pageTitle == null) pageTitle = "Dashboard";
    
    UserModel user = (UserModel) session.getAttribute("user");
    String userName = (user != null) ? user.getFullName() : "Administrator";
%>
<div class="top-header">
    <div>
        <h1><%= pageTitle %></h1>
        <p class="breadcrumb">Home / <%= pageTitle %></p>
    </div>
    <div>
        <span style="font-weight:600;"><%= userName %></span>
        <span style="font-size:12px;color:#777;margin-left:10px;">Administrator</span>
    </div>
</div>