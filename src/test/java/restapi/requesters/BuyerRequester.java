package restapi.requesters;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restapi.models.Buyer;
import utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class BuyerRequester extends ConfigFileReader {

    private ValidatableResponse response = null;
    private Logger logger = LogManager.getLogger(getClass().getName());
    private String requestUrl;
    private Buyer buyer;

    public ValidatableResponse createBuyer(String json) {
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .when()
                    .baseUri(getConfigValue("api.base.url"))
                    .post(getConfigValue("buyer.path"))
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        logger.info(getClass().getName() + "\n" + response.extract().response().asPrettyString());
        return response;
    }

    public Buyer getBuyerByUserName(String userName) {
        try {
            requestUrl = getConfigValue("api.base.url") + getConfigValue("buyer.path") + "/" + userName;
            buyer = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(requestUrl).as(Buyer.class);
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        logger.info(getClass().getName() + "\n" + new Gson().toJson(buyer));
        return buyer;
    }

    public ValidatableResponse deleteBuyerByUserName(String userName) {
        try {
            requestUrl = getConfigValue("api.base.url") + getConfigValue("buyer.path") + "/" + userName;
            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(requestUrl)
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        logger.info(getClass().getName() + "\n" + response.extract().response().asPrettyString());
        return response;
    }

    public ValidatableResponse putBuyerByUserName(String userName, String json) {
        try {
            requestUrl = getConfigValue("api.base.url") + getConfigValue("buyer.path") + "/" + userName;
            response = given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .when()
                    .put(requestUrl)
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        logger.info(getClass().getName() + "\n" + response.extract().response().asPrettyString());
        return response;
    }
}

