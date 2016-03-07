package me.ele.opdev.demo.exc;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by xuemingli on 16/3/7.
 */
public class AuthenticationFailedException extends AuthenticationException {
    public AuthenticationFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}
