package com.qaminds.tests.home.header;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qaminds.pages.Pages;
import com.qaminds.tests.BaseTest;
import com.qaminds.utils.reporter.ReporterManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class HomePageTest extends BaseTest {

    @Test
    public void validateHomePage() {
        log.info("Step 1: Get the title of Home Page");
        String title = getDriver().getTitle();
        System.out.println("The title of the Home page is: " + title);
        log.info("Step 3: Validate the title is correct");
        assertThat(title).isEqualTo("Tripadvisor: más de mil millones de opiniones y aportes sobre hoteles, atracciones, restaurantes y más");
        ReporterManager.createLogTest().log(Status.INFO, MarkupHelper.createLabel("Validate the title", ExtentColor.BLUE));
    }

    @Test
    public void validateVisibleLogo() {
        log.info("Step 1: Logo is visible in the home page");
        Boolean isLogoVisible = Pages.returnHomePage(getDriver()).isVisibleLogo();
        assertThat(isLogoVisible).isTrue();
    }
}