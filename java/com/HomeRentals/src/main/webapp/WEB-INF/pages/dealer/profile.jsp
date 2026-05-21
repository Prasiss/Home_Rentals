<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="activePage" value="profile"   scope="request"/>
<c:set var="pageTitle"  value="Profile"   scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - HomeRental Dealer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dealer.css">
    <style>
        .profile-card { background:var(--white); border:1px solid var(--border); padding:32px; max-width:520px; margin:0 auto; }
        .profile-card-header { font-size:13px; font-weight:700; text-transform:uppercase; letter-spacing:0.5px; color:var(--text-gray); padding-bottom:16px; margin-bottom:24px; border-bottom:1px solid var(--border); }
        .profile-photo-wrapper { display:flex; flex-direction:column; align-items:center; margin-bottom:28px; gap:10px; }
        .profile-photo-circle { width:90px; height:90px; border-radius:50%; background:var(--primary); display:flex; align-items:center; justify-content:center; overflow:hidden; font-size:32px; font-weight:700; color:#fff; border:3px solid var(--border); }
        .profile-photo-circle img { width:100%; height:100%; object-fit:cover; display:block; }
        input[type="file"]#profileImage { display:none; }
        .photo-upload-label { font-size:13px; font-weight:500; color:var(--primary); cursor:pointer; padding:6px 14px; border:1px solid var(--primary); background:#fff; }
        .photo-upload-label:hover { background:var(--primary); color:#fff; }
        .btn-save { width:100%; padding:12px; background:var(--primary); color:#fff; border:none; font-size:14px; font-weight:600; cursor:pointer; }
        .btn-save:hover { opacity:0.88; }
    </style>
</head>
<body>
<div class="dealer-wrapper">

    <jsp:include page="/WEB-INF/pages/dealer/dealer_template/sidebar.jsp"/>

    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/header.jsp"/>

        <div class="profile-card">
            <div class="profile-card-header">Account Details</div>

            <c:if test="${not empty successMsg}">
                <div class="alert success">${successMsg}</div>
            </c:if>
            <c:if test="${not empty errorMsg}">
                <div class="alert error">${errorMsg}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/profile" enctype="multipart/form-data">

                <div class="profile-photo-wrapper">
                    <div class="profile-photo-circle">
                        <c:choose>
                            <c:when test="${not empty user.profileImage}">
                                <img src="${pageContext.request.contextPath}/getImage?name=${user.profileImage}" alt="Profile Photo">
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

                <button type="submit" class="btn-save">Update Profile</button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/footer.jsp"/>
    </div>

</div>
</body>
</html>
