package br.testing.requests;

import br.testing.config.Configuration;
import br.testing.config.ConfigurationManager;
import br.testing.model.ProcuctModel;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProductsRequests {

    private final Configuration config;

    public ProductsRequests() {
        this.config = ConfigurationManager.getConfiguration();
    }

    public String getUrlAuthProduct() {
        return config.baseUri() + config.productFind();
    }

    public String getUrlProductFindAll() {
        return config.baseUri() + config.productFindAll();
    }

    public String getUrlProductId(int id) {
        return config.baseUri() + config.productId() + id;
    }

    public String getUrlProductCreate() {
        return config.baseUri() + config.productCreate();
    }

    private String generateBody(ProcuctModel product) {
        return new Gson().toJson(product);
    }

    public Response getAuthProduct(String token) {
        return given().
                header("accept", "application/json").
                header("Contet-Type", "application/json").
                header("Authorization", "Bearer " + token).
                when().
                get(getUrlAuthProduct()).
                then().
                extract().response();
    }

    public Response getAuthProductErrorAuth() {
        return given().
                header("accept", "application/json").
                header("Contet-Type", "application/json").
                when().
                get(getUrlAuthProduct()).
                then().
                extract().response();
    }

    public Response getFindAllProducts() {
        return given().
                header("accept", "application/json").
                header("Contet-Type", "application/json").
                when().
                get(getUrlProductFindAll()).
                then().
                extract().response();
    }

    public Response getFindProductId(int id) {
        return given().
                header("accept", "application/json").
                header("Contet-Type", "application/json").
                when().
                get(getUrlProductId(id)).
                then().
                extract().response();
    }

    public Response postNewProduct(ProcuctModel product) {
        return given().
                header("accept", "application/json").
                header("Contet-Type", "application/json").
                body(generateBody(product)).
                when().
                post(getUrlProductCreate()).
                then().
                extract().response();
    }

}

