package testscripts.smokeTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.pages.HomePage;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners(ExtentITestListenerClassAdapter.class)
public class TC01_SimpleProductCheckout extends BaseClass{

	HomePage homePage;
	
	public TC01_SimpleProductCheckout() {
		super();
	}
	
	@BeforeMethod
	@Parameters({"executionMode", "toolName", "mobileExecutionPlatform", "mobileOsVersion", "deviceName", "browser", "browserVersion", "platform", "platformVersion" })
	public void setUp(@Optional("LOCAL")String executionMode  , @Optional("")String toolName, @Optional("")String mobileExecutionPlatform, @Optional("")String mobileOsVersion, @Optional("")String deviceName, @Optional("CHROME")String browser, @Optional("")String browserVersion, @Optional("")String platform, @Optional("")String platformVersion) {
		initializeWebDriver(executionMode,  toolName,  mobileExecutionPlatform, mobileOsVersion,  deviceName,  browser,  browserVersion,  platform, platformVersion); 
		
		homePage = new HomePage(this.getClass().getSimpleName());
		gl= new Report(this.getClass().getSimpleName());
		gl.start_report(this.getClass().getSimpleName(), "Verify Simple Product Checkout", properties.getProperty("ApplicationUrl"));
	}
	
	@Test
	public void verifySimpleProductCheckout() throws Exception {
	
	}
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
		gl.endReport();
		
	}

}
