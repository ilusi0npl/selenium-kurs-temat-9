package page.objects;

import driver.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

public class AngelfishListPage {

    private Logger logger = LogManager.getRootLogger();

    @FindBy(css = "a.Button[href$='EST-2']")
    private WebElement smallAngelfishAddToCartButton;

    public AngelfishListPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    public ShoppingCartPage clickOnAddToCartSmallAngelfish() {
        WaitForElement.waitUntilElementIsVisible(smallAngelfishAddToCartButton);
        smallAngelfishAddToCartButton.click();
        logger.info("Clicked on Small Angelfish Add to cart button");
        return new ShoppingCartPage();
    }
}