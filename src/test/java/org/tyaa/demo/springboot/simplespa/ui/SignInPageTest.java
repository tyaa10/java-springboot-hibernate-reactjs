package org.tyaa.demo.springboot.simplespa.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tyaa.demo.springboot.simplespa.SpringbootSimplespaApplication;
import org.tyaa.demo.springboot.simplespa.ui.pagefactory.HomePage;
import org.tyaa.demo.springboot.simplespa.ui.pagefactory.SignInPage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = SpringbootSimplespaApplication.class
)
public class SignInPageTest extends AbstractPageTest {

    private HomePage homePage;

    private SignInPage signInPage;

    @BeforeEach
    public void setupCase() {
        driver.get("http://localhost:8090/simplespa/");
        homePage = new HomePage(driver);
        signInPage = homePage.clickSignIn();
    }

    @Test
    public void performSignInWithCorrectAdminUserNameAndPassword() {
        signInPage.loginWithValidCredentials("admin", "AdminPassword1");
        assertEquals("http://localhost:8090/simplespa/#!home", driver.getCurrentUrl());
    }

    @Test
    public void failSignInWithWrongUserNameAndCorrectAdminPassword() {
        signInPage =
            signInPage.loginWithInvalidCredentials("wrong", "AdminPassword1");
        String errorText = signInPage.getErrorText();
        assertNotNull(errorText);
        assertEquals("Error: wrong username or password", errorText);
    }
}
