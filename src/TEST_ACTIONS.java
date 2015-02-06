import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TEST_ACTIONS {
	
	public static  WebDriver driver;
	 
	@BeforeClass
	public static void setUp() throws Exception {
		
		// create anonymous class WebDriverManager and get driver for chooses browser
		driver = new WebDriverManager(driver).getWebDriverFor(WebDriverManager.Browser.FIREFOX);
	 	// set time for wait driver
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	 /*
	  *  test method for log in log out
	  */
	 @Ignore
	 public void testUserProfileLogInLogOut() throws Exception{
		 
		 String wrongData = "WRONG";
		
		 // load main page
		 driver.get("https://tradernet.ru/social/feed#/all");
	 	
		 // create Login User 
		 LoginUser currUser = new  LoginUser(driver); 
		 
		 // try wrong login
		 currUser.loginAs(wrongData, "15021976");
		 
		// ASSERT for visible error
		 assertTrue(currUser.isErrorVisible());
		 
		 // close window
		 //currUser.closeLogWnd();
		 
		 // reload main page
		 driver.get("https://tradernet.ru/social/feed#/all");
		 
		 // try not authorization email
		 currUser.loginAs("angelluk@mail.net", "15021976");
		 
		 // ASSERT for visible error
		 assertTrue(currUser.isErrorVisible());
		 
		 // reload main page
		 driver.get("https://tradernet.ru/social/feed#/all");
		 
		 // try wrong password
		 currUser.loginAs("angelluk@ukr.net", wrongData);
		 
		 // ASSERT for visible error
		 assertTrue(currUser.isErrorVisible());
		 
		 // reload main page
		 driver.get("https://tradernet.ru/social/feed#/all");
		 
		// valid SingIn
		 currUser.loginAs("angelluk@ukr.net", "15021976");
		 
		 Thread.sleep(10000); // for FX
		 
		 // ASSERT for visible user menu after login
		 assertTrue(currUser.isUserMenuVisible());
		 
		 // user log out
		 currUser.logOut();
		 
		// ASSERT for absent user menu after log out
		 assertFalse(currUser.isUserMenuVisible());	
	 }
	 
	 /*
	  *  test method for update user settings
	  */
	 @Test
	 public void testUserProfileSettings() throws Exception{
		 
		 String userName 		= "NewName1";
		 String userProfile 	= "NewMyProfile1";
		 String userDescr 		= "My new decription \n with new line1";
		 
		 // load main page
		 driver.get("https://tradernet.ru/social/feed#/all");
	 	
		 // create Login User 
		 LoginUser currUser = new  LoginUser(driver); 
		 
		
		 currUser.loginAs("angelluk@ukr.net", "15021976")	// log in as valid user
		 		 .showUserSetting()							// show user settings
		 		 .setUserName(userName) 					// set user name
		 		 .setUserProfile(userProfile) 				// set user Profile
		 		 .setUserDescription(userDescr)				//set description
		 		 .saveUserSettings();						// save user settings
		
		 Thread.sleep(3000);
		 
		 // log out log in  and show settings
		 currUser.logOut().loginAs("angelluk@ukr.net", "15021976").showUserSetting();
		 
		// ASSERT for save user name
		 assertTrue(currUser.checkUserName(userName)); 
		 
		 // ASSERT for save user profile
		 assertTrue(currUser.checkUserProfile(userProfile)); 
		 
		 // ASSERT for save user description
		 assertTrue(currUser.checkUserDescription(userDescr)); 
		 
	 }
		
	@AfterClass
     public static void tearDown()
     { 
          driver.quit(); 
     } 
	
}
