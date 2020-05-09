package coHelp.config.security.jwt.provider;

import coHelp.config.security.jwt.authentication.JwtAuthentication;
import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import coHelp.model.user.Role;
import coHelp.model.user.State;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Value("dkKef534cD2")
    private String secret;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        Claims claims;
        try {
            // выполняю парсинг токена со своим секретным ключом
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        // создаем UserDetails
        UserDetails userDetails = UserDetailsImpl.builder()
                .userId(Long.parseLong(claims.get("sub", String.class)))
                .role(Role.valueOf(claims.get("role", String.class)))
                .email(claims.get("email", String.class))
                .state(State.valueOf(claims.get("state", String.class)))
                .build();
        // аутентификация прошла успешно
        authentication.setAuthenticated(true);
        // положили в эту аутентификацию пользователя
        ((JwtAuthentication) authentication).setUserDetails(userDetails);
        return authentication;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
