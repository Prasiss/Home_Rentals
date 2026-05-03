<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
    <style>
        .profile-image-section { display:flex; align-items:center; gap:20px; margin-bottom:20px; padding:16px; background:#f8f9fa; border-radius:8px; }
        .profile-preview-placeholder { width:90px; height:90px; border-radius:50%; border:3px solid #dee2e6; background:#e9ecef; display:flex; align-items:center; justify-content:center; font-size:32px; color:#adb5bd; }
        .file-choose-btn { display:inline-block; padding:8px 16px; background:#6c757d; color:white; border-radius:4px; font-size:14px; margin-bottom:6px; cursor:pointer; }
    </style>
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/views/admin/header.jsp"/>

        <c:if test="${not empty successMsg}">
            <div style="background:#d4edda;color:#155724;border:1px solid #c3e6cb;padding:12px 16px;border-radius:6px;margin-bottom:20px;">${successMsg}</div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div style="background:#f8d7da;color:#721c24;border:1px solid #f5c6cb;padding:12px 16px;border-radius:6px;margin-bottom:20px;">${errorMsg}</div>
        </c:if>

        <%-- Profile Information --%>
        <div class="card">
            <div class="card-header"><h2>Profile Information</h2></div>

       
            <form method="post"
                  action="${pageContext.request.contextPath}/admin/profile"
                  enctype="multipart/form-data">

                <div class="form-group">
                    <label>Profile Photo</label>
                    <div class="profile-image-section">
                        <div class="profile-preview-placeholder">&#128100;</div>
                        <div>
                            <label class="file-choose-btn">
                                &#128247; Choose Photo
                                <%-- type="file" — no onchange handler --%>
                                <input type="file" name="profileImage"
                                       accept="image/jpeg,image/png,image/gif,image/webp"
                                       style="display:none;">
                            </label>
                            <span style="font-size:12px;color:#999;display:block;">
                                JPG, PNG or GIF &middot; Max 5 MB &middot; Leave blank to keep current
                            </span>
                        </div>
                    </div>
                </div>

   
                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="fullName" value="${adminProfile.fullName}">
                </div>
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="text" name="email" value="${adminProfile.email}">
                </div>
                <div class="form-group">
                    <label>Phone Number</label>
                    <input type="text" name="phone" value="${adminProfile.phone}">
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input type="text" name="address" value="${adminProfile.address}">
                </div>

                <button type="submit" name="action" value="updateProfile" class="btn btn-primary">
                    Save Changes
                </button>
            </form>
        </div>

        <%-- Change Password --%>
        <div class="card">
            <div class="card-header"><h2>Change Password</h2></div>

            <c:if test="${not empty passwordError}">
                <div style="background:#f8d7da;color:#721c24;padding:10px;border-radius:4px;margin-bottom:12px;">${passwordError}</div>
            </c:if>

         
            <form method="post" action="${pageContext.request.contextPath}/admin/profile">
                <div class="form-group">
                    <label>New Password</label>
                    <input type="password" name="newPassword">
                </div>
                <div class="form-group">
                    <label>Confirm New Password</label>
                    <input type="password" name="confirmPassword">
                </div>
                <button type="submit" name="action" value="changePassword" class="btn btn-primary">
                    Change Password
                </button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/views/admin/footer.jsp"/>
    </div>
</div>
</body>
</html>
