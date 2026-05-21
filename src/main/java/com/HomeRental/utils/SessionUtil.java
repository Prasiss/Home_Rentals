package com.HomeRental.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    /**
     * Sets a session attribute with an inactive timeout.
     * @param request HttpServletRequest object
     * @param name Session attribute name
     * @param value Value to store in session
     * @param seconds Session timeout in seconds
     */
    public static void setAttribute(HttpServletRequest request, String name, Object value, int seconds) {
        HttpSession session = request.getSession(true);
        session.setAttribute(name, value);
        session.setMaxInactiveInterval(seconds);
    }

    /**
     * Retrieves a session attribute.
     * @param request HttpServletRequest object
     * @param name Attribute name
     * @return Object stored in session or null if not found
     */
    public static Object getAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        return (session != null) ? session.getAttribute(name) : null;
    }

    /**
     * Removes a specific attribute from session.
     * @param request HttpServletRequest object
     * @param name Attribute name to remove
     */
    public static void removeAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(name);
        }
    }

    /**
     * Invalidates the current user session.
     * @param request HttpServletRequest object
     */
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}