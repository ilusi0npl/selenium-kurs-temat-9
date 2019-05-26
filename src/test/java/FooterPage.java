import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage {

    @FindBy(css = "#Banner img[src*='dog']")
    private WebElement bannerAfterLoginLogo;

    private WebDriver driver;

    public FooterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isBannerAfterLoginDisplayed(){
        boolean isDisplayed = bannerAfterLoginLogo.isDisplayed();
        return isDisplayed;
    }

}