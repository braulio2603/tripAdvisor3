package com.qaminds.tests;

import com.qaminds.utils.ScreenshotHelpers;
import com.qaminds.utils.WebDriverConfiguration;
import com.qaminds.utils.reporter.ReporterManager;
import com.qaminds.utils.reporter.ReporterTestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Slf4j
@Getter
@Setter
@Listeners(ReporterTestListener.class)
public class BaseTest {

    WebDriver driver;
    private boolean isSuite = false;

    @BeforeSuite
    public void beforeSuite(){
        setSuite(true);
        log.debug("Create the report html in the before Suite");
        ReporterManager.createReportHTML();
    }

    @BeforeMethod
    public void beforeTest(Method method){
        if (!isSuite()){
            log.debug("Create the report html in the Test");
            ReporterManager.createReportHTML();
        }
        ReporterManager.createTest(method.getName());

        WebDriverManager.chromedriver().setup();

        setDriver(WebDriverConfiguration.getInstance(new ChromeDriver()).getDriver());

        getDriver().manage().deleteAllCookies();
        new ScreenshotHelpers(getDriver());
        getDriver().manage().window().maximize();

        log.info("Step 0: Navigate to Tripadvisor.com.mx");
        navigateTo("tripadvisor.com.mx");
    }

    public void navigateTo(String _url){
        String url = String.format("http://%s", _url);
        getDriver().get(url);

        if (!getDriver().getCurrentUrl().contains(_url)){
            log.error(String.format("El navegador no pudo navegar a la pagina solicitada."));
            throw new RuntimeException("No se encontro: " + url);
        }
    }
    @AfterMethod
    public void afterTest(ITestResult testResult){
        if (!isSuite()){
            ReporterManager.extentFlush();
        }
        log.info("Close browser");
        getDriver().quit();
    }
    @AfterSuite
    public void afterSuite(){
        ReporterManager.extentFlush();
    }
}
