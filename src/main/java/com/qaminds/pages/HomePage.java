package com.qaminds.pages;

import com.qaminds.utils.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

@Slf4j
public class HomePage {
    public Boolean isVisibleLogo(){
      log.debug("Validate is visible the logo in the home page");
      return logo.isEnabled();
    }
    public String getTextOptionHotels(){
        log.debug("Validate the option is: Hotels");
        return optionHotels.getText();
    }
    public String getTextOptionSearchAll(){
        log.debug("Validate the option is: Search All");
        return optionSearchAll.getText();
    }
    public String getTextOptionThinksForDo(){
        log.debug("Validate the option is: Thinks For Do");
        return optionThinksForDo.getText();
    }
    public String getTextOptionRestaurants(){
        log.debug("Validate the option is: Restaurants");
        return optionRestaurants.getText();
    }
    public String getTextOptionVacationRentals(){
        log.debug("Validate the option is: Vacation Rentals");
        return optionVacationRentals.getText();
    }
    public void onClickOptionHotels(){
        log.debug("Click on option Hotels");
        optionHotels.click();
    }
    public void onClickOptionSearchAll(){
        optionSearchAll.click();
    }
    public void onClickOptionThinksForDo(){
        optionThinksForDo.click();
    }
    public void onClickOptionRestaurants(){
        optionRestaurants.click();
    }
    public void onClickOptionVacationRentals(){
        optionVacationRentals.click();
    }
    public void onClickButtonSearchInput(){
        log.debug("Click on text field Search");
        searchInput.click();
    }
    public void sendDestinationPlace(String destination){
        searchInput.sendKeys(destination, Keys.ENTER);
    }
    public String getTitleSearch(WebDriver driver){
        return WaitHelper.waitForElementByWebElement(driver,titleSearchHotels, Duration.ofSeconds(10),Duration.ofSeconds(2)).getAttribute("placeholder");
    }
    @FindBy(css = "div.pCeGb.u")
    public WebElement logo;
    @FindBy(css = "button:nth-child(2) > span > a")
    public WebElement optionHotels;
    @FindBy(css = "div > div > input")
    public WebElement searchInput;
    @FindBy(css = "div.ctKgY > div > form > div > div > input")
    public WebElement titleSearchHotels;
    @FindBy(css = "button.PFswe.d.Gv.B-._S.Mi.Nj.MH.NL.wSSLS.BmgDU > span > span")
    public WebElement optionSearchAll;
    @FindBy(css = "button:nth-child(3) > span > a")
    public WebElement optionThinksForDo;
    @FindBy(css = "div.IgMIB.c > div.eGVWv.q > button:nth-child(4) > span")
    public WebElement optionRestaurants;
    @FindBy(css = "div.eGVWv.q > button:nth-child(5) > span > a")
    public WebElement optionVacationRentals;
}
