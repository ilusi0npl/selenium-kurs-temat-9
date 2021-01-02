import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class FailedLoginTests {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        //Ustawienie ścieżki do WebDrivera Chrome
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");

        //Inicliazjacja ChromeDriver
        driver = new ChromeDriver();

        //Ustawienie Implicit Wait na 10 sekund
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Przejśćie do strony sklepu
        driver.navigate().to("http://przyklady.javastart.pl/jpetstore/");
    }

    @Test
    public void asUserTryToLogInWithIncorrectLoginAndPassword() {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnEnterStoreLink();

        TopMenuPage topMenuPage = new TopMenuPage(driver);
        topMenuPage.clickOnSignInLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeIntoUserNameField("NotExistingLogin");
        loginPage.typeIntoPasswordField("NotProperPassword");
        loginPage.clickOnLoginButton();
        String warningMessage = loginPage.getWarningMessage();

        assertEquals(warningMessage, "Invalid username or password. Signon failed.");
    }

    @Test
    public void asUserLoginUsingValidLoginAndPassword() {

        WebElement enterStoreLink = driver.findElement(By.cssSelector("#Content a"));
        enterStoreLink.click();

        WebElement signOnLink = driver.findElement(By.cssSelector("#MenuContent a[href*='signonForm']"));
        signOnLink.click();

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("j2ee");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("j2ee");

        WebElement signOnButton = driver.findElement(By.name("signon"));
        signOnButton.click();

        WebElement bannerAfterLoginLogo = driver.findElement(By.cssSelector("#Banner img[src*='dog']"));

        assertTrue(bannerAfterLoginLogo.isDisplayed());
    }

    @AfterMethod
    public void afterTest() {

        //Zamknięcie okna przeglądarki
        driver.close();

        //Zabicie procesu WebDrivera
        driver.quit();
    }

}