<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dealers - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-header"><div class="logo">🏠</div><span class="logo-text">HomeRentals</span></div>
            <nav class="sidebar-nav">
                <ul>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dashboard" class="nav-link"><span class="nav-icon">📊</span><span class="nav-text">Dashboard</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/revenue" class="nav-link"><span class="nav-icon">💰</span><span class="nav-text">Revenue Analysis</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/platform" class="nav-link"><span class="nav-icon">⚙️</span><span class="nav-text">Platform Control</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/listings" class="nav-link"><span class="nav-icon">🏠</span><span class="nav-text">Listing Review</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/users" class="nav-link"><span class="nav-icon">👤</span><span class="nav-text">All Users</span></a></li>
                    <li class="nav-item active"><a href="<%= contextPath %>/admin/dealers" class="nav-link"><span class="nav-icon">🏢</span><span class="nav-text">Dealers</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealer-applications" class="nav-link"><span class="nav-icon">📝</span><span class="nav-text">Dealer Applications</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/bookings" class="nav-link"><span class="nav-icon">📅</span><span class="nav-text">Bookings</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/reviews" class="nav-link"><span class="nav-icon">⭐</span><span class="nav-text">Reviews</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/moderation" class="nav-link"><span class="nav-icon">🚩</span><span class="nav-text">Moderation</span></a></li>
                </ul>
            </nav>
            <div class="sidebar-footer"><div class="user-info"><div class="user-avatar">A</div><div class="user-details"><span class="user-name">Admin User</span><span class="user-role">Administrator</span></div></div><a href="#" class="logout-btn"><span>🚪</span><span>Logout</span></a></div>
        </aside>
        
        <main class="main-content">
            <header class="content-header"><div class="header-left"><h1>Dealers</h1><div class="breadcrumb"><a href="<%= contextPath %>/admin/dashboard">Home</a> / Dealers</div></div></header>
            
            <div class="stats-grid">
                <div class="stat-card"><div class="stat-icon" style="background:#dbeafe;color:#2563eb;">🏢</div><div class="stat-info"><h3>312</h3><p>Total Dealers</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#dcfce7;color:#15803d;">✅</div><div class="stat-info"><h3>289</h3><p>Verified Dealers</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#fef3c7;color:#d97706;">⏳</div><div class="stat-info"><h3>23</h3><p>Pending Verification</p></div></div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>Dealer List</h2></div>
                <table class="data-table">
                    <thead><tr><th>Dealer</th><th>Company</th><th>Email</th><th>Phone</th><th>Listings</th><th>Rating</th><th>Status</th><th>Action</th></tr></thead>
                    <tbody>
                        <tr><td><strong>Arjun Kumar</strong></td><td>Arjun Properties</td><td>arjun@homerentals.com</td><td>9800000001</td><td>12</td><td>4.8 ⭐</td><td><span class="status-badge verified">Verified</span></td><td><button class="action-btn view">View</button></td></tr>
                        <tr><td><strong>Sara Thapa</strong></td><td>Lakeview Homes</td><td>sara@homerentals.com</td><td>9800000002</td><td>8</td><td>4.6 ⭐</td><td><span class="status-badge verified">Verified</span></td><td><button class="action-btn view">View</button></td></tr>
                        <tr><td><strong>Manu K.C.</strong></td><td>City Rentals</td><td>manu@homerentals.com</td><td>9800000003</td><td>0</td><td>-</td><td><span class="status-badge pending">Pending</span></td><td><button class="action-btn approve">Verify</button></td></tr>
                        <tr><td><strong>Real Homes Nepal</strong></td><td>Real Homes Pvt Ltd</td><td>realhomes@homerentals.com</td><td>9800000004</td><td>15</td><td>4.9 ⭐</td><td><span class="status-badge verified">Verified</span></td><td><button class="action-btn view">View</button></td></tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>