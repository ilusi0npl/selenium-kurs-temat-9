package tests;

import org.testng.annotations.Test;
import page.objects.LandingPage;
import page.objects.LoginPage;
import page.objects.TopMenuPage;

import static org.testng.AssertJUnit.assertEquals;

public class FailedLoginTests extends TestBase {

    @Test
    public void asUserTryToLogInWithIncorrectLoginAndPassword() {

        LandingPage landingPage = new LandingPage();
        landingPage.clickOnEnterStoreLink();

        TopMenuPage topMenuPage = new TopMenuPage();
        topMenuPage.clickOnSignInLink();

        LoginPage loginPage = new LoginPage();
        loginPage.typeIntoUserNameField("NotExistingLogin");
        loginPage.typeIntoPasswordField("NotProperPassword");
        loginPage.clickOnLoginButton();
        String warningMessage = loginPage.getWarningMessage();

        assertEquals(warningMessage, "Invalid username or password. Signon failed.");
    }

}