<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<div class="sidebar">

    <div class="sidebar-brand">
        <h3><i class="fa-solid fa-house"></i> HomeRental</h3>
        <p>Dealer Panel</p>
    </div>

    <ul class="sidebar-nav">

        <li>
            <a href="${pageContext.request.contextPath}/dealer/dashboard"
               class="${activePage == 'dashboard' ? 'active' : ''}">
                <i class="fa-solid fa-chart-line"></i>
                <span>Dashboard</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/dealer/properties"
               class="${activePage == 'properties' ? 'active' : ''}">
                <i class="fa-solid fa-building"></i>
                <span>My Properties</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/dealer/add-property"
               class="${activePage == 'add-property' ? 'active' : ''}">
                <i class="fa-solid fa-plus-circle"></i>
                <span>Add Property</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/profile"
               class="${activePage == 'profile' ? 'active' : ''}">
                <i class="fa-solid fa-user"></i>
                <span>Profile</span>
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
