package com.registration.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.registration.model.ApplicationUser;

import static com.registration.config.SecurityConstants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.registration.config.SecurityConstants.SIGNING_KEY;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

private static final long serialVersionUID = 1L;

public String getUsernameFromToken(String token) {
return getClaimFromToken(token, Claims::getSubject);
}

public Date getExpirationDateFromToken(String token) {
return getClaimFromToken(token, Claims::getExpiration);
}

public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
final Claims claims = getAllClaimsFromToken(token);
return claimsResolver.apply(claims);
}

private Claims getAllClaimsFromToken(String token) {
return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
}

private Boolean isTokenExpired(String token) {
final Date expiration = getExpirationDateFromToken(token);
return expiration.before(new Date());
}

// generates a Token, for a given user
public String generateToken(ApplicationUser user) {
return doGenerateToken(user.getPanNo());
}

private String doGenerateToken(String string) {

Claims claims = Jwts.claims().setSubject(string);
claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

System.out.println("Current timeeeeeeee:" + new Date(System.currentTimeMillis()));
System.out.println("Expiration timeeeeeeeeeeee:"
+ new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000));

return Jwts.builder().setClaims(claims).setIssuer("http://devglan.com")
.setIssuedAt(new Date(System.currentTimeMillis()))
.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
.signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
}

public Boolean validateToken(String token, UserDetails userDetails) {
final String username = getUsernameFromToken(token);
return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

}

