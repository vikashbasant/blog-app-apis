package co.blog.security;

import co.blog.constants.JwtSecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenHelper {


    /*----Retrieve UserName from jwt token:----*/
    public String getUsernameFromToken(String token) {
        log.info("===: JwtTokenHelper:: Inside getUsernameFromToken Method :===");
        return getClaimFromToken(token, Claims::getSubject);
    }

    /*----Retrieve Expiration date from jwt token:----*/
    public Date getExpirationDateFromToken(String token) {
        log.info("===: JwtTokenHelper:: Inside getExpirationDateFromToken Method :===");
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        log.info("===: JwtTokenHelper:: Inside getClaimFromToken Method :===");
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    /*----For Retrieving any information from token we will need the secret key:----*/
    private Claims getAllClaimsFromToken(String token) {
        log.info("===: JwtTokenHelper:: Inside getAllClaimsFromToken Method :===");
        return Jwts.parser().setSigningKey(JwtSecurityConstants.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /*----Check if the token has expired:----*/
    private Boolean isTokenExpired(String token) {
        log.info("===: JwtTokenHelper:: Inside isTokenExpired Method :===");
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    /*----Generate token for user:----*/
    public String generateToken(UserDetails userDetails) {
        log.info("===: JwtTokenHelper:: Inside generateToken Method :===");
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }


    /**
     * While Creating The Token:
     * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HSS12 algorithm and secret key.
     * 3. According to JWS Compact Serialization(<a href="https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#secttion-3.1">...</a>)
     * compaction of the JWT to a URL-safe String
     */
    private String doGenerateToken (Map<String, Object> claims, String subject) {
        log.info("===: JwtTokenHelper:: Inside doGenerateToken Method :===");
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JwtSecurityConstants.JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, JwtSecurityConstants.SECRET_KEY).compact();

    }


    /*----Validate Token:----*/
    public Boolean validateToken(String token, UserDetails userDetails) {
        log.info("===: JwtTokenHelper:: Inside validateToken Method :===");
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
