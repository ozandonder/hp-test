package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.StrongerDriver;

public class HomePage extends StrongerDriver {
    private static final By LOGIN_LINK = By.id("login");
    private static final By MY_ACCOUNT_FULL_NAME_TEXT = By.xpath("//*[@title='Hesabım']//span[2]");

    public void clickLoginLink() {
        clickToByWithJs(LOGIN_LINK);
    }

    public void checkLoginUser(String username) {
        Assert.assertEquals("Url incorrect for Login Page", getDriver().findElement(MY_ACCOUNT_FULL_NAME_TEXT).getText(), username);
    }
}