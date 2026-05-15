<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    request.setAttribute("activePage", "properties");
    request.setAttribute("pageTitle", "Property Approvals");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Property Approvals - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin.css">
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/pages/admin/admin_template/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/admin/admin_template/header.jsp"/>

        <div class="filter-tabs">
            <a href="${pageContext.request.contextPath}/admin/properties?status=pending" class="${empty currentFilter || currentFilter == 'pending' ? 'active' : ''}">Pending</a>
            <a href="${pageContext.request.contextPath}/admin/properties?status=APPROVED" class="${currentFilter == 'APPROVED' ? 'active' : ''}">Approved</a>
            <a href="${pageContext.request.contextPath}/admin/properties?status=REJECTED" class="${currentFilter == 'REJECTED' ? 'active' : ''}">Rejected</a>
        </div>

        <div class="card">
            <div class="card-header"><h2>Property List</h2></div>
            <c:choose>
                <c:when test="${not empty propertyList}">
                    <table class="data-table">
                        <thead>
                            <tr><th>Title</th><th>Owner</th><th>Price/Month</th><th>Location</th><th>Status</th><th>Action</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${propertyList}" var="prop">
                            <tr>
                                <td><c:out value="${prop.title}"/></td>
                                <td><c:out value="${prop.ownerName}"/></td>
                                <td><fmt:formatNumber value="${prop.pricePerMonth}" type="currency" currencySymbol="$"/></td>
                                <td><c:out value="${prop.location}"/></td>
                                <td><span class="badge ${prop.approvalStatus == 'APPROVED' ? 'badge-success' : prop.approvalStatus == 'PENDING' ? 'badge-warning' : 'badge-danger'}"><c:out value="${prop.approvalStatus}"/></span></td>
                                <td>
                                    <c:if test="${prop.approvalStatus == 'PENDING'}">
                                        <form method="post" style="display:inline;">
                                            <input type="hidden" name="propertyId" value="${prop.propertyId}">
                                            <button type="submit" name="action" value="approveProperty" class="btn btn-success">Approve</button>
                                            <button type="submit" name="action" value="rejectProperty" class="btn btn-danger">Reject</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No properties found.</p>
                </c:otherwise>
            </c:choose>
        </div>
        <jsp:include page="/WEB-INF/pages/admin/admin_template/footer.jsp"/>
    </div>
</div>
</body>
</html>