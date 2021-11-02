package driver.manager;

import driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

import static configuration.TestRunProperties.getBrowserToRun;
import static configuration.TestRunProperties.getIsRemoteRun;
import static driver.BrowserType.FIREFOX;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {

        if (driver == null) {
            driver = new BrowserFactory(getBrowserToRun(), getIsRemoteRun()).getBrowser();
        }

        return driver;
    }

    public static void disposeDriver() {
        driver.close();
        if (!getBrowserToRun().equals(FIREFOX)) {
            driver.quit();
        }
        driver = null;
    }
}