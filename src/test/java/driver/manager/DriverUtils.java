package driver.manager;

import java.time.Duration;

public class DriverUtils {

    public static void setInitialConfiguration() {
        DriverManager.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.getWebDriver().manage().window().maximize();
    }

    public static void navigateToPage(String pageUrl) {
        DriverManager.getWebDriver().navigate().to(pageUrl);
    }

}