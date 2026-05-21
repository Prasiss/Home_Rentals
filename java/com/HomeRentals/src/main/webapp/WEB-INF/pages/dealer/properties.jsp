<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="activePage" value="properties"    scope="request"/>
<c:set var="pageTitle"  value="My Properties" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Properties - HomeRental Dealer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dealer.css">
    <style>
        /* ── Confirmation Modal ── */
        .modal-overlay {
            display: none;
            position: fixed;
            inset: 0;
            background: rgba(0,0,0,0.45);
            z-index: 1000;
            align-items: center;
            justify-content: center;
        }
        .modal-overlay.active { display: flex; }

        .modal-box {
            background: #fff;
            border-radius: 10px;
            padding: 32px 28px 24px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 8px 32px rgba(0,0,0,0.18);
            text-align: center;
        }
        .modal-icon {
            width: 56px; height: 56px;
            border-radius: 50%;
            background: var(--danger-bg, #f8d7da);
            display: flex; align-items: center; justify-content: center;
            margin: 0 auto 16px;
            font-size: 24px; color: #dc3545;
        }
        .modal-box h3 { margin: 0 0 8px; font-size: 18px; }
        .modal-box p  { margin: 0 0 24px; color: var(--text-gray, #666); font-size: 14px; }
        .modal-actions { display: flex; gap: 10px; justify-content: center; }
        .modal-actions .btn { min-width: 110px; }
        .btn-secondary {
            background: #f1f3f5; color: #333;
            border: 1px solid #dee2e6;
            padding: 8px 18px; border-radius: 6px;
            font-size: 13px; cursor: pointer;
        }
        .btn-secondary:hover { background: #e2e6ea; }
    </style>
</head>
<body>
<div class="dealer-wrapper">

    <jsp:include page="/WEB-INF/pages/dealer/dealer_template/sidebar.jsp"/>

    <div class="main-content">
        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/header.jsp"/>

        <%-- ── Flash messages ── --%>
        <c:if test="${param.deactivated == '1'}">
            <div class="alert-success">
                <i class="fa-solid fa-check-circle"></i>
                Property deactivated successfully. It is no longer visible to renters.
            </div>
        </c:if>
        <c:if test="${param.error == 'failed'}">
            <div class="alert-error">
                <i class="fa-solid fa-triangle-exclamation"></i>
                Deactivation failed. Please try again.
            </div>
        </c:if>
        <c:if test="${param.error == 'notfound'}">
            <div class="alert-error">
                <i class="fa-solid fa-triangle-exclamation"></i>
                Property not found or you do not have permission to deactivate it.
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert-error">
                <i class="fa-solid fa-triangle-exclamation"></i> ${errorMessage}
            </div>
        </c:if>

        <div class="card">
            <div class="card-header">
                <h2>My Properties</h2>
                <a href="${pageContext.request.contextPath}/dealer/add-property" class="btn btn-primary">
                    <i class="fa-solid fa-plus"></i> Add Property
                </a>
            </div>

            <div class="filter-bar">
                <a href="?status="         class="filter-btn ${empty currentFilter || currentFilter == 'ALL'      ? 'active' : ''}">All</a>
                <a href="?status=APPROVED" class="filter-btn ${currentFilter == 'APPROVED' ? 'active' : ''}">Approved</a>
                <a href="?status=PENDING"  class="filter-btn ${currentFilter == 'PENDING'  ? 'active' : ''}">Pending</a>
                <a href="?status=REJECTED" class="filter-btn ${currentFilter == 'REJECTED' ? 'active' : ''}">Rejected</a>
            </div>

            <table class="data-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Property</th>
                        <th>Category</th>
                        <th>Type</th>
                        <th>Price/mo</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty properties}">
                            <tr>
                                <td colspan="7" style="text-align:center;color:var(--text-gray);padding:30px;">
                                    No properties found.
                                    <a href="${pageContext.request.contextPath}/dealer/add-property">Add one now &rarr;</a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="prop" items="${properties}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>
                                        <div class="prop-title">${prop.title}</div>
                                        <div class="prop-location">
                                            <i class="fa-solid fa-location-dot"></i> ${prop.location}
                                        </div>
                                    </td>
                                    <td>${prop.categoryName}</td>
                                    <td>${prop.propertyType}</td>
                                    <td class="price">$${prop.pricePerMonth}</td>
                                    <td>
                                        <span class="badge ${prop.approvalStatus == 'APPROVED' ? 'badge-success' :
                                                             prop.approvalStatus == 'PENDING'  ? 'badge-warning' : 'badge-danger'}">
                                            ${prop.approvalStatus}
                                        </span>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/dealer/edit-property?id=${prop.propertyId}"
                                           class="btn btn-primary" style="font-size:12px;padding:5px 12px;">
                                            <i class="fa-solid fa-pen"></i> Edit
                                        </a>

                                        <%-- Deactivate button triggers the modal --%>
                                        <button type="button"
                                                class="btn btn-danger"
                                                style="font-size:12px;padding:5px 12px;"
                                                onclick="openDeactivateModal(${prop.propertyId}, '${prop.title}')">
                                            <i class="fa-solid fa-ban"></i> Deactivate
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <jsp:include page="/WEB-INF/pages/dealer/dealer_template/footer.jsp"/>
    </div>
</div>

<%-- ── Deactivate Confirmation Modal ── --%>
<div class="modal-overlay" id="deactivateModal">
    <div class="modal-box">
        <div class="modal-icon">
            <i class="fa-solid fa-ban"></i>
        </div>
        <h3>Deactivate Property?</h3>
        <p id="modalMessage">This property will be hidden from renters immediately.</p>

        <form method="post" action="${pageContext.request.contextPath}/dealer/properties" id="deactivateForm">
            <input type="hidden" name="action"     value="deactivateProperty">
            <input type="hidden" name="propertyId" id="modalPropertyId" value="">

            <div class="modal-actions">
                <button type="button" class="btn-secondary" onclick="closeDeactivateModal()">
                    Cancel
                </button>
                <button type="submit" class="btn btn-danger">
                    Yes, Deactivate
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    function openDeactivateModal(propertyId, propertyTitle) {
        document.getElementById('modalPropertyId').value = propertyId;
        document.getElementById('modalMessage').textContent =
            '"' + propertyTitle + '" will be hidden from renters immediately.';
        document.getElementById('deactivateModal').classList.add('active');
    }

    function closeDeactivateModal() {
        document.getElementById('deactivateModal').classList.remove('active');
        document.getElementById('modalPropertyId').value = '';
    }

    // Close when clicking outside the box
    document.getElementById('deactivateModal').addEventListener('click', function(e) {
        if (e.target === this) closeDeactivateModal();
    });

    // Close on Escape key
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') closeDeactivateModal();
    });
</script>
</body>
</html>
