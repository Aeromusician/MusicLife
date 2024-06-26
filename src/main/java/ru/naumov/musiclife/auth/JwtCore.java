package ru.naumov.musiclife.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCore {

    @Value("${musiclife.app.secret}")
    private String secret;

    @Value("${musiclife.app.expirationMs}")
    private Integer lifeTime;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((userDetails.getUsername()))
                .claim("userType", userDetails.getUserType())
                .claim("id", userDetails.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + lifeTime)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getNameFromJwt(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
