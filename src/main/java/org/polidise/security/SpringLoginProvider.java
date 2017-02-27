package org.polidise.security;

import org.polidise.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * LoginProvider for the Spring-Interface.
 */
public class SpringLoginProvider implements LoginInfoProvider {

    /**
     * Returns current login.
     * @return the login as User object.
     */
    @Override
    public User getCurrentLogin() {
        try {
            SecurityContext sc = SecurityContextHolder.getContext();
            Authentication authentication = sc.getAuthentication();
            return (User) authentication.getPrincipal();
        } catch (ClassCastException| NullPointerException ex) {
            return null;
        }
    }
}
