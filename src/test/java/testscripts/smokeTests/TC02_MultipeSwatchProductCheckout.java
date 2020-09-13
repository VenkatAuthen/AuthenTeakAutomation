package testscripts.smokeTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.framework.Status;
import com.automation.pages.HomePage;
import com.automation.pages.ProductListingPage;
import com.automation.pages.ProductPage;
import com.automation.pages.ShoppingCartPage;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners(ExtentITestListenerClassAdapter.class)
public class TC02_MultipeSwatchProductCheckout extends BaseClass {

	HomePage homePage;
	ProductListingPage productListingPage;
	ProductPage productPage;
	ShoppingCartPage shoppingCartPage;

	public TC02_MultipeSwatchProductCheckout() {
		super();
	}

	@BeforeMethod
	@Parameters({ "executionMode", "toolName", "mobileExecutionPlatform", "mobileOsVersion", "deviceName", "browser",
			"browserVersion", "platform", "platformVersion", "dataTableName" })
	public void setUp(@Optional("LOCAL") String executionMode, @Optional("") String toolName,
			@Optional("") String mobileExecutionPlatform, @Optional("") String mobileOsVersion,
			@Optional("") String deviceName, @Optional("FIREFOX") String browser, @Optional("") String browserVersion,
			@Optional("") String platform, @Optional("") String platformVersion,
			@Optional("Website_Validations") String dataTableName) {
		initializeWebDriver(executionMode, toolName, mobileExecutionPlatform, mobileOsVersion, deviceName, browser,
				browserVersion, platform, platformVersion, dataTableName);
		homePage = new HomePage(this.getClass().getSimpleName());
		productListingPage = new ProductListingPage(this.getClass().getSimpleName());
		productPage = new ProductPage(this.getClass().getSimpleName());
		shoppingCartPage = new ShoppingCartPage(this.getClass().getSimpleName());
		report = new Report(this.getClass().getSimpleName());

	}

	@Test
	public void verifySimpleProductCheckout() throws Exception {
		try {
			homePage.invokeApplication();
			homePage.searchProduct();
			productListingPage.clickOnFirstProduct();
			productPage.customizeCushionwithSwatches();
			productPage.validateSelectionsMiniCartAndViewCart();
			shoppingCartPage.verifyShoppingCartPage();
			shoppingCartPage.enterCustomerDetails();
			shoppingCartPage.chooseStandarShipping();
			shoppingCartPage.validateOrderSummary();
			shoppingCartPage.enterPaymentDetails();

		} catch (Exception e) {
			Assert.fail();
			Report.updateExtentStatus("", "Test case failed due to the exception:"+e.getMessage(), Status.FAIL);
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
