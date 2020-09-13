package com.automation.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.automation.componentgroups.*;
import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.framework.Status;
import com.automation.pageObjects.HomePageObjects;
import com.automation.pageObjects.MyAccountPageObjects;

public class MyAccountPage extends BaseClass {

	String testcaseName;

	public MyAccountPage(String testcaseName) {
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

	public void navigateToCreateAccount() {
		try {
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnCreateAccount), MyAccountPageObjects.btnCreateAccount.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void enterDetailsToCreateAccount() {
		try {
			String email = "autotestmail"+reusableFunctions.randomAlphaNumeric(8)+"@rmail.com";
			String password = dataTable.getData("General_Data", "Password");
			String fname = dataTable.getData("General_Data", "FirstName");
			String lname = dataTable.getData("General_Data", "LastName");
			String address = dataTable.getData("General_Data", "Address");
			String city = dataTable.getData("General_Data", "City");
			String state = dataTable.getData("General_Data", "State");
			String zip = dataTable.getData("General_Data", "Zip");
			String phone = dataTable.getData("General_Data", "Phone");
			
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxemail), email, MyAccountPageObjects.txtBoxemail.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxPassword), password, MyAccountPageObjects.txtBoxPassword.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxConfirmPassword), password, MyAccountPageObjects.txtBoxConfirmPassword.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxFirstName), fname, MyAccountPageObjects.txtBoxFirstName.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxLastName), lname, MyAccountPageObjects.txtBoxLastName.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxAddressLine1), address, MyAccountPageObjects.txtBoxAddressLine1.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxCity), city, MyAccountPageObjects.txtBoxCity.getObjectname());
			reusableFunctions.selectAnyElementByValue(getPageElement(MyAccountPageObjects.selectDrpDownState), state, MyAccountPageObjects.selectDrpDownState.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxZipCode), zip, MyAccountPageObjects.txtBoxZipCode.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxPhoneNumber), phone, MyAccountPageObjects.txtBoxPhoneNumber.getObjectname());
			
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnCreateNewAccount), MyAccountPageObjects.btnCreateNewAccount.getObjectname());
			
			reusableFunctions.isElementPresentContainsText(getPageElement(MyAccountPageObjects.titleAccountCreated), MyAccountPageObjects.titleAccountCreated.getObjectname(), "Account Created");
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.descAccountCreated), MyAccountPageObjects.descAccountCreated.getObjectname());
			
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnContinue), MyAccountPageObjects.btnContinue.getObjectname());
			reusableFunctions.verifyIfElementPresent(getPageElement(HomePageObjects.logoHomePage), HomePageObjects.logoHomePage.getObjectname());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
