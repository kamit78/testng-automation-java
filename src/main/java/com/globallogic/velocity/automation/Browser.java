package com.globallogic.velocity.automation;

import com.globallogic.velocity.automation.configs.Configurator;
import com.globallogic.velocity.automation.pages.LandingPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.asserts.SoftAssert;

import javax.xml.bind.Element;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.AccessException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Amit.Kumar2 on 7/20/2017.
 */
public class Browser extends Base {

    private Consumer<WebDriver> action = null;

    public Browser(String browser) {
        SeleniumCallBack seleniumCallBack = new SeleniumCallBack(browser);
        Base.driver = seleniumCallBack.getDriver();
    }

    public Actions perform(Consumer<WebDriver> consumer) {
        System.out.println("clicked ......");

        action = consumer;
        return new Actions();
    }


    public class Actions {

        public void click(By by) {
            System.out.println("Performing callback ........");
            action.accept(driver);
            action = null;

        }

        private void PageAction(Class<?> c, String methodName) {

            try {

                for (Method m : c.getMethods()) {

                    System.out.println(m.getName());
                }

                Constructor<?> constructor = c.getConstructor(WebDriver.class);
                System.out.println("Parameter of constructor is ......");
                System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"
                Object obj = constructor.newInstance(driver);
                Method m = obj.getClass().getMethod(methodName, null);
                m.invoke(obj, null);
                action.accept(driver);
                action = null;
//                return this;

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {

                System.out.println("Exception occurred.." + e.getMessage() + e.getStackTrace());
                action = null;
                Assert.fail(e.getMessage() + e.getStackTrace());
//                return this;
            }

        }

        private void PageAction(Class<?> c, String methodName, String launch) {

            try {

                for (Method m : c.getMethods()) {

                    System.out.println(m.getName());
                }

                Constructor<?> constructor = c.getConstructor(WebDriver.class);
                System.out.println("Parameter of constructor is ......");
                System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"
                Object obj = constructor.newInstance(driver);
                Method method = obj.getClass().getMethod(methodName, null);
                Method get = obj.getClass().getMethod(launch, null);
                get.invoke(obj, null);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                method.invoke(obj, null);
                action.accept(driver);
                action = null;
//                return this;

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {

                System.out.println("Exception occurred.." + e.getMessage() + e.getStackTrace());
                action = null;
                Assert.fail(e.getMessage() + e.getStackTrace());
//                return this;
            }
        }

        private void PageAction(Class<?> c, String methodName, String launch, Object... args) {

            try {

                for (Method m : c.getMethods()) {

                    System.out.println(m.getName());
                }

                Constructor<?> constructor = c.getConstructor(WebDriver.class);
                System.out.println("Parameter of constructor is ......");
                System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"
                Object obj = constructor.newInstance(driver);
                Method get = obj.getClass().getMethod(launch, null);
                get.invoke(obj, null);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                for (Method g : obj.getClass().getDeclaredMethods()) {
                    System.out.print(g.getName());
                    System.out.print("  ::  ");
                    System.out.print(g.getParameterCount());
                    System.out.print("  ::  ");
                    System.out.print(g.getParameterTypes().toString());
                    System.out.print("  ::  ");
                    System.out.println(g.getModifiers());
                    if (g.getName().equalsIgnoreCase(methodName)) {

                        Method m = obj.getClass().getMethod(methodName, g.getParameterTypes());
                        m.invoke(obj, args);
                        break;
                    }
                }

                action.accept(driver);
                action = null;
//                return this;

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {

                System.out.println("Exception occurred.." + e.getMessage() + e.getStackTrace());
                action = null;
                Assert.fail(e.getMessage() + e.getStackTrace());
//                return this;
            }
        }

        private void PageAction(Class<?> c, String methodName, Object... args) {

            try {

                for (Method m : c.getMethods()) {

                    System.out.println(m.getName());
                }

                Constructor<?> constructor = c.getConstructor(WebDriver.class);
                System.out.println("Parameter of constructor is ......");
                System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"
                Object obj = constructor.newInstance(driver);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                for (Method g : obj.getClass().getDeclaredMethods()) {
                    System.out.print(g.getName());
                    System.out.print("  ::  ");
                    System.out.print(g.getParameterCount());
                    System.out.print("  ::  ");
                    System.out.print(g.getParameterTypes().toString());
                    System.out.print("  ::  ");
                    System.out.println(g.getModifiers());
                    if (g.getName().equalsIgnoreCase(methodName)) {

                        Method m = obj.getClass().getMethod(methodName, g.getParameterTypes());
                        m.invoke(obj, args);
                        break;
                    }
                }

                action.accept(driver);
                action = null;
//                return this;

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {

                System.out.println("Exception occurred.." + e.getMessage() + e.getStackTrace());
                action = null;
                Assert.fail(e.getMessage() + e.getStackTrace());
//                return this;
            }
        }

        public Actions InvokePage(Class<?> c, String methodName, String launch, Object... args) {

            if (args.equals(null) && !(launch.equals(null))) {

                PageAction(c, methodName, launch);

            } else if (launch.equals(null) && !(args.equals(null))) {

                PageAction(c, methodName, args);

            } else if (launch.equals(null) && args.equals(null)) {
                PageAction(c, methodName);
            } else
                PageAction(c, methodName, launch, args);

            return this;
        }

        public void WaitFor(By by) {

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(30, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .withMessage(() -> "Waiting for element ...");

            wait.until((ExpectedCondition<WebElement>) (webDriver) -> webDriver.findElement(by));
        }

        public void quitBrowser() {

            driver.quit();
            action.accept(driver);
        }



    }
}
