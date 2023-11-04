package com.example.mangodbcookiememorycache.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {
    boolean createCookie(HttpServletResponse response, String value, String name, int maxAgeSeconds);
    String retrieveCookie(HttpServletRequest request, String cookieName);
}
