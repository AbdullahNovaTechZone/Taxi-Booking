package com.novatechzone.taxi.util;

import com.novatechzone.taxi.exception.AccessDeniedException;
import com.novatechzone.taxi.model.AdminUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    private static final String SECRET = "JBX/b]MJ)x0M.}Ve6i(b+[pt,f(T53A8u+Z8Mwx/;UQc@V@&RFm%ubutt)4;x]7X";
    private static final long EXPIRY_DURATION = 1800L;

    public JWTUtil() {
    }

    public String generateAccessToken(AdminUser adminUser) {
        long currentTimeMillis = System.currentTimeMillis();
        long expiryTime = currentTimeMillis + 1800000L;
        Date issuedAt = new Date(currentTimeMillis);
        Date expiryAt = new Date(expiryTime);
        Claims claims = Jwts.claims().setIssuer(String.valueOf(adminUser.getAdminUserId())).setIssuedAt(issuedAt).setExpiration(expiryAt);
        claims.put("username", adminUser.getUsername());
        claims.put("password", adminUser.getPassword());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "JBX/b]MJ)x0M.}Ve6i(b+[pt,f(T53A8u+Z8Mwx/;UQc@V@&RFm%ubutt)4;x]7X").compact();
    }

    public Claims verifyAccessToken(String authorization) throws Exception {
        try {
            return (Claims)Jwts.parser().setSigningKey("JBX/b]MJ)x0M.}Ve6i(b+[pt,f(T53A8u+Z8Mwx/;UQc@V@&RFm%ubutt)4;x]7X").parseClaimsJws(authorization).getBody();
        } catch (Exception var3) {
            throw new AccessDeniedException("Un Authorized");
        }
    }
}