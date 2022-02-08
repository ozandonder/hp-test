package stepdefinitions;

import cucumber.api.java.en.Then;
import pages.BasketSummaryPage;

public class BasketSummaryStepDefinitions {
    private BasketSummaryPage basketSummaryPage = new BasketSummaryPage();

    @Then("^Buyer should see instant transfer bank name as (.*) at Basket Summary Page$")
    public void userShouldSeeReceivedMessageAtJobApplicationSuccessPage(String bankName) {
        basketSummaryPage.checkBankNameForInstantTransfer(bankName);
    }
}