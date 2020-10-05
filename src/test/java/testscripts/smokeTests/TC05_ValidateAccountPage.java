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
import com.automation.pages.MyAccountPage;
import com.automation.pages.ProductListingPage;
import com.automation.pages.ProductPage;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners(ExtentITestListenerClassAdapter.class)
public class TC05_ValidateAccountPage extends BaseClass {

	HomePage homePage;
	MyAccountPage myAccountPage;
	ProductListingPage productListingPage;
	ProductPage productPage;
	

	public TC05_ValidateAccountPage() {
		super();
	}

	@BeforeMethod
	@Parameters({ "executionMode", "toolName", "mobileExecutionPlatform", "mobileOsVersion", "deviceName", "browser",
			"browserVersion", "platform", "platformVersion", "dataTableName" })
	public void setUp(@Optional("LOCAL") String executionMode, @Optional("") String toolName,
			@Optional("") String mobileExecutionPlatform, @Optional("") String mobileOsVersion,
			@Optional("") String deviceName, @Optional("EDGE") String browser, @Optional("") String browserVersion,
			@Optional("") String platform, @Optional("") String platformVersion,
			@Optional("Website_Validations") String dataTableName) {
		initializeWebDriver(executionMode, toolName, mobileExecutionPlatform, mobileOsVersion, deviceName, browser,
				browserVersion, platform, platformVersion, dataTableName);
		homePage = new HomePage(this.getClass().getSimpleName());
		myAccountPage = new MyAccountPage(this.getClass().getSimpleName());
		productListingPage = new ProductListingPage(this.getClass().getSimpleName());
		productPage = new ProductPage(this.getClass().getSimpleName());
		report = new Report(this.getClass().getSimpleName());

	}

	@Test
	public void validateAccountPage() throws Exception {
		try {
			homePage.invokeApplication();
			homePage.navigateToMyAccountPage();
			myAccountPage.loginToAccount();
			homePage.searchProductLoggedIn();
			productListingPage.clickOnFirstProduct();
			homePage.navigateToMyAccountPageLoggedIn();
			myAccountPage.verifyOrdersInYourAccount();
			myAccountPage.validateRecentlyViewed();
			myAccountPage.enterNewAddress();
			myAccountPage.deleteAddress();
			myAccountPage.validateAccountSettings();
			myAccountPage.logOut();
			
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
