package com.globallogic.velocity.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Predicate;

/**
 * Created by Amit.Kumar2 on 7/20/2017.
 */
public class SeleniumCallBack extends Base {

    private String browser;

    public SeleniumCallBack(String browser) {
        this.browser = browser;
    }

    public WebDriver getDriver() {

        if (driver != null)
            return driver;
        else
            return getBrowser((browser) -> this.browser.equalsIgnoreCase(browser));
    }

    private WebDriver getBrowser(Predicate<String> predicate) {

        if (predicate.test("Chrome")) {
            System.out.println("Starting chrome driver .......");
            System.setProperty("webdriver.chrome.driver", "D:\\selenium-grid\\chromedriver.exe");
            return new ChromeDriver();
        }

        if (predicate.test("Firefox")) {

            System.out.println("Starting gecko driver ......");
            System.setProperty("webdriver.gecko.driver", "D:\\GeckoDriver\\geckodriver.exe");
            return new FirefoxDriver();
        }

        System.out.println("inside get browser function ....");
        return null;
    }
}
