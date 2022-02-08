package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.StrongerDriver;

public class LoginPage extends StrongerDriver {
    private static final By USER_NAME_TEXTBOX = By.id("txtUserName");
    private static final By PASSWORD_TEXTBOX = By.id("txtPassword");
    private static final By LOGIN_BUTTON = By.id("btnLogin");
    private static final By EMAIL_SELECT_BUTTON = By.id("btnEmailSelect");
    private static final String loginUrl = "giris.hepsiburada.com";

    public LoginPage typeEmail(String email) {
        type(USER_NAME_TEXTBOX, email, true);
        clickToBy(LOGIN_BUTTON);
        return this;
    }

    public LoginPage typePassword(String password) {
        type(PASSWORD_TEXTBOX, password, true);
        clickToBy(EMAIL_SELECT_BUTTON);
        return this;
    }

    public LoginPage checkLoginPage() {
        Assert.assertTrue("Url incorrect for Login Page", getDriver().getCurrentUrl().contains(loginUrl));
        return this;
    }
}