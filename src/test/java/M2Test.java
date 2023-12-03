import com.m2.engine.*;
import com.m2.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class M2Test extends BaseTest {

    @Test(description = "TC1: Check navigation to Markets link and fetch network requests", dataProvider = "targetUrl")
    public void navigateToMarketsAndFetchAPI(String targetUrl) {
        NetworkHandler.captureNetwork(targetUrl);
        driver.get(baseUrl);

        // Navigate to Wallets tab
        BasePage basePage = new BasePage(driver);
        basePage.click("marketsLink").click("randomElement");


        // Validate current URL
        softAssert.assertEquals(driver.getCurrentUrl(), "https://www.m2.com/en_AE/markets/");
        softAssert.assertAll();

        // More Assertions can be added for captured request here
    }

    @Test(description = "TC2: Check navigation to Wallets tab and sort by Price Desc", dataProvider = "targetUrl")
    public void navigateToWalletsAndSortByPrice(String targetUrl) {

        // Capture Network Requests, specifying target URL
        NetworkHandler.captureNetwork(targetUrl);
        driver.get(baseUrl);

        // Navigate to Wallets tab
        BasePage basePage = new BasePage(driver);
        basePage.click("earnLink").click("walletsTab").scrollIntoViewAndClick("priceSortDes");


        // Validate current URL
        softAssert.assertEquals(driver.getCurrentUrl(), "https://www.m2.com/en_AE/wallets/");
        softAssert.assertAll();

        // More Assertions can be added for captured request here
    }
}
