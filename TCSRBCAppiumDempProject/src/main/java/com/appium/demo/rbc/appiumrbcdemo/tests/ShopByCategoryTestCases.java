/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.tests;

import org.testng.annotations.Test;

import com.appium.demo.rbc.appiumrbcdemo.core.RBCTestBase;
import com.appium.demo.rbc.appiumrbcdemo.pages.HomePage;
import com.appium.demo.rbc.appiumrbcdemo.pages.ShopByCategory;
import com.appium.demo.rbc.appiumrbcdemo.pages.SignUpPage;


public class ShopByCategoryTestCases extends RBCTestBase{
	
	
	
	//We can add the Groups and other related concepts here........
	//Also....We can implement the Data Provider concept to read the Data from various ways like  - Properties files or Json files or Excel and csv files
	
	@Test
	public void verifyKindleItems() {			
		signup.skipSignin();
		homePage.navigateToShopByCategory();
		shopBy.navigateToShopByKindle();
		shopBy.verifyKindleItems();			
	}


}
