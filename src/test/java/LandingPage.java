import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    private WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnEnterStoreLink(){
        WebElement enterStoreLink = driver.findElement(By.cssSelector("#Content a"));
        enterStoreLink.click();

    }
}