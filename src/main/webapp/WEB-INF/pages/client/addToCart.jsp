<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

<%@ include file="/WEB-INF/pages/client/client_template/header.jsp" %>

<main>

    <div class="layout">

        <section class="main-content">

            <h2>Your Cart</h2>

            <c:if test="${empty cart}">
                <p>Your cart is empty</p>
            </c:if>

            <c:set var="total" value="0" />

            <c:forEach var="home" items="${cart}">

                <div class="card">

                    <h3>${home.name}</h3>
                    <p>${home.location}</p>
                    <p>${home.price}</p>

                </div>


                <c:set var="total" value="${total + home.price}" />

            </c:forEach>

            <hr>

            <h3>Total: NPR ${total}</h3>

            <c:set var="discount" value="0" />

            <c:if test="${total > 20000000}">
                <c:set var="discount" value="${total * 0.10}" />
            </c:if>

            <h4>Discount: NPR ${discount}</h4>

            <h2>Final Price: NPR ${total - discount}</h2>

            <br>

            <a href="${pageContext.request.contextPath}/checkout">
                <button>Proceed to Checkout</button>
            </a>

        </section>

    </div>

</main>

<%@ include file="/WEB-INF/pages/client/client_template/footer.jsp" %>