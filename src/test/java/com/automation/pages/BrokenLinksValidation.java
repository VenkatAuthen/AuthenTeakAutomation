package com.automation.pages;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.automation.framework.BaseClass;
import com.automation.framework.Report;
import com.automation.framework.Status;
import com.automation.framework.drivers.WebDriverUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class BrokenLinksValidation extends BaseClass {

	WebDriverUtil objWebd = new WebDriverUtil(driver);
	@SuppressWarnings("unused")
	private static final String GENERAL_DATA = "General_Data";
	String testcase;

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper The {@link testCaseName} object passed from the
	 *                     {@link DriverScript}
	 */
	public BrokenLinksValidation(String testcaseName) {
		this.testcase = testcaseName;
		PageFactory.initElements(driver, this);
		dataTable.setCurrentRow(testcaseName, 1, 1);
	}

	public void verifyBrokenLinks() {

		try {

			// Set file path and file name to set for input excel
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Datatables";
			String fileName = "BrokenLinksUrl.xlsx";

			File file = new File(filePath + "\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook UrltestWorkbook = null;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {
				UrltestWorkbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				UrltestWorkbook = new HSSFWorkbook(inputStream);
			}

			// Find no of entries in the first sheet from input excel
			Sheet UrltestSheet = UrltestWorkbook.getSheet("Sheet1");
			int rowCount = UrltestSheet.getLastRowNum() - UrltestSheet.getFirstRowNum();

			// Create new excel for storing output
			String dateFormatString = "dd-MMM-yyyy hh:mm:ss a";
			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
			Calendar calendar = Calendar.getInstance();
			String filename = System.getProperty("user.dir") + "\\Results\\Output_"
					+ dateFormat.format(calendar.getTime()).replace(" ", "_").replace(":", "-") + ".xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(filename);

			for (int i = 1; i < rowCount + 1; i++) {

				// Get first row data from the input excel
				Row row = UrltestSheet.getRow(i);
				System.out.println("--------------------------------------------------------------------");

				if (!row.getCell(0).getStringCellValue().isEmpty()) {

					System.out.print(row.getCell(0).getStringCellValue() + "|| ");

					// Create first sheet in the output excel
					XSSFSheet sheet = workbook.createSheet("Sheet" + i);

					// Set first row value in output excel as the URL
					XSSFRow sheetTitle = sheet.createRow((short) 0);
					sheetTitle.createCell(0).setCellValue(row.getCell(0).getStringCellValue());

					// Create column headers in the output excel
					Row rowhead = sheet.createRow((short) 1);
					rowhead.createCell(0).setCellValue("No.");
					rowhead.createCell(1).setCellValue("URL");
					rowhead.createCell(2).setCellValue("Response Code");
					rowhead.createCell(3).setCellValue("Response Message");
					System.out.println(row.getCell(0).getStringCellValue());
					// Open the URL in web-browser and find all the links within that page
					driver.get(row.getCell(0).getStringCellValue());
					List<WebElement> links = driver.findElements(By.tagName("a"));
					System.out.println("Total links are " + links.size());
					System.out.println("--------------------------------------------------------------------");

					Report.updateExtentStatus("", "URL name - " + row.getCell(0).getStringCellValue()
							+ " (Total links - " + links.size() + ")", Status.DONE);

					for (int k = 0; k < links.size(); k++) {
						XSSFRow excelRow = sheet.createRow((short) (k + 2));

						// Get href attribute value from the link
						WebElement ele = links.get(k);
						String url = ele.getAttribute("href");
						if (url != null) {
							if (url.contains("https:")) {
								excelRow.createCell(0).setCellValue(k + 1);
								verifyLinkActivehttps(url, excelRow);
							} else if (url.contains("http:")) {
								excelRow.createCell(0).setCellValue(k + 1);
								verifyLinkActivehttp(url, excelRow);
							} else {
								System.out.println(url + " - Not correct form URL.");
								Report.updateExtentStatus(url, url + ":Not correct form URL.", Status.FAIL);
								excelRow.createCell(0).setCellValue(k + 1);
								excelRow.createCell(1).setCellValue(url);
								excelRow.createCell(2).setCellValue("Invalid");
								excelRow.createCell(3).setCellValue("Not correct form URL.");
							}
						} else {
							System.out.println(url + " No URL present..");
							Report.updateExtentStatus(url, url + ":No URL present.", Status.FAIL);
							excelRow.createCell(0).setCellValue(k + 1);
							excelRow.createCell(1).setCellValue(url);
							excelRow.createCell(2).setCellValue("Invalid");
							excelRow.createCell(3).setCellValue("Not correct form URL.");
						}
					}
				}

			}

			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception - " + e);
		}
	}

	public void verifyLinkActivehttp(String linkUrl, XSSFRow excelRow) throws IOException {
		HttpURLConnection httpURLConnect = null;
		System.setProperty(
				"org.apache.commons.logging.simplelog.log.org.apache.http.client.protocol.ResponseProcessCookies",
				"fatal");

		try {
			URL url = new URL(linkUrl);

			httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(20000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			} else if ((httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
					|| (httpURLConnect.getResponseCode() == 404)) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				Report.updateExtentStatus(linkUrl, "Response Code : " + httpURLConnect.getResponseCode()
						+ "; Response Message : " + httpURLConnect.getResponseMessage(), Status.FAIL);
			} else if ((httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
					|| (httpURLConnect.getResponseCode() == 503)) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				Report.updateExtentStatus(linkUrl, "Response Code : " + httpURLConnect.getResponseCode()
						+ "; Response Message : " + httpURLConnect.getResponseMessage(), Status.FAIL);
			} else {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ httpURLConnect.getResponseCode());
			}

			excelRow.createCell(1).setCellValue(linkUrl);
			excelRow.createCell(2).setCellValue(httpURLConnect.getResponseCode());
			excelRow.createCell(3).setCellValue(httpURLConnect.getResponseMessage());

		} catch (Exception e) {

			System.out.println(linkUrl + "  - Connection timeout.");
			Report.updateExtentStatus(linkUrl, linkUrl + ": Connection Timeout.", Status.FAIL);
			excelRow.createCell(1).setCellValue(linkUrl);
			excelRow.createCell(2).setCellValue("Exception");
			excelRow.createCell(3).setCellValue("Connection Timeout");
			e.printStackTrace();
		}
	}

	public void verifyLinkActivehttps(String linkUrl, XSSFRow excelRow) throws IOException {
		HttpsURLConnection httpsURLConnect = null;
		System.setProperty(
				"org.apache.commons.logging.simplelog.log.org.apache.http.client.protocol.ResponseProcessCookies",
				"fatal");

		try {
			/*
			 * System.setProperty("http.proxyHost","proxy.cognizant.com");
			 * System.setProperty("http.proxyPort","6050");
			 * System.setProperty("https.proxyHost","proxy.cognizant.com ");
			 * System.setProperty("https.proxyPort","6050");
			 */

			URL url = new URL(linkUrl);

			httpsURLConnect = (HttpsURLConnection) url.openConnection();
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());

			httpsURLConnect.setSSLSocketFactory(sc.getSocketFactory());
			httpsURLConnect.setConnectTimeout(20000);
			httpsURLConnect.connect();

			if (httpsURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpsURLConnect.getResponseMessage());
				Report.updateExtentStatus(linkUrl,linkUrl+ ":Response Code : " + httpsURLConnect.getResponseCode()
				+ "; Response Message : " + httpsURLConnect.getResponseMessage(), Status.PASS);
			} else if ((httpsURLConnect.getResponseCode() == HttpsURLConnection.HTTP_NOT_FOUND)
					|| (httpsURLConnect.getResponseCode() == 404)) {
				System.out.println(linkUrl + " - " + httpsURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				Report.updateExtentStatus(linkUrl,linkUrl+ ":Response Code : " + httpsURLConnect.getResponseCode()
						+ "; Response Message : " + httpsURLConnect.getResponseMessage(), Status.FAIL);
			} else if ((httpsURLConnect.getResponseCode() == HttpsURLConnection.HTTP_NOT_FOUND)
					|| (httpsURLConnect.getResponseCode() == 503)) {
				System.out.println(linkUrl + " - " + httpsURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				Report.updateExtentStatus(linkUrl,linkUrl+ ":Response Code : " + httpsURLConnect.getResponseCode()
						+ "; Response Message : " + httpsURLConnect.getResponseMessage(), Status.FAIL);
			}

			else {
				System.out.println(linkUrl + " - " + httpsURLConnect.getResponseMessage() + " - "
						+ httpsURLConnect.getResponseCode());
			}

			excelRow.createCell(1).setCellValue(linkUrl);
			excelRow.createCell(2).setCellValue(httpsURLConnect.getResponseCode());
			excelRow.createCell(3).setCellValue(httpsURLConnect.getResponseMessage());

		} catch (Exception e) {
			System.out.println(linkUrl + " - Connection timeout.");
			Report.updateExtentStatus(linkUrl, linkUrl + ": Connection Timeout.", Status.FAIL);
			excelRow.createCell(1).setCellValue(linkUrl);
			excelRow.createCell(2).setCellValue("Exception");
			excelRow.createCell(3).setCellValue("Connection Timeout");
			e.printStackTrace();
		}
	}

	/*
	 * Method to verify all the links given in SiteMapUrl excel
	 */
	public void checkAllInputLinks() {
		try {

			// Set file path and file name to set for input excel
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Datatables";
			String fileName = "SiteMapUrl.xlsx";
			// String siteName = dataTable.getData("General_Data", "SiteName");
			fileName = dataTable.getData("General_Data", "FileName");
			File file = new File(filePath + "\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook UrltestWorkbook = null;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {
				UrltestWorkbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				UrltestWorkbook = new HSSFWorkbook(inputStream);
			}

			// Find no of entries in the first sheet from input excel
			Sheet UrltestSheet = UrltestWorkbook.getSheet("Sheet1");
			int rowCount = UrltestSheet.getLastRowNum() - UrltestSheet.getFirstRowNum();

			// Create new excel for storing output
			String dateFormatString = "dd-MMM-yyyy hh:mm:ss a";
			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
			Calendar calendar = Calendar.getInstance();
			String filename = System.getProperty("user.dir") + "\\Results\\Output_" + testcase + "_"
					+ dateFormat.format(calendar.getTime()).replace(" ", "_").replace(":", "-") + ".xls";
			XSSFWorkbook workbook = new XSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(filename);

			// Create first sheet in the output excel
			XSSFSheet sheet = workbook.createSheet("Sheet1");

			// Create column headers in the output excel
			XSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("No.");
			rowhead.createCell(1).setCellValue("URL");
			rowhead.createCell(2).setCellValue("Response Code");
			rowhead.createCell(3).setCellValue("Response Message");

			System.out.println("Total links are " + rowCount);
			System.out.println("--------------------------------------------------------------------");
			Report.updateExtentStatus("", "Total links validated - " + rowCount, Status.DONE);
			driver.get(dataTable.getData("General_Data", "URL"));
			for (int i = 1; i < rowCount + 1; i++) {

				// Get first row data from the input excel
				Row row = UrltestSheet.getRow(i);
				// System.out.println("--------------------------------------------------------------------");

				if (!row.getCell(1).getStringCellValue().isEmpty()) {

					// System.out.print(row.getCell(1).getStringCellValue()+"|| ");
					XSSFRow excelRow = sheet.createRow((short) (i));

					String url = row.getCell(1).getStringCellValue();
					if (url != null) {
						if (url.contains("https:")) {
							excelRow.createCell(0).setCellValue(i);
							verifyLinkActivehttps(url, excelRow);
						} else if (url.contains("http:")) {
							excelRow.createCell(0).setCellValue(i);
							verifyLinkActivehttp(url, excelRow);
						} else {
							System.out.println(url + " - Not correct form URL.");
							Report.updateExtentStatus(url, url + ":Not correct form URL.", Status.FAIL);
							excelRow.createCell(0).setCellValue(i);
							excelRow.createCell(1).setCellValue(url);
							excelRow.createCell(2).setCellValue("Invalid");
							excelRow.createCell(3).setCellValue("Not correct form URL.");
						}

					} else {
						System.out.println(url + " No URL present..");
						Report.updateExtentStatus(url, url + ":No URL present.", Status.FAIL);
						excelRow.createCell(0).setCellValue(i);
						excelRow.createCell(1).setCellValue(url);
						excelRow.createCell(2).setCellValue("Invalid");
						excelRow.createCell(3).setCellValue("Not correct form URL.");
					}
				}
				System.out.println("Validate row - " + i);
			}

			workbook.write(fileOut);
			workbook.close();
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Exception - " + e);
		}
	}

	public void invokeApplication() throws IOException {
		Report.updateExtentStatus("Invoke Application", "Application invoked.", Status.DONE);
	}

}
