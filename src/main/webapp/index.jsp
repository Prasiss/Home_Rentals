<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<%@ include file="/WEB-INF/pages/client/client_template/header.jsp" %>

<main>

<div class="layout">

    <!-- LEFT SIDE -->
    <section class="main-content">

        <h2>Available For Rent</h2>

        <div class="cards">

            <!-- LOOP START -->
            <c:forEach var="home" items="${homes}">

                <!-- FULL CARD CLICKABLE -->
                <a href="${pageContext.request.contextPath}/homedescription?id=${home.id}" style="text-decoration:none; color:inherit;">

                    <div class="card">

                        <img src="${pageContext.request.contextPath}/getimage?id=${home.id}">

                        <div class="card-body">

                            <h3>${home.name}</h3>

                            <p> ${home.location}</p>

                            <p> <i class="fa-solid fa-money-bill-wave"></i> NPR ${home.price}</p>

                            <div class="stars">⭐⭐⭐⭐</div>

                            <p class="desc">${home.description}</p>

                        </div>

                    </div>

                </a>

            </c:forEach>
            <!-- LOOP END -->

            <!-- EMPTY STATE -->
            <c:if test="${empty homes}">
                <p>No properties available.</p>
            </c:if>

        </div>

        <h2>More Options</h2>

        <div class="cards small">

            <div class="card">
                <img src="${pageContext.request.contextPath}/image/home.png">
                <div class="card-body">
                    <h3>Luxury Villa</h3>
                    <p><i class="fa-solid fa-location-dot"></i>Kathmandu</p>
                </div>
            </div>

            <div class="card">
                <img src="${pageContext.request.contextPath}/image/home.png">
                <div class="card-body">
                    <h3>Apartment</h3>
                    <p> <i class="fa-solid fa-location-dot"></i>Lalitpur</p>
                </div>
            </div>

            <div class="card">
                <img src="${pageContext.request.contextPath}/image/home.png">
                <div class="card-body">
                    <h3>Modern Flat</h3>
                    <p><i class="fa-solid fa-location-dot"></i>Bhaktapur</p>
                </div>
            </div>

        </div>

    </section>

    <!-- RIGHT SIDEBAR -->
    <aside class="sidebar">

        <h3>Customer Favorites</h3>

        <div class="sidebar-card">
            <img src="${pageContext.request.contextPath}/image/home.png">
        </div>

        <div class="sidebar-card">
            <img src="${pageContext.request.contextPath}/image/home.png">
        </div>

        <div class="sidebar-card">
            <img src="${pageContext.request.contextPath}/image/home.png">
        </div>

        <div class="sidebar-card">
            <img src="${pageContext.request.contextPath}/image/home.png">
        </div>

    </aside>

</div>

</main>

<%@ include file="/WEB-INF/pages/client/client_template/footer.jsp" %>