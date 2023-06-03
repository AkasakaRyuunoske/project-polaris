package projectpolaris.CBA.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Log4j2
public class JWTUtils {
    private final String secret = "dsadsasdasda";

    // Extraction Methods
    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims CLAIMS = exctractAllClaims(token);

        return claimsResolver.apply(CLAIMS);
    }

    public Claims exctractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}