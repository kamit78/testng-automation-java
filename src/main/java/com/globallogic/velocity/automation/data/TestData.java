package com.globallogic.velocity.automation.data;

import com.globallogic.velocity.automation.utils.CSVUtil;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Amit.Kumar2 on 7/26/2017.
 */
public class TestData {

    @DataProvider(name = "default")
    public Object[][] credentials(){
        Path resourceDirectory = Paths.get("src/test/resources/data.csv");
        return CSVUtil.getCSVDataAsArray(resourceDirectory.toString());
    }
}
