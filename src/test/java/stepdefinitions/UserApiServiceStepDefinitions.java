package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import pages.UserApiService;
import restapi.models.Buyer;
import restapi.requesters.BuyerRequester;
import utils.ConfigFileReader;
import utils.DataHooks;
import utils.JsonUtils;
import utils.StrongerDriver;

import java.util.List;
import java.util.Map;

public class UserApiServiceStepDefinitions extends StrongerDriver {
    private BuyerRequester buyerRequester = new BuyerRequester();
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private UserApiService userApiService = new UserApiService();
    private static DataHooks dataHooks = new DataHooks();
    private JsonUtils jsonUtils = new JsonUtils();
    private ValidatableResponse response;
    private JSONObject buyerJson;
    private Buyer buyer;
    private int count = 0;

    @Given("^API Operator should create user$")
    public void apiOperatorShouldCreateUser(List<Map<String, String>> elementsList) {
        buyerJson = JsonUtils.getJson(configFileReader.getConfigValue("buyer.json.path"));
        while (elementsList.size() > count) {
            Map<String, String> parameter = elementsList.get(count);
            if (parameter.get("Value").equals("VALID")) {
                if (parameter.get("Key").equals("id") || parameter.get("Key").equals("userStatus")) {
                    buyerJson.put(parameter.get("Key"), getRandomInt(2));
                } else {
                    buyerJson.put(parameter.get("Key"), getRandomString(10));
                }
            } else {
                buyerJson.put(parameter.get("Key"), parameter.get("Value"));
            }
            count++;
        }
        response = buyerRequester.createBuyer(buyerJson.toString());
        dataHooks.setResponse(response);
    }

    @And("^API Operator set user$")
    public void apiOperatorSetUser() {
        while (count < 10) {
            buyer = buyerRequester.getBuyerByUserName(buyerJson.get("username").toString());
            if (buyerJson.get("id").toString().equals(String.valueOf(buyer.getId())))
                break;
            count++;
        }
        dataHooks.setBuyer(buyer);
    }

    @And("^API Operator should delete user with username as \"([^\"]*)\"$")
    public void apiOperatorShouldDeleteUserWithUsernameAs(String userName) {
        if (userName.equals("VALID")) {
            userName = buyer.getUsername();
        }
        response = buyerRequester.deleteBuyerByUserName(userName);
        dataHooks.setResponse(response);
    }

    @And("^API Operator should edit user with username as \"([^\"]*)\"$")
    public void apiOperatorShouldEditUserWithUsernameAs(String userName, List<Map<String, String>> elementsList) {
        if (userName.equals("VALID")) {
            userName = dataHooks.getValue();
        }
        buyerJson = jsonUtils.getJson();
        int count = 0;
        while (elementsList.size() > count) {
            Map<String, String> parameter = elementsList.get(count);
            String value = parameter.get("Value");
            if (value.equals("VALID")) {
                dataHooks.setValue(getRandomString(10));
                value = dataHooks.getValue();
            }
            buyerJson.put(parameter.get("Key"), value);
            count++;
        }
        response = buyerRequester.putBuyerByUserName(userName, buyerJson.toString());
        dataHooks.setResponse(response);
    }

    @Then("^Check api response$")
    public void checkApiResponse(List<Map<String, String>> elementsList) {
        response = dataHooks.getResponse();
        Map<String, String> parameters = elementsList.get(0);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getKey().equals("message") && entry.getValue().equals("VALID")) {
                userApiService.checkResponse(response.extract().response().path(entry.getKey()).toString(), buyerJson.get("id").toString());
            } else if (entry.getValue().equals("VALID")) {
                userApiService.checkResponse(response.extract().response().path(entry.getKey()).toString(), dataHooks.getValue());
            } else {
                userApiService.checkResponse(response.extract().response().path(entry.getKey()).toString(), entry.getValue());
            }
        }
    }

    @Then("^Check delete api response$")
    public void checkDeleteApiResponse(List<Map<String, String>> elementsList) {
        response = dataHooks.getResponse();
        while (count < 10) {
            buyer = buyerRequester.getBuyerByUserName(buyerJson.get("username").toString());
            if (!buyerJson.get("id").toString().equals(String.valueOf(buyer.getId())))
                break;
            count++;
        }
        Map<String, String> parameters = elementsList.get(0);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getKey().equals("message") && entry.getValue().equals("VALID")) {
                userApiService.checkResponse(response.extract().response().path(entry.getKey()).toString(), buyerJson.get("username").toString());
            } else {
                userApiService.checkResponse(response.extract().response().path(entry.getKey()).toString(), entry.getValue());
            }
        }
    }

}