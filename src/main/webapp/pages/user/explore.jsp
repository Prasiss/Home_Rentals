<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Explore Properties - HomeRentals</title>
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
        .filter-bar { display: flex; gap: 10px; margin-bottom: 20px; flex-wrap: wrap; }
        .filter-bar a { padding: 8px 18px; background: #fff; border: 1px solid #e0e0e0; color: #555; text-decoration: none; font-size: 13px; }
        .filter-bar a.active, .filter-bar a:hover { background: #6b5b95; color: #fff; border-color: #6b5b95; }
        @media (max-width: 768px) { .property-grid { grid-template-columns: 1fr; } }
    </style>
</head>
<body>
<jsp:include page="/pages/includes/sidebar.jsp"/>
<div class="main-content">
    <jsp:include page="/pages/includes/header.jsp"/>

    <h1 style="font-size:26px;margin-bottom:20px;">Explore Properties</h1>

    <div class="filter-bar">
        <a href="${pageContext.request.contextPath}/explore" class="active">All</a>
        <a href="${pageContext.request.contextPath}/explore?filter=house">Houses</a>
        <a href="${pageContext.request.contextPath}/explore?filter=apartment">Apartments</a>
        <a href="${pageContext.request.contextPath}/explore?filter=villa">Villas</a>
    </div>

    <div class="property-grid">
        <c:choose>
            <c:when test="${not empty properties}">
                <c:forEach items="${properties}" var="prop">
                    <div class="property-card">
                        <div class="property-img">Property Image</div>
                        <div class="property-info">
                            <h3><c:out value="${prop.title}"/></h3>
                            <p class="location"><c:out value="${prop.location}"/></p>
                            <p class="price"><fmt:formatNumber value="${prop.pricePerMonth}" type="currency" currencySymbol="$"/>/mo</p>
                            <a href="${pageContext.request.contextPath}/product?id=${prop.propertyId}" class="btn btn-primary" style="width:100%;text-align:center;">View Details</a>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p style="grid-column:1/-1;text-align:center;padding:40px;color:#777;">No properties found. Check back later!</p>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/pages/includes/footer.jsp"/>
</div>
</body>
</html>