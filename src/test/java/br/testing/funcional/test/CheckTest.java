package br.testing.funcional.test;

import br.testing.data.Messages;
import br.testing.requests.TestCheckRequests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckTest {

    @Test
    @DisplayName("Validate Status 200 and success message")
    void TestCheckRequests() {
        Response response = new TestCheckRequests().getTestCheck();
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.getBody().jsonPath().get("status"), is(Messages.TEST_CHECK.getMessage()));
    }
}
