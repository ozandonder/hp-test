package restapi.requesters;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class ProductRequester extends ConfigFileReader {
    private ValidatableResponse response = null;
    private Logger logger = LogManager.getLogger(getClass().getName());

    public ValidatableResponse createProduct(String json) {
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .when()
                    .baseUri(getConfigValue("api.base.url"))
                    .post(getConfigValue("product.path"))
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }

        logger.info(getClass().getName() + "\n" + response.extract().response().asPrettyString());
        return response;
    }

    public ValidatableResponse getProductById(int productId) {
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .baseUri(getConfigValue("api.base.url"))
                    .get(getConfigValue("product.path") + "/" + productId)
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        logger.info(getClass().getName() + "\n" + response.extract().response().asPrettyString());
        return response;
    }

    public ValidatableResponse getProductByStatus(String saleStatus) {
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .queryParam("status", saleStatus)
                    .when()
                    .baseUri(getConfigValue("api.base.url"))
                    .get(getConfigValue("product.path") + "/findByStatus")
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        response.extract().response().prettyPrint();
        return response;
    }

    public ValidatableResponse deleteProduct(int productId) {
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .queryParam("petId", productId)
                    .when()
                    .baseUri(getConfigValue("api.base.url"))
                    .delete(getConfigValue("product.path"))
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        logger.info(getClass().getName() + "\n" + response.extract().response().asPrettyString());
        return response;
    }
}

