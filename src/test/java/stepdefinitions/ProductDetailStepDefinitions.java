package stepdefinitions;

import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import pages.ProductDetailPage;
import restapi.models.Product;
import restapi.requesters.ProductRequester;
import utils.ConfigFileReader;
import utils.JsonUtils;

import java.util.ArrayList;

public class ProductDetailStepDefinitions {
    private ProductDetailPage productDetailPage = new ProductDetailPage();
    private ProductRequester productRequester = new ProductRequester();
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private ValidatableResponse response;
    private Product product;
    private int count = 0;

    @When("^Buyer should add to product basket$")
    public void buyerShouldAddToProductBasket() {
        JSONObject productJson = JsonUtils.getJson(configFileReader.getConfigValue("product.json.path"));
        response = productRequester.createProduct(productJson.toString());
        while (count < 10) {
            response = productRequester.getProductById(Integer.parseInt(productJson.get("id").toString()));
            if (response.extract().response().path("id").toString().equals(productJson.get("id").toString()))
                break;
            count++;
        }
        String productName = response.extract().response().path("name");
        ArrayList productUrl = response.extract().response().path("photoUrls");
        productDetailPage.goToProductDetail(productUrl.get(0).toString(), productName)
                .addProductToBasket();
    }
}