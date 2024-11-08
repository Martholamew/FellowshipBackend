package fellowshipofthemovieclub.fellowship.services;

import fellowshipofthemovieclub.fellowship.jpaentities.Role;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import static io.jsonwebtoken.security.Keys.secretKeyFor;
@Service
public class JwtTokenService {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds

    public String generateToken(UserInfo userInfo) {
        List<String> roleNames = userInfo.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(userInfo.getUserName())
                .claim("userId", userInfo.getId())
                .claim("roles", roleNames)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) // Use only the key without specifying the algorithm
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Remove "Bearer " prefix to get the token
        }
        return null;  // No token found
    }

    public List<String> getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", List.class);
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // This should return the username
    }

    public boolean validateToken(String token) {
        try {
            // Parse the token to verify its signature and check for expiration
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Use the same secret key that was used to sign the token
                    .build()
                    .parseClaimsJws(token); // If parsing fails, an exception will be thrown

            return true; // Token is valid
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("JWT token is malformed: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("JWT signature validation failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token is invalid: " + e.getMessage());
        }
        return false; // Token is invalid
    }
}
