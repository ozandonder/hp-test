package utils;

import cucumber.api.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class DriverHooks {

    public static WebDriver driver;
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private Logger logger = LogManager.getLogger(getClass().getName());

    public WebDriver setupDriver() {
        if (driver == null) {
            String browser = configFileReader.getConfigValue("browser");
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", configFileReader.getConfigValue("chrome.driver.path"));
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("incognito");
                    DesiredCapabilities cap = DesiredCapabilities.chrome();
                    cap.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new ChromeDriver(cap);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", configFileReader.getConfigValue("firefox.driver.path"));
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-private");
                    capabilities.setCapability("moz:firefoxOptions", firefoxOptions);
                    driver = new FirefoxDriver(capabilities);
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported for " + browser);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(configFileReader.getConfigValue("implicitly.wait")), TimeUnit.SECONDS);
            driver.navigate().to(configFileReader.getConfigValue("homepage.url"));
        }
        return driver;
    }

    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                DateFormat dateFormat = new SimpleDateFormat("_dd_MM_yyyy_HH:mm:ss");
                Date date = new Date();
                String desPath = "/screenshots/" + scenario.getName().replaceAll(" ", "") + dateFormat.format(date) + ".png";
                File destFile = new File(System.getProperty("user.dir") + desPath);
                FileUtils.copyFile(scrFile, destFile);
                logger.info(scenario.getName() + " is failed");
            } catch (WebDriverException dontSupportScreenshots) {
                logger.info(dontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
        driver = null;
    }
}
