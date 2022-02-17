package step_definitions;

import io.cucumber.java.en.And;
import pages.PaymentPage;

public class PaymentStepDefinitions {
    private PaymentPage paymentPage = new PaymentPage();

    @And("^Buyer select (.*) instant transfer")
    public void buyerSelectInstantTransfer(String bankName) {
        paymentPage.payWithInstantTransfer(bankName).clickContinueOrderButton();
    }
}