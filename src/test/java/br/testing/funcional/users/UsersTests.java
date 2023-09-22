package br.testing.funcional.users;

import br.testing.config.ConfigurationManager;
import br.testing.data.ExceptionsMessages;
import br.testing.model.UserModel;
import br.testing.requests.UsersRequests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersTests {

    private final String passwordSuccess = ConfigurationManager.getConfiguration().password();
    @Test
    @DisplayName("Test validate success login, expect status 201")
    void realizeSuccessLogin() {
        UserModel user = new UserModel("kminchelle", passwordSuccess);
        Response responseUser = new UsersRequests().postAuthUser(user);
        assertThat(responseUser.statusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseUser.getBody().jsonPath().get("firstName"), is("Jeanne"));
    }

    @Test
    @DisplayName("Test login with an invalid password, expect error 401")
    void loginInvalidPassword() {
        UserModel user = new UserModel("kminchelle", "12345");
        Response responseUser = new UsersRequests().postAuthUser(user);
        assertThat(responseUser.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
        assertThat(responseUser.getBody().jsonPath().get("message"), is(ExceptionsMessages.USER_INVALID_ACCESS.getMessage()));
    }

    @Test
    @DisplayName("Test the GET method in users endpoint, expect status 200")
    void getUsersEndpoint() {
        Response response = new UsersRequests().getUsers();
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.getBody().jsonPath().get("users"), is(notNullValue()));
    }

    @Test
    @DisplayName("Test the GET method using user id, expect status 200")
    void getUsersIdEndpoint() {
        Response response = new UsersRequests().getUsersId(20);
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.getBody().jsonPath().get("firstName"), is("Lenna"));
    }

    @Test
    @DisplayName("Test the GET method when user id not exist, expect error 404")
    void getUserInvalidId() {
        Response response = new UsersRequests().getUsersId(1000);
        assertThat(response.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        assertThat(response.getBody().jsonPath().get("message"), is(ExceptionsMessages.USER_NOT_FOUND.getMessage()));
    }
}
