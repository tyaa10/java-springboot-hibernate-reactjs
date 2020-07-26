package org.tyaa.demo.springboot.simplespa.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPageTest {

    protected static WebDriver driver;

    @BeforeAll
    private static void setupAll() {
        System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
    }

    @BeforeEach
    private void setupEach() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    private void disposeEach() {
        driver.quit();
    }
}
