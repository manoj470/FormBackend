package com.clover.form.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtil {

    private static final String SECRET_KEY="secret";

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${bezkoder.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        System.out.println(cookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(UserDetails userPrincipal) {
        System.out.println("Creating cookie...."+userPrincipal);
        String jwt = generateToken(userPrincipal);
        return ResponseCookie.from(jwtCookie, jwt).path("/emp")
                .maxAge(2 * 60 * 60)
                .httpOnly(true).build();
    }

    public ResponseCookie getCleanJwtCookie() {
        System.out.println("cleaning cookie.....");
        return ResponseCookie.from(jwtCookie, null)
                .path("/emp").build();
    }

    public String extractUserName(String token){
        System.out.println("extract username for token..."+token);
        return extractClaim(token,Claims::getSubject);
    }

    private Date extractExpiration(String token){
        System.out.println("Check expiration for token...."+token);
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
//        System.out.println("extract claims...");
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
//        System.out.println("extract all claims....");
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.HS256,jwtSecret).compact();
    }

    public boolean validateToken(String userName,String token,UserDetails userDetails){
//        final String userName = extractUserName(token);
        if(userDetails==null || userName==null || token==null){
            return false;
        }
        return (userName.equalsIgnoreCase(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
