package com.m2.utils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.m2.engine.BaseTest.driver;

public class ScreenshotRobot {

    public static void takeScreenShot() {
        try {
            // Take base64Screenshot screenshot for extent reports
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).
                    getScreenshotAs(OutputType.BASE64);
            // extent reports log and screenshot operations for test steps
            ExtentManager.getTest().log(Status.INFO, "Screenshot attached");
            ExtentManager.getTest().addScreenCaptureFromBase64String(base64Screenshot);

        } catch (Exception e) {
            TestLogger.error("Failed to take screenshot " + e.getMessage());
        }
    }
}
