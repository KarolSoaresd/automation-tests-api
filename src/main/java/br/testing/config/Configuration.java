package com.testing.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({
        "classpath:api.properties"
})
public interface Configuration extends Config {

    @Key("base.uri")
    String baseUri();

    @Key("base.path")
    String basePath();

    @Key("health.check")
    String healthCheck();

    @Key("users.register")
    String usersRegister();

    @Key("users.login")
    String usersLogin();

    @Key("users.profile")
    String usersProfile();

    @Key("users.forgotPassword")
    String usersForgotPassword();

    @Key("users.verifyResetPasswordToken")
    String usersVerifyResetPasswordToken();

    @Key("users.resetPassword")
    String usersResetPassword();

    @Key("users.changePassword")
    String usersChangePassword();

    @Key("users.logout")
    String usersLogout();

    @Key("users.deleteAccount")
    String usersDeleteAccount();

    @Key("notes")
    String notes();
}
