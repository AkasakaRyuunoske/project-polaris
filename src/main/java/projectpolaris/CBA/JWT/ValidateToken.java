package projectpolaris.CBA.JWT;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class ValidateToken {
    @Autowired
    JWTUtils jwtUtils;

    // Validation Methods
    private Boolean isTokenExpired(String token){
        return jwtUtils.extractExpirationDate(token).before(new Date());
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = jwtUtils.extractUserName(token);
        log.info("Token : " + token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
