package org.tyaa.demo.springboot.simplespa.ui.pagefactory;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
