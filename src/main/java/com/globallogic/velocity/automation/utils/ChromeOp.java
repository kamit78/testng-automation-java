package com.globallogic.velocity.automation.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Amit.Kumar2 on 7/22/2017.
 */
public class ChromeOp {

    public void getChromeOption(){

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("user-data-dir=C:/Users/amit.kumar2/AppData/Local/Google/Chrome/Custom");
        chromeOptions.addArguments("--start-maximized");

        WebDriver webDriver = new ChromeDriver(chromeOptions);

        webDriver.get("https://www.google.co.in");

        Set<Cookie> a = webDriver.manage().getCookies();

        if(a.isEmpty()){

            System.out.println("Cookies set  is empty........");
        }

        Iterator<Cookie> iter = a.iterator();

        while(iter.hasNext()){

            System.out.println(iter.next().getName());
        }
    }
}
