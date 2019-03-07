package Tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Tests.ExecuteScripts;
import Utilities.ExtentReports.Excel;

public class LOCI_Draw_Stmt_Dep_Clear_BNK_RTN_StatusBNK {
	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	public String DueDate;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;


	@BeforeClass

	public synchronized void initialize() {

		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());


		String filename="AA_LOC_RegressionScenario_Scenario.No_133_"+timestamp+".html";

		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/AA_LOC_RegressionScenarios_/AA_LOC_RegressionScenario_Scenario.No_133_/"+filename, true);
	}

	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}


	public void Login (String username,String password,String storenumber) throws InterruptedException {										
		//Launch URL
		driver.get(appUrl);
		test.log(LogStatus.INFO, "CSR Application is launched");
		driver.manage().window().maximize();
		String usenameId = "loginRequestBean.userId";
		String passwordId = "loginRequestBean.password";
		String StoreId = "loginRequestBean.locNbr";
		String Login = "login";


		driver.findElement(By.name(usenameId)).clear();
		driver.findElement(By.name(usenameId)).sendKeys(username);
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Username is entered: "+username);

Thread.sleep(2000);
		driver.findElement(By.name(passwordId)).clear();
		driver.findElement(By.name(passwordId)).sendKeys(password);
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Password is entered: "+password);
		Thread.sleep(2000);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		Thread.sleep(2000);
		driver.findElement(By.name(Login)).click();
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Clicked on Submit button");
	}



	public boolean IsElementExits(String Value) {
		int secondsToWait = 5;

		try {
			new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Value)));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}


	public void IETaskKiller() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}
	
	public void NewLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);   	
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
				System.out.println(ProductID);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
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
			
				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
				 String Parent_Window = driver.getWindowHandle();  
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 //	Selection of Product based on the Name provided in Test Data
				 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				 {
					 test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " +SSN +" :: Starts");
					 
					 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
				//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
				
					if(ProductName.equals("Line of Credit"))
					{
						
						if(StoreID.equals("4330"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						}
						if(StoreID.equals("4324"))
						{
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						
						}
						if(StoreID.equals("4325"))
						{
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						
						}
						if(StoreID.equals("1343"))
						{
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
					}

					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(6000);
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
					driver.findElement(By.id("LoanButtonId")).click();

					test.log(LogStatus.PASS, "Clicked on New Loan button");
					if(ProductID.equals("LOC"))
					{
					
						test.log(LogStatus.INFO, "Navigated to New Loan Screen");
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
					//	driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
					//	test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
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
							
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
						
					//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
				 	}
					
			}
		}

	}



	public void DrawLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
					test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
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
							 driver.findElement(By.name("loanAmt")).sendKeys("300");	
								Thread.sleep(2000);
							 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 300");
							 driver.findElement(By.name("disbAmtFirst")).sendKeys("300");					
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 300");
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



	public void Bankrupt(String SSN,String FileName) throws Exception
		{
	 Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
				//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

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
			 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys(BNKstatus);
			 test.log(LogStatus.PASS, "select status as :" + BNKstatus);
			 
			 
			 

			 
			 
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
			




	public void BankruptStatus_1(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

					//driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();	
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




	public void Check_PPNStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				
				if(driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']")).isDisplayed())
						{
					test.log(LogStatus.PASS,"Payment Plan status is ::Active"  );
						}
				else
				{
					test.log(LogStatus.PASS,"Payment Plan status is ::InActive"  );
				}
				// //*[@id="PPNScheduleHistoryTable"]/tbody/tr[2]/td[2]
				/*String PaymentStatus=null;
				PaymentStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is ::"  +PaymentStatus);
				String LineStatus=null;
				LineStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS,"Line status is ::"  +LineStatus);
				String WOReason=null;
				WOReason =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS,"Reason for Customer got WriteOff ::"  +WOReason);*/


			}
		}
	}


	
	public void WOStatus_Bankrupt(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			


			}
		}
	}

	public void WORECStatus_Bankrupt(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			


			}
		}
	}

	
	public void Check_PPN(String SSN,String FileName,int Days) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

				for(int i=1;i<=n;i++)
				{
					String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getAttribute("value");

					if(transactino_value.contains("Plan"))
					{
						test.log(LogStatus.PASS, "PPN Option is Available");	
					}
					else
					{
						test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
						test.log(LogStatus.PASS, "PPN Option is not Available");	
					}
				}

			}
		}
	}



	public void Check_Draw(String SSN,String FileName) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

				for(int i=1;i<=n;i++)
				{
					String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getAttribute("value");

					if(transactino_value.contains("Draw"))
					{
						test.log(LogStatus.PASS, "Draw Option is Available");	
					}
					else
					{
						test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
						test.log(LogStatus.PASS, "Draw Option is not Available");	
					}
				}

			}
		}
	}


	
public void Writoff_Recovery(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
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
				String PaymentAMT= null;
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
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				driver.findElement(By.name("transactionList")).sendKeys("Write Off Recovery");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//String PaymentAmount=null;
				
				PaymentAMT = driver.findElement(By.name("totalDue")).getAttribute("value");
				test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);

				driver.findElement(By.name("requestBean.tenderType")).sendKeys("Cash");
				test.log(LogStatus.PASS, "Select the Disb Type");
				
				driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAMT);
				test.log(LogStatus.PASS, "Enter the Tender Amount");

				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
				
				 if(driver.findElement(By.name("Ok")).isDisplayed())
				 {
					 test.log(LogStatus.PASS, "Write off Recovery completed Sucessfully");
					 driver.findElement(By.name("Ok")).click();
				 }
						 
					 
					 else
					 {

				test.log(LogStatus.PASS, "Write off Recovery not completed Sucessfully");
					 }
			

			 
			}
			}

			}
		

public void WORecovery_Void(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
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
			driver.findElement(By.name("transactionList")).sendKeys("Void");
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

			//String PaymentAmount=null;

			driver.findElement(By.name("defPaymentRequestBeanRC.tenderType")).sendKeys("Cash");
			test.log(LogStatus.PASS, "Select the Tender Type:: Cash");

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Void Write off Recovery Payment ");

			//test.log(LogStatus.PASS, "Void Write off Recovery Payment completed Sucessfully");
//Ok
		    if(driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).isDisplayed())
				{
			// /html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input[2]
		    	
		    	driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).click();
		    	test.log(LogStatus.PASS, "Void Write off Recovery Payment completed Sucessfully");
				}
		
		else
		{
			test.log(LogStatus.PASS, "Void Write off Recovery Payment Not completed Sucessfully");
		}
		}

		}
	}






public void StatementGeneration_Interest(String SSN,String FileName) throws Exception
{

        Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
        int lastrow=TestData.getLastRow("NewLoan");
        System.out.println("NewLoan "+lastrow);
        String sheetName="NewLoan";
        for(int row=2;row<=lastrow;row++)
        {
                String RegSSN = TestData.getCellData(sheetName,"SSN",row);
                String SSN1 = SSN.substring(0, 3);
                String SSN2 = SSN.substring(3,5);
                String SSN3 = SSN.substring(5,9);
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
                       /// String DueDate=null;

                        /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
                        }

                         //String winHandleBefore = driver.getWindowHandle();
                         for(String winHandle : driver.getWindowHandles()){
                                    driver.switchTo().window(winHandle);
                                }
                         Thread.sleep(8000);
                          // driver.findElement(By.xpath("//*[@id='home']")).click();*/

                        //DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                        DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                                                               //*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
                        test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);
                        //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
                        System.out.print(DueDate);
                        driver.close();

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
                        String DueDate0[] =DueDate.split("/");
                        String DueDate1 = DueDate0[0];
                        String DueDate2 = DueDate0[1];
                        String DueDate3 = DueDate0[2];
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("topFrame");
                        Thread.sleep(8000);
                    	Thread.sleep(8000);
                        WebDriverWait wait = new WebDriverWait(driver, 10000);
   					    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
                        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Transactions");
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        Thread.sleep(5000);
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Borrower")).click();
                        test.log(LogStatus.PASS, "Clicked on Borrower");
                        Thread.sleep(5000);
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Process Date Change")).click();
                        test.log(LogStatus.PASS, "Clicked on Process Date Change");
                        //Thread.sleep(2000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
                         Actions action = new Actions(driver);
                         action.moveToElement(element).build().perform();
                        Thread.sleep(6000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("storeCode")).click();
                        //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
                        driver.findElement(By.name("storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
                        Thread.sleep(2000);
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
                                driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
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
                        driver.findElement(By.linkText("Unsecure Loc Statement")).click();
                        test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

                        ///Thread.sleep(6000);  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        // /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        WebElement elements = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
                        Actions actions = new Actions(driver);
                        actions.moveToElement(elements).build().perform();

                        Thread.sleep(6000);

                        driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);


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
                        driver.findElement(By.name("submit")).click();
                        test.log(LogStatus.PASS, "Clicked on submit button");
                        test.log(LogStatus.PASS, "Statement Generated");
                        this.Interest_AmtCalc(SSN, FileName, 0);
                }
            
        }
}


	public void DrawerDeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Deassign");	
				driver.switchTo().frame("main");		
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				//driver.findElement(By.name("drawerDeassignRequestBean.currentCashBalance")).sendKeys("0");
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Banker PIN# is enetered as"+Password);	
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Click on Finish De-assign Button");
				try{

					driver.close();
				}
				catch (Exception e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);				
				driver.findElement(By.name("drawerdeassign")).click();
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
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
						//List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));												

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select")).sendKeys("Delete");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).click();						
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
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
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
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


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

			/// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[7]/td[3]

			// driver.findElement(By.name("requestBean.amount")).sendKeys("SafeOverShortAmount");
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
public void StoreInfo(String SSN,String FileName) throws Exception
	{
 Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			test.log(LogStatus.INFO, "Admin portal is launched");
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
 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Store Setup')]")));
	driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Store Setup");
	Thread.sleep(10000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(10000);
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Config")));
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
public void Safeassign(String SSN,String FileName) throws Exception{
 	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
    
    ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input
    ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input
   // if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).isDisplayed())
    if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
    {

    	 test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
    	 driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
    	 //driver.findElement(By.name("done")).click();
    }
    else
    {
    	test.log(LogStatus.PASS,"Safe not assigned successfully with over/short.");
    }
}
}
}

public void Payments(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
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
						
							// String Pmt= driver.findElement(By.name("currentBalance")).getAttribute("value");
							// name="requestBean.paymentAmt"
							 driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("50");
							 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("50");
							test.log(LogStatus.PASS, "Tender Amt is entered as :: 50");							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Password is selected as "+Password);			
							 driver.findElement(By.name("Submit22")).click();
								test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
							 
							 Thread.sleep(2000);
					
							/* driver.findElement(By.id("btnADV_Yes")).click();
								test.log(LogStatus.PASS, "Clicked on Navigate to  Payoff Screen button ");																	
						
										
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 String Pmt1= driver.findElement(By.name("payOffAmount")).getAttribute("value");
								 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
								 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
								driver.findElement(By.name("tenderAmount")).sendKeys(Pmt1);
								test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt1);							
								 driver.findElement(By.name("password")).sendKeys(Password);
								 test.log(LogStatus.PASS, "Password is selected as "+Password);			
								 driver.findElement(By.name("Submit22")).click();
									test.log(LogStatus.PASS, "Clicked on Finish payOff button ");
								 
								 
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
									}*/
						/*		
							 
							 try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
									
								
								 
								    //do what you normally would if you didn't have the alert.
								}
							 */
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
									Thread.sleep(2000);
									 Thread.sleep(2000);
							/*	 if(driver.findElement(By.name("Ok")).isDisplayed())
									{*/
									 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
									/* driver.findElement(By.name("Ok")).click();*/
								/*	}
								 else
									{
										test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
									}*/
							
					    	
						 }
					
			}
			
		}
	}

public void AgeStore_LoanDate(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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


			if(ProductID.equals("PDL"))
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
			
			String DueDate=null;
			                                      //*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();

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
			driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

			driver.findElement(By.name("storeCode")).click();
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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
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



	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

	int lastrow=TestData.getLastRow("NewLoan");

	System.out.println("NewLoan "+lastrow);

	String sheetName="NewLoan";

	for(int row=2;row<=lastrow;row++)

	{

		String RegSSN = TestData.getCellData(sheetName,"SSN",row);

		if(SSN.equals(RegSSN))

		{



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

			driver.findElement(By.name("drawerassign")).click();

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

				test.log(LogStatus.PASS, "Click on the Deassign");

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

						//if alert present, accept and move on.

					}

					catch (NoAlertPresentException e) {

						//do what you normally would if you didn't have the alert.

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


				driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("900");

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

				driver.findElement(By.name("drawerassign")).click();

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

					test.log(LogStatus.PASS, "Click on the Deassign");

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

					driver.findElement(By.name("drawerassign")).click();

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

						test.log(LogStatus.PASS,"Drawer assigned successfully .");

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();

					}

					else

					{

						test.log(LogStatus.PASS,"Drawer not assigned successfully .");

					}

				}

				else

				{
					Thread.sleep(3000);
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())

					{

						test.log(LogStatus.PASS,"Drawer Assigned successfully with over/short.");


						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
					   
						

					}

					else

					{

						test.log(LogStatus.PASS,"Drawer not Assigned successfully with over/short.");

					}
					

				}
				
				
			}

		}

	}
	
	 Thread.sleep(2000);
	//    driver.quit();
	   // driver = new InternetExplorerDriver();

}


public void PayOffLoan(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

public boolean isAlertPresent(){
	try{
		driver.switchTo().alert();
		return true;
	}catch(NoAlertPresentException ex){
		return false;
	}
}



public void EODProcessing(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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


public void AgeStore(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

			driver.findElement(By.name("storeCode")).click();
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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}




		}
	}
}


public void DeliquentPaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			test.log(LogStatus.INFO, "DeliquentPaymentStatus with-SSN: " +SSN +" :: Starts");
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
					 test.log(LogStatus.INFO, "DeliquentPaymentStatus for-SSN: " +SSN +" :: is ::"+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			  //driver.close();//need to change to close
			  driver.quit();//Uncomment 
			  driver = new InternetExplorerDriver();

		}
	}
}
public void DeliquentPaymentStatus1(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			test.log(LogStatus.INFO, "DeliquentPaymentStatus with-SSN: " +SSN +" :: Starts");
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
					 test.log(LogStatus.INFO, "DeliquentPaymentStatus for-SSN: " +SSN +" :: is ::"+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);
				 String CheckStaus1=null;
				 CheckStaus1 = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
				 //                                      //*[@id="revolvingCreditHistTable"]/tbody/tr[10]/td[3]/span[2]
				//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
				 test.log(LogStatus.PASS,"Payment status is Cure"+CheckStaus1);
	             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
		   System.out.print(CheckStaus1);
			   
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			  //driver.close();//need to change to close
			  driver.quit();//Uncomment 
			  driver = new InternetExplorerDriver();

		}
	}
}



public void CurePaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
					 //                                      //*[@id="revolvingCreditHistTable"]/tbody/tr[10]/td[3]/span[2]
					//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
					 test.log(LogStatus.PASS,"Payment status is Cure."+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			 //   driver.close();//need to change to close
			 driver.quit();//Uncomment 
			    driver = new InternetExplorerDriver();

		}
	}
}


public void CustomerDefault(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			 test.log(LogStatus.INFO, "CustomerDefault with-SSN: " +SSN +" :: is ::"+"Starts");

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
			driver.quit();	//need to change to close
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
			cal.add(Calendar.DATE, 0);
			Date DDueDateminus1= cal.getTime();
			// String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
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



			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
			driver.findElement(By.linkText("Default Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Default Loc");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			//Thread.sleep(6000);
			/* WebElement element = driver.findElement(By.name("cancel"));
							        Actions action = new Actions(driver);								        
							        action.moveToElement(element).build().perform();*/

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
			 Thread.sleep(6000);
			 WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
			  Actions action = new Actions(driver);								        
			  action.moveToElement(element).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
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
			 test.log(LogStatus.INFO, "CustomerDefault with-SSN: " +SSN +" :: is ::"+"Completed");
			Thread.sleep(6000);


		}
	}
}

public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			 test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Starts");
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
			 test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Successful");
			System.out.print(CheckStaus);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.quit();//need to change to close
			 driver = new InternetExplorerDriver();

		}
	}
}

public void DefaultPaymentStatus1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			 test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Starts");
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

			test.log(LogStatus.PASS,"Default status is :: Null");
			 test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Successful");
			System.out.print(CheckStaus);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.quit();//need to change to close
			 driver = new InternetExplorerDriver();

		}
	}
}



public void Default_Payment(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			 test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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
			driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
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
			
			String PaymentAmount=null;
			
			PaymentAmount = driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");
			//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
			Thread.sleep(2000);
			driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Tender Type");
			
			driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAmount);
			test.log(LogStatus.PASS, "Enter the Tender Amount");
			
			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");
			
			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on Finish Payment Button");
			
			test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Successful");
		
		
		}
	}
}

public void Default_PartialPayment(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			 test.log(LogStatus.INFO, "Default_PartialPayment with-SSN: " +SSN +" :: is ::"+"Starts");
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
			driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
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
			
			//String PaymentAmount=null;
			
			//PaymentAmount = driver.findElement(By.name("totalDue")).getAttribute("value");
			//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
			driver.findElement(By.name("requestBean.paymentAmt")).clear();
			driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("10");
			driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Tender Type");
			
			driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("10");
			test.log(LogStatus.PASS, "Enter the Tender Amount");
			
			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");
			
			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on Finish Payment Button");
			
			test.log(LogStatus.INFO, "Default_PartialPayment with-SSN: " +SSN +" :: is ::"+"Successful");
		
		
		}
	}
}


/*public void Interest_AmtCalc(String SSN,String FileName,double d) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			 test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();

driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
			///  /html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]
			 String  LoanDt= driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();
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
			
			////////////
			driver.switchTo().frame("bottom");
			// String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
			 String Busdate[]=LoanDt.split(":");
			 String date = Busdate[1];
			
			 // 
			String  DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
               //*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
test.log(LogStatus.PASS, "Captured Date: "+DueDate);
			
			String DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
			String DDueDate[] =DueDate.split("/");
		
		
		

			//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
		
			

				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 0);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				 int month = Integer.valueOf(DueDate1);
					test.log(LogStatus.PASS, "month ::"+month);
				String DueDate2 = DueDate0[1];
				 int date = Integer.valueOf(DueDate2);
				 int day =date+1;
					test.log(LogStatus.PASS, "date ::"+day);
				String DueDate3 = DueDate0[2];
				int year = Integer.valueOf(DueDate3);
				test.log(LogStatus.PASS, "year ::"+year);
				
				LocalDate endofCentury = LocalDate.of(year, month, day); 

				String DDueDate4[] =LoanDt.split("/");


				Date DDueDateminus4 = df.parse(LoanDt);

				Calendar cal1 = Calendar.getInstance();

				cal1.setTime(DDueDateminus4);

				cal.add(Calendar.DATE, 0);

				Date DDueDat41= cal1.getTime();

				LoanDt =df.format(DDueDat41);
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				String DDueDat410[] =LoanDt.split("/");

				String DDueDat411 = DDueDat410[0];
				//Date month1 = format.parse(DDueDat411);
				 int month1 = Integer.valueOf(DDueDat411);
					test.log(LogStatus.PASS, "month1 ::"+month1);
				String DDueDat412 = DDueDat410[1];
				//Date date = format.parse(DDueDat412);
				 int date1 = Integer.valueOf(DDueDat412);
				 
				 test.log(LogStatus.PASS, "date1 ::"+date1);
				String DDueDat413 = DDueDat410[2];
				 int year1 = Integer.valueOf(DDueDat413);
				 test.log(LogStatus.PASS, "year1 ::"+year1);
				///Date year = format.parse(DDueDat413);
			Date date1 = df.parse(DDueDateminus0);
			Date date2=df.parse(DDueDateminus1);
			 //long diff = date1.getTime() - date2.getTime();
			 //Days diff = Days.daysBetween(date1, date2); 

			LocalDate endofCentury1 = LocalDate.of(year1, month1, date1); 
			Period diff = Period.between(endofCentury1, endofCentury);
			int a =diff.getDays();
				
			test.log(LogStatus.PASS, "Date Difference ::"+a);

			
			
			
			
		
			String InterestAmt=null;
			String CustFee=null;
			//*[@id="revolvingCreditDetailsTable"]/tbody/tr[5]/td[8]
			InterestAmt = driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr[5]/td[8]")).getText();
			test.log(LogStatus.PASS, "Calculated  InterestAmt :: "+InterestAmt);
			CustFee = driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr[4]/td[11]")).getText();
			test.log(LogStatus.PASS, "Calculated  CustFee :: "+CustFee);
			double RegAmt = Double.parseDouble(InterestAmt);
			double IntFee = (300*24.0*a/100)/365;
			test.log(LogStatus.PASS, "Expected  IntFee :: "+IntFee);
			//String ReqAmt = Double.toString(PerAmt);
			if(CollateralType.equals("CASH"))
			{
			double RegAmt1 = Double.parseDouble(CustFee);
			double CustomerFee = (300*0.7*a)/100;
			test.log(LogStatus.PASS, "Expected  CustFee :: "+CustomerFee);
			}
			if(CollateralType.equals("ACH"))
			{
			double RegAmt1 = Double.parseDouble(CustFee);
			double CustomerFee = (300*0.55*a)/100;
			test.log(LogStatus.PASS, "Expected  CustFee :: "+CustomerFee);
			}
	
		
		}
	}
}
*/
public void Interest_AmtCalc(String SSN,String FileName,double d) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			 test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();

driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
			///  /html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]
			 String  LoanDt= driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();
			                                             
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
			
			////////////
		/*	driver.switchTo().frame("bottom");*/
			// String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
		/*	 String Busdate[]=LoanDt.split(":");
			 String date = Busdate[1];*/
			
			 // 
/*			String  DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
               //*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
test.log(LogStatus.PASS, "Captured Date: "+DueDate);*/
			
		/*	String DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			*/	
		/*	String DDueDate[] =DueDate.split("/");*/
		
		
		

		/*	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
*/				
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
			                                       
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				
		System.out.print(DueDate);	
		
			

				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 0);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				 int month = Integer.valueOf(DueDate1);
					test.log(LogStatus.PASS, "month ::"+month);
				String DueDate2 = DueDate0[1];
				 int date = Integer.valueOf(DueDate2);
				 int day =date+1;
					test.log(LogStatus.PASS, "date ::"+day);
				String DueDate3 = DueDate0[2];
				int year = Integer.valueOf(DueDate3);
				test.log(LogStatus.PASS, "year ::"+year);
				
				

				String DDueDate4[] =LoanDt.split("/");


				Date DDueDateminus4 = df.parse(LoanDt);

				Calendar cal1 = Calendar.getInstance();

				cal1.setTime(DDueDateminus4);

				cal.add(Calendar.DATE, 0);

				Date DDueDat41= cal1.getTime();

				LoanDt =df.format(DDueDat41);
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			    Date firstDate = sdf.parse(DueDate);
			    Date secondDate = sdf.parse(LoanDt);
			 
			    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			    long diff1 = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			    test.log(LogStatus.PASS, "Difference Days ::"+diff1);
				String DDueDat410[] =LoanDt.split("/");

				String DDueDat411 = DDueDat410[0];
				//Date month1 = format.parse(DDueDat411);
				 int month1 = Integer.valueOf(DDueDat411);
					test.log(LogStatus.PASS, "month1 ::"+month1);
				String DDueDat412 = DDueDat410[1];
				//Date date = format.parse(DDueDat412);
				 int date1 = Integer.valueOf(DDueDat412);
				 
				 test.log(LogStatus.PASS, "date1 ::"+date1);
				String DDueDat413 = DDueDat410[2];
				 int year1 = Integer.valueOf(DDueDat413);
				 test.log(LogStatus.PASS, "year1 ::"+year1);

			 LocalDate endofCentury = LocalDate.of(year, month, day); 
			 test.log(LogStatus.PASS, "endofCentury ::"+endofCentury);
			LocalDate endofCentury1 = LocalDate.of(year1, month1, date1); 
			 test.log(LogStatus.PASS, "endofCentury1 ::"+endofCentury1);
			Period diff = Period.between(endofCentury1, endofCentury);
			test.log(LogStatus.PASS, "Date Difference ::"+diff);
			
			int a =diff.getDays();
		
			test.log(LogStatus.PASS, "Date Difference ::"+a);

			
			
			
			
		
			String InterestAmt=null;
			String CustFee=null;
			//*[@id="revolvingCreditDetailsTable"]/tbody/tr[5]/td[8]
			InterestAmt = driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr[5]/td[8]")).getText();
			test.log(LogStatus.PASS, "Calculated  InterestAmt :: "+InterestAmt);
			CustFee = driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr[4]/td[11]")).getText();
			test.log(LogStatus.PASS, "Calculated  CustFee :: "+CustFee);
		//	double RegAmt = Double.parseDouble(InterestAmt);
			double IntFee = (300*24.0*a/100)/365;
			test.log(LogStatus.PASS, "Expected  IntFee :: "+IntFee);
			//String ReqAmt = Double.toString(PerAmt);
			if(CollateralType.equals("CASH"))
			{
		//	double RegAmt1 = Double.parseDouble(CustFee);
			double CustomerFee = (300*0.7*a)/100;
			test.log(LogStatus.PASS, "Expected  CustFee :: "+CustomerFee);
			}
			if(CollateralType.equals("ACH"))
			{
		//	double RegAmt1 = Double.parseDouble(CustFee);
			double CustomerFee = (300*0.55*a)/100;
			test.log(LogStatus.PASS, "Expected  CustFee :: "+CustomerFee);
			}
	
		
		}
	}
}



public void NACHA_1(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

//				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			//	WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));    Temporary change
			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
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



public void ACHReturnPosting(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

public void ACH_Deposit_1(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

			//	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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
	

public void StatementGeneration(String SSN,String FileName) throws Exception

{


Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

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

// driver.findElement(By.name("button")).click();

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


//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();

DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();

test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);

//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

System.out.print(DueDate);

driver.close();


driver = new InternetExplorerDriver();

driver.get(AdminURL);

test.log(LogStatus.INFO, "Admin portal is launched");

driver.manage().window().maximize();

// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

test.log(LogStatus.PASS, "Username is entered: admin");

driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

test.log(LogStatus.PASS, "Password is entered: "+Password);

//Click Login Button

driver.findElement(By.name("login")).click();

test.log(LogStatus.PASS, "Clicked on Submit button");

Thread.sleep(8000);

Thread.sleep(8000);

String DueDate0[] =DueDate.split("/");

String DueDate1 = DueDate0[0];

String DueDate2 = DueDate0[1];

String DueDate3 = DueDate0[2];

driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

Thread.sleep(8000);

WebDriverWait wait = new WebDriverWait(driver, 10000);

wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

test.log(LogStatus.PASS, "Clicked on Transactions");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

Thread.sleep(5000);

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));

driver.findElement(By.linkText("QA Jobs")).click();

test.log(LogStatus.PASS, "Clicked on QA Jobs");

Thread.sleep(5000);

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));

driver.findElement(By.linkText("Process Date Change")).click();

test.log(LogStatus.PASS, "Clicked on Process Date Change");

Thread.sleep(2000);

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));

Actions action = new Actions(driver);

action.moveToElement(element).build().perform();

Thread.sleep(6000);

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

driver.findElement(By.name("storeCode")).click();

//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

driver.findElement(By.name("storeCode")).sendKeys(StoreID);

test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

Thread.sleep(2000);

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

driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

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

driver.findElement(By.linkText("Unsecure Loc Statement")).click();

test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");


///Thread.sleep(6000); /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img

WebElement elements = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));

Actions actions = new Actions(driver);

actions.moveToElement(elements).build().perform();


Thread.sleep(6000);


driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);

test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);



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

driver.findElement(By.name("submit")).click();

test.log(LogStatus.PASS, "Clicked on submit button");

test.log(LogStatus.PASS, "Statement Generated");

}

}

}



public void Bankrupt_NoRecord(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
	/* if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }*/
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys(BNKstatus);
	 test.log(LogStatus.PASS, "select status as :" + BNKstatus);
	 
	 
	 

	 
	 
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
		/* if( driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
		 {
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
		 }*/
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");

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


public void RPP_1(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			test.log(LogStatus.INFO, "RPP(Starts)");

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
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");	
			test.log(LogStatus.INFO,"Navigate to Transactions Screen");

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
			test.log(LogStatus.INFO,"Navigate to Customer Record Screen");

			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");

			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

			//driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Transaction List Screen");

			driver.findElement(By.name("transactionList")).sendKeys("Payment Plan Payment");
			test.log(LogStatus.PASS,"Payment Plan Selected From Transaction List");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			Thread.sleep(5000);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}


			Thread.sleep(5000);
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"RPP Selected From Transaction List");

			test.log(LogStatus.INFO,"Navigate to RPP 1st Screen");
			if(driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).isDisplayed());
			{
				driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).click();

			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			test.log(LogStatus.INFO,"Navigate to RPP 2nd Screen");

			driver.findElement(By.name("collateralTypeId")).sendKeys("ACH");
			test.log(LogStatus.PASS,"Collateral Type is Selected as ACH");

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS,"Password is Selected as : "+Password);

			driver.findElement(By.name("submitBtn")).click();
			test.log(LogStatus.PASS,"Submit Button Clicked Successfully ");

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			test.log(LogStatus.INFO," Navigated to Perform Another Transaction Permission Screen ");

			if(driver.findElement(By.xpath("//*[@id='OKBut']")).isDisplayed());
			{
				driver.findElement(By.xpath("//*[@id='OKBut']")).click();
			}

			driver.findElement(By.name("checkno")).click();
			test.log(LogStatus.PASS,"No Button for Perform Another Transaction Screen Clicked Successfully ");
			test.log(LogStatus.INFO,"RPP(Ends)"); 


		}
	}
}



public void RPP_Status(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			test.log(LogStatus.INFO, "RPP(Starts)");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");	
			test.log(LogStatus.INFO,"Navigate to Transactions Screen");
			
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
			test.log(LogStatus.INFO,"Navigate to Customer Record Screen");
			String CustStaus=null;
			CustStaus = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();

			test.log(LogStatus.PASS,"Customer status is Default."+CustStaus);
		//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

			//driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");
			

		
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Transaction List Screen");
			
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.INFO,"Navigate to History Credit Table Screen");
			
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.INFO,"Transaction Selection Go Button Click");
			
			//button
			String CheckStatus=null;
			CheckStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[1]/span[2]")).getText();
			if(CheckStatus.equals("Y")){
			test.log(LogStatus.PASS,"Loan in RPP : "+CheckStatus);
			test.log(LogStatus.INFO,"RPP Transaction Done Successfully");
			System.out.print(CheckStatus);	
			//driver.close();
			}
			String CheckStatus1=null;
			CheckStatus1 = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[1]/span[2]")).getText();
			if(CheckStatus1.equals("Y")){
			test.log(LogStatus.INFO,"Payment Status After RPP Is $ Loan In RCC $ : "+CheckStatus1);
			System.out.print(CheckStatus1);	
			
			}
			
			String CheckStatus2=null;
			CheckStatus2 = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[2]/td[2]/span[2]")).getText();
			if(CheckStatus2.equals("Y")){
			test.log(LogStatus.INFO,"  Status After RPP Is $ ACH/RCC Revoke Flag $: "+CheckStatus2);
			System.out.print(CheckStatus2);	
			
			}

		}	
		
		}
			//driver.close();
	
}
	

public void RCCRevoke_1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
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
			driver.findElement(By.name("transactionList")).sendKeys("RCC Revoke");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}

			driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='RCC Revoke')]")).click();
			//driver.findElement(By.name("bt_BankDetails")).click();

			test.log(LogStatus.PASS, "Click on RCCRevoke Button");

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='OK')]")).isDisplayed())
			{
				driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='OK')]")).click();
				test.log(LogStatus.PASS, "Click on Confirmation OK Button");
			}
			/*				driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/
			driver.quit();//need to change to close
			driver = new InternetExplorerDriver();

		}
	}
}


public void RPP_Payment_1(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
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
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();

				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    //	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 driver.findElement(By.name("transactionList")).sendKeys("Payment Plan Payment");
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
						String RPPAmt = driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");
						test.log(LogStatus.PASS, "Payment Amount is ::"+RPPAmt);
						 driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
						 test.log(LogStatus.PASS, "Tender Type selected is ::Cash");
						 driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(RPPAmt);
						 test.log(LogStatus.PASS, "Tender Amount selected is ::"+RPPAmt);
						 driver.findElement(By.name("password")).sendKeys(Password);
						 test.log(LogStatus.PASS, "Password entered is ::"+Password);
						 driver.findElement(By.name("Submit22")).click();
						 test.log(LogStatus.PASS, "Clicked on FinishPaymentPlan button ");
						 
						 
							
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
							
							
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							
							
							 if((driver.findElement(By.name("checkyes"))).isDisplayed())
							 {
									test.log(LogStatus.INFO, "PaymentPlan Transaction with-SSN: " +SSN +" :: is Successful");
							 }
							
					
						
							else
							{
								test.log(LogStatus.PASS, "PaymentPlan Transaction is not Completed Successfully ");
							}
					    	
						 }
					
			}
			
		}



public void RPP_Payment_Void(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
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
				    	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
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

						 driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys("Cash");
						 test.log(LogStatus.PASS, "DisbursementType selected is ::Cash");

						 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
						 test.log(LogStatus.PASS, "Password entered is ::"+Password);
						 driver.findElement(By.name("Submit22")).click();
						 test.log(LogStatus.PASS, "Clicked on FinishPaymentPlan button ");
						 
						 
							
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
							
							
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							
							
							 if((driver.findElement(By.xpath("//*[@type='button' and @value='OK']"))).isDisplayed())
							 {
									test.log(LogStatus.INFO, "PaymentPlan Transaction with-SSN: " +SSN +" :: is Successful");
							 }
							
					
						
							else
							{
								test.log(LogStatus.PASS, "PaymentPlan Transaction is not Completed Successfully ");
							}
					    	
						 }
					
			}
			
		}


public void Check_PPN_1(String SSN,String FileName) throws Exception

{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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

			for(int i=1;i<=n;i++)
			{
				String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getAttribute("value");

				if(transactino_value.contains("Plan"))
				{
					test.log(LogStatus.PASS, "PPN Option is Available");	
				}
				else
				{
					test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
					test.log(LogStatus.PASS, "PPN Option is not Available");	
				}
			}

		}
	}
}

public void DefaultPayment_Void_1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "DefaultPayment_Void with-SSN: " +SSN +" :: is ::"+"Starts");
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
			driver.findElement(By.name("transactionList")).sendKeys("Void");
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

			String PaymentAmount=null;

			driver.findElement(By.name("defPaymentRequestBeanRC.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Disb Type");

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Void Default Payment");

			test.log(LogStatus.INFO, "DefaultPayment_Void with-SSN: " +SSN +" :: is ::"+"Successful");



		}
	}
}


public void Default_Payment_1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			 test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
			driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
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
			
			String PaymentAmount=null;
			
			PaymentAmount = driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");
			//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
			Thread.sleep(2000);
			driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Tender Type");
			
			driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAmount);
			test.log(LogStatus.PASS, "Enter the Tender Amount");
			
			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");
			
			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on Finish Payment Button");
			
			test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Successful");
		
		
		}
	}
}

public void CustomerDefault_1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			 test.log(LogStatus.INFO, "CustomerDefault with-SSN: " +SSN +" :: is ::"+"Starts");

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

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
			driver.quit();	//need to change to close
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
			cal.add(Calendar.DATE, 0);
			Date DDueDateminus1= cal.getTime();
			// String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
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



			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
			driver.findElement(By.linkText("Default Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Default Loc");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			//Thread.sleep(6000);
			/* WebElement element = driver.findElement(By.name("cancel"));
							        Actions action = new Actions(driver);								        
							        action.moveToElement(element).build().perform();*/

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
			 Thread.sleep(6000);
			 WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
			  Actions action = new Actions(driver);								        
			  action.moveToElement(element).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
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
			 test.log(LogStatus.INFO, "CustomerDefault with-SSN: " +SSN +" :: is ::"+"Completed");
			Thread.sleep(6000);


		}
	}
}

public void DefaultPaymentStatus_1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			 test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Starts");
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
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
			 test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Successful");
			System.out.print(CheckStaus);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.quit();//need to change to close
			 driver = new InternetExplorerDriver();

		}
	}
}


public void NewLoan_PDL(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);     	
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
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType1 = TestData.getCellData(sheetName,"ESign_CollateralType1",row);
				System.out.println(ESign_CollateralType1);
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
			
				test.log(LogStatus.INFO, "Scheduler-Store Aging");
				 String Parent_Window = driver.getWindowHandle();  
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
				//*[@id="911100"]/a
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
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				 if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				 {

						Thread.sleep(3000);								
						driver.findElement(By.name("prodSel")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					
					      driver.findElement(By.id("LoanButtonId")).click();

                             test.log(LogStatus.PASS, "Clicked on New Loan button");
			
					Thread.sleep(3000);
					 driver.switchTo().window(Parent_Window);

                      for( String winHandle1 : driver.getWindowHandles())

                             {

                                 driver.switchTo().window(winHandle1);

                             }                    

                             driver.switchTo().defaultContent();

                             driver.switchTo().frame("mainFrame");

                             driver.switchTo().frame("main");
                       
					
					
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys("CHECK");
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType1);
						if(!(ESign_LoanAmt.isEmpty()))
						{
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
						}
						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
						test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys("Cash");
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						///driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
							driver.findElement(By.name("Ok")).click();
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
					
					
						
				
			}
			
			}
		}

	}



public void CurePaymentStatus_1(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
			    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 //                                      //*[@id="revolvingCreditHistTable"]/tbody/tr[10]/td[3]/span[2]
					//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
					 test.log(LogStatus.PASS,"Payment status is Cure."+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			 //   driver.close();//need to change to close
			 driver.quit();//Uncomment 
			    driver = new InternetExplorerDriver();

		}
	}
}


public void DeliquentPaymentStatus_1(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			test.log(LogStatus.INFO, "DeliquentPaymentStatus with-SSN: " +SSN +" :: Starts");
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
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
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
					 test.log(LogStatus.INFO, "DeliquentPaymentStatus for-SSN: " +SSN +" :: is ::"+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			  //driver.close();//need to change to close
			  driver.quit();//Uncomment 
			  driver = new InternetExplorerDriver();

		}
	}
}


public void AgeStore_1(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

			driver.findElement(By.name("storeCode")).click();
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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}




		}
	}
}

public void Payments_1(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
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
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    //	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
						
							// String Pmt= driver.findElement(By.name("currentBalance")).getAttribute("value");
							// name="requestBean.paymentAmt"
							 driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("20");
							 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("20");
							test.log(LogStatus.PASS, "Tender Amt is entered as :: 20");							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Password is selected as "+Password);			
							 driver.findElement(By.name("Submit22")).click();
								test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
							 
							 Thread.sleep(2000);
					
							/* driver.findElement(By.id("btnADV_Yes")).click();
								test.log(LogStatus.PASS, "Clicked on Navigate to  Payoff Screen button ");																	
						
										
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 String Pmt1= driver.findElement(By.name("payOffAmount")).getAttribute("value");
								 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
								 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
								driver.findElement(By.name("tenderAmount")).sendKeys(Pmt1);
								test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt1);							
								 driver.findElement(By.name("password")).sendKeys(Password);
								 test.log(LogStatus.PASS, "Password is selected as "+Password);			
								 driver.findElement(By.name("Submit22")).click();
									test.log(LogStatus.PASS, "Clicked on Finish payOff button ");
								 
								 
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
									}*/
						/*		
							 
							 try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
									
								
								 
								    //do what you normally would if you didn't have the alert.
								}
							 */
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
									Thread.sleep(2000);
									 Thread.sleep(2000);
							/*	 if(driver.findElement(By.name("Ok")).isDisplayed())
									{*/
									 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
									/* driver.findElement(By.name("Ok")).click();*/
								/*	}
								 else
									{
										test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
									}*/
							
					    	
						 }
					
			}
			
		}
	}


public void DFLTPmtFull_Status_1(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String CustStatus=null;
			CustStatus =driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();
			test.log(LogStatus.PASS,"Customer status is ::"  +CustStatus);
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();

				//driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();	
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



public void DrawLoan_1(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
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
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
				    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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


public void StatementGeneration_1(String SSN,String FileName) throws Exception
{

        Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
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
                        	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();
                        	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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

                        //DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                        DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                                                               //*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
                        test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);
                        //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
                        System.out.print(DueDate);
                        driver.close();

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
                        String DueDate0[] =DueDate.split("/");
                        String DueDate1 = DueDate0[0];
                        String DueDate2 = DueDate0[1];
                        String DueDate3 = DueDate0[2];
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("topFrame");
                        Thread.sleep(8000);
                    	Thread.sleep(8000);
                        WebDriverWait wait = new WebDriverWait(driver, 10000);
   					    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
                        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Transactions");
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        Thread.sleep(5000);
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));
                        driver.findElement(By.linkText("QA Jobs")).click();
                        test.log(LogStatus.PASS, "Clicked on QA Jobs");
                        Thread.sleep(5000);
                        //wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Process Date Change")).click();
                        test.log(LogStatus.PASS, "Clicked on Process Date Change");
                        //Thread.sleep(2000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
                         Actions action = new Actions(driver);
                         action.moveToElement(element).build().perform();
                        Thread.sleep(6000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("storeCode")).click();
                        //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
                        driver.findElement(By.name("storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
                        Thread.sleep(2000);
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
                                driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
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
                        driver.findElement(By.linkText("Unsecure Loc Statement")).click();
                        test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

                        ///Thread.sleep(6000);  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        // /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        WebElement elements = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
                        Actions actions = new Actions(driver);
                        actions.moveToElement(elements).build().perform();

                        Thread.sleep(6000);

                        driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);


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
                        driver.findElement(By.name("submit")).click();
                        test.log(LogStatus.PASS, "Clicked on submit button");
                        test.log(LogStatus.PASS, "Statement Generated");
                }
        }
}


public void NACHA(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			//	WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));    Temporary change
			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
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



public void Admin_ACH_Clear(String SSN,String FileName,int Days,int i) throws Exception
{

Excel TestData = new
		Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
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
		String dt ;
		String Parent_Window = driver.getWindowHandle(); 

		CSRLoginpage login = new CSRLoginpage();

		login.Login(UserName, Password, StoreID, driver, AppURL, test);
		System.out.println(AdminURL);
		test.log(LogStatus.INFO, "Admin_ACH_Clear");

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
			driver.findElement(By.name("button")).click();
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");


		//
		// /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
		if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
		{

			Thread.sleep(3000);

			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();
			test.log(LogStatus.PASS, "Clicked on RCC Schedule Button");

			for( String winHandle1 : driver.getWindowHandles())

			{
				if(!(winHandle1.equals(Parent_Window)))
				{
					driver.switchTo().window(winHandle1);
					Thread.sleep(6000);
					System.out.println(driver.getTitle());
					dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
					test.log(LogStatus.PASS, "date is"+dt);
					System.out.println(dt);


					driver.quit();

					driver = new InternetExplorerDriver();
					driver.get(AdminURL);
					test.log(LogStatus.PASS, "date in outside loop"+dt);




					String DDe[] =dt.split(" ");

					DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");


					String DueDate = DDe[0];

					String DDueDate[] =DueDate.split("-");


					Date DDueDateminus1 = df.parse(DueDate);

					Calendar cal = Calendar.getInstance();

					cal.setTime(DDueDateminus1);

					cal.add(Calendar.DATE, Days);

					Date DDueDate1= cal.getTime();

					DueDate =df.format(DDueDate1);

					String DueDate0[] =DueDate.split("-");

					String DueDate3 = DueDate0[0];
					String DueDate1 = DueDate0[1];

					String DueDate2 = DueDate0[2];


					//driver.close();



					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
					test.log(LogStatus.PASS, "Username is entered::admin");
					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is entered:"+Password);
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
					test.log(LogStatus.PASS, "Clicked on RCC Payments");
					Thread.sleep(5000);
					driver.findElement(By.linkText("ACH Clear")).click();
					test.log(LogStatus.PASS, "Clicked on ACH Clear");
					driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);

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

					driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
					driver.findElement(By.name("beginMonth")).click();
					driver.findElement(By.name("beginMonth")).clear();
					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
					driver.findElement(By.name("beginDay")).clear();
					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
					test.log(LogStatus.PASS, "beginDay is entered:"+DueDate2);
					driver.findElement(By.name("beginYear")).clear();
					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
					test.log(LogStatus.PASS, "beginYear is entered:"+DueDate3);
					Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					Thread.sleep(1000);
					Thread.sleep(5000);
					driver.findElement(By.name("submit")).click();
					test.log(LogStatus.PASS, "Clicked on submit button");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					test.log(LogStatus.PASS, "ACH Clear Process updated successfully.");
				/*	if(
							driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
					{
						test.log(LogStatus.PASS, "ACH Clear Process updated successfully.");
						driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "ACH Clear Process not updated successfully.");
					}*/


				}
			}
		}
	}
}
}


public void ACH_Deposit(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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

public void Default_WOProc(String SSN,String FileName,int days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
		/*	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();*/
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture CureEndDate as :: "+DueDate);
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
		/*	Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, 0);
			cal.add(Calendar.DATE, 90);
			Date DDueDateminus1= cal.getTime();

			 String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];*/
			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

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
			test.log(LogStatus.INFO, "WriteOff Completed Successfully ");

		}
	}
}

public void WOPaymentStatus(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
			String CustStatus=null;
			CustStatus =driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();
			test.log(LogStatus.PASS,"Customer status is ::"  +CustStatus);
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
				// /html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();    
				
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
			String CheckStaus=null;
			
			if(driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).isDisplayed())
			{
				test.log(LogStatus.PASS,"Payment status is Default Displayed sucessfully.");
				CheckStaus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is Writeoff."  +CheckStaus);
				
			}
			else
			{
				test.log(LogStatus.PASS,"Payment status is  Writeoff not Displayed sucessfully.");
			}

			System.out.print(CheckStaus);	
			//driver.close();

		}
	}
}


public void ACH_Clear(String SSN,String FileName,int Days) throws Exception
	{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
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
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				                          //   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
			test.log(LogStatus.INFO,"Navigate to Transaction List Screen");
			
			driver.findElement(By.name("transactionList")).sendKeys("History");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			test.log(LogStatus.INFO,"Navigate to History Credit Table Screen");
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
				driver.findElement(By.linkText("ACH Clear")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Clear");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

				driver.findElement(By.name("requestBean.storeCode")).click();
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
	



		}
	}
	}	

	@Test (priority=0)
	
	 public void LOCI_Draw_Stmt_Depo_RTN_DLQ_DFLT_WO_BNK_WOR() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_Stmt_Dep_Clear_BNK_RTN_Txn_Testdata.xls";
		// AA_LOCI_Draw_Stmt_Dep_Clear_BNK_RTN_Txn_Testdata
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);  
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
		       // System.out.println(Password);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);

				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"_S.No:133_"+PayFrequency+"_"+CollateralType, "LOCI _ Draw _ Deposit _ Clear _BNK_RTN _Status should be in BNK");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		         
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.DrawLoan(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN, FileName, -1);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.NACHA(SSN, FileName, -1);
		        this.ACH_Deposit(SSN, FileName, 0);
		        this.ACH_Clear(SSN, FileName,7);
		        this.AgeStore(SSN, FileName, 8);
		        this.Bankrupt(SSN, FileName);
		        this.ACHReturnPosting(SSN, FileName);
		        this.DeliquentPaymentStatus(SSN, FileName);
		        this.RPP_Status(SSN, FileName);

		     

		     
		     
		        
		}
		}			
			}
	
	

	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());

			String screenshotPath = ExecuteScripts.getScreenhot(driver, result.getName());

			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}

		reports.flush();

	}			
	@AfterTest

	public void endReport(){

		reports.endTest(test);
		reports.flush();
		driver.quit();



	}
	@AfterClass

	public void closeBrowser() throws Exception{

		driver.quit();

	}
}
