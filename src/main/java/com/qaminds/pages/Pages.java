package com.qaminds.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    private static <T> T Pages(WebDriver driver, Class<T> clazz){
        return PageFactory.initElements(driver, clazz);
    }
    public static HomePage returnHomePage(WebDriver driver){
        return Pages(driver, HomePage.class);
    }
    public static HotelsPage returnHotelsPage(WebDriver driver){
        return Pages(driver, HotelsPage.class);
    }
}
