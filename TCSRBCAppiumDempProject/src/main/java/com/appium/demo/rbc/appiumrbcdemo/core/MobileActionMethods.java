/*Author :  Venkatramana Reddy Araveeti
 * 
 */

package com.appium.demo.rbc.appiumrbcdemo.core;
import java.io.IOException;


import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.MobileElement;


import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MobileActionMethods {
	
	//Creating the Generic Driver Instance.

	 private AppiumDriver<MobileElement> driver;
	 
	 public static Logger log = Logger.getLogger(Log.class.getName());
	 
	 public CustomAssersion assersion;

	
	 public MobileActionMethods(AppiumDriver<MobileElement> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	         assersion = new CustomAssersion(driver);
	    }

	private static final TimeUnit SECONDS = null;
	
	
		public boolean click(MobileElement objElement)
			throws InterruptedException, IOException, NoSuchElementException {
			
		boolean isVerify = false;
		try {
				waitForElementClickable(objElement);
				objElement.click();
				log.info("The element" + objElement + " Has been clicked successfully");
				isVerify = true;
		} catch (IllegalArgumentException e) {
			log.error(" Exception is thrown at run time");
			throw e;


		} catch (NoSuchElementException n) {
			log.error("No such Element Exception");
			throw n;


		} catch (Exception e) {
			log.error("An Exception is thrown at run time ");
			throw e;


		}
		return isVerify;
	}

	

	
	
	public boolean  sendKeys(MobileElement objElement, String sTextToSend)
			throws InterruptedException, IOException, TimeoutException {
		boolean isVerify = false;
		try {
			waitForElementClickable(objElement);
			objElement.clear();
			objElement.sendKeys(sTextToSend);
			log.info("The Text is entered Successfully");
			isVerify = true;
			
		} catch (IllegalArgumentException e) {
			log.error("An Exception is thrown at run time ");
			throw e;


		} catch (Exception e) {
			log.error("Time out Exception is thrown at run time ");
			throw e;

		}	
		
		return isVerify;
	}

	
	
	//Get the screen size
	 
	public int[] getScreenSize() throws IOException {
		try {
			Dimension size = ((AppiumDriver) driver).manage().window().getSize();
			int x = size.width;
			int y = size.height;
			int[] returnVal = { x, y };
			return returnVal;
		} catch (Exception e) {
			log.error("Exception is thrown at run time and the test Fails");		

		} 
		return null;
	}

	
	//Closing  the App
	public boolean  closeApp() throws IOException {
		boolean isVerify = false;
		try {
			((AppiumDriver) driver).closeApp();
			log.info("Successfully closed the App ");
			isVerify = true;
		} catch (Exception e) {
			
			log.error("An Exception is thrown at run time");
			throw e;

		}
				
		return isVerify;
	}

	
	
	
	//Getting the Page Source
	
	public String getPageSource() throws InterruptedException {
		return driver.getPageSource();
	
	}

	/*
	 * Wait for the Element to be clickable
	 * 
	 */
	public boolean waitForElementClickable(MobileElement objElement) {
		boolean isVerify =  false;
		log.info("Waiting for the element  ::" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait((AppiumDriver) driver, 10L);
			wait.until(ExpectedConditions.elementToBeClickable(objElement));
			isVerify = true;
		}
		catch(Exception e) {
			log.error("Exception is thrown at run time and the test Fails");
			isVerify = false;
		}
		return isVerify;
	
	}
	
	/*
	 * Check the Presence of the Element with Text
	 * 
	 */

	public boolean verifyElementPresence(MobileElement objElement,String aText) {
		boolean isVerify =  false;
		log.info("Verifying the Presence of Element" + objElement);
		try {
			WebDriverWait wait = new WebDriverWait((AppiumDriver) driver, 10L);
			wait.until(ExpectedConditions.textToBePresentInElement(objElement, aText));
			isVerify = true;
		}
		catch(Exception e) {
			log.error("Exception is thrown at run time while verifying the presence of Element with Text and the test Fails");
			isVerify = false;
		}
		return isVerify;
	
		}

}
