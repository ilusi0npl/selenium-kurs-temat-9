package tests;

import org.testng.annotations.Test;
import page.objects.*;

import static org.testng.Assert.assertEquals;

public class ShoppingCartTest extends TestBase {

    @Test
    public void asNotLoggedInUserIShallNotProceedToCheckout() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnEnterStoreLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFishImageButton();

        FishListPage fishListPage = new FishListPage(driver);
        fishListPage.clickOnAngelfishId();

        AngelfishListPage angelfishListPage = new AngelfishListPage(driver);
        angelfishListPage.clickOnAddToCartSmallAngelfish();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.clickOnProceedToCheckout();

        loginPage = new LoginPage(driver);
        String warningMessage = loginPage.getWarningMessage();
        assertEquals(warningMessage, "You must sign on before attempting to check out. Please sign on and try checking out again.");
    }

}