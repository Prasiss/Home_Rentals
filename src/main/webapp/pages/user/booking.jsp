<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bookings - HomeRentals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>
<jsp:include page="/pages/includes/sidebar.jsp"/>
<div class="main-content">
    <jsp:include page="/pages/includes/header.jsp"/>

    <h1 style="font-size:26px;margin-bottom:20px;">My Bookings</h1>

    <div class="filter-bar">
        <a href="${pageContext.request.contextPath}/bookings" class="active">All</a>
        <a href="${pageContext.request.contextPath}/bookings?status=PENDING">Pending</a>
        <a href="${pageContext.request.contextPath}/bookings?status=CONFIRMED">Confirmed</a>
        <a href="${pageContext.request.contextPath}/bookings?status=COMPLETED">Completed</a>
    </div>

    <div class="card">
        <div class="card-header"><h2>Booking History</h2></div>
        <c:choose>
            <c:when test="${not empty bookings}">
                <table class="data-table">
                    <thead><tr><th>Property</th><th>Dates</th><th>Amount</th><th>Status</th><th>Action</th></tr></thead>
                    <tbody>
                        <c:forEach items="${bookings}" var="b">
                            <tr>
                                <td><c:out value="${b.propertyTitle}"/></td>
                                <td><c:out value="${b.checkInDate}"/> - <c:out value="${b.checkOutDate}"/></td>
                                <td>$<c:out value="${b.totalPrice}"/></td>
                                <td><span class="badge badge-warning"><c:out value="${b.bookingStatus}"/></span></td>
                                <td><button class="btn btn-danger">Cancel</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="text-align:center;padding:40px;color:#777;">No bookings yet. <a href="${pageContext.request.contextPath}/explore">Explore properties</a></p>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/pages/includes/footer.jsp"/>
</div>
</body>
</html>