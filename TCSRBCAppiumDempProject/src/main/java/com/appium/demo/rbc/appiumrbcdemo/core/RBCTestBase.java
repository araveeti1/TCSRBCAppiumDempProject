/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.demo.rbc.appiumrbcdemo.core.CustomAssersion;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.appium.demo.rbc.appiumrbcdemo.core.RBCUtils;
import com.appium.demo.rbc.appiumrbcdemo.pages.HomePage;
import com.appium.demo.rbc.appiumrbcdemo.pages.ShopByCategory;
import com.appium.demo.rbc.appiumrbcdemo.pages.SignUpPage;

public class RBCTestBase {
	
	protected static AppiumDriver<MobileElement> driver;
	
	public static String outPutDirectory = null;
	
	public CustomAssersion assersion;
	public RBCUtils utils;
	public SignUpPage signup;
	public HomePage homePage;
	public ShopByCategory shopBy;
	
	// ATUTestRecorder recorder;

	
	//Change the Default Output Folder - The Output folder will be created here....
	@BeforeSuite
	public void setup(ITestContext ctx) {
		utils = new RBCUtils();
		DOMConfigurator.configure("log4j.xml");
		//SimpleLayout layout = new SimpleLayout();           
		//FileAppender appender = new FileAppender(layout,"your filename",false);
		//log.addAppender(appender); 
		
		String baseDir = System.getProperty("user.dir");
		 Date date= new Date();
		 outPutDirectory = baseDir+File.separator+"testresults"+File.separator+ new Timestamp(date.getTime());
	    TestRunner runner = (TestRunner) ctx;
	    runner.setOutputDirectory(outPutDirectory);
	}
	
	//Make sure this always runs successfully

	@BeforeTest(alwaysRun = true)
    @Parameters( { "platform", "udid", "platformVersion"})
	public void beforeTest(String platform, String udid, String platformVersion) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        
        String gridURL = "http://"+utils.readProperty("grid.ip")+":"+utils.readProperty("device.port")+"/wd/hub";
        switch (platform.toLowerCase()) {        
            case "ios":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,utils.readProperty("devce_name"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.APP, new File("Path to the ipa file").getAbsolutePath());

                driver = new IOSDriver<MobileElement>(new URL(gridURL), capabilities);
        		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        		assersion = new CustomAssersion(driver);


                break;

            case "android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, utils.readProperty("devce_name"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                File classpathRoot = new File(System.getProperty("user.dir"));
        		File appDir = new File(classpathRoot, "/Apps/Amazon/");
        		File app = new File(appDir, "Amazon India Online Shopping and Payments_v16.21.0.300_apkpure.com.apk");
        		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
                capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

                driver = new AndroidDriver<MobileElement>(new URL(gridURL), capabilities);
        		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        		assersion = new CustomAssersion(driver);

                break;

            default:
                throw new Exception("unknown platform name is Given and the test Fails.Please check the platform name in the testng.xml file for the same.");
        }
    }
	
	

	
	//Quit the Appium After the Test
	@AfterTest
	 public void End() throws IOException, ATUTestRecorderException {
	  driver.quit();
	  // Stop appium server when test Is ended.
	  
	  // Stop video recording.
	//  recorder.stop();
	 }
	
	@BeforeMethod
    public void setUp(Method m) {
		
		//Initializing the Page Objects with driver instance here....
		
		 signup = new SignUpPage(driver);
		 homePage  = new HomePage(driver);
		 shopBy = new ShopByCategory(driver);
		
        Test test = m.getAnnotation(Test.class);
        if (test == null) {
            return;
        }
		Reporter.log("The Method that is going to execute is::"+m.getName());

    }

	@AfterMethod
	public void TestResultAfterTestMethod(ITestResult result,Method m) {

	    int status = result.getStatus();

	    switch (status) {
	    
	        case ITestResult.SUCCESS:
	    		Reporter.log("The Method ::"+ m.getName() +"is Passed Successfully");

	            break;
	        case ITestResult.FAILURE:
	    		Reporter.log("The Method ::"+ m.getName() +"is Failed");

	        	assersion.takeScreenShot();
	        	
	            break;
	        case ITestResult.SKIP:
	    		Reporter.log("The Method ::"+ m.getName() +"is Skipped");

	        	assersion.takeScreenShot();
	            break;
	        default:
	            throw new RuntimeException("The Invalid status");
	    }
	}

}
