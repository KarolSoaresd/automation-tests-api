package br.testing.config;

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

    @Key("test.check")
    String testCheck();

    @Key("users.find")
    String usersFinder();

    @Key("users.login")
    String usersLogin();

    @Key("password")
    String password();

    @Key("product.find")
    String productFind();

    @Key("product.create")
    String productCreate();

    @Key("product.findAll")
    String productFindAll();

    @Key("product.id")
    String productId();

}
