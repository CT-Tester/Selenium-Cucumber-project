package main;

import java.io.File;
//import java.io.File;
//import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;//Selenium Grid tutorial (http://www.guru99.com/introduction-to-selenium-grid.html)
import org.openqa.selenium.remote.RemoteWebDriver;//Selenium Grid tutorial (http://www.guru99.com/introduction-to-selenium-grid.html)
import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class WebDriverSettings {
	
	//Getter and setters of myBrowser and isDebug parameters. These values are defined in the xml test suite file.
    private String myBrowser;
    public String getMyBrowser() {
    	return myBrowser;
	}

    public void setMyBrowser(String myBrowser) {
    	this.myBrowser = myBrowser;
	}
    private String isDebug;
    public String getIsDebug() {
    	return isDebug;
	}

    public void setIsDebug(String isDebug) {
    	this.isDebug = isDebug;
	}

    @BeforeTest
    @Parameters({"myBrowser", "isDebug"})
    public void beforeClass(String myBrowser, String isDebug){
		this.myBrowser = myBrowser;
		this.isDebug = isDebug;
		onBeforeClass();
    };
    
    //Getter and setter of the Remote WebDriver
    public WebDriver getDriver() {
        return dr.get();
    }
 
    public void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }
    //initialValue is the procedure to initialize the webdriver based on myBrowser, isDebug values and the capabilities.
    //IMPORTANT NOTE: the RemoteWebdriver is initialized based on the isDebug parameter. In case it is set, the webdriver will start using the my machine as a Grid Hub
    //In case it is not, it will look for the remote grid hub.(Due I don´t know it, I set "DNS" in the url). I use Selenium Grid to use these tests in a continuous integration system (Jenkins, Azure).
    //public ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>() {
    public ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>() {
    	public RemoteWebDriver initialValue() {
    	//protected WebDriver initialValue() {	
    		RemoteWebDriver driver = null;
    		boolean checkDriver = false;
    		//Due to an unknown problem that doesn´t allow me to use the selenium grid (¿?), I had to use a webdriver instead of a RemoteWebdriver to complete the task.
    		//WebDriver driver = null;
    		try {
	            if(getMyBrowser().equals("chrome")){
	            	
	            	if (getIsDebug().equals("Yes"))
	            	{	//Driver for Chrome v105.0.5195.102(chrome driver v105.0.5195.52) and Windows operative system
	            		System.setProperty("webdriver.chrome.driver",".\\Resources\\Win_chrome_driver\\chromedriver.exe");
	            		try {
	            			checkDriver = new File(".\\Resources\\Win_chrome_driver\\chromedriver.exe").isFile();
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
	            		if (checkDriver==false)
	            			System.out.println("ChromeDriver doesn´t  exist");
	            	}
	            	else
	            		//Driver for Chrome v105.0.5195.102 (chrome driver v105.0.5195.52) and Linux operative system
	            		System.setProperty("webdriver.chrome.driver",".\\Resources\\Linux_chrome_driver\\chromedriver.exe");
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
	                if (getIsDebug().equals("Yes")) //I am debugging the code in my computer, that is a windows machine.
	                	options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10);
	                else //We are running the code in a remote Selenium Grid network, that is a linux machine.
	                	options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
	                
	                /*************************************************/
	                //options.addArguments("--proxy-server='direct://'");
	                //options.addArguments("--proxy-bypass-list=*");
	                /*************************************************/
	                options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
	                options.addArguments("--disable-impl-side-painting", "--enable-gpu-rasterization", "--force-gpu-rasterization", "--whitelist-ips=\"\"");
	                
	                if (getIsDebug().equals("Yes"))
	                	//This line needs to be activated in case you want to use a local Selenium Grid
	                	/************************************************************************************/
		                driver = new RemoteWebDriver(new URL("http://MDIZ04135.Euro.intl.cigna.com:3000/wd/hub"), options);
	                else
	                	driver = new RemoteWebDriver(new URL("http://DNS:3000/wd/hub"), options);
	                //driver = new ChromeDriver();	
	            }
	            else if(getMyBrowser().equals("firefox")){

	            	if (getIsDebug().equals("Yes"))
	            		//Driver for Chrome v105.0.5195.102(chrome driver v105.0.5195.52) and Windows operative system
	            		System.setProperty("webdriver.gecko.driver",".\\Resources\\Win_geckodriver\\geckodriver.exe");
	            	else
	            		//Driver for Chrome v105.0.5195.102 (chrome driver v105.0.5195.52) and Linux operative system
	            		System.setProperty("webdriver.gecko.driver",".\\Resources\\Linux_geckodriver\\geckodriver.exe");
	            	
	            	FirefoxOptions options = new FirefoxOptions();
	            	options.setCapability("marionette", true);
	            	//Platform capability should not be set to windows in case we use the Enterprise Selenium Grid due all the nodes uses linux os.
	            	if (getIsDebug().equals("Yes"))
	            		options.setCapability("Platform", Platform.WIN10);
	            	else //We are running the code in a remote Selenium Grid network, that is a linux machine.
	            		options.setCapability("Platform", Platform.LINUX);
	            	//These capabilities are set to true to prevent the certificate warning pop up messages
	            	options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
	                
	                if (getIsDebug().equals("Yes"))
	                	//This line needs to be activated in case you want to use a local Selenium Grid
	                	/************************************************************************************/
	                	driver = new RemoteWebDriver(new URL("http://MDIZ04135.Euro.intl.cigna.com:3000/wd/hub"), options);
	                else
		                driver = new RemoteWebDriver(new URL("http://DNS:3000/wd/hub"), options);
	                //driver = new FirefoxDriver();
	            }
	            else if(getMyBrowser().equals("internet explorer")){
	            	
	            	InternetExplorerOptions options = new InternetExplorerOptions();
	            	//Platform capability should not be set to windows in case we use the Enterprise Selenium Grid due all the nodes uses linux os.
	            	if (getIsDebug().equals("Yes"))
	            		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10);
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
	            	
	                if (getIsDebug().equals("Yes"))
	                	//This line needs to be activated in case you want to use a local Selenium Grid
	                	/************************************************************************************/
	                	driver = new RemoteWebDriver(new URL("http://MDIZ04135.Euro.intl.cigna.com:3000/wd/hub"), options);
	                else
	                	driver = new RemoteWebDriver(new URL("http://DNS:3000/wd/hub"), options);
	                
	                //driver = new InternetExplorerDriver();
		        }
    		} catch (Exception e){
    			e.printStackTrace();
    			throw new AssertionError("Cannot create web driver");
    		}
    		
    		//System.out.println("Video URL: " + videoURL + driver.getSessionId().toString());

            return driver;
    	};
    };
    
    /*@BeforeClass
    public void BeforeClass(){
    	if (this.getIsDebug().equals("Yes"))
    	{
    		try {
				Runtime.getRuntime().exec(".//Resources//Hub_start.bat", null, new File(".//Additional_project_files"));
				Runtime.getRuntime().exec(".//Resources//Node_start.bat", null, new File(".//Additional_project_files"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }*/
    
    @AfterClass
    public void afterClass(){
    	onAfterClass();
    	try{
    		getDriver().close();
    	}catch (Exception e){
    	//if (this.myBrowser.equals("chrome"))
    		getDriver().quit();
    	}
        dr.set(null);
        
        
 
    };
    protected void onAfterClass() {}
    protected void onBeforeClass() {}
}
