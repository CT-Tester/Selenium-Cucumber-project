package runners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
//import cucumber.deps.com.thoughtworks.xstream.io.path.Path;
//import cucumber.runtime.model.CucumberFeature;
//import cucumber.runtime.model.CucumberTagStatement;
//import gherkin.formatter.model.Feature;
//import gherkin.formatter.model.Step;
//import gherkin.formatter.model.Tag;
import main.WebDriverSettings;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		//features = {"src/test/resources/functionalTests"},
		features = {"src/test/resources/FeatureFiles/Search_Wikipedia_TestNg.feature"},
		//features = {"src/test/resources/FeatureFiles_Feature/LoginCWB_Feature.feature"},
		glue= {"stepDefinitions"},
		plugin = { "pretty", "html:Test_report", "json:target/cucumber-report/cucumber.json"},
		monochrome = true
		)
public class TestRunnerTestNg extends AbstractTestNGCucumberTests{

	public TestNGCucumberRunner testNGCucumberRunner;
	
	/*public ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>(); 
    public static RemoteWebDriver driver;*/
	public ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>(); 
    public static WebDriver driver;
	Runtime rt = Runtime.getRuntime();
    public Process proc;
    protected WebDriverSettings driverRunner;
    
    
    public String isDebug;
    public String getIsDebug() {
    	return isDebug;
	}

    public void setIsDebug(String isDebug) {
    	this.isDebug = isDebug;
	}
    
    public String myBrowser;
    public String getMyBrowser() {
    	return myBrowser;
	}

    public void setMyBrowser(String myBrowser) {
    	this.myBrowser = myBrowser;
	}
    
    public String myUserName;
    public String getMyUserName() {
    	return myUserName;
	}

    public void setMyUserName(String myUserName) {
    	this.myUserName = myUserName;
	}
    
    public String myPassword;
    public String getMyPassword() {
    	return myPassword;
	}

    public void setMyPassword(String myPassword) {
    	this.myPassword = myPassword;
	}

    
    @BeforeMethod(alwaysRun = true)
    @Parameters({"isDebug", "myBrowser", "myUserName", "myPassword"})
    public void setUpClass(String isDebug, String myBrowser, String myUserName, String myPassword) throws Exception {

		driver = null;
		//RemoteWebDriver driver = null;
		boolean checkDriver = false;
		//Due to an unknown problem that doesn´t allow me to use the selenium grid (¿?), I had to use a webdriver instead of a RemoteWebdriver to complete the task.
		//WebDriver driver = null;
		try {
            if(myBrowser.equals("chrome")){
            	
            	if (isDebug.equals("Yes"))
            	{	//Driver for Chrome v106.0.5249.119(chrome driver v106.0.5249.61) and Windows operative system
            		System.setProperty("webdriver.chrome.driver",".\\src\\test\\resources\\Win_chrome_driver\\chromedriver.exe");
            		try {
            			checkDriver = new File(".\\src\\test\\resources\\Win_chrome_driver\\chromedriver.exe").isFile();
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
            		if (checkDriver==false)
            			System.out.println("ChromeDriver doesn´t  exist");
            	}
            	else
            		//Driver for Chrome v105.0.5195.102 (chrome driver v105.0.5195.52) and Linux operative system
            		System.setProperty("webdriver.chrome.driver",".\\src\\test\\resources\\Linux_chrome_driver\\chromedriver.exe");
            	//new DesiredCapabilities();
            	System.out.println(System.getProperties().values().toString());
                ChromeOptions options = new ChromeOptions();
                
                options.setCapability("network", true);
                options.setCapability("visual", true);
                options.setCapability("video", true);
                options.setCapability("console", true);
                
                
                //These capabilities are set to true to prevent the certificate warning pop up messages
                options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                
                //Platform capability should not be set to windows in case we use the Enterprise Selenium Grid due all the nodes uses linux os.
                if (isDebug.equals("Yes")&&System.getProperty("os.name").toUpperCase().equals("WIN10"))
            		options.setCapability("Platform", Platform.WIN10);
            	if (isDebug.equals("Yes")&&(System.getProperty("os.name").toUpperCase().equals("VISTA")||System.getProperty("os.name").toUpperCase().equals("WINDOWS 7")))
            		options.setCapability("Platform", Platform.VISTA);
                else //We are running the code in a remote Selenium Grid network, that is a linux machine.
                	options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
                
                /*************************************************/
                //options.addArguments("--proxy-server='direct://'");
                //options.addArguments("--proxy-bypass-list=*");
                /*************************************************/
                options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                options.addArguments("--disable-impl-side-painting", "--enable-gpu-rasterization", "--force-gpu-rasterization", "--whitelist-ips=\"\"");
                
                /*if (isDebug.equals("Yes"))
                	//This line needs to be activated in case you want to use a local Selenium Grid
                	/************************************************************************************/
	                //driver = new RemoteWebDriver(new URL("http://localhost:3000/wd/hub"), options);
                /*else
                	driver = new RemoteWebDriver(new URL("http://DNS:3000/wd/hub"), options);*/
                driver = new ChromeDriver();	
            }
            else if(myBrowser.equals("firefox")){

            	if (isDebug.equals("Yes"))
            		//Driver for Chrome v105.0.5195.102(chrome driver v105.0.5195.52) and Windows operative system
            		System.setProperty("webdriver.gecko.driver",".\\src\\test\\resources\\Win_geckodriver\\geckodriver.exe");
            	else
            		//Driver for Chrome v105.0.5195.102 (chrome driver v105.0.5195.52) and Linux operative system
            		System.setProperty("webdriver.gecko.driver",".\\src\\test\\resources\\Linux_geckodriver\\geckodriver.exe");
            	
            	FirefoxOptions options = new FirefoxOptions();
            	options.setCapability("marionette", true);
            	//Platform capability should not be set to windows in case we use the Enterprise Selenium Grid due all the nodes uses linux os.
            	if (isDebug.equals("Yes")&&System.getProperty("os.name").toUpperCase().equals("WIN10"))
            		options.setCapability("Platform", Platform.WIN10);
            	if (isDebug.equals("Yes")&&(System.getProperty("os.name").toUpperCase().equals("VISTA")||System.getProperty("os.name").toUpperCase().equals("WINDOWS 7")))
            		options.setCapability("Platform", Platform.VISTA);
            	else //We are running the code in a remote Selenium Grid network, that is a linux machine.
            		options.setCapability("Platform", Platform.LINUX);
            	//These capabilities are set to true to prevent the certificate warning pop up messages
            	options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                
                /*if (isDebug.equals("Yes"))
                	//This line needs to be activated in case you want to use a local Selenium Grid
                	/************************************************************************************/
                	//driver = new RemoteWebDriver(new URL("http://localhost:3000/wd/hub"), options);
                /*else
	                driver = new RemoteWebDriver(new URL("http://DNS:3000/wd/hub"), options);*/
                driver = new FirefoxDriver();
            }
            else if(myBrowser.equals("internet explorer")){
            	
            	InternetExplorerOptions options = new InternetExplorerOptions();
            	//Platform capability should not be set to windows in case we use the Enterprise Selenium Grid due all the nodes uses linux os.
            	if (isDebug.equals("Yes")&&System.getProperty("os.name").toUpperCase().equals("WIN10"))
            		options.setCapability("Platform", Platform.WIN10);
            	if (isDebug.equals("Yes")&&(System.getProperty("os.name").toUpperCase().equals("VISTA")||System.getProperty("os.name").toUpperCase().equals("WINDOWS 7")))
            		options.setCapability("Platform", Platform.VISTA);
            	else //We are running the code in a remote Selenium Grid network, that is a linux machine.
            		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
                options.setCapability("browserName", "Internet Explorer");
                options.setCapability("browserVersion", "11.0");
                options.setCapability("nativeEvents", false);    
	            options.setCapability("unexpectedAlertBehaviour", "accept");
	            options.setCapability("ignoreProtectedModeSettings", true);
	            options.setCapability("disable-popup-blocking", true);
	            options.setCapability("enablePersistentHover", true);
	            //These capabilities are set to true to prevent the certificate warning pop up messages
	            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
            	
                /*if (isDebug.equals("Yes"))
                	//This line needs to be activated in case you want to use a local Selenium Grid
                	/************************************************************************************/
                	//driver = new RemoteWebDriver(new URL("http://localhost:3000/wd/hub"), options);
                /*else
                	driver = new RemoteWebDriver(new URL("http://DNS:3000/wd/hub"), options);*/
                driver = new InternetExplorerDriver();
	        }
			
		} catch (Exception e){
			e.printStackTrace();
    		throw new AssertionError("Cannot create Remote Webdriver driver");
		}
		//return driver;
    }
    
    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
         testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    //public void feature(CucumberFeatureWrapper cucumberFeature, String myFeatureFile) throws IOException {
    public void feature(CucumberFeatureWrapper cucumberFeature) {
    	
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }
 
    @AfterSuite(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
        Boolean fileClosed = false;
        
        //First, I check the file ./target/cucumber-report/cucumber.json is closed.
        FileOutputStream jsonFile = null; //".//target//cucumber-report//Cucumber.json"
        while (fileClosed==false)
        {
        	try {
        		// Make sure that the output stream is in Append mode. Otherwise you will
        		// truncate your file, which probably isn't what you want to do :-) 
        		jsonFile = new FileOutputStream(".//target//cucumber-report//Cucumber.json", true);
        		// -> file was closed
        	} catch(Exception e) {
        		e.printStackTrace();
        		fileClosed = true;
        	} finally {
        		if(jsonFile != null) {
        			try {
        				jsonFile.close();
        				fileClosed = true;
        			} catch (Exception e) {
        				e.printStackTrace();
        				fileClosed = true;
        			}
        		
        		}
        	}
        }
        
    	//Second, I execute maven command to generate the test report.
    	Process p=Runtime.getRuntime().exec("cmd.exe /c mvn cluecumber-report:reporting");
    	p.waitFor();
    	Integer exitValue = p.exitValue();
    	if (exitValue == 1){
    		String linee;
    		InputStream stackTrace= p.getErrorStream();
    		InputStreamReader isrerror = new InputStreamReader(stackTrace);
    		BufferedReader bre = new BufferedReader(isrerror);
    		while ((linee = bre.readLine()) != null) {
    			System.out.println(linee);
    		}
    	}
    }
    
    @AfterTest(alwaysRun = true)
    public void tearApp() throws Exception{
    	try{
    		driver.close();
    	} catch (Exception e){
    		driver.quit(); //This is done to avoid the timeout errors after the test is completed, due the app is not closed.
    	}
    }
}