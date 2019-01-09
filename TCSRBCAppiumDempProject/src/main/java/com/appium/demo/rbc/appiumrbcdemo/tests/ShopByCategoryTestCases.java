/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.tests;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appium.demo.rbc.appiumrbcdemo.core.RBCTestBase;
import com.appium.demo.rbc.appiumrbcdemo.pages.HomePage;
import com.appium.demo.rbc.appiumrbcdemo.pages.ShopByCategory;
import com.appium.demo.rbc.appiumrbcdemo.pages.SignUpPage;


public class ShopByCategoryTestCases extends RBCTestBase{
	
	SignUpPage signup;
	 HomePage homePage;
	 ShopByCategory shopBy;
	
	
	
	
	//We can add the Groups and other related concepts here........
	//Also....We can implement the Data Provider concept to read the Data from various ways like  - Properties files or Json files or Excel and csv files
	
	
	@BeforeMethod(alwaysRun = true)
    public void setUp(Method m) {
		
		//Initializing the Page Objects with driver instance here....
		
		  signup = new SignUpPage(driver);
		  homePage = new HomePage(driver);
		  shopBy = new ShopByCategory(driver);
		  
        Test test = m.getAnnotation(Test.class);
        if (test == null) {
            return;
        }
		Reporter.log("The Method that is going to execute is::"+m.getName());

    }
	
	
	//Verify if the Item is In stock or not

	@Test(groups = { "SmokeTest" })
	public void verifyKindleItems() {
		signup.skipSignin();
		homePage.navigateToShopByCategory();
		shopBy.navigateToShopByKindle();
		shopBy.chooseItem();
		shopBy.verifyItemAvailability();	

	}


}
