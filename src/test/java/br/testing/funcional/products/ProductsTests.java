package br.testing.funcional;

import br.testing.config.ConfigurationManager;
import br.testing.data.ExceptionsMessages;
import br.testing.model.ProcuctModel;
import br.testing.model.UserModel;
import br.testing.requests.ProductsRequests;
import br.testing.requests.UsersRequests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ProductsTests {

    private final String passwordSuccess = ConfigurationManager.getConfiguration().password();

    @Test
    @DisplayName("Test the GET method with correct token in product endpoint, expect status 200")
    void getProductAuth() {
        UserModel user = new UserModel("kminchelle", passwordSuccess);
        String responseToken = new UsersRequests().postAuthToken(user);
        Response responseProduct = new ProductsRequests().getAuthProduct(responseToken);
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_OK));
        assertThat(responseProduct.getBody().jsonPath().get("products"), is(notNullValue()));
        assertThat(responseProduct.getBody().jsonPath().get("total"), is(100));
    }

    @Test
    @DisplayName("Test the GET method with user unauthorized, expect error 401")
    void getProductNoAuth() {
        String tokenInvalid = "Bearer 123";
        Response responseProduct = new ProductsRequests().getAuthProduct(tokenInvalid);
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
        assertThat(responseProduct.getBody().jsonPath().get("message"), is(ExceptionsMessages.TOKEN_INVALID.getMessage()));
    }

    @Test
    @DisplayName("Test the GET method without header authotization, expect error 403")
    void getProductErrorAuth() {
        Response responseProduct = new ProductsRequests().getAuthProductErrorAuth();
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_FORBIDDEN));
        assertThat(responseProduct.getBody().jsonPath().get("message"), is(ExceptionsMessages.FORBIDDEN_AUTH.getMessage()));
    }

    @Test
    @DisplayName("Test validate GET method for find all products, expect status")
    void getAllProducts() {
        Response responseProduct = new ProductsRequests().getFindAllProducts();
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_OK));
        assertThat(responseProduct.getBody().jsonPath().get("products"), is(notNullValue()));
        assertThat(responseProduct.getBody().jsonPath().get("products[0].title"), is("iPhone 9"));
    }

    @Test
    @DisplayName("Test the GET method using product id, expect status 200")
    void getProductIdEndpoint() {
        Response responseProduct = new ProductsRequests().getFindProductId(15);
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_OK));
        assertThat(responseProduct.getBody().jsonPath().get("title"), is("Eau De Perfume Spray"));
    }

    @Test
    @DisplayName("Test the GET method when product id not exist, expect error 404")
    void getProductInvalidId() {
        Response responseProduct = new ProductsRequests().getFindProductId(1200);
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        assertThat(responseProduct.getBody().jsonPath().get("message"), is(ExceptionsMessages.PRODUCT_NOT_FOUND.getMessage()));
    }

    @Test
    @DisplayName("Test add new product, expect status 201")
    void postNewProduct() {
        ProcuctModel product = new ProcuctModel("Macbook", "No details", 5000, 1, 2, 100, "Apple", "computer", "http:teste.com");
        Response responseProduct = new ProductsRequests().postNewProduct(product);
        assertThat(responseProduct.statusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseProduct.getBody().jsonPath().get("id"), is(101));
    }
}
