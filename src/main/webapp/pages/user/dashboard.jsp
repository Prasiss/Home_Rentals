<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - HomeRentals</title>
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
                    <li class="nav-item active"><a href="#" class="nav-link"><span class="nav-icon">🏠</span><span class="nav-text">Dashboard</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">🔍</span><span class="nav-text">Explore</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">📅</span><span class="nav-text">My Bookings</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">❤️</span><span class="nav-text">Wishlist</span></a></li>
                    <li class="nav-item"><a href="#" class="nav-link"><span class="nav-icon">👤</span><span class="nav-text">Profile</span></a></li>
                </ul>
            </nav>
            <div class="sidebar-footer">
                <div class="user-info">
                    <span class="user-name">Bikash Shrestha</span>
                    <span class="user-role">Guest</span>
                </div>
                <a href="#" class="logout-btn">Logout</a>
            </div>
        </aside>
        
        <main class="main-content">
            <div class="welcome-banner">
                <h1>Welcome back, Bikash! 👋</h1>
                <p>Ready to find your next home?</p>
            </div>
            
            <div class="search-bar-large">
                <input type="text" placeholder="Search by city, neighborhood...">
                <button>🔍 Search</button>
            </div>
            
            <div class="stats-grid">
                <div class="stat-card"><div class="stat-icon">📅</div><div class="stat-info"><h3>8</h3><p>Total Bookings</p></div></div>
                <div class="stat-card"><div class="stat-icon">✅</div><div class="stat-info"><h3>2</h3><p>Active Stays</p></div></div>
                <div class="stat-card"><div class="stat-icon">🏠</div><div class="stat-info"><h3>5</h3><p>Completed</p></div></div>
                <div class="stat-card"><div class="stat-icon">❤️</div><div class="stat-info"><h3>6</h3><p>Saved</p></div></div>
            </div>
            
            <div class="dashboard-row">
                <div class="dashboard-card">
                    <div class="card-header"><h2>Recommended for You</h2></div>
                    <div class="property-grid">
                        <div class="property-card">
                            <div class="property-image">🏠</div>
                            <div class="property-info">
                                <div class="property-price">$45,000/mo</div>
                                <div class="property-title">Luxury Villa</div>
                                <div class="property-location">📍 Kathmandu</div>
                            </div>
                        </div>
                        <div class="property-card">
                            <div class="property-image">🏢</div>
                            <div class="property-info">
                                <div class="property-price">$25,000/mo</div>
                                <div class="property-title">Modern Apartment</div>
                                <div class="property-location">📍 Durbarmarg</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dashboard-card">
                    <div class="card-header"><h2>Recent Bookings</h2></div>
                    <table class="data-table">
                        <thead><tr><th>Property</th><th>Dates</th><th>Status</th></tr></thead>
                        <tbody>
                            <tr><td>Luxury Villa</td><td>May 1-5, 2026</td><td><span class="status-badge active">Confirmed</span></td></tr>
                            <tr><td>Studio Apartment</td><td>Apr 20-25, 2026</td><td><span class="status-badge" style="background:#e3f2fd;color:#1565c0;">Completed</span></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="dashboard-card">
                <div class="card-header"><h2>❤️ Your Wishlist</h2></div>
                <div class="property-grid">
                    <div class="property-card">
                        <div class="property-image">🏘️</div>
                        <div class="property-info">
                            <div class="property-price">$35,000/mo</div>
                            <div class="property-title">Family Home</div>
                            <div class="property-location">📍 Pokhara</div>
                        </div>
                    </div>
                    <div class="property-card">
                        <div class="property-image">🏰</div>
                        <div class="property-info">
                            <div class="property-price">$65,000/mo</div>
                            <div class="property-title">Penthouse</div>
                            <div class="property-location">📍 Lazimpat</div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</body>
</html>