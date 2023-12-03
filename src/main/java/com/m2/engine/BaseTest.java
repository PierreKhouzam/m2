package com.m2.engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;


public class BaseTest {
    public static WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();

    public String baseUrl = "https://www.m2.com";


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        DriverScript.objectRepoLoad();
        NetworkHandler.initialize(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @DataProvider(name = "targetUrl")
    public Object[][] targetUrlProvider(Method method) {
        if (method.getName().equals("navigateToMarketsAndFetchAPI")) {
            return new Object[][]{
                    {"https://www.m2.com/asset-service/v1/coinSymbol/list"}
            };
        } else if (method.getName().equals("navigateToWalletsAndSortByPrice")) {
            return new Object[][]{
                    {"https://wa.appsflyer.com/events?site-id=65cd80a2-79a1-4325-bec5-a9014d78eb21"}
            };
        }
        return new Object[][]{{}};
    }
}


