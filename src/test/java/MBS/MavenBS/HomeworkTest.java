package MBS.MavenBS;


	import java.time.Duration;
	import java.util.List;
	import java.util.Hashtable;
	import java.util.Iterator;
	import java.net.MalformedURLException;
	import java.net.URL;

	import java.lang.Thread;
	import java.util.Hashtable;

	import com.mashape.unirest.http.Unirest;
	import org.junit.Assert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.remote.SessionId;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.*;

	public class HomeworkTest {

		  public String username = System.getenv("BROWSERSTACK_username");
		  public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		  public static String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
		  public static String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
		  public static String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
		  public String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
		  public static final String baseUrl = "https://www.google.com";
		
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
			  JavascriptExecutor jse = (JavascriptExecutor) driver;

		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  driver.get(baseUrl);
		  WebElement element = driver.findElement(By.name("q"));
		  element.sendKeys("BrowserStack");
		  element.submit();
		  // Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page contains 'BrowserStack'
		  SessionId session = ((RemoteWebDriver) driver).getSessionId();
		  String sID = session.toString();
		  System.out.println(driver.getTitle());
		  try {
			  wait.until(ExpectedConditions.titleContains("BrowserStack"));


			  String putEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";
			  System.out.println(putEndpoint);

			  System.out.println(putEndpoint);

			  Unirest.setTimeouts(0, 0);
			  String response = Unirest.put(putEndpoint)
					  .header("Content-Type", "application/json")
					  .body("{\n    \"status\":\"passed\",\n    \"reason\":\"it's done\"\n}")
					  .asString()
					  .getBody();
			  System.out.println(response);



			  //jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		  } catch (Exception e) {
			  jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		  }


		  driver.quit();

		  try {

			  String logEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + "/logs";
			  Unirest.setTimeouts(0, 0);
			  String response2 = Unirest.get(logEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();



			  System.out.println(response2);
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  try {
			  String getEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";
			  String response3 = Unirest.get(getEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();
			  System.out.println(response3);
		  } catch (Exception e) {
			  e.printStackTrace();
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

		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  // launch browsers on job search site
		  driver.get(baseUrl);
		  WebElement element = driver.findElement(By.name("q"));
		  element.sendKeys("BrowserStack");
		  element.submit();
		  SessionId session = ((RemoteWebDriver) driver).getSessionId();
		  String sID = session.toString();
		  System.out.println(driver.getTitle());
		  try {
			  wait.until(ExpectedConditions.titleContains("BrowserStack"));


			  String putEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";


			  System.out.println(putEndpoint);

			  Unirest.setTimeouts(0, 0);
			  String response = Unirest.put(putEndpoint)
					  .header("Content-Type", "application/json")
					  .body("{\n    \"status\":\"passed\",\n    \"reason\":\"it's done\"\n}")
					  .asString()
					  .getBody();
			  System.out.println(response);



			  //jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		  } catch (Exception e) {
			  jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		  }


		  driver.quit();

		  try {

			  String logEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + "/logs";
			  Unirest.setTimeouts(0, 0);
			  String response2 = Unirest.get(logEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();



			  System.out.println(response2);
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  try {
			  String getEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";
			  String response3 = Unirest.get(getEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();
			  System.out.println(response3);
		  } catch (Exception e) {
			  e.printStackTrace();
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

		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  driver.get(baseUrl);
		  WebElement element = driver.findElement(By.name("q"));
		  element.sendKeys("BrowserStack");
		  element.submit();
		  // Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page contains 'BrowserStack'
		  SessionId session = ((RemoteWebDriver) driver).getSessionId();
		  String sID = session.toString();
		  System.out.println(driver.getTitle());
		  try {
			  wait.until(ExpectedConditions.titleContains("BrowserStack"));


			  String putEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";


			  System.out.println(putEndpoint);

			  Unirest.setTimeouts(0, 0);
			  String response = Unirest.put(putEndpoint)
					  .header("Content-Type", "application/json")
					  .body("{\n    \"status\":\"passed\",\n    \"reason\":\"it's done\"\n}")
					  .asString()
					  .getBody();
			  System.out.println(response);



			  //jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		  } catch (Exception e) {
			  jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		  }


		  driver.quit();

		  try {

			  String logEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + "/logs";
			  Unirest.setTimeouts(0, 0);
			  String response2 = Unirest.get(logEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();



			  System.out.println(response2);
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  try {
			  String getEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";
			  String response3 = Unirest.get(getEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();
			  System.out.println(response3);
		  } catch (Exception e) {
			  e.printStackTrace();
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

		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  driver.get(baseUrl);
		  WebElement element = driver.findElement(By.name("q"));
		  element.sendKeys("BrowserStack");
		  element.submit();
		  SessionId session = ((RemoteWebDriver) driver).getSessionId();
		  String sID = session.toString();
		  System.out.println(driver.getTitle());
		  try {
			  wait.until(ExpectedConditions.titleContains("BrowserStack"));


			  String putEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";


			  System.out.println(putEndpoint);

			  Unirest.setTimeouts(0, 0);
			  String response = Unirest.put(putEndpoint)
					  .header("Content-Type", "application/json")
					  .body("{\n    \"status\":\"passed\",\n    \"reason\":\"it's done\"\n}")
					  .asString()
					  .getBody();
			  System.out.println(response);



			  //jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		  } catch (Exception e) {
			  jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		  }


		  driver.quit();

		  try {

			  String logEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + "/logs";
			  Unirest.setTimeouts(0, 0);
			  String response2 = Unirest.get(logEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();



			  System.out.println(response2);
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  try {
			  String getEndpoint = "https://" + username + ":" + accessKey + "@api.browserstack.com/automate/sessions/" + sID + ".json";
			  String response3 = Unirest.get(getEndpoint)
					  .header("Cookie", "tracking_id=63b7f9d2-5ad3-43bd-807b-7453d1bafdbc")
					  .asString()
					  .getBody();
			  System.out.println(response3);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }

	}
	
