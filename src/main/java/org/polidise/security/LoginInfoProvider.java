package org.polidise.security;

import org.polidise.domain.User;

public interface LoginInfoProvider {
    /**
     * Returns current login.
     * @return the login as User object.
     */
    User getCurrentLogin();
}
