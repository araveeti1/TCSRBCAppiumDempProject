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

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.demo.rbc.appiumrbcdemo.core.MobileActionMethods;

public class HomePage extends MobileActionMethods{
	
	//Creating the Generic Driver Instance.
	
	 private AppiumDriver<MobileElement> driver;
	 
	    
	    //Initilize the Elements using Page Factory
	    
	    public HomePage(AppiumDriver<MobileElement> driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	    
	    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/action_bar_burger_icon")
	    private MobileElement btnHamburger;
	    
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shop by Category']")
	    private MobileElement btnShopBy;
	    
	   
	    
	    public boolean navigateToShopByCategory() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		click(btnHamburger);
	    		click(btnShopBy);
	    		log.info("Successfully navigated to the Shop by Category screen");
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		log.error("Failed to navigate to the Shop by Category Screen");
	    		assersion.takeScreenShot();    		
	    	}
	    	return blnVerify;
	    }
	    
	   
}

