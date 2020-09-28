package com.automation.pages;

import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.automation.componentgroups.*;
import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.framework.Status;
import com.automation.framework.TestExecutionException;
import com.automation.pageObjects.HomePageObjects;
import com.automation.pageObjects.MyAccountPageObjects;
import com.automation.pageObjects.ProductListingPageObjects;

public class HomePage extends BaseClass {

	String testcaseName;

	public HomePage(String testcaseName) {
		this.testcaseName = testcaseName;
		PageFactory.initElements(driver, this);
		dataTable.setCurrentRow(testcaseName, 1, 1);
	}

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper The {@link ScriptHelper} object passed from the
	 *                     {@link DriverScript}
	 */

	ReusableFunctions reusableFunctions = new ReusableFunctions(testcaseName);

	private WebElement getPageElement(HomePageObjects pageEnum) throws IOException {
		WebElement element;
		try {
			element = reusableFunctions.getElementByProperty(pageEnum.getProperty(),
					pageEnum.getLocatorType().toString(), true);
			if (element != null)
				System.out.println("Found the element: " + pageEnum.getObjectname());
			else
				System.out.println("Element Not Found: " + pageEnum.getObjectname());
			return element;
		} catch (Exception e) {
			Report.updateExtentStatus("Home Page - get page element",
					pageEnum.toString() + " object is not defined or found.", Status.FAIL);
			return null;
		}
	}

	private WebElement getPageElement(ProductListingPageObjects pageEnum) throws IOException {
		WebElement element;
		try {
			element = reusableFunctions.getElementByProperty(pageEnum.getProperty(),
					pageEnum.getLocatorType().toString(), true);
			if (element != null)
				System.out.println("Found the element: " + pageEnum.getObjectname());
			else
				System.out.println("Element Not Found: " + pageEnum.getObjectname());
			return element;
		} catch (Exception e) {
			Report.updateExtentStatus("Product Listing Page- get page element",
					pageEnum.toString() + " object is not defined or found.", Status.FAIL);
			return null;
		}
	}

	private WebElement getPageElement(MyAccountPageObjects pageEnum) throws IOException {
		WebElement element;
		try {
			element = reusableFunctions.getElementByProperty(pageEnum.getProperty(),
					pageEnum.getLocatorType().toString(), true);
			if (element != null)
				System.out.println("Found the element: " + pageEnum.getObjectname());
			else
				System.out.println("Element Not Found: " + pageEnum.getObjectname());
			return element;
		} catch (Exception e) {
			Report.updateExtentStatus("My Account Page - get page element",
					pageEnum.toString() + " object is not defined or found.", Status.FAIL);
			return null;
		}
	}
	public void invokeApplication() throws IOException {
		try {
		String WebsiteURL = dataTable.getData("General_Data", "URL");
		driver.get(WebsiteURL);
		}catch(Exception e) {
			Report.updateExtentStatus("",
					"Exception : Not able to Invoke Application:"+ ExceptionUtils.getFullStackTrace(e), Status.FAIL);
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}

	public void searchProduct() throws IOException, InterruptedException {
		try {
		String productName = dataTable.getData("General_Data", "Search_Product");
		System.out.println("Entered Product Name:"+productName);
		reusableFunctions.clickIfElementPresent(getPageElement(HomePageObjects.closePopUp), HomePageObjects.closePopUp.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(HomePageObjects.txtBox_SearchProduct), productName,
				HomePageObjects.txtBox_SearchProduct.getObjectname());
		reusableFunctions.hitEnterKey(getPageElement(HomePageObjects.txtBox_SearchProduct),
				HomePageObjects.txtBox_SearchProduct.getProperty());
		if (reusableFunctions.verifyIfElementIsPresent(getPageElement(ProductListingPageObjects.sideBarFilter),
				ProductListingPageObjects.sideBarFilter.getObjectname())) {
			Report.updateExtentStatus("Verify User navigated to Product Search Listing Page",
					"User is successfully navigated to Product Search Listing Page", Status.PASS);
		} else {
			Report.updateExtentStatus("Verify User navigated to Product Search Listing Page",
					"User is NOT navigated to Product Search Listing Page", Status.FAIL);
		}
		}catch(Exception e) {
			Report.updateExtentStatus("Verify User navigated to Product Search Listing Page",
					"Exception : User is NOT navigated to Product Search Listing Page:"+ ExceptionUtils.getFullStackTrace(e), Status.FAIL);
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	public void searchProductLoggedIn() throws IOException, InterruptedException {
		try {
		String productName = dataTable.getData("General_Data", "Search_Product");
		System.out.println("Entered Product Name:"+productName);
		reusableFunctions.clearAndEnterText(getPageElement(HomePageObjects.txtBox_SearchProduct), productName,
				HomePageObjects.txtBox_SearchProduct.getObjectname());
		reusableFunctions.hitEnterKey(getPageElement(HomePageObjects.txtBox_SearchProduct),
				HomePageObjects.txtBox_SearchProduct.getProperty());
		Thread.sleep(5000);
		if (reusableFunctions.verifyIfElementIsPresent(getPageElement(ProductListingPageObjects.sideBarFilter),
				ProductListingPageObjects.sideBarFilter.getObjectname())) {
			Report.updateExtentStatus("Verify User navigated to Product Search Listing Page",
					"User is successfully navigated to Product Search Listing Page", Status.PASS);
		} else {
			Report.updateExtentStatus("Verify User navigated to Product Search Listing Page",
					"User is NOT navigated to Product Search Listing Page", Status.FAIL);
		}
		}catch(Exception e) {
			Report.updateExtentStatus("Verify User navigated to Product Search Listing Page",
					"Exception : User is NOT navigated to Product Search Listing Page:"+ ExceptionUtils.getFullStackTrace(e), Status.FAIL);
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	public void navigateToMyAccountPage() throws IOException {
		try {
			reusableFunctions.clickIfElementPresent(getPageElement(HomePageObjects.link_MyAccount), HomePageObjects.link_MyAccount.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.txtLogin), MyAccountPageObjects.txtLogin.getObjectname());
			//reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.popUp), MyAccountPageObjects.popUp.getObjectname());
		
		}catch(Exception e) {
			e.printStackTrace();
			Report.updateExtentStatus("",
					"Exception : Not able to Navigate to My Account Page:"+ ExceptionUtils.getFullStackTrace(e), Status.FAIL);
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}

	public void navigateToMyAccountPageLoggedIn() throws IOException {
		try {
			reusableFunctions.clickIfElementPresent(getPageElement(HomePageObjects.link_MyAccount), HomePageObjects.link_MyAccount.getObjectname());
					
		
		}catch(Exception e) {
			e.printStackTrace();
			Report.updateExtentStatus("",
					"Exception : Not able to Navigate to My Account Page:"+ ExceptionUtils.getFullStackTrace(e), Status.FAIL);
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
}
