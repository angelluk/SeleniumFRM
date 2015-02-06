import org.openqa.selenium.WebDriver;


public class HomePage {

	 private  WebDriver driver;

	    public  HomePage(WebDriver driver) {
	        this.driver = driver;

	        // Check that we're on the right page.
	        if (!"Login".equals(driver.getTitle())) {
	            // Alternatively, we could navigate to the login page, perhaps logging out first
	            throw new IllegalStateException("This is not the login page");
	        }
	    }
}
