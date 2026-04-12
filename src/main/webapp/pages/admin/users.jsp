<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <title>All Users - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-header"><div class="logo-text">HomeRentals</div></div>
            <nav class="sidebar-nav">
                <ul>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dashboard" class="nav-link">Dashboard</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/revenue" class="nav-link">Revenue Analysis</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/platform" class="nav-link">Platform Control</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/listings" class="nav-link">Listing Review</a></li>
                    <li class="nav-item active"><a href="<%= contextPath %>/admin/users" class="nav-link">All Users</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealers" class="nav-link">Dealers</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealer-applications" class="nav-link">Dealer Applications</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/bookings" class="nav-link">Bookings</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/reviews" class="nav-link">Reviews</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/moderation" class="nav-link">Moderation</a></li>
                </ul>
            </nav>
            <div class="sidebar-footer"><a href="#" class="logout-link">Logout</a></div>
        </aside>
        
        <main class="main-content">
            <div class="top-header">
                <div class="profile-section">
                    <div class="profile-info"><div class="profile-name">Admin User</div><div class="profile-role">Administrator</div></div>
                    <div class="profile-avatar">A</div>
                </div>
            </div>
            
            <header class="content-header">
                <div class="header-left"><h1>All Users</h1><div class="breadcrumb"><a href="<%= contextPath %>/admin/dashboard">Home</a> / Users</div></div>
                <div class="header-right"><div class="header-search"><input type="text" placeholder="Search users..."><button>Search</button></div></div>
            </header>
            
            <div class="stats-grid">
                <div class="stat-card"><h3>2,847</h3><p>Total Users</p></div>
                <div class="stat-card"><h3>2,520</h3><p>Active Users</p></div>
                <div class="stat-card"><h3>327</h3><p>Pending</p></div>
                <div class="stat-card"><h3>45</h3><p>Suspended</p></div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>User List</h2></div>
                <div class="table-container">
                    <table class="data-table">
                        <thead><tr><th>User</th><th>Email</th><th>Phone</th><th>Location</th><th>Joined</th><th>Bookings</th><th>Status</th><th>Action</th></tr></thead>
                        <tbody>
                            <tr><td>Bikash Shrestha</td><td>bikash@gmail.com</td><td>9810000001</td><td>Kathmandu</td><td>Jan 15, 2026</td><td>12</td><td><span class="status-badge active">Active</span></td><td><button class="btn">View</button></td></tr>
                            <tr><td>Priya Gurung</td><td>priya@gmail.com</td><td>9810000002</td><td>Pokhara</td><td>Feb 3, 2026</td><td>8</td><td><span class="status-badge active">Active</span></td><td><button class="btn">View</button></td></tr>
                            <tr><td>Ramesh Adhikari</td><td>ramesh@gmail.com</td><td>9810000003</td><td>Biratnagar</td><td>Mar 20, 2026</td><td>0</td><td><span class="status-badge pending">Pending</span></td><td><button class="btn btn-success">Approve</button></td></tr>
                            <tr><td>Sita Sharma</td><td>sita@gmail.com</td><td>9810000004</td><td>Lalitpur</td><td>Dec 10, 2025</td><td>5</td><td><span class="status-badge active">Active</span></td><td><button class="btn">View</button></td></tr>
                            <tr><td>Hari Bahadur</td><td>hari@gmail.com</td><td>9810000005</td><td>Bhaktapur</td><td>Apr 1, 2026</td><td>1</td><td><span class="status-badge suspended">Suspended</span></td><td><button class="btn">View</button></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</body>
</html>