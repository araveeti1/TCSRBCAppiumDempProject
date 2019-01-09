/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.appium.demo.rbc.appiumrbcdemo.core.CustomAssersion;
import com.appium.demo.rbc.appiumrbcdemo.core.MobileActionMethods;

public class ShopByCategory extends MobileActionMethods{
	
	 private AppiumDriver<MobileElement> driver;
	 public  CustomAssersion assersion;
	 
	    
	    //Initilize the Elements using Page Factory
	 
		//Creating the Generic Driver Instance.

	    
	    public ShopByCategory(AppiumDriver<MobileElement> driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	         assersion = new CustomAssersion(driver);

	    }
	    
	    @AndroidFindBy(xpath = "//android.view.View[@text='Kindle E-Readers & eBooks']")
	    private MobileElement btnKindle;
	    	   
	    @AndroidFindBy(xpath = "//android.view.View[@text='Kindle E-Readers']")
	    private MobileElement btnEreader;
	    
	    @AndroidFindBy(xpath = "//android.view.View[contains(@text, 'Kindle E-reader - Black')]")
	    private MobileElement iTemKindle;
	    	    
	    @AndroidFindBy(xpath = "//android.view.View[@text='In stock.']")
	    private MobileElement lblInStock;
	    
	    
	    
	    public boolean navigateToShopByKindle() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		click(btnKindle);
	    		click(btnEreader);
	    		log.info("Successfully Navigated to the Shop by Kindle Items");
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		assersion.takeScreenShot();
	    		log.error("Failed to navigate to the Shop by Kindle Items and the Test Fails");    		
	    	}
	    	return blnVerify;
	    }
	    
	    
	    
	
	    public boolean chooseItem() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		click(iTemKindle);
	    		log.info("Successfully Clicked on the Kindle Item on the amazon app");
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		assersion.takeScreenShot();
	    		log.error("Failed to verify the Kindle Items on the Amazon App and the Test Fails");
	    	}
	    	return blnVerify;
	    }
	    
	    
	    public boolean verifyItemAvailability() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		Assert.assertTrue(verifyElementPresence(lblInStock, "In stock."));
	    		log.info("Successfully Verified the Item Availability and the Test Passed");
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		assersion.takeScreenShot();
	    		log.error("The Item is not Available in the Stock and the Test Fails");
	    	}
	    	return blnVerify;
	    }
 
	   
}

