package com.novatechzone.taxi.config;

import com.novatechzone.taxi.dto.RequestMetaDTO;
import com.novatechzone.taxi.util.JWTUtil;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    JWTUtil jwtUtil;
    RequestMetaDTO requestMetaDTO;

    public JWTInterceptor(RequestMetaDTO requestMetaDTO) {
        this.requestMetaDTO = requestMetaDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("authorization");
        if (!request.getRequestURI().contains("auth/") && !request.getRequestURI().contains("admin/view/") && !request.getRequestURI().contains("assets/")) {
            Claims claims = this.jwtUtil.verifyAccessToken(auth);
            this.requestMetaDTO.setAdminUserId(Integer.parseInt(claims.getIssuer()));
            this.requestMetaDTO.setUsername(claims.get("username").toString());
            this.requestMetaDTO.setPassword(claims.get("password").toString());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
