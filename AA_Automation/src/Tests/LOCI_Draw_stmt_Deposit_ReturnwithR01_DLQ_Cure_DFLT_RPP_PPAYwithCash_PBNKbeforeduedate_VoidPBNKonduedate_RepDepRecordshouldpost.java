package Tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
//import java.sql.Time;
import java.text.DateFormat;
//import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.Iterator;
import java.util.List;
import java.util.Random;
//import java.util.Set;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

//import Pages.HomePage;
//import Pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.Assert;

import org.testng.annotations.AfterClass;
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
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;

public class LOCI_Draw_stmt_Deposit_ReturnwithR01_DLQ_Cure_DFLT_RPP_PPAYwithCash_PBNKbeforeduedate_VoidPBNKonduedate_RepDepRecordshouldpost {

	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	String SSN;
	String FileName;

	@BeforeClass 
	public void setup() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}
	
	@BeforeClass

	public synchronized void initialize() {

		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());


		String filename="AA_LOC_RegressionScenarios_Scenario.No_108_"+timestamp+".html";

		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/"+filename, true);
	}


	
	public void Login (String username,String password,String storenumber) {										
		driver.get(appUrl);
		test.log(LogStatus.INFO, "CSR Application is launched");
		driver.manage().window().maximize();
		String usenameId = "loginRequestBean.userId";
		String passwordId = "loginRequestBean.password";
		String StoreId = "loginRequestBean.locNbr";
		String Login = "login";
		driver.findElement(By.name(usenameId)).sendKeys(username);
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Username is entered: "+username);
		driver.findElement(By.name(passwordId)).clear();
		driver.findElement(By.name(passwordId)).sendKeys(password);
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Password is entered: "+password);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		driver.findElement(By.name(Login)).click();
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Clicked on Submit button");
	}
	
	public void NewLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);		
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
				 Thread.sleep(1000);
				 //	Selection of Product based on the Name provided in Test Data
				
				 
				 //if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				 if(driver.findElement(By.id("ShareScreenBtn")).isEnabled())
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
						if(StoreID.equals("4329"))
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

	                    driver.switchTo().window(winHandle1);

	                }
					WebDriverWait wait = new WebDriverWait(driver, 10000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("confirmSummary")));
					driver.findElement(By.name("confirmSummary")).click();
					test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
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

					test.log(LogStatus.PASS, "Clicked on New Loan button");
					
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
						WebDriverWait wait1 = new WebDriverWait(driver, 1000);	
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
						WebDriverWait wait2 = new WebDriverWait(driver, 10);
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
							/* test.log(LogStatus.INFO, "Navigated to Draw Screen");
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
							 driver.findElement(By.name("loanAmt")).sendKeys("50");	
							 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							 driver.findElement(By.name("disbAmtFirst")).sendKeys("50");					
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
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
								}*/
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
	public void Active_Military_Start(String SSN,String FileName) throws Exception
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
			
	public void Agestore_Loandate(String SSN,String FileName,int Days) throws Exception
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


				String LoanDate=null;

				LoanDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[3]")).getText();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[3]
				
				test.log(LogStatus.PASS, "Capture LoanDate"+LoanDate);
				System.out.print(LoanDate);	
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
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =LoanDate.split("/");

				Date DDueDateminus1 = df.parse(LoanDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1= cal.getTime();

				LoanDate =df.format(DDueDate1);

				String DueDate0[] =LoanDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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
		driver.close();
		
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
				Thread.sleep(1000);

				//if(ProductID.equals("LOC"))
				//{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(1000);
				int n=driver.findElements(By.xpath("//select[@name='transactionList']/option")).size();

				for(int i=1;i<=n;i++)
				{
					String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getAttribute("value");


					if(transactino_value.equalsIgnoreCase("Draw"))
					{
						test.log(LogStatus.PASS, "Draw option is available in the list, Hence test fail");
					}
					else
					{
						test.log(LogStatus.PASS, "Draw option is not available in the list");
					}
				}
			}}
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
	                       // wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
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
					driver = new InternetExplorerDriver();
					this.Login(UserName,Password,StoreID);
					driver.switchTo().defaultContent();
					Thread.sleep(2000);
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
							 driver.findElement(By.name("loanAmt")).sendKeys("400");	
								Thread.sleep(2000);
							 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 400");
							 driver.findElement(By.name("disbAmtFirst")).sendKeys("400");					
							 test.log(LogStatus.PASS, "Disb Amt is enterted as 400");
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
	public void AgeStore_EOD(String SSN,String FileName) throws Exception
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
						
								 DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				   										
				   System.out.print(DueDate);	
				   // driver.close();
				   // Thread.sleep(5000);
				    /*for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}*/
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
				    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				   //driver.quit();//--need to change to quit
				    //driver.quit();//Uncomment 
				  // driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
				  // driver.switchTo().defaultContent();
					//driver.switchTo().frame("topFrame");
					//driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				 //*[@id="icons"]/li[7]/a
					Thread.sleep(5000);
					driver.quit();
				   driver = new InternetExplorerDriver();
				   driver.get(AdminURL);
				   test.log(LogStatus.INFO, "Admin portal is launched");
					driver.manage().window().maximize();
				   storeupdate_EOD(UserName,Password,StoreID,DueDate,AdminURL);
			}
		}
	}
	public void storeupdate_EOD(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
	{
		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
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
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		// driver.switchTo().frame("main");
		Date DDueDate = df.parse(DueDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(DDueDate);
		//cal.add(Calendar.DATE, -1);
		// Date DDueDateminus1= cal.getTime();
		// String DueDateminus1 =df.format(DDueDateminus1);
		String DueDate0[] =DueDate.split("/");
		String DueDate1 = DueDate0[0];
		String DueDate2 = DueDate0[1];
		String DueDate3 = DueDate0[2];
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
		test.log(LogStatus.PASS, "Clicked on Transactions");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		Thread.sleep(5000);
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
		 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
		driver.findElement(By.linkText("Borrower")).click();
		test.log(LogStatus.PASS, "Clicked on Borrower");
		Thread.sleep(5000);
		 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));
		driver.findElement(By.linkText("Process Date Change")).click();
		test.log(LogStatus.PASS, "Clicked on Process Date Change");
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
		driver.findElement(By.name("btnPreview")).click();
		Thread.sleep(2000);
		//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		Thread.sleep(2000);
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
				Thread.sleep(2000);
				//driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				Thread.sleep(2000);
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
				Thread.sleep(2000);
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
				String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
				//String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

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

				Thread.sleep(4000);
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

				Thread.sleep(3000);
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
		                    //driver.switchTo().frame("main");
		                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		                    //driver.findElement(By.cssSelector("li[id='911101']")).click();
		                    driver.findElement(By.linkText("Drawer")).click();
		                    test.log(LogStatus.PASS, "Clicked on Drawer");
		                    //driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
		                    //driver.findElement(By.linkText("Drawer")).click();
		                    driver.switchTo().defaultContent();
		                    driver.switchTo().frame("mainFrame");
		                    //driver.switchTo().frame("main");
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
		                            //if alert present, accept and move on.

		                    }
		                    catch (NoAlertPresentException e) {
		                            //do what you normally would if you didn't have the alert.

		                    }
		                   
		                    Thread.sleep(2000);
		                    driver.switchTo().defaultContent();
		                    driver.switchTo().frame("mainFrame");
		                    driver.switchTo().frame("main");
		                    //|| driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font")).getCssValue("color")=="red"
		                    if(this.Field(driver) != null )
		                    //if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).isDisplayed())
		                    {                    		                   
		                            Thread.sleep(1000);
		                            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		                            driver.switchTo().defaultContent();
		                            driver.switchTo().frame("mainFrame");
		                            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		                            //driver.findElement(By.cssSelector("li[id='911101']")).click();
		                            driver.findElement(By.linkText("Safe")).click();
		                            test.log(LogStatus.PASS, "Clicked on Safe");
		                            //driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
		                            //driver.findElement(By.linkText("Drawer")).click();
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
		                            	//if alert present, accept and move on.														
			
		                            	}
		                            catch (NoAlertPresentException e) {
		                            	//do what you normally would if you didn't have the alert.
			
		                            }
		                            driver.switchTo().defaultContent();
		                			driver.switchTo().frame("mainFrame");
		                			driver.switchTo().frame("main");
		                			if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
		                			{
		                				test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
		                				driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
		                				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
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

		                            driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
		                            //Password

		                            driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
		                            test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");


		                            driver.findElement(By.name("safeassign")).click();
		                            test.log(LogStatus.PASS,"Click on Safe Assigen");

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
		                           // if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
		                            if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
		                            {

		                                    test.log(LogStatus.PASS,"Safe assigned successfully.");
		                                    driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
		                                    //driver.findElement(By.name("done")).click();
		                            }
		                            else
		                            {
		                                    test.log(LogStatus.PASS,"Safe not assigned successfully.");
		                            }

		                            Thread.sleep(1000);
		                            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		                            driver.switchTo().defaultContent();
		                            driver.switchTo().frame("mainFrame");
		                            //driver.switchTo().frame("main");
		                            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		                            //driver.findElement(By.cssSelector("li[id='911101']")).click();
		                            driver.findElement(By.linkText("Drawer")).click();
		                            test.log(LogStatus.PASS, "Clicked on Drawer");
		                            //driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
		                            //driver.findElement(By.linkText("Drawer")).click();
		                            driver.switchTo().defaultContent();
		                            driver.switchTo().frame("mainFrame");
		                            //driver.switchTo().frame("main");
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
		                                    //if alert present, accept and move on.

		                            }
		                            catch (NoAlertPresentException e) {
		                                    //do what you normally would if you didn't have the alert.

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
		                             // if(driver.findElement(By.name("done")).isDisplayed())
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
						 test.log(LogStatus.PASS,"Payment status is Deliquent."+CheckStaus);
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
					Thread.sleep(4000);
					//driver.get(appUrl);		
					// for(String winHandle : driver.getWindowHandles()){
					//	    driver.switchTo().window(winHandle);
						//	}
					//driver.manage().window().maximize();
					 CSRLoginpage login = new CSRLoginpage();
				     login.Login(UserName, Password, StoreId, driver, AppURL, test);
					driver.switchTo().defaultContent();	
					Thread.sleep(2000);
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
						 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
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
										 test.log(LogStatus.PASS, "Payoff Completed Successfully ");
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
	public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
	{
	        DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
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
	        //cal.add(Calendar.DATE, -1);
	        // Date DDueDateminus1= cal.getTime();

	        // String DueDateminus1 =df.format(DDueDateminus1);
	        String DueDate0[] =DueDate.split("/");
	        String DueDate1 = DueDate0[0];
	        String DueDate2 = DueDate0[1];
	        String DueDate3 = DueDate0[2];
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame("topFrame");
	        WebDriverWait wait = new WebDriverWait(driver, 10000);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
	        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
	        test.log(LogStatus.PASS, "Clicked on Transactions");


	        driver.switchTo().defaultContent();
	        driver.switchTo().frame("mainFrame");
	        Thread.sleep(5000);
	        driver.findElement(By.linkText("Borrower")).click();
	        test.log(LogStatus.PASS, "Clicked on Borrower");
	        Thread.sleep(5000);
	        driver.findElement(By.linkText("Process Date Change")).click();
	        test.log(LogStatus.PASS, "Clicked on Process Date Change");
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame("mainFrame");
	        driver.switchTo().frame("main");
	        WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
	         Actions action = new Actions(driver);
	         action.moveToElement(element).build().perform();


	        Thread.sleep(6000);


	        //driver.findElement(By.name("storeCode")).click();
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
	        driver.findElement(By.name("btnPreview")).click();
	        Thread.sleep(2000);
	        //driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	        Thread.sleep(2000);
	        //Thread.sleep(8000);
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
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
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

				/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
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
	
	
	
	public void Represent_AgeStore(String SSN,String FileName,int Days) throws Exception
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
				//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				
				DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[3]/td[4]")).getText();
				//*[@id="achHistoryTable"]/tbody/tr[3]/td[4]
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
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
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

				/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
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
	
	
	
	public void ACH_Clear(String SSN,String FileName) throws Exception
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
			driver.findElement(By.name("transactionList")).sendKeys("ACH Clear");
		/*	if(ProductID.equals("LOC"))
		 * 
			{*/
			try { 
			    Alert alert = driver.switchTo().alert();
			    alert.accept();
			    //if alert present, accept and move on.														
				
			}
			catch (NoAlertPresentException e) {
			    //do what you normally would if you didn't have the alert.
				
			}
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				//driver.findElement(By.name("button")).click(); 
			//}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
	
			Thread.sleep(3000);

			driver.findElement(By.name("requestBean.chkName")).click();
		
			test.log(LogStatus.PASS, "Select checkbbox of Customer record to Clear");
			driver.findElement(By.name("CmdReturnPosting")).click();
			test.log(LogStatus.PASS, "Clicked on Finish ACH Clear Button");
			
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
			
			if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]")).isDisplayed())
			{
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]")).click();
			test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
			}		
			
			//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

			//String CheckStaus=null;
	/*	//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
			CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
			//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
			if((CheckStaus).contains("Deposit"))
			{
				test.log(LogStatus.PASS,"CheckStatus is  Displayed as::"+CheckStaus);
			}
			else
			{
				test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
			}*/

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

    			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    			driver.switchTo().defaultContent();
    			driver.switchTo().frame("mainFrame");
    			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
    			driver.findElement(By.linkText("Daily Jobs")).click();
    			test.log(LogStatus.PASS, "Clicked on Daily Jobs");*/
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
	
	
	public void Represent_ACHDep(String SSN,String FileName,int Days) throws Exception
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
    		//	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
    			DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[3]/td[4]")).getText();                                    
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

    			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    			driver.switchTo().defaultContent();
    			driver.switchTo().frame("mainFrame");
    			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
    			driver.findElement(By.linkText("Daily Jobs")).click();
    			test.log(LogStatus.PASS, "Clicked on Daily Jobs");*/
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


	public void Agestore_StatementGeneration(String SSN,String FileName, int Days ) throws Exception
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

				/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("Daily Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on Daily Jobs");*/
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
	
	
	public void Represent_NACHA(String SSN,String FileName,int Days) throws Exception
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
				//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[3]/td[4]")).getText();
				
				
				//*[@id="achHistoryTable"]/tbody/tr[3]/td[4]                                       
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
				test.log(LogStatus.PASS, "Clicked on Daily Jobs");*/
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.quit();//need to change to close
				 driver = new InternetExplorerDriver();

			}
		}
	}
	
	public void Default_WOProc(String SSN,String FileName,int Days) throws Exception

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

	// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]

	//driver.findElement(By.name("button")).click();

	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();

	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();

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

	/* DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();*/

	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();

	test.log(LogStatus.PASS, "Capture PWO Dtae"+DueDate);

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

	/* Date DDueDate = df.parse(DueDate);

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

	cal.add(Calendar.DATE, Days);

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

	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

	// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

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

	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();

	test.log(LogStatus.PASS,"Payment status is Writeoff." +CheckStaus);

	}

	else

	{

	test.log(LogStatus.PASS,"Payment status is Writeoff not Displayed sucessfully.");

	}

	System.out.print(CheckStaus);

	//driver.close();

	}

	}

	}
	public void PendingBNK(String SSN,String FileName) throws Exception
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
		 
		 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Pending");
		 test.log(LogStatus.PASS, "select status as :" + "Pending");
		 
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
			 test.log(LogStatus.PASS, "<FONT color=green style=Arial> Status BNKPending is Saved");
		 
				
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
		 Thread.sleep(3000);
		 if( driver.findElement(By.name("loanCode")).isDisplayed())
		 {
		 driver.findElement(By.name("loanCode")).click();
		 test.log(LogStatus.PASS, "Selecting Check box for loan");
		 }
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
	


public void RCCStatus(String SSN,String FileName) throws Exception

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
			//test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

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
			String RCCStatus=null;
			String LoanStatus = null;
			String RCCIneligibleReason = null;
			String RccflagStatus = null;
			/*	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed();

			test.log(LogStatus.PASS," RCC schdule is Displayed");*/
			//CheckStaus=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
			RCCStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[2]/span[2]")).getText();
			LoanStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
			RCCIneligibleReason = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
			RccflagStatus=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[2]/td[2]/span[2]")).getText();
			//*[@id="revolvingCreditHistTable"]/tbody/tr[12]/td[2]/span[2]
			//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
			test.log(LogStatus.PASS," RCC Status ::"+RCCStatus);
			test.log(LogStatus.PASS," Loan Status ::"+LoanStatus);
			test.log(LogStatus.PASS," RCCIneligibleReason ::"+RCCIneligibleReason);
			test.log(LogStatus.PASS," RccflagStatus ::"+RccflagStatus);
			// //*[@id="revolvingCreditHistTable"]/tbody/tr[14]/td[2]/span[2]
			//	System.out.print(CheckStaus);	
			//driver.close();

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
 //Thread.sleep(8000);
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
		

public void Nextpaydate_Borrower(String SSN,String FileName) throws Exception

{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

int lastrow=TestData.getLastRow("NewLoan");

System.out.println("NewLoan"+lastrow);

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

String MonthlyPayDay=TestData.getCellData(sheetName,"MonthlyPayDay",row);

String Income_PayFrequency=TestData.getCellData(sheetName,"Income_PayFrequency",row);

String SemiMonOthFirstDay=TestData.getCellData(sheetName,"SemiMonOthFirstDay",row);

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

String Monthlydate=null;

String Monthlydate1=null;

if(MonthlyPayDay.length()==3)

{

Monthlydate = MonthlyPayDay.substring(0, 1);

Monthlydate1="0"+Monthlydate;

}

if(MonthlyPayDay.length()==4)

{

Monthlydate1 = MonthlyPayDay.substring(0, 2);

}

System.out.println(Monthlydate1);

WebDriverWait wait = new WebDriverWait(driver, 1000);

driver.switchTo().frame("topFrame");

driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();

test.log(LogStatus.PASS, "Clicked on Borrower");

driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='902000']")));

driver.findElement(By.cssSelector("li[id='902000']")).click();

//driver.findElement(By.cssSelector("//*[@id='902000']/a")).click();

test.log(LogStatus.PASS, "Clicked on Edit");

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

String NextPayday =null;

if(Income_PayFrequency.equals("Bi-Weekly"))

{

	// //*[@id="biWk_second"] //*[@id="biWk_second"]/text()
NextPayday = driver.findElement(By.xpath("//*[@id='biWeekly']/td/table/tbody/tr[2]/td[2]/input")).getAttribute("value");
//*[@id="biwkfstid"]

String PayStubReviewedDate0[] =NextPayday.split("/");

String PayStubReviewedDate2 = PayStubReviewedDate0[0];

String month=null;

if(PayStubReviewedDate2.length()==1)

{

month = "0"+PayStubReviewedDate0[0];

}

else

{

month = PayStubReviewedDate0[0];

}

// int day= Integer.parseInt(PayStubReviewedDate2);

String Day = PayStubReviewedDate0[1];

if(Day.length()==1)

{

Day = "0"+PayStubReviewedDate0[1];

}

else

{

Day = PayStubReviewedDate0[1];

}

String Year = PayStubReviewedDate0[2];

NextPayday = month+"/"+Day+"/"+Year;

}

if(Income_PayFrequency.equals("Monthly"))

{

driver.switchTo().defaultContent();

driver.switchTo().frame("bottom");

String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

String Busdate[]=BusinessDt.split(":");

String date = Busdate[1];

DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

Date d1 = df.parse(date);

Calendar cal = Calendar.getInstance();

cal.setTime(d1);

cal.add(Calendar.DATE, 0);

Date PayStubReviewedDate1= cal.getTime();

String PayStubReviewedDate =df.format(PayStubReviewedDate1);

//Date D=Add(date1,7);

//System.out.println(date);

//System.out.println(PayStubReviewedDate);

String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");

String PayStubReviewedDate2 = PayStubReviewedDate0[0];

int day= Integer.parseInt(PayStubReviewedDate2);

String PayStubReviewedDate3 = PayStubReviewedDate0[1];

String PayStubReviewedDate4 = PayStubReviewedDate0[2];

int yyyy= Integer.parseInt(PayStubReviewedDate4);

int DD= day+1;

String month="0"+String.valueOf(DD);

String days=Monthlydate1;

String year=null;

if(day==12)

{

yyyy=yyyy+1;

year=String.valueOf(yyyy);

}

else

{

year=PayStubReviewedDate4;

}

NextPayday = month+"/"+days+"/"+year;

}

if(Income_PayFrequency.equals("Semi-Monthly"))

{

driver.switchTo().defaultContent();

driver.switchTo().frame("bottom");

String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

String Busdate[]=BusinessDt.split(":");

String date = Busdate[1];

DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

Date d1 = df.parse(date);

Calendar cal = Calendar.getInstance();

cal.setTime(d1);

cal.add(Calendar.DATE, 0);

Date PayStubReviewedDate1= cal.getTime();

String PayStubReviewedDate =df.format(PayStubReviewedDate1);

//Date D=Add(date1,7);

//System.out.println(date);

//System.out.println(PayStubReviewedDate);

String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");

String PayStubReviewedDate2 = PayStubReviewedDate0[0];

int day= Integer.parseInt(PayStubReviewedDate2);

String PayStubReviewedDate3 = PayStubReviewedDate0[1];

String PayStubReviewedDate4 = PayStubReviewedDate0[2];

int yyyy= Integer.parseInt(PayStubReviewedDate4);

int DD= day+1;

String month="0"+String.valueOf(DD);

String days="01";

String year=null;

if(day==12)

{

yyyy=yyyy+1;

year=String.valueOf(yyyy);

}

else

{

year=PayStubReviewedDate4;

}

NextPayday = month+"/"+days+"/"+year;

}

if(Income_PayFrequency.equals("Weekly"))

{

driver.switchTo().defaultContent();

driver.switchTo().frame("bottom");

String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

String Busdate[]=BusinessDt.split(":");

String date = Busdate[1];

DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

Date d1 = df.parse(date);

Calendar cal = Calendar.getInstance();

cal.setTime(d1);

if(SemiMonOthFirstDay.equals("Monday"))

{

cal.add(Calendar.DATE, 1);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

if(SemiMonOthFirstDay.equals("Tuesday"))

{

cal.add(Calendar.DATE, 2);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

if(SemiMonOthFirstDay.equals("Wednesday"))

{

cal.add(Calendar.DATE, 3);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

if(SemiMonOthFirstDay.equals("Thursday"))

{

cal.add(Calendar.DATE, 4);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

if(SemiMonOthFirstDay.equals("Friday"))

{

cal.add(Calendar.DATE, 5);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

if(SemiMonOthFirstDay.equals("Saturday"))

{

cal.add(Calendar.DATE, 6);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

if(SemiMonOthFirstDay.equals("Sunday"))

{

cal.add(Calendar.DATE, 7);

Date PayStubReviewedDate1= cal.getTime();

NextPayday =df.format(PayStubReviewedDate1);

}

//Date D=Add(date1,7);

//System.out.println(date);

//System.out.println(PayStubReviewedDate);

}

test.log(LogStatus.PASS,"Next Paydate."+NextPayday);

/* driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/

driver.quit(); //need to change to c

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

/* DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

Date d1 = df.parse(date);

Calendar cal = Calendar.getInstance();

cal.setTime(d1);

cal.add(Calendar.DATE, -10);*/

Date DDueDate = df.parse(NextPayday);

Calendar cal = Calendar.getInstance();

cal.setTime(DDueDate);

cal.add(Calendar.DATE, 0);

Date DDueDateminus1= cal.getTime();

String DueDateminus1 =df.format(DDueDateminus1);

String NextPayday0[] =DueDateminus1.split("/");

String NextPayday1 = NextPayday0[0];

String NextPayday2 = NextPayday0[1];

String NextPayday3 = NextPayday0[2];

//WebDriverWait wait = new WebDriverWait(driver, 10000);

driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

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

driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);

driver.findElement(By.name("beginDay")).clear();

driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);

driver.findElement(By.name("beginYear")).clear();

driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);

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
driver.close();
driver = new InternetExplorerDriver();

}



public void RPP(String SSN,String FileName) throws Exception{

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


			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
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



public void AgeStore_EPP(String SSN,String FileName,int Days, int i) throws Exception
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
			DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+i+"]/td[2]")).getText();
			//*[@id="PPNScheduleHistoryTable"]/tbody/tr[2]/td[2]
		//	DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[+i+]/td[4]")).getText();
			//*[@id="achHistoryTable"]/tbody/tr[2]/td[4]
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("mm/dd/yyyy");		
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
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



			//String DDueDate[] =DueDate.split("/");


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



public void RPP_Payment(String SSN,String FileName) throws Exception{
	
	
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

public void Represent_Status(String SSN,String FileName) throws Exception
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
					//String ScdCnt1= String.valueOf(ScdCnt);
					

		}
	}
}


public void RCC_Revoke(String SSN,String FileName) throws Exception
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
		driver.findElement(By.name("transactionList")).sendKeys("RCC Revoke");
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
	    driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='RCC Revoke')]")).click();
		//driver.findElement(By.name("bt_BankDetails")).click(); 
		test.log(LogStatus.PASS, "Clicked on RCC Revoke");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		if(driver.findElement(By.name("bt_BankDetails")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "RCC Revoke Transaction completed successfully.");
			driver.findElement(By.name("bt_BankDetails")).click(); 
			test.log(LogStatus.PASS, "Clicked on Ok button");
		}
		else
		{
			test.log(LogStatus.PASS, "RCC Revoke Transaction not completed successfully.");
		}
		/*Thread.sleep(5000);
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
			//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
		{
			test.log(LogStatus.PASS, "RCC Tracking Status is displayed in History screen");
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
		}*/
	}
}
}



public void PendingBNK_Void(String SSN,String FileName) throws Exception
{
Excel TestData = new
Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
        String BNKstatus1=TestData.getCellData(sheetName,"BNKstatus1",row);
        String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
        String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
        String SSN1 = SSN.substring(0, 3);
        String SSN2 = SSN.substring(3,5);
        String SSN3 = SSN.substring(5,9);
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

driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void Pending");
     test.log(LogStatus.PASS, "select status as :" + "Void Pending Bankruptcy");

driver.findElement(By.name("bt_AddBankruptcy")).click();
         test.log(LogStatus.PASS, "Status BNKPending is Saved");


        }
    }



/*if(driver.findElement(By.name("submitButton")).isDisplayed())
        {
         test.log(LogStatus.PASS, "Store Aging is Successfully ");
driver.findElement(By.name("submitButton")).click();
        }
     else
        {
            test.log(LogStatus.FAIL, "Store Aging is not
Successfully ");
        }*/
    //driver.close();
}






	
	
	@Test (priority=0)
	
	 public void LOCI_Draw_stmt_Deposit_ReturnwithR01_DLQ_Cure_DFLT_RPP_PPAYwithCash_PBNKbeforeduedate_VoidPBNKonduedate_RepDepRecordshouldpost_Sc108() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_stmt_Deposit_ReturnwithR01_DLQ_Cure_DFLT_RPP_PPAYwithCash_PBNKbeforeduedate_VoidPBNKonduedate_RepDepRecordshouldpost_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
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
	            test = reports.startTest(Header+"_S.No:108"+"_"+PayFrequency+"_"+CollateralType,"LOCI=>Draw=>Statement =>Depsoit=>Rtn=>DLQ=>CURE=>DFLT=> RPP=>PPAY WD Cash => PBNK Before Due date =>Void PBNK on due date=> deposit reperesenment");
		        appUrl = AppURL;
		       
		        this.Login(UserName,Password,StoreId);			        
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
		        this.NewLoan(SSN,FileName);  	       
		        this.Agestore_Loandate(SSN, FileName, 2);
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
			    this.AgeStore(SSN, FileName, 3);
			    this.ACHReturnPosting(SSN, FileName);			    
		        this.DeliquentPaymentStatus(SSN, FileName);
		        this.AgeStore(SSN,FileName, 10);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);
		        this.AgeStore(SSN,FileName, 30);		       
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.RCC_Revoke(SSN, FileName);	
		        
		        this.AgeStore(SSN, FileName, 3);
		        this.RPP(SSN, FileName);
		        this.RPP_Status(SSN, FileName);
		        this.AgeStore_EPP(SSN, FileName,-3,2);
		        this.RPP_Payment(SSN, FileName);
		        this.Represent_Status(SSN, FileName);
		        this.AgeStore_EPP(SSN, FileName,-2,2);
		        this.PendingBNK(SSN, FileName);
		        this.AgeStore_EPP(SSN, FileName,0,2);
		        this.PendingBNK_Void(SSN, FileName);
		        this.Represent_Status(SSN, FileName);
		}
}
}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 
			String screenshotPath = ExecuteScripts.getScreenhot(driver, result.getName());
			//To add it in the extent report 
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
		//Call close() at the very end of your session to clear all resources. 
		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		//Once this method is called, calling any Extent method will throw an error.
		//close() - To close all the operation
		//driver.quit();


	}
	@AfterClass

	public void closeBrowser() throws Exception{

		driver.quit();

	}
}
