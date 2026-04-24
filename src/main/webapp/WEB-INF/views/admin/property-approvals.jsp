<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Properties - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/administration.css">
</head>
<body>
<div class="administration-wrapper">
    <c:set var="activePage" value="properties" scope="request"/>
    <jsp:include page="/components/admin-navigation.jsp"/>
    <div class="main-content-area">
        <c:set var="pageTitle" value="Property Approvals" scope="request"/>
        <jsp:include page="/components/admin-header.jsp"/>
        
        <div class="filter-tabs-container">
            <a href="${pageContext.request.contextPath}/admin/properties?status=pending" class="filter-tab ${empty currentFilter || currentFilter == 'pending' ? 'active' : ''}">Pending</a>
            <a href="${pageContext.request.contextPath}/admin/properties?status=APPROVED" class="filter-tab ${currentFilter == 'APPROVED' ? 'active' : ''}">Approved</a>
            <a href="${pageContext.request.contextPath}/admin/properties?status=REJECTED" class="filter-tab ${currentFilter == 'REJECTED' ? 'active' : ''}">Rejected</a>
        </div>
        
        <div class="content-card">
            <div class="card-header-section"><h2 class="card-title">Properties</h2></div>
            <table class="data-table"><thead><tr><th>ID</th><th>Title</th><th>Dealer</th><th>Price</th><th>Location</th><th>Status</th><th>Action</th></tr></thead><tbody>
            <c:forEach items="${pendingPropertiesList}" var="p"><tr><td>#<c:out value="${p.propertyNo}"/></td><td><c:out value="${p.title}"/></td><td><c:out value="${p.dealerName}"/></td><td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/></td><td><c:out value="${p.location}"/></td><td><span class="status-indicator ${p.approvalStatus == 'APPROVED' ? 'active' : p.approvalStatus == 'PENDING' ? 'pending' : 'suspended'}"><c:out value="${p.approvalStatus}"/></span></td><td><c:if test="${p.approvalStatus == 'PENDING'}"><form method="post"><input type="hidden" name="propertyNo" value="${p.propertyNo}"><input type="hidden" name="currentFilter" value="${currentFilter}"><button type="submit" name="action" value="approveProperty" class="action-button success">Approve</button><button type="submit" name="action" value="rejectProperty" class="action-button danger">Reject</button></form></c:if></td></tr></c:forEach>
            </tbody></table>
        </div>
        <jsp:include page="/components/admin-footer.jsp"/>
    </div>
</div>
</body>
</html>