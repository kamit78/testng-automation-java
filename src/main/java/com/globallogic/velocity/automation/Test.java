package com.globallogic.velocity.automation;

import com.globallogic.velocity.automation.configs.Configurator;
import com.globallogic.velocity.automation.pages.LandingPage;
import com.globallogic.velocity.automation.utils.CSVUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Amit.Kumar2 on 7/22/2017.
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

         Object [][] objArray;

         Test t = new Test();

         objArray = CSVUtil.getCSVDataAsArray(t.getFile());

        System.out.println(objArray.length);

        for(int i=0; i< objArray.length; i++)
            for(int j=0; j<objArray.length;j++)
                System.out.println(objArray[j][i]);



//        SeleniumCallBack seleniumCallBack = new SeleniumCallBack("Chrome");
//        Base.driver = seleniumCallBack.getDriver();

//        Browser browser = new Browser("Chrome");
//
//        File scrFile = ((TakesScreenshot)Base.driver).getScreenshotAs(OutputType.FILE);
//
//        System.out.println(scrFile.getAbsolutePath());
//        System.out.println(scrFile.getName());
//        try {
//            System.out.println(scrFile.getCanonicalPath());
//        }catch (IOException e){
//
//            System.out.println("Exception occcured ....");
//        }


//
//        LandingPage pagee = new LandingPage(Base.driver);
//        pagee.get();
//        pagee.enterGoogle();
//        pagee.enterUserName();


//        Object[] oargs = {"amit.kumar2", "heartop#3"};
//
//        Class<?> c = LandingPage.class;
//        Constructor<?> constructor = c.getConstructor(WebDriver.class);
//        System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"
//        Object obj = constructor.newInstance(Base.driver);
//        for(Method g : obj.getClass().getDeclaredMethods()){
//            System.out.print(g.getName());
//            System.out.print("  ::  ");
//            System.out.print(g.getParameterCount());
//            System.out.print("  ::  ");
//            System.out.print(g.getParameterTypes().toString());
//            System.out.print("  ::  ");
//            System.out.println(g.getModifiers());
//            if(g.getName().equalsIgnoreCase("loginToApplication")){
//
//                Method m = obj.getClass().getMethod("loginToApplication", g.getParameterTypes());
//                m.invoke(obj,oargs);
//                break;
//            }
//        }


//        Method m = obj.getClass().getMethod("loginToApplication");
//
//        m.invoke(obj,oargs);

//        Method m = c.getDeclaredMethod("enterUserName", null);
//        System.out.println(m.getName());
//        System.out.println(m.getDeclaringClass());
//        //Object d = c.newInstance();
//        m.invoke(c, Base.driver);

    }

    public String getFile(){

//        ClassLoader classLoader = getClass().getClassLoader();
//        String file = classLoader.getResource("config.properties").getPath();
//        System.out.println(file);
//        return file;
        Path resourceDirectory = Paths.get("src/test/resources/data.csv");
        return resourceDirectory.toString();
    }

}
