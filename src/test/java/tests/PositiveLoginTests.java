package tests;

import org.testng.annotations.Test;
import page.objects.*;

import static org.testng.Assert.assertTrue;

public class PositiveLoginTests extends TestBase {

    @Test
    public void asUserLoginUsingValidLoginAndPassword() {

        LandingPage landingPage = new LandingPage();
        landingPage.clickOnEnterStoreLink();

        TopMenuPage topMenuPage = new TopMenuPage();
        topMenuPage.clickOnSignInLink();

        LoginPage loginPage = new LoginPage();
        loginPage.typeIntoUserNameField("j2ee");
        loginPage.typeIntoPasswordField("j2ee");
        loginPage.clickOnLoginButton();
        FooterPage footerPage = new FooterPage();

        assertTrue(footerPage.isBannerAfterLoginDisplayed());
    }

}