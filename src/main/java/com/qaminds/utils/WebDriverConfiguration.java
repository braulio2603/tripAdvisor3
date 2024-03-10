package com.qaminds.utils;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class WebDriverConfiguration {
    private WebDriver driver;

    private static WebDriverConfiguration webDriverConfiguration;

    public WebDriverConfiguration(WebDriver driver){
        setDriver(driver);
    }

    public static WebDriverConfiguration getInstance(WebDriver driver){
        return webDriverConfiguration = new WebDriverConfiguration(driver);
    }
}
