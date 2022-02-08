package stepdefinitions;

import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import pages.ProductDetailPage;
import restapi.requesters.ProductRequester;
import utils.ConfigFileReader;
import utils.JsonUtils;

import java.util.ArrayList;

public class ProductDetailStepDefinitions {
    private ProductDetailPage productDetailPage = new ProductDetailPage();
    private ProductRequester productRequester = new ProductRequester();
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private ValidatableResponse response;

    @When("^Buyer should add to product basket$")
    public void buyerShouldAddToProductBasket() {
        JSONObject json = JsonUtils.getJson(configFileReader.getConfigValue("product.json.path"));
        response = productRequester.createProduct(json.toString());
        int petId = response.extract().response().path("id");
        response = productRequester.getProductById(petId);
        String productName = response.extract().response().path("name");
        ArrayList productUrl = response.extract().response().path("photoUrls");
        productDetailPage.goToProductDetail(productUrl.get(0).toString(), productName)
                .addProductToBasket();
    }
}