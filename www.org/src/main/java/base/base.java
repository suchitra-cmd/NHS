package base;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	
	@BeforeSuite
	public void setUp()
		{
	     prop=new Properties();
		try
		{
			prop.load(new FileInputStream("src/main/java/config/config.properties"));
		}
		catch(Exception ex)
		{}
		
		
			if(prop.getProperty("browsername").matches("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(prop.getProperty("browsername").matches("chrome"))
			{
				ChromeOptions co=new ChromeOptions();
	    		String[] s=new String[] {"enable-automation"};
	    		co.setExperimentalOption("excludeSwitches",s);
	    		co.addArguments("--disable-notifications");
	    		//chrome_opt = webdriver.ChromeOptions()
	    		Map<String, Object> prefs = new HashMap<String, Object>();
	    		prefs.put("credentials_enable_service", false);
	    		prefs.put("profile.password_manager_enabled", false);

	    		co.setExperimentalOption("prefs", prefs);

	    		
	    		WebDriverManager.chromedriver().setup();
	    		
	    		driver=new ChromeDriver(co);
				
				
			}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    
   	wait=new WebDriverWait(driver, 120);
   	
		}

	@AfterSuite
		public void close()
		{
		
		//driver.close();
		}
	
}

