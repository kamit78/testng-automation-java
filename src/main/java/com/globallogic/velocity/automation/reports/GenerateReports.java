package com.globallogic.velocity.automation.reports;

import com.globallogic.velocity.automation.Base;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Amit.Kumar2 on 7/25/2017.
 */


public class GenerateReports extends Base implements IReporter {

    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlsuite, List<ISuite> suites, String output) {

        System.out.println(System.getProperty("user.dir"));

        extent = new ExtentReports(System.getProperty("user.dir") + File.separator + "ExtentReportTestNG.html", true);

        for (ISuite suite : suites) {

            Map<String, ISuiteResult> results = suite.getResults();

            for (ISuiteResult iSuiteResult : results.values()) {

                ITestContext iTestContext = iSuiteResult.getTestContext();

                buildTestNodes(iTestContext.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(iTestContext.getPassedTests(), LogStatus.PASS);
                buildTestNodes(iTestContext.getSkippedTests(), LogStatus.SKIP);
            }

        }

        extent.flush();
        extent.close();
    }


    public void buildTestNodes(IResultMap iResultMap, LogStatus status) {

        ExtentTest test;

        for (ITestResult iTestResult : iResultMap.getAllResults()) {

            test = extent.startTest(iTestResult.getMethod().getMethodName());

            test.getTest().setStartedTime(getTime(iTestResult.getStartMillis()));
            test.getTest().setEndedTime(getTime(iTestResult.getEndMillis()));

            for (String group : iTestResult.getMethod().getGroups())
                test.assignCategory(group);

            String message = "Test " + status.toString().toLowerCase() + "ed";

            if (iTestResult.getThrowable() != null)
                message = iTestResult.getThrowable().getMessage();
            else
                test.log(status, message);

            if (status.equals(LogStatus.FAIL)) {
                test.log(status, "Failed .... Taking screenshot");
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    String destinationFile = System.getProperty("user.dir") + File.separator + "screenshots"+ File.separator + "screenshot" + "_" + getTimeMillis() + ".png";
                    File desFile = new File(destinationFile);
                    FileUtils.copyFile(scrFile, desFile);
                    test.addScreenCapture(destinationFile);
                    test.log(status, test.addScreenCapture(destinationFile));

                }catch (IOException e){
                    System.out.println("Exception in embedding screenshot ....." + e.getMessage());
                    test.log(status, e.getMessage());
                }


            }else if(status.equals(LogStatus.PASS)){
                test.log(status, "Passed .... Taking screenshot");
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    String destinationFile = System.getProperty("user.dir") + File.separator + "screenshots"+ File.separator + "screenshot" + "_" + getTimeMillis() + ".png";
                    File desFile = new File(destinationFile);
                    FileUtils.copyFile(scrFile, desFile);
                    test.addScreenCapture(destinationFile);
                    test.log(status, test.addScreenCapture(destinationFile));

                }catch (IOException e){
                    System.out.println("Exception in embedding screenshot ....." + e.getMessage());
                    test.log(status, e.getMessage());
                }
            }
//                test.addBase64ScreenShot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));

            test.log(status, message);
            extent.endTest(test);

        }

    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    private long getTimeMillis(){

        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();

    }
}
