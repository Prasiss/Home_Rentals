<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Wishlist - HomeRentals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
    <style>
        .property-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; margin-top: 20px; }
        .property-card { background: #fff; border: 1px solid #e0e0e0; overflow: hidden; }
        .property-img { width: 100%; height: 160px; background: #e8e4f2; display: flex; align-items: center; justify-content: center; font-size: 14px; color: #6b5b95; }
        .property-info { padding: 15px; }
        .property-info h3 { font-size: 15px; margin-bottom: 6px; }
        .property-info .location { color: #777; font-size: 13px; margin-bottom: 6px; }
        .property-info .price { font-size: 18px; font-weight: 600; color: #6b5b95; margin-bottom: 10px; }
        @media (max-width: 768px) { .property-grid { grid-template-columns: 1fr; } }
    </style>
</head>
<body>
<jsp:include page="/pages/includes/sidebar.jsp"/>
<div class="main-content">
    <jsp:include page="/pages/includes/header.jsp"/>

    <h1 style="font-size:26px;margin-bottom:20px;">My Wishlist</h1>

    <div class="property-grid">
        <c:choose>
            <c:when test="${not empty wishlist}">
                <c:forEach items="${wishlist}" var="prop">
                    <div class="property-card">
                        <div class="property-img">Property Image</div>
                        <div class="property-info">
                            <h3><c:out value="${prop.title}"/></h3>
                            <p class="location"><c:out value="${prop.location}"/></p>
                            <p class="price">$<c:out value="${prop.pricePerMonth}"/>/mo</p>
                            <div style="display:flex;gap:8px;">
                                <a href="${pageContext.request.contextPath}/product?id=${prop.propertyId}" class="btn btn-primary" style="flex:1;text-align:center;">View</a>
                                <button class="btn btn-danger">Remove</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p style="grid-column:1/-1;text-align:center;padding:40px;color:#777;">Your wishlist is empty. <a href="${pageContext.request.contextPath}/explore">Explore properties</a></p>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/pages/includes/footer.jsp"/>
</div>
</body>
</html>