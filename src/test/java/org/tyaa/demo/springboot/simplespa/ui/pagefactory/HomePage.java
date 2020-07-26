package org.tyaa.demo.springboot.simplespa.ui.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    private By signInButton = By.cssSelector("nav a[href*='signin']");

    public HomePage(WebDriver driver) {
        super(driver);
        System.out.println("HomePage Loaded");
    }

    public SignInPage clickSignIn() {
        driver.findElement(signInButton).click();
        return new SignInPage(driver);
    }
}
