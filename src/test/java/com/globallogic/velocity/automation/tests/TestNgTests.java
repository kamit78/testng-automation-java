package com.globallogic.velocity.automation.tests;

import com.globallogic.velocity.automation.Browser;
import com.globallogic.velocity.automation.configs.Configurator;
import com.globallogic.velocity.automation.data.TestData;
import com.globallogic.velocity.automation.pages.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Amit.Kumar2 on 7/20/2017.
 */

public class TestNgTests {

    public Browser browser;

    @BeforeSuite
    public void suiteSetup() {

        browser = new Browser(new Configurator().property("browser"));

    }

    @Test(dataProvider = "default", dataProviderClass = TestData.class)
    public void loginToApplication(String username, String password) {

        browser.perform((callbackDriver) -> {

            System.out.println(callbackDriver.getTitle());
            Assert.assertEquals(callbackDriver.getTitle(), "System Dashboard - Velocity Jira");

        }).InvokePage(LandingPage.class, "loginToApplication","get", username, password).WaitFor(By.id("add-gadget"));
    }


    @AfterSuite
    public void SuiteTearDown(){

        Reporter.log("Suite finiehsed");
    }

}
