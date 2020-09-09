package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum ShoppingCartPageObjects implements PageObjects {
	
		//Home Page
        title_shoppingCart("cart-title page-title",CLASSNAME,"Shopping Cart - Page title"),
        shoppingCartProductQuantityValue("form-input quantity-input",CLASSNAME,"Shopping Cart - Selected Product Quantity"),
        shoppingCartSelectedProductTotal("//div[@class='cell cart-item-price']/span[2]",XPATH,"Shopping Cart - Selected Product total"),
        shoppingCartSubTotal("cartSubTotal",ID,"Shopping Cart Sub Total Value"),
        shoppingCartGrandTotalValue("cart-total-item grand-total",CLASSNAME,"Shopping Cart - Grand Total Value"),
        btn_checkOut("//div[@data-tid='bolt-checkout-button']",XPATH,"Shopping Cart - Checkout Button"),
        
        //Checkout Pop up - Shipping
        
        txtBox_email("//input[@id='email']",XPATH,"Shopping Cart - Checkout Pop Up - Email Text box"),
        txtBox_phone("//input[@id='phone']",XPATH,"Shopping Cart - Checkout Pop Up - Phone Text box"),
        txtBox_ShippingFirstName("shippingFirstName",ID,"Shopping Cart - Checkout Pop up - Shipping First Name"),
        txtBox_ShippingLastName("shippingLastName",ID,"Shopping Cart - Checkout Pop Up - Shipping Last Name"),
        txtBox_ShippingAddressLine1("shippingAddressLine1",ID,"Shopping Cart - Checkout Pop Up - Shipping Address Line Text Box"),
        txtBox_ShippingAddressZip("shippingZip",ID,"Shopping Cart - Checkout Out Pop Up - Shipping Address Zip Text Box"),
        btn_continue("//button[@data-tid='checkout-continue-button']",XPATH,"Shopping Cart - Checkout Out Pop Up - Continue Button"),
        
        // Checkout Pop Up - Delivery
        shippingOption_standarDelivery("//label[contains(text(),'Standard')]",XPATH,"Shopping Cart - Checkout Pop UP - Shipping Type - Standar Delivery"),
        txtTitlesubtotal("//div[@title='Subtotal' and @data-tid='name']/span",XPATH,"Shopping Cart - Checkout Pop - SubTotal Title"),
        txtPriceDollarSubTotal("//div[@title='Subtotal' and @data-tid='name']/span/following::div[1]",XPATH,"Shopping Cart - Checkout Pop Up - Subtotal Price in $"),
        

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
	
	private ShoppingCartPageObjects (String strPropertyValue, ObjectLocator locatorType, String strObjName) {
		// TODO Auto-generated method stub
		this.strProperty = strPropertyValue;
   		this.locatorType = locatorType;
   		this.strObjName = strObjName;
		
	}

}
