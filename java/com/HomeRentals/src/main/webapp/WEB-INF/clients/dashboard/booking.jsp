<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bookings - HomeRental</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>
<jsp:include page="/WEB-INF/pages/client/dashboard/sidebar.jsp"/>

<div class="main-content">
    <jsp:include page="/WEB-INF/pages/client/dashboard/header.jsp"/>

    <h1 style="font-size:26px;margin-bottom:20px;">My Bookings</h1>

    <div class="filter-bar">
        <a href="${pageContext.request.contextPath}/bookings"
           class="${activeFilter == 'ALL' ? 'active' : ''}">All</a>
        <a href="${pageContext.request.contextPath}/bookings?status=PENDING"
           class="${activeFilter == 'PENDING' ? 'active' : ''}">Pending</a>
        <a href="${pageContext.request.contextPath}/bookings?status=CONFIRMED"
           class="${activeFilter == 'CONFIRMED' ? 'active' : ''}">Confirmed</a>
        <a href="${pageContext.request.contextPath}/bookings?status=COMPLETED"
           class="${activeFilter == 'COMPLETED' ? 'active' : ''}">Completed</a>
        <a href="${pageContext.request.contextPath}/bookings?status=CANCELLED"
           class="${activeFilter == 'CANCELLED' ? 'active' : ''}">Cancelled</a>
    </div>

    <div class="card">
        <div class="card-header"><h2>Booking History</h2></div>
        <c:choose>
            <c:when test="${not empty bookings}">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Property</th>
                            <th>Location</th>
                            <th>Amount</th>
                            <th>Date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${bookings}" var="b">
                            <tr>
                                <td><c:out value="${b.bookingId}"/></td>
                                <td><c:out value="${b.propertyTitle}"/></td>
                                <td><c:out value="${b.location}"/></td>
                                <td>Rs. <c:out value="${b.totalPrice}"/></td>
                                <td><c:out value="${b.createdAt}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${b.status == 'CONFIRMED'}">
                                            <span class="badge badge-success">Confirmed</span>
                                        </c:when>
                                        <c:when test="${b.status == 'PENDING'}">
                                            <span class="badge badge-warning">Pending</span>
                                        </c:when>
                                        <c:when test="${b.status == 'CANCELLED'}">
                                            <span class="badge badge-danger">Cancelled</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge"><c:out value="${b.status}"/></span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="text-align:center;padding:40px;color:#777;">
                    No bookings yet. <a href="${pageContext.request.contextPath}/home">Explore properties</a>
                </p>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/WEB-INF/pages/client/dashboard/footer.jsp"/>
</div>

</body>
</html>