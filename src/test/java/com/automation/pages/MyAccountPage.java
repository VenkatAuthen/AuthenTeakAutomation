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
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.titleCreateAccount), MyAccountPageObjects.titleCreateAccount.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
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
			reusableFunctions.scrollIntoView(getPageElement(MyAccountPageObjects.txtBoxCity));
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnCreateNewAccount), MyAccountPageObjects.btnCreateNewAccount.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.descAccountCreated), MyAccountPageObjects.descAccountCreated.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnContinue), MyAccountPageObjects.btnContinue.getObjectname());
			reusableFunctions.verifyIfElementPresent(getPageElement(HomePageObjects.logoHomePage), HomePageObjects.logoHomePage.getObjectname());
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void loginToAccount() {
		try {
			String userName = dataTable.getData("General_Data", "UserName");
			String password = dataTable.getData("General_Data", "Password");
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxLoginUsernameEmail),userName, MyAccountPageObjects.txtBoxLoginUsernameEmail.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxLoginPassword),password, MyAccountPageObjects.txtBoxLoginPassword.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnLogin), MyAccountPageObjects.btnLogin.getObjectname());
			reusableFunctions.isElementPresentVerification(getPageElement(MyAccountPageObjects.headingOrders), MyAccountPageObjects.headingOrders.getObjectname());
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void verifyOrdersInYourAccount() {
		try {
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.headingOrders), MyAccountPageObjects.headingOrders.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.ordersblock), MyAccountPageObjects.ordersblock.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.orderNumber), MyAccountPageObjects.orderNumber.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.noOfItems), MyAccountPageObjects.noOfItems.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.txtPlaced), MyAccountPageObjects.txtPlaced.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.txtOrderDate), MyAccountPageObjects.txtOrderDate.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.orderStatus), MyAccountPageObjects.orderStatus.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.lnkViewStatusDetails), MyAccountPageObjects.lnkViewStatusDetails.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
		
	}
	
	public void enterNewAddress() {
		try {
			String firstName = dataTable.getData("General_Data", "FirstName");
			String lastName = dataTable.getData("General_Data", "LastName");
			String addr = dataTable.getData("General_Data", "Address");
			String city = dataTable.getData("General_Data", "City");
			String state = dataTable.getData("General_Data", "State");
			String zip = dataTable.getData("General_Data", "Zip");
			String phone = dataTable.getData("General_Data", "Phone");
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.lnkNavAdresess), MyAccountPageObjects.lnkNavAdresess.getObjectname());			
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.lnkNewAddress), MyAccountPageObjects.lnkNewAddress.getObjectname());
			Thread.sleep(5000);
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.txtNewAddressHeading), MyAccountPageObjects.txtNewAddressHeading.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxNewAddressFirstName),firstName, MyAccountPageObjects.txtBoxNewAddressFirstName.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxNewAddressLastName),lastName, MyAccountPageObjects.txtBoxNewAddressLastName.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxNewAddressAddressline1),addr, MyAccountPageObjects.txtBoxNewAddressAddressline1.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxNewAddressCity),city, MyAccountPageObjects.txtBoxNewAddressCity.getObjectname());
			reusableFunctions.selectAnyElementByValue(getPageElement(MyAccountPageObjects.drpDownNewAddressState),state, MyAccountPageObjects.drpDownNewAddressState.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxNewAddressZip),zip, MyAccountPageObjects.txtBoxNewAddressZip.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxNewAddressPhoneNumber),phone, MyAccountPageObjects.txtBoxNewAddressPhoneNumber.getObjectname());
			reusableFunctions.scrollIntoView(getPageElement(MyAccountPageObjects.txtBoxNewAddressAddressline1));
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnSaveAddress), MyAccountPageObjects.btnSaveAddress.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void deleteAddress() {
		try {
			Thread.sleep(5000);
			reusableFunctions.clickIfElementPresentJavaScript(getPageElement(MyAccountPageObjects.btnAddressDelete), MyAccountPageObjects.btnAddressDelete.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}

	public void validateAccountSettings() {
		try {
			String phone = dataTable.getData("General_Data", "Phone");
			String email = dataTable.getData("General_Data", "Email");
			String password = dataTable.getData("General_Data", "Password");
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.lnkNavAccountSettings), MyAccountPageObjects.lnkNavAccountSettings.getObjectname());
			Thread.sleep(5000);
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.titleAccountSettings), MyAccountPageObjects.titleAccountSettings.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxAccountSettingsPhoneNumber),phone, MyAccountPageObjects.txtBoxAccountSettingsPhoneNumber.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxAccountSettingsEmail),email, MyAccountPageObjects.txtBoxAccountSettingsEmail.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxAccountSettingsPassword),password, MyAccountPageObjects.txtBoxAccountSettingsPassword.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxAccountSettingConfirmPassword),password, MyAccountPageObjects.txtBoxAccountSettingConfirmPassword.getObjectname());
			reusableFunctions.clearAndEnterText(getPageElement(MyAccountPageObjects.txtBoxAccountSettingCurrentPassword),password, MyAccountPageObjects.txtBoxAccountSettingCurrentPassword.getObjectname());
			reusableFunctions.scrollIntoView(getPageElement(MyAccountPageObjects.txtBoxAccountSettingsPassword));
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.btnUpdateDetails), MyAccountPageObjects.btnUpdateDetails.getObjectname());
			reusableFunctions.isElementPresentContainsText(getPageElement(MyAccountPageObjects.msgAccountDetailsUpdated), MyAccountPageObjects.msgAccountDetailsUpdated.getObjectname(), "Your account details have been updated.");
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void validateRecentlyViewed() {
		try {
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.lnkNavRecentlyViewed), MyAccountPageObjects.lnkNavRecentlyViewed.getObjectname());
			reusableFunctions.verifyIfElementIsPresent(getPageElement(MyAccountPageObjects.imgRecentlyViewedItem), MyAccountPageObjects.imgRecentlyViewedItem.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void logOut() {
		try {
			reusableFunctions.clickIfElementPresent(getPageElement(MyAccountPageObjects.lnkLogOut), MyAccountPageObjects.lnkLogOut.getObjectname());
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
}
