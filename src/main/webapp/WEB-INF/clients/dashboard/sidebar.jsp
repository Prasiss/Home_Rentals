<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<div class="sidebar">

    <div class="sidebar-brand">
        <h3><i class="fa-solid fa-house"></i> HomeRental</h3>
        <p>User Panel</p>
    </div>

    <ul class="sidebar-nav">

        <li>
            <a href="${pageContext.request.contextPath}/userdashboard">
                <i class="fa-solid fa-chart-line"></i>
                <span>Dashboard</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/home">
                <i class="fa-solid fa-compass"></i>
                <span>Explore</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/booking">
                <i class="fa-solid fa-calendar-check"></i>
                <span>My Bookings</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/wishlist">
                <i class="fa-solid fa-heart"></i>
                <span>Wishlist</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/profile">
                <i class="fa-solid fa-user"></i>
                <span>Profile</span>
            </a>
        </li>

    </ul>
    <div class="sidebar-footer">

        <form method="get"
              action="${pageContext.request.contextPath}/dealerapplication"
              style="margin-bottom:10px;">

            <button type="submit"
                    class="btn btn-primary"
                    style="width:100%; background:#28a745; border:none;">

                <i class="fa-solid fa-store"></i>
                Become a Dealer

            </button>
        </form>

        <a href="${pageContext.request.contextPath}/logout">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Logout</span>
        </a>

    </div>

</div>