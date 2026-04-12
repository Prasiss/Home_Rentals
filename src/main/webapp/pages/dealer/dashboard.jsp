<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <title>Dealer Dashboard - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <div class="logo-text">Dealer Portal</div>
            </div>
            <nav class="sidebar-nav">
                <ul>
                    <li class="nav-item active"><a href="<%= contextPath %>/dealer/dashboard" class="nav-link">Dashboard</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/properties" class="nav-link">My Properties</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/properties/add" class="nav-link">Add Property</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/bookings" class="nav-link">Bookings</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/earnings" class="nav-link">Earnings</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/calendar" class="nav-link">Availability</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/reviews" class="nav-link">Reviews</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/analytics" class="nav-link">Analytics</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/dealer/profile" class="nav-link">Profile</a></li>
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
                        <div class="profile-name">Arjun Kumar</div>
                        <div class="profile-role">Verified Dealer</div>
                    </div>
                    <div class="profile-avatar">A</div>
                </div>
            </div>
            
            <header class="content-header">
                <div class="header-left">
                    <h1>Dealer Dashboard</h1>
                    <div class="breadcrumb"><a href="<%= contextPath %>/dealer/dashboard">Home</a> / Dashboard</div>
                </div>
                <div class="header-right">
                    <span class="verification-badge">Verified Dealer</span>
                </div>
            </header>
            
            <div class="quick-actions">
                <a href="<%= contextPath %>/dealer/properties/add" class="quick-btn">Add New Property</a>
                <a href="<%= contextPath %>/dealer/calendar" class="quick-btn secondary">Update Availability</a>
                <a href="<%= contextPath %>/dealer/bookings" class="quick-btn secondary">View All Bookings</a>
            </div>
            
            <div class="stats-grid">
                <div class="stat-card"><h3>12</h3><p>Total Properties</p></div>
                <div class="stat-card"><h3>9</h3><p>Active Listings</p></div>
                <div class="stat-card"><h3>34</h3><p>Total Bookings</p></div>
                <div class="stat-card"><h3>$58,200</h3><p>Total Earnings</p></div>
            </div>
            
            <div class="dashboard-row">
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>My Property Listings</h2>
                        <a href="<%= contextPath %>/dealer/properties" class="view-all">View All</a>
                    </div>
                    <div class="table-container">
                        <table class="data-table">
                            <thead><tr><th>Property</th><th>Type</th><th>Price</th><th>Status</th><th>Views</th><th>Action</th></tr></thead>
                            <tbody>
                                <tr><td>Luxury Villa<br><small>Kathmandu</small></td><td>Villa</td><td>$45,000/mo</td><td><span class="status-badge approved">Approved</span></td><td>342</td><td><button class="btn">Edit</button></td></tr>
                                <tr><td>Modern Apartment<br><small>Durbarmarg</small></td><td>Apartment</td><td>$25,000/mo</td><td><span class="status-badge approved">Approved</span></td><td>256</td><td><button class="btn">Edit</button></td></tr>
                                <tr><td>Penthouse Suite<br><small>Lazimpat</small></td><td>Penthouse</td><td>$65,000/mo</td><td><span class="status-badge pending">Pending</span></td><td>48</td><td><button class="btn">Edit</button></td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>Recent Booking Requests</h2>
                        <a href="<%= contextPath %>/dealer/bookings" class="view-all">View All</a>
                    </div>
                    <div class="table-container">
                        <table class="data-table">
                            <thead><tr><th>Property</th><th>Guest</th><th>Dates</th><th>Amount</th><th>Action</th></tr></thead>
                            <tbody>
                                <tr><td>Luxury Villa</td><td>Bikash Shrestha</td><td>May 1-5, 2026</td><td>$180,000</td><td><button class="btn btn-success">Approve</button> <button class="btn btn-danger">Reject</button></td></tr>
                                <tr><td>Modern Apartment</td><td>Priya Gurung</td><td>May 10-15, 2026</td><td>$125,000</td><td><button class="btn btn-success">Approve</button> <button class="btn btn-danger">Reject</button></td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header">
                    <h2>Upcoming Bookings</h2>
                </div>
                <div class="table-container">
                    <table class="data-table">
                        <thead><tr><th>Property</th><th>Guest</th><th>Check-in</th><th>Check-out</th><th>Amount</th><th>Status</th></tr></thead>
                        <tbody>
                            <tr><td>Luxury Villa</td><td>Ramesh Adhikari</td><td>Jun 1, 2026</td><td>Jun 7, 2026</td><td>$270,000</td><td><span class="status-badge confirmed">Confirmed</span></td></tr>
                            <tr><td>Modern Apartment</td><td>Sita Sharma</td><td>May 20, 2026</td><td>May 25, 2026</td><td>$125,000</td><td><span class="status-badge confirmed">Confirmed</span></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</body>
</html>