<%-- pages/admin/dashboard.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - HomeRentals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar Navigation -->
        <aside class="sidebar" id="sidebar">
            <div class="sidebar-header">
                <div class="logo-container">
                    <img src="${pageContext.request.contextPath}/images/site-logo.png" alt="HomeRentals" class="logo">
                    <span class="logo-text">HomeRentals</span>
                </div>
                <button class="sidebar-toggle" id="sidebarToggle">☰</button>
            </div>
            
            <nav class="sidebar-nav">
                <ul class="nav-menu">
                    <li class="nav-item active">
                        <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link">
                            <span class="nav-icon">📊</span>
                            <span class="nav-text">Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/revenue" class="nav-link">
                            <span class="nav-icon">💰</span>
                            <span class="nav-text">Revenue Analysis</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/platform" class="nav-link">
                            <span class="nav-icon">⚙️</span>
                            <span class="nav-text">Platform Control</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/management" class="nav-link">
                            <span class="nav-icon">👥</span>
                            <span class="nav-text">Admin Management</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/listings" class="nav-link">
                            <span class="nav-icon">🏠</span>
                            <span class="nav-text">Listing Review</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/users" class="nav-link">
                            <span class="nav-icon">👤</span>
                            <span class="nav-text">All Users</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/dealers" class="nav-link">
                            <span class="nav-icon">🏢</span>
                            <span class="nav-text">Dealers</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/bookings" class="nav-link">
                            <span class="nav-icon">📅</span>
                            <span class="nav-text">Bookings</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/reviews" class="nav-link">
                            <span class="nav-icon">⭐</span>
                            <span class="nav-text">Reviews</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/moderation" class="nav-link">
                            <span class="nav-icon">🚩</span>
                            <span class="nav-text">Moderation</span>
                        </a>
                    </li>
                </ul>
            </nav>
            
            <div class="sidebar-footer">
                <div class="user-info">
                    <span class="user-name">${sessionScope.user.fullName}</span>
                    <span class="user-role">Administrator</span>
                </div>
                <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a>
            </div>
        </aside>
        
        <!-- Main Content Area -->
        <main class="main-content">
            <!-- Top Header -->
            <header class="content-header">
                <div class="header-left">
                    <h1>Dashboard</h1>
                    <div class="breadcrumb">
                        <span>Home</span> / <span>Dashboard</span>
                    </div>
                </div>
                <div class="header-right">
                    <div class="search-bar">
                        <input type="text" placeholder="Search...">
                        <button>🔍</button>
                    </div>
                    <div class="notifications">
                        <span class="notification-icon">🔔</span>
                        <span class="notification-badge">3</span>
                    </div>
                    <div class="admin-profile">
                        <img src="${pageContext.request.contextPath}/images/admin-avatar.png" alt="Admin">
                    </div>
                </div>
            </header>
            
            <!-- Dashboard Content -->
            <div class="dashboard-content">
                <!-- Stats Cards Grid -->
                <div class="stats-grid">
                    <div class="stat-card revenue">
                        <div class="stat-icon">💰</div>
                        <div class="stat-info">
                            <h3>$${stats.totalRevenue}</h3>
                            <p>Total Platform Revenue</p>
                            <span class="stat-trend positive">↑ 12.5%</span>
                        </div>
                    </div>
                    
                    <div class="stat-card listings">
                        <div class="stat-icon">🏠</div>
                        <div class="stat-info">
                            <h3>${stats.activeListings}</h3>
                            <p>Total Active Listings</p>
                            <span class="stat-trend positive">↑ 8.2%</span>
                        </div>
                    </div>
                    
                    <div class="stat-card users">
                        <div class="stat-icon">👥</div>
                        <div class="stat-info">
                            <h3>${stats.totalUsers}</h3>
                            <p>Registered Users</p>
                            <span class="stat-trend positive">↑ 15.3%</span>
                        </div>
                    </div>
                    
                    <div class="stat-card bookings">
                        <div class="stat-icon">📅</div>
                        <div class="stat-info">
                            <h3>${stats.activeBookings}</h3>
                            <p>Active Bookings</p>
                            <span class="stat-trend positive">↑ 5.7%</span>
                        </div>
                    </div>
                    
                    <div class="stat-card admins">
                        <div class="stat-icon">🛡️</div>
                        <div class="stat-info">
                            <h3>${stats.activeAdmins}</h3>
                            <p>Active Admins</p>
                        </div>
                    </div>
                    
                    <div class="stat-card dealers">
                        <div class="stat-icon">🏢</div>
                        <div class="stat-info">
                            <h3>${stats.activeDealers}</h3>
                            <p>Active Dealers</p>
                        </div>
                    </div>
                    
                    <div class="stat-card flagged">
                        <div class="stat-icon">🚩</div>
                        <div class="stat-info">
                            <h3>${stats.flaggedReports}</h3>
                            <p>Flagged Post Reports</p>
                        </div>
                    </div>
                    
                    <div class="stat-card uptime">
                        <div class="stat-icon">📈</div>
                        <div class="stat-info">
                            <h3>${stats.uptime}%</h3>
                            <p>Platform Uptime</p>
                        </div>
                    </div>
                </div>
                
                <!-- Revenue Analytics Section -->
                <div class="analytics-section">
                    <div class="section-header">
                        <h2>Revenue Analytics — Last 7 Days</h2>
                        <p>Platform commission + subscription fees</p>
                    </div>
                    
                    <div class="revenue-comparison">
                        <div class="comparison-item">
                            <span class="label">This Week</span>
                            <span class="value">$${revenueData.thisWeek}</span>
                        </div>
                        <div class="comparison-item">
                            <span class="label">Last Week</span>
                            <span class="value">$${revenueData.lastWeek}</span>
                        </div>
                        <div class="comparison-item">
                            <span class="label">Target</span>
                            <span class="value">$${revenueData.target}</span>
                        </div>
                    </div>
                    
                    <div class="chart-container">
                        <canvas id="revenueChart"></canvas>
                    </div>
                </div>
                
                <!-- Two Column Layout -->
                <div class="dashboard-row">
                    <!-- Admin Management Section -->
                    <div class="dashboard-card admin-management">
                        <div class="card-header">
                            <h2>Admin Management</h2>
                            <p>Manage platform admins and permissions</p>
                            <a href="${pageContext.request.contextPath}/admin/management" class="view-all">View All →</a>
                        </div>
                        
                        <div class="table-container">
                            <table class="data-table">
                                <thead>
                                    <tr>
                                        <th>AGENT</th>
                                        <th>REGION</th>
                                        <th>LAST ACTIVE</th>
                                        <th>STATUS</th>
                                        <th>ACTION</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${admins}" var="admin">
                                    <tr>
                                        <td>
                                            <div class="user-cell">
                                                <img src="${admin.avatar}" alt="${admin.name}" class="user-avatar">
                                                <span>${admin.name}</span>
                                            </div>
                                        </td>
                                        <td>${admin.region}</td>
                                        <td>${admin.lastActive}</td>
                                        <td>
                                            <span class="status-badge ${admin.status}">${admin.status}</span>
                                        </td>
                                        <td>
                                            <button class="action-btn edit" onclick="editAdmin(${admin.id})">Edit</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <!-- Audit Logs Section -->
                    <div class="dashboard-card audit-logs">
                        <div class="card-header">
                            <h2>Audit Logs</h2>
                            <a href="${pageContext.request.contextPath}/admin/audit-logs" class="view-all">View All →</a>
                        </div>
                        
                        <div class="audit-log-list">
                            <c:forEach items="${auditLogs}" var="log">
                            <div class="audit-log-item">
                                <div class="log-icon">📋</div>
                                <div class="log-content">
                                    <p class="log-description">
                                        <strong>${log.adminName}</strong> ${log.action} ${log.description}
                                    </p>
                                    <span class="log-time">${log.timeAgo}</span>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                
                <!-- Second Row -->
                <div class="dashboard-row">
                    <!-- Feature Flags Section -->
                    <div class="dashboard-card feature-flags">
                        <div class="card-header">
                            <h2>Feature Flags</h2>
                        </div>
                        
                        <div class="feature-list">
                            <div class="feature-item">
                                <div class="feature-info">
                                    <h3>Instant Booking</h3>
                                    <p>Allow users to book without dealer confirmation</p>
                                </div>
                                <label class="toggle-switch">
                                    <input type="checkbox" ${featureFlags.instantBooking ? 'checked' : ''}>
                                    <span class="toggle-slider"></span>
                                </label>
                            </div>
                            
                            <div class="feature-item">
                                <div class="feature-info">
                                    <h3>Dealer Verification Badge</h3>
                                    <p>Show badge on verified dealer listings</p>
                                </div>
                                <label class="toggle-switch">
                                    <input type="checkbox" ${featureFlags.dealerVerification ? 'checked' : ''}>
                                    <span class="toggle-slider"></span>
                                </label>
                            </div>
                            
                            <div class="feature-item">
                                <div class="feature-info">
                                    <h3>AI Price Suggestions</h3>
                                    <p>Show AI recommendations for pricing</p>
                                </div>
                                <label class="toggle-switch">
                                    <input type="checkbox" ${featureFlags.aiPriceSuggestions ? 'checked' : ''}>
                                    <span class="toggle-slider"></span>
                                </label>
                            </div>
                            
                            <div class="feature-item">
                                <div class="feature-info">
                                    <h3>Guest Checkout</h3>
                                    <p>Allow bookings without account registration</p>
                                </div>
                                <label class="toggle-switch">
                                    <input type="checkbox" ${featureFlags.guestCheckout ? 'checked' : ''}>
                                    <span class="toggle-slider"></span>
                                </label>
                            </div>
                            
                            <div class="feature-item">
                                <div class="feature-info">
                                    <h3>Maintenance Mode</h3>
                                    <p>Take platform offline for maintenance</p>
                                </div>
                                <label class="toggle-switch">
                                    <input type="checkbox" ${featureFlags.maintenanceMode ? 'checked' : ''}>
                                    <span class="toggle-slider"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Flagged Content Section -->
                    <div class="dashboard-card flagged-content">
                        <div class="card-header">
                            <h2>Flagged Content</h2>
                            <p>Reported listings & user content</p>
                        </div>
                        
                        <div class="table-container">
                            <table class="data-table">
                                <thead>
                                    <tr>
                                        <th>TYPE</th>
                                        <th>SUBJECT</th>
                                        <th>REPORTS</th>
                                        <th>ACTION</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${flaggedContent}" var="item">
                                    <tr>
                                        <td>${item.type}</td>
                                        <td>
                                            <div class="subject-cell">
                                                <span class="subject-title">${item.title}</span>
                                                <span class="subject-dealer">${item.dealer}</span>
                                            </div>
                                        </td>
                                        <td>
                                            <span class="report-count">${item.reports}</span>
                                        </td>
                                        <td>
                                            <button class="action-btn remove" onclick="removeContent(${item.id})">Remove</button>
                                            <button class="action-btn dismiss" onclick="dismissReport(${item.id})">Dismiss</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <!-- Pending Approvals Section -->
                <div class="dashboard-card pending-approvals">
                    <div class="card-header">
                        <h2>Pending Property Approvals</h2>
                        <a href="${pageContext.request.contextPath}/admin/listings" class="view-all">View All →</a>
                    </div>
                    
                    <div class="table-container">
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>PROPERTY</th>
                                    <th>DEALER</th>
                                    <th>PRICE</th>
                                    <th>SUBMITTED</th>
                                    <th>ACTION</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pendingProperties}" var="property">
                                <tr>
                                    <td>
                                        <div class="property-cell">
                                            <img src="${property.mainImage}" alt="${property.title}" class="property-thumb">
                                            <div>
                                                <span class="property-title">${property.title}</span>
                                                <span class="property-location">${property.location}</span>
                                            </div>
                                        </div>
                                    </td>
                                    <td>${property.dealerName}</td>
                                    <td>$${property.price}/month</td>
                                    <td>${property.submittedDate}</td>
                                    <td>
                                        <button class="action-btn approve" onclick="approveProperty(${property.id})">Approve</button>
                                        <button class="action-btn reject" onclick="rejectProperty(${property.id})">Reject</button>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        // Revenue Chart
        const ctx = document.getElementById('revenueChart').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                datasets: [{
                    label: 'Revenue',
                    data: ${revenueData.chartData},
                    borderColor: '#4CAF50',
                    backgroundColor: 'rgba(76, 175, 80, 0.1)',
                    tension: 0.4,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false }
                }
            }
        });
        
        // Sidebar Toggle
        document.getElementById('sidebarToggle').addEventListener('click', function() {
            document.getElementById('sidebar').classList.toggle('collapsed');
        });
        
        function approveProperty(id) { /* Implementation */ }
        function rejectProperty(id) { /* Implementation */ }
        function editAdmin(id) { /* Implementation */ }
        function removeContent(id) { /* Implementation */ }
        function dismissReport(id) { /* Implementation */ }
    </script>
</body>
</html>