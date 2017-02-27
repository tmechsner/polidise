package org.polidise;

import org.polidise.domain.User;

public class LoginInfo {
    private static LoginInfoProvider provider;

    /**
     * Returns the currently logged in user.
     * @return an User object representing the logged in user.
     */
    public static User getCurrentLogin() {
        return provider.getCurrentLogin();
    }

    /**
     * Sets the currently active loginProvider.
     *
     * Use with care!
     * @param loginProvider the loginProvider
     */
    public static void setLoginProvider(LoginInfoProvider loginProvider) {
        LoginInfo.provider = loginProvider;
    }

}
