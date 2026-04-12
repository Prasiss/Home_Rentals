<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <title>Dashboard - HomeRentals</title>
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
                    <li class="nav-item active"><a href="<%= contextPath %>/dashboard" class="nav-link">Dashboard</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/explore" class="nav-link">Explore</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/bookings" class="nav-link">My Bookings</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/wishlist" class="nav-link">Wishlist</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/cart" class="nav-link">Cart</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/apply-dealer" class="nav-link">Become a Dealer</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/messages" class="nav-link">Messages</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/saved-searches" class="nav-link">Saved Searches</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/profile" class="nav-link">Profile</a></li>
                    <li class="nav-item"><a href="<%= contextPath %>/settings" class="nav-link">Settings</a></li>
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
                        <div class="profile-name">Bikash Shrestha</div>
                        <div class="profile-role">Guest</div>
                    </div>
                    <div class="profile-avatar">B</div>
                </div>
            </div>
            
            <div class="welcome-banner">
                <h1>Welcome back, Bikash</h1>
                <p>Ready to find your next home? We have 1,847 properties waiting for you.</p>
            </div>
            
            <div class="search-section">
                <div class="search-bar">
                    <input type="text" placeholder="Search by city, neighborhood...">
                    <button>Search</button>
                </div>
                <div class="filter-tags">
                    <span class="filter-tag active">All</span>
                    <span class="filter-tag">Houses</span>
                    <span class="filter-tag">Apartments</span>
                    <span class="filter-tag">Villas</span>
                    <span class="filter-tag">Pet Friendly</span>
                </div>
            </div>
            
            <div class="stats-grid">
                <div class="stat-card"><h3>12</h3><p>Total Bookings</p></div>
                <div class="stat-card"><h3>3</h3><p>Active Stays</p></div>
                <div class="stat-card"><h3>8</h3><p>Completed Stays</p></div>
                <div class="stat-card"><h3>6</h3><p>Saved Properties</p></div>
            </div>
            
            <div class="dashboard-row">
                <div class="dashboard-card">
                    <div class="card-header">
                        <h2>Recommended for You</h2>
                        <a href="<%= contextPath %>/explore" class="view-all">View All</a>
                    </div>
                    <div class="property-grid">
                        <div class="property-card">
                            <div class="property-image">Property Image</div>
                            <div class="property-info">
                                <div class="property-price">$45,000<span>/mo</span></div>
                                <div class="property-title">Luxury Villa with Pool</div>
                                <div class="property-location">Baluwatar, Kathmandu</div>
                                <div class="property-features">
                                    <span>4 Beds</span>
                                    <span>3 Baths</span>
                                    <span>2,800 sqft</span>
                                </div>
                                <button class="wishlist-btn">Save to Wishlist</button>
                            </div>
                        </div>
                        <div class="property-card">
                            <div class="property-image">Property Image</div>
                            <div class="property-info">
                                <div class="property-price">$25,000<span>/mo</span></div>
                                <div class="property-title">Modern Apartment</div>
                                <div class="property-location">Durbarmarg, Kathmandu</div>
                                <div class="property-features">
                                    <span>2 Beds</span>
                                    <span>2 Baths</span>
                                    <span>1,200 sqft</span>
                                </div>
                                <button class="wishlist-btn">Save to Wishlist</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="dashboard-card">
                        <div class="card-header">
                            <h2>Recent Bookings</h2>
                            <a href="<%= contextPath %>/bookings" class="view-all">View All</a>
                        </div>
                        <div class="table-container">
                            <table class="data-table">
                                <thead><tr><th>Property</th><th>Dates</th><th>Status</th></tr></thead>
                                <tbody>
                                    <tr><td>Luxury Villa</td><td>May 1-5, 2026</td><td><span class="status-badge confirmed">Confirmed</span></td></tr>
                                    <tr><td>Studio Apartment</td><td>Apr 20-25, 2026</td><td><span class="status-badge completed">Completed</span></td></tr>
                                    <tr><td>Family Home</td><td>Jun 10-15, 2026</td><td><span class="status-badge pending">Pending</span></td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="dashboard-card" style="margin-top:20px;">
                        <div class="card-header">
                            <h2>Your Cart</h2>
                            <a href="<%= contextPath %>/cart" class="view-all">View Cart</a>
                        </div>
                        <div class="cart-preview">
                            <div class="cart-item"><span>Luxury Villa (4 nights)</span><span>$180,000</span></div>
                            <div class="cart-item"><span>Cleaning Fee</span><span>$5,000</span></div>
                            <div class="cart-item"><span>Service Fee</span><span>$18,000</span></div>
                            <div class="cart-total"><span>Total</span><span>$203,000</span></div>
                            <button class="checkout-btn">Proceed to Checkout</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="dashboard-card" style="margin-top:25px;">
                <div class="card-header">
                    <h2>Your Wishlist</h2>
                    <a href="<%= contextPath %>/wishlist" class="view-all">View All</a>
                </div>
                <div class="property-grid">
                    <div class="property-card">
                        <div class="property-image">Property Image</div>
                        <div class="property-info">
                            <div class="property-price">$65,000<span>/mo</span></div>
                            <div class="property-title">Penthouse Suite</div>
                            <div class="property-location">Lazimpat, Kathmandu</div>
                        </div>
                    </div>
                    <div class="property-card">
                        <div class="property-image">Property Image</div>
                        <div class="property-info">
                            <div class="property-price">$35,000<span>/mo</span></div>
                            <div class="property-title">Family Garden Home</div>
                            <div class="property-location">Lakeside, Pokhara</div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</body>
</html>