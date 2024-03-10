package com.qaminds.utils.reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.qaminds.utils.ScreenshotHelpers;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ReporterTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("--------- Start Test: {} ----------", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("--------- Successful Test : {} ----------", result.getName());
        ReporterManager.createLogTest().log(Status.PASS, MarkupHelper.createLabel("Successful Test", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String message = String.format("--------- Failed Test : {} ---------- \\ln{}", result.getName(), result.getThrowable().getMessage());

        log.error(message);

        Media img = MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotHelpers.screenshot()).build();
        ReporterManager.createLogTest().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED), img);
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("--------- {} Tests Failed----------", context.getFailedTests().size());
        log.info("--------- {} Tests Success----------", context.getPassedTests().size());
        EmailSender.sendEmail(ReporterManager.getReportHTMLPath());
    }
}
