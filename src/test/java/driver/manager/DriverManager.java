package driver.manager;

import driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

import static configuration.TestRunProperties.getBrowserToRun;
import static configuration.TestRunProperties.getIsRemoteRun;
import static driver.BrowserType.FIREFOX;

public class DriverManager {

    //Utworzenie zmiennej webDriverThreadLocal
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {

        //Sprawdzenie czy wartość zmiennej WebDrivera dla danego wątku jest nullem
        if (webDriverThreadLocal.get() == null) {

            //Wywołanie metody getBrowser() z klasy BrowserFactory zwraca instancję WebDrivera, który następnie jest
            // dodana do puli instancji klasy ThreadLocal za pomocą metody set()
            webDriverThreadLocal.set(new BrowserFactory(getBrowserToRun(), getIsRemoteRun()).getBrowser());
        }

        //Zwrócenie instancji WebDrivera dla danego wątku
        return webDriverThreadLocal.get();
    }

    public static void disposeDriver() {

        //Wywołanie metody close() z klasy WebDriver dla danego wątku
        webDriverThreadLocal.get().close();
        if (!getBrowserToRun().equals(FIREFOX)) {

            //Wywołanie metody quit() z klasy WebDriver dla danego wątku
            webDriverThreadLocal.get().quit();
        }

        //Wywołanie metody remove() z klasy ThreadLocal dla danego wątku w celu usunięcia WebDrivera dla aktualnego wątku
        webDriverThreadLocal.remove();
    }
}