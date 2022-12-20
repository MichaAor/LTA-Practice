package com.lta.blogapirest.Security;

import com.lta.blogapirest.Exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationMs;

    public String generateToken(Authentication auth){
        String username = auth.getName();
        Date currDate = new Date();
        Date finalDate = new Date(currDate.getTime() + jwtExpirationMs);

        String token = Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(finalDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    return token;
    }

    public String UsernameFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean ValidToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Invalid JWT signature.");
        }catch (MalformedJwtException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Invalid Token JWT.");
        }catch (ExpiredJwtException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT expired.");
        }catch (UnsupportedJwtException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT unsupported.");
        }catch (IllegalArgumentException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"The JWT claims string is empty.");
        }
    }
}
