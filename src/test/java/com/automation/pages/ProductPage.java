package com.automation.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.automation.componentgroups.*;
import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.framework.Status;
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
			Report.updateExtentStatus("Registration Page - get page element",
					pageEnum.toString() + " object is not defined or found.", Status.FAIL);
			return null;
		}
	}
 
	public void customizeSimpleProduct() {
		
	}

}
