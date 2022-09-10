package com.sachin.emeritus.commonlib.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.emeritus.commonlib.security.exceptions.JwtTokenMalformedException;
import com.sachin.emeritus.commonlib.security.exceptions.JwtTokenMissingException;
import com.sachin.emeritus.commonlib.security.vo.UserToken;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Map;

import static com.sachin.emeritus.commonlib.security.util.Claim.*;
import static com.sachin.emeritus.commonlib.security.util.Constant.SECRET;
import static com.sachin.emeritus.commonlib.security.util.Constant.VALIDITY;
import static org.apache.logging.log4j.util.Strings.isEmpty;


public class JwtUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static Boolean hasAuthHeader() {
        final String authHeader = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        if (!isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            return true;
        }
        return false;
    }

    public static String getAuthHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    public static String getToken() {
        String authHeader = getAuthHeader();
        return authHeader.substring(7, authHeader.length());
    }

    public static Claims getClaims() {
        try {
            String token = getToken();
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public static String getUserRole() {
        return objectMapper.convertValue(getClaims().get(USER), UserToken.class).getRole();

    }

    public static String generateToken(UserToken userToken) {
        Claims claims = Jwts.claims().setSubject(userToken.getId());
        claims.put(USER, userToken);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + VALIDITY;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public static void validateToken(final String bearerToken) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            String token = bearerToken.substring(7, bearerToken.length());
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }

}