package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum ProductPageObjects implements PageObjects {
	
		title_productName("//h1[@class='product-title mobileHide']",XPATH,"Product Page - Product Name"),
        title_customizeProduct("//h2[@class='product__title product__title--2 product__title--customizePDP']",XPATH,"Product Page - title text - Customize your product"),
        btn_selectCushion("//label[@class='product__swatchLabel']",XPATH,"Product Page - Customize Product - Select button"),
        cushion("//div[@class='form-field-control drawer__controls--list']/label[1]",XPATH,"Product Page - Select Cushion - Cushion"),
        btn_saveAndBack("drawer__saveBtn",CLASSNAME,"Product Page - Select Cushion - Save and Back Button"),
        txtBox_productQuantity("//input[@name='qty[]']",XPATH,"Product Page - Product Quantity Text Box"),
        btn_addToCart("//div[@class='product-quantity-toggle-wrapper']/following::button[1]",XPATH,"Product Page - Add to Cart Button"),
        title_productName_inMiniCart("mini-cart-item-title",CLASSNAME,"Product Page - Mini Cart - Selected Product Name"),
        miniCart_ProductQuantity("modal-cart__count-value",CLASSNAME,"Product Page - Mini Cart - Selected Quantity"),
        btn_viewCart("//div[@class='modal-cart__summary']/a",XPATH,"Product Page - Mini Cart - View Cart Button"),
        addedToCartTitle("modal-cart__title",CLASSNAME,"Product Page - Mini Cart Title - Added To Cart"),
        productCost("price-value",CLASSNAME,"Product Page - Product Cost"),
		miniCart_SubTotal("modal-cart__subtotal-value",CLASSNAME,"ProductPage - Mini Cart - Sub Total value"),
		btn_filterSwatch("//button[@class='drawer__displayFiltersBtn']",XPATH,"Product Page - Cushion - Filter Cushion button"),
		brandFilterSecondOption("//ul[@id='filterByBrand']/li[1]/label/input",XPATH,"Product Page - Cushion - Filter cushion - 2nd checkbox - Sunbrella Rain option "),
		gradeFilterGradeA("//ul[@id='filterByGrade']/li[1]/label/input",XPATH,"Product Page - Cushion Selection Filter - Grade Checkbox"),
		selectProtectionCoverDropDown("//label[@class='selectBox__label form-label']/select[1]",XPATH,"Product Page - Optional 	protective Cover"),
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
