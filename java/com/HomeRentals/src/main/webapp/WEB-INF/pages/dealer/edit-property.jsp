<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="activePage" value="properties"     scope="request"/>
<c:set var="pageTitle"  value="Edit Property"  scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Property - HomeRental Dealer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dealer.css">
    <style>
        .form-card { background:var(--white); border:1px solid var(--border); padding:28px 32px; max-width:680px; }
        .pending-note { background:var(--warning-bg); border:1px solid #fde68a; color:#856404; padding:10px 16px; margin-bottom:20px; font-size:13px; }
        .btn-submit { background:var(--primary); color:#fff; border:none; padding:11px 28px; font-size:14px; font-weight:600; cursor:pointer; }
        .btn-submit:hover { opacity:0.88; }
        .btn-cancel { background:#f3f4f6; color:var(--text-dark); border:none; padding:11px 22px; font-size:14px; font-weight:600; cursor:pointer; text-decoration:none; margin-left:10px; }
        .btn-cancel:hover { opacity:0.80; }
    </style>
</head>
<body>
<div class="dealer-wrapper">

    <jsp:include page="/WEB-INF/pages/dealer/dealer_template/sidebar.jsp"/>

    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/header.jsp"/>

        <div class="pending-note">
            <i class="fa-solid fa-info-circle"></i>
            Saving changes will re-submit this property for admin approval (status will reset to <strong>PENDING</strong>).
        </div>

        <div class="form-card">
            <form action="${pageContext.request.contextPath}/dealer/edit-property" method="post">
                <input type="hidden" name="propertyId" value="${property.propertyId}">

                <div class="form-group">
                    <label>Property Title</label>
                    <input type="text" name="title" value="${property.title}" placeholder="e.g. Sunset Apartments" required>
                </div>

                <div class="form-group">
                    <label>Location</label>
                    <input type="text" name="location" value="${property.location}" placeholder="e.g. Kathmandu, Nepal" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Price / Month ($)</label>
                        <input type="number" name="pricePerMonth" value="${property.pricePerMonth}" step="0.01" required>
                    </div>
                    <div class="form-group">
                        <label>Category</label>
                        <select name="categoryId" required>
                            <option value="">-- Select Category --</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.categoryId}"
                                    ${cat.categoryId == property.categoryId ? 'selected' : ''}>
                                    ${cat.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Available From</label>
                        <input type="date" name="availableFrom" value="${property.availableFrom}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description">${property.description}</textarea>
                </div>

                <div style="display:flex;align-items:center;">
                    <button type="submit" class="btn-submit">Save Changes</button>
                    <a href="${pageContext.request.contextPath}/dealer/properties" class="btn-cancel">Cancel</a>
                </div>
            </form>
        </div>

        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/footer.jsp"/>
    </div>

</div>
</body>
</html>
