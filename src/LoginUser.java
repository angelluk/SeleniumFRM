import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


/*
 * Represent  user  login logout service
 */
public class LoginUser {
  
	private  WebDriver driver;

   
	/*
     *  default constructor
     */
	public  LoginUser(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!"TRADERNET".equals(driver.getTitle())) {
           
            throw new IllegalStateException("This is not the login page");
        }
    }

	  /*
	   * LOGIN_USER PAGE OBJECT LOCATORS 
	   */
    
    	By logInLocator 			= By.cssSelector(".login>span>a");
        By userNameLocator 			= By.xpath("//*[@id='login-element']/input");
        By passwordLocator 			= By.id("pwd");
        By loginButtonLocator		= By.id("submitLoginForm");
        By userMenuLocator			= By.xpath("//*[@id='showMainMenuDropDown']/a");
        By userExitLocator			= By.xpath("//*[@id='lastNavigationForLoggedInUser']/ul/ul/li[4]/a");
        By errorShowLocator			= By.id("loginFailed");	
        By closeLogLocator			= By.xpath("//*[@id='login']/div[2]/a");
        By editUserLocator			= By.cssSelector(".editAvatar");
        By editUserLoginlocator 	= By.id("login");
        By editUserProfileLocator 	= By.id("profilename");
        By editUserDescrLocator		= By.id("description");
        By editUserSaveLocator		= By.id("userProfileOk");	
 
    /*
     *  SignIn User to site
     */
    public LoginUser loginAs(String username, String password) throws InterruptedException {
       
    	// to click to SignIn link
    	driver.findElement(logInLocator).click();
    	
    	// type user name
    	driver.findElement(userNameLocator).click();
    	driver.findElement(userNameLocator).clear();
    	driver.findElement(userNameLocator).sendKeys(username); 
    	
    	// type password
    	driver.findElement(passwordLocator).click();
    	driver.findElement(passwordLocator).clear();
    	driver.findElement(passwordLocator).sendKeys(password);;
        
        // click button for login
        driver.findElement(loginButtonLocator).click();
       
        return this;
    }
    
    
    /*
     *  Log out of user
     */
   public LoginUser logOut() throws InterruptedException{
	   
	   // click on Menu
	   driver.findElement(userMenuLocator).click();
	   
	   // click Exit link
	   driver.findElement(userExitLocator).click();
	   
	   Thread.sleep(10000);  // wait for logout because implicitlyWait wait only for present element 
	   
   		return this; 
   }
  
   
   /*
    *  Close login window
    */
  public LoginUser closeLogWnd() throws InterruptedException{
  
	  // close login  window
	  driver.findElement(closeLogLocator).click();
	  return this; 
    }
  
  
    /*
     *  Check for visible user menu
     */
    public boolean isUserMenuVisible() throws Exception{
    	
    	return  isElemVisible(userMenuLocator);
    }
    
     
    /*
     *  Check for visible error message
     */
    public boolean isErrorVisible() throws Exception{
    	
    	return  isElemVisible(errorShowLocator);
    }
   
    /*
     *  Check for visible element
     */
    public boolean isElemVisible(By Locator) throws Exception {
       
    	boolean result;
    	
    	try {
            result = driver.findElement(Locator).isDisplayed();
                     
        }
    	catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return false;
        }
    	
    	return result;
    }
    
    /*
     *  Show setting of user
     */
    public LoginUser showUserSetting() throws Exception{
    	
    	// click on Menu
 	   driver.findElement(userMenuLocator).click();
 	   // click on Profile
	   driver.findElement(editUserLocator).click();
    	
    	return  this;
    }
    
    /*
     *  Set name of user
     */
    public LoginUser setUserName(String userName) throws Exception{
    	
    	// set User name
 	   driver.findElement(editUserLoginlocator).clear();
 	   driver.findElement(editUserLoginlocator).sendKeys(userName);
    	
    	return  this;
    }
    
    /*
     *  Set profile of user
     */
    public LoginUser setUserProfile(String userProfile) throws Exception{
    	
    	// set User name
 	   driver.findElement(editUserProfileLocator).clear();
 	   driver.findElement(editUserProfileLocator).sendKeys(userProfile);
    	
    	return  this;
    }
    
    
    /*
     *  Set description of user
     */
    public LoginUser setUserDescription(String userDescr) throws Exception{
    	
    	// set User name
 	   driver.findElement(editUserDescrLocator).clear();
 	   driver.findElement(editUserDescrLocator).sendKeys(userDescr);
    	
    	return  this;
    }
    
    /*
     *  Set description of user
     */
    public LoginUser saveUserSettings() throws Exception{
    	
    	// set User name
 	   driver.findElement(editUserSaveLocator).click();
    	
    	return  this;
    }
    
    /*
     *  Check profile of user
     */
    public boolean checkUserProfile(String userProfile) throws Exception{
    	
    	return driver.findElement(editUserProfileLocator).getAttribute("value").equalsIgnoreCase(userProfile);

    }
    
    /*
     *  Check name of user
     */
    public boolean checkUserName(String userName) throws Exception{
    	
    	return driver.findElement(editUserLoginlocator).getAttribute("value").equalsIgnoreCase(userName);

    }
    
    /*
     *  Check description of user
     */
    public boolean checkUserDescription(String userDescription) throws Exception{
    	
    	return driver.findElement(editUserDescrLocator).getAttribute("value").equalsIgnoreCase(userDescription);

    }
}