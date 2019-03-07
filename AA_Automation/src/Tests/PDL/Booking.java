package Tests.PDL;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utilities.ExtentReports.Excel;
	
public class Booking {
	



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
					
			String filename="BorrowerRegistration_NewLoan_"+timestamp+".html";
			//System.out.print(filename);
			reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/BorrowerRegistration_NewLoan/"+filename, true);
			//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/PDL/ShortListedScenarios.html", true);
		}

		@BeforeTest
		public void setup() throws IOException {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
			//System.setProperty("webdriver.ie.driver","E:/Ncp_Workspace/Selenium/IEDriverServer.exe");
			driver = new InternetExplorerDriver();		
			//appUrl = "http://192.168.2.203/cc/demoIndex.do";
		}

	
		
	/*	public void Booking(String FileName) throws Exception{
			
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);     		
			int lastrow=TestData.getLastRow("Borrower_Registration");
			
			String sheetName="Borrower_Registration";		
			for(int row=2;row<=lastrow;row++)
			{		
				//String AppURL = TestData.getCellData(sheetName,"Url",row);
				//appUrl = AppURL;
					//String URl = TestData.getCellData(sheetName,"Url",row);
				String UserName = TestData.getCellData(sheetName,"Uname",row);     
		       String Password = TestData.getCellData(sheetName,"Password",row);
		       String Captcha = TestData.getCellData(sheetName,"Captcha",row);
		       
		       String DropDownDistrict = TestData.getCellData(sheetName,"DropDownDistrict",row);	
		       String District = TestData.getCellData(sheetName,"District",row);
		       String Mandal = TestData.getCellData(sheetName,"Mandal",row);
		       String Village = TestData.getCellData(sheetName,"Village",row);
		       String VehicleNo = TestData.getCellData(sheetName,"VehicleNo",row);
		       
		       
		     
		   
		       
				        Thread.sleep(5000);
				 
				WebDriverWait wait = new WebDriverWait(driver, 1000);	
				
				
				driver.findElement(By.id("ccMain_DWTF")).sendKeys(UserName);
				driver.findElement(By.id("ccMain_DVPB")).sendKeys(Password);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
				driver.findElement(By.id("ccMain_txtEnterCode")).sendKeys(Captcha);
				driver.findElement(By.id("btnLogin")).click();
				
				driver.findElement(By.id("jUIDDA")).click();
				
				driver.findElement(By.id("ccMain_yDaNhd")).sendKeys(DropDownDistrict);
				
				driver.findElement(By.id("lblstockpointid1")).click();
				
				driver.findElement(By.id("ccMain_ddlsandpurpose")).sendKeys("Domestic");
			   	driver.findElement(By.id("ccMain_txtVehzNo")).sendKeys(VehicleNo);// Vechile number
			   	driver.findElement(By.id("ccMain_ddldeldistrict")).sendKeys(District);
			   	driver.findElement(By.id("ccMain_ddldelMandal")).sendKeys(Mandal);
			   	driver.findElement(By.id("ccMain_ddldelvillage")).sendKeys(Village);
			   	
			   	Thread.sleep(500000);
			}
		} */
			   	
			
				
		       				
				
		public boolean isAlertPresent(){
			 try{
			  driver.switchTo().alert();
			  return true;
			 }catch(NoAlertPresentException ex){
			  return false;
			 }
		}
		@Test (priority=0)
		
		 public void RegistrationTest() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "QC_Booking_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);   
			int lastrow=TestData.getLastRow("Borrower_Registration");
			String sheetName="Borrower_Registration";
			System.out.println(lastrow);
			for(int row=2;row<=lastrow;row++)
			{
				String RunFlag = TestData.getCellData(sheetName,"Run",row);
				//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
			   	String AppURL = TestData.getCellData(sheetName,"Url",row);
				String UserName = TestData.getCellData(sheetName,"Uname",row);     
			       String Password = TestData.getCellData(sheetName,"Password",row);
			       String Captcha = TestData.getCellData(sheetName,"Captcha",row);
			       
			       String DropDownDistrict = TestData.getCellData(sheetName,"DropDownDistrict",row);	
			       String District = TestData.getCellData(sheetName,"District",row);
			       String Mandal = TestData.getCellData(sheetName,"Mandal",row);
			       String Village = TestData.getCellData(sheetName,"Village",row);
			       String VehicleNo = TestData.getCellData(sheetName,"VehicleNo",row);
			       //test = reports.startTest("BorrowerRegistration_NewLoan_"+Header, "New Loan");
			       
			       
			       
			       
			       
			       appUrl = AppURL;
			       

			        Thread.sleep(5000);
			 
			WebDriverWait wait = new WebDriverWait(driver, 1000);	
			
			
			driver.findElement(By.id("ccMain_DWTF")).sendKeys(UserName);
			driver.findElement(By.id("ccMain_DVPB")).sendKeys(Password);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			driver.findElement(By.id("ccMain_txtEnterCode")).sendKeys(Captcha);
			driver.findElement(By.id("btnLogin")).click();
			
			driver.findElement(By.id("jUIDDA")).click();
			
			driver.findElement(By.id("ccMain_yDaNhd")).sendKeys(DropDownDistrict);
			
			driver.findElement(By.id("lblstockpointid1")).click();
			
			driver.findElement(By.id("ccMain_ddlsandpurpose")).sendKeys("Domestic");
		   	driver.findElement(By.id("ccMain_txtVehzNo")).sendKeys(VehicleNo);// Vechile number
		   	driver.findElement(By.id("ccMain_ddldeldistrict")).sendKeys(District);
		   	driver.findElement(By.id("ccMain_ddldelMandal")).sendKeys(Mandal);
		   	driver.findElement(By.id("ccMain_ddldelvillage")).sendKeys(Village);
		   	
		   	Thread.sleep(500000);
		
		   	
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

	}




