<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="servletPath" value="${pageContext.request.servletPath}" />
<c:set var="username" value="${sessionScope.username}" />

<link rel="stylesheet"
 href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<link rel="stylesheet"
 href="${pageContext.request.contextPath}/css/header.css">
<header>

    <!-- LOGO -->
    <div class="logo">
        <a href="${pageContext.request.contextPath}/home">
            <img src="${pageContext.request.contextPath}/image/site-logo.png"
                 alt="Villare Homes">
        </a>
    </div>

    <!-- NAV -->
    <nav>
        <div class="navigation">
        <div class="nav-left"></div>
            <!-- RIGHT NAV -->
            <div class="nav-right">
            <a href="${pageContext.request.contextPath}/search"
                   class="icon-link">
                    <i class="fas fa-search"></i>
                </a>
            <a href="${pageContext.request.contextPath}/home"
                   class="link ${servletPath == '/home' || servletPath == '/' ? 'active' : ''}">
                    Home
                </a>

                <a href="${pageContext.request.contextPath}/rentals"
                   class="link ${servletPath == '/rentals' ? 'active' : ''}">
                    Rentals
                </a>

                <a href="${pageContext.request.contextPath}/aboutus"
                   class="link ${servletPath == '/aboutus' ? 'active' : ''}">
                    About
                </a>

                <a href="${pageContext.request.contextPath}/contactus"
                   class="link ${servletPath == '/contactus' ? 'active' : ''}">
                    Contact
                </a>
                <c:choose>

                    <c:when test="${not empty username}">

                        <a href="${pageContext.request.contextPath}/wishlist"
                           class="icon-link badge-wrap">
                            <i class="fas fa-heart"></i>
                            <span class="badge">0</span>
                        </a>

                        <a href="${pageContext.request.contextPath}/addtocart"
                           class="icon-link badge-wrap">
                            <i class="fas fa-shopping-cart"></i>
                            <span class="badge">0</span>
                        </a>

                        <div class="user-dropdown">
                            <div class="user-nav-profile">
                                <img src="${pageContext.request.contextPath}/image/default-user.png"
                                     alt="User">
                                <span>${username}</span>
                                <i class="fa-solid fa-chevron-down"></i>
                            </div>

                            <div class="dropdown-menu">
                                <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                            </div>
                        </div>

                    </c:when>

                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login"
                           class="link ${servletPath == '/login' ? 'active' : ''}">
                            Login
                        </a>
                    </c:otherwise>

                </c:choose>

            </div>

        </div>
    </nav>

</header>
