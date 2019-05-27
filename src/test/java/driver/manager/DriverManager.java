package driver.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }

        return driver;
    }

    public static void disposeDriver(){
        driver.close();
        driver.quit();
        driver = null;
    }
}