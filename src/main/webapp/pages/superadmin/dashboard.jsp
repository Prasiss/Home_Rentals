<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Super Admin — Home Rentals</title>
    <%-- CSS from webapp/css/ as mapped in web.xml --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
</head>
<body>

<%-- ═══════════════════════ HEADER ═══════════════════════════ --%>
<header class="dash-header">
    <div class="hdr-logo">
        <div class="hdr-circle">
            <img src="${pageContext.request.contextPath}/images/site-logo.png"
                 alt="logo" style="width:22px;height:22px;object-fit:contain;"
                 onerror="this.style.display='none';this.parentNode.textContent='V'"/>
        </div>
        <span class="hdr-name">Villare<span class="hdr-badge">Super Admin</span></span>
    </div>
    <div class="hdr-right">
        <div class="hdr-user">
            <div class="hdr-avatar">${sessionScope.loggedInUser.fullName.substring(0,1).toUpperCase()}</div>
            <div>
                <div class="hdr-uname">${sessionScope.loggedInUser.fullName}</div>
                <div class="hdr-urole">&#9733; Super Admin</div>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/logout" class="btn-logout">
            <i class="fa-solid fa-right-from-bracket"></i> Logout
        </a>
    </div>
</header>

<%-- ═══════════════════════ FLASH ════════════════════════════ --%>
<c:if test="${not empty sessionScope.flash}">
    <div class="flash"><i class="fa-solid fa-circle-check"></i> ${sessionScope.flash}</div>
    <c:remove var="flash" scope="session"/>
</c:if>

<div class="dash-wrap">

<%-- ═══════════════════════ SIDEBAR ══════════════════════════ --%>
<nav class="sidebar">
    <div class="sb-label">Overview</div>
    <a href="?tab=overview"   class="${activeTab=='overview'   ?'active':''}">
        <i class="fa-solid fa-gauge"></i> Dashboard
    </a>

    <div class="sb-label">Management</div>
    <a href="?tab=users"      class="${activeTab=='users'      ?'active':''}">
        <i class="fa-solid fa-users"></i> Users
    </a>
    <a href="?tab=admins"     class="${activeTab=='admins'     ?'active':''}">
        <i class="fa-solid fa-user-shield"></i> Admins
    </a>
    <a href="?tab=dealers"    class="${activeTab=='dealers'    ?'active':''}">
        <i class="fa-solid fa-handshake"></i> Dealers
    </a>
    <a href="?tab=properties" class="${activeTab=='properties' ?'active':''}">
        <i class="fa-solid fa-house"></i> Properties
    </a>

    <div class="sb-label">Transactions</div>
    <a href="?tab=bookings"   class="${activeTab=='bookings'   ?'active':''}">
        <i class="fa-solid fa-calendar-check"></i> Bookings
    </a>
</nav>

<%-- ═══════════════════════ MAIN ══════════════════════════════ --%>
<main class="dash-main">

<%-- ─────────────── OVERVIEW ──────────────────────────────── --%>
<c:if test="${activeTab == 'overview'}">
    <div class="banner">
        <div>
            <h2>Welcome back, ${sessionScope.loggedInUser.fullName} &#128075;</h2>
            <p>Full control of the Villare Rentals platform.</p>
        </div>
        <div class="banner-btns">
            <button class="btn btn-white btn-sm" onclick="openModal('mdlAdmin')">
                <i class="fa-solid fa-user-plus"></i> Add Admin
            </button>
        </div>
    </div>

    <div class="stat-grid">
        <div class="stat-card">
            <div class="stat-ico ico-purple"><i class="fa-solid fa-users"></i></div>
            <div class="stat-info"><h3>${totalUsers}</h3><p>Total Users</p></div>
        </div>
        <div class="stat-card">
            <div class="stat-ico ico-green"><i class="fa-solid fa-house"></i></div>
            <div class="stat-info"><h3>${totalProperties}</h3><p>Properties</p></div>
        </div>
        <div class="stat-card">
            <div class="stat-ico ico-orange"><i class="fa-solid fa-calendar-check"></i></div>
            <div class="stat-info"><h3>${activeBookings}</h3><p>Active Bookings</p></div>
        </div>
        <div class="stat-card">
            <div class="stat-ico ico-blue"><i class="fa-solid fa-money-bill-wave"></i></div>
            <div class="stat-info">
                <h3>NPR <fmt:formatNumber value="${totalRevenue}" pattern="#,##0"/></h3>
                <p>Total Revenue</p>
            </div>
        </div>
    </div>

    <div class="g2">
        <div class="card">
            <div class="card-head">
                <h3><i class="fa-solid fa-calendar-check" style="color:#6c5fc7;margin-right:5px"></i>Recent Bookings</h3>
                <a href="?tab=bookings" class="btn btn-outline btn-sm">View All</a>
            </div>
            <table>
                <thead>
                    <tr><th>Booking #</th><th>User</th><th>Property</th><th>Status</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="b" items="${recentBookings}">
                        <tr>
                            <td>#${b.bookingNo}</td>
                            <td>${b.userName}</td>
                            <td>${b.propertyTitle}</td>
                            <td><span class="badge b-${b.bookingStatus}">${b.bookingStatus}</span></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty recentBookings}">
                        <tr><td colspan="4" class="empty-td">No bookings yet.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <div class="card">
            <div class="card-head">
                <h3><i class="fa-solid fa-triangle-exclamation" style="color:#e09c3c;margin-right:5px"></i>Pending Actions</h3>
            </div>
            <div class="act-list">
                <div class="act-item">
                    <div class="act-dot orange"><i class="fa-solid fa-house"></i></div>
                    <div class="act-text">
                        <p><b>${pendingProperties.size()}</b> properties awaiting approval</p>
                        <span><a href="?tab=properties&filter=pending">Review &rarr;</a></span>
                    </div>
                </div>
                <div class="act-item">
                    <div class="act-dot purple"><i class="fa-solid fa-user-shield"></i></div>
                    <div class="act-text">
                        <p><b>${totalAdmins}</b> admin accounts active</p>
                        <span><a href="?tab=admins">Manage Admins &rarr;</a></span>
                    </div>
                </div>
                <div class="act-item">
                    <div class="act-dot blue"><i class="fa-solid fa-handshake"></i></div>
                    <div class="act-text">
                        <p><b>${totalDealers}</b> dealers registered</p>
                        <span><a href="?tab=dealers">Manage Dealers &rarr;</a></span>
                    </div>
                </div>
                <div class="act-item">
                    <div class="act-dot green"><i class="fa-solid fa-calendar-check"></i></div>
                    <div class="act-text">
                        <p><b>${totalBookings}</b> total bookings on platform</p>
                        <span><a href="?tab=bookings">View All &rarr;</a></span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- Add Admin Modal --%>
    <div class="overlay" id="mdlAdmin">
        <div class="modal">
            <button class="modal-x" onclick="closeModal('mdlAdmin')">&times;</button>
            <h3><i class="fa-solid fa-user-shield" style="color:#6c5fc7;margin-right:7px"></i>Create Admin</h3>
            <form method="post">
                <input type="hidden" name="action" value="createAdmin"/>
                <div class="field"><label>Full Name</label><input type="text" name="fullName" required/></div>
                <div class="field"><label>Username</label><input type="text" name="username" required/></div>
                <div class="field"><label>Email</label><input type="email" name="email" required/></div>
                <div class="field"><label>Phone</label><input type="text" name="phone"/></div>
                <div class="field"><label>Temporary Password</label><input type="password" name="password" required/></div>
                <div class="modal-foot">
                    <button type="button" class="btn btn-outline" onclick="closeModal('mdlAdmin')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Create Admin</button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<%-- ─────────────── USERS ──────────────────────────────────── --%>
<c:if test="${activeTab == 'users'}">
    <div class="pg-title">
        <h1>User Management</h1>
        <p>All registered accounts across the platform.</p>
    </div>
    <div class="card">
        <div class="card-head">
            <h3>All Users <span class="cnt">(${users.size()})</span></h3>
            <div class="card-head-r">
                <button class="btn btn-primary btn-sm" onclick="openModal('mdlUser')">
                    <i class="fa-solid fa-plus"></i> Add User
                </button>
            </div>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>User</th><th>Phone</th><th>Role</th><th>Status</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${users}" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>
                            <div class="avc">
                                <div class="av">${u.fullName.substring(0,1).toUpperCase()}</div>
                                <div>
                                    <div class="av-name">${u.fullName}</div>
                                    <div class="av-sub">${u.email}</div>
                                </div>
                            </div>
                        </td>
                        <td>${not empty u.phone ? u.phone : '—'}</td>
                        <td><span class="badge b-${u.role}">${u.role}</span></td>
                        <td><span class="badge b-${u.status}">${u.status}</span></td>
                        <td>
                            <c:choose>
                                <c:when test="${u.status == 'active'}">
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="action" value="suspendUser"/>
                                        <input type="hidden" name="userId" value="${u.userNo}"/>
                                        <button class="btn btn-danger btn-sm"
                                            onclick="return confirm('Suspend ${u.fullName}?')">Suspend</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="action" value="restoreUser"/>
                                        <input type="hidden" name="userId" value="${u.userNo}"/>
                                        <button class="btn btn-success btn-sm">Restore</button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                            <form method="post" style="display:inline">
                                <input type="hidden" name="action" value="deleteUser"/>
                                <input type="hidden" name="userId" value="${u.userNo}"/>
                                <button class="btn btn-danger btn-sm"
                                    onclick="return confirm('Permanently delete ${u.fullName}?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty users}">
                    <tr><td colspan="6" class="empty-td">No users found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <div class="overlay" id="mdlUser">
        <div class="modal">
            <button class="modal-x" onclick="closeModal('mdlUser')">&times;</button>
            <h3><i class="fa-solid fa-user-plus" style="color:#6c5fc7;margin-right:7px"></i>Add User</h3>
            <form method="post">
                <input type="hidden" name="action" value="createUser"/>
                <div class="field"><label>Full Name</label><input type="text" name="fullName" required/></div>
                <div class="field"><label>Username</label><input type="text" name="username" required/></div>
                <div class="field"><label>Email</label><input type="email" name="email" required/></div>
                <div class="field"><label>Phone</label><input type="text" name="phone"/></div>
                <div class="field"><label>Password</label><input type="password" name="password" required/></div>
                <div class="field"><label>Role</label>
                    <select name="role">
                        <option value="user">User</option>
                        <option value="dealer">Dealer</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
                <div class="modal-foot">
                    <button type="button" class="btn btn-outline" onclick="closeModal('mdlUser')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Add User</button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<%-- ─────────────── ADMINS ─────────────────────────────────── --%>
<c:if test="${activeTab == 'admins'}">
    <div class="pg-title">
        <h1>Admin Management</h1>
        <p>Only Super Admin can create or revoke admin accounts.</p>
    </div>
    <div class="card">
        <div class="card-head">
            <h3>Admins <span class="cnt">(${admins.size()})</span></h3>
            <button class="btn btn-primary btn-sm" onclick="openModal('mdlAdmin2')">
                <i class="fa-solid fa-user-shield"></i> Create Admin
            </button>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>Admin</th><th>Phone</th><th>Status</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${admins}" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>
                            <div class="avc">
                                <div class="av o">${a.fullName.substring(0,1).toUpperCase()}</div>
                                <div>
                                    <div class="av-name">${a.fullName}</div>
                                    <div class="av-sub">${a.email}</div>
                                </div>
                            </div>
                        </td>
                        <td>${not empty a.phone ? a.phone : '—'}</td>
                        <td><span class="badge b-${a.status}">${a.status}</span></td>
                        <td>
                            <form method="post" style="display:inline">
                                <input type="hidden" name="action" value="revokeAdmin"/>
                                <input type="hidden" name="userId" value="${a.userNo}"/>
                                <button class="btn btn-danger btn-sm"
                                    onclick="return confirm('Revoke admin rights for ${a.fullName}?')">Revoke</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty admins}">
                    <tr><td colspan="5" class="empty-td">No admins found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <div class="overlay" id="mdlAdmin2">
        <div class="modal">
            <button class="modal-x" onclick="closeModal('mdlAdmin2')">&times;</button>
            <h3><i class="fa-solid fa-user-shield" style="color:#6c5fc7;margin-right:7px"></i>Create Admin</h3>
            <form method="post">
                <input type="hidden" name="action" value="createAdmin"/>
                <div class="field"><label>Full Name</label><input type="text" name="fullName" required/></div>
                <div class="field"><label>Username</label><input type="text" name="username" required/></div>
                <div class="field"><label>Email</label><input type="email" name="email" required/></div>
                <div class="field"><label>Phone</label><input type="text" name="phone"/></div>
                <div class="field"><label>Temporary Password</label><input type="password" name="password" required/></div>
                <div class="modal-foot">
                    <button type="button" class="btn btn-outline" onclick="closeModal('mdlAdmin2')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Create Admin</button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<%-- ─────────────── DEALERS ────────────────────────────────── --%>
<c:if test="${activeTab == 'dealers'}">
    <div class="pg-title">
        <h1>Dealer Management</h1>
        <p>Approve, suspend, or remove dealers from the platform.</p>
    </div>
    <div class="card">
        <div class="card-head">
            <h3>All Dealers <span class="cnt">(${dealers.size()})</span></h3>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>Dealer</th><th>Phone</th><th>Status</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${dealers}" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>
                            <div class="avc">
                                <div class="av g">${d.fullName.substring(0,1).toUpperCase()}</div>
                                <div>
                                    <div class="av-name">${d.fullName}</div>
                                    <div class="av-sub">${d.email}</div>
                                </div>
                            </div>
                        </td>
                        <td>${not empty d.phone ? d.phone : '—'}</td>
                        <td><span class="badge b-${d.status}">${d.status}</span></td>
                        <td>
                            <c:choose>
                                <c:when test="${d.status == 'inactive'}">
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="action" value="approveDealer"/>
                                        <input type="hidden" name="userId" value="${d.userNo}"/>
                                        <button class="btn btn-success btn-sm">Approve</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="action" value="suspendDealer"/>
                                        <input type="hidden" name="userId" value="${d.userNo}"/>
                                        <button class="btn btn-danger btn-sm"
                                            onclick="return confirm('Suspend ${d.fullName}?')">Suspend</button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                            <form method="post" style="display:inline">
                                <input type="hidden" name="action" value="deleteUser"/>
                                <input type="hidden" name="userId" value="${d.userNo}"/>
                                <button class="btn btn-danger btn-sm"
                                    onclick="return confirm('Delete ${d.fullName}?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty dealers}">
                    <tr><td colspan="5" class="empty-td">No dealers found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</c:if>

<%-- ─────────────── PROPERTIES ─────────────────────────────── --%>
<c:if test="${activeTab == 'properties'}">
    <div class="pg-title">
        <h1>Property Management</h1>
        <p>Approve pending listings or remove inappropriate ones.</p>
    </div>
    <div class="card">
        <div class="card-head">
            <h3>All Properties <span class="cnt">(${properties.size()})</span></h3>
            <div class="card-head-r">
                <a href="?tab=properties"                  class="btn btn-sm ${empty filter        ?'btn-primary':'btn-outline'}">All</a>
                <a href="?tab=properties&filter=pending"   class="btn btn-sm ${filter=='pending'   ?'btn-primary':'btn-outline'}">Pending</a>
                <a href="?tab=properties&filter=available" class="btn btn-sm ${filter=='available' ?'btn-primary':'btn-outline'}">Available</a>
                <a href="?tab=properties&filter=rented"    class="btn btn-sm ${filter=='rented'    ?'btn-primary':'btn-outline'}">Rented</a>
            </div>
        </div>
        <table>
            <thead>
                <tr><th>ID</th><th>Title</th><th>Location</th><th>Price/mo</th><th>Status</th><th>Dealer</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${properties}">
                    <tr>
                        <td>#${p.propertyNo}</td>
                        <td>${p.title}</td>
                        <td>${p.location}</td>
                        <td>NPR <fmt:formatNumber value="${p.price}" pattern="#,##0"/></td>
                        <td><span class="badge b-${p.availability}">${p.availability}</span></td>
                        <td>${p.dealerName}</td>
                        <td>
                            <c:if test="${p.availability == 'pending'}">
                                <form method="post" style="display:inline">
                                    <input type="hidden" name="action" value="approveProperty"/>
                                    <input type="hidden" name="propertyId" value="${p.propertyNo}"/>
                                    <button class="btn btn-success btn-sm">Approve</button>
                                </form>
                            </c:if>
                            <form method="post" style="display:inline">
                                <input type="hidden" name="action" value="deleteProperty"/>
                                <input type="hidden" name="propertyId" value="${p.propertyNo}"/>
                                <button class="btn btn-danger btn-sm"
                                    onclick="return confirm('Remove: ${p.title}?')">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty properties}">
                    <tr><td colspan="7" class="empty-td">No properties found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</c:if>

<%-- ─────────────── BOOKINGS ───────────────────────────────── --%>
<c:if test="${activeTab == 'bookings'}">
    <div class="pg-title">
        <h1>Booking Management</h1>
        <p>All bookings across the platform.</p>
    </div>
    <div class="card">
        <div class="card-head">
            <h3>All Bookings <span class="cnt">(${bookings.size()})</span></h3>
        </div>
        <table>
            <thead>
                <tr><th>Booking #</th><th>User</th><th>Property</th><th>Check-In</th><th>Check-Out</th><th>Status</th><th>Payment</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="b" items="${bookings}">
                    <tr>
                        <td>#${b.bookingNo}</td>
                        <td>${b.userName}</td>
                        <td>${b.propertyTitle}</td>
                        <td>${b.checkInDate}</td>
                        <td>${b.checkOutDate}</td>
                        <td><span class="badge b-${b.bookingStatus}">${b.bookingStatus}</span></td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty b.paymentStatus}">
                                    <span class="badge b-${b.paymentStatus}">${b.paymentStatus}</span>
                                </c:when>
                                <c:otherwise><span class="badge b-inactive">None</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${b.bookingStatus == 'pending'}">
                                <form method="post" style="display:inline">
                                    <input type="hidden" name="action" value="confirmBooking"/>
                                    <input type="hidden" name="bookingId" value="${b.bookingNo}"/>
                                    <button class="btn btn-success btn-sm">Confirm</button>
                                </form>
                            </c:if>
                            <c:if test="${b.bookingStatus != 'cancelled'}">
                                <form method="post" style="display:inline">
                                    <input type="hidden" name="action" value="cancelBooking"/>
                                    <input type="hidden" name="bookingId" value="${b.bookingNo}"/>
                                    <button class="btn btn-danger btn-sm"
                                        onclick="return confirm('Cancel booking #${b.bookingNo}?')">Cancel</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty bookings}">
                    <tr><td colspan="8" class="empty-td">No bookings found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</c:if>

</main>
</div>

<%-- ═══════════════════════ FOOTER ════════════════════════════ --%>
<footer>
    <div class="footer-top">
        <div class="footer-brand">
            <div class="hdr-circle">V</div>
            <span class="footer-desc">Villare Rentals — Admin Panel</span>
        </div>
        <div class="footer-contact">
            <h4>Contact</h4>
            <p>VillareRentals@gmail.com</p>
            <p>9741688808 &bull; Kathmandu, Nepal</p>
        </div>
    </div>
    <div class="footer-bottom">Copyright &copy; VillareRentals</div>
</footer>

<script>
    function openModal(id)  { document.getElementById(id).classList.add('open'); }
    function closeModal(id) { document.getElementById(id).classList.remove('open'); }
    document.querySelectorAll('.overlay').forEach(o =>
        o.addEventListener('click', e => { if (e.target === o) o.classList.remove('open'); }));
    const f = document.querySelector('.flash');
    if (f) setTimeout(() => f.style.display = 'none', 3500);
</script>
</body>
</html>
