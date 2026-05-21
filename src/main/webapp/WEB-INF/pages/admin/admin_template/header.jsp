<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="top-header">
    <div>
        <h1>${not empty pageTitle ? pageTitle : 'Dashboard'}</h1>
        <p class="breadcrumb">Home / ${not empty pageTitle ? pageTitle : 'Dashboard'}</p>
    </div>
    <div>
        <span style="font-weight:600;">${sessionScope.username}</span>
        <span style="font-size:12px;color:#777;margin-left:10px;">Administrator</span>
    </div>
</div>