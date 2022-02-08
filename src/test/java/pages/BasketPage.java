package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.StrongerDriver;

public class BasketPage extends StrongerDriver {
    private static final By BASKET_HEADER_TITLE_TEXT = By.xpath("//*[@class='basket_headerTop_15H0U']//h1");
    private static final By CONTINUE_STEP_BUTTON = By.id("continue_step_btn");
    private static final String BASKET_HEADER_TITLE = "Sepetim";

    public BasketPage checkBasketPage() {
        Assert.assertEquals("Basket page not opened", getDriver().findElement(BASKET_HEADER_TITLE_TEXT).getText(), BASKET_HEADER_TITLE);
        return this;
    }

    public void clickContinueOrderButton() {
        clickToBy(CONTINUE_STEP_BUTTON);
    }
}