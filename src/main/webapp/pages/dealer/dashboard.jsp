<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dealer Dashboard - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <div class="logo" style="background:#4CAF50;">🏢</div>
                <span class="logo-text">Dealer Portal</span>
            </div>
            <nav class="sidebar-nav">
                <ul>
                    <li class="nav-item active"><a href="#" class="nav-link"><span class="nav-icon">📊</span><span class="nav-text">Dashboard</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">🏠</span><span class="nav-text">My Properties</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">➕</span><span class="nav-text">Add Property</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">📅</span><span class="nav-text">Bookings</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">💰</span><span class="nav-text">Earnings</span></a></li>
                </ul>
            </nav>
            <div class="sidebar-footer">
                <div class="user-info">
                    <span class="user-name">Arjun Kumar</span>
                    <span class="user-role">Dealer</span>
                </div>
                <a href="#" class="logout-btn">Logout</a>
            </div>
        </aside>
        
        <main class="main-content">
            <header class="content-header">
                <div class="header-left">
                    <h1>Dealer Dashboard</h1>
                    <div class="breadcrumb">Home / Dashboard</div>
                </div>
            </header>
            
            <div class="quick-actions">
                <a href="#" class="quick-btn"><span>➕</span> Add New Property</a>
                <a href="#" class="quick-btn secondary"><span>📋</span> View Bookings</a>
            </div>
            
            <div class="stats-grid">
                <div class="stat-card"><div class="stat-icon" style="background:#e3f2fd;color:#1565c0;">🏠</div><div class="stat-info"><h3>8</h3><p>Total Properties</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#e8f5e9;color:#2e7d32;">✅</div><div class="stat-info"><h3>6</h3><p>Active Listings</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#fff3e0;color:#f57c00;">📅</div><div class="stat-info"><h3>24</h3><p>Total Bookings</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#fce4ec;color:#d32f2f;">💰</div><div class="stat-info"><h3>$45,500</h3><p>Total Earnings</p></div></div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>My Property Listings</h2></div>
                <table class="data-table">
                    <thead><tr><th>Property</th><th>Price</th><th>Status</th><th>Views</th><th>Action</th></tr></thead>
                    <tbody>
                        <tr><td>Luxury Villa - Kathmandu</td><td>$45,000/mo</td><td><span class="status-badge active">Approved</span></td><td>245</td><td><button class="action-btn">Edit</button></td></tr>
                        <tr><td>Modern Apartment - Durbarmarg</td><td>$25,000/mo</td><td><span class="status-badge active">Approved</span></td><td>189</td><td><button class="action-btn">Edit</button></td></tr>
                        <tr><td>Penthouse - Lazimpat</td><td>$65,000/mo</td><td><span class="status-badge pending">Pending</span></td><td>32</td><td><button class="action-btn">Edit</button></td></tr>
                    </tbody>
                </table>
            </div>
            
            <div class="dashboard-card" style="margin-top:25px;">
                <div class="card-header"><h2>Recent Booking Requests</h2></div>
                <table class="data-table">
                    <thead><tr><th>Property</th><th>Guest</th><th>Dates</th><th>Amount</th><th>Action</th></tr></thead>
                    <tbody>
                        <tr><td>Luxury Villa</td><td>Bikash Shrestha</td><td>May 1-5, 2026</td><td>$180,000</td><td><button class="action-btn">Approve</button></td></tr>
                        <tr><td>Modern Apartment</td><td>Priya Gurung</td><td>May 10-15, 2026</td><td>$125,000</td><td><button class="action-btn">Approve</button></td></tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>