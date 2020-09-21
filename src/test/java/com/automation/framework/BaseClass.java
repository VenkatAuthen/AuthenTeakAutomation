package com.automation.framework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import com.automation.framework.drivers.AppiumDriverFactory;
import com.automation.framework.drivers.MobileExecutionPlatform;
import com.automation.framework.drivers.WebDriverFactory;

public class BaseClass {

	public static WebDriver driver;
	public static Properties properties;
	public static Properties mobileProperties;
	Browser browser;
	public static Report report;
	public static DataTable dataTable;
	public com.aventstack.extentreports.ExtentReports extent;
	public com.aventstack.extentreports.ExtentTest test;

	static {
		final String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())
				.replaceAll(":", "-");
		final String reportFullPath = System.getProperty("user.dir") + "\\test-output\\HtmlReport\\ExtentHtml_"
				+ timestamp + ".html";
		System.setProperty("extent.reporter.avent.start", "false");
		System.setProperty("extent.reporter.bdd.start", "false");
		System.setProperty("extent.reporter.cards.start", "false");
		System.setProperty("extent.reporter.email.start", "false");
		System.setProperty("extent.reporter.html.start", "true");
		System.setProperty("extent.reporter.klov.start", "false");
		System.setProperty("extent.reporter.logger.start", "false");
		System.setProperty("extent.reporter.tabular.start", "false");
		System.setProperty("extent.reporter.html.config", "src/test/resources/html-config.xml");
		System.setProperty("extent.reporter.avent.out", "test-output/AventReport/");
		System.setProperty("extent.reporter.bdd.out", "test-output/BddReport/");
		System.setProperty("extent.reporter.cards.out", "test-output/CardsReport/");
		System.setProperty("extent.reporter.email.out", "test-output/EmailReport/ExtentEmail.html");
		System.setProperty("extent.reporter.html.out", reportFullPath);
		System.setProperty("extent.reporter.logger.out", "test-output/LoggerReport/");
		System.setProperty("extent.reporter.tabular.out", "test-output/TabularReport/");
	}

	// public ObjectRepo repo = new ObjectRepo();
	public BaseClass() {
		properties = Settings.getInstance();
		mobileProperties = Settings.getMobilePropertiesInstance();
	}

	public static void intialization() {

		String browserstr = properties.getProperty("Browser");
		System.out.println(browserstr);
		driver = WebDriverFactory.getWebDriver(Browser.CHROME);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("ImplicitWait")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("PageLoadTimeOut")),
				TimeUnit.SECONDS);

	}

	public static void initializeWebDriver(String executionMode, String toolName, String mobileExecutionPlatform,
			String mobileOsVersion, String deviceName, String browser, String browserVersion, String platform,
			String platformVersion, String dataTableName) {
		dataTable = new DataTable(System.getProperty("user.dir") + "\\src\\test\\resources\\Datatables",
				dataTableName);
		switch (executionMode.replace("\"", "")) {

		case "API":
			break;
			
		case "LinksValidation":
			break;
			
		case "LOCAL":
			WebDriver webDriver = WebDriverFactory.getWebDriver(Browser.browserValue(browser.replace("\"", "")));
			driver = webDriver;
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("ImplicitWait")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("PageLoadTimeOut")), TimeUnit.SECONDS);
			break;

		case "GRID":
			WebDriver remoteGridDriver = WebDriverFactory.getRemoteWebDriver(
					Browser.browserValue(browser.replace("\"", "")), browserVersion, Platform.fromString(platform),
					properties.getProperty("RemoteUrl"));
			driver = remoteGridDriver;

			driver.manage().window().maximize();
			break;

		case "MOBILE":
			WebDriver appiumDriver = AppiumDriverFactory.getAppiumDriver(
					MobileExecutionPlatform.fromString(mobileExecutionPlatform.replace("\"", "")),
					deviceName.replace("\"", ""), mobileOsVersion.replace("\"", ""),
					Boolean.parseBoolean(mobileProperties.getProperty("ShouldInstallApplication")),
					mobileProperties.getProperty("AppiumURL"));
			driver = appiumDriver;
			break;

		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}
	}

	public static void initializeWebDriver(String executionMode, String browser, String browserVersion, String platform,
			String platformVersion) {

		switch (executionMode.replace("\"", "")) {

		case "API":
			break;

		case "LOCAL":
			WebDriver webDriver = WebDriverFactory.getWebDriver(Browser.browserValue(browser.replace("\"", "")));
			driver = webDriver;
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("ImplicitWait")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("PageLoadTimeOut")), TimeUnit.SECONDS);
			break;

		case "GRID":
			WebDriver remoteGridDriver = WebDriverFactory.getRemoteWebDriver(
					Browser.browserValue(browser.replace("\"", "")), browserVersion, Platform.fromString(platform),
					properties.getProperty("RemoteUrl"));
			driver = remoteGridDriver;

			driver.manage().window().maximize();
			break;
		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}
	}

}