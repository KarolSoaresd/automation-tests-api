package br.testing.requests;

import br.testing.config.Configuration;
import br.testing.config.ConfigurationManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TestCheckRequests {
    private final Configuration config;

    public TestCheckRequests() {
        this.config = ConfigurationManager.getConfiguration();
    }

    private String getUrl() {
        final String url = config.baseUri() + config.testCheck();
        return url;
    }

    public Response getTestCheck() {
        return given().
                when().
                get(getUrl()).
                then().
                extract().
                response();
    }
}
