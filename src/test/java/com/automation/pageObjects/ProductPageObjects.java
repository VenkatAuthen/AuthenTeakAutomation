package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum ProductPageObjects implements PageObjects {
	
		
        title_customizeProduct("product__title product__title--2 product__title--customizePDP",CLASSNAME,"SideBar Search filter on Product Listing Page"),
        btn_selectCushion("//div[@class='product__swatch product__swatch--labelRight']/input",XPATH,"Product Page - Customize Product - Select button"),
        cushion("//input[@class='form-input swatch-radio']",XPATH,"Product Page - Select Cushion - Cushion"),
        btn_saveAndBack("drawer__saveBtn",CLASSNAME,"Product Page - Select Cushion - Save and Back Button"),
        txtBox_productQuantity("product-quantity form-input",CLASSNAME,"Product Page - Product Quantity Text Box"),
        btn_addToCart("//div[@class='product-quantity-toggle-wrapper']/following::button[1]",XPATH,"Product Page - Add to Cart Button"),
        title_productName_inMiniCart("mini-cart-item-title",CLASSNAME,"Product Page - Mini Cart - Selected Product Name"),
        miniCart_ProductQuantity("modal-cart__count-value",CLASSNAME,"Product Page - Mini Cart - Selected Quantity"),
        btn_viewCart("//div[@class='modal-cart__summary']/a",XPATH,"Product Page - Mini Cart - View Cart Button"),
        
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
	
	private ProductPageObjects (String strPropertyValue, ObjectLocator locatorType, String strObjName) {
		// TODO Auto-generated method stub
		this.strProperty = strPropertyValue;
   		this.locatorType = locatorType;
   		this.strObjName = strObjName;
		
	}

}
