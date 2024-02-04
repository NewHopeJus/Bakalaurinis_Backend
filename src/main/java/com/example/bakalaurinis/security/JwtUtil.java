package com.example.bakalaurinis.security;

import com.example.bakalaurinis.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *The @Component annotation marks the JwtUtil class as a Spring-managed bean.
 *  This means Spring will automatically instantiate, configure, and manage a JwtUtil
 *  object as part of the application context. You can inject this bean into other parts of your application,
 *  such as controllers or security filters, where you need to work with JWTs.
 * */

/**
 *JwtUtil class is a utility for handling JWT operations withing your Spring Security configuration.
 * It Encapsulates the logic for generating, parsing and validating JWT's
 * */

@Component
public class JwtUtil {
    public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    //is tokeno istraukia emaila
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //is tokeno istraukia data
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Claim is a piece of information
    //In JWT it is a key-value pair that conveys specific data about the user and other details relevant to the token
    //Claims are part of jwt token embedded within its payload section.


    //the Claims object essentially represents a collection of all the claims (key-value pairs) present in the token.
    //Claims is the input type and T is an output type which can be anything: String, Date etc

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); //calina funkcija apacioje
        return claimsResolver.apply(claims);
    }

    //Jwt parser is a part of jwt library
    //setSigningKey(SECRET_KEY): This sets the key used to verify the digital signature of the JWT.
    //.parseClaimsJws(token): This method takes the JWT (token) and parses it. It verifies the digital signature
    // (if the JWT is signed) and extracts the claims contained within the token.
    //.getBody(): After successfully parsing the JWT, this method retrieves the payload of the JWT, which contains all the claims.
    //parseClaims jwt is also part of JJWT library
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Kviecia create token pries tai sukures nauja hashmapa claimam (payloudui) jwt tokeno
    //Returnina jwt tokena
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getUsername());
    }

    //Paduodam is virsaus tuscia ta lista claimams sukurta ir vartototjo username
    //10 hours of expiration
    //signina tokena su mano secret key is virsaus

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //Validatina JWT tokena - ar jis galiojantis ir ar matchina passintus userDetails (username)
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
