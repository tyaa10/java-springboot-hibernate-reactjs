package org.tyaa.demo.springboot.simplespa.ui.pagefactory;

import org.openqa.selenium.WebDriver;

public class SignInPage extends AbstractPage {

    public SignInPage(WebDriver driver) {
        super(driver);
        System.out.println("SignInPage Loaded");
    }
}
