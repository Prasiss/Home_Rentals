<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile - HomeRental</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>

<jsp:include page="/WEB-INF/pages/client/dashboard/sidebar.jsp"/>

<div class="main-content">
    <jsp:include page="/WEB-INF/pages/client/dashboard/header.jsp"/>

    <div class="card" style="max-width:500px;margin:0 auto;">
        <div class="card-header"><h2>My Profile</h2></div>

        <c:if test="${not empty successMsg}">
            <div class="alert success">${successMsg}</div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div class="alert error">${errorMsg}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/profile"
              enctype="multipart/form-data">

            <div class="profile-photo-wrapper">
                <div class="profile-photo-circle">
                    <c:choose>
                        <c:when test="${not empty profileImageName}">
                            <img src="${pageContext.request.contextPath}/getImage?name=${profileImageName}"
                                 alt="Profile Photo">
                        </c:when>
                        <c:otherwise>
                            <span>${user.fullName.substring(0,1).toUpperCase()}</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <label for="profileImage" class="photo-upload-label">Change Photo</label>
                <input type="file" id="profileImage" name="profileImage" accept="image/*">
            </div>

            <div class="form-group">
                <label>Full Name</label>
                <input type="text" name="fullName" value="${user.fullName}">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" name="email" value="${user.email}">
            </div>
            <div class="form-group">
                <label>Phone Number</label>
                <input type="text" name="number" value="${user.number}">
            </div>

            <button type="submit" class="btn btn-primary"
                    style="width:100%;padding:12px;">Update Profile</button>
        </form>
    </div>

    <jsp:include page="/WEB-INF/pages/client/dashboard/footer.jsp"/>
</div>

</body>
</html>