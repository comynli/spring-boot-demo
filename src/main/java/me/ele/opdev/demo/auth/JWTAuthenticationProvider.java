package me.ele.opdev.demo.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import me.ele.opdev.demo.domain.Group;
import me.ele.opdev.demo.domain.User;
import me.ele.opdev.demo.exc.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xuemingli on 16/3/7.
 */
@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {
    private final static ObjectMapper mapper = new ObjectMapper();

    @Value("${demo.jwt.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthenticationToken token = (JWTAuthenticationToken) authentication;
        String code = (String) token.getCredentials();
        System.out.println(code);
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(code).getBody();
            User user = mapper.readValue(claims.get("user", String.class), User.class);
            System.out.println(claims.get("user", String.class));
            List<GrantedAuthority> authorities = user.getGroups()
                    .stream()
                    .map(Group::getName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new JWTAuthenticationToken(user, authorities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationFailedException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JWTAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
