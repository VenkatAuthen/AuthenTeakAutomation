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
import com.automation.pageObjects.ProductPageObjects;

public class ProductPage extends BaseClass {

	String testcaseName;

	public ProductPage(String testcaseName) {
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



	private WebElement getPageElement(ProductPageObjects pageEnum) throws IOException {
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
			Report.updateExtentStatus("Product Page - get page element",
					pageEnum.toString() + " object is not defined or found.", Status.FAIL);
			return null;
		}
	}
 
	public void customizeSimpleProductAndAddToCart() throws IOException {
		try {
		String quantity = dataTable.getData("General_Data", "Quantity");
		String productName =reusableFunctions.getTextFromElement(getPageElement(ProductPageObjects.title_productName));
		dataTable.putData("General_Data", "Product_Name", productName);
		String productCost = reusableFunctions.getTextFromElement(getPageElement(ProductPageObjects.productCost));
		dataTable.putData("General_Data","Product_Cost",productCost);
		reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_selectCushion), ProductPageObjects.btn_selectCushion.getObjectname());
		reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.cushion), ProductPageObjects.cushion.getObjectname());
		reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_saveAndBack), ProductPageObjects.btn_saveAndBack.getObjectname());
		reusableFunctions.clearAndEnterText(getPageElement(ProductPageObjects.txtBox_productQuantity), quantity, ProductPageObjects.txtBox_productQuantity.getObjectname());
		reusableFunctions.scrollIntoView(getPageElement(ProductPageObjects.lnkLearnMoreLoan));
		reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_addToCart), ProductPageObjects.btn_addToCart.getObjectname());
		reusableFunctions.isElementPresentContainsText(getPageElement(ProductPageObjects.addedToCartTitle), ProductPageObjects.addedToCartTitle.getObjectname(), "Added to your Cart");
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
		}

	public void validateSelectionsMiniCartAndViewCart() throws IOException {
		try {
			String productName = dataTable.getData("General_Data", "Product_Name");
			String quantity = dataTable.getData("General_Data", "Quantity");
			String productCost = dataTable.getData("General_Data", "Product_Cost");
			String costwithoutDollarSign =StringUtils.remove(StringUtils.substring(productCost, 1),",");
			double finalproductcost = (Double.parseDouble(costwithoutDollarSign)*Integer.parseInt(quantity));
			dataTable.putData("General_Data", "Total_Cost", reusableFunctions.returnCurrencyFormat(finalproductcost));
			reusableFunctions.isElementPresentContainsText(getPageElement(ProductPageObjects.title_productName_inMiniCart), ProductPageObjects.title_productName_inMiniCart.getObjectname(), productName);
			reusableFunctions.isElementPresentContainsText(getPageElement(ProductPageObjects.miniCart_ProductQuantity), ProductPageObjects.miniCart_ProductQuantity.getObjectname(), quantity);
			reusableFunctions.isElementPresentContainsText(getPageElement(ProductPageObjects.miniCart_SubTotal), ProductPageObjects.miniCart_SubTotal.getObjectname(),reusableFunctions.returnCurrencyFormat(finalproductcost) );
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_viewCart), ProductPageObjects.btn_viewCart.getObjectname());
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
			}
	}
	
	public void customizeCushionwithSwatches() {
		try {
			String quantity = dataTable.getData("General_Data", "Quantity");
			String productName =reusableFunctions.getTextFromElement(getPageElement(ProductPageObjects.title_productName));
			dataTable.putData("General_Data", "Product_Name", productName);
			String defaultProductCost = reusableFunctions.getTextFromElement(getPageElement(ProductPageObjects.productCost));
			double defautcost = Double.parseDouble(StringUtils.remove(StringUtils.substring(defaultProductCost, 1),","));
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_selectCushion), ProductPageObjects.btn_selectCushion.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_filterSwatch), ProductPageObjects.btn_filterSwatch.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.brandFilterSecondOption), ProductPageObjects.brandFilterSecondOption.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.gradeFilterGradeA), ProductPageObjects.gradeFilterGradeA.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.cushion), ProductPageObjects.cushion.getObjectname());
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_saveAndBack), ProductPageObjects.btn_saveAndBack.getObjectname());
			reusableFunctions.scrollIntoView(getPageElement(ProductPageObjects.title_productName));
			reusableFunctions.selectAnyElement(getPageElement(ProductPageObjects.selectProtectionCoverDropDown), 2, ProductPageObjects.selectProtectionCoverDropDown.getObjectname());
			String updatedProductCost = reusableFunctions.getTextFromElement(getPageElement(ProductPageObjects.productCost));
			double updatedcost = Double.parseDouble(StringUtils.remove(StringUtils.substring(updatedProductCost, 1),","));
			if(updatedcost==(108+defautcost)) {
				Report.updateExtentStatus("", "The cost is updated based on the selection of Protection Cover", Status.PASS);
			}else {
				Report.updateExtentStatus("", "The cost is NOT updated based on the selection of Protection Cover", Status.FAIL);
			}
			dataTable.putData("General_Data","Product_Cost",updatedProductCost);
			reusableFunctions.clearAndEnterText(getPageElement(ProductPageObjects.txtBox_productQuantity), quantity, ProductPageObjects.txtBox_productQuantity.getObjectname());
			reusableFunctions.scrollIntoView(getPageElement(ProductPageObjects.btn_selectCushion));
			reusableFunctions.clickIfElementPresent(getPageElement(ProductPageObjects.btn_addToCart), ProductPageObjects.btn_addToCart.getObjectname());
			reusableFunctions.isElementPresentContainsText(getPageElement(ProductPageObjects.addedToCartTitle), ProductPageObjects.addedToCartTitle.getObjectname(), "Added to your Cart");
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new TestExecutionException("Exception: "+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
		}
	}
	
}
