package stepDefinitions;

import runners.TestRunnerTestNg;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;


import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import enums.DesktopXpath;
//import enums.AndroidXpath;

@StepDefAnnotation

public class CWB_Definitions extends TestRunnerTestNg {

	WebDriver driverMob=TestRunnerTestNg.driver;
	String platformName =this.myBrowser;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	private Scenario scenario;

	@Given ("^I start a \"([^\"]*)\" sesion and debug environment is set to \"([^\"]*)\"$")
	public void startApp(String myBrowser, String isDebug) throws Exception
	{
		this.setMyBrowser(myBrowser);
		this.setIsDebug(isDebug);
		driverMob.manage().window().maximize();
	}
	
	@When ("^I wait for \"([^\"]*)\" screen to load$")
	public void waitForElement(String pageStr){
	
		WebDriverWait wait = new WebDriverWait(driverMob, Duration.ofSeconds(60));
		String elementXpath ="", url ="";
		switch (pageStr) 
		{
			case "Home":
				elementXpath = DesktopXpath.Desktop_my_account.label;
				url="https://www.amazon.es/";
			break;
			case "Username":
				elementXpath = DesktopXpath.Desktop_name.label;
			break;
			case "Password":
				elementXpath = DesktopXpath.Desktop_password.label;
			break;
			case "Item_selected":
				elementXpath = DesktopXpath.Desktop_item_description.label;
			break;
			case "Home_Google":
				elementXpath = DesktopXpath.Desktop_home_google.label;
				url="https://www.google.es/";
			break;
			case "Wikipedia":
				elementXpath = DesktopXpath.Desktop_historia_temprana.label;
			break;
		}
		
		if (!url.equals(""))
			driverMob.get(url);
		//Accept cookies in google.es
		List<WebElement> buttonList=null;
		if (pageStr.equals("Home_Google"))
		{
			buttonList=driverMob.findElements(By.xpath("//button"));
			for(WebElement button:buttonList){

				if(button.getAttribute("innerText").matches("Reject all|Rechazar todo?"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(button)).click();
					break;
				}
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
	}
	
	@Then ("^I tap on \"([^\"]*)\"$")
	public void selectBoB(String controlName){
		
		WebDriverWait wait = new WebDriverWait(driverMob, Duration.ofSeconds(10));
		String elementXpath ="";
		switch(controlName){
			case "Desktop_my_account":
				elementXpath = DesktopXpath.Desktop_my_account.label;
			break;
			case "Desktop_search_bar":
				elementXpath = DesktopXpath.Desktop_home_google.label;
			break;
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath))).click();
	}
	
	@Then ("^I write the item name \"([^\"]*)\"$")
	public void setStrToSearch(String itemSTR){
		
		WebDriver localeDriver = this.driverMob;
		WebDriverWait wait = new WebDriverWait(localeDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DesktopXpath.Desktop_home_google.label))).click();
		localeDriver.findElement(By.xpath(DesktopXpath.Desktop_home_google.label)).sendKeys(itemSTR+Keys.ENTER);
	}
	
	@Then ("^I look for the wikipedia entry$")
	public void wikipediaEntry(){
		
		WebDriver localeDriver = this.driverMob;
		WebDriverWait wait = new WebDriverWait(localeDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DesktopXpath.Desktop_wikipedia_link.label))).click();
	}
	
	@Then ("^I look for the year of the first automation process$")
	public void historiaTemprana(){
		
		WebDriver localeDriver = this.driverMob;
		WebDriverWait wait = new WebDriverWait(localeDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DesktopXpath.Desktop_historia_temprana.label)));
		
		//Click on Historia temprana link in the index.
		localeDriver.findElement(By.xpath(DesktopXpath.Desktop_historia_temprana_link.label)).click();
		//scrollToElement(DesktopXpath.Desktop_historia_temprana.label);
	}
	
	@Then ("^I enter \"([^\"]*)\"$")
	public void setPref(String fieldName){
		WebDriverWait wait = new WebDriverWait(driverMob, Duration.ofSeconds(10));
		String elementXpath = "";
		switch (fieldName){
			case "Desktop_my_account":
				elementXpath = DesktopXpath.Desktop_my_account.label;
			break;
		}
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath))).sendKeys(fieldName);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Then ("^I look for the item label \"([^\"]*)\" and click on it$")
	public void clickOnLabel(String itemLabel)
	{
		WebDriver localeDriver = this.driverMob;
		WebDriverWait wait = new WebDriverWait(localeDriver, Duration.ofSeconds(10));
		//Check results of the search
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DesktopXpath.Desktop_search_results.label)));
    	
    	//Select the first item that include in its label the string included in the search and has a valuation of 4.5-5 stars
    	WebElement searchResults = localeDriver.findElement(By.xpath(DesktopXpath.Desktop_search_results.label));
    	List<WebElement> searchItems = searchResults.findElements(By.xpath(DesktopXpath.Desktop_search_item_label.label));

    	for (int i=0; i<searchItems.size(); i++)
    	{
    		if (searchItems.get(i).getAttribute("textContent").toUpperCase().contains(itemLabel))
    		{
    			//Select one item clicking on its label
    			searchItems.get(i).click();
    			break;
    		}
    	}
	}
	@Then ("^I try to include the item into the basket$")
	public void addToBasket()
	{
		WebDriver localeDriver=this.driverMob;
		WebDriverWait wait = new WebDriverWait(localeDriver, Duration.ofSeconds(10));
		
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DesktopXpath.Desktop_add_basket.label))).click();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	@Then ("^I verify \"([^\"]*)\" text is displayed$")
	public void waitForText(String textExpected){

		WebDriverWait wait = new WebDriverWait(driverMob, Duration.ofSeconds(10));
		//mobileOS = driverMob.getCapabilities().getCapability("platformName").toString();
		if (driverMob.findElements(By.xpath("//*[contains(@text, '"+textExpected+"')]")).size()>0)//Xpath in Android
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text, '"+textExpected+"')]")));
		else if (driverMob.findElements(By.xpath("//*[@value='"+textExpected+"']")).size()>0) //Xpath in IOS
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='"+textExpected+"']")));
		//We include these assert sentences to generate an error message in case the text is not displayed
		else if (platformName.equals("Android"))
			assertEquals(driverMob.findElements(By.xpath("//*[contains(@text, '"+textExpected+"')]")).size(), 1);
		else //IOS
			assertEquals(driverMob.findElements(By.xpath("//*[@value='"+textExpected+"']")).size(), 1);
	}
	
	@Then ("^I take one screenshot$")
	public void pageScreenshot(){
		//Scenario scenario = this.testNGCucumberRunner.
		byte[] screenshot = ((TakesScreenshot) driverMob).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}
	/*private void scrollToElement(String xpath){
		WebElement element = driverMob.findElement(By.xpath(xpath));
		Actions actions = new Actions(driverMob);
		actions.moveByOffset(element.getLocation().x, element.getLocation().y-50);
		actions.perform();
	}*/
	@Before
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}
	/*@BeforeMethod
	private void loadTestCapabilities(){
		platformName = this.getMyBrowser();
	}*/
	
    @AfterSuite
    public void AfterTest(){
    	try{
    		//driverMob.closeApp();
    		TestRunnerTestNg.driver.close();
    	} catch(Exception e){
    		TestRunnerTestNg.driver.quit();
    	}
    };
    
}

