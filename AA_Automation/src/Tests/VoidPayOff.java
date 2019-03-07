package Tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.IOException;

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

public class VoidPayOff {
	
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
				
		String filename="NewLoan_Draw_PayOff"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/VoidPayOffLoan/"+filename, true);
	}

	@BeforeTest
	public void setup() throws IOException {
		System.setProperty("webdriver.ie.driver","E:/QC_Workspace/AA_Automation/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		 //driver.quit();
		//appUrl = "http://192.168.2.203/cc/demoIndex.do";
	}

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
	        //System.out.println("user name::: "+username);

	        //Enter Password
	        //writeText(By.name(passwordId), password);
		    driver.findElement(By.name(passwordId)).clear();
		    driver.findElement(By.name(passwordId)).sendKeys(password);
	        test.log(LogStatus.PASS, "Password is entered: "+password);
	       // System.out.println("user pwd::: "+password);
	        //writeText(By.name(StoreId), storenumber);
	        driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
	        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
	        //Click Login Button
	        driver.findElement(By.name(Login)).click();
	        test.log(LogStatus.PASS, "Clicked on Submit button");
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
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
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
				 if(driver.findElement(By.id("LoanButtonId")).isEnabled())
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
						if(StoreID.equals("4330"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						}
						if(StoreID.equals("4353"))
						{
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						
						}
						if(StoreID.equals("1343"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
					}
				
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
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
	public void AgeStore(String SSN,String FileName) throws Exception
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
						
								 DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				   										
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
				   storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
			}
		}
	}
			
	public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
			{
									
				   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			        test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
			        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			        //Click Login Button
			        driver.findElement(By.name("login")).click();
			        test.log(LogStatus.PASS, "Clicked on Submit button");
			        Thread.sleep(8000);
			        driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Store Setup");
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
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
						    String DueDate0[] =DueDate.split("/");
					        String DueDate1 = DueDate0[0];
					        String DueDate2 = DueDate0[1];
					        String DueDate3 = DueDate0[2];
					        driver.findElement(By.name("procDt1")).clear();
					        driver.findElement(By.name("procDt1")).sendKeys(DueDate1); 
					        test.log(LogStatus.PASS, "DueDate1 is entered: "+DueDate1);
					        driver.findElement(By.name("procDt2")).clear();
					        driver.findElement(By.name("procDt2")).sendKeys(DueDate2);
					        test.log(LogStatus.PASS, "DueDate2 is entered: "+DueDate2);
					        driver.findElement(By.name("procDt3")).clear();
					        driver.findElement(By.name("procDt3")).sendKeys(DueDate3);
					        test.log(LogStatus.PASS, "DueDate3 is entered: "+DueDate3);
					        Thread.sleep(5000);
					        driver.findElement(By.name("submitButton")).click();
					        test.log(LogStatus.PASS, "Clicked on submitted button");
					       // driver.close();		
					        driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");													    	
							 if(driver.findElement(By.name("Submit")).isDisplayed())
								{
								 test.log(LogStatus.PASS, "Store Aging is Successfully ");
									driver.findElement(By.name("Submit")).click();
								}
							 else
								{
									test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
								}
							// Thread.sleep(2000);
							//driver.close();
							// Thread.sleep(2000);
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
				//Thread.sleep(5000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
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
public void Void_Payoff(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String TxnType=TestData.getCellData(sheetName,"TxnType",row);
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
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							// System.out.println(Pmt);
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish VoidPayOff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Void PayOff Payment Completed Successfully ");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void PayOff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
	

	
	@Test (priority=0)
	
	 public void RegistrationTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_VoidPayOff_Txn_Testdata.xls";
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
		       // System.out.println(Password);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("VoidPayOff"+Header, "Void PayOff");
		        appUrl = AppURL;
		      //this.Login(UserName,Password,StoreId);
		       //this.AgeStore(SSN,FileName);
		       //this.PayOffLoan(SSN,FileName);
		       
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoanDraw(SSN,FileName);
		        this.AgeStore(SSN,FileName);
		       // Thread.sleep(5000);
		        this.PayOffLoan(SSN,FileName);
		        this.Void_Payoff(SSN, FileName);
		        
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
	
	


