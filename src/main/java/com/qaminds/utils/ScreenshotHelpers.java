package com.qaminds.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScreenshotHelpers {

    private static String directory = "";
    private static WebDriver driver;

    public ScreenshotHelpers(WebDriver driver){
        this.driver = driver;
        String currentWorkinkDirectory = System.getProperty("user.dir");
        directory = String.format("%s/src/test/resources/screenshot/%s", currentWorkinkDirectory, new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
    }

    public static String screenshot(){
        String fileName = String.format("screenshot_%s.png", new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));

        File dirScreenshot = new File(directory);
        if(!dirScreenshot.exists()){
            dirScreenshot.mkdirs();
        }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File newScreenshotFile = new File(dirScreenshot, fileName);

        try {
            Files.copy(screenshotFile.toPath(), newScreenshotFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newScreenshotFile.toURI().toString();
    }
}
