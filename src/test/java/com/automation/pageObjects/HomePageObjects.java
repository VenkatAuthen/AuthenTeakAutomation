package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum HomePageObjects implements PageObjects {
	
		//Home Page
        txtBox_SearchProduct("desktop_search_query",ID,"Product Search Text box"),
        closePopUp("//button[@alt='Close form']",XPATH,"Home Page - Covid message pop up"),
        link_MyAccount("//span[@class='button-text']",XPATH,"Home Page - My Account Link"),
        logoHomePage("//div[@class='h1 site-branding has-logo']/a/img",XPATH,"Home Page - Logo"),
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
	
	private HomePageObjects (String strPropertyValue, ObjectLocator locatorType, String strObjName) {
		// TODO Auto-generated method stub
		this.strProperty = strPropertyValue;
   		this.locatorType = locatorType;
   		this.strObjName = strObjName;
		
	}

}
