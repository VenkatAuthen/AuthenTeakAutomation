package  com.automation.framework;
 
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.service.ExtentTestManager;
 
public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentTest extentTest;
	public static String x;
	private static ExtentHtmlReporter htmlReporter;
	static String callerClassName = new Exception().getStackTrace()[0].getClassName();

 	static java.util.Date date = new java.util.Date();
    static String[] date1 = date.toString().split(" ");
    static String[] date2 = date1[3].split(":");
    static String dateval = date2[0] + date2[1] + date2[2];
	private static String filePath = System.getProperty("user.dir")+File.separator+"Reports";
	
	@SuppressWarnings("static-access")
	public  void setpath(String def)
	{
		
		this.x = def;
		
		
	}
	 @SuppressWarnings("static-access")
	public String getName() {
		
	        return this.x;
	    }
	public static ExtentReports GetExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();	
		extent.attachReporter(getHtmlReporter());
		return extent;
	}
 
	private static ExtentHtmlReporter getHtmlReporter() {
		
		ExtentManager em = new ExtentManager();
		
		String filepath2 = filePath + em.getName() + date1[1] + date1[2] + dateval + ".html";
		
		
        htmlReporter = new ExtentHtmlReporter(filepath2);
        htmlReporter.loadXMLConfig("C:/IAS/IAS_Selenium/Generated Tests/Automation_Scripts/Reports/XmlTemplate/Myextent-config.xml");
        return htmlReporter;
}
	
	public static ExtentTest createTest(String name, String description){
		extentTest = extent.createTest(name, description);
		return extentTest;
	}
	
	public static void updateExtentStatus(String stepName, String stepDescription, Status stepStatus) throws IOException {
		if (!(stepName.equalsIgnoreCase("error")))
			if (stepStatus.equals(Status.FAIL)) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.FAIL,
						MarkupHelper.createLabel(stepDescription, ExtentColor.RED));
				ExtentTestManager.getTest().addScreenCaptureFromPath(takeScreenshot(System.getProperty("user.dir")+File.separator+"Screenshots.jpg"));
			} else if (stepStatus.equals(Status.PASS)) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.PASS,
						stepDescription,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(System.getProperty("user.dir")+File.separator+"Screenshots")).build());
				ExtentTestManager.getTest().addScreenCaptureFromPath(takeScreenshot(System.getProperty("user.dir")+File.separator+"Screenshots"));
			} else if (stepStatus.equals(Status.WARNING)) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.WARNING,
						MarkupHelper.createLabel(stepDescription, ExtentColor.ORANGE));
			} else if (stepStatus.equals(Status.DEBUG)) {
				ExtentTestManager.getTest().log(com.aventstack.extentreports.Status.DEBUG,
						MarkupHelper.createLabel(stepDescription, ExtentColor.INDIGO));
			}
	}

	/**
	 * Function to take a screenshot
	 * 
	 * @param screenshotPath
	 *            The path where the screenshot should be saved
	 */
	protected static String takeScreenshot(String screenshotPath) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle rectangle = new Rectangle(0, 0, screenSize.width, screenSize.height);
		Robot robot;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while creating Robot object (for taking screenshot)");
		}

		BufferedImage screenshotImage = robot.createScreenCapture(rectangle);
		File screenshotFile = new File(screenshotPath);

		try {
			ImageIO.write(screenshotImage, "jpg", screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while writing screenshot to .jpg file");
		}
		return screenshotFile.getAbsolutePath();
	}
	
	
}
