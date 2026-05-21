<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile - HomeRental</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin.css">
    <style>
        .profile-image-section { display:flex; align-items:center; gap:20px; margin-bottom:20px; padding:16px; background:#f8f9fa; border-radius:8px; }
        .profile-preview-placeholder { width:90px; height:90px; border-radius:50%; border:3px solid #dee2e6; background:#e9ecef; display:flex; align-items:center; justify-content:center; font-size:32px; color:#adb5bd; }
        .file-choose-btn { display:inline-block; padding:8px 16px; background:#6c757d; color:white; border-radius:4px; font-size:14px; margin-bottom:6px; cursor:pointer; }
    </style>
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/pages/admin/admin_template/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/admin/admin_template/header.jsp"/>

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
                        <%-- Show existing photo if stored, otherwise show placeholder icon --%>
                        <c:choose>
                            <c:when test="${not empty adminProfile.profileImage}">
                                <img id="profilePreview"
                                     src="${pageContext.request.contextPath}/getImage?name=${adminProfile.profileImage}"
                                     alt="Profile Photo"
                                     style="width:90px;height:90px;border-radius:50%;border:3px solid #dee2e6;object-fit:cover;">
                            </c:when>
                            <c:otherwise>
                                <div id="profilePreviewPlaceholder"
                                     class="profile-preview-placeholder">&#128100;</div>
                                <img id="profilePreview"
                                     src=""
                                     alt="Profile Photo"
                                     style="display:none;width:90px;height:90px;border-radius:50%;border:3px solid #dee2e6;object-fit:cover;">
                            </c:otherwise>
                        </c:choose>
                        <div>
                            <label class="file-choose-btn">
                                &#128247; Choose Photo
                                <input type="file" name="profileImage"
                                       id="profileImageInput"
                                       accept="image/jpeg,image/png,image/gif,image/webp"
                                       style="display:none;"
                                       onchange="previewPhoto(this)">
                            </label>
                            <span style="font-size:12px;color:#999;display:block;">
                                JPG, PNG or GIF &middot; Max 5 MB &middot; Leave blank to keep current
                            </span>
                        </div>
                    </div>
                </div>

                <script>
                function previewPhoto(input) {
                    if (!input.files || !input.files[0]) return;
                    var file = input.files[0];
                    if (file.size > 5 * 1024 * 1024) {
                        alert('File is too large. Maximum size is 5 MB.');
                        input.value = '';
                        return;
                    }
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        var preview = document.getElementById('profilePreview');
                        var placeholder = document.getElementById('profilePreviewPlaceholder');
                        preview.src = e.target.result;
                        preview.style.display = 'block';
                        if (placeholder) placeholder.style.display = 'none';
                    };
                    reader.readAsDataURL(file);
                }
                </script>

   
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
                    <input type="text" name="phone" value="${adminProfile.number}">
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

        <jsp:include page="/WEB-INF/pages/admin/admin_template/footer.jsp"/>
    </div>
</div>
</body>
</html>
