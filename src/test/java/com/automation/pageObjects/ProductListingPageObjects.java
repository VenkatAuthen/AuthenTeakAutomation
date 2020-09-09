package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum ProductListingPageObjects implements PageObjects {
	
		//Home Page
        sideBarFilter("searchspring-sidebar",ID,"SideBar Search filter on Product Listing Page"),
        firstProduct("//*[@id=\"searchspring-content\"]/div[3]/ul/li[1]/figure/div[2]/div[1]/div/div[1]/div/a/img",XPATH,"First Product in teh Product Search Listing Page"),
        
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
	
	private ProductListingPageObjects (String strPropertyValue, ObjectLocator locatorType, String strObjName) {
		// TODO Auto-generated method stub
		this.strProperty = strPropertyValue;
   		this.locatorType = locatorType;
   		this.strObjName = strObjName;
		
	}

}
