package driver.manager;

import driver.BrowserType;
import driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final BrowserType BROWSER_TYPE = BrowserType.CHROME;
    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {

        if (driver == null) {
            driver = BrowserFactory.getBrowser(BROWSER_TYPE);
        }

        return driver;
    }

    public static void disposeDriver() {
        driver.close();
        if (!BROWSER_TYPE.equals(BrowserType.FIREFOX)){
            driver.quit();
        }
        driver = null;
    }
}