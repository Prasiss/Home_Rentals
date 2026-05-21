<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Become a Dealer - HomeRental</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
    <style>
        .dealer-form-wrapper { max-width:600px; margin:0 auto; }
        .dealer-form-card { background:#fff; border:1px solid #e0e0e0; padding:30px; }
        .section-label { font-size:13px; font-weight:700; text-transform:uppercase; color:#999; margin:20px 0 12px; border-bottom:1px solid #f0f0f0; padding-bottom:6px; }
        .info-note { background:#f0f4ff; border:1px solid #c7d4ff; padding:12px 16px; font-size:13px; color:#3a5ce6; margin-bottom:20px; }
        .form-group textarea { width:100%; padding:10px; border:1px solid #e0e0e0; font-size:14px; resize:vertical; min-height:100px; font-family:Arial; }
        .status-pending { background:#fff8e1; border:1px solid #ffe082; padding:20px; text-align:center; border-radius:6px; }
        .status-pending h3 { color:#f59e0b; margin-bottom:8px; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/client/dashboard/sidebar.jsp"/>
<div class="main-content">
    <jsp:include page="/WEB-INF/pages/client/dashboard/header.jsp"/>

    <div class="dealer-form-wrapper">
        <a href="${pageContext.request.contextPath}/dashboard" style="color:#6b5b95;text-decoration:none;">← Back to Dashboard</a>

        <div class="dealer-form-card" style="margin-top:15px;">
            <h2>Become a Dealer</h2>

            <c:choose>
                <c:when test="${dealerRequestStatus == 'PENDING'}">
                    <div class="status-pending">
                        <h3>&#9203; Request Under Review</h3>
                        <p>Your dealer application has been submitted and is waiting for admin approval.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <p style="color:#777;margin-bottom:20px;">Send a request to the admin. Your details will be reviewed.</p>
                    <div class="info-note">Your message will be sent directly to the admin for review.</div>

                    <c:if test="${not empty errorMsg}">
                        <div style="background:#f8d7da;color:#721c24;padding:10px;border-radius:4px;margin-bottom:12px;">
                            ${errorMsg}
                        </div>
                    </c:if>

                    <c:if test="${dealerRequestStatus == 'REJECTED'}">
                        <div style="background:#fff3cd;color:#856404;border:1px solid #ffc107;padding:10px;border-radius:4px;margin-bottom:12px;">
                            Your previous request was rejected. You may submit a new application below.
                        </div>
                    </c:if>
                    <form method="post" action="${pageContext.request.contextPath}/dealerapplication">
                        <div class="section-label">Business Details</div>

                        <div class="form-group">
                            <label>Company Name <span style="color:red;">*</span></label>
                            <input type="text" name="companyName" placeholder="e.g. Sunrise Properties" value="${param.companyName}">
                        </div>

                        <div style="display:grid;grid-template-columns:1fr 1fr;gap:15px;">
                            <div class="form-group">
                                <label>Years of Experience</label>
                                <input type="text" name="yearsExperience" placeholder="e.g. 5" value="${param.yearsExperience}">
                            </div>
                            <div class="form-group">
                                <label>No. of Properties</label>
                                <input type="text" name="propertiesCount" placeholder="e.g. 10" value="${param.propertiesCount}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label>About Your Business <span style="color:red;">*</span></label>
                            <textarea name="aboutBusiness" placeholder="Describe your business...">${param.aboutBusiness}</textarea>
                        </div>

                        <button type="submit" class="btn btn-primary" style="width:100%;padding:12px;">Send Request</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <jsp:include page="/WEB-INF/pages/client/dashboard/footer.jsp"/>
</div>
</body>
</html>
