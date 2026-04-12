<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <title>Admin Dashboard - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <div class="logo-text">HomeRentals</div>
            </div>
            <nav class="sidebar-nav">
                <ul>
                    <li class="nav-item active"><a href="<%= contextPath %>/admin/dashboard" class="nav-link">Dashboard</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/revenue" class="nav-link">Revenue Analysis</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/platform" class="nav-link">Platform Control</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/listings" class="nav-link">Listing Review</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/users" class="nav-link">All Users</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealers" class="nav-link">Dealers</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/dealer-applications" class="nav-link">Dealer Applications</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/bookings" class="nav-link">Bookings</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/reviews" class="nav-link">Reviews</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/admin/moderation" class="nav-link">Moderation</a></li>
                </ul>
            </nav>
            <div class="sidebar-footer">
                <a href="#" class="logout-link">Logout</a>
            </div>
        </aside>
        
        <main class="main-content">
            <div class="top-header">
                <div class="profile-section">
                    <div class="profile-info">
                        <div class="profile-name">Admin User</div>
                        <div class="profile-role">Administrator</div>
                    </div>
                    <div class="profile-avatar">A</div>
                </div>
            </div>
            
            <header class="content-header">
                <div class="header-left">
                    <h1>Dashboard</h1>
                    <div class="breadcrumb"><a href="<%= contextPath %>/admin/dashboard">Home</a> / Dashboard</div>
                </div>
                <div class="header-right">
                    <div class="header-search">
                        <input type="text" placeholder="Search...">
                        <button>Search</button>
                    </div>
                </div>
            </header>
            
            <div class="stats-grid">
                <div class="stat-card"><h3>$2.4M</h3><p>Total Revenue</p><span class="stat-trend">+12.5%</span></div>
                <div class="stat-card"><h3>1,847</h3><p>Active Listings</p><span class="stat-trend">+8.2%</span></div>
                <div class="stat-card"><h3>2,847</h3><p>Total Users</p><span class="stat-trend">+15.3%</span></div>
                <div class="stat-card"><h3>820</h3><p>Active Bookings</p><span class="stat-trend">+5.7%</span></div>
            </div>
            
            <div class="dashboard-row">
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>Recent Users</h2>
                        <a href="<%= contextPath %>/admin/users" class="view-all">View All</a>
                    </div>
                    <div class="table-container">
                        <table class="data-table">
                            <thead><tr><th>User</th><th>Email</th><th>Joined</th><th>Status</th><th>Action</th></tr></thead>
                            <tbody>
                                <tr><td>Bikash Shrestha</td><td>bikash@gmail.com</td><td>2 days ago</td><td><span class="status-badge active">Active</span></td><td><button class="btn">View</button></td></tr>
                                <tr><td>Priya Gurung</td><td>priya@gmail.com</td><td>5 days ago</td><td><span class="status-badge active">Active</span></td><td><button class="btn">View</button></td></tr>
                                <tr><td>Ramesh Adhikari</td><td>ramesh@gmail.com</td><td>1 week ago</td><td><span class="status-badge pending">Pending</span></td><td><button class="btn btn-success">Approve</button></td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>Recent Dealers</h2>
                        <a href="<%= contextPath %>/admin/dealers" class="view-all">View All</a>
                    </div>
                    <div class="table-container">
                        <table class="data-table">
                            <thead><tr><th>Dealer</th><th>Company</th><th>Listings</th><th>Status</th><th>Action</th></tr></thead>
                            <tbody>
                                <tr><td>Arjun Kumar</td><td>Arjun Properties</td><td>12</td><td><span class="status-badge verified">Verified</span></td><td><button class="btn">View</button></td></tr>
                                <tr><td>Sara Thapa</td><td>Lakeview Homes</td><td>8</td><td><span class="status-badge verified">Verified</span></td><td><button class="btn">View</button></td></tr>
                                <tr><td>Manu K.C.</td><td>City Rentals</td><td>0</td><td><span class="status-badge pending">Pending</span></td><td><button class="btn btn-success">Approve</button></td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <div class="dashboard-row">
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>Pending Dealer Applications</h2>
                        <a href="<%= contextPath %>/admin/dealer-applications" class="view-all">View All</a>
                    </div>
                    <div class="table-container">
                        <table class="data-table">
                            <thead><tr><th>Applicant</th><th>Company</th><th>Applied</th><th>Action</th></tr></thead>
                            <tbody>
                                <tr><td>Arjun Kumar</td><td>Arjun Properties</td><td>2 days ago</td><td><button class="btn btn-primary">Review</button></td></tr>
                                <tr><td>Sara Thapa</td><td>Lakeview Homes</td><td>1 day ago</td><td><button class="btn btn-primary">Review</button></td></tr>
                                <tr><td>Manu K.C.</td><td>City Rentals</td><td>3 hours ago</td><td><button class="btn btn-primary">Review</button></td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>Audit Logs</h2>
                        <a href="#" class="view-all">View All</a>
                    </div>
                    <div class="audit-log-item"><div class="log-content"><p><strong>Admin</strong> banned user @spam_user</p><span class="log-time">1 min ago</span></div></div>
                    <div class="audit-log-item"><div class="log-content"><p><strong>Listing approved</strong> Luxury Villa - Kathmandu</p><span class="log-time">10 min ago</span></div></div>
                    <div class="audit-log-item"><div class="log-content"><p><strong>Feature flag</strong> instant_booking turned ON</p><span class="log-time">11 min ago</span></div></div>
                    <div class="audit-log-item"><div class="log-content"><p><strong>New admin</strong> Sara Reeves created</p><span class="log-time">13 min ago</span></div></div>
                    <div class="audit-log-item"><div class="log-content"><p><strong>Database backup</strong> completed</p><span class="log-time">14 min ago</span></div></div>
                </div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header">
                    <h2>Pending Property Approvals</h2>
                    <a href="<%= contextPath %>/admin/listings" class="view-all">View All</a>
                </div>
                <div class="table-container">
                    <table class="data-table">
                        <thead><tr><th>Property</th><th>Dealer</th><th>Price</th><th>Submitted</th><th>Action</th></tr></thead>
                        <tbody>
                            <tr><td>Luxury Penthouse - Kathmandu</td><td>Arjun Kumar</td><td>$65,000/mo</td><td>2 days ago</td><td><button class="btn btn-success">Approve</button> <button class="btn btn-danger">Reject</button></td></tr>
                            <tr><td>Commercial Space - Jhamsikhel</td><td>Real Homes</td><td>$80,000/mo</td><td>1 day ago</td><td><button class="btn btn-success">Approve</button> <button class="btn btn-danger">Reject</button></td></tr>
                            <tr><td>Family Home - Pokhara</td><td>Sara Thapa</td><td>$35,000/mo</td><td>5 hours ago</td><td><button class="btn btn-success">Approve</button> <button class="btn btn-danger">Reject</button></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</body>
</html>