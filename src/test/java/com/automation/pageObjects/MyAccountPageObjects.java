package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum MyAccountPageObjects implements PageObjects {
	
		//My Account Page
        btnCreateAccount("//a[@class='button account-button-primary']",XPATH,"My Account Page - Create Account Button"),
        
        //Create Account
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
