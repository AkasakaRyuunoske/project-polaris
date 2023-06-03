package projectpolaris.CBA.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projectpolaris.Configuration.JWTConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class GenerateToken {
    @Autowired
    JWTConfiguration jwtConfiguration;

    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject){
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecret()).compact();

        log.info("generated token is: " + token);
        log.info("subject of token is: " + subject);
        log.info("role is: " + claims.get("role"));

        return token;
    }
}
