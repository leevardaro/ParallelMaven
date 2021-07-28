package MBS.MavenBS;



	import java.net.MalformedURLException;
	import java.net.URL;

	import java.lang.Thread;
	import org.junit.Assert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.Test;

	public class HomeworkTest {

		  public String username = System.getenv("BROWSERSTACK_username");
		  public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		  public static String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
		  public static String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
		  public static String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
		  public String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
		  public static final String baseUrl = "https://jobs.workable.com/";
		  //variables for assertions
		  public static final String expectedTitle = "Job Search - Job Finder - Job Listings | Workable for Job Seekers";
		  public static final String expJob = "Customer Engineer- San Francisco";
	  @Test
	  public void testMethodsOne() {
	        long id = Thread.currentThread().getId();
	        System.out.println("Simple test-method One. Thread id is: " + id);
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("browserName", "Chrome");
	        caps.setCapability("browser_version", "latest");
	        caps.setCapability("os", "Windows");
	        caps.setCapability("os_version", "10");
	        caps.setCapability("name", "Chrome"); 
	        caps.setCapability("build", buildName);
	        

	    
	    WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	    JavascriptExecutor jse = (JavascriptExecutor)driver;

			try {

	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        // launch browsers on job search site
	        driver.get(baseUrl);
	        String actualTitle = driver.getTitle();
	        
	        //assertion 1 
	        Assert.assertEquals(expectedTitle, actualTitle);
	    
	        //input job
	        WebElement jobSearch = driver.findElement(By.xpath("//*[@data-ui=\'search-input\']"));
	        jobSearch.sendKeys("browserstack customer experience engineer");
	        //input location and submit
	        WebElement locSearch = driver.findElement(By.xpath("//*[@data-ui=\'location-input\']"));
	        locSearch.sendKeys("San Francisco");
	        locSearch.submit();
	        // get rid of cookie bar
	        WebElement declineLink = driver.findElement(By.xpath("//*[@id='hs-eu-decline-button']"));
	        declineLink.click();
	        //click on job
	        WebElement jobLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[.='Customer Engineer- San Francisco']/..//a[@data-ui='job-view']")));
	        Boolean isPresent = driver.findElements(By.xpath("//h3[.='Customer Engineer- San Francisco']")).size() > 0;

	        //assertion 2
	        Assert.assertTrue(isPresent);
	        
	        jobLink.click();
	        //verify job
	        WebElement jobTitle = driver.findElement(By.xpath("//a[@href='http://www.browserstack.com']/../h1[@data-ui=\"preview-job-title\"]"));
	        String actualJob = jobTitle.getText();
	        
	        //assertion 3 passes test	    
		    Assert.assertEquals(actualJob, expJob);
		    

		    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Correct Job!\"}}");
		    
		    //close driver
		    driver.quit();
			}
			catch (Exception e) {
				e.printStackTrace();
				jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed!\"}}");
			    
			    //close driver
			    driver.quit();	
			}
			}
		
	  @Test
	public void testMethodsTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Two. Thread id is: " + id);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "FireFox");
        caps.setCapability("browser_version", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("name", "FireFox"); 
        caps.setCapability("build", buildName);
        

    
    WebDriver driver = null;
	try {
		driver = new RemoteWebDriver(new URL(URL), caps);
	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	}
    JavascriptExecutor jse = (JavascriptExecutor)driver;

		try {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        // launch browsers on job search site
        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        
        //assertion 1 
        Assert.assertEquals(expectedTitle, actualTitle);
    
        //input job
        WebElement jobSearch = driver.findElement(By.xpath("//*[@data-ui=\'search-input\']"));
        jobSearch.sendKeys("browserstack customer experience engineer");
        //input location and submit
        WebElement locSearch = driver.findElement(By.xpath("//*[@data-ui=\'location-input\']"));
        locSearch.sendKeys("San Francisco");
        locSearch.submit();
        // get rid of cookie bar
        WebElement declineLink = driver.findElement(By.xpath("//*[@id='hs-eu-decline-button']"));
        declineLink.click();
        //click on job
        WebElement jobLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[.='Customer Engineer- San Francisco']/..//a[@data-ui='job-view']")));
        Boolean isPresent = driver.findElements(By.xpath("//h3[.='Customer Engineer- San Francisco']")).size() > 0;

        //assertion 2
        Assert.assertTrue(isPresent);
        
        jobLink.click();
        //verify job
        WebElement jobTitle = driver.findElement(By.xpath("//a[@href='http://www.browserstack.com']/../h1[@data-ui=\"preview-job-title\"]"));
        String actualJob = jobTitle.getText();
        
        //assertion 3 passes test	    
	    Assert.assertEquals(actualJob, expJob);
	    

	    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Correct Job!\"}}");
	    
	    //close driver
	    driver.quit();
		}
		catch (Exception e) {
			e.printStackTrace();
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed!\"}}");
		    
		    //close driver
		    driver.quit();	
		}
		}
	  @Test
	  public void testMethodsThree() {
	        long id = Thread.currentThread().getId();
	        System.out.println("Simple test-method Three. Thread id is: " + id);
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("browserName", "Safari");
	        caps.setCapability("browser_version", "14");
	        caps.setCapability("os", "OS X");
	        caps.setCapability("os_version", "Big Sur");
	        caps.setCapability("name", "Safari"); 
	        caps.setCapability("build", buildName);
	        

	    
	    WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	    JavascriptExecutor jse = (JavascriptExecutor)driver;

			try {

	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        // launch browsers on job search site
	        driver.get(baseUrl);
	        String actualTitle = driver.getTitle();
	        
	        //assertion 1 
	        Assert.assertEquals(expectedTitle, actualTitle);
	    
	        //input job
	        WebElement jobSearch = driver.findElement(By.xpath("//*[@data-ui=\'search-input\']"));
	        jobSearch.sendKeys("browserstack customer experience engineer");
	        //input location and submit
	        WebElement locSearch = driver.findElement(By.xpath("//*[@data-ui=\'location-input\']"));
	        locSearch.sendKeys("San Francisco");
	        locSearch.submit();
	        // get rid of cookie bar
	        WebElement declineLink = driver.findElement(By.xpath("//*[@id='hs-eu-decline-button']"));
	        declineLink.click();
	        //click on job
	        WebElement jobLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[.='Customer Engineer- San Francisco']/..//a[@data-ui='job-view']")));
	        Boolean isPresent = driver.findElements(By.xpath("//h3[.='Customer Engineer- San Francisco']")).size() > 0;

	        //assertion 2
	        Assert.assertTrue(isPresent);
	        
	        jobLink.click();
	        //verify job
	        WebElement jobTitle = driver.findElement(By.xpath("//a[@href='http://www.browserstack.com']/../h1[@data-ui=\"preview-job-title\"]"));
	        String actualJob = jobTitle.getText();
	        
	        //assertion 3 passes test	    
		    Assert.assertEquals(actualJob, expJob);
		    

		    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Correct Job!\"}}");
		    
		    //close driver
		    driver.quit();
			}
			catch (Exception e) {
				e.printStackTrace();
				jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed!\"}}");
			    
			    //close driver
			    driver.quit();	
			}
			}
		
	  @Test
	public void testMethodsFour() {
      long id = Thread.currentThread().getId();
      System.out.println("Simple test-method Four. Thread id is: " + id);
      DesiredCapabilities caps = new DesiredCapabilities();
      caps.setCapability("browserName", "Edge");
      caps.setCapability("browser_version", "latest");
      caps.setCapability("os", "Windows");
      caps.setCapability("os_version", "10");
      caps.setCapability("name", "Edge"); 
      caps.setCapability("build", buildName);
      

  
  WebDriver driver = null;
	try {
		driver = new RemoteWebDriver(new URL(URL), caps);
	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	}
  JavascriptExecutor jse = (JavascriptExecutor)driver;

		try {

      WebDriverWait wait = new WebDriverWait(driver, 10);
      // launch browsers on job search site
      driver.get(baseUrl);
      String actualTitle = driver.getTitle();
      
      //assertion 1 
      Assert.assertEquals(expectedTitle, actualTitle);
  
      //input job
      WebElement jobSearch = driver.findElement(By.xpath("//*[@data-ui=\'search-input\']"));
      jobSearch.sendKeys("browserstack customer experience engineer");
      //input location and submit
      WebElement locSearch = driver.findElement(By.xpath("//*[@data-ui=\'location-input\']"));
      locSearch.sendKeys("San Francisco");
      locSearch.submit();
      // get rid of cookie bar
      WebElement declineLink = driver.findElement(By.xpath("//*[@id='hs-eu-decline-button']"));
      declineLink.click();
      //click on job
      WebElement jobLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[.='Customer Engineer- San Francisco']/..//a[@data-ui='job-view']")));
      Boolean isPresent = driver.findElements(By.xpath("//h3[.='Customer Engineer- San Francisco']")).size() > 0;

      //assertion 2
      Assert.assertTrue(isPresent);
      
      jobLink.click();
      //verify job
      WebElement jobTitle = driver.findElement(By.xpath("//a[@href='http://www.browserstack.com']/../h1[@data-ui=\"preview-job-title\"]"));
      String actualJob = jobTitle.getText();
      
      //assertion 3 passes test	    
	    Assert.assertEquals(actualJob, expJob);
	    

	    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Correct Job!\"}}");
	    
	    //close driver
	    driver.quit();
		}
		catch (Exception e) {
			e.printStackTrace();
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed!\"}}");
		    
		    //close driver
		    driver.quit();	
		}
		}
	  @Test
	public void testMethodsFive() {
      long id = Thread.currentThread().getId();
      System.out.println("Simple test-method Five. Thread id is: " + id);
      DesiredCapabilities caps = new DesiredCapabilities();
      caps.setCapability("device", "iPhone 12 Pro");
      caps.setCapability("real_mobile", "true");
      caps.setCapability("os_version", "14");
      caps.setCapability("name", "iPhone"); 
      caps.setCapability("build", buildName);
      

  
  WebDriver driver = null;
	try {
		driver = new RemoteWebDriver(new URL(URL), caps);
	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	}
  JavascriptExecutor jse = (JavascriptExecutor)driver;

		try {

      WebDriverWait wait = new WebDriverWait(driver, 10);
      // launch browsers on job search site
      driver.get(baseUrl);
      String actualTitle = driver.getTitle();
      
      //assertion 1 
      Assert.assertEquals(expectedTitle, actualTitle);
  
      //input job
      WebElement jobSearch = driver.findElement(By.xpath("//*[@data-ui=\'search-input\']"));
      jobSearch.sendKeys("browserstack customer experience engineer");
      //input location and submit
      WebElement locSearch = driver.findElement(By.xpath("//*[@data-ui=\'location-input\']"));
      locSearch.sendKeys("San Francisco");
      locSearch.submit();
      // get rid of cookie bar
      WebElement declineLink = driver.findElement(By.xpath("//*[@id='hs-eu-decline-button']"));
      declineLink.click();
      //click on job
      WebElement jobLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[.='Customer Engineer- San Francisco']/..//a[@data-ui='job-view']")));
      Boolean isPresent = driver.findElements(By.xpath("//h3[.='Customer Engineer- San Francisco']")).size() > 0;

      //assertion 2
      Assert.assertTrue(isPresent);
      
      jobLink.click();
      //verify job
      WebElement jobTitle = driver.findElement(By.xpath("//a[@href='http://www.browserstack.com']/../h1[@data-ui=\"preview-job-title\"]"));
      String actualJob = jobTitle.getText();
      
      //assertion 3 passes test	    
	    Assert.assertEquals(actualJob, expJob);
	    

	    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Correct Job!\"}}");
	    
	    //close driver
	    driver.quit();
		}
		catch (Exception e) {
			e.printStackTrace();
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed!\"}}");
		    
		    //close driver
		    driver.quit();	
		}
		}
	}
	
