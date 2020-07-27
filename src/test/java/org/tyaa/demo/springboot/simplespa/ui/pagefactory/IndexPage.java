package org.tyaa.demo.springboot.simplespa.ui.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends AbstractPage {

    private By signInButton = By.cssSelector("nav a[href*='signin']");
    private By logOutButton = By.cssSelector("nav a[href*='#!home:out']");

    public IndexPage(WebDriver driver) {
        super(driver);
        System.out.println("IndexPage Loaded");
    }

    public SignInPage clickSignIn() {
        driver.findElement(signInButton).click();
        return new SignInPage(driver);
    }

    public String getLogOutButtonText() {
        WebElement logOutButtonElement =
                driver.findElement(logOutButton);
        return logOutButtonElement != null ? logOutButtonElement.getText() : null;
    }
}
