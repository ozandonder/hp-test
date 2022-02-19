package step_definitions;

import io.cucumber.java.en.And;
import pages.BasketPage;
import pages.DeliveryPage;
import utils.StrongerDriver;

public class BasketStepDefinitions extends StrongerDriver {
    private BasketPage basketPage = new BasketPage();
    private DeliveryPage deliveryPage = new DeliveryPage();

    @And("^Buyer should go to Payment Page")
    public void buyerShouldGoToPaymentPage() {
        basketPage.checkBasketPage().clickContinueOrderButton();
        deliveryPage.checkDeliveryPage().clickContinueOrderButton();
    }
}