package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum MyAccountPageObjects implements PageObjects {
	
		//My Account Page
        btnCreateAccount("//a[@class='button account-button-primary']",XPATH,"My Account Page - Create Account Button"),
        popUp("//button[@alt='Close form']",XPATH,"My Account Page - Close pop up"),
        lnkLogOut("//a[@class='top-bar-button']",XPATH,"My Account Page - Logout Link"),
        msgLogoutSuccessful("//div[@class='alert alert-success']/div",XPATH,"My Account Page - Logout Successful message"),
        lnkHome("HOME",LINKTEXT,"Logged Out Page - Home Link"),
        //Create Account
        titleCreateAccount("//div[@class='h1 account-heading']",XPATH,"Create Account Page - Tile Create Account"),
        txtBoxemail("input-FormField_1",ID,"My Account - Create Account Page - Email Text Field"),
        txtBoxPassword("password-FormField_2",ID,"My Account - Create Account Page - Password Text Field"),
        txtBoxConfirmPassword("password-FormField_3",ID,"My Account - Create Account Page - Confirm Password Text Field"),
        radioBtnProfessionalTraderYes("//input[@value='Yes' and @id='radio-FormField[1][26]-']",XPATH,"My Account - Create Account Page - Are you a Professional Trader Radio Button - YES"),
        radioBtnProfessionalTraderNo("//input[@value='No' and @id='radio-FormField[1][26]-']",XPATH,"My Account - Create Account Page - Are you a Professional Trader Radio Button - NO"),
        radioBtnTradeApplicationYes("//input[@value='Yes' and @id='radio-FormField[1][27]-']",XPATH,"My Account - Create Account Page - Have you completed a Trade Application Radio Button - YES"),
        radioBtnTradeApplicationNo("//input[@value='No' and @id='radio-FormField[1][27]-']",XPATH,"My Account - Create Account Page - Have you completed a Trade Application Radio Button - NO"),
        txtBoxFirstName("input-FormField_4",ID,"My Account - Create Account - First Name text Box"),
        txtBoxLastName("input-FormField_5",ID,"My Account - Create Account - Last Name Text Box"),
        txtBoxAddressLine1("input-FormField_8",ID,"My Account - Create Account - Address Line 1 Text Box"),
        txtBoxCity("input-FormField_10",ID,"My Account - Create Account - City Text Box"),
        selectDrpDownState("select-FormField_12",ID,"My Account - Create Account - Select State Drop Down"),
        txtBoxZipCode("input-FormField_13",ID,"My Account - Create Account - Zip Code"),
        txtBoxPhoneNumber("input-FormField_7",ID,"My Account - Create Account - Phone Number Text Field"),
        btnCreateNewAccount("//button[@class='button account-button-primary']",XPATH,"My Account - Create Account Button"),
        
        // Account Created
        
        titleAccountCreated("//div[@class='h1 account-heading']",XPATH,"My Account - Account Created Title"),
        descAccountCreated("//div[@class='account-content']/p",XPATH,"My Account - Account Created with email address"),
        btnContinue("//a[@class='button account-button-primary']",XPATH,"My Account - Account Created Page - Continue Button"),
        
        //Login Page
        txtLogin("//h2[@class='account-sub-heading']",XPATH,"Login Page - Title - Log in"),
        txtBoxLoginUsernameEmail("input-login_email",ID,"Login Page - Username - Email Text Box"),
        txtBoxLoginPassword("password-login_pass",ID,"Login Page - Password Text Box "),
        btnLogin("//input[@value='Log In']",XPATH,"Login Page - Login Button"),
       
        // Your Account page
        lnkNavOrders("//ul[@class='account-nav-list']/li[1]/a",XPATH,"Your Account Page - Nav Link - Orders"),
        headingOrders("//div[@class='h1 account-heading']",XPATH,"Your Account Page - Heading - Orders"),
        ordersblock("//div[@class='account-items']",XPATH,"Your Account Page - Orders Block"),
        orderNumber("//h1[@class='account-item-title']/a",XPATH,"Your Account Page - Order Number"),
        noOfItems("//p[@class='account-item-description']",XPATH,"Your Account Page - Number of Items"),
        txtPlaced("//h2[@class='account-item-detail-title']",XPATH,"Your Account Page - Text - Placed On"),
        txtOrderDate("//span[@class='account-item-detail-value']",XPATH,"Your Account Page - Text - Order Date"),
        
        orderStatus("//h2[@class='account-status']",XPATH,"Your Account Page - Order Status"),
        lnkViewStatusDetails("//div[@class='account-item-badge']/p/a",XPATH,"Your Account Page - Order - View Status Details"),
        
        lnkNavAdresess("//ul[@class='account-nav-list']/li[3]/a",XPATH,"Your Account Page - Nav Link - Addresses"),
        lnkNavRecentlyViewed("RECENTLY VIEWED",LINKTEXT,"Your Account Page - Nav Link - Recently Viewed"),
        lnkNavAccountSettings("//ul[@class='account-nav-list']/li[5]/a",XPATH,"Your Account Page - Nav Link - Account Settings"),
        
        //Address
        btnAddressEdit("//div[@class='account-item-actions']/form/a",XPATH,"Your Account - Addresses - Edit Button"),
        btnAddressDelete("(//div[@class='account-item-actions']/form/button)[1]",XPATH,"Your Account Addresses - Delete Button"),
        txtAddressNameHeading("//h1[@class='account-item-title account-item-title-large']",XPATH,"Your Account Addresses - Name in Address field"),
        txtAddress("//div[@class='account-item-details']",XPATH,"Your Account - Address section - Address"),
        lnkNewAddress("New Address",LINKTEXT,"Your Account - Address Seciton - New Address Link"),
        
        //New Address
        txtNewAddressHeading("//div[@class='account-header']/div[1]",XPATH,"Your Account - New Address Heading"),
        txtBoxNewAddressFirstName("input-FormField_4",ID,"Your Account - New Adresses - First Name Text Box"),
        txtBoxNewAddressLastName("input-FormField_5",ID,"Your Account - New Adresses Last Name Text Box "),
        txtBoxNewAddressAddressline1("input-FormField_8",ID,"Your Account - New Addresses - Address Line 1"),
        txtBoxNewAddressCity("input-FormField_10",ID,"Your Account - New Addresses - City Text Box"),
        drpDownNewAddressState("select-FormField_12",ID,"Your Account - New Addresses - State Drop Down"),
        txtBoxNewAddressZip("input-FormField_13",ID,"Your Account - New Addresses - Zip Text Box"),
        txtBoxNewAddressPhoneNumber("input-FormField_7",ID,"Your Account - New Addresses - Phone Number Text Box"),
        
        btnSaveAddress("//input[@value ='Save Address']",XPATH,"Your Account - New Address - Save Address Button"),
        
        // Recently View
        imgRecentlyViewedItem("//figure",XPATH,"Your Account - Recently Viewed - Item"),
        
        //Account Settings
        titleAccountSettings("//div[@class='account-header']/div[1]",XPATH,"My Account Page - Account Settings page Heading"),
        txtBoxAccountSettingsPhoneNumber("//input[@name='account_phone']",XPATH,"Your Account - Account Settings - Phone Number Text Box"),
        txtBoxAccountSettingsEmail("input-FormField_1",ID,"Your Account - Account Settings - Email Text Box"),
        txtBoxAccountSettingsPassword("password-FormField_2",ID,"Your Account - Account Settings - Password text box"),
        txtBoxAccountSettingConfirmPassword("password-FormField_3",ID,"Your Account - Account Settings - Confirm Password text box"),
        txtBoxAccountSettingCurrentPassword("password-FormField_24",ID,"Your Account - Account Settings - Current Password text box"),
        btnUpdateDetails("//button[@class='button account-button-primary']",XPATH,"Your Account - Account Settings - Update Details Button"),
        msgAccountDetailsUpdated("//div[@class='alert-message']",XPATH,"Your Account - Message - Account Details Updated"),
        
        ;
		
	String strProperty = "";
   	ObjectLocator locatorType = null;
   	String strObjName = "";
   	
	@Override
	public String getProperty() {
		// TODO Auto-generated method stub
		return strProperty;
	}

	@Override
	public ObjectLocator getLocatorType() {
		// TODO Auto-generated method stub
		return locatorType;
	}

	@Override
	public String getObjectname() {
		// TODO Auto-generated method stub
		return strObjName;
	}
	
	private MyAccountPageObjects (String strPropertyValue, ObjectLocator locatorType, String strObjName) {
		// TODO Auto-generated method stub
		this.strProperty = strPropertyValue;
   		this.locatorType = locatorType;
   		this.strObjName = strObjName;
		
	}

}
