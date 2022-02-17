package step_definitions;

import io.cucumber.java.en.Then;
import pages.BasketSummaryPage;

public class BasketSummaryStepDefinitions {
    private BasketSummaryPage basketSummaryPage = new BasketSummaryPage();

    @Then("^Buyer should see instant transfer bank name as (.*) at Basket Summary Page$")
    public void buyerShouldSeeInstantTransferBankNameAsAtBasketSummaryPage(String bankName) {
        basketSummaryPage.checkBankNameForInstantTransfer(bankName);
    }
}