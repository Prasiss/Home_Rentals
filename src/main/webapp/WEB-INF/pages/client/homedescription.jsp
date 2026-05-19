<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/homedescription.css">

<%@ include file="/WEB-INF/pages/client/client_template/header.jsp" %>

<main>

<div class="layout">

<section class="main-content">

<c:if test="${not empty home}">

    <div class="card" style="max-width:700px; margin:auto;">
        <img src="${pageContext.request.contextPath}/image/home.png">

        <div class="card-body">

            <h2>${home.name}</h2>

            <p><i class="fa-solid fa-location-dot"></i> ${home.location}</p>
            <p> <i class="fa-solid fa-money-bill-wave"></i>NPR ${home.price}</p>

            <p class="desc">${home.description}</p>

            <hr>

            <h3>Owner Details</h3>

            <c:if test="${not empty owner}">
                <p>Name: ${owner.fullName}</p>
                <p>Email: ${owner.email}</p>
                <p>Phone: ${owner.number}</p>
            </c:if>

            <hr>

            <form action="${pageContext.request.contextPath}/addtocart" method="post">
                <input type="hidden" name="id" value="${home.id}">
                <button type="submit">Book Now</button>
            </form>

        </div>
    </div>

</c:if>

<c:if test="${empty home}">

    <h3 style="text-align:center;">Property not found</h3>

    <h2 style="text-align:center; margin-top:20px;">Browse Available Homes</h2>
    <button ><a href="${pageContext.request.contextPath}/home">Go back to Home Page </a></button>

    <div style="
        display:flex;
        gap:20px;
        justify-content:center;
        flex-wrap:wrap;
        margin-top:20px;
    ">

        <c:forEach var="h" items="${suggestedHomes}">

            <div class="card" style="width:250px;">

                <img src="${pageContext.request.contextPath}/image/home.png">

                <div class="card-body">

                    <h4>${h.name}</h4>

                    <p><i class="fa-solid fa-location-dot"></i>${h.location}</p>
                    <p><i class="fa-solid fa-money-bill-wave"></i> NPR ${h.price}</p>

                    <p class="desc">
                        ${fn:substring(h.description, 0, 70)}...
                    </p>

                    <form action="${pageContext.request.contextPath}/homedescription" method="get">
                        <input type="hidden" name="id" value="${h.id}">
                        <button type="submit">View Details</button>
                    </form>

                </div>
            </div>

        </c:forEach>

    </div>

</c:if>

</section>

</div>

</main>

<%@ include file="/WEB-INF/pages/client/client_template/footer.jsp" %>