package Tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Random;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;

import org.openqa.selenium.By;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.*;
import Utilities.ExtentReports.Excel;
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;

public class Loan_Draw_StGn_Dep_RTN_ReDep_ACHRevoke_checkpostDeporNot {
	
	public WebDriverWait wait;	
	WebDriver driver;
	String appUrl;	
	static ExtentReports reports;
	ExtentTest test;

	@BeforeClass
	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();
				
		String filename="AA_LOC_RegressionScenarios_Scenario.No_315"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/AA_LOC_RegressionScenarios_Scenario.No_315/"+filename, true);
	}
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}

	//@BeforeTest
	/*public void setup() throws IOException {
		System.setProperty("webdriver.ie.driver","E:/QC_Workspace/AA_Automation/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		
		//appUrl = "http://192.168.2.203/cc/demoIndex.do";
	}*/

	//@BeforeTest
	public void Login (String username,String password,String storenumber) {
		
		
						
			//Launch URL
			driver.get(appUrl);
			test.log(LogStatus.INFO, "CSR Application is launched");
			driver.manage().window().maximize();
			String usenameId = "loginRequestBean.userId";
		    String passwordId = "loginRequestBean.password";
		    String StoreId = "loginRequestBean.locNbr";
		    String Login = "login";
		 
		  
		    
		  //Enter Username(Email)
	        //writeText(By.name(usenameId),username);
		    driver.findElement(By.name(usenameId)).sendKeys(username);
	        test.log(LogStatus.PASS, "Username is entered: "+username);

	        //Enter Password
	        //writeText(By.name(passwordId), password);
		    driver.findElement(By.name(passwordId)).clear();
		    driver.findElement(By.name(passwordId)).sendKeys(password);
	        test.log(LogStatus.PASS, "Password is entered: "+password);
	        
	        //writeText(By.name(StoreId), storenumber);
	        driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
	        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
	        //Click Login Button
	        driver.findElement(By.name(Login)).click();
	        test.log(LogStatus.PASS, "Clicked on Submit button");
	}
			
	public void NewLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);		
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{		
				String State = TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
				String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
				String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
				String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
				String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
				String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				String ErrorMsg=null;
				
				
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911100']")).click();			
				test.log(LogStatus.PASS, "Clicked on New Loan");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				
				ErrorMsg = driver.findElement(By.xpath("//*[@id='errMsg']/ul/li[2]")).getText();
				test.log(LogStatus.PASS, "capture the Error message::"+ErrorMsg);
				test.log(LogStatus.PASS, "After Loan Deceased New loan Origination should not be allowed::"+ErrorMsg);
				
				
			
						
			}
		}
					 	
	}
		

	
	
	

	
	public void StatementGeneration(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(ProductID.equals("LOC"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS, "statement generation date Captured::"+DueDate);			
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");


				//Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Unsecure Loc Statement")).click();
				test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");
				
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
				Actions actions1 = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				driver.findElement(By.name("beginMonth")).click();
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");

				///Process Date  
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				
				/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements = driver.findElement(By.linkText("Daily Jobs"));
				Actions actions = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);*/
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				{									        								
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}

				

			}
		}
	}

	
	public void StatementGeneration_2(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(ProductID.equals("LOC"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS, "statement generation date Captured::"+DueDate);			
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");


				//Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Unsecure Loc Statement")).click();
				test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");
				
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));
				Actions actions1 = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				driver.findElement(By.name("beginMonth")).click();
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				
				test.log(LogStatus.PASS, "Second time statement generarted sucessfully");

				

			}
		}
	}


	public void Agestore_StatementGeneration(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(ProductID.equals("LOC"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS, "statement generation date Captured::"+DueDate);			
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				///Process Date  
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements = driver.findElement(By.linkText("Daily Jobs"));
				Actions actions = new Actions(driver);								        
				actions.moveToElement(elements).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				{									        								
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}

				

			}
		}
	}
		
	
	public void AgeStore(String SSN,String FileName,int Days) throws Exception
	{
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");
				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");						
				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				   appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 String DueDate=null;
						 
			   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}
				 
				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
						
								 DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
								 test.log(LogStatus.PASS, "Due date is captured::"+DueDate);	
								//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]	
				   System.out.print(DueDate);	
				   //driver.quit();
				   // Thread.sleep(5000);
				    /*for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}*/
				   driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
				    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				   driver.close();															
				   driver = new InternetExplorerDriver();
				   driver.get(AdminURL);
				  // storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
			//}
		//}
//	}
			
	//public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
			///{
					//DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			        test.log(LogStatus.PASS, "Username is entered: admin");			        
			        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			        //Click Login Button
			        driver.findElement(By.name("login")).click();
			        test.log(LogStatus.PASS, "Clicked on Submit button");
			        Thread.sleep(8000);
			        Thread.sleep(1000);
			        
					  for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
							}
						    driver.switchTo().defaultContent();
						    driver.switchTo().frame("mainFrame");
						    
						    
						    
						   // driver.switchTo().frame("main");
						    Date DDueDate = df.parse(DueDate);
							 Calendar cal = Calendar.getInstance();
							 cal.setTime(DDueDate);
							 cal.add(Calendar.DATE, Days);
							 Date DDueDateminus1= cal.getTime();
							 
							 String DueDateminus1 =df.format(DDueDateminus1);
						    String DueDate0[] =DueDateminus1.split("/");
					        String DueDate1 = DueDate0[0];
					        String DueDate2 = DueDate0[1];
					        String DueDate3 = DueDate0[2];
					        
					        driver.switchTo().defaultContent();
							 driver.switchTo().frame("topFrame");
								driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
								test.log(LogStatus.PASS, "Clicked on Transactions");
					        
					     
					             driver.switchTo().defaultContent();
							      driver.switchTo().frame("mainFrame");
							//Thread.sleep(5000);
							/* driver.findElement(By.linkText("Borrower")).click();
							   test.log(LogStatus.PASS, "Clicked on Borrower");*/
							      
							      driver.findElement(By.linkText("QA Jobs")).click();
								   test.log(LogStatus.PASS, "Clicked on QA Jobs");
							   Thread.sleep(5000);
								driver.findElement(By.linkText("Process Date Change")).click();
								test.log(LogStatus.PASS, "Clicked on Process Date Change");
								
								/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
								driver.switchTo().defaultContent();
								driver.switchTo().frame("mainFrame");
								WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));
								Actions actions1 = new Actions(driver);								        
								actions1.moveToElement(elements1).build().perform();
								driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);*/
								
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 driver.findElement(By.name("storeCode")).click();
								 //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
								 driver.findElement(By.name("storeCode")).sendKeys(StoreID);
								 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
								 driver.findElement(By.name("beginMonth")).clear();
							        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
							        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
							        driver.findElement(By.name("beginDay")).clear();
							        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
							        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
							        driver.findElement(By.name("beginYear")).clear();
							        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
							        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
							        driver.findElement(By.name("btnPreview")).click();
							        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							        Thread.sleep(1000);
							        Thread.sleep(8000);
							        test.log(LogStatus.PASS, "Clicked on submit button");
							        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							        
							        driver.switchTo().defaultContent();
									 driver.switchTo().frame("mainFrame");
									 driver.switchTo().frame("main");
							        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
							        {									        								
							        	test.log(LogStatus.PASS, "Process Date updated successfully");
							        }
							        else
							        {
							        	test.log(LogStatus.FAIL, "Process Date updated successfully.");
							        }
			}
							      
			}
							       						//driver.close();
	}
			
	public void DrawerDeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				Thread.sleep(5000);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Deassign");	
				driver.switchTo().frame("main");		
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Banker PIN# is enetered as"+Password);	
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Click on Finish De-assign Button");
				try{
					driver.close();
				}
				catch (Exception e) {
				}
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);	
				test.log(LogStatus.PASS, "Password is entered");
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer deassign Button");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {
				}
				Thread.sleep(5000);
				
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
				{
					WebElement htmltable=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));	

					List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
					System.out.println("current row num "+rows.size());
					int count=0;							
					count=driver.findElements(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr")).size();				 				
					for(int rnum=1;rnum<rows.size();rnum++)
					{                      
						System.out.println("current row num "+rnum);						

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select")).sendKeys("Delete");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).click();						
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();

						}
						catch (NoAlertPresentException e) {
						}
						Thread.sleep(5000);
					}
				}
				String DrawerOverShortAmount =driver.findElement(By.name("drawerRequestBean.drawerOverShort")).getAttribute("value");
				driver.findElement(By.name("drawerRequestBean.amount")).sendKeys(DrawerOverShortAmount);
				test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);
				driver.findElement(By.name("drawerRequestBean.primary")).sendKeys("Cash Handling");
				test.log(LogStatus.PASS, "Primary Reason is selected as Cash Handling");
				driver.findElement(By.name("drawerRequestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");	
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
				Thread.sleep(3000);
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();

				test.log(LogStatus.PASS, "Click on Finish Drawer O/S");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {
				}
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
				{

					test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
				}
				else
				{
					test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
				}
			}
		}
	}
	public void StatementGeneration_EODProcessing(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				Thread.sleep(5000);	    

				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Daily Processing");
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("eod")).click();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				Thread.sleep(4000);
				// driver.findElement(By.name("requestBean.comments")).click();
				driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
				// requestBean.comments
				Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");
				Thread.sleep(4000);


				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");

				Thread.sleep(1000);
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
				String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

				driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);

				/// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[7]/td[3]

				// driver.findElement(By.name("requestBean.amount")).sendKeys("SafeOverShortAmount");
				test.log(LogStatus.PASS,"Enter the Balance 50");

				driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
				test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
				driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");	
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
				Thread.sleep(3000);

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")).click();
				// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input
				test.log(LogStatus.PASS, "Clicked on Next");


				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}



				Thread.sleep(1000);
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("Next"));
				// Next
				test.log(LogStatus.PASS, "Clicked on Next");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
				test.log(LogStatus.PASS, "Clicked on Next");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
				test.log(LogStatus.PASS, "Clicked on Next");
				driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
				test.log(LogStatus.PASS, "Bag number is provided as 34");
				driver.findElement(By.name("finishdeposit")).click();
				test.log(LogStatus.PASS, "Clicked on Finish Deposit");
				test.log(LogStatus.PASS, "StatmentGeneration EOD Processing Completed");
				Thread.sleep(4000);

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}



			}
		}
	}

				    
	public void EODProcessing(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				Thread.sleep(5000);	    

				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Daily Processing");
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("eod")).click();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				Thread.sleep(4000);
				// driver.findElement(By.name("requestBean.comments")).click();
				driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
				// requestBean.comments
				//Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");



				Thread.sleep(1000);
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("Next"));
				// Next
				test.log(LogStatus.PASS, "Clicked on Next");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
				test.log(LogStatus.PASS, "Clicked on Next");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
				test.log(LogStatus.PASS, "Clicked on Next");
				driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
				test.log(LogStatus.PASS, "Bag number is provided as 34");
				driver.findElement(By.name("finishdeposit")).click();
				test.log(LogStatus.PASS, "Clicked on Finish Deposit");
				test.log(LogStatus.PASS, "EOD Processing Completed");
				Thread.sleep(4000);

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}



			}
		}
	}

					    
   public void Safeassign(String SSN,String FileName) throws Exception{
					    	 	
					    	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
					    		int lastrow=TestData.getLastRow("NewLoan");
					    		System.out.println("NewLoan "+lastrow);
					    		String sheetName="NewLoan";		
					    		for(int row=2;row<=lastrow;row++)
					    		{	
					    			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
					    			if(SSN.equals(RegSSN))
					    			{
					    				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
					    				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
					    				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
					    				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					    				String UserName = TestData.getCellData(sheetName,"UserName",row);
					    				String Password = TestData.getCellData(sheetName,"Password",row);
					    				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					    				 CSRLoginpage login = new CSRLoginpage();
					    				 login.Login(UserName, Password, StoreId, driver, AppURL, test);
					    			     Thread.sleep(5000);
					    				/*driver.switchTo().defaultContent();				
					    				driver.switchTo().frame("topFrame");
					    				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
					    				test.log(LogStatus.PASS, "Clicked on Cash Management");
					    				Thread.sleep(1000);
					    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					    				driver.switchTo().defaultContent();
					    				driver.switchTo().frame("mainFrame");
					    				//driver.switchTo().frame("main");
					    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					    				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
					    				driver.findElement(By.linkText("Safe")).click();
					    				test.log(LogStatus.PASS, "Clicked on safe");	
					    				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
					    				//driver.findElement(By.linkText("Drawer")).click();
					    				
					    				driver.findElement(By.linkText("Assign")).click();
					    				test.log(LogStatus.PASS, "Clicked on Assign");
					    				
		
					    				
					    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					    				
					    				driver.switchTo().defaultContent();
					    				driver.switchTo().frame("mainFrame");
					    				driver.switchTo().frame("main");
					    				
					    				
					    			//if(driver.findElement(By.name("previous")).isDisplayed())
					    			
					    				
					    				
					    					
					    				driver.findElement(By.name("previous")).click();
					    				
					    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					    				driver.switchTo().defaultContent();
					    				driver.switchTo().frame("mainFrame");
					    				driver.switchTo().frame("main");
					    				
					    				
					    				driver.findElement(By.name("yes")).click(); */
					    				
					    									    			
					    				
						driver.switchTo().defaultContent();				
						driver.switchTo().frame("topFrame");
						driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
						test.log(LogStatus.PASS, "Clicked on Cash Management");
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						//driver.findElement(By.cssSelector("li[id='911101']")).click();	
						driver.findElement(By.linkText("Safe")).click();
						test.log(LogStatus.PASS, "Clicked on Assign");	
						//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
						//driver.findElement(By.linkText("Drawer")).click();
						driver.findElement(By.linkText("Assign")).click();
						test.log(LogStatus.PASS, "Clicked on Assign");
						
											//login.Login(UserName, Password, StoreId, driver, AppURL, test);
					     Thread.sleep(5000);
						
					driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
						
						driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys(Password);
						
						
						driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
						
						
						driver.findElement(By.name("safeassign")).click();
						
						try { 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();
						    //if alert present, accept and move on.														
							
						}
						catch (NoAlertPresentException e) {
						    //do what you normally would if you didn't have the alert.
							
						}
						Thread.sleep(5000);
						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
						
						 driver.switchTo().defaultContent();
						    driver.switchTo().frame("mainFrame");
						    driver.switchTo().frame("main");
						    
						                                   
						    if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).isDisplayed())
						    {

						    	 test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
						    	 driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).click();
						    	 //driver.findElement(By.name("done")).click();
						    }
						    else
						    {
						    	test.log(LogStatus.PASS,"Safe not assigned successfully with over/short.");
						    }
					    }
					}
			}
	
			    						
   public WebElement Field(WebDriver driver) {


		try {
			Thread.sleep(500);
			WebElement element = (new WebDriverWait(driver, 9)).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
			return element;
		} catch (Exception e) {
			return null;
		}
	}	    		
									   	
   public void Drawerassign(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);

				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("Assign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Passwored is Entered");
				driver.findElement(By.name("drawerassign")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer Assigen Button");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {

				}

				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(this.Field(driver) != null )
				{                    		                   
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Safe")).click();
					test.log(LogStatus.PASS, "Clicked on Safe");
					driver.findElement(By.linkText("Deassign")).click();
					test.log(LogStatus.PASS, "Clicked on Deassign");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");                            
					driver.switchTo().frame("main");
					driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
					test.log(LogStatus.PASS, "Enter the Value 0");


					driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Enter the Password");

					driver.findElement(By.name("safedeassign")).click();
					test.log(LogStatus.PASS, "Click on the Safe Deassign Button");

					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();

					}
					catch (NoAlertPresentException e) {

					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
					{
						test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
					}
					else
					{
						driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
						test.log(LogStatus.PASS, "Enter the Password");
						driver.findElement(By.name("safedeassign")).click();
						test.log(LogStatus.PASS, "Click on the Deassign");
						for(String winHandle : driver.getWindowHandles()){
							driver.switchTo().window(winHandle);
						}				    
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						String DrawerOverShortAmount =driver.findElement(By.name("safeRequestBean.safeOverShort")).getAttribute("value");
						driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);
						test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);
						driver.findElement(By.name("safeRequestBean.primary")).sendKeys("Counterfeit Bill");
						test.log(LogStatus.PASS, "Primary Reason is selected as Counterfeit Bill");
						driver.findElement(By.name("safeRequestBean.notes")).sendKeys("Notes");
						test.log(LogStatus.PASS, "Notes Entered ");	
						driver.findElement(By.name("bt_AddDrawer")).click();
						test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
						Thread.sleep(3000);
						driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();

						test.log(LogStatus.PASS, "Click on Finish Safe O/S");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();

						}
						catch (NoAlertPresentException e) {
						}
						Thread.sleep(2000);
						for(String winHandle : driver.getWindowHandles()){
							driver.switchTo().window(winHandle);
						}				    
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");

						if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
						{

							test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
							driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
						}
						else
						{
							test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");
						}                            
					}
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Safe")).click();
					test.log(LogStatus.PASS, "Clicked on Assign");
					driver.findElement(By.linkText("Assign")).click();
					test.log(LogStatus.PASS, "Clicked on Assign");
					Thread.sleep(5000);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
					test.log(LogStatus.PASS,"Passwored is Entered");

					driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");


					driver.findElement(By.name("safeassign")).click();
					test.log(LogStatus.PASS,"Click on Safe Assigen");

					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();

					}
					catch (NoAlertPresentException e) {

					}
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
					{

						test.log(LogStatus.PASS,"Safe assigned successfully.");
						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
					}
					else
					{
						test.log(LogStatus.PASS,"Safe not assigned successfully.");
					}

					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Drawer")).click();
					test.log(LogStatus.PASS, "Clicked on Drawer");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.findElement(By.linkText("Assign")).click();
					test.log(LogStatus.PASS, "Clicked on Assign");

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

					driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS,"Passwored is Entered");
					driver.findElement(By.name("drawerassign")).click();
					test.log(LogStatus.PASS,"Click on drawer Assigen Button");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();

					}
					catch (NoAlertPresentException e) {

					}
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
					{

						test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
					}
					else
					{
						test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
					}

				}                                        
				else
				{                    	
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
					{

						test.log(LogStatus.PASS,"Drawer Assigned successfully with over/short.");
						//driver.findElement(By.name("done")).click();
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
					}
					else
					{
						test.log(LogStatus.PASS,"Drawer not Assigned successfully with over/short.");
					}
				}


			}
		}
		//driver.quit();
		//driver.close();
	}



public void SafeDeassign(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 CSRLoginpage login = new CSRLoginpage();
login.Login(UserName, Password, StoreId, driver, AppURL, test);
Thread.sleep(5000);
driver.switchTo().defaultContent();				
driver.switchTo().frame("topFrame");
driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
test.log(LogStatus.PASS, "Clicked on Cash Management");
Thread.sleep(1000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//driver.findElement(By.cssSelector("li[id='911101']")).click();	
driver.findElement(By.linkText("Safe")).click();
test.log(LogStatus.PASS, "Clicked on Drawer");	
//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
//driver.findElement(By.linkText("Drawer")).click();
driver.findElement(By.linkText("Deassign")).click();
test.log(LogStatus.PASS, "Clicked on Assign");


driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

//driver.findElement(By.name("previous")).click();

driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");

driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

driver.findElement(By.name("safedeassign")).click();

try { 
	   Alert alert = driver.switchTo().alert();
	   alert.accept();
	   //if alert present, accept and move on.														
		
	}
	catch (NoAlertPresentException e) {
	   //do what you normally would if you didn't have the alert.
		
	}

driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");
driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");

driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");

driver.findElement(By.name("safedeassign")).click();
try { 
	   Alert alert = driver.switchTo().alert();
	   alert.accept();
	   //if alert present, accept and move on.														
		
	}
	catch (NoAlertPresentException e) {
	   //do what you normally would if you didn't have the alert.
		
	}
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

          driver.findElement(By.name("safeDeassignRequestBean.comments")).sendKeys("chenna");
          driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");
          
          driver.findElement(By.name("safedeassign")).click();
          
          driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    
		    if(driver.findElement(By.name("done")).isDisplayed())
		    {

		    	 test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
		    	 driver.findElement(By.name("done")).click();
		    }
		    else
		    {
		    	test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");
		    }
		
         
         



driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

driver.findElement(By.name("yes")).click();


driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
driver.findElement(By.name("drawerassign")).click();
try { 
   Alert alert = driver.switchTo().alert();
   alert.accept();
   //if alert present, accept and move on.														
	
}
catch (NoAlertPresentException e) {
   //do what you normally would if you didn't have the alert.
	
}


driver.switchTo().defaultContent();
   driver.switchTo().frame("mainFrame");
   driver.switchTo().frame("main");
   
   if(driver.findElement(By.name("done")).isDisplayed())
   {

   	 test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
   	 driver.findElement(By.name("done")).click();
   }
   else
   {
   	test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
   }

			}}}






   public void Safe(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
			int lastrow=TestData.getLastRow("NewLoan");
			System.out.println("NewLoan "+lastrow);
			String sheetName="NewLoan";		
			for(int row=2;row<=lastrow;row++)
			{	
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType=TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
					String ProductID=TestData.getCellData(sheetName,"ProductID",row);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
					 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					 CSRLoginpage login = new CSRLoginpage();
	login.Login(UserName, Password, StoreId, driver, AppURL, test);
	Thread.sleep(5000);
	driver.switchTo().defaultContent();				
	driver.switchTo().frame("topFrame");
	driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
	test.log(LogStatus.PASS, "Clicked on Cash Management");
	Thread.sleep(1000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	//driver.findElement(By.cssSelector("li[id='911101']")).click();	
	driver.findElement(By.linkText("Drawer")).click();
	test.log(LogStatus.PASS, "Clicked on Drawer");	
	//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
	//driver.findElement(By.linkText("Drawer")).click();
	driver.findElement(By.linkText("Assign")).click();
	test.log(LogStatus.PASS, "Clicked on Assign");


	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");

	driver.findElement(By.name("previous")).click();
	
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");

	driver.findElement(By.name("yes")).click();


	driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
	test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

	driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
	driver.findElement(By.name("drawerassign")).click();
	try { 
	   Alert alert = driver.switchTo().alert();
	   alert.accept();
	   //if alert present, accept and move on.														
		
	}
	catch (NoAlertPresentException e) {
	   //do what you normally would if you didn't have the alert.
		
	}


	driver.switchTo().defaultContent();
	   driver.switchTo().frame("mainFrame");
	   driver.switchTo().frame("main");
	   
	   if(driver.findElement(By.name("done")).isDisplayed())
	   {

	   	 test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
	   	 driver.findElement(By.name("done")).click();
	   }
	   else
	   {
	   	test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
	   }

				}}}
   
   
   
   public void DeliquentPaymentStatus(String SSN,String FileName) throws Exception
	{
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");
								
				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				   appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 String CheckStaus=null;
						 
			   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}
				 
				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
						//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
						 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
						 test.log(LogStatus.PASS,"Payment status is ."+CheckStaus);
			             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				   System.out.print(CheckStaus);	
				   // driver.close();
	
			}
		}
	}
  
   public void PaymentStatus(String SSN,String FileName) throws Exception
	{
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");
								
				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				   appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 String CheckStaus=null;
						 
			   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}
				 
				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
						//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
						 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
						 test.log(LogStatus.PASS,"Payment status is ."+CheckStaus);
			             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				   System.out.print(CheckStaus);	
				   // driver.close();
	
			}
		}
	}

   public void StoreInfo(String SSN,String FileName) throws Exception
  	{
     Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
  	int lastrow=TestData.getLastRow("NewLoan");
  	System.out.println("NewLoan "+lastrow);
  	String sheetName="NewLoan";		
  	for(int row=2;row<=lastrow;row++)
  	{	
  		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
  		if(SSN.equals(RegSSN))
  		{
  			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
  			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
  			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
  			String UserName = TestData.getCellData(sheetName,"UserName",row);
  			String Password = TestData.getCellData(sheetName,"Password",row);
  			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
  			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
  			System.out.println(AdminURL);
  			
  			driver.get(AdminURL);
  			 Thread.sleep(1000);
  			 
  			 
     
     driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
     test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
     driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
     test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
     //Click Login Button
     driver.findElement(By.name("login")).click();
     test.log(LogStatus.PASS, "Clicked on Submit button");
     Thread.sleep(10000);
     driver.switchTo().frame("topFrame");
  	driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
  	test.log(LogStatus.PASS, "Clicked on Store Setup");
  	Thread.sleep(10000);
  	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
  	driver.switchTo().defaultContent();
  	driver.switchTo().frame("mainFrame");
  	Thread.sleep(10000);
  	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
  	 driver.findElement(By.linkText("Store Config")).click();
  	//Store Config
  	/*WebElement element= driver.findElement(By.cssSelector("li[id='101000']"));	
  	Actions action = new Actions(driver);
  	action.moveToElement(element).perform();
     WebElement subElement = driver.findElement(By.cssSelector("li[id='101020']"));			        
     action.moveToElement(subElement).perform();			 
     action.click();	*/
     driver.findElement(By.linkText("Edit Store")).click();
    // action.perform();
   //  driver.findElement(By.cssSelector("li[id='101020']")).click();
  	test.log(LogStatus.PASS, "Clicked on Store Config");
  			
  	test.log(LogStatus.PASS, "Clicked on Edit Store");					
  	driver.switchTo().frame("main");		
  	  driver.findElement(By.name("locationBean.locNbr")).sendKeys(StoreID);
  	  test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
  	  driver.findElement(By.name("Submit2")).click();
  	  test.log(LogStatus.PASS, "Clicked on submit button");
  	  for(String winHandle : driver.getWindowHandles()){
  		    driver.switchTo().window(winHandle);
  			} 
     
  	  
  	     driver.switchTo().defaultContent();
  		 driver.switchTo().frame("mainFrame");
  		 driver.switchTo().frame("main");													  	 	  
  	     driver.findElement(By.name("locationBean.locStatusCd")).sendKeys("Crash Package");
  	    // test.log(LogStatus.PASS, "Store Info Status is Chenged: "+Storestaus);
  	  
  	  //locationBean.locStatusCd
     
  	  
  	  
  	  driver.switchTo().defaultContent();
  		 driver.switchTo().frame("mainFrame");
  		 driver.switchTo().frame("main");													    	
  		 if(driver.findElement(By.name("submitButton")).isDisplayed())
  			{
  			 test.log(LogStatus.PASS, "Store Aging is Successfully ");
  				driver.findElement(By.name("submitButton")).click();
  			}
  		 else
  			{
  				test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
  			}
  		//driver.close();
  }
  		}
  	}
  	
   public void DrawLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
			int lastrow=TestData.getLastRow("NewLoan");
			System.out.println("NewLoan "+lastrow);
			String sheetName="NewLoan";		
			for(int row=2;row<=lastrow;row++)
			{	
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType=TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
					String ProductID=TestData.getCellData(sheetName,"ProductID",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					String State = TestData.getCellData(sheetName,"StateID",row);
					System.out.println(ProductID);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					//String Password = TestData.getCellData(sheetName,"Password",row);
					String ProductType = TestData.getCellData(sheetName,"ProductType",row);
					String ProductName = TestData.getCellData(sheetName,"ProductName",row);
					//String Term = TestData.getCellData(sheetName,"Term",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
					//System.out.println(Term);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					//String stateProduct=State+" "+ProductID;
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					System.out.println(ESign_CollateralType);
					String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
					String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
					String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
					String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
					String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
					String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
					String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
					String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
					String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
					String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
					String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
					System.out.println(last4cheknum);
					System.out.println(stateProductType);
					this.Login(UserName,Password,StoreID);	

					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					test.log(LogStatus.INFO, "VoidDrawLoan with-SSN: " +SSN +" :: Starts");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.cssSelector("li[id='911101']")).click();			
					test.log(LogStatus.PASS, "Clicked on Transactions");		
					driver.switchTo().frame("main");		
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(By.name("ssn2")).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(By.name("ssn3")).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(By.name("submit1")).click();
					test.log(LogStatus.PASS, "Click on submit Button");		
					for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}
				    driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
						}				    
					 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    
					 
					    if(ProductID.equals("LOC"))
						 {
					    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
						 }
					  //  driver.findElement(By.name("button")).click();
						test.log(LogStatus.PASS, "Click on GO Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.name("transactionList")).sendKeys("Draw");
						 if(ProductID.equals("LOC"))
						 {
							 driver.findElement(By.name("button")).click(); 
						 }
						 
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 driver.findElement(By.name("loanAmt")).clear();
								
								
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
							 
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
								Thread.sleep(2000);
							 driver.findElement(By.name("loanAmt")).sendKeys("500");	
								Thread.sleep(2000);
							 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							// test.log(LogStatus.PASS, "Disb Amt is enterted as 200");
							 driver.findElement(By.name("disbAmtFirst")).sendKeys("500");					
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 500");
							 driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
							 test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
								Thread.sleep(2000);
								driver.findElement(By.name("finishadvance")).click();
								test.log(LogStatus.PASS, "Click on Finish Loan Button");
							
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								
								if(driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td")).isDisplayed())
								{
									test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " +SSN +" :: is Successful");
								}
								else
								{
									test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
								}
						    	
							 }
						
				}
				
			}
	public void NewLoanDraw(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);		
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{		
				String State = TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				System.out.println(ProductID);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
				String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
				String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
				String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
				String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
				String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
			
				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 //	Selection of Product based on the Name provided in Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				 {
					 	
					if(ProductID.equals("TLP"))							
					{					
					System.out.println("IN TLP");
					driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
					driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
					driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
					}												
					if(ProductName.equals("TNPAYDAY"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						driver.findElement(By.xpath("//*[@id='termSel1']")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Line of Credit"))
					{
						if(StoreID.equals("4353"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						}
						else
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						}
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					
					
					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");
					
					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
				
					driver.findElement(By.id("LoanButtonId")).click();
				//New Loan Screens
					if(ProductID.equals("PDL"))
					{	
					
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						if(!(ESign_LoanAmt.isEmpty()))
						{
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
						}
						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
						test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
								if(ESign_Preference.equals("Call"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
								}
								if(ESign_Preference.equals("Mail"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
								}
								if(ESign_Preference.equals("SMS"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
									
									try { 
										    Alert alert = driver.switchTo().alert();
										    alert.dismiss();
										    //if alert present, accept and move on.														
											
										}
										catch (NoAlertPresentException e) {
										    //do what you normally would if you didn't have the alert.
										}
								}
								
						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.xpath("//*[@id='allowCoupons']/td[3]/input")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							driver.findElement(By.xpath("//*[@id='coupon']/td[3]/div[1]/input")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
						}
						driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
						WebDriverWait wait = new WebDriverWait(driver, 1000);	
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
						driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
						driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						try { 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();
						    //if alert present, accept and move on.														
							
						}
						catch (NoAlertPresentException e) {
						    //do what you normally would if you didn't have the alert.
						}
					for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						// driver.findElement(By.name("OKBut")).click();					
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("bdyLoad");
						if(driver.findElement(By.name("Ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("ILP"))
					{	
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
								if(ESign_Preference.equals("Call"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
								}
								if(ESign_Preference.equals("Mail"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
								}
								if(ESign_Preference.equals("SMS"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
									
									try { 
										    Alert alert = driver.switchTo().alert();
										    alert.dismiss();
										    //if alert present, accept and move on.														
											
										}
										catch (NoAlertPresentException e) {
										    //do what you normally would if you didn't have the alert.
										}
								}
								
						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							//String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
						}
						 WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
				 		String NumofInst=ele.getAttribute("value");
				 		//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
				 		System.out.println(NumofInst);
				 		int installments = Integer.parseInt(NumofInst);
				 		for(int i=0;i<installments;i++)
				 		{
				 			 Random rand = new Random();
				 			 int rand1 = rand.nextInt(100000);	
				 			 String chknum = Integer.toString(rand1);
				 			driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);
				 			
				 		}			 					 			
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						try { 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();
						    //if alert present, accept and move on.														
							
						}
						catch (NoAlertPresentException e) {
						    //do what you normally would if you didn't have the alert.
						}
						//for( String winHandle1 : driver.getWindowHandles())
						//{
						   // driver.switchTo().window(winHandle1);
						//}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						 driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
						
					if(ProductID.equals("TLP"))
					{	
						String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
						String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
						String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
						String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
						//String VehicleGrade=TestData.getCellData(sheetName,"Vehicle Grade",row);
						String LicensePlateExp=TestData.getCellData(sheetName,"License Plate Expiry",row);
						String InsuranceCoverage=TestData.getCellData(sheetName,"Insurance Coverage",row);
						String PhoneNbr=TestData.getCellData(sheetName,"Phone Nbr",row);
						 String PhoneNbr1 = PhoneNbr.substring(0, 3);
				        String PhoneNbr2 = PhoneNbr.substring(3, 6);
				        String PhoneNbr3 = PhoneNbr.substring(6, 10);
				        String InsuranceCompany =TestData.getCellData(sheetName,"Insurance Company",row);
				        String InsuranceExpiryDate=TestData.getCellData(sheetName,"Insurance Expiry Date",row);
				        String PolicyNumber=TestData.getCellData(sheetName,"Policy Number",row);
				        String InsuranceExpiryDate0[] =InsuranceExpiryDate.split("/");
				        String InsuranceExpiryDate1 = InsuranceExpiryDate0[0];
				        String InsuranceExpiryDate2 = InsuranceExpiryDate0[1];
				        String InsuranceExpiryDate3 = InsuranceExpiryDate0[2];
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
					//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
				
						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
						driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
						driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
						driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
						driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
						driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
						driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
						driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
						driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
						driver.findElement(By.name("button2")).click();			
						driver.findElement(By.name("button2")).click();	
							
						test.log(LogStatus.PASS, "click on Update 2 button ");
						Thread.sleep(8000);
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						driver.findElement(By.name("process")).click();
						
						test.log(LogStatus.PASS, "click on process Loan button ");
						try { 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();
						    //if alert present, accept and move on.														
							
						}
						catch (NoAlertPresentException e) {
						    //do what you normally would if you didn't have the alert.
						}
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
								if(ESign_Preference.equals("Call"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
								}
								if(ESign_Preference.equals("Mail"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
								}
								if(ESign_Preference.equals("SMS"))	
								{
									driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
									test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
									
									try { 
										    Alert alert = driver.switchTo().alert();
										    alert.dismiss();
										    //if alert present, accept and move on.														
											
										}
										catch (NoAlertPresentException e) {
										    //do what you normally would if you didn't have the alert.
										}
								}
								
						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
														
						}
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						test.log(LogStatus.PASS, "Click on Finish Loan Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
						 //driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("LOC"))
					{
					
						test.log(LogStatus.INFO, "Navigated to New Loan Screen");
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
						//driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						//driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
						test.log(LogStatus.PASS, "Click on Finish LOC Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
						 //driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						
						if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).click();
							 test.log(LogStatus.INFO, "Navigated to Draw Screen");
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 
							 driver.findElement(By.name("loanAmt")).clear();
								
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
							 driver.findElement(By.name("loanAmt")).sendKeys("100");	
							 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 100");
							 driver.findElement(By.name("disbAmtFirst")).sendKeys("100");					
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 100");
							 driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
							 test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
								driver.findElement(By.name("finishadvance")).click();
								test.log(LogStatus.PASS, "Click on Finish Loan Button");
							
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								
								if(driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Draw New Loan is Completed Successfully ");
								}
								else
								{
									test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
								}
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
						
					//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
				 	}
					else
					{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
					}
			}
		}

	}
   
	
public void DrawLoanwithACH(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
			int lastrow=TestData.getLastRow("NewLoan");
			System.out.println("NewLoan "+lastrow);
			String sheetName="NewLoan";		
			for(int row=2;row<=lastrow;row++)
			{	
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType=TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
					String ProductID=TestData.getCellData(sheetName,"ProductID",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					String State = TestData.getCellData(sheetName,"StateID",row);
					System.out.println(ProductID);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					//String Password = TestData.getCellData(sheetName,"Password",row);
					String ProductType = TestData.getCellData(sheetName,"ProductType",row);
					String ProductName = TestData.getCellData(sheetName,"ProductName",row);
					//String Term = TestData.getCellData(sheetName,"Term",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
					//System.out.println(Term);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					//String stateProduct=State+" "+ProductID;
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					System.out.println(ESign_CollateralType);
					String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
					String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
					String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
					String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
					String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
					String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
					String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
					String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
					String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
					String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
					String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
					System.out.println(last4cheknum);
					System.out.println(stateProductType);
					this.Login(UserName,Password,StoreID);	

					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					test.log(LogStatus.INFO, "VoidDrawLoan with-SSN: " +SSN +" :: Starts");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.cssSelector("li[id='911101']")).click();			
					test.log(LogStatus.PASS, "Clicked on Transactions");		
					driver.switchTo().frame("main");		
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(By.name("ssn2")).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(By.name("ssn3")).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(By.name("submit1")).click();
					test.log(LogStatus.PASS, "Click on submit Button");		
					for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}
				    driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
						}				    
					 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    
					 
					    if(ProductID.equals("LOC"))
						 {
					    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
						 }
					  //  driver.findElement(By.name("button")).click();
						test.log(LogStatus.PASS, "Click on GO Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.name("transactionList")).sendKeys("Draw");
						 if(ProductID.equals("LOC"))
						 {
							 driver.findElement(By.name("button")).click(); 
						 }
						 
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 driver.findElement(By.name("loanAmt")).clear();
								
								
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
							 
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
								Thread.sleep(2000);
							 driver.findElement(By.name("loanAmt")).sendKeys("50");	
								Thread.sleep(2000);
							 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							 driver.findElement(By.name("disbAmtFirst")).sendKeys("50");					
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							 driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
							 test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
								Thread.sleep(2000);
								driver.findElement(By.name("finishadvance")).click();
								test.log(LogStatus.PASS, "Click on Finish Loan Button");
							
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								
								if(driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td")).isDisplayed())
								{
									test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " +SSN +" :: is Successful");
								}
								else
								{
									test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
								}
						    	
							 }
						
				}
				
			}

public void PayOffLoan(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("PayOff");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
							// System.out.println(Pmt);
							// driver.findElement(By.name("requestBean.paymentAmt")).clear();
							// driver.findElement(By.name("tenderType")).sendKeys("10");
							 //test.log(LogStatus.PASS, "tenderType");
							 String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");
							 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("tenderAmount")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								//for( String winHandle1 : driver.getWindowHandles())
								//{
								    //driver.switchTo().window(winHandle1);
								//}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 
								 if(driver.findElement(By.name("ok")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: is Successful");
									 driver.findElement(By.name("ok")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payoff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}



public void PartialPayment(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Payments");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
							// System.out.println(Pmt);
							 driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
							driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("30");
							test.log(LogStatus.PASS, "Tender Amt is entered as "+30);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
								 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								//for( String winHandle1 : driver.getWindowHandles())
								//{
								    //driver.switchTo().window(winHandle1);
								//}			
								/* driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 
								 if(driver.findElement(By.name("ok")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
									 driver.findElement(By.name("ok")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
									}
							 */
					    	
						 }
					
			}
			
		}
	}


public void MinPayments(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Payments");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]")).getText();						
							 System.out.println(Pmt);
							 driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys(Pmt);
							driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
								Thread.sleep(3000);
								//*[@id="btnADV_Yes"]
								// driver.findElement(By.xpath("//*[@id='btnADV_Yes']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
							
								
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 
								 if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "MinPayment with-SSN: " +SSN +" :: is Successful");
									// driver.findElement(By.name("ok")).click();
									 driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "MinPayment not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}






public void CurePaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String CheckStaus=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
					 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
					 
					//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
					 test.log(LogStatus.PASS,"Payment status is Cure."+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			    //driver.close();

		}
	}
}

public void EncryptionKey_Void(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String Eankey=null;
			Thread.sleep(4000);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.id("go_Button")).click();
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			String TranID = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td")).getText();
			test.log(LogStatus.PASS, "TranId captured:"+TranID);	
			String TranID0[] =TranID.split(":");
			String TranID1 = TranID0[0];
			String TranID2 =  TranID0[1]; 
            Thread.sleep(3000);
			//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
            driver.findElement(By.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]")).click();
           //                              /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]
            //driver.findElement(By.xpath("//input[@name='NO' and @type='button']")).click();
			test.log(LogStatus.PASS, "No button is clicked ");		
//name="NO"
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Employee')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Encryption Key")).click();
			test.log(LogStatus.PASS, "Clicked on Encryption Key");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("Encryption")).click();
			test.log(LogStatus.PASS, "Clicked on Encryption");
			Thread.sleep(5000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreId);
			test.log(LogStatus.PASS, "Store number Entered");	

			driver.findElement(By.name("requestBean.tranNbr")).sendKeys(TranID2);
			test.log(LogStatus.PASS, "Tran number Entered");	

			driver.findElement(By.name("trancd")).sendKeys("Revolving Credit Buy-RCBUY");
			test.log(LogStatus.PASS, "Trancd selected");

			driver.findElement(By.name("GetKey")).click();
			test.log(LogStatus.PASS, "GetKey clicked");

			Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
			test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

			driver.close();
			driver = new InternetExplorerDriver();
			this.Login(UserName,Password,StoreId);	
			Thread.sleep(4000);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();		
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			
			test.log(LogStatus.PASS, "Click on GO Button"); 
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");					
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.id("go_Button")).click();
			Thread.sleep(5000);

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
			 driver.findElement(By.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[1]")).click();
			//driver.findElement(By.xpath("//input[@name='YES' and @type='button']")).click();
			test.log(LogStatus.PASS, "Yes Button clicked");

			if(ProductID.equals("LOC"))
			{                           
				driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
			//	String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
			//	System.out.println(Pmt);
				//driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				//test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

				driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
				test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



			}
			
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("password")).sendKeys(Password);
				// Robot robot = new Robot();
				//	Thread.sleep(2000);
				//	robot.keyPress(KeyEvent.VK_F11);
				driver.findElement(By.name("Submit22")).click();
			//	robot.keyPress(KeyEvent.VK_F11);
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}	
			
			Thread.sleep(500);


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();

			}
			catch (NoAlertPresentException e) {
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}
			test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
			/*driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{

				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
				}
			}*/
		}
	}
}


public void Rescind(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String Eankey=null;
			Thread.sleep(4000);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Rescind");
			test.log(LogStatus.PASS, "Transaction Type is selected as Rescind");	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();


			if(ProductID.equals("LOC"))
			{
				String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
				//String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
				System.out.println(Pmt);
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

			//	driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
			//	test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



			}
			
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("password")).sendKeys(Password);
				// Robot robot = new Robot();
				//	Thread.sleep(2000);
				//	robot.keyPress(KeyEvent.VK_F11);
				driver.findElement(By.name("Submit22")).click();
			//	robot.keyPress(KeyEvent.VK_F11);
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}	


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();

			}
			catch (NoAlertPresentException e) {
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{
				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input
				if (driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				//if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Rescind Loan is Completed Successfully ");
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Rescind Payment is not Completed Successfully ");
				}
			}
		}
	}
}


public void Void(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String Eankey=null;
			Thread.sleep(4000);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();


			if(ProductID.equals("LOC"))
			{
				String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
				//String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
				System.out.println(Pmt);
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

			//	driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
			//	test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



			}
			
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("password")).sendKeys(Password);
				// Robot robot = new Robot();
				//	Thread.sleep(2000);
				//	robot.keyPress(KeyEvent.VK_F11);
				driver.findElement(By.name("Submit22")).click();
			//	robot.keyPress(KeyEvent.VK_F11);
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}	


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();

			}
			catch (NoAlertPresentException e) {
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{
				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input
				if (driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				//if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
				}
			}
		}
	}
}

public void CustomerDefault(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{


				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture Cure End Dtae"+DueDate);
			System.out.print(DueDate);			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.close();	
			//System.out.print(DueDate);	
			// driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);
			// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			//String DueDate="12/12/2020";
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);


			Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, 0);
			Date DDueDateminus1= cal.getTime();

			// String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];




			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QAJobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updated successfully.");
			}



			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");


			//Thread.sleep(5000);
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");

			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Default Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Default Loc");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			Thread.sleep(6000);
			 WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
							        Actions action = new Actions(driver);								        
							        action.moveToElement(element).build().perform();

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(6000);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


			// driver.findElement(By.linkText("iPads")).click();
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			Thread.sleep(6000);


		}
	}
}



public void CustomerDefault_Agestore(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{


				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture Cure End Dtae"+DueDate);
			System.out.print(DueDate);			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.close();	
			//System.out.print(DueDate);	
			// driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);
			// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			//String DueDate="12/12/2020";
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);


			Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, 0);
			Date DDueDateminus1= cal.getTime();

			// String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];




			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updated successfully.");
			}

		}
	}
}

public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String CheckStaus=null;
			CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();

			test.log(LogStatus.PASS,"Payment status is Default."+CheckStaus);
			System.out.print(CheckStaus);	
			//driver.close();

		}
	}
}


public void Bankrupt_Deceased_Loanclosuer(String SSN,String FileName) throws Exception
		{
	 Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
		        String AttorneyP2 = AttorneyPhone.substring(3, 6);
		        String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);
				String Bankstatus = null;
				///////////////////////////////////////
				this.Login(UserName,Password,StoreID);
		
				Thread.sleep(5000);
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(ProductID.equals("LOC"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("LOC"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

			//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");
				

				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 10);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				
				////////////////////////////////////
		/*		driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");*/
				driver.manage().window().maximize();
				 Thread.sleep(1000);
				 
				 
	 
	 driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
	 test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
	 driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
	 test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
	 //Click Login Button
	 driver.findElement(By.name("login")).click();
	 test.log(LogStatus.PASS, "Clicked on Submit button");
	 Thread.sleep(10000);
	 Thread.sleep(8000);
	 driver.switchTo().frame("topFrame");
	 WebDriverWait wait = new WebDriverWait(driver, 10000);					   
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 
	 
	 driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
		test.log(LogStatus.PASS, "Clicked on Transactions");
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		Thread.sleep(10000);
	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
		 driver.findElement(By.linkText("Borrower")).click();
		 test.log(LogStatus.PASS, "Clicked on Borrower");
		 
		 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
		test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		
		
		  for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				} 
		       driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");		 
			    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);			
				
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");  
		     driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 
			 
			 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		
			 
			 test.log(LogStatus.PASS,"Click on Go button");		 

		     driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 Thread.sleep(3000);
			/* if( driver.findElement(By.name("loanCode")).isDisplayed())
			 {
			 driver.findElement(By.name("loanCode")).click();
			 test.log(LogStatus.PASS, "Selecting Check box for loan");
			 }*/
			 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
			 test.log(LogStatus.PASS, "select status as :" + BNKstatus); 
			 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
				Thread.sleep(500);			
				driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
				
				/* driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
				 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
				 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
				 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
				
				
			 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
			 test.log(LogStatus.PASS, "Entered Attorny Name");
			 
			 
			 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
				test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
				test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
				Thread.sleep(500);			
				driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
				test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);*/
				
				driver.findElement(By.name("bt_AddBankruptcy")).click();			
				 test.log(LogStatus.PASS, "Status BNKPending is Saved");
			 
				 Thread.sleep(50000);
			//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
				 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
				 
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted::+Bankstatus");
				 
					driver.close();

					driver = new InternetExplorerDriver();
					
				}

			}		 
			 
	}
		


public void BankruptStatus(String SSN,String FileName) throws Exception


	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();	
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			  // driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				test.log(LogStatus.PASS, "History Selected in DropDown");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
					//driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String PaymentStatus=null;
				PaymentStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is ::"  +PaymentStatus);
				String LineStatus=null;
				LineStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS,"Line status is ::"  +LineStatus);
				String WOReason=null;
				WOReason =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS,"Reason for Customer got WriteOff ::"  +WOReason);


			}
		}
	}

	
public void Bankrupt_Void(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		///////////////////////////////////////
		

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		

		
		////////////////////////////////////
/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 

  
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(5000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
		driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 driver.findElement(By.name("menu")).sendKeys("Edit");
	 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();
	 
		 

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
	 test.log(LogStatus.PASS, "select status as Void");			
		
		
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, " Clicked on Saved");
	 
		 Thread.sleep(5000);
	
       String ele= driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
       if(ele.contains("Void")){
  		 test.log(LogStatus.PASS, " Bankrupt void complted Sucessfully ");
    	   
       }
       else{
    	   test.log(LogStatus.PASS, " Bankrupt void not complted Sucessfully ");
       }
       

			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
	 
	 
	 /*if(driver.findElement(By.name("submitButton")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "Store Aging is Successfully ");
			driver.findElement(By.name("submitButton")).click();
		}
	 else
		{
			test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
		}*/
	//driver.close();
}


public void Void_Deceased(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		///////////////////////////////////////
		

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		

		
		////////////////////////////////////
/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 

  
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(5000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
		driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 driver.findElement(By.name("menu")).sendKeys("Edit");
	 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();
	 
		 

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);
	/* if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }*/
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
	 test.log(LogStatus.PASS, "select status as Void");			
		
		
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, " Clicked on Saved");
	 
		 Thread.sleep(5000);
	
       String ele= driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
       if(ele.contains("Void")){
  		 test.log(LogStatus.PASS, " Bankrupt void complted Sucessfully ");
    	   
       }
       else{
    	   test.log(LogStatus.PASS, " Bankrupt void not complted Sucessfully ");
       }
       

			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
	 
	 
	 /*if(driver.findElement(By.name("submitButton")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "Store Aging is Successfully ");
			driver.findElement(By.name("submitButton")).click();
		}
	 else
		{
			test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
		}*/
	//driver.close();
}



public void Default_WOProc(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
			//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
			    //driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.PASS, "History Selected in DropDown");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture PWO  Dtae"+DueDate);
			System.out.print(DueDate);			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.close();	
			//System.out.print(DueDate);	
			// driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			driver.manage().window().maximize();
			// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);


			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);
			Thread.sleep(8000);
			Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, 60);
			Date DDueDateminus1= cal.getTime();
			String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDateminus1.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];


			WebDriverWait wait = new WebDriverWait(driver, 10000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			/*driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

			Actions actions1 = new Actions(driver);

			actions1.moveToElement(elements1).build().perform();

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
*/
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updated successfully.");
			}



			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");


			Thread.sleep(5000);
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");

			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Writeoff Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Writeoff Loc");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			Thread.sleep(6000);
			/* WebElement element = driver.findElement(By.name("cancel"));
							        Actions action = new Actions(driver);								        
							        action.moveToElement(element).build().perform();*/

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
			//Thread.sleep(6000);
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			
			
			//try { 
				//Alert alert = driver.switchTo().alert();
				//alert.dismiss();
				//if alert present, accept and move on.														

			//}
			//catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
		//}
			
			
			 Thread.sleep(6000);
			 WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
			  Actions action = new Actions(driver);								        
			  action.moveToElement(element).build().perform();
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


			// driver.findElement(By.linkText("iPads")).click();
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			Thread.sleep(6000);


		}
	}
}


public void Closure(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Starts");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			if(ProductID.equals("LOC"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("LOC Closure");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			else
			{
				driver.findElement(By.id("go_Button")).click();
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				driver.findElement(By.name("close loc")).click();
																								
				test.log(LogStatus.PASS, "Clicked on Finish Closure Loan button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("ok")).click();


				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("ok")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Closure Loan is not Completed Successfully ");
				}


			
		
			}
		}

	}
}

public void Bankrupt_Deseased1(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		///////////////////////////////////////
		this.Login(UserName,Password,StoreID);

		Thread.sleep(5000);
		Thread.sleep(1000);
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
		test.log(LogStatus.PASS, "Clicked on Loan Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("li[id='911101']")).click();			
		test.log(LogStatus.PASS, "Clicked on Transactions");		
		driver.switchTo().frame("main");		
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit1")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}				    
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");


		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		}
		test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("History");
		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.id("go_Button")).click();  
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String DueDate=null;

	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
		//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
		test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
		System.out.print(DueDate);	
		driver.close();

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
		String DDueDate[] =DueDate.split("/");


		Date DDueDateminus1 = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDateminus1);

		cal.add(Calendar.DATE, -2);

		Date DDueDate1= cal.getTime();

		DueDate =df.format(DDueDate1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		
		////////////////////////////////////
/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 

  
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(5000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
		
  
  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 
	// driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();
	 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		
	 
	 test.log(LogStatus.PASS,"Click on Go button");		 

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);
	/* if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }*/
	 
	 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click(); 
	 driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);                       
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
	 test.log(LogStatus.PASS, "select status as Deceased:");
	 
	 
	 
	 
	 
	 
	 driver.findElement(By.name("bankruptcyFilingDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("bankruptcyFilingDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("bankruptcyFilingDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
		
		 driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
		 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
		 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
		 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
		
		
	 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
	 test.log(LogStatus.PASS, "Entered Attorny Name");
	 
	 
	 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
		test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
		test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
		Thread.sleep(500);			
		driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
		test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status BNKPending is Saved");
	 
		 Thread.sleep(50000);
	//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
		 if( driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
		 {
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
		 
	 }
			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
	 
	 
	 /*if(driver.findElement(By.name("submitButton")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "Store Aging is Successfully ");
			driver.findElement(By.name("submitButton")).click();
		}
	 else
		{
			test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
		}*/
	//driver.close();
}



public void Check_Draw(String SSN,String FileName) throws Exception

{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			//String age_store = TestData.getCellData(sheetName, "AgeStore",row);
			//int Age_store = Integer.parseInt(age_store);
			//System.out.println(Age_store);
			//System.out.println("age_store:::"+age_store);
			//int Days= Integer.parseInt(age_store);

			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			String transactino_value=null;

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			Thread.sleep(5000);
			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();   
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			int n=driver.findElements(By.xpath("//select[@name='transactionList']/option")).size();
			//test.log(LogStatus.PASS, "Draw Option is Available::+n");	

			for(int i=1;i<=n;i++)
			{
			 transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getText();
				test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
			  
			
				if(transactino_value.contains("Draw"))
				{
					test.log(LogStatus.PASS, "Draw Option is Available");	
					//test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
					//test.log(LogStatus.PASS, "Draw Option is Available");	
                  

				}
				/*else
				{
				test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
				test.log(LogStatus.PASS, "Draw Option is not Available");*/
					//test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
					//
				}
				
		
			}
	
			
			
		}
	}
//}




public void Dismissed_AfterBANKRUPT(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		String Bankstatus = null;
		///////////////////////////////////////
		this.Login(UserName,Password,StoreID);

		Thread.sleep(5000);
		Thread.sleep(1000);
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
		test.log(LogStatus.PASS, "Clicked on Loan Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("li[id='911101']")).click();			
		test.log(LogStatus.PASS, "Clicked on Transactions");		
		driver.switchTo().frame("main");		
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit1")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}				    
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");


		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		}
		test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("History");
		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.id("go_Button")).click();  
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String DueDate=null;

	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
		//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
		test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
		System.out.print(DueDate);	
		driver.close();

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
		String DDueDate[] =DueDate.split("/");


		Date DDueDateminus1 = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDateminus1);

		cal.add(Calendar.DATE, 10);

		Date DDueDate1= cal.getTime();

		DueDate =df.format(DDueDate1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		
		////////////////////////////////////
/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(3000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 
	 
	 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();
	 
	 
	 test.log(LogStatus.PASS,"Click on Go button");	
	    driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 driver.findElement(By.name("menu")).sendKeys("Edit");
		 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	   driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Dismissed");
	    test.log(LogStatus.PASS, "select status as  Dismissed"); 
	 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);			
		
		/* driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
		 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
		 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
		 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
		
		
	 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
	 test.log(LogStatus.PASS, "Entered Attorny Name");
	 
	 
	 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
		test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
		test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
		Thread.sleep(500);			
		driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
		test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);*/
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status Dismissed is Saved");
	 
		 Thread.sleep(50000);
	//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
		 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
		 
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Dismissed");
		 
			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
}


public void Bankrupt_Dismissed(String SSN,String FileName) throws Exception
	{
 Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			//String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
			String AttorneyPhone = TestData.getCellData(sheetName,"PhoneNumber",row);
			String AttorneyP1 = AttorneyPhone.substring(0, 3);
	        String AttorneyP2 = AttorneyPhone.substring(3, 6);
	        String AttorneyP3 = AttorneyPhone.substring(6, 10);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			System.out.println(AdminURL);
			System.out.println(AttorneyPhone);
			///////////////////////////////////////
			this.Login(UserName,Password,StoreID);
	
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

		//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			

			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, -2);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			
			////////////////////////////////////
	/*		driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");*/
			driver.manage().window().maximize();
			 Thread.sleep(1000);
			 
			 
 
 driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
 test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
 driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
 test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
 //Click Login Button
 driver.findElement(By.name("login")).click();
 test.log(LogStatus.PASS, "Clicked on Submit button");
 Thread.sleep(10000);
 Thread.sleep(8000);
 driver.switchTo().frame("topFrame");
 WebDriverWait wait = new WebDriverWait(driver, 10000);					   
 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 
 
 driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Transactions");
	Thread.sleep(10000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(10000);
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	 driver.findElement(By.linkText("Borrower")).click();
	 test.log(LogStatus.PASS, "Clicked on Borrower");
	 
	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
     driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
	test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		
	
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			} 
 
	  
	       driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");		 
		    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			Thread.sleep(5000);
			Actions action = new Actions(driver);
			action.moveByOffset(200,100).perform();
			Thread.sleep(10000);
			action.click();
			Thread.sleep(5000);			
			
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Click on submit Button");  
			
	  
	  
	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 
		 
		 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		
		 
		 test.log(LogStatus.PASS,"Click on Go button");		 

	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 Thread.sleep(3000);
		 if( driver.findElement(By.name("loanCode")).isDisplayed())
		 {
		 driver.findElement(By.name("loanCode")).click();
		 test.log(LogStatus.PASS, "Selecting Check box for loan");
		 }
		 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Bankrupt");
		 test.log(LogStatus.PASS, "select status as :");
		 
		 
		 

		 
		 
		    driver.findElement(By.name("bankruptcyFilingDate1")).sendKeys(DueDate1.trim());			
			test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
			Thread.sleep(500);		    
			driver.findElement(By.name("bankruptcyFilingDate2")).sendKeys(DueDate2.trim());			
			test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
			Thread.sleep(500);			
			driver.findElement(By.name("bankruptcyFilingDate3")).sendKeys(DueDate3.trim());			
			test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
			
			 driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
			 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
			 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
			 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
			
			
		 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
		 test.log(LogStatus.PASS, "Entered Attorny Name");
		 
		 
		 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
			test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
			Thread.sleep(500);		    
			driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
			test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
			Thread.sleep(500);			
			driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
			test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);
			
			driver.findElement(By.name("bt_AddBankruptcy")).click();			
			 test.log(LogStatus.PASS, "Status BNKPending is Saved");
		 
			 Thread.sleep(50000);
		//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
			 if( driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
			 {
			 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
			 }
				driver.close();

				driver = new InternetExplorerDriver();
				
			}

		}		 
		 
		 
		 
		 /*if(driver.findElement(By.name("submitButton")).isDisplayed())
			{
			 test.log(LogStatus.PASS, "Store Aging is Successfully ");
				driver.findElement(By.name("submitButton")).click();
			}
		 else
			{
				test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
			}*/
		//driver.close();
}
		


public void EODProcessing_with_recordsChecking(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			Thread.sleep(5000);	    

			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Daily Processing");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("eod")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			Thread.sleep(4000);
			// driver.findElement(By.name("requestBean.comments")).click();
			driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
			// requestBean.comments
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			Thread.sleep(4000);
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}
			Thread.sleep(4000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			
			//String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
			String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

			driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);

			
			test.log(LogStatus.PASS,"Enter the Balance 50");
			driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
			test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
			driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
			test.log(LogStatus.PASS, "Notes Entered ");	
			driver.findElement(By.name("bt_AddDrawer")).click();
			test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
			Thread.sleep(5000);
			
			 WebDriverWait wait = new WebDriverWait(driver, 10000);
			 
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")));
			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")).click();
			
			test.log(LogStatus.PASS, "Clicked on Next");


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}



			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			
			Thread.sleep(5000);
			 
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")));
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			
			test.log(LogStatus.PASS, "Clicked on Next");
			
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			
			Thread.sleep(5000);
			
			try { 
							
					if( driver.findElement(By.name("achDepRecLoc")).isDisplayed())
					//if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[6]")).isDisplayed())
					{									        								
										
						//driver.findElement(By.name("achDepRecLoc")).isDisplayed();
						test.log(LogStatus.PASS, "LOC ACH  Deposit Available");
						
					}
					else{
						test.log(LogStatus.PASS, "LOC ACH  Deposit Not Available.");
					}
			
			}
			catch (Exception e) {
				//do what you normally would if you didn't have the alert.

			

		
			
				test.log(LogStatus.PASS, "LOC ACH  Deposit record Not Available.");
			}
			

			Thread.sleep(5000);
			
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
			test.log(LogStatus.PASS, "Bag number is provided as 34");
			driver.findElement(By.name("finishdeposit")).click();
			test.log(LogStatus.PASS, "Clicked on Finish Deposit");
			test.log(LogStatus.PASS, "StatmentGeneration EOD Processing Completed");
			Thread.sleep(4000);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}



		}
		
		
	}
}


public void NACHA(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			appUrl = AppURL;


			CSRLoginpage login = new CSRLoginpage();

			login.Login(UserName, Password, StoreID, driver, AppURL, test);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	

			appUrl = AppURL;


			Thread.sleep(5000);
			Thread.sleep(1000);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			


			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

		//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			                                          
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);



			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   

			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);


			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Green Bank")).click();
			test.log(LogStatus.PASS, "Clicked on Green Bank");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Green Bank NACHA File")).click();
			test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);*/

			String DDueDate[] =DueDate.split("/");

			Date DDueDateminus1 = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDateminus1);
			cal.add(Calendar.DATE, Days);
			Date DDueDate1= cal.getTime();
			DueDate =df.format(DDueDate1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];



			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			driver.findElement(By.name("beginMonth")).click();
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process NACHA file successfully.");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process NACHA is not updated successfully.");
			}




		}
	}
}

public void ACH_Deposit(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			appUrl = AppURL;


			CSRLoginpage login = new CSRLoginpage();

			login.Login(UserName, Password, StoreID, driver, AppURL, test);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	

			appUrl = AppURL;


			Thread.sleep(5000);
			Thread.sleep(1000);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");



			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);



			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   

			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);


			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");
			Thread.sleep(5000);
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");
			Thread.sleep(5000);
			driver.findElement(By.linkText("LOC Pre ACH Deposit")).click();
			test.log(LogStatus.PASS, "Clicked on LOC Pre ACH Deposit");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);

			String DDueDate[] =DueDate.split("/");

			Date DDueDateminus1 = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDateminus1);
			cal.add(Calendar.DATE, Days);
			Date DDueDate1= cal.getTime();
			DueDate =df.format(DDueDate1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];



			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			driver.findElement(By.name("beginMonth")).click();
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "LOC Pre ACH Deposit Process  successfully.");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process LOC Pre ACH Deposit is not updated successfully.");
			}




		}
	}
}
	
public void ACHReturnPosting(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		//String age_store = TestData.getCellData(sheetName, "AgeStore",row);
		//int Age_store = Integer.parseInt(age_store);
		//System.out.println(Age_store);
		//System.out.println("age_store:::"+age_store);
		//int Days= Integer.parseInt(age_store);
		
		System.out.println(AdminURL);
	

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);


		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
		test.log(LogStatus.PASS, "Username is entered: admin");			        
		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
		//Click Login Button
		driver.findElement(By.name("login")).click();
		test.log(LogStatus.PASS, "Clicked on Submit button");
		Thread.sleep(8000);
		
	
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
		test.log(LogStatus.PASS, "Clicked on Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
		driver.findElement(By.linkText("ACH")).click();
		test.log(LogStatus.PASS, "Clicked on ACH");
		Thread.sleep(5000);
		driver.findElement(By.linkText("LOC")).click();
		test.log(LogStatus.PASS, "Clicked on LOC");
		
		driver.findElement(By.linkText("ACH Return")).click();
		test.log(LogStatus.PASS, "Clicked on ACH Return");
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		Thread.sleep(5000);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");

		driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);					  	        			   
			//Click Login Button
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");

			for( String winHandle1 : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle1);
			}			
			 driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
		

				driver.findElement(By.name("requestBean.chkName")).click();
					test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					  	        			   
					//Click Login Button
					driver.findElement(By.name("rtnReasonId")).sendKeys("R01-Insufficient Funds");
					test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");
					driver.findElement(By.name("CmdReturnPosting")).click();
					test.log(LogStatus.PASS, "Clicked on ACH Return Posting button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
				
if(driver.findElement(By.name("Ok")).isDisplayed())
{
	driver.findElement(By.name("Ok")).click();
	test.log(LogStatus.PASS, "ACH Return Posting Done Successfull");	
}


	}
}
}

public void LineStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String Linestatus=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
					 Linestatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
					 
					//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
					 test.log(LogStatus.PASS,"Line status is ."+Linestatus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(Linestatus);	
			    //driver.close();

		}
	}
}
public void Bankrupt(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		///////////////////////////////////////
		this.Login(UserName,Password,StoreID);

		Thread.sleep(5000);
		Thread.sleep(1000);
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
		test.log(LogStatus.PASS, "Clicked on Loan Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("li[id='911101']")).click();			
		test.log(LogStatus.PASS, "Clicked on Transactions");		
		driver.switchTo().frame("main");		
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit1")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}				    
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");


		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		}
		test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("History");
		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.id("go_Button")).click();  
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String DueDate=null;

	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
		//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
		test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
		System.out.print(DueDate);	
		driver.close();

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
		String DDueDate[] =DueDate.split("/");


		Date DDueDateminus1 = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDateminus1);

		cal.add(Calendar.DATE, -2);

		Date DDueDate1= cal.getTime();

		DueDate =df.format(DDueDate1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		
		////////////////////////////////////
/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 

  
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(5000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
		
  
  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 
	 
	 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		
	 
	 test.log(LogStatus.PASS,"Click on Go button");		 

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Bankrupt");
	 test.log(LogStatus.PASS, "select status as :");	 
	 
	 driver.findElement(By.name("bankruptcyFilingDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("bankruptcyFilingDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("bankruptcyFilingDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
		
		 driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
		 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
		 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
		 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
		
		
	 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
	 test.log(LogStatus.PASS, "Entered Attorny Name");
	 
	 
	 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
		test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
		test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
		Thread.sleep(500);			
		driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
		test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status BNKPending is Saved");
	 
		 Thread.sleep(50000);
	//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
		 if( driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
		 {
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
		 }
		 driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
	 
	 
	 /*if(driver.findElement(By.name("submitButton")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "Store Aging is Successfully ");
			driver.findElement(By.name("submitButton")).click();
		}
	 else
		{
			test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
		}*/
	//driver.close();
}

public void Bankrupt_Deceased(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		String Bankstatus = null;
		///////////////////////////////////////
		this.Login(UserName,Password,StoreID);

		Thread.sleep(5000);
		Thread.sleep(1000);
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
		test.log(LogStatus.PASS, "Clicked on Loan Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("li[id='911101']")).click();			
		test.log(LogStatus.PASS, "Clicked on Transactions");		
		driver.switchTo().frame("main");		
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit1")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}				    
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");


		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		}
		test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("History");
		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.id("go_Button")).click();  
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String DueDate=null;

	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
		//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
		test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
		System.out.print(DueDate);	
		driver.close();

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
		String DDueDate[] =DueDate.split("/");


		Date DDueDateminus1 = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDateminus1);

		cal.add(Calendar.DATE, 10);

		Date DDueDate1= cal.getTime();

		DueDate =df.format(DDueDate1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		
		////////////////////////////////////
/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(3000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 
	 
	 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();
	 
	 
	 test.log(LogStatus.PASS,"Click on Go button");	
	    driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 driver.findElement(By.name("menu")).sendKeys("Edit");
		 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	   driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
	    test.log(LogStatus.PASS, "select status as  Discharged"); 
	 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
		
		/* driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
		 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
		 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
		 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
		
		
	 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
	 test.log(LogStatus.PASS, "Entered Attorny Name");
	 
	 
	 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
		test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
		test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
		Thread.sleep(500);			
		driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
		test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);*/
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status BNKPending is Saved");
	 
		 Thread.sleep(50000);
	//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
		 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
		 
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Deceased ::+Bankstatus");
		 
			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
}

public void Active_Military_Start(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		System.out.println(AdminURL);
		test.log(LogStatus.INFO, "Scheduler-Store Aging");

		System.out.println(ProductID);	
		String AppURL = TestData.getCellData(sheetName,"AppURL",row);
		appUrl = AppURL;
		driver=new InternetExplorerDriver();
		this.Login(UserName,Password,StoreID);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		Thread.sleep(5000);
		driver.switchTo().frame("topFrame");
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
		Thread.sleep(1000);
        driver.findElement(By.cssSelector("li[id='900000']")).click();				
		test.log(LogStatus.PASS, "Clicked on Borrower");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='988190443']")));
		driver.findElement(By.cssSelector("li[id='988190443']")).click();			
		test.log(LogStatus.PASS, "Clicked on Active Military");	
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().frame("main");
		driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//*[contains(text(),'Go')]")).click();			
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
	
		//Thread.sleep(1000);
		//driver.findElement(By.name("menu1")).sendKeys("Active Military");
		driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		//driver.findElement(By.xpath("//html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input]")).click();
		///html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input
		test.log(LogStatus.PASS, "Click on GO Button");
		
		try { 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    //if alert present, accept and move on.														
			
		}
		catch (NoAlertPresentException e) {
		    //do what you normally would if you didn't have the alert.
		}
		test.log(LogStatus.PASS, "Accept the Alert");				
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
	
		Thread.sleep(2000);
		driver.findElement(By.name("requestBean.activeMilitaryType")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("finishBank")).click();
		
		try { 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    //if alert present, accept and move on.														
			
		}
		catch (NoAlertPresentException e) {
		    //do what you normally would if you didn't have the alert.
		}
		
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='ok' and @type='button']")).click();
		//driver.findElement(By.xpath("///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input]")).click();
		///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input
		test.log(LogStatus.PASS, "Active Military Transaction Completed Successfully.");
	}
}
}
public void Active_Military_End(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		System.out.println(AdminURL);
		test.log(LogStatus.INFO, "Scheduler-Store Aging");

		System.out.println(ProductID);	
		String AppURL = TestData.getCellData(sheetName,"AppURL",row);
		appUrl = AppURL;
		driver=new InternetExplorerDriver();
		this.Login(UserName,Password,StoreID);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		Thread.sleep(5000);
		driver.switchTo().frame("topFrame");
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
		Thread.sleep(1000);
        driver.findElement(By.cssSelector("li[id='900000']")).click();				
		test.log(LogStatus.PASS, "Clicked on Borrower");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='988190443']")));
		driver.findElement(By.cssSelector("li[id='988190443']")).click();			
		test.log(LogStatus.PASS, "Clicked on Active Military");	
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().frame("main");
		driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//*[contains(text(),'Go')]")).click();			
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
	
		//Thread.sleep(1000);
		//driver.findElement(By.name("menu1")).sendKeys("Active Military");
		driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		//driver.findElement(By.xpath("//html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input]")).click();
		///html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input
		test.log(LogStatus.PASS, "Click on GO Button");
		
		try { 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    //if alert present, accept and move on.														
			
		}
		catch (NoAlertPresentException e) {
		    //do what you normally would if you didn't have the alert.
		}
		test.log(LogStatus.PASS, "Accept the Alert");				
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
	
		Thread.sleep(2000);
		driver.findElement(By.name("finishBank")).click();
		
					
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='ok' and @type='button']")).click();
		//driver.findElement(By.xpath("///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input]")).click();
		///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input
		test.log(LogStatus.PASS, "Active Military Transaction Completed Successfully.");
	}
}
}

public void Represent_Status(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{

				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			/*String CheckStaus=null;
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed();

			test.log(LogStatus.PASS," RCC schdule is Displayed");
			CheckStaus=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
			test.log(LogStatus.PASS," RCC inEligible Reason :::"+CheckStaus)*/;
			// //*[@id="revolvingCreditHistTable"]/tbody/tr[14]/td[2]/span[2]
			//	System.out.print(CheckStaus);	
			//driver.close();

                  List<WebElement> rows =driver.findElements(By.xpath("//*[@id='achHistoryTable']/tbody/tr"));
					int ScdCnt = rows.size();
					test.log(LogStatus.PASS, "Rows count is::"+ScdCnt);
					test.log(LogStatus.PASS, "Represent Record Displayed::");
					//String ScdCnt1= String.valueOf(ScdCnt);
					

		}
	}
}

public void ACH_Revoke(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("ACH Revoke");
			test.log(LogStatus.PASS, "RCC Revoke Transaction is selected");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			try { 
			    Alert alert = driver.switchTo().alert();
			    alert.accept();
			    test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
			    //if alert present, accept and move on.														
				
			}
			catch (NoAlertPresentException e) {
			    //do what you normally would if you didn't have the alert.
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
		    test.log(LogStatus.PASS, "Navigated to Customer RCC Authorization Details");
		    driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]")).click();
			//driver.findElement(By.name("bt_BankDetails")).click(); 
			test.log(LogStatus.PASS, "Clicked on ACH Revoke");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.name("bt_BankDetails")).isDisplayed())
			{
			 test.log(LogStatus.PASS, "ACH Revoke Transaction completed successfully.");
				driver.findElement(By.name("bt_BankDetails")).click(); 
				test.log(LogStatus.PASS, "Clicked on Ok button");
			}
			else
			{
				test.log(LogStatus.PASS, "ACH Revoke Transaction not completed successfully.");
			}
			Thread.sleep(5000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.PASS, "History Transaction is selected");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.PASS, "Navigated to History screen");
			if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]")).isDisplayed())
			{
				test.log(LogStatus.PASS, "ACH Tracking Status is displayed in History screen");
				  WebElement htmltable=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]"));	
				  
					List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
					int count=0;							
					 count=driver.findElements(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]/tbody/tr[1]/td[1]/table/tbody/tr")).size();
					 
					 System.out.println("current row num "+count);
					 String Details=null;
					for(int rnum=0;rnum<count;rnum++)
					{
						System.out.println("current row num "+rnum);						
					List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
					
					System.out.println("columns Count "+columns.size());
						
					for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
					{					
						 Details=columns.get(cnum).getText();
						 test.log(LogStatus.PASS, "RCC Tracking Status Details"+Details);
													
					}
					
					
					}
					
			}
			else
			{
				test.log(LogStatus.PASS, "RCC Tracking Status is not displayed in History screen");
			}
		}
	}
}


	@Test (priority=0)
	
	 public void RegistrationTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_StGn_Dep_RTN_ReDep_ACHRevoke_checkpostDeporNot_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		//int lastrow=TestData.getLastRow("Borrower");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		       // System.out.println(Password);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"_S.No:315"+"_"+PayFrequency+"_"+CollateralType,"LOC Init WKY/BIM/BI) Customer =>Draw => Stmnt =>Dep =>Return =>Rep Dep record posted =>ACH Revoke =>Check Rep deposit is posted or not ?");
		        appUrl = AppURL;     
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoanDraw(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawLoan(SSN, FileName);
		        this.AgeStore(SSN, FileName, -1);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
	            this.NACHA(SSN, FileName, -1);
		        this.ACH_Deposit(SSN, FileName, 0);
		        this.ACHReturnPosting(SSN, FileName);
		        this.Represent_Status(SSN, FileName);
		        this.ACH_Revoke(SSN, FileName);
		        this.Represent_Status(SSN, FileName);
		        		        	       
			          		        		        	        	        
		}
		}
		
	
			}
	@AfterMethod
	@AfterTest
	public void tearDown() {
		// Ending Test
		reports.endTest(test);

		// writing everything into HTML report
		reports.flush();
	}

	@AfterClass
	public void quit() {
		// Closing browser
		driver.quit();

	}

	public void takeScreenShot(WebDriver driver, String filePath) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
	
//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]





