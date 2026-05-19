<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">

<%@ include file="/WEB-INF/pages/client/client_template/header.jsp" %>

<main>

    <div class="layout">

        <section class="main-content">

            <h2>Checkout</h2>

            <c:set var="total" value="0" />

            <c:if test="${not empty sessionScope.cart}">

                <!-- Order Summary -->
                <h3>Order Summary</h3>

                <c:forEach var="item" items="${sessionScope.cart}">
                    <div class="card">
                        <div class="card-body">
                            <h4>${item.name}</h4>
                            <p>Price: NPR ${item.price}</p>
                            <c:set var="total" value="${total + item.price}" />
                        </div>
                    </div>
                </c:forEach>

                <hr>

                <!-- Pricing -->
                <c:set var="discount" value="0" />

                <c:if test="${total > 20000000}">
                    <c:set var="discount" value="${total * 0.10}" />
                </c:if>

                <h4>Total: NPR ${total}</h4>
                <h4>Discount: NPR ${discount}</h4>
                <h3>Final Amount: NPR ${total - discount}</h3>

                <br>

                <!-- Customer Info Form -->
                <h3>Billing Details</h3>

                <form action="placeOrder" method="post">

                    <label>Full Name:</label><br>
                    <input type="text" name="fullname" required><br><br>

                    <label>Email:</label><br>
                    <input type="email" name="email" required><br><br>

                    <label>Phone:</label><br>
                    <input type="text" name="phone" required><br><br>

                    <label>Address:</label><br>
                    <textarea name="address" required></textarea><br><br>

                    <!-- Hidden values -->
                    <input type="hidden" name="total" value="${total}">
                    <input type="hidden" name="discount" value="${discount}">
                    <input type="hidden" name="finalAmount" value="${total - discount}">

                    <button type="submit">Place Order</button>

                </form>

            </c:if>

            <c:if test="${empty sessionScope.cart}">
                <h3>Your cart is empty. Cannot proceed to checkout.</h3>
                <a href="${pageContext.request.contextPath} /home">
                    <button>Go Back to Shopping</button>
                </a>
            </c:if>

        </section>

    </div>
    <div class="Qr">
    <img src=""></img></div>

</main>

<%@ include file="/WEB-INF/pages/client/client_template/footer.jsp" %>