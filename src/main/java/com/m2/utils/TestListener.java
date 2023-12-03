package com.m2.utils;

import com.aventstack.extentreports.Status;
import com.m2.engine.BaseTest;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestListener extends BaseTest implements ITestListener {

    public void onStart(ITestContext context) {
        TestLogger.startTestSuite(context.getName());
    }

    public void onTestStart(ITestResult result) {
        TestLogger.info(("*** Running test method " + result.getMethod().getMethodName() + " ***"));
        ExtentManager.startTest(result.getMethod().getDescription());

    }


    @SneakyThrows
    public void onTestSuccess(ITestResult result) {
        TestLogger.info("*** Executed " + result.getMethod().getMethodName() + " test successfully ***");
        ExtentManager.getTest().log(Status.PASS, "Executed " + result.getMethod().getMethodName() + " test successfully.");
        Thread.sleep(3000);
        ScreenshotRobot.takeScreenShot();

    }

    public void onTestFailure(ITestResult result) {
        TestLogger.info("*** Test execution of " + result.getMethod().getMethodName() + " failed ***");
        ExtentManager.getTest().log(Status.FAIL, result.getMethod().getMethodName()
                + " Test failed as " + result.getThrowable().getMessage());
        ScreenshotRobot.takeScreenShot();
    }


    public void onTestSkipped(ITestResult result) {
        TestLogger.info("*** Test " + result.getMethod().getMethodName() + " skipped ***");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        TestLogger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName() + " ***");
    }

    public void onFinish(ITestContext context) {
        TestLogger.info(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentReporter.getReport().flush();
        File htmlFile = new File(System.getProperty("user.dir") + "/extentreports/ExtentReportResults.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            TestLogger.error("*** Couldn't fire report " + e);
        }
    }
}
