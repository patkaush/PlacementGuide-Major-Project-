package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final String SECRET_KEY = "secret";
  
  public String extractUsername(String token) {
    String user = ((Claims)Jwts.parser()
      .setSigningKey("secret")
      .parseClaimsJws(token)
      .getBody())
      .getSubject();
    return user;
  }
  
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }
  
  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 36000000L))
      .signWith(SignatureAlgorithm.HS256, "secret").compact();
  }
}
