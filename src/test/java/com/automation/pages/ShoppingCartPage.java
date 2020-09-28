package com.automation.pages;

import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.automation.componentgroups.*;
import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.framework.Status;
import com.automation.framework.TestExecutionException;
import com.automation.pageObjects.ShoppingCartPageObjects;

public class ShoppingCartPage extends BaseClass {

	String testcaseName;

	public ShoppingCartPage(String testcaseName) {
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

	private WebElement getPageElement(ShoppingCartPageObjects pageEnum) throws IOException {
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
			Report.updateExtentStatus("Shopping Cart Page - get page element",	
					pageEnum.toString() + " object is not defined or found.", Status.FAIL);
			return null;
		}
	}

	public void verifyShoppingCartPage() throws IOException {
	try {
		String productName = dataTable.getData("General_Data", "Product_Name");
		String ProductQuantity = dataTable.getData("General_Data", "Quantity");
		String totalcost = dataTable.getData("General_Data", "Total_Cost");
		totalcost = StringUtils.remove(totalcost, ",");
		reusableFunctions.verifyIfElementIsPresent(getPageElement(ShoppingCartPageObjects.title_shoppingCart), ShoppingCartPageObjects.title_shoppingCart.getObjectname());
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.shoppingCartProductName), ShoppingCartPageObjects.shoppingCartProductName.getObjectname(), productName);
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.shoppingCartProductQuantityValue), ShoppingCartPageObjects.shoppingCartProductQuantityValue.getObjectname(), ProductQuantity);
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.shoppingCartSelectedProductTotal), ShoppingCartPageObjects.shoppingCartSelectedProductTotal.getObjectname(), totalcost);
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.shoppingCartSubTotal), ShoppingCartPageObjects.shoppingCartSubTotal.getObjectname(), totalcost);
		reusableFunctions.scrollIntoView(getPageElement(ShoppingCartPageObjects.shoppingCartSelectedProductTotal));
		String shippingCost = reusableFunctions.getTextFromElement(getPageElement(ShoppingCartPageObjects.shoppingCart_ShippingCost));
		double grandTotal = Double.parseDouble(StringUtils.substring(totalcost, 1))+Double.parseDouble(StringUtils.substring(shippingCost, 1));
		dataTable.putData("General_Data", "Grand_Total", reusableFunctions.returnCurrencyFormat(grandTotal));
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.shoppingCartGrandTotalValue), ShoppingCartPageObjects.shoppingCartGrandTotalValue.getObjectname(), reusableFunctions.returnCurrencyFormat(grandTotal));
		//reusableFunctions.scrollToTopOfPage();
		reusableFunctions.clickIfElementPresent(getPageElement(ShoppingCartPageObjects.btn_checkOut), ShoppingCartPageObjects.btn_checkOut.getObjectname());
	}catch(Exception e) {
		e.printStackTrace();
		throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
	}
	}
	
	public void enterCustomerDetails() throws IOException, InterruptedException {
		try {
		String email = dataTable.getData("General_Data", "Email");
		String phone = dataTable.getData("General_Data", "Phone");
		String fname = dataTable.getData("General_Data", "FirstName");
		String lname = dataTable.getData("General_Data", "LastName");
		String address = dataTable.getData("General_Data", "Address");
		String zip = dataTable.getData("General_Data", "Zip");
		driver.switchTo().frame("bolt-checkout-frame");
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txtBox_email), email, ShoppingCartPageObjects.txtBox_email.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txtBox_phone), phone, ShoppingCartPageObjects.txtBox_phone.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txtBox_ShippingFirstName), fname, ShoppingCartPageObjects.txtBox_ShippingFirstName.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txtBox_ShippingLastName), lname, ShoppingCartPageObjects.txtBox_ShippingLastName.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txtBox_ShippingAddressLine1), address, ShoppingCartPageObjects.txtBox_ShippingAddressLine1.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txtBox_ShippingAddressZip), zip, ShoppingCartPageObjects.txtBox_ShippingAddressZip.getObjectname());
		reusableFunctions.clickIfElementPresent(getPageElement(ShoppingCartPageObjects.btn_continue), ShoppingCartPageObjects.btn_continue.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void chooseStandarShipping() throws IOException {
		try {
		reusableFunctions.clickIfElementPresent(getPageElement(ShoppingCartPageObjects.shippingOption_standarDelivery), ShoppingCartPageObjects.shippingOption_standarDelivery.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
		}
	
	public void validateOrderSummary() throws IOException {
		try {
		String subTotal = dataTable.getData("General_Data", "Total_Cost");
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.txtPriceDollarSubTotal), ShoppingCartPageObjects.txtPriceDollarSubTotal.getObjectname(), subTotal);
		String tax = reusableFunctions.getTextFromElement(getPageElement(ShoppingCartPageObjects.txtTaxdollar));
		double totalCost = (Double.parseDouble(StringUtils.remove(StringUtils.substring(subTotal, 1),","))+Double.parseDouble(StringUtils.substring(tax, 1)));
		reusableFunctions.isElementPresentContainsText(getPageElement(ShoppingCartPageObjects.txtTotalCostdollar), ShoppingCartPageObjects.txtTotalCostdollar.getObjectname(), reusableFunctions.returnCurrencyFormat(totalCost));
		reusableFunctions.clickIfElementPresent(getPageElement(ShoppingCartPageObjects.btn_continue), ShoppingCartPageObjects.btn_continue.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	public void enterPaymentDetails() throws IOException, InterruptedException {
		try {
		String cardNumber = dataTable.getData("General_Data", "Card_Number");
		String exp = dataTable.getData("General_Data", "Expiration");
		String cvv = dataTable.getData("General_Data", "CVV");
		String fname = dataTable.getData("General_Data", "FirstName");
		String lname = dataTable.getData("General_Data", "LastName");
		
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txt_CardNumber), cardNumber, ShoppingCartPageObjects.txt_CardNumber.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txt_expiration), exp, ShoppingCartPageObjects.txt_expiration.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txt_cvv), cvv, ShoppingCartPageObjects.txt_cvv.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txt_billingFirstName), fname, ShoppingCartPageObjects.txt_billingFirstName.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ShoppingCartPageObjects.txt_billingLastName), lname, ShoppingCartPageObjects.txt_billingLastName.getObjectname());
		reusableFunctions.verifyIfElementIsPresent(getPageElement(ShoppingCartPageObjects.btn_pay), ShoppingCartPageObjects.btn_pay.getObjectname());
		driver.switchTo().parentFrame();
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
}
