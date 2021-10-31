package com.example.springsecurity.config;

import static java.lang.String.format;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.springsecurity.domain.model.User;
import com.example.springsecurity.properties.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

    private final String jwtIssuer = "example.io";
    private final AppProperties appProperties;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public JwtTokenUtil(AppProperties appProperties) {
        this.appProperties = appProperties;
    }


    public String generateAccessToken(User user) {
        return Jwts.builder()
            .setSubject(format("%s,%s", user.getId(), user.getUsername()))
            .setIssuer(jwtIssuer)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
            .signWith(SignatureAlgorithm.HS512,
                appProperties.getSecurity().getJwt().getBase64Secret())
            .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(appProperties.getSecurity().getJwt().getBase64Secret())
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(appProperties.getSecurity().getJwt().getBase64Secret())
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(appProperties.getSecurity().getJwt().getBase64Secret())
            .parseClaimsJws(token)
            .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(appProperties.getSecurity().getJwt().getBase64Secret())
                .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
