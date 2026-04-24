<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Applications - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/administration.css">
</head>
<body>
<div class="administration-wrapper">
    <c:set var="activePage" value="applications" scope="request"/>
    <jsp:include page="/components/admin-navigation.jsp"/>
    <div class="main-content-area">
        <c:set var="pageTitle" value="Dealer Applications" scope="request"/>
        <jsp:include page="/components/admin-header.jsp"/>
        
        <div class="filter-tabs-container">
            <a href="${pageContext.request.contextPath}/admin/applications?status=pending" class="filter-tab ${empty currentFilter || currentFilter == 'pending' ? 'active' : ''}">Pending</a>
            <a href="${pageContext.request.contextPath}/admin/applications?status=APPROVED" class="filter-tab ${currentFilter == 'APPROVED' ? 'active' : ''}">Approved</a>
            <a href="${pageContext.request.contextPath}/admin/applications?status=REJECTED" class="filter-tab ${currentFilter == 'REJECTED' ? 'active' : ''}">Rejected</a>
        </div>
        
        <div class="content-card">
            <div class="card-header-section"><h2 class="card-title">Applications</h2></div>
            <c:forEach items="${pendingApplicationsList}" var="a">
                <div class="application-entry">
                    <div class="application-header"><h3 class="applicant-name"><c:out value="${a.applicantName}"/></h3><span class="status-indicator ${a.applicationStatus == 'APPROVED' ? 'active' : a.applicationStatus == 'PENDING' ? 'pending' : 'suspended'}"><c:out value="${a.applicationStatus}"/></span></div>
                    <div class="application-meta">
                        <div><div class="meta-item-label">Company</div><div class="meta-item-value"><c:out value="${a.companyName}"/></div></div>
                        <div><div class="meta-item-label">Email</div><div class="meta-item-value"><c:out value="${a.applicantEmail}"/></div></div>
                        <div><div class="meta-item-label">Phone</div><div class="meta-item-value"><c:out value="${a.applicantPhone}"/></div></div>
                        <div><div class="meta-item-label">Experience</div><div class="meta-item-value"><c:out value="${a.yearsExperience}"/> years</div></div>
                    </div>
                    <div class="application-description"><strong>About:</strong><br><c:out value="${a.aboutBusiness}"/></div>
                    <c:if test="${a.applicationStatus == 'PENDING'}">
                        <div class="application-actions">
                            <form method="post"><input type="hidden" name="applicationNo" value="${a.applicationNo}"><input type="hidden" name="currentFilter" value="${currentFilter}"><button type="submit" name="action" value="approveApplication" class="action-button success">Approve</button><button type="submit" name="action" value="rejectApplication" class="action-button danger">Reject</button></form>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <jsp:include page="/components/admin-footer.jsp"/>
    </div>
</div>
</body>
</html>