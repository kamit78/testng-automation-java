package com.globallogic.velocity.automation.listners;

import com.globallogic.velocity.automation.Base;
import com.globallogic.velocity.automation.Browser;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * Created by Amit.Kumar2 on 7/26/2017.
 */
public class SuiteExecutionListener extends Base implements ISuiteListener {

    @Override
    public void onStart(ISuite iSuite) {

        System.out.println("Starting test execution .....");

    }

    @Override
    public void onFinish(ISuite iSuite) {

        System.out.println("Ending suite ......");

    }
}
