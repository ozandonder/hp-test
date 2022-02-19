package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.StrongerDriver;

public class BasketSummaryPage extends StrongerDriver {
    private static final By BANK_NAME_TEXT = By.xpath("//*[contains(@class,'modal_bank_details')]//div[1]//span[2]");
    private static final By PAYMENT_METHOD_TEXT = By.xpath("//*[contains(@class,'modal_bank_details')]//div[2]//span[2]");
    private static final String INSTANT_TRANSFER_TEXT = "Anında Havale";

    public void checkBankNameForInstantTransfer(String bankName) {
        Assert.assertEquals("Payment method incorrect", getDriver().findElement(PAYMENT_METHOD_TEXT).getText(), INSTANT_TRANSFER_TEXT);
        Assert.assertEquals("Bank Name incorrect", getDriver().findElement(BANK_NAME_TEXT).getText(), bankName);
    }
}