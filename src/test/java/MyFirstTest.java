import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class MyFirstTest {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        //Ustawienie ścieżki do WebDrivera Chrome
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");

        //Inicjalizajca ChromeDriver
        driver = new ChromeDriver();

        //Ustawienie Implicit Wait na 10 sekund
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Przejśćie do strony sklepu
        driver.navigate().to("http://przyklady.javastart.pl/jpetstore/");
    }

    @Test
    public void myFirstTest() {

        //Kliknięcie w link "Enter the Store"
        driver.findElement(By.cssSelector("#Content a")).click();

        //Kliknięcie w link "Sign In"
        driver.findElement(By.cssSelector("#MenuContent a[href*='signonForm']")).click();

        //Wpisanie w polu Username wartośći "NotExistingLogin"
        driver.findElement(By.name("username")).sendKeys("NotExistingLogin");

        //Wpisanie w polu Password wartości "NotProperPassword"
        driver.findElement(By.name("password")).sendKeys("NotProperPassword");

        //Kliknięcie w przycisk Login
        driver.findElement(By.name("signon")).click();

        //Sprawdzenie czy na stronie pojawił się komunikat "Invalid username or password. Signon failed."
        // przez sprawdzenie jaki tekst wyświetli się w elemencie
        assertEquals(driver.findElement(By.cssSelector("#Content ul[class='messages'] li")).getText(), "Invalid username or password. Signon failed.");
    }

    @AfterMethod
    public void afterTest() {

        //Zamknięcie okna przeglądarki
        driver.close();

        //Zabicie procesu WebDrivera
        driver.quit();
    }

}