package Pages;

import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class CSRLoginpage {
 
        private static WebElement element = null;
        static ExtentReports reports;
      // ExtentTest test;
 
    public static WebElement txtbx_UserName(WebDriver driver){
 
         element = driver.findElement(By.name("loginRequestBean.userId"));
 
         return element;
 
         }
 
     public static WebElement txtbx_PIN(WebDriver driver){
 
         element = driver.findElement(By.name("loginRequestBean.password"));
 
         return element;
 
         }
     
     public static WebElement txtbx_StoreId(WebDriver driver){
    	 
         element = driver.findElement(By.name("loginRequestBean.locNbr"));
 
         return element;
 
         }
 
     public static WebElement btn_LogIn(WebDriver driver){
 
         element = driver.findElement(By.name("login"));
 
         return element;
 
         }
 	public void Login(String username,String password,String storenumber,WebDriver driver,String appUrl, ExtentTest test){	
 		 	  
 		driver.get(appUrl);		
		driver.manage().window().maximize();
		//Launch URL
		test.log(LogStatus.INFO, "Application is launched");
		CSRLoginpage.txtbx_UserName(driver).sendKeys(username);		  	  
        test.log(LogStatus.PASS, "Username is entered: "+username);
        //Enter Password	
        CSRLoginpage.txtbx_PIN(driver).clear(); 
        CSRLoginpage.txtbx_PIN(driver).sendKeys(password);	    
        test.log(LogStatus.PASS, "Password is entered: "+password);	 
        CSRLoginpage.txtbx_StoreId(driver).sendKeys(storenumber);       
        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
        //Click Login Button
        CSRLoginpage.btn_LogIn(driver).click();       
        test.log(LogStatus.PASS, "Clicked on Submit button");
	}
 
}
