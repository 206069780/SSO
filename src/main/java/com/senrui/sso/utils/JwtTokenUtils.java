package com.senrui.sso.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class JwtTokenUtils {


    private static String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY);
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);
    private static Long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;
    private static Long EXPIRATION = 60 * 60L;
    private static String ROLE_CLAIMS = "rol";
    private static String TOKEN_TYPE = "JWT";


    public static String createToken(String username, String id, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        String tokenPrefix = Jwts.builder()
                .setHeaderParam("type", TOKEN_TYPE)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .claim(ROLE_CLAIMS, String.join(",", roles))
                .setId(id).setIssuer("SnailClimb").setIssuedAt(createdDate)
                .setSubject(username).setExpiration(expirationDate).compact();
        return tokenPrefix; // 添加 token 前缀 "Bearer ";
    }

    public static String getId(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        String userName = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String role = (String) claims.get(ROLE_CLAIMS);
        return Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private static Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

}