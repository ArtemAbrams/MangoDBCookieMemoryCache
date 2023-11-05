package com.example.mangodbcookiememorycache.service.impl;

import com.example.mangodbcookiememorycache.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class CookieServiceImpl implements CookieService {
    @Override
    public boolean createCookie(HttpServletResponse response,
                                String value,
                                String name,
                                int maxAgeSeconds) {
        try {
            Cookie cookie = new Cookie(name, encodeCookieValue(value));
            cookie.setPath("/");
            cookie.setMaxAge(maxAgeSeconds);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public String retrieveCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return decodeCookieValue(cookie.getValue());
                }
            }
        }
        return null;
    }
    private String decodeCookieValue(String encodedValue) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
        return new String(decodedBytes);
    }
    private String encodeCookieValue(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
}
