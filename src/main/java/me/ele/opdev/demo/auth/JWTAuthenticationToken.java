package me.ele.opdev.demo.auth;

import me.ele.opdev.demo.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by xuemingli on 16/3/7.
 */
public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private String credentials = null;
    private User principal = null;
    private boolean isAuthenticated = false;

    public JWTAuthenticationToken(String token) {
        super(null);
        credentials = token;
        isAuthenticated = false;
    }

    public JWTAuthenticationToken(User principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        isAuthenticated = true;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if(isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
