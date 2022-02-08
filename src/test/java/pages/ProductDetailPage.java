package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.StrongerDriver;

public class ProductDetailPage extends StrongerDriver {
    private static final By ADD_TO_CART_BUTTON = By.id("addToCart");
    private static final By PRODUCT_NAME_TEXT = By.id("product-name");
    private static final By GO_TO_BASKET_BUTTON = By.xpath("//button[contains(text(),'Sepete git')]");

    public void addProductToBasket() {
        clickToBy(ADD_TO_CART_BUTTON);
        visibilityOfElement(GO_TO_BASKET_BUTTON);
        clickToBy(GO_TO_BASKET_BUTTON);
    }

    public ProductDetailPage goToProductDetail(String productUrl, String productName) {
        navigateUrl(productUrl);
        Assert.assertEquals("Product name incorrect", getDriver().findElement(PRODUCT_NAME_TEXT).getText(), productName);
        return this;
    }
}