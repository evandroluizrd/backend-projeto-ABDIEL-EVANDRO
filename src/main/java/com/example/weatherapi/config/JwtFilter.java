package com.example.weatherapi.config;

import com.example.weatherapi.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Só intercepta rotas protegidas
        if (path.startsWith("/favoritos")) {
            String authHeader = req.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType("application/json");
                res.getWriter().write("{\"erro\":\"Token ausente\"}");
                return;
            }

            String token = authHeader.substring(7);
            if (!JwtUtil.validateToken(token)) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType("application/json");
                res.getWriter().write("{\"erro\":\"Token inválido ou expirado\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
