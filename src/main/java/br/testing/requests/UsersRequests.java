package br.testing.requests;

import br.testing.config.Configuration;
import br.testing.config.ConfigurationManager;
import br.testing.model.UserModel;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UsersRequests {

    private final Configuration config;

    public UsersRequests() {
        this.config = ConfigurationManager.getConfiguration();
    }

    public String getUrlAuth() {
        return config.baseUri() + config.usersLogin();
    }

    public String getUrlGetUser() {
        return config.baseUri() + config.usersFinder();
    }

    public String getUrlGetUserId(int id) {
        return config.baseUri() + config.usersFinder() + "/"+id;
    }

    private String generateBody(UserModel user) {
        return new Gson().toJson(user);
    }

    public Response postAuthUser(UserModel user) {
        return given().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                body(generateBody(user)).
                when().
                post(getUrlAuth()).
                then().
                extract().response();
    }

    public String postAuthToken(UserModel user) {
        return given().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                body(generateBody(user)).
                when().
                post(getUrlAuth()).
                then().
                extract().
                path("token");
    }

    public Response getUsers() {
        return given().
                header("Content-Type", "application/json").
                when().
                get(getUrlGetUser()).
                then().
                extract().response();
    }

    public Response getUsersId(int id) {
        return given().
                header("Content-Type", "application/json").
                when().
                get(getUrlGetUserId(id)).
                then().
                extract().response();
    }
}
