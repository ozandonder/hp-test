package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.StrongerDriver;

public class DeliveryPage extends StrongerDriver {
    private static final By DELIVERY_PAGE_TITLE_TEXT = By.xpath("//*[@class='delivery_addresses_container']/div[1]/h2");
    private static final By CONTINUE_STEP_BUTTON = By.id("continue_step_btn");
    private static final By CHANGE_PAYMENT_METHOD_LINK = By.className("redirect_button_MHGMG");
    private static final String DELIVERY_PAGE_TITLE = "Teslimat adresi";

    public DeliveryPage checkDeliveryPage() {
        Assert.assertEquals("Delivery page not opened", getDriver().findElement(DELIVERY_PAGE_TITLE_TEXT).getText(), DELIVERY_PAGE_TITLE);
        return this;
    }

    public void clickContinueOrderButton() {
        clickToBy(CONTINUE_STEP_BUTTON);
    }

    public void clickChangePaymentMethod() {
        clickToBy(CHANGE_PAYMENT_METHOD_LINK);
    }
}