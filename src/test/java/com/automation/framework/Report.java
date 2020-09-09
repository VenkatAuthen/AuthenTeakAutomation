package com.automation.framework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;;

public class Report extends BaseClass {
	// ********************************************************************
	// Declarations
	// ********************************************************************
	static String ClassName;

	static String TestCaseName;

	static File FILE;

	static File imFILE;

	StopWatch pageLoad = new StopWatch();
	StopWatch timer = new StopWatch();

	/**
	 * Document
	 */
	Document document;

	/**
	 * PdfWriter
	 */
	PdfWriter writer;

	/**
	 * PdfPTables
	 */
	PdfPTable statusTable;

	/**
	 * PdfCell
	 */
	PdfPCell cell;

	/**
	 * Overal Run Result
	 */
	public static boolean overalRunResultFlag = false;

	// *******************************
	// Date Declarations For Run Summary
	// *******************************
	static java.util.Date runStartTimeStamp = new java.util.Date();

	static String[] date1 = runStartTimeStamp.toString().split(" ");
	static String[] date2 = date1[3].split(":");
	static String dateval = date2[0] + date2[1] + date2[2];

	// Current Directory
	private static String currentDir = System.getProperty("user.dir");

	// Dynamic Report Folder Path
	private static String dateFolder;

	// ********************************************************************
	// Constructor
	// ********************************************************************

	@SuppressWarnings("static-access")
	public Report(String testCaseName) {
		this.ClassName = testCaseName;
	}
	
	public static void updateExtentStatus(String stepName, String stepDescription, String stepStatus)
			throws IOException {
		if (!(stepName.equalsIgnoreCase("error")))
			if (stepStatus.equalsIgnoreCase("fail")) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.FAIL, stepDescription,
						MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
			} else if (stepStatus.equalsIgnoreCase("pass")) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.PASS, stepDescription,
						MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
			} else if (stepStatus.equalsIgnoreCase("warning")) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.WARNING,
						MarkupHelper.createLabel(stepDescription, ExtentColor.ORANGE));
			} else if (stepStatus.equalsIgnoreCase("debug")) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.DEBUG,
						MarkupHelper.createLabel(stepDescription, ExtentColor.INDIGO));
			}
	}
	
	public static void updateExtentStatus(String stepName, String stepDescription, Status status)
			throws IOException {
		if (!status.equals(Status.ERROR))
			
			if (status.equals(Status.FAIL)) {
				if(Boolean.parseBoolean(properties.getProperty("TakeScreenshotFailedStep"))) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.FAIL, stepDescription,
						MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
				}else {
					ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.FAIL,
							MarkupHelper.createLabel(stepDescription, ExtentColor.RED));
				}
			} else if (status.equals(Status.PASS)) {
				if(Boolean.parseBoolean(properties.getProperty("TakeScreenshotPassedStep"))) {
				ExtentTestManager.getTest().pass(stepDescription,
						MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
				}else {
					ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.PASS,
							MarkupHelper.createLabel(stepDescription, ExtentColor.GREEN));
				}
			} else if (status.equals(Status.WARNING)) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.WARNING,
						MarkupHelper.createLabel(stepDescription, ExtentColor.ORANGE));
			} else if (status.equals(Status.DEBUG)) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.DEBUG,
						MarkupHelper.createLabel(stepDescription, ExtentColor.INDIGO));
			}
	}
	
	// ******************************************************************************************************************************
	// ScreenShot function
	// ******************************************************************************************************************************
	protected static String takeScreenshot() {
		// ****************************************************************************
		// Folder path creation
		// ****************************************************************************
		// If Images folder is not present create an image folder in current directory
		imFILE = new File(currentDir + "\\images");
		if (!imFILE.exists()) {
			imFILE.mkdir();
		}

		// If Screenshots folder is not present, then create a screenshot folder in
		// current directory
		imFILE = new File(currentDir + "\\images\\Screenshots");
		if (!imFILE.exists()) {
			imFILE.mkdir();
		}

		// ****************************************************************************

		// Image Time Stamp
		java.util.Date imgTimeStamp = new java.util.Date();
		String[] imgdate1 = imgTimeStamp.toString().split(" ");
		String[] imgdate2 = imgdate1[3].split(":");
		String imgdateval = imgdate2[0] + imgdate2[1] + imgdate2[2];

		// ImagePath
		String imgPath = currentDir + "\\test-output\\images\\ScreenShots\\page_" + imgdate1[1] + imgdate1[2] + imgdateval + ".jpeg";

		// ****************************************************************************

		// GetScreenShot Method Directory and Image File
		File getSreenShotMethodImageFile = new File(imgPath);

		// Take Screenshot of viewable area
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Write Screenshot to a file
		try {
			FileUtils.copyFile(scrFile, getSreenShotMethodImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgPath;
	}
	
	// ******************************************************************************************************************************
	// ******************************************************************************************************************************
	// ******************************************************************************************************************************
	// Reporting Functiions
	// ******************************************************************************************************************************
	// ******************************************************************************************************************************
	// ******************************************************************************************************************************

	// ******************************************************************************************************************************
	// Start Report
	// ******************************************************************************************************************************

	@SuppressWarnings("static-access")
	public void start_report(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl) {

		this.TestCaseName = TestCaseName;
		pageLoad.reset();
		pageLoad.start();

		// ****************************************************************************
		// Folder Path Creation
		// ****************************************************************************

		// Create pdf_Reports folder if it is not created
		this.FILE = new File(currentDir + "\\Pdf_Reports");
		if (!this.FILE.exists()) {
			this.FILE.mkdir();
		}

		this.dateFolder = currentDir + "\\Pdf_Reports\\" + this.date1[1] + "_" + this.date1[2] + "_" + this.date1[5];

		this.FILE = new File(dateFolder);
		if (!this.FILE.exists()) {
			this.FILE.mkdir();
		}

		// Create page specific folder
		this.FILE = new File(dateFolder + "\\" + this.ClassName);
		if (!this.FILE.exists()) {
			this.FILE.mkdir();
		}
		try {
			this.document = new Document(PageSize.A4);
			writer = PdfWriter.getInstance(document, new FileOutputStream(new File(dateFolder + "\\" + this.ClassName
					+ "\\" + this.TestCaseName + "_" + date1[1] + date1[2] + dateval + ".pdf")));
			/* PdfWriter.getInstance(document, new FileOutputStream(new File (FILE))); */
			document.open();

			// ***************************************************************************************************************************
			// Main Heading
			// ***************************************************************************************************************************
			// Test Report Name

			// Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));
			// Add Main Heading
			Font blackTimes = new Font(FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
			Chunk mainHeading = new Chunk(TestCaseName, blackTimes);
			Paragraph p = new Paragraph(mainHeading);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			// Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			// Add a dummy line
			document.add(new Paragraph("\n"));

			// ***************************************************************************************************************************
			// Test Case Details
			// ***************************************************************************************************************************
			// Test case Name
			document.add(new Paragraph("Testcase Name : " + TestCaseName,
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Test Objective
			document.add(new Paragraph("Test Objective : " + TestCaseObjective,
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Test Environment
			document.add(new Paragraph("Test Environment : " + TestEnvironmentUrl,
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Java Version
			document.add(new Paragraph("Java Version : " + System.getProperty("java.version"),
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Host Name
			document.add(new Paragraph("Host Name : " + InetAddress.getLocalHost().getHostName(),
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Operating System
			document.add(new Paragraph("Operating System : " + System.getProperty("os.name"),
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Add a dummy line
			document.add(new Paragraph("\n"));

			// Add a line separator
			document.add(new LineSeparator(0.5f, 100, null, 0, -5));

			Image dxcLogo = Image.getInstance(currentDir + "\\dxc_logo.png");
			// If image size exceeds a threshold value decrease it to below size
			if ((dxcLogo.getWidth() > 525.00) | (dxcLogo.getHeight() > 500.00)) {
				dxcLogo.scaleToFit(500, 600);
				dxcLogo.setAlignment(dxcLogo.ALIGN_CENTER);
			}

			// Add DXC Logo
			document.add(dxcLogo);

			// Add a new page/ page break
			document.newPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void LogResult_and_CaptureImage(String Status, String StepName, String StepDescription, String screenCapture,
			String... pageRenderTime) throws DocumentException, MalformedURLException, Exception {
		java.util.Date date1 = new java.util.Date();
		//updateExtentStatus(StepName, StepDescription, Status);
		// ******************************************************************************************************************************
		// Basic Table format
		// ******************************************************************************************************************************
		try {
			Font blackTimesNormal = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
			Font blackTimesBold = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);

			this.statusTable = new PdfPTable(new float[] { .5f, .5f, .2f, .6f });
			Chunk stepDetails = new Chunk("Step Details", blackTimesBold);
			Paragraph p = new Paragraph(stepDetails);
			p.setAlignment(Element.ALIGN_LEFT);
			cell = new PdfPCell(p);
			cell.setColspan(4);
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			this.statusTable.addCell(cell);

			Chunk stepNameHeading = new Chunk("Step Name", blackTimesBold);
			cell = new PdfPCell(new Paragraph(stepNameHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			this.statusTable.addCell(cell);

			Chunk stepDescriptionHeading = new Chunk("Step Description", blackTimesBold);
			cell = new PdfPCell(new Paragraph(stepDescriptionHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			this.statusTable.addCell(cell);

			Chunk statusHeading = new Chunk("Status", blackTimesBold);
			cell = new PdfPCell(new Paragraph(statusHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			this.statusTable.addCell(cell);

			Chunk timeHeading = new Chunk("Time", blackTimesBold);
			cell = new PdfPCell(new Paragraph(timeHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			this.statusTable.addCell(cell);

			// ******************************************************************************************************************************
			// Appending Data To Table
			// ******************************************************************************************************************************

			// When Passed
			if (Status.equalsIgnoreCase("PASS")) {
				// Step name
				Chunk stepName = new Chunk(StepName, blackTimesNormal);
				cell = new PdfPCell(new Paragraph(stepName));
				this.statusTable.addCell(cell);
				// Step description
				Chunk stepDescription = new Chunk(StepDescription, blackTimesNormal);
				cell = new PdfPCell(new Paragraph(stepDescription));
				this.statusTable.addCell(cell);
				// Status
				Font green = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(39, 174, 96));
				Chunk greenStatus = new Chunk(Status, green);
				cell = new PdfPCell(new Paragraph(greenStatus));
				this.statusTable.addCell(cell);
				// Time
				Chunk time = new Chunk(date1.toGMTString(), blackTimesNormal);
				cell = new PdfPCell(new Paragraph(time));
				this.statusTable.addCell(cell);

			}

			// When Failed
			else if (Status.equalsIgnoreCase("Fail")) {
				// Step name
				Chunk stepName = new Chunk(StepName, blackTimesNormal);
				cell = new PdfPCell(new Paragraph(stepName));
				this.statusTable.addCell(cell);
				// Step description
				Chunk stepDescription = new Chunk(StepDescription, blackTimesNormal);
				cell = new PdfPCell(new Paragraph(stepDescription));
				this.statusTable.addCell(cell);
				// Status
				Font red = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(231, 76, 60));
				Chunk redStatus = new Chunk(Status, red);
				cell = new PdfPCell(new Paragraph(redStatus));
				this.statusTable.addCell(cell);
				// Time
				Chunk time = new Chunk(date1.toGMTString(), blackTimesNormal);
				cell = new PdfPCell(new Paragraph(time));
				this.statusTable.addCell(cell);

				// Change the result flag to "True"
				overalRunResultFlag = true;
			}

			// Update Report
			updateReport();

			if (pageRenderTime.length == 1) {
				document.add(new Paragraph("Page Load Time : " + pageRenderTime[0] + "secs",
						new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));
			}

			// Add a dummy line
			document.add(new Paragraph("\n"));

			// Screen capture if needed
			// Test Step Details: Along With Image
			// Create a Dynamic table with respect to number of test logs
			if (screenCapture.equalsIgnoreCase("YES")) {
				// Add a dummy line
				document.add(new Paragraph("\n"));
				document.add(new Paragraph(new Paragraph("Screenshot : ",
						new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD))));
				// Image
				Image img = Image.getInstance(takeScreenshot());
				// If image size exceeds a threshold value decrease it to below size
				if (img.getWidth() > 600.00) {
					img.scaleToFit(400, img.getHeight());
					img.setAlignment(Image.ALIGN_CENTER);
				}
				if (writer.getVerticalPosition(true) - document.bottom() < 160) {
					document.newPage();
				}
				document.add(img);

				// Add a dummy line
				document.add(new Paragraph("\n"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ******************************************************************************************************************************
	// Update Report
	// ******************************************************************************************************************************

	public void updateReport() {
		if (this.statusTable != null) {
			this.statusTable.setSpacingBefore(15f);
			try {

				/*
				 * float m_temp = writer.getVerticalPosition(true); float b_temp =
				 * document.bottom();
				 */
				// If in case the page space is less add a new page

				if (writer.getVerticalPosition(true) - document.bottom() < 160) {
					document.newPage();
				}

				// Add a dummy line
				document.add(new Paragraph("\n"));

				// Add a line separator
				document.add(new LineSeparator(0.5f, 100, null, 0, -5));

				// Add a dummy line
				document.add(new Paragraph("\n"));

				// Add the table
				this.document.add(this.statusTable);
			} catch (DocumentException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.statusTable.setSpacingAfter(15f);
		}
	}

	// ******************************************************************************************************************************
	// End Report
	// ******************************************************************************************************************************
	@SuppressWarnings("deprecation")
	public void endReport() {
		java.util.Date runEndTimeStamp = new java.util.Date();

		// Stop Timer
		pageLoad.stop();

		// *********************************************************************
		// Result String
		String runResult;
		// Validate run result flag
		if (overalRunResultFlag == true) {
			// Set Run result to FAIL
			runResult = "FAIL";
		} else {
			// Set Run result to PASS
			runResult = "PASS";
		}
		overalRunResultFlag = false;

		// *********************************************************************

		try {
			// Add a new page
			document.newPage();

			// *****************************************************************
			// Test Summary
			// *****************************************************************
			// Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			Font blackTimes = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Font greenResult = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(39, 174, 96));
			Font redResult = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(231, 76, 60));
			Font blackTimesDefaultSize = new Font(FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD, BaseColor.BLACK);

			Chunk summaryHeading = new Chunk("Run Summary", blackTimes);
			Paragraph p = new Paragraph(summaryHeading);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			// Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			// Add a dummy line
			document.add(new Paragraph("\n"));

			// ***************************************************************************************************************************
			// Test Run Details
			// ***************************************************************************************************************************

			// Add a line separator
			document.add(new LineSeparator(0.5f, 100, null, 0, -5));

			// Add a dummy line
			document.add(new Paragraph("\n"));

			// Overall Status
			Chunk beginning = new Chunk("Result : ", blackTimesDefaultSize);
			Phrase p1 = new Phrase(beginning);
			if (runResult.equalsIgnoreCase("PASS")) {
				Chunk runresult = new Chunk(runResult, greenResult);
				p1.add(runresult);
				Paragraph p8 = new Paragraph();
				p8.add(p1);
				document.add(p8);
			} else {
				Chunk runresult = new Chunk(runResult, redResult);
				p1.add(runresult);
				Paragraph p8 = new Paragraph();
				p8.add(p1);
				document.add(p8);
			}

			// Run Started
			document.add(new Paragraph("Run Started : " + runStartTimeStamp.toGMTString(),
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Run Ended
			document.add(new Paragraph("Run Ended : " + runEndTimeStamp.toGMTString(),
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Run Duration
			document.add(new Paragraph("Run Duration : " + (pageLoad.getTime() / 1000) + " seconds",
					new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			// Add a dummy line
			document.add(new Paragraph("\n"));

			// Add a line separator
			document.add(new LineSeparator(0.5f, 100, null, 0, -5));

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}