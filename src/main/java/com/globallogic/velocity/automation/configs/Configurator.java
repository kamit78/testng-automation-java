package com.globallogic.velocity.automation.configs;

import org.testng.Reporter;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Amit.Kumar2 on 7/26/2017.
 */
public class Configurator{

    private String getProperty(String propertyName){

        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
            return properties.getProperty(propertyName);

        }catch (IOException a ){

            Reporter.log(a.getMessage());
            return null;
        }

    }

    public String property(String property){

        Configurable configurable = (String url) -> {

            return getProperty(url);

        };

        return configurable.property(property);
    }

//    public String property(String property) {
//        return getProperty(property);
//    }
}
