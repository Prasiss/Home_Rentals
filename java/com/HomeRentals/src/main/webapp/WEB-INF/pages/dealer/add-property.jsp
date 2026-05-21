<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="activePage" value="add-property"   scope="request"/>
<c:set var="pageTitle"  value="Add Property"   scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Property - HomeRental Dealer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dealer.css">
    <style>
        .form-card { background:var(--white); border:1px solid var(--border); padding:28px 32px; max-width:680px; }
        .btn-submit { background:var(--primary); color:#fff; border:none; padding:11px 28px; font-size:14px; font-weight:600; cursor:pointer; }
        .btn-submit:hover { opacity:0.88; }
    </style>
</head>
<body>
<div class="dealer-wrapper">

    <jsp:include page="/WEB-INF/pages/dealer/dealer_template/sidebar.jsp"/>

    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/header.jsp"/>

        <c:if test="${not empty errorMessage}">
            <div class="alert-error">
                <i class="fa-solid fa-triangle-exclamation"></i> ${errorMessage}
            </div>
        </c:if>

        <div class="form-card">
            <form action="${pageContext.request.contextPath}/dealer/add-property" method="post">

                <div class="form-group">
                    <label>Property Title</label>
                    <input type="text" name="title" placeholder="e.g. Sunset Apartments" required>
                </div>

                <div class="form-group">
                    <label>Location</label>
                    <input type="text" name="location" placeholder="e.g. Kathmandu, Nepal" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Price / Month ($)</label>
                        <input type="number" name="pricePerMonth" placeholder="e.g. 850" step="0.01" required>
                    </div>
                    <div class="form-group">
                        <label>Category</label>
                        <select name="categoryId" required>
                            <option value="">-- Select Category --</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.categoryId}">${cat.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Available From</label>
                        <input type="date" name="availableFrom" required>
                    </div>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description" placeholder="Describe your property..."></textarea>
                </div>

                <button type="submit" class="btn-submit">Submit Property</button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/footer.jsp"/>
    </div>

</div>
</body>
</html>
