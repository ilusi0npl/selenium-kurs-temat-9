package page.objects;

import driver.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

public class FishListPage {

    private Logger logger = LogManager.getRootLogger();

    @FindBy(css = "tr:nth-child(2) a")
    private WebElement angelfishIdLink;

    public FishListPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    public AngelfishListPage clickOnAngelfishId() {
        WaitForElement.waitUntilElementIsVisible(angelfishIdLink);
        angelfishIdLink.click();
        logger.info("Clicked on Angelfish Link");
        return new AngelfishListPage();
    }
}