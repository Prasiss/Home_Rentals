<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Users - HomeRentals</title>
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
                    <li class="nav-item active"><a href="<%= contextPath %>/admin/users" class="nav-link"><span class="nav-icon">👤</span><span class="nav-text">All Users</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealers" class="nav-link"><span class="nav-icon">🏢</span><span class="nav-text">Dealers</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealer-applications" class="nav-link"><span class="nav-icon">📝</span><span class="nav-text">Dealer Applications</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/bookings" class="nav-link"><span class="nav-icon">📅</span><span class="nav-text">Bookings</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/reviews" class="nav-link"><span class="nav-icon">⭐</span><span class="nav-text">Reviews</span></a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/moderation" class="nav-link"><span class="nav-icon">🚩</span><span class="nav-text">Moderation</span></a></li>
                </ul>
            </nav>
            <div class="sidebar-footer"><div class="user-info"><div class="user-avatar">A</div><div class="user-details"><span class="user-name">Admin User</span><span class="user-role">Administrator</span></div></div><a href="#" class="logout-btn"><span>🚪</span><span>Logout</span></a></div>
        </aside>
        
        <main class="main-content">
            <header class="content-header"><div class="header-left"><h1>All Users</h1><div class="breadcrumb"><a href="<%= contextPath %>/admin/dashboard">Home</a> / Users</div></div></header>
            
            <div class="stats-grid">
                <div class="stat-card"><div class="stat-icon" style="background:#dbeafe;color:#2563eb;">👥</div><div class="stat-info"><h3>2,847</h3><p>Total Users</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#dcfce7;color:#15803d;">✅</div><div class="stat-info"><h3>2,520</h3><p>Active Users</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#fef3c7;color:#d97706;">⏳</div><div class="stat-info"><h3>327</h3><p>Pending</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#fee2e2;color:#dc2626;">🚫</div><div class="stat-info"><h3>45</h3><p>Suspended</p></div></div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>User List</h2></div>
                <table class="data-table">
                    <thead><tr><th>User</th><th>Email</th><th>Phone</th><th>Location</th><th>Joined</th><th>Bookings</th><th>Status</th><th>Action</th></tr></thead>
                    <tbody>
                        <tr><td><strong>Bikash Shrestha</strong></td><td>bikash@gmail.com</td><td>9810000001</td><td>Kathmandu</td><td>Jan 15, 2026</td><td>12</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn view">View</button></td></tr>
                        <tr><td><strong>Priya Gurung</strong></td><td>priya@gmail.com</td><td>9810000002</td><td>Pokhara</td><td>Feb 3, 2026</td><td>8</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn view">View</button></td></tr>
                        <tr><td><strong>Ramesh Adhikari</strong></td><td>ramesh@gmail.com</td><td>9810000003</td><td>Biratnagar</td><td>Mar 20, 2026</td><td>0</td><td><span class="status-badge pending">Pending</span></td><td><button class="action-btn approve">Approve</button></td></tr>
                        <tr><td><strong>Sita Sharma</strong></td><td>sita@gmail.com</td><td>9810000004</td><td>Lalitpur</td><td>Dec 10, 2025</td><td>5</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn view">View</button></td></tr>
                        <tr><td><strong>Hari Bahadur</strong></td><td>hari@gmail.com</td><td>9810000005</td><td>Bhaktapur</td><td>Apr 1, 2026</td><td>1</td><td><span class="status-badge suspended">Suspended</span></td><td><button class="action-btn view">View</button></td></tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>