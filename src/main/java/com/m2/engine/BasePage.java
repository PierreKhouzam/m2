package com.m2.engine;

import com.aventstack.extentreports.Status;
import com.m2.utils.ExtentManager;
import com.m2.utils.TestLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.m2.engine.BaseTest.driver;
import static com.m2.engine.DriverScript.objectRepo;

public class BasePage {

    WebDriverWait wait ;

    public BasePage(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Wait till object is visible
    public void waitVisibility(String object) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectRepo.getProperty(object))));
            TestLogger.info("Waiting visibility of object: " + object);
            ExtentManager.getTest().log(Status.PASS, "Waiting visibility of object: " + object);
        } catch (Exception e) {
            TestLogger.error("Failed to find object: " + object + ", While error is: " + e);
            ExtentManager.getTest().log(Status.FAIL, "Failed to find object: " + object);
        }
    }

    // Wait till object is clickable
    public void waitClickability(String object) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectRepo.getProperty(object))));
            TestLogger.info("Waiting object to be clickable: " + object);
            ExtentManager.getTest().log(Status.PASS, "Waiting clickability of object: " + object);
        } catch (Exception e) {
            TestLogger.error("Failed to find object: " + object + ", While error is: " + e);
            ExtentManager.getTest().log(Status.FAIL, "Failed to find clickable object: " + object);
        }
    }

    // Click on object
    public BasePage click(String object) {
        try {
            waitVisibility(object);
            waitClickability(object);
            driver.findElement(By.xpath(objectRepo.getProperty(object))).click();
            TestLogger.info("Clicking on object: " + object);
            ExtentManager.getTest().log(Status.PASS, "Clicking on object: " + object);
        } catch (Exception e) {
            TestLogger.error("Failed to click on object: " + object + ", While error is: " + e);
            ExtentManager.getTest().log(Status.FAIL, "Failed to click on object: " + object);
        }
        return this;
    }

    // Scroll into view and click on object
    public void scrollIntoViewAndClick(String object) {
        waitVisibility(object);
        WebElement element = driver.findElement(By.xpath(objectRepo.getProperty(object)));
        try {
            // Scroll to the center of the element then click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            TestLogger.info("Scrolled to and clicked on: " + object);
            ExtentManager.getTest().log(Status.PASS, "Scrolled to and clicked on: " + object);
        } catch (Exception e) {
            TestLogger.error("Couldn't scroll or click on object " + object + ", while error is " + e);
            ExtentManager.getTest().log(Status.FAIL, "Couldn't scroll or click on object " + object);
        }
    }
}

