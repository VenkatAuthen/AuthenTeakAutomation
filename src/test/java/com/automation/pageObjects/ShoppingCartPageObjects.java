package com.automation.pageObjects;

import static com.automation.pageObjects.ObjectLocator.*;

public enum ShoppingCartPageObjects implements PageObjects {
	
		//Shopping Cart Page
        title_shoppingCart("//h1[@class='cart-title page-title']",XPATH,"Shopping Cart - Page title"),
        shoppingCartProductName("product-name",CLASSNAME,"Shopping Cart - Product Name"),
        shoppingCartProductQuantityValue("//input[contains(@name,'qty')]",XPATH,"Shopping Cart - Selected Product Quantity"),
        shoppingCartSelectedProductTotal("//div[@class='cell cart-item-price']/span[2]",XPATH,"Shopping Cart - Selected Product total"),
        shoppingCartSubTotal("cartSubTotal",ID,"Shopping Cart Sub Total Value"),
        shoppingCartGrandTotalValue("//div[@class='cart-grand-total cart-totals-item']/span[2]",XPATH,"Shopping Cart - Grand Total Value"),
        shoppingCart_ShippingCost("//div[@class='cart-totals-item']/span[2]",XPATH,"Shopping Cart - Shipping Cart"),
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
        txtTaxdollar("//div[@title='Tax']/span/following::div[1]",XPATH,"Shopping Cart - Checkout Pop up - Tax value in $"),
        txtTotalCostdollar("//div[@title='Total']/span/following::div[1]",XPATH,"Shopping Cart - Checkout Pop up - Total cost in $"),

        // Checkout Pop up - Payment
        txt_CardNumber("ccn",ID,"Shopping Cart Page - Checkout Pop up - Payment section - Card Number text box"),
        txt_expiration("exp",ID,"Shopping Cart Page - Checkout Pop UP - Payment Section - Card Expiry text box"),
        txt_cvv("cvv",ID,"Shopping Cart Page - Checkout Pop UP - Payment Section - CVV text box"),
        txt_billingFirstName("billingFirstName",ID,"Shopping Cart Page - Checkout Pop Up - Payment Section - Billing First Name"),
        txt_billingLastName("billingLastName",ID,"Shopping Cart Page - Checkout Pop Up - Payment Section - Billing Last Name"),
        btn_pay("//button[@data-tid='checkout-button']",XPATH,"Shopping Cart Page - Checkout Pop UP - Payment Section - Button Pay"),
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
