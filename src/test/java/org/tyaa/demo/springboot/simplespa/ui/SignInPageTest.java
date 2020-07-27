package org.tyaa.demo.springboot.simplespa.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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

    private SignInPage signInPage;

    @BeforeEach
    public void setupCase() {
        signInPage = indexPage.clickSignIn();
    }

    @Test
    @Order(1)
    public void performSignInWithCorrectAdminUserNameAndPassword() {
        signInPage.loginWithValidCredentials("admin", "AdminPassword1");
        assertEquals("http://localhost:8090/simplespa/#!home", driver.getCurrentUrl());
        String logOutButtonText = indexPage.getLogOutButtonText();
        assertNotNull(logOutButtonText);
        assertEquals("Sign Out (admin)", logOutButtonText);
    }

    @Test
    @Order(2)
    public void failSignInWithWrongUserNameAndCorrectAdminPassword() {
        //signInPage =
            signInPage.loginWithInvalidCredentials("wrong", "AdminPassword1");
        String logOutButtonText = indexPage.getLogOutButtonText();
        assertEquals("", logOutButtonText);
        String errorText = signInPage.getErrorText();
        assertNotNull(errorText);
        assertEquals("Error: wrong username or password", errorText);
    }
}
