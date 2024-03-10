package com.qaminds.pages;

import com.qaminds.utils.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

@Slf4j
public class HotelsPage {

    public String getTitleQuerySearch(WebDriver driver){
        log.debug("Validate the search");
        return WaitHelper.waitForElementByWebElement(driver,titleQuerySearch,Duration.ofSeconds(10),Duration.ofSeconds(2)).getText();
    }
    public String getTitleIncorrectValueSearch(WebDriver driver){
        log.debug("Validate the search");
        return WaitHelper.waitForElementByWebElement(driver,titleIncorrectSearch,Duration.ofSeconds(10),Duration.ofSeconds(2)).getText();
    }
    @FindBy(css = "div.main_content.ui_column.is-12 > div > div.ui_columns.is-mobile.is-multiline > div > div.title-column-left > div > span")
    public WebElement titleQuerySearch;
    @FindBy(css = "div > div > div.title-block > span")
    public WebElement titleIncorrectSearch;
}
