<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("activePage", "profile");
    request.setAttribute("pageTitle", "Edit Profile");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
    <style>
        .profile-image-section {
            display: flex;
            align-items: center;
            gap: 20px;
            margin-bottom: 20px;
            padding: 16px;
            background: #f8f9fa;
            border-radius: 8px;
        }
        .profile-preview {
            width: 90px;
            height: 90px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #dee2e6;
            background: #e9ecef;
        }
        .profile-preview-placeholder {
            width: 90px;
            height: 90px;
            border-radius: 50%;
            border: 3px solid #dee2e6;
            background: #e9ecef;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
            color: #adb5bd;
        }
        .file-input-label {
            cursor: pointer;
            display: inline-block;
        }
        .file-input-label input[type="file"] {
            display: none;
        }
        .file-choose-btn {
            display: inline-block;
            padding: 8px 16px;
            background: #6c757d;
            color: white;
            border-radius: 4px;
            font-size: 14px;
            margin-bottom: 6px;
        }
        .file-choose-btn:hover { background: #5a6268; }
        .file-name-display {
            font-size: 13px;
            color: #6c757d;
            display: block;
        }
        .success-banner {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="admin-wrapper">
    <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/views/admin/header.jsp"/>

        <c:if test="${not empty param.updated}">
            <div class="success-banner">✅ Profile updated successfully!</div>
        </c:if>

        <!-- ── Profile Information Card ── -->
        <div class="card">
            <div class="card-header"><h2>Profile Information</h2></div>

            <%-- enctype="multipart/form-data" is REQUIRED for file uploads --%>
            <form method="post"
                  action="${pageContext.request.contextPath}/admin/profile"
                  enctype="multipart/form-data">

                <!-- Profile Image Upload -->
                <div class="form-group">
                    <label>Profile Photo</label>
                    <div class="profile-image-section">

                        <%-- Show current image if set, otherwise show placeholder icon --%>
                        <c:choose>
                            <c:when test="${not empty adminProfile.profileImage and adminProfile.profileImage != 'default.png'}">
                                <img id="imagePreview"
                                     class="profile-preview"
                                     src="${pageContext.request.contextPath}/uploads/profiles/${adminProfile.profileImage}"
                                     alt="Profile Photo"
                                     onerror="this.style.display='none'; document.getElementById('imgPlaceholder').style.display='flex';">
                                <div id="imgPlaceholder" class="profile-preview-placeholder" style="display:none;">👤</div>
                            </c:when>
                            <c:otherwise>
                                <img id="imagePreview" class="profile-preview" style="display:none;" alt="Preview">
                                <div id="imgPlaceholder" class="profile-preview-placeholder">👤</div>
                            </c:otherwise>
                        </c:choose>

                        <div>
                            <label class="file-input-label">
                                <span class="file-choose-btn">📷 Choose Photo</span>
                                <input type="file"
                                       name="profileImage"
                                       id="profileImageInput"
                                       accept="image/jpeg,image/png,image/gif,image/webp"
                                       onchange="previewImage(this)">
                            </label>
                            <span class="file-name-display" id="fileNameDisplay">
                                <c:choose>
                                    <c:when test="${not empty adminProfile.profileImage and adminProfile.profileImage != 'default.png'}">
                                        Current: ${adminProfile.profileImage}
                                    </c:when>
                                    <c:otherwise>No photo selected — leave blank to keep current</c:otherwise>
                                </c:choose>
                            </span>
                            <span style="font-size:12px; color:#999;">JPG, PNG or GIF · Max 5 MB</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="fullName" value="${adminProfile.fullName}" required>
                </div>
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" name="email" value="${adminProfile.email}" required>
                </div>
                <div class="form-group">
                    <label>Phone Number</label>
                    <input type="text" name="phone" value="${adminProfile.phone}" required>
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

        <!-- ── Change Password Card ── -->
        <div class="card">
            <div class="card-header"><h2>Change Password</h2></div>
            <form method="post" action="${pageContext.request.contextPath}/admin/profile">
                <div class="form-group">
                    <label>New Password</label>
                    <input type="password" name="newPassword" required minlength="6">
                </div>
                <div class="form-group">
                    <label>Confirm New Password</label>
                    <input type="password" id="confirmPass" required minlength="6"
                           oninput="checkPassMatch()">
                    <span id="passMismatch" style="color:red; font-size:13px; display:none;">
                        Passwords do not match.
                    </span>
                </div>
                <button type="submit" name="action" value="changePassword" class="btn btn-primary">
                    Change Password
                </button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/views/admin/footer.jsp"/>
    </div>
</div>

<script>
    // Live image preview before upload
    function previewImage(input) {
        const file = input.files[0];
        const preview = document.getElementById('imagePreview');
        const placeholder = document.getElementById('imgPlaceholder');
        const label = document.getElementById('fileNameDisplay');

        if (file) {
            label.textContent = file.name + ' (' + (file.size / 1024).toFixed(1) + ' KB)';
            const reader = new FileReader();
            reader.onload = function(e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
                if (placeholder) placeholder.style.display = 'none';
            };
            reader.readAsDataURL(file);
        }
    }

    // Password match check
    function checkPassMatch() {
        const newPass = document.querySelector('input[name="newPassword"]');
        const confirm = document.getElementById('confirmPass');
        const msg = document.getElementById('passMismatch');
        if (newPass && confirm.value && newPass.value !== confirm.value) {
            msg.style.display = 'block';
        } else {
            msg.style.display = 'none';
        }
    }
</script>
</body>
</html>
