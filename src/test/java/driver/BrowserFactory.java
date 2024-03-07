package driver;

import configuration.LocalWebDriverProperties;
import configuration.TestRunProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private static final String MESSAGE_UNKNOWN_BROWSER = "Unknown browser type! Please check your configuration";

    //Pole określające typ przeglądarki
    private BrowserType browserType;

    //Pole określające czy uruchomienie jest zdalne czy lokalne
    private boolean isRemoteRun;

    //Konstruktor dla klasy, który ustawia wartości pól browserType or isRemoteRun
    public BrowserFactory(BrowserType browserType, boolean isRemoteRun) {
        this.browserType = browserType;
        this.isRemoteRun = isRemoteRun;
    }

    //Metoda dostarcza obiekt WebDrivera
    public WebDriver getBrowser() {

        //Sprawdzamy czy uruchomienie jest zdalne, jeśli tak to kod wejdzie do warunku
        if (isRemoteRun) {

            //Tworzymy obiekt desiredCapabilities, który jest wymagany do wyboru przeglądarki
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            //Wybór przeglądarki zdalnej w zależności od wartości pola browserType
            switch (browserType) {
                case CHROME:

                    //Do wyboru przeglądarki Chrome używamy klasy ChromeOptions
                    ChromeOptions chromeOptions = new ChromeOptions();
                    desiredCapabilities.merge(chromeOptions);
                    return getRemoteWebDriver(desiredCapabilities);
                case FIREFOX:

                    //Do wyboru przeglądarki FireFox używamy klasy FireFoxOptions
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    desiredCapabilities.merge(firefoxOptions);
                    return getRemoteWebDriver(desiredCapabilities);
                case IE:

                    //Do wyboru przeglądarki InternetExplorer używamy klasy InternetExplorerOptions
                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                    desiredCapabilities.merge(internetExplorerOptions);
                    return getRemoteWebDriver(desiredCapabilities);
                default:
                    throw new IllegalStateException(MESSAGE_UNKNOWN_BROWSER);
            }

            //Jeśli uruchomienie nie jest zdalne, kod wchodzi do else. Jest to uruchomienie lokalne
        } else {

            //Wybór przeglądarki zdalnej w zależności od wartości pola browserType. Analogicznie jak wyżej
            switch (browserType) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", LocalWebDriverProperties.getChromeWebDriverLocation());
                    return new ChromeDriver();
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", LocalWebDriverProperties.getFirefoxWebDriverLocation());
                    return new FirefoxDriver();
                case IE:
                    System.setProperty("webdriver.ie.driver", LocalWebDriverProperties.getInternetExplorerWebDriverLocation());
                    return new InternetExplorerDriver();
                default:
                    throw new IllegalStateException(MESSAGE_UNKNOWN_BROWSER);
            }

        }
    }

    //Metoda zwraca nam obiekt RemoteWebDrivera na podstawie obiektu desiredCapabilities
    private WebDriver getRemoteWebDriver(DesiredCapabilities desiredCapabilities) {
        RemoteWebDriver remoteWebDriver = null;

        try {
            remoteWebDriver = new RemoteWebDriver(new URL(TestRunProperties.getGridUrl()), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create RemoteWebDriver due to: " + e.getMessage());
        }
        return remoteWebDriver;
    }
}