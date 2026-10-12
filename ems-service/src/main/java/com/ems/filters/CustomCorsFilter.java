package com.ems.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter implements Filter {

    @Value("${app.cors.allowed-origins}")
    private List<String> ALLOWED_ORIGINS;

    @Value("${server.servlet.context-path}")
    private String appContext;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");

        if (isAllowedOrigin(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods",
                    "GET,POST,PUT,DELETE,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers",
                    "Authorization, Content-Type, X-Requested-With, Origin, Accept");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Expose-Headers", "Authorization");

            /*
            String jwtToken = request.getHeader("Authorization");
            if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                String token = jwtToken.substring(7);
                // You can add logic here to validate the token if needed
                log.info("JWT Token received: " + token);
            }

            Cookie cookie = new Cookie("EMS-Cookie", "EMS-Cookie-Value");
            cookie.setMaxAge(3600);
            cookie.setPath(appContext);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);*/
        }

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.info("doFilter method called for OPTIONS request: {}", request.getMethod());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    private boolean isAllowedOrigin(String origin) {
        return origin != null && (ALLOWED_ORIGINS.contains(origin) || ALLOWED_ORIGINS.contains("*"));
    }
}