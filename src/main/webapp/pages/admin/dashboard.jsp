<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - HomeRentals</title>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <div class="logo">🏠</div>
                <span class="logo-text">HomeRentals</span>
            </div>
            <nav class="sidebar-nav">
                <ul>
                    <li class="nav-item active"><a href="#" class="nav-link"><span class="nav-icon">📊</span><span class="nav-text">Dashboard</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">💰</span><span class="nav-text">Revenue</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">👥</span><span class="nav-text">Users</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">🏢</span><span class="nav-text">Dealers</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">🏠</span><span class="nav-text">Properties</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">📅</span><span class="nav-text">Bookings</span></a></li>
                </ul>
            </nav>
            <div class="sidebar-footer">
                <div class="user-info">
                    <span class="user-name">Admin</span>
                    <span class="user-role">Administrator</span>
                </div>
                <a href="#" class="logout-btn">Logout</a>
            </div>
        </aside>
        
        <main class="main-content">
            <header class="content-header">
                <div class="header-left">
                    <h1>Dashboard</h1>
                    <div class="breadcrumb">Home / Dashboard</div>
                </div>
            </header>
            
            <div class="stats-grid">
                <div class="stat-card"><div class="stat-icon" style="background:#e8f5e9;color:#2e7d32;">💰</div><div class="stat-info"><h3>$2.4M</h3><p>Total Revenue</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#e3f2fd;color:#1565c0;">🏠</div><div class="stat-info"><h3>1,847</h3><p>Active Listings</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#fff3e0;color:#e65100;">👥</div><div class="stat-info"><h3>2,500</h3><p>Total Users</p></div></div>
                <div class="stat-card"><div class="stat-icon" style="background:#f3e5f5;color:#6a1b9a;">📅</div><div class="stat-info"><h3>820</h3><p>Active Bookings</p></div></div>
            </div>
            
            <div class="dashboard-row">
                <div class="dashboard-card">
                    <div class="card-header"><h2>Recent Users</h2></div>
                    <table class="data-table">
                        <thead><tr><th>Name</th><th>Email</th><th>Status</th><th>Action</th></tr></thead>
                        <tbody>
                            <tr><td>Bikash Shrestha</td><td>bikash@gmail.com</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn">Edit</button></td></tr>
                            <tr><td>Priya Gurung</td><td>priya@gmail.com</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn">Edit</button></td></tr>
                            <tr><td>Ramesh Adhikari</td><td>ramesh@gmail.com</td><td><span class="status-badge pending">Pending</span></td><td><button class="action-btn">Approve</button></td></tr>
                        </tbody>
                    </table>
                </div>
                <div class="dashboard-card">
                    <div class="card-header"><h2>Recent Dealers</h2></div>
                    <table class="data-table">
                        <thead><tr><th>Name</th><th>Company</th><th>Status</th><th>Action</th></tr></thead>
                        <tbody>
                            <tr><td>Arjun Kumar</td><td>Arjun Properties</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn">Edit</button></td></tr>
                            <tr><td>Sara Thapa</td><td>Lakeview Homes</td><td><span class="status-badge active">Active</span></td><td><button class="action-btn">Edit</button></td></tr>
                            <tr><td>Manu K.C.</td><td>City Rentals</td><td><span class="status-badge pending">Pending</span></td><td><button class="action-btn">Approve</button></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>Pending Property Approvals</h2></div>
                <table class="data-table">
                    <thead><tr><th>Property</th><th>Dealer</th><th>Price</th><th>Action</th></tr></thead>
                    <tbody>
                        <tr><td>Luxury Penthouse</td><td>Arjun Kumar</td><td>$65,000/mo</td><td><button class="action-btn">Approve</button></td></tr>
                        <tr><td>Commercial Space</td><td>Real Homes</td><td>$80,000/mo</td><td><button class="action-btn">Approve</button></td></tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>