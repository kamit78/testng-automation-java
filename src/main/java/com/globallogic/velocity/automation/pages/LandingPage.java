package com.globallogic.velocity.automation.pages;

import com.globallogic.velocity.automation.Base;
import com.globallogic.velocity.automation.configs.Configurator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.concurrent.TimeUnit;

/**
 * Created by Amit.Kumar2 on 7/24/2017.
 */
public class LandingPage extends LoadableComponent<LandingPage> {

    private WebDriver driver;
    private final String title = "System Dashboard - Velocity Jira";
//    private final String title = "Google";


    public LandingPage(WebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    @Override
    protected void load() {

        driver.get(new Configurator().property("url"));
//        driver.get("https://www.google.co.in");
    }

    @Override
    protected void isLoaded() throws Error {

        Assert.assertEquals(driver.getTitle(), this.title);
    }

    @FindBy(how = How.CSS, using = "#login-form-username")
    private WebElement usernameText;

    @FindBy(how = How.CSS, using = "#login-form-password")
    private WebElement passwordText;

    @FindBy(how = How.CSS, using = "#login")
    private WebElement loginButton;

//    @FindBy(how = How.ID, using = "lst-ib")
//    private WebElement googleText;


    private void enterUserName(String username) {

        System.out.println("Enter username......");

        if(usernameText == null){

            System.out.println("value is null.....");
        }

        usernameText.sendKeys(username);

    }

//    public void enterGoogle(){
//
//        googleText.sendKeys("hi");
//    }

    private void enterPassword(String password) {

        passwordText.sendKeys(password);
    }

    private void submitLogin() {

        loginButton.click();

    }

    public void loginToApplication(String username, String password){

      enterUserName(username);
      enterPassword(password);
      submitLogin();

    }

}
