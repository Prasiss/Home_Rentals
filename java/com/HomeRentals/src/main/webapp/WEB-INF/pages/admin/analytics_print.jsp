<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Analytics Report - HomeRental</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 13px;
            color: #222;
            background: #fff;
            padding: 30px 40px;
        }

        /* Print button  */
        .print-bar {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 28px;
        }
        .btn-print {
            padding: 8px 20px;
            background: #6b5b95;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 13px;
            cursor: pointer;
            font-weight: 600;
        }
        .btn-back {
            padding: 8px 16px;
            background: #f0eef8;
            color: #6b5b95;
            border: 1px solid #c5bde0;
            border-radius: 4px;
            font-size: 13px;
            cursor: pointer;
            text-decoration: none;
            font-weight: 600;
        }
        .print-note {
            font-size: 12px;
            color: #777;
        }

        /*  Report header  */
        .report-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-end;
            border-bottom: 2px solid #6b5b95;
            padding-bottom: 12px;
            margin-bottom: 24px;
        }
        .report-title  { font-size: 22px; font-weight: 700; color: #6b5b95; }
        .report-sub    { font-size: 13px; color: #555; margin-top: 3px; }
        .report-date   { font-size: 12px; color: #777; text-align: right; }

        /*  Section headings  */
        .section-title {
            font-size: 14px;
            font-weight: 700;
            color: #6b5b95;
            background: #f0eef8;
            padding: 5px 10px;
            margin: 22px 0 10px;
            border-left: 4px solid #6b5b95;
        }

        /*  KPI grid */
        .kpi-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 12px;
            margin-bottom: 6px;
        }
        .kpi-card {
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 12px;
            text-align: center;
        }
        .kpi-value { font-size: 20px; font-weight: 700; color: #6b5b95; }
        .kpi-label { font-size: 11px; color: #777; margin-top: 3px; text-transform: uppercase; letter-spacing: 0.4px; }

        /* Data tables  */
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 12px;
            margin-bottom: 4px;
        }
        th {
            background: #6b5b95;
            color: #fff;
            padding: 6px 10px;
            text-align: left;
            font-weight: 600;
            font-size: 11px;
            text-transform: uppercase;
            letter-spacing: 0.3px;
        }
        td {
            padding: 6px 10px;
            border-bottom: 1px solid #eee;
            color: #333;
        }
        tr:last-child td { border-bottom: none; }
        tr:nth-child(even) td { background: #fafafa; }

        /* Two-column layout for tables  */
        .two-col {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        /* Status badges */
        .badge {
            display: inline-block;
            padding: 2px 8px;
            border-radius: 20px;
            font-size: 10px;
            font-weight: 700;
            text-transform: uppercase;
        }
        .badge-success { background: #d4edda; color: #155724; }
        .badge-danger  { background: #f8d7da; color: #721c24; }
        .badge-warning { background: #fff3cd; color: #856404; }

        /*  Footer */
        .report-footer {
            margin-top: 30px;
            border-top: 1px solid #ddd;
            padding-top: 10px;
            font-size: 11px;
            color: #999;
            text-align: center;
        }

        /*  Print media */
        @media print {
            .print-bar { display: none; }
            body { padding: 15px 20px; }
            .kpi-grid { grid-template-columns: repeat(4, 1fr); }
            table { page-break-inside: avoid; }
            .section-title { page-break-after: avoid; }
        }
    </style>
</head>
<body>


<div class="print-bar">
    <button class="btn-print" onclick="window.print()">&#128438; Save as PDF / Print</button>
    <a class="btn-back" href="${pageContext.request.contextPath}/admin/analytics">&#8592; Back to Analytics</a>
    <span class="print-note">Use your browser&#8217;s &#8220;Save as PDF&#8221; destination when printing.</span>
</div>


<div class="report-header">
    <div>
        <div class="report-title">HomeRental &mdash; Analytics Report</div>
        <div class="report-sub">Admin overview &bull; All-time &amp; last 12 months</div>
    </div>
    <div class="report-date">Generated: <c:out value="${reportDate}"/></div>
</div>


<div class="section-title">Summary</div>
<div class="kpi-grid">
    <div class="kpi-card">
        <div class="kpi-value">
            $<fmt:formatNumber value="${totalRevenue}" pattern="#,##0"/>
        </div>
        <div class="kpi-label">Total Revenue</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">
            $<fmt:formatNumber value="${avgRevenue}" pattern="#,##0"/>
        </div>
        <div class="kpi-label">Avg per Booking</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">${totalBookings}</div>
        <div class="kpi-label">Total Bookings</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">${completedBookings}</div>
        <div class="kpi-label">Completed Bookings</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">${totalUsers}</div>
        <div class="kpi-label">Total Users</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">${totalRegularUsers}</div>
        <div class="kpi-label">Regular Users</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">${totalDealers}</div>
        <div class="kpi-label">Active Dealers</div>
    </div>
    <div class="kpi-card">
        <div class="kpi-value">${activeProperties}</div>
        <div class="kpi-label">Active Properties</div>
    </div>
</div>


<div class="section-title">Monthly Trends (Last 12 Months)</div>
<div class="two-col">

    <div>
        <table>
            <thead><tr><th>Month</th><th>Revenue ($)</th></tr></thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty monthlyRevenue}">
                        <tr><td colspan="2">No data available.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${monthlyRevenue}" var="row">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td>$<fmt:formatNumber value="${row[1]}" pattern="#,##0"/></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <div>
        <table>
            <thead><tr><th>Month</th><th>Bookings</th></tr></thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty monthlyBookings}">
                        <tr><td colspan="2">No data available.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${monthlyBookings}" var="row">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td><c:out value="${row[1]}"/></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <div>
        <table>
            <thead><tr><th>Month</th><th>New Users</th></tr></thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty monthlyUsers}">
                        <tr><td colspan="2">No data available.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${monthlyUsers}" var="row">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td><c:out value="${row[1]}"/></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <div>
        <table>
            <thead><tr><th>Month</th><th>Properties Listed</th></tr></thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty monthlyProperties}">
                        <tr><td colspan="2">No data available.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${monthlyProperties}" var="row">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td><c:out value="${row[1]}"/></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

</div>


<div class="section-title">Status Breakdowns</div>
<div class="two-col">

    <div>
        <table>
            <thead><tr><th>Booking Status</th><th>Count</th><th>Share</th></tr></thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty bookingStatus}">
                        <tr><td colspan="3">No data available.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <%-- Compute total in EL using c:set accumulation --%>
                        <c:set var="bTotal" value="0"/>
                        <c:forEach items="${bookingStatus}" var="row">
                            <c:set var="bTotal" value="${bTotal + row[1]}"/>
                        </c:forEach>
                        <c:forEach items="${bookingStatus}" var="row">
                            <tr>
                                <td>
                                    <span class="badge
                                        <c:choose>
                                            <c:when test="${row[0] == 'COMPLETED'}">badge-success</c:when>
                                            <c:when test="${row[0] == 'CANCELLED'}">badge-danger</c:when>
                                            <c:otherwise>badge-warning</c:otherwise>
                                        </c:choose>
                                    "><c:out value="${row[0]}"/></span>
                                </td>
                                <td><c:out value="${row[1]}"/></td>
                                <td>
                                    <fmt:formatNumber
                                        value="${row[1] * 100 / (bTotal > 0 ? bTotal : 1)}"
                                        pattern="#0.0"/>%
                                </td>
                            </tr>
                        </c:forEach>
                        <tr><td colspan="2" style="font-weight:700;">Total</td><td style="font-weight:700;">${bTotal}</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <div>
        <table>
            <thead><tr><th>Property Status</th><th>Count</th><th>Share</th></tr></thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty propertyStatus}">
                        <tr><td colspan="3">No data available.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:set var="pTotal" value="0"/>
                        <c:forEach items="${propertyStatus}" var="row">
                            <c:set var="pTotal" value="${pTotal + row[1]}"/>
                        </c:forEach>
                        <c:forEach items="${propertyStatus}" var="row">
                            <tr>
                                <td>
                                    <span class="badge
                                        <c:choose>
                                            <c:when test="${row[0] == 'APPROVED'}">badge-success</c:when>
                                            <c:when test="${row[0] == 'REJECTED'}">badge-danger</c:when>
                                            <c:otherwise>badge-warning</c:otherwise>
                                        </c:choose>
                                    "><c:out value="${row[0]}"/></span>
                                </td>
                                <td><c:out value="${row[1]}"/></td>
                                <td>
                                    <fmt:formatNumber
                                        value="${row[1] * 100 / (pTotal > 0 ? pTotal : 1)}"
                                        pattern="#0.0"/>%
                                </td>
                            </tr>
                        </c:forEach>
                        <tr><td colspan="2" style="font-weight:700;">Total</td><td style="font-weight:700;">${pTotal}</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

</div>

<div class="section-title">Top Locations by Approved Listings</div>
<table>
    <thead><tr><th>#</th><th>Location</th><th>Listings</th></tr></thead>
    <tbody>
        <c:choose>
            <c:when test="${empty topLocations}">
                <tr><td colspan="3">No data available.</td></tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${topLocations}" var="loc" varStatus="st">
                    <tr>
                        <td>${st.index + 1}</td>
                        <td><c:out value="${loc[0]}"/></td>
                        <td><c:out value="${loc[1]}"/></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>


<div class="report-footer">
    HomeRental Admin &bull; Report generated on <c:out value="${reportDate}"/> &bull; Confidential
</div>

</body>
</html>
