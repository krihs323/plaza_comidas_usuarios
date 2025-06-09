package com.plazas.usuarios.infraestructure.security;

import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.token}")
    private String token;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, User user) {
        return generateToken(new HashMap<>(), userDetails, user);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            User user
    ) {

        Optional<String> roles = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority);
        extraClaims.put("role", roles.orElseThrow());
        extraClaims.put("idUuser", user.getId());
        extraClaims.put("idRestaurantEmployee", user.getIdRestaurantEmployee());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(token);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public User extractUser(String jwtAuthorizationHeader) {
        Claims claims = extractAllClaims(jwtAuthorizationHeader);

        String role = (String) claims.get("role");
        Integer idUser = (Integer) claims.get("idUuser");
        Integer idRestaurantEmployee = (Integer) claims.get("idRestaurantEmployee");

        User user = new User();
        user.setRole(Role.valueOf(role));
        if(idUser!=null){
            user.setId(Long.valueOf(idUser));
        }
        if(idRestaurantEmployee!=null){
            user.setIdRestaurantEmployee(Long.valueOf(idRestaurantEmployee));
        }
        return user;
    }
}
