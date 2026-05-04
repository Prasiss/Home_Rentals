<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile - HomeRentals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>
<jsp:include page="/pages/includes/sidebar.jsp"/>
<div class="main-content">
    <jsp:include page="/pages/includes/header.jsp"/>

    <div class="card" style="max-width:500px;margin:0 auto;">
        <div class="card-header"><h2>My Profile</h2></div>

        <div class="profile-avatar" style="width:80px;height:80px;background:var(--primary);border-radius:50%;display:flex;align-items:center;justify-content:center;margin:0 auto 15px;font-size:28px;color:#fff;font-weight:700;">
            ${user.fullName.substring(0,1).toUpperCase()}
        </div>

        <c:if test="${not empty successMsg}">
            <div class="alert success" style="background:#d4edda;color:#155724;padding:10px;border-radius:4px;margin-bottom:12px;">
                ${successMsg}
            </div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div class="alert error" style="background:#f8d7da;color:#721c24;padding:10px;border-radius:4px;margin-bottom:12px;">
                ${errorMsg}
            </div>
        </c:if>

        <%-- No required / type="email" — all validation is server-side in ProfileServlet --%>
        <form method="post" action="${pageContext.request.contextPath}/profile">
            <div class="form-group">
                <label>Full Name</label>
                <input type="text" name="full_name" value="${user.fullName}">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" name="email" value="${user.email}">
            </div>
            <div class="form-group">
                <label>Phone Number</label>
                <input type="text" name="phone_number" value="${user.phone}">
            </div>
            <button type="submit" class="btn btn-primary" style="width:100%;padding:12px;">Update Profile</button>
        </form>
    </div>

    <jsp:include page="/pages/includes/footer.jsp"/>
</div>
</body>
</html>
