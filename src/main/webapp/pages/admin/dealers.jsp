<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <title>Dealers - HomeRentals</title>
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
                    <li class="nav-item"><a href="<%= contextPath %>/admin/users" class="nav-link">All Users</a></li>
                    <li class="nav-item active"><a href="<%= contextPath %>/admin/dealers" class="nav-link">Dealers</a></li>
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
                <div class="header-left"><h1>Dealers</h1><div class="breadcrumb"><a href="<%= contextPath %>/admin/dashboard">Home</a> / Dealers</div></div>
            </header>
            
            <div class="stats-grid">
                <div class="stat-card"><h3>312</h3><p>Total Dealers</p></div>
                <div class="stat-card"><h3>289</h3><p>Verified Dealers</p></div>
                <div class="stat-card"><h3>23</h3><p>Pending Verification</p></div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>Dealer List</h2></div>
                <div class="table-container">
                    <table class="data-table">
                        <thead><tr><th>Dealer</th><th>Company</th><th>Email</th><th>Phone</th><th>Listings</th><th>Rating</th><th>Status</th><th>Action</th></tr></thead>
                        <tbody>
                            <tr><td>Arjun Kumar</td><td>Arjun Properties</td><td>arjun@homerentals.com</td><td>9800000001</td><td>12</td><td>4.8</td><td><span class="status-badge verified">Verified</span></td><td><button class="btn">View</button></td></tr>
                            <tr><td>Sara Thapa</td><td>Lakeview Homes</td><td>sara@homerentals.com</td><td>9800000002</td><td>8</td><td>4.6</td><td><span class="status-badge verified">Verified</span></td><td><button class="btn">View</button></td></tr>
                            <tr><td>Manu K.C.</td><td>City Rentals</td><td>manu@homerentals.com</td><td>9800000003</td><td>0</td><td>-</td><td><span class="status-badge pending">Pending</span></td><td><button class="btn btn-success">Verify</button></td></tr>
                            <tr><td>Real Homes Nepal</td><td>Real Homes Pvt Ltd</td><td>realhomes@homerentals.com</td><td>9800000004</td><td>15</td><td>4.9</td><td><span class="status-badge verified">Verified</span></td><td><button class="btn">View</button></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</body>
</html>