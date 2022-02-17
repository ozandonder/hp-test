package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import pages.HomePage;
import pages.LoginPage;
import restapi.models.Buyer;
import restapi.requesters.BuyerRequester;
import utils.ConfigFileReader;
import utils.JsonUtils;
import utils.StrongerDriver;
import utils.DataHooks;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HomePageStepDefinitions extends StrongerDriver {
    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private BuyerRequester buyerRequester = new BuyerRequester();
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private DataHooks dataHooks = new DataHooks();
    private Buyer buyer = new Buyer();
    private ValidatableResponse response;
    private JSONObject buyerJson;

    @Before(value = "@mf")
    public void before() {
        setupDriver();
    }

    @After(value = "@mf")
    public void after(Scenario scenario) throws IOException {
        tearDown(scenario);
    }

    @Given("^Buyer try to login$")
    public void buyerTryToLogin(List<Map<String, String>> elementsList) {
        Map<String, String> parameter = elementsList.get(0);
        if (parameter.get("username").toUpperCase().equals("VALID") && parameter.get("email").toUpperCase().equals("VALID")) {
            buyerJson = JsonUtils.getJson(configFileReader.getConfigValue("buyer.json.path"));
            response = buyerRequester.createBuyer(buyerJson.toString());
            MatcherAssert.assertThat(response.extract().response().path("code"), Matchers.equalTo(200));
            buyer = buyerRequester.getBuyerByUserName(buyerJson.get("username").toString());
        } else {
            buyer.setUsername(parameter.get("username"));
            buyer.setEmail(parameter.get("email"));
            buyer.setPassword(parameter.get("password"));
        }
        dataHooks.setBuyer(buyer);
        homePage.clickLoginLink();
        loginPage.checkLoginPage()
                .typeEmail(dataHooks.getBuyer().getEmail())
                .typePassword(dataHooks.getBuyer().getPassword());
        homePage.checkLoginUser(dataHooks.getBuyer().getUsername());
    }
}