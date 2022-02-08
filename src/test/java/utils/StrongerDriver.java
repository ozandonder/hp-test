package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class StrongerDriver extends DriverHooks {

    public WebDriver getDriver() {
        return driver;
    }

    public void navigateUrl(String url) {
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void clickToBy(By by) {
        scrollTo(by);
        driver.findElement(by).click();
        waitForAjax();
    }

    public void clickToByWithJs(By by) {
        scrollTo(by);
        WebElement element = driver.findElement(by);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
        waitForAjax();
    }

    public void type(By by, String text, boolean clear) {
        WebElement element = scrollTo(by);

        if (clear) {
            element.clear();
        }

        element.sendKeys(text);
        waitForAjax();
    }

    public void typeWithJs(By by, String text, boolean clear) {
        WebElement element = scrollTo(by);
        if (clear) {
            element.clear();
        }
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value='" + text + "';", element);
        waitForAjax();
    }


    public WebElement scrollTo(By by) {
        WebElement element = driver.findElement(by);
        waitToByWebElement(by);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
        highlightElement(by);
        return element;
    }

    public void waitToByWebElement(By by) {
        try {
            int count = 0;
            while (isElementPresent(by) && count < 100) {
                count++;
            }
        } catch (RuntimeException rte) {
            System.out.println(rte);
        }
    }

    public void waitForAjax() {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Page has not loaded yet ");
            }
            boolean stillRunningAjax = (Boolean) jsDriver
                    .executeScript("return (window.jQuery != undefined && ($(':animated').length != 0 || jQuery.active != 0)) || document.readyState != \"complete\"");
            if (!stillRunningAjax) {
                break;
            }
        }
    }

    public boolean isElementPresent(By by) {
        return getDriver().findElements(by).isEmpty();
    }

    public String getRandomString(int i) {
        return RandomStringUtils.randomAlphabetic(i);
    }

    public int getRandomInt(int i) {
        i = parseInt(RandomStringUtils.randomNumeric(i));
        return i;
    }

    public boolean visibilityOfElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).
                withTimeout(ofSeconds(100)).
                pollingEvery(ofMillis(500)).
                ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
        try {
            wait.until(visibilityOfElementLocated(by));
            return true;
        } catch (WebDriverException wde) {
            return false;
        }
    }

    public boolean presenceOfElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).
                withTimeout(ofSeconds(100)).
                pollingEvery(ofMillis(1000)).
                ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (WebDriverException wde) {
            return false;
        }
    }

    private void highlightElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.background = 'yellow';", element);
    }
}

