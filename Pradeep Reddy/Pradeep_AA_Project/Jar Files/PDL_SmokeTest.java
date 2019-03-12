package Tests;

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
import java.util.Iterator;
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
import org.openqa.selenium.Keys;
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
import Utilities.ExtentReports.Excel;
import scala.collection.Set;
	
public class PDL_SmokeTest {
	
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
					
			String filename="PDL_SmokeTest"+timestamp+".html";
			//System.out.print(filename);
			reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/PDL_Scenarios/"+filename, true);
			//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/PDL/ShortListedScenarios.html", true);
		}

@BeforeMethod
	public void KillAcroRd32() throws IOException, InterruptedException {
			
			Runtime.getRuntime().exec("taskkill /T /F /IM AcroRd32.exe");
			}
@BeforeTest
	public void setup() throws IOException, InterruptedException {
			Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
			//Thread.sleep(4000);
            System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
			driver = new InternetExplorerDriver();		
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//appUrl = "http://192.168.2.203/cc/demoIndex.do";
		}
//@BeforeTest
	public void Login (String username,String password,String storenumber) throws InterruptedException {
			
		//Launch URL
				driver.get(appUrl);
				test.log(LogStatus.INFO, "CSR Application is launched:"+appUrl);
				driver.manage().window().maximize();
				String usenameId = "loginRequestBean.userId";
			    String passwordId = "loginRequestBean.password";
			    String StoreId = "loginRequestBean.locNbr";
			    String Login = "login";
			    driver.findElement(By.name(usenameId)).sendKeys(username);
		        test.log(LogStatus.PASS, "Username is entered: "+username);
		        driver.findElement(By.name(passwordId)).clear();
			    driver.findElement(By.name(passwordId)).sendKeys(password);
		        test.log(LogStatus.PASS, "Password is entered: "+password);
		        driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		        driver.findElement(By.name(Login)).click();
		        test.log(LogStatus.PASS, "Clicked on Submit button");
		        Thread.sleep(2000);
		}
					
public void RegistrationPage(String SSN,String FileName) throws Exception{
			
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);     		
	int lastrow=TestData.getLastRow("Borrower_Registration");
			
			String sheetName="Borrower_Registration";		
			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{	
					
			   String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   String LastName = TestData.getCellData(sheetName,"LastName",row);
		       String FirstName = TestData.getCellData(sheetName,"FirstName",row);
		       String AddressLine = TestData.getCellData(sheetName,"AddressLine",row);
		       String City = TestData.getCellData(sheetName,"City",row);
		       String State = TestData.getCellData(sheetName,"State",row);	      
		       String ZipCode = TestData.getCellData(sheetName,"ZipCode",row);
		       String MonthsAtAddress = TestData.getCellData(sheetName,"MonthsAtAddress",row);	     
		       String Bank_ABARoutingNbr = TestData.getCellData(sheetName,"Bank_ABARoutingNbr",row);
		       String Bank_ChkgAcctNbr = TestData.getCellData(sheetName,"Bank_ChkgAcctNbr",row);	       
		       String Ctc_PrimaryPhone = TestData.getCellData(sheetName,"Ctc_PrimaryPhone",row);
		       String Ctc_PhoneType = TestData.getCellData(sheetName,"Ctc_PhoneType",row);
		       String Misc_PhotoIDNbr = TestData.getCellData(sheetName,"Misc_PhotoIDNbr",row);
		       String Misc_IDExpDate = TestData.getCellData(sheetName,"Misc_IDExpDate",row);	   
		       String Misc_PhotoIDType = TestData.getCellData(sheetName,"Misc_PhotoIDType",row);
		       String BorrDOB = TestData.getCellData(sheetName,"Misc_DOB",row);
		       String Income_IncomeType = TestData.getCellData(sheetName,"Income_IncomeType",row);
		       String Income_Employer = TestData.getCellData(sheetName,"Income_Employer",row);
		       String Income_WorkPhone = TestData.getCellData(sheetName,"Income_WorkPhone",row);
		       String Income_NetIncomeAmt = TestData.getCellData(sheetName,"Income_NetIncomeAmt",row);
		       String Income_GrossIncome = TestData.getCellData(sheetName,"Income_GrossIncome",row);
		       String Income_PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		       String Income_HireDt = TestData.getCellData(sheetName,"Income_HireDt",row);
		       String Income_DirectDeposit=TestData.getCellData(sheetName,"Income_DirectDeposit",row);	
		       String ProductType=TestData.getCellData(sheetName,"ProductType",row);
		       String Bankruptcy=TestData.getCellData(sheetName,"Bankruptcy",row);	
		        test.log(LogStatus.INFO, "Navigate to Barrower Registration " );
				test.log(LogStatus.INFO, "Borrower Registration-SSN: " +SSN);
					    DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
				        String SSN1 = SSN.substring(0, 3);
				        String SSN2 = SSN.substring(3,5);
				        String SSN3 = SSN.substring(5,9);
				        String PP1 = Ctc_PrimaryPhone.substring(0, 3);
				        String PP2 = Ctc_PrimaryPhone.substring(3, 6);
				        String PP3 = Ctc_PrimaryPhone.substring(6, 10);
				        String IncomeP1 = Income_WorkPhone.substring(0, 3);
				        String IncomeP2 = Income_WorkPhone.substring(3, 6);
				        String IncomeP3 = Income_WorkPhone.substring(6, 10);
				      	System.out.println(Misc_IDExpDate);
				        Date Misc_IDExpDt = df.parse(Misc_IDExpDate);
				        String IDExpDate0 =df.format(Misc_IDExpDt);	
				        System.out.println(IDExpDate0);
				        String IDExpDate[] =IDExpDate0.split("/");
				        String IDExpD1 = IDExpDate[0];
				        String IDExpD2 = IDExpDate[1];
				        String IDExpD3 = IDExpDate[2];
				        String DOB[] =BorrDOB.split("/");
				        String DOB1 = DOB[0];
				        String DOB2 = DOB[1];
				        String DOB3 = DOB[2];	
				        appUrl = AppURL;
				        Thread.sleep(2000);
				WebDriverWait wait = new WebDriverWait(driver, 1000);	
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
		        driver.findElement(By.cssSelector("li[id='900000']")).click();	
				test.log(LogStatus.PASS, "Clicked on Borrower");
				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");			 
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='901000']")));
				driver.findElement(By.cssSelector("li[id='901000']")).click();			
				test.log(LogStatus.PASS, "Clicked on Registration");			
				driver.switchTo().frame("main");
				driver.findElement(By.name("customerBean.custProdType")).sendKeys(ProductType);
				test.log(LogStatus.PASS, "ProductType is entered: "+ProductType);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("ssn4")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "Confirm SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn5")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "Confirm SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn6")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "Confirm SSN3 is entered: "+SSN3);			
				driver.findElement(By.name("customerBean.lastNm")).sendKeys(LastName);
				test.log(LogStatus.PASS, "LastName is entered: "+LastName);
				driver.findElement(By.name("customerBean.firstNm")).sendKeys(FirstName);
				test.log(LogStatus.PASS, "FirstName is entered: "+FirstName);
				driver.findElement(By.name("customerBean.addressLn")).sendKeys(AddressLine);
				test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
				driver.findElement(By.name("customerBean.city")).sendKeys(City);
				test.log(LogStatus.PASS, "City is entered: "+City);
				driver.findElement(By.name("customerBean.stateCd")).sendKeys(State);
				test.log(LogStatus.PASS, "State is entered: "+State);
				driver.findElement(By.name("customerBean.postalCd")).sendKeys(ZipCode);
				test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
				driver.findElement(By.name("customerBean.sameMailAddress")).click();
				test.log(LogStatus.PASS, "Mailing address is selected as same as above");
				driver.findElement(By.name("customerBean.monthsAtAddress")).sendKeys(MonthsAtAddress);
				test.log(LogStatus.PASS, "MonthsAtAddress is entered: "+MonthsAtAddress);			
				driver.findElement(By.name("customerBean.rentOwnFlg")).sendKeys("Yes");
				test.log(LogStatus.PASS, "Own Residence?* is entered: Yes");
				driver.findElement(By.name("phoneNbr1")).sendKeys(PP1);
				test.log(LogStatus.PASS, "PP1 is entered: "+PP1);
				driver.findElement(By.name("phoneNbr2")).sendKeys(PP2);
				test.log(LogStatus.PASS, "PP2 is entered: "+PP2);
				driver.findElement(By.name("phoneNbr3")).sendKeys(PP3);
				test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
				Select PhoneType  = new Select(driver.findElement(By.name("customerBean.phoneCd")));
				PhoneType.selectByVisibleText(Ctc_PhoneType);
				test.log(LogStatus.PASS, "Phone Type is selected as: "+Ctc_PhoneType);
				driver.findElement(By.name("sphoneNbr1")).sendKeys(PP1);
				test.log(LogStatus.PASS, "SPP1 is entered: "+PP1);
				driver.findElement(By.name("sphoneNbr2")).sendKeys(PP1);
				test.log(LogStatus.PASS, "SPP2 is entered: "+PP1);
				driver.findElement(By.name("sphoneNbr3")).sendKeys(PP3);
				test.log(LogStatus.PASS, "SPP3 is entered: "+PP3);
				Select SubPhoneType  = new Select(driver.findElement(By.name("customerBean.cphoneCd")));
				SubPhoneType.selectByVisibleText(Ctc_PhoneType);
				test.log(LogStatus.PASS, "Secondary Phone Type is selected as: "+Ctc_PhoneType);
				driver.findElement(By.name("customerBean.isCustomerEmailQuest")).click();
				test.log(LogStatus.PASS, "Does not have e-mail selected");
				driver.findElement(By.name("customerBean.driversLicNbr")).sendKeys(Misc_PhotoIDNbr);
				test.log(LogStatus.PASS, "PhotoIDNbr is entered: "+Misc_PhotoIDNbr);
				driver.findElement(By.name("customerBean.driversLicSt")).sendKeys(State);
				test.log(LogStatus.PASS, "ID State is entered: "+State);
				driver.findElement(By.name("dlexpiry1")).sendKeys(IDExpD1);
				test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD1);
				driver.findElement(By.name("dlexpiry2")).sendKeys(IDExpD2);
				test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD2);
				driver.findElement(By.name("dlexpiry3")).sendKeys(IDExpD3);
				test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD3);
				driver.findElement(By.name("customerBean.photoIdType")).sendKeys(Misc_PhotoIDType);
				test.log(LogStatus.PASS, "PhotoIDType is entered: "+Misc_PhotoIDType);
				driver.findElement(By.name("customerBean.drivingZipcode")).sendKeys(ZipCode);
				test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
				driver.findElement(By.name("dob1")).sendKeys(DOB1);
				test.log(LogStatus.PASS, "DOB1 Date1 is entered: "+DOB1);
				driver.findElement(By.name("dob2")).sendKeys(DOB2);
				test.log(LogStatus.PASS, "DOB3 is entered: "+DOB2);
				driver.findElement(By.name("dob3")).sendKeys(DOB3);
				test.log(LogStatus.PASS, "DOB3 is entered: "+DOB3);
				//driver.findElement(By.name("PhoneNbr2")).sendKeys(PP3);
				driver.findElement(By.name("customerBean.incomeCdDisp")).sendKeys(Income_IncomeType);
				test.log(LogStatus.PASS, "IncomeType is entered: "+Income_IncomeType);
				driver.findElement(By.name("customerBean.empNmDisp")).sendKeys(Income_Employer);
				test.log(LogStatus.PASS, "Employer is entered: "+Income_Employer);
				driver.findElement(By.name("workPhoneNbrDisp1")).sendKeys(IncomeP1);
				test.log(LogStatus.PASS, "PP1 is entered: "+IncomeP1);
				driver.findElement(By.name("workPhoneNbrDisp2")).sendKeys(IncomeP2);
				test.log(LogStatus.PASS, "PP2 is entered: "+IncomeP2);
				driver.findElement(By.name("workPhoneNbrDisp3")).sendKeys(IncomeP3);
				test.log(LogStatus.PASS, "PP3 is entered: "+IncomeP3);
				driver.findElement(By.name("customerBean.incomeAmtDisp")).sendKeys(Income_NetIncomeAmt);
				test.log(LogStatus.PASS, "Income_NetIncomeAmt is entered: "+Income_NetIncomeAmt);
				driver.findElement(By.name("customerBean.grossAmtDisp")).sendKeys(Income_GrossIncome);
				test.log(LogStatus.PASS, "Income_GrossIncome is entered: "+Income_GrossIncome);
				driver.findElement(By.name("customerBean.payFreqCdDisp")).sendKeys(Income_PayFrequency);
				test.log(LogStatus.PASS, "Income_PayFrequency is entered: "+Income_PayFrequency);
				String Parent_Window = driver.getWindowHandle();
				if(Income_PayFrequency.equals("Semi-Monthly"))
				{
					driver.findElement(By.id("rad_semi1")).click();
					test.log(LogStatus.PASS, "The 1st and 16th day of each month is selected");
				}
				if(Income_PayFrequency.equals("Bi-Weekly"))
				{
					driver.findElement(By.id("rad_wk4")).click();
					test.log(LogStatus.PASS, "Wednesday is selected");
					driver.findElement(By.id("biwksndid")).click();
					test.log(LogStatus.PASS, "Which day is your next Pay date? is selected as last date radio button");				
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("bottom");
				String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
				String Busdate[]=BusinessDt.split(":");
				String date = Busdate[1];
				
				Date d1 = df.parse(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d1);
				cal.add(Calendar.DATE, -20);
				Date PayStubReviewedDate1= cal.getTime();
				 
				String PayStubReviewedDate =df.format(PayStubReviewedDate1);
				String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");
			    String PayStubReviewedDate2 = PayStubReviewedDate0[0];
			    String PayStubReviewedDate3 = PayStubReviewedDate0[1];
			    String PayStubReviewedDate4 = PayStubReviewedDate0[2];
			        driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
			        driver.findElement(By.name("payStubReviewed1")).sendKeys(PayStubReviewedDate2);
					test.log(LogStatus.PASS, "PayStubReviewed1 is entered: "+PayStubReviewedDate2);
					driver.findElement(By.name("payStubReviewed2")).sendKeys(PayStubReviewedDate3);
					test.log(LogStatus.PASS, "PayStubReviewed2 is entered: "+PayStubReviewedDate3);
					driver.findElement(By.name("payStubReviewed3")).sendKeys(PayStubReviewedDate4);
					test.log(LogStatus.PASS, "PayStubReviewed3 is entered: "+PayStubReviewedDate4);
					cal.add(Calendar.DATE, -30);
					Date PayStubDate1= cal.getTime();
					String PayStubDate =df.format(PayStubDate1);
					String PayStubDate0[] =PayStubDate.split("/");
				    String PayStubDate2 = PayStubDate0[0];
				    String PayStubDate3 = PayStubDate0[1];
				    String PayStubDate4 = PayStubDate0[2];
				    driver.findElement(By.name("payStubDate1")).sendKeys(PayStubDate2);
				    test.log(LogStatus.PASS, "payStubDate1 is entered: "+PayStubDate2);
					driver.findElement(By.name("payStubDate2")).sendKeys(PayStubDate3);
					test.log(LogStatus.PASS, "payStubDate2 is entered: "+PayStubDate3);
					driver.findElement(By.name("payStubDate3")).sendKeys(PayStubDate4);
					test.log(LogStatus.PASS, "payStubDate3 is entered: "+PayStubDate4);
						
					String Income_HireDt0[] =Income_HireDt.split("/");
				    String Income_HireDt1 = Income_HireDt0[0];
				    String Income_HireDt2 = Income_HireDt0[1];
				    String Income_HireDt3 = Income_HireDt0[2];
				        
				    driver.findElement(By.name("hireDate1")).sendKeys(Income_HireDt1);
				    test.log(LogStatus.PASS, "hireDate1 is entered: "+Income_HireDt1);
					driver.findElement(By.name("hireDate2")).sendKeys(Income_HireDt2);
					test.log(LogStatus.PASS, "hireDate2 is entered: "+Income_HireDt2);
					driver.findElement(By.name("hireDate3")).sendKeys(Income_HireDt3);
					test.log(LogStatus.PASS, "hireDate3 is entered: "+Income_HireDt3);
			
					driver.findElement(By.name("customerBean.directDeposit")).sendKeys(Income_DirectDeposit);
					test.log(LogStatus.PASS, "DirectDeposit is entered: "+Income_DirectDeposit);
					cal.add(Calendar.DATE, -60);
					Date Bank_AcctVerificationDt0= cal.getTime();
				 
					String Bank_AcctVerificationDt =df.format(Bank_AcctVerificationDt0);
					String Bank_AcctVerificationDt1[] =Bank_AcctVerificationDt.split("/");
			        String Bank_AcctVerificationDt2 = Bank_AcctVerificationDt1[0];
			        String Bank_AcctVerificationDt3 = Bank_AcctVerificationDt1[1];
			        String Bank_AcctVerificationDt4 = Bank_AcctVerificationDt1[2];
			        driver.findElement(By.name("statementEndDtDisp1")).sendKeys(Bank_AcctVerificationDt2);
			        test.log(LogStatus.PASS, "Bank_AcctVerificationDt1 is entered: "+Bank_AcctVerificationDt2);
			        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.name("statementEndDtDisp2")).sendKeys(Bank_AcctVerificationDt3);
					test.log(LogStatus.PASS, "Bank_AcctVerificationDt2 is entered: "+Bank_AcctVerificationDt3);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.name("statementEndDtDisp3")).sendKeys(Bank_AcctVerificationDt4);
					test.log(LogStatus.PASS, "Bank_AcctVerificationDt3 is entered: "+Bank_AcctVerificationDt4);
					driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
					test.log(LogStatus.PASS, "Bank_ABARoutingNbr is entered: "+Bank_ABARoutingNbr);
					driver.findElement(By.name("checkAbaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
					test.log(LogStatus.PASS, "Confirm ABA/Routing Nbr is entered: "+Bank_ABARoutingNbr);
					driver.findElement(By.name("customerBean.accountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
					test.log(LogStatus.PASS, "Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
					driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
					test.log(LogStatus.PASS, "Confirm Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
					driver.findElement(By.name("customerBean.bankrupty")).sendKeys(Bankruptcy);
					test.log(LogStatus.PASS, "Bankrupty is selected as: "+Bankruptcy);
					driver.findElement(By.name("SLoan")).click();							
					test.log(LogStatus.PASS, "Clicked on Save&Loan");
					Thread.sleep(3000);
					
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
									
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 
					
						 	if(driver.findElement(By.id("LoanButtonId")).isEnabled())
						 	{
							test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);						
						 	}
							else
							{
							test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
							}
						 	
							
						 }
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
	public boolean IsElementExits(String Value) {
		    int secondsToWait = 5;

		    try {
		        new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Value)));
		        return true;
		    } catch (org.openqa.selenium.TimeoutException e) {
		        return false;
		    }
		}
public void NewLoan(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);    	
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
					String ProductType = TestData.getCellData(sheetName,"ProductType",row);
					String ProductName = TestData.getCellData(sheetName,"ProductName",row);
					//String Term = TestData.getCellData(sheetName,"Term",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
					//System.out.println(Term);
					//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
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
					String Parent_Window = driver.getWindowHandle();
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 //	Selection of Product based on the Name provided in Test Data
					
					 //if(driver.findElement(By.id("LoanButtonId")).isEnabled())
					 test.log(LogStatus.INFO, "Navigate New Loan ");
					 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
					 {
						 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
					//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
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
							////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("TN PDL SF"))
						{
			
							WebDriverWait wait = new WebDriverWait(driver, 10);	
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")));
							//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")));
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
							//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
							//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("Tennessee"))
						{
							driver.findElement(By.xpath("//*[@id='termSel1']")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("Line of Credit"))
						{
							
							if(StoreID.equals("5436"))
							{
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
								//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
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
						driver.findElement(By.name("ShareScreenBtn")).click();

						test.log(LogStatus.PASS, "ShareScreen Button clicked");

						for( String winHandle1 : driver.getWindowHandles())

						{

						if(!(winHandle1.equals(Parent_Window)))

						{

						driver.switchTo().window(winHandle1);

						Thread.sleep(2000);

						driver.findElement(By.name("confirmSummary")).click();

						test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");

						}

						}

						Thread.sleep(2000);

						driver.switchTo().window(Parent_Window);
						driver.switchTo().defaultContent();
	                    driver.switchTo().frame("mainFrame");
	                    driver.switchTo().frame("main");
	                    driver.findElement(By.id("LoanButtonId")).click();
	                             //driver.findElement(By.id("LoanButtonId")).click();

	                     test.log(LogStatus.PASS, "Clicked on New Loan button");
					//New Loan Screens
						if(ProductID.equals("PDL"))
						{	
						
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
							//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/input")).sendKeys(ESign_CollateralType);
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
							Thread.sleep(2000);
							String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
							System.out.println(Instamt);
							driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
							//Thread.sleep(2000);
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
							WebDriverWait wait1 = new WebDriverWait(driver, 1000);	
							wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
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
							test.log(LogStatus.PASS, "clicked on Yes button ");
							//Thread.sleep(2000);
							/*for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("bdyLoad");*/
							if(driver.findElement(By.name("Ok")).isDisplayed())
							{
								driver.findElement(By.name("Ok")).click();
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
					 			 int rand1 = rand.nextInt(1000);	
					 			 String chknum = Integer.toString(rand1);
					 			driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);
					 			test.log(LogStatus.PASS, "Enter CHK Number"+chknum);
					 			
					 			
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
								driver.findElement(By.name("ok")).click();
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
							WebDriverWait wait1 = new WebDriverWait(driver, 10);
							wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
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
							wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
							driver.findElement(By.name("button2")).click();			
							driver.findElement(By.name("button2")).click();	
							//driver.findElement(By.name("button2")).click();	
							//driver.findElement(By.name("button2")).click();	
							test.log(LogStatus.PASS, "click on Update 2 button ");
							Thread.sleep(2000);
							wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
							driver.findElement(By.name("process")).click();
							//driver.findElement(By.name("process")).click();
							//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
							test.log(LogStatus.PASS, "click on process Loan button ");
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							//Thread.sleep(3000);
							wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
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
								//String mwh=driver.getWindowHandle();
								driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
								test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
								//String winHandle = driver.getWindowHandle(); //Get current window handle.									
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
						
							driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
							test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
							Thread.sleep(3000);
							driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
							driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
							driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
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
							
							if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).isDisplayed())
							{
								test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
								driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).click();
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
			
public boolean isAlertPresent(){
			 try{
			  driver.switchTo().alert();
			  return true;
			 }catch(NoAlertPresentException ex){
			  return false;
			 }
		}
public void NewLoan_MultipulDisbTypes(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  
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
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			//String Term = TestData.getCellData(sheetName,"Term",row);
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String Esign_DisbType1 = TestData.getCellData(sheetName,"Esign_DisbType1",row);
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
			String Parent_Window = driver.getWindowHandle();
			for( String winHandle1 : driver.getWindowHandles())
			{
			driver.switchTo().window(winHandle1);
			} 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
			   test.log(LogStatus.PASS, "ShareScreenBtn is Enabled ");
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
			if(ProductName.equals("TN PDL SF"))
			{ 
				//driver.findElement(By.name("prodSel")).click();
			    driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
			  //driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
			    							 //*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]
			    test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("Tennessee"))
			{
			driver.findElement(By.xpath("//*[@id='termSel1']")).click();
			test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("Line of Credit"))
			{
			if(StoreID.equals("5436"))
			{
			driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
			}
			if(StoreID.equals("4353"))
			{
			driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
			test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(StoreID.equals("1343"))
			{
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
			//Thread.sleep(3000);
			driver.findElement(By.name("confirmSummary")).click();
			test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
			}
		}
			Thread.sleep(3000);
			driver.switchTo().window(Parent_Window);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.id("LoanButtonId")).click();
			//driver.findElement(By.id("LoanButtonId")).click();
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
			test.log(LogStatus.PASS, " Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);
			driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
			test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
			//Thread.sleep(4000);
			driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys("150"); 
			test.log(LogStatus.PASS, "Disb Amt is enterted as 150 ");
			driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(Esign_DisbType1);
			test.log(LogStatus.PASS, "Disb Type2 is selected as ::Check");
			driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("175");
			test.log(LogStatus.PASS, "Disb Amt1 is enterted as 175");
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
			driver.findElement(By.xpath("//*[@id='OKBut']")).click(); 
			//driver.findElement(By.xpath("//*[@id='/html/body/form[1]/table/tbody/tr[11]/td/table/tbody/tr[1]/td[5]/input']")).click();
			//html/body/form[1]/table/tbody/tr[11]/td/table/tbody/tr[1]/td[5]/input			
			test.log(LogStatus.PASS, "click on Yes button ");
			/*for( String winHandle1 : driver.getWindowHandles())
			{
			driver.switchTo().window(winHandle1);
			} 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("bdyLoad");*/
			Thread.sleep(2000);
			if(driver.findElement(By.name("Ok")).isDisplayed())
		    test.log(LogStatus.PASS, "Ok Button is Displayed ");
			{
			driver.findElement(By.name("Ok")).click();
			test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
			}
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
			// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
			//driver.findElement(By.name("button2")).click(); 
			//driver.findElement(By.name("button2")).click(); 
			test.log(LogStatus.PASS, "click on Update 2 button ");
			//Thread.sleep(8000);
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

			//Thread.sleep(4000);

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

			//String mwh=driver.getWindowHandle();

			driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);

			test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);

			//String winHandle = driver.getWindowHandle(); //Get current window handle. 

			}

			driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
			test.log(LogStatus.INFO, "Enter PassWord : "+ESign_Password);

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
			test.log(LogStatus.INFO, "Click Ok Button");

			}

			else

			{

			test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");

			}

			}

			if(ProductID.equals("LOC"))

			{


			driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);

			test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);

			//Thread.sleep(3000);

			driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);

			test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);

			driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);

			test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);

			driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);

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


			if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).isDisplayed())

			{

			test.log(LogStatus.PASS, "New Loan is Completed Successfully ");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).click();

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


public void LoanDate_AgeStore(String SSN,String FileName, int days) throws Exception
		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  	
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
					//Thread.sleep(3000);
					//Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
					test.log(LogStatus.INFO, "transactionList Selected : History " );
					if(ProductID.equals("PDL"))
					{
					driver.findElement(By.id("go_Button")).click();					
					test.log(LogStatus.INFO, "Click Go Button " );
					}
					for( String winHandle1 : driver.getWindowHandles())
					{
					driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DueDate=null;
					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
														//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
					System.out.print(DueDate);
					driver.close();
					driver = new InternetExplorerDriver();
					driver.get(AdminURL);
					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);					
					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
					test.log(LogStatus.PASS, "Username is entered: admin");
					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is entered: "+Password);
					//Click Login Button
					driver.findElement(By.name("login")).click();
					test.log(LogStatus.PASS, "Clicked on Submit button");
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
			        //WebDriverWait wait4 = new WebDriverWait(driver, 10);	
			        driver.switchTo().frame("topFrame");
			        Thread.sleep(2000);
			        //wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
					Actions actions1 = new Actions(driver); 
					actions1.moveToElement(elements1).build().perform();
					
					/*wait4.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
					driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					//Thread.sleep(3000);
					
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");	
					
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String DDueDate[] =DueDate.split("/");
					//String date = DDueDate[1];
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("storeCode")).click();
					//Thread.sleep(2000);
					test.log(LogStatus.PASS, "Click Store Code ");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
					driver.findElement(By.name("storeCode")).sendKeys(StoreID);
					test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
					Thread.sleep(3000);
					driver.findElement(By.name("beginMonth")).clear();
					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
					driver.findElement(By.name("beginDay")).clear();
					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
					driver.findElement(By.name("beginYear")).clear();
					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
					//Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					//Thread.sleep(1000);
					//Thread.sleep(2000);
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
					
						test.log(LogStatus.PASS, "Process Date not updated successfully");

					}



				}
			}
		}

		
		
		public void AgeStore(String SSN,String FileName,int days) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  
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

					Thread.sleep(2000);

					driver.switchTo().frame("topFrame");					
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
					test.log(LogStatus.INFO, "transactionList as Selected : History " );
					if(ProductID.equals("PDL"))
					{
					driver.findElement(By.id("go_Button")).click();					
					test.log(LogStatus.PASS, "Click on GO Button");
					}
					for( String winHandle1 : driver.getWindowHandles())
					{
					driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DueDate=null;
					//String LoanAmount = null;					
					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
					//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
					System.out.print(DueDate);
					driver.close();
					
					driver = new InternetExplorerDriver();
					driver.get(AdminURL);
					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
					test.log(LogStatus.PASS, "Username is entered: admin");
					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is entered: "+Password);
					//Click Login Button
					driver.findElement(By.name("login")).click();
					test.log(LogStatus.PASS, "Clicked on Submit button");
					Thread.sleep(3000);
					//WebDriverWait wait1 = new WebDriverWait(driver, 2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");					
					//wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					//Thread.sleep(3000);
					
					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
					Actions actions1 = new Actions(driver); 
					actions1.moveToElement(elements1).build().perform();
					
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/					
					//Thread.sleep(3000);
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					
					/*WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
					test.log(LogStatus.PASS, "Click on QA Jobs");
					Actions actions1 = new Actions(driver); 
					actions1.moveToElement(elements1).build().perform();*/
					
					//Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String DDueDate[] =DueDate.split("/");
					//String date = DDueDate[1];					
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("storeCode")).click();
					Thread.sleep(1000);
					test.log(LogStatus.PASS, "Click on Store Edit Box");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
					driver.findElement(By.name("storeCode")).sendKeys(StoreID);
					test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
					Thread.sleep(3000);
					driver.findElement(By.name("beginMonth")).clear();
					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
					driver.findElement(By.name("beginDay")).clear();
					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
					driver.findElement(By.name("beginYear")).clear();
					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
					//Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					//Thread.sleep(1000);
					//Thread.sleep(3000);
					driver.findElement(By.name("btnPreview")).click();
					test.log(LogStatus.PASS, "Clicked on submit button");
					//Thread.sleep(3000);
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
						test.log(LogStatus.PASS, "Process Date not updated successfully");
					}
				}
			}
		}

	public void LoanPartialPayment(String SSN,String FileName) throws Exception{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  
			int lastrow=TestData.getLastRow("NewLoan");
			System.out.println("NewLoan "+lastrow);
			String sheetName="NewLoan";
			for(int row=2;row<=lastrow;row++)
			{
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
			String TxnType1=TestData.getCellData(sheetName,"TxnType1",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);			
			this.Login(UserName,Password,StoreId);				
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			//Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			//driver.findElement(By.xpath("//*[@id='900000']/a"));
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
			driver.findElement(By.name("transactionList")).sendKeys(TxnType1);			
			test.log(LogStatus.INFO, "transactionList Selected : " +TxnType1);
			//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");
			if(ProductID.equals("PDL"))
			{
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.INFO, "Clicked On GO Button " );
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
			driver.switchTo().window(winHandle1);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("PDL"))
			{
			// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
			//String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");
			// System.out.println(Pmt);
			driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
			driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("20");
			test.log(LogStatus.PASS, "Payment Amt is entered as 10");
			driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
			test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);
			//transactionDataBean.change	
			driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("20");
			test.log(LogStatus.PASS, "Tender Amt is entered as 20");
			driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
			driver.findElement(By.name("finish")).click();
			test.log(LogStatus.PASS, "Password is selected as "+Password);
			test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
			//Thread.sleep(1000);
			try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			//if alert present, accept and move on.
			}
			catch (NoAlertPresentException e) {
			//do what you normally would if you didn't have the alert.
			}
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
			// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]
			if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).isDisplayed())
			{
			test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");			
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).click();
			}
			else
			{
			test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");
			}
		}
	 }
  }
}

public void BuybackChange (String SSN,String FileName) throws Exception{
			
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);    	
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
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					//this.Login(UserName,Password,StoreId);    for(int j = 0; j < row.getLastCellNum(); j++){
			        //Fill data in row
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
					System.out.println(AdminURL);
					test.log(LogStatus.INFO, "Scheduler-Store Aging");
					System.out.println(ProductID);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					appUrl = AppURL;
					this.Login(UserName,Password,StoreID);
					driver.switchTo().defaultContent();		
					//Thread.sleep(3000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
					//Thread.sleep(3000);
					for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
						}				    
					 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    //driver.findElement(By.xpath("//input[@name='button'][@value='Go']")).click();
					    if(ProductID.equals("PDL"))
						 {
					    driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					      test.log(LogStatus.PASS, "Click on GO Button");
						 }
					    if(ProductID.equals("TLP"))
						 {
					    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
						 //Thread.sleep(3000);
						 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
						 test.log(LogStatus.PASS, "Transaction Type is selected as: "+TxnType);	
						 driver.findElement(By.id("go_Button")).click();
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 if(ProductID.equals("PDL"))
							 {
							// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td[1]/table/tbody/tr[1]/td[2]/input[2]")).sendKeys(LoanAmount);	
							 
							driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
							test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
							 //Thread.sleep(3000);
							 ////String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						 
							 String Pmt = driver.findElement(By.name("payAmt")).getAttribute("value");
							 System.out.println(Pmt);
							 //String var = Pmt+10;
								 //int pmt1 = Integer.parseInt(Pmt);
							 float pmt1=Float.parseFloat(Pmt);
								float pmt2 = pmt1+30;
							 test.log(LogStatus.INFO, "Given Total Amount "+pmt2);
							 String pmtv=Float.toString(pmt2);
							// String pmtv = String.valueOf(pmt2);
							 test.log(LogStatus.PASS, "Total Amount is as "+pmtv);
							 //String Pmt= driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");						 
							 System.out.println(pmtv);
							 						
							 driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(pmtv);						 
							 test.log(LogStatus.PASS, "Tender Amt is entered as "+pmtv);
								//String ChangeAmount = driver.findElement(By.name("transactionDataBean.change")).getAttribute("value");
							 	//String ChangeAmount1 = driver.findElement(By.name("transactionDataBean.change")).getText();
								String ChangeAmount1 = driver.findElement(By.name("transactionDataBean.change")).getCssValue("value");
								//test.log(LogStatus.INFO, "Change Amount is :: "+  ChangeAmount);
								test.log(LogStatus.INFO, "Change Amount is :: "+  "30");
							 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Entered Transction Password "+Password);							 
							 driver.findElement(By.name("finish")).click();
							 test.log(LogStatus.PASS, "Click finish button ");
							// Thread.sleep(3000);
							 
							 try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    String Var3 = alert.getText();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
							 
							
							 try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    String Var3 = alert.getText();
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
							 if(driver.findElement(By.name("checkyes")).isDisplayed())
								{
								 	Thread.sleep(1000);
									test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
									driver.findElement(By.name("checkyes")).click();
								}
								else
								{
									test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
								}
							 }
							 
							 if(ProductID.equals("TLP"))
							 {
							 driver.findElement(By.xpath("//*[@id='PD3']")).click();
							 test.log(LogStatus.PASS, " Pay Off the balance is selected ");
							 driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
							 //Thread.sleep(3000);
							 String Pmt= driver.findElement(By.name("payOff")).getAttribute("value");						 
							 System.out.println(Pmt);						 
							 driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Pmt);						 
							 test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
							 
						    	driver.findElement(By.name("requestBean.password")).sendKeys(Password);
						    	driver.findElement(By.name("finish")).click();											 						 
								test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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
								
								 if(driver.findElement(By.name("Ok")).isDisplayed())
									 
									{
									 test.log(LogStatus.PASS, "Ok Button is Enabled ");
									driver.findElement(By.name("Ok")).click();
									 test.log(LogStatus.PASS, "Ok Button Clicked ");
								     test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
								   
									}
								 else
									{
										test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
									}
								 }												
				}
		}
}			

public void Void(String SSN,String FileName) throws Exception{
			
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			//Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/QC_BuybackLoan_Void_Txn_Testdata.xls");  	
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
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

					System.out.println(AdminURL);

					test.log(LogStatus.INFO, "Scheduler-Store Aging");

					System.out.println(ProductID);

					String AppURL = TestData.getCellData(sheetName,"AppURL",row);

					appUrl = AppURL;

					this.Login(UserName,Password,StoreID);
					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
					    	
					    driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
						 }
					    if(ProductID.equals("TLP"))
						 {
					    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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
						 test.log(LogStatus.PASS, "transactionList Selected as Void ");
						 driver.findElement(By.id("go_Button")).click();
						 test.log(LogStatus.PASS, "Click on GO Button");
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 if(ProductID.equals("PDL"))
							 {
								//transactionDataBean.disbursementType ..DisbType
							
							//*[@id="collectchange"]/td[2]/b/input
						String Changeamt=	 driver.findElement(By.xpath("//*[@id='collectchange']/td[2]/b/input")).getAttribute("value");
						if(Changeamt.equals("30.00"))
						{
							test.log(LogStatus.PASS, "Change Amount  displayed in Void screen is as same as in BuyBack screen: " +Changeamt);
						}
						else
						{
							test.log(LogStatus.FAIL, "Change Amount  displayed in Void screen is not as same as in BuyBack screen: " +Changeamt);
						}
						 driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
							//*[@id="collectchange"]/td[2]/b/input
							// String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
							// System.out.println(Pmt);
							//	driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
							//	test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
							 }
							 if(ProductID.equals("TLP"))
							 {
							 driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(TenderType);
							 }
							 if(ProductID.equals("PDL"))
							 {
								 
								
									//transactionDataBean.password ..Password
									//Submit22 ..FinishVoid BuyBack
								 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
								 test.log(LogStatus.PASS, "Transaction Password entered as "+Password);
								 driver.findElement(By.name("Submit22")).click();
								 test.log(LogStatus.PASS, "Click Submit Button ");
								 try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														
										
									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}
							 }
						    if(ProductID.equals("TLP"))
							 {
						    	driver.findElement(By.name("requestBean.password")).sendKeys(Password);
						    	
						    	driver.findElement(By.name("finish")).click();
						    	driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[1]")).click();
							 }						 						 
								test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
								
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 if(ProductID.equals("TLP"))
								 {
							    	
								 if(driver.findElement(By.name("Ok")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Ok Button isDisplayed  ");
									   driver.findElement(By.name("Ok")).click();
									   test.log(LogStatus.PASS, "Click on GO Button");
									   test.log(LogStatus.PASS, "BuyBack Void  is Completed Successfully ");
									 	
									}
								 else
									{
									 test.log(LogStatus.FAIL, "BuyBack Void  is not Completed Successfully ");
									}
								 }
								 if(ProductID.equals("PDL"))
								 {
										
										//checkyes .. confirm yes button
										//checkno  ..confirm no
						}
								if(driver.findElement(By.name("checkyes")).isDisplayed())
								{
									test.log(LogStatus.PASS, "checkyes isDisplayed ");
									driver.findElement(By.name("checkyes")).click();
									test.log(LogStatus.PASS, "Yes Button is Clicked ");
									test.log(LogStatus.PASS, "BuyBack Void  is Completed Successfully ");
									
									
								}
								else
								{
									test.log(LogStatus.FAIL, "BuyBack Void  is not Completed Successfully ");
								}
				}
		}
				
}

public void PrenoteDeposit_6DaysBeforeDuedate(String SSN,String FileName,int Days) throws Exception
		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
					//Thread.sleep(3000);
					//Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					WebDriverWait wait4 = new WebDriverWait(driver, 30);				      
			        wait4.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='911101']")));
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
					 test.log(LogStatus.PASS, "Transaction List  Selected is; History ");
					if(ProductID.equals("PDL"))
					{
						driver.findElement(By.id("go_Button")).click(); 
						test.log(LogStatus.PASS, "Click on GO Button");
					}

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DueDate=null;
					
					                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
					
					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
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
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					WebDriverWait wait2 = new WebDriverWait(driver, 10);	
					wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					Thread.sleep(1000);
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
					driver.findElement(By.linkText("QA Jobs")).click();
					Thread.sleep(1000);
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
					Thread.sleep(1000);
					test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String DDueDate[] =DueDate.split("/");
					//String date = DDueDate[1];
					Date DDueDateminus1 = df.parse(DueDate);
					Calendar cal = Calendar.getInstance();
					 cal.setTime(DDueDateminus1);
					 cal.add(Calendar.DATE, -7);
					 Date DDueDate1= cal.getTime();
					 DueDate =df.format(DDueDate1);
				    String DueDate0[] =DueDate.split("/");
					String DueDate1 = DueDate0[0];
					String DueDate2 = DueDate0[1];
					String DueDate3 = DueDate0[2];

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.name("requestBean.locNbrCsr")).click();
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
					driver.findElement(By.name("requestBean.locNbrCsr")).sendKeys(StoreID);
					test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
					//Thread.sleep(3000);
					driver.findElement(By.name("beginMonth")).clear();
					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
					driver.findElement(By.name("beginDay")).clear();
					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
					driver.findElement(By.name("beginYear")).clear();
					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
					//Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					//Thread.sleep(1000);
					//Thread.sleep(3000);
					driver.findElement(By.name("btnPreview")).click();
					test.log(LogStatus.PASS, "Clicked on submit button");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
					{					
						test.log(LogStatus.PASS, "OK Button is Enabled");
						
					    driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
					    test.log(LogStatus.PASS, "PDL Pre Note Deposit Process updated successfully");
					   
					    
					}
					else
					{
						test.log(LogStatus.FAIL, "PDL Pre Note Deposit Process not updated successfully.");
					}




				}
			}
		}
		
		public void PrenoteClear_BeforeDuedate(String SSN,String FileName,int Days) throws Exception
		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
					//Thread.sleep(3000);
					//Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					WebDriverWait wait2 = new WebDriverWait(driver, 1000);	
					wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Loan Transactions')]")));
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
					wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='911101']")));
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
					test.log(LogStatus.PASS, "transactionList Selected as History");
					if(ProductID.equals("PDL"))
					{
						driver.findElement(By.id("go_Button")).click(); 
						test.log(LogStatus.PASS, "Click on GO Button");
						
					}

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DueDate=null;

					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
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
					Thread.sleep(2000);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					
					WebDriverWait wait3 = new WebDriverWait(driver, 2000);
					wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
					
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					//Thread.sleep(3000);
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
					driver.findElement(By.linkText("QA Jobs")).click();
					//Thread.sleep(2000);
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
					//Thread.sleep(2000);
					test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String DDueDate[] =DueDate.split("/");
					//String date = DDueDate[1];
					Date DDueDateminus1 = df.parse(DueDate);
					Calendar cal = Calendar.getInstance();
					cal.setTime(DDueDateminus1);
					cal.add(Calendar.DATE, -1);
					Date DDueDate1= cal.getTime();
					DueDate =df.format(DDueDate1);
					String DueDate0[] =DueDate.split("/");
					String DueDate1 = DueDate0[0];
					String DueDate2 = DueDate0[1];
					String DueDate3 = DueDate0[2];
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
						//Thread.sleep(3000);
						// driver.switchTo().defaultContent();
						//driver.switchTo().frame("topFrame");
						//driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
						//test.log(LogStatus.PASS, "Clicked on Transactions");
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						//Thread.sleep(3000);
						driver.findElement(By.linkText("EOD Batch Process")).click();
						test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
						driver.findElement(By.name("submit")).click();
						test.log(LogStatus.PASS, "Clicked on submit button");
						if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
						{
							test.log(LogStatus.PASS, "OK Button is Enabled");
							test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
							
						}
						else
						{
							test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
						}
						//driver.close();
					}



				}




			}
		
public void EditBorrower_Inactive(String SSN,String FileName) throws Exception
	  	{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
	  				//Thread.sleep(3000);
	  				String Monthlydate=null;
	  				String Monthlydate1=null;
	  				
	  				WebDriverWait wait = new WebDriverWait(driver, 1000);	
	  				
	  				driver.switchTo().frame("topFrame");
	  				driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();			
	  				test.log(LogStatus.PASS, "Clicked on Borrower");
	  				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  								
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
	  				//String NextPayday =null;
	  				driver.findElement(By.name("customerBean.activeFlgDisp")).sendKeys("Inactive");
	  				test.log(LogStatus.PASS,"Acount status is chend to Inactive");
	  				
	  				driver.findElement(By.name("Save")).click();							
					test.log(LogStatus.PASS, "Clicked on Save&Exit");
					//Thread.sleep(1000);
					try { 
					    Alert alert = driver.switchTo().alert();
					    alert.accept();
					    //if alert present, accept and move on.														
						
					}
					catch (NoAlertPresentException e) {
					    //do what you normally would if you didn't have the alert.
					}
					   
	  				
	   			
	   			//driver.quit();	//need to change to c

	  	   			
	  	   		}
	  	   	}
}
public void DrawerDeassign(String SSN,String FileName) throws Exception{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
					Thread.sleep(2000);
					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Cash Management");
					Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					/*try{
						driver.close();
					}
					catch (Exception e) {
						//do what you normally would if you didn't have the alert.
					}*/
					Thread.sleep(2000);
					driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
					driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
					test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
					//Thread.sleep(2000);
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
					/*try{
						driver.close();
					}
					catch (Exception e) {
						//do what you normally would if you didn't have the alert.
					}*/
					Thread.sleep(2000);
					
					for(String winHandle : driver.getWindowHandles()){
						driver.switchTo().window(winHandle);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
						//*[@id="denomDetails"]/tbody/tr/td[2]/table/tbody/tr[7]/td[2]/input
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
								Thread.sleep(1000);
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
					//Thread.sleep(3000);
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
													 //html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input
					}
					else
					{
						test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
					}
				}
			}
		}

public void EODProcessing(String SSN,String FileName) throws Exception{


			Excel TestData = new Excel("E:/AA/TestData/"+FileName);	
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
					//Thread.sleep(3000);	    

					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Daily Processing");
					//Thread.sleep(1000);
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
						
					Thread.sleep(3000);
					// driver.findElement(By.name("requestBean.comments")).click();
					driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
					// requestBean.comments
										
					//Thread.sleep(4000);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.name("Submit2")).click();
					test.log(LogStatus.PASS,"Clicked on Balance Safe");
					//Thread.sleep(4000);					
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
					//Thread.sleep(4000);

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
			 
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  		
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
				//Thread.sleep(1000);
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				 test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				 driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				 test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				 //Click Login Button
				 driver.findElement(By.name("login")).click();
				 test.log(LogStatus.PASS, "Clicked on Submit button");
				 Thread.sleep(2000);
				 driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Store Setup");
					//Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
					 driver.findElement(By.linkText("Store Config")).click();
					 driver.findElement(By.linkText("Edit Store")).click();
					test.log(LogStatus.PASS, "Clicked on Store Config");							
					test.log(LogStatus.PASS, "Clicked on Edit Store");					
					driver.switchTo().frame("main");		
					  driver.findElement(By.name("locationBean.locNbr")).sendKeys(StoreID);
					  //Thread.sleep(1000);
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
					}
			}
	}
		
public void Safeassign(String SSN,String FileName) throws Exception{
		 	
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  					int lastrow=TestData.getLastRow("NewLoan");
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
						     //Thread.sleep(4000);				
							driver.switchTo().defaultContent();				
							driver.switchTo().frame("topFrame");
							driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
							test.log(LogStatus.PASS, "Clicked on Cash Management");
							//Thread.sleep(1000);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							//driver.findElement(By.cssSelector("li[id='911101']")).click();	
							driver.findElement(By.linkText("Safe")).click();
							test.log(LogStatus.PASS, "Clicked on Assign");	
							//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
							//driver.findElement(By.linkText("Drawer")).click();
							driver.findElement(By.linkText("Assign")).click();
							test.log(LogStatus.PASS, "Clicked on Assign");
				
												//login.Login(UserName, Password, StoreId, driver, AppURL, test);
							 //Thread.sleep(3000);
				
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
				
							driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys(Password);
							            test.log(LogStatus.PASS, "Enter Password:"+Password);
				
							driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
							                test.log(LogStatus.PASS, "Enter 500 Dollars ");  
				
				
							driver.findElement(By.name("safeassign")).click();
							   test.log(LogStatus.PASS, "Clicked as safeassign Button ");  
				
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
								
							}
							//Thread.sleep(3000);
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				
							 driver.switchTo().defaultContent();
							    driver.switchTo().frame("mainFrame");
							    driver.switchTo().frame("main");
							    
							    ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input
							    ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input
							   // if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).isDisplayed())
							    if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
							    {
									 test.log(LogStatus.PASS, "OK Button as Enabled ");  
							         driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
							         test.log(LogStatus.PASS,"Click Ok Button.");
							    	 test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
							    	
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

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName); 
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
			//Thread.sleep(4000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();
			//Thread.sleep(1000);
			test.log(LogStatus.PASS, "Clicked on Cash Management");
			//Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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


			//Thread.sleep(2000);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			//|| driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font")).getCssValue("color")=="red"

			if(this.Field(driver) != null )

			//if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).isDisplayed())

			{ 

			//Thread.sleep(1000);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

			//Thread.sleep(3000);

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

			//Thread.sleep(2000);

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

			//Thread.sleep(1000);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//driver.findElement(By.cssSelector("li[id='911101']")).click();

			driver.findElement(By.linkText("Safe")).click();

			test.log(LogStatus.PASS, "Clicked on Assign");

			//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();

			//driver.findElement(By.linkText("Drawer")).click();

			driver.findElement(By.linkText("Assign")).click();

			test.log(LogStatus.PASS, "Clicked on Assign");


			//login.Login(UserName, Password, StoreId, driver, AppURL, test);

			//Thread.sleep(3000);


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

			//Thread.sleep(3000);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


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

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			//driver.switchTo().frame("main");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
			//Thread.sleep(2000);
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
									 //html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input

			}
			else

			{

			test.log(LogStatus.PASS,"Drawer not Assigned successfully with over/short.");

			}

			}

		}

	}

}

public void CustomerEodS_Recoredtatus(String SSN,String FileName) throws Exception
		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
					Thread.sleep(3000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
						
						//driver.findElement(By.name("button")).click();
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					//String CheckStaus=null;
				//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();

					test.log(LogStatus.PASS,"CustomerEOD Recored is Not Displayed.");
				//	System.out.print(CheckStaus);	
					//driver.close();

				}
			}
		}
			
public void ActiveCustomerEodS_Recoredtatus(String SSN,String FileName) throws Exception{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
					Thread.sleep(3000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
						
						//driver.findElement(By.name("button")).click();
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					//String CheckStaus=null;
				//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();

					test.log(LogStatus.PASS,"CustomerEOD Recored  Displayed.");
				//	System.out.print(CheckStaus);	
					//driver.close();

				}
			}
		}


public void EditBorrower_Active(String SSN,String FileName) throws Exception
	  	{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
	  				//Thread.sleep(3000);
	  				String Monthlydate=null;
	  				String Monthlydate1=null;
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
	  				//String NextPayday =null;
	  				driver.findElement(By.name("customerBean.activeFlgDisp")).sendKeys("Active");
	  				test.log(LogStatus.PASS,"Acount status is chend to Inactive");
	  				
	  				driver.findElement(By.name("Save")).click();							
					test.log(LogStatus.PASS, "Clicked on Save&Exit");
					Thread.sleep(1000);
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
public void EPP(String SSN,String FileName) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  		int lastrow=TestData.getLastRow("NewLoan");
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
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);	
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				this.Login(UserName,Password,StoreId);	
				//driver.switchTo().defaultContent();		
				//Thread.sleep(3000);				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				    driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				    if(ProductID.equals("TLP"))
					 {
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
					 //Thread.sleep(3000);
					 driver.findElement(By.name("transactionList")).sendKeys("RPP");
					 driver.findElement(By.id("go_Button")).click();
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
						 driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).click();
						 test.log(LogStatus.PASS, "Click on Next");
						 if(ProductID.equals("PDL"))
						 {
						
						// driver.findElement(By.name("chkNbr")).sendKeys(ESign_CheckNbr);
						// test.log(LogStatus.PASS, "Chek number is entered as "+ESign_CheckNbr);
						// driver.findElement(By.name("chkgAcctNbr")).sendKeys(last4cheknum);
						// test.log(LogStatus.PASS, "Checking Account Nbr(Last 4 digits Only) is entered as "+last4cheknum);
						
						 driver.findElement(By.name("password")).sendKeys(Password);
						 driver.findElement(By.name("submitBtn")).click();
						 Thread.sleep(2000);
						 driver.findElement(By.xpath("//*[@id='OKBut']")).click();
						 test.log(LogStatus.PASS, "Clicked on YES button");
						 
						 
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
						 if(driver.findElement(By.name("checkyes")).isDisplayed())
							{
								test.log(LogStatus.PASS, "EPP Loan is Completed Successfully ");
								driver.findElement(By.name("checkyes")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Epp Loan is not Completed Successfully ");
							}
						 }
						 
						 if(ProductID.equals("TLP"))
						 {
							 driver.findElement(By.xpath("//*[@id='PD3']")).click();
							 test.log(LogStatus.PASS, " Pay Off the balance is selected ");
						 driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						 test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
						 //Thread.sleep(3000);
						 String Pmt= driver.findElement(By.name("payOff")).getAttribute("value");						 
						 System.out.println(Pmt);						 
						 driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Pmt);						 
						 test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
						 
					    	driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					    	driver.findElement(By.name("finish")).click();											 						 
							test.log(LogStatus.PASS, "Password is selected as "+Password);																					
							test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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
							
							 if(driver.findElement(By.name("Ok")).isDisplayed())
								{
								 test.log(LogStatus.PASS, "EPP Loan is Completed Successfully ");
									driver.findElement(By.name("Ok")).click();
								}
							 else
								{
									test.log(LogStatus.FAIL, "EPP Loan is not Completed Successfully ");
								}
							 }												
					}
			}
	}
		
public void LoanPaymentstaus(String SSN,String FileName) throws Exception	{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
					//Thread.sleep(3000);
					Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
					if(ProductID.equals("PDL"))
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
					String CheckStatus=null;

					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

					test.log(LogStatus.PASS, "Capture CheckStatus::"+CheckStatus);
					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
					System.out.print(CheckStatus);	
					//driver.close();
				}

					
				}

			}
		public void StatementGeneration_EODProcessing(String SSN,String FileName) throws Exception{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName); 
			///Excel TestData = new Excel("E:/AA/TestData/"+FileName);	
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
					//Thread.sleep(3000);	    
					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Daily Processing");
					//Thread.sleep(1000);
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
					WebElement webElement = driver.findElement(By.name("requestBean.noOf100Dollars"));
					webElement.sendKeys(Keys.TAB);
				//	webElement.sendKeys(Keys.ENTER);
					//Thread.sleep(2000);
					WebElement element = driver.findElement(By.name("Submit2"));
					Actions actions = new Actions(driver);
					actions.moveToElement(element);
					actions.perform();
					driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
					// requestBean.comments
					//Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.name("Submit2")).click();
					test.log(LogStatus.PASS,"Clicked on Balance Safe");
					Thread.sleep(2000);
					
					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						Thread.sleep(1000);
						driver.findElement(By.name("Submit2")).click();
						test.log(LogStatus.PASS,"Clicked on Balance Safe");
						//driver.findElement(By.name("Next")).click();
						//test.log(LogStatus.PASS,"Clicked on Next");

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
						//Thread.sleep(4000);
						driver.findElement(By.xpath("//input[@type='button'][@value='Next']")).click();
						//if alert present, accept and move on.														

						try { 
							Alert alert1 = driver.switchTo().alert();
							alert1.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.

						}
					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.

					}
					
					//Thread.sleep(1000);
					for(String winHandle : driver.getWindowHandles()){
						driver.switchTo().window(winHandle);
					}				    
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//Thread.sleep(3000);

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
					//Thread.sleep(4000);

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
				
		@Test (priority=1)
		
		 public void RegistrationTest() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "AA_BorrowerRegistration_NewLoan_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
			        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			        String StateID = TestData.getCellData(sheetName,"StateID",row);
			        String SSN = TestData.getCellData(sheetName,"SSN",row);	
			        String Header = StateID+ "_" + ProductID;
			        //System.out.println(SSN);
			        test = reports.startTest("BorrowerRegistration_NewLoan_"+Header, "Login_Home screen_Borrower_Registration_New loan");
			        appUrl = AppURL;
			        this.Login(UserName,Password,StoreId);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			        this.NewLoan(SSN, FileName);
			   	}
			}
			//this.Login("CSR353","1234","353");
		
				}

	@Test (priority=2)

		public void VoidloanTest() throws Exception {
			
			// Start test. Mention test script name
			String FileName= "AA_Newloan_MultipullDisb_Types.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
			        test = reports.startTest("AEA_Newloan_MultipullDisb_Type"+Header, "Newloan with multiple disbursements like check& cash_check whether loan is processed smoothly .");
			        appUrl = AppURL;
			        this.Login(UserName,Password,StoreId);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			        this.NewLoan_MultipulDisbTypes(SSN, FileName);
			  	}
		}
	}

	@Test (priority=3)
		
		 public void BuyBack_Change_Void() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "AA_PartialPayment_BuyBackChange_Void_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
			        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			        String StateID = TestData.getCellData(sheetName,"StateID",row);
			        String SSN = TestData.getCellData(sheetName,"SSN",row);	
			        String Header = StateID+ "_" + ProductID;
			        test = reports.startTest("AA_PartialPayment_BuyBackChange_Void_"+Header, "New Loan_Age store to some days before due date_Partial Payment_Age store up to some days before due date_BuyBack transaction with change_Void BuyBack Transaction_check whether change amount is displayed or not");
			        appUrl = AppURL;
			        this.Login(UserName,Password,StoreId);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			        this.NewLoan(SSN, FileName);
			        this.AgeStore(SSN, FileName,-5);	
			       // this.LoanDate_AgeStore(SSN, FileName,+5);
			       // this.AgeStore(SSN, FileName,-2);
			        this.LoanPartialPayment(SSN, FileName);
			        this.AgeStore(SSN, FileName,-2);							     
			        this.BuybackChange(SSN, FileName);
			        this.Void(SSN, FileName);
				}
			}
		}
	@Test (priority=4)
		
		 public void PreNoteDeposit_Clear_CustomerInactive_Deposit() throws Exception {
		
			// Start test. Mention test script name
			                
			String FileName= "AA_PreNoteDeposit_Clear_CustomerInactive_Deposit_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
			        test = reports.startTest("PreNoteDeposit_Clear_CustomerInactive_Deposit_"+Header, "Loan_Pre Note DEP_PreNote Clr_Change BNK status to Inactive before due date_Custmr should not come for DEPosit on due date");
			        appUrl = AppURL;
			        this.Login(UserName,Password,StoreId);
			       	BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			        this.NewLoan(SSN, FileName);
			        this.AgeStore(SSN, FileName, -7);
			        this.PrenoteDeposit_6DaysBeforeDuedate(SSN, FileName, -7);
			        this.AgeStore(SSN, FileName, -1);
			        this.PrenoteClear_BeforeDuedate(SSN, FileName, -1);
			        this.EditBorrower_Inactive(SSN, FileName);
			        this.AgeStore(SSN, FileName, 0);
      		        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName); 
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.CustomerEodS_Recoredtatus(SSN, FileName);
			}
		}
}
		
	@Test (priority=5)
		
		 public void PreNoteDeposit_Clear_CustomerActive_Deposit() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "AA_PreNoteDeposit_Clear_CustomerActive_Deposit_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
			        test = reports.startTest("PreNoteDeposit_Clear_CustomerActive_Deposit_"+Header, "Loan_Pre Note DEP_PreNote Clr_Change BNK status to Inactive before due date_again Change BNK status to ACT on due dt_Custmr should come for DEPosit on due date");
			       appUrl = AppURL;
			        
			        this.Login(UserName,Password,StoreId);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			        this.NewLoan(SSN, FileName);
			        this.AgeStore(SSN, FileName, -7);
			        this.PrenoteDeposit_6DaysBeforeDuedate(SSN, FileName, -7);
			        this.AgeStore(SSN, FileName, -1);
			        this.PrenoteClear_BeforeDuedate(SSN, FileName, -1);
			        this.EditBorrower_Inactive(SSN, FileName);
			        this.AgeStore(SSN, FileName, 0);
			        this.EditBorrower_Active(SSN, FileName);
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName); 
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.ActiveCustomerEodS_Recoredtatus(SSN, FileName);
			              
			}
		}
		
}
	@Test (priority=6)	
		
		 	public void Newloan_missEPP_EODprocess() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "AA_Newloan_missEPP_EODprocess_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_SmokeTest/"+FileName);  			
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
			       test = reports.startTest("Newloan_missEPP_EODprocess_"+Header, "Loan-EPP- Miss the EPP Payment_Age the store to 14 days from EPP due date_Perform EOD_Customer record should display in EOD process and should comes out forcefully from EPP.");
			        appUrl = AppURL;
			        
			        //this.CustomerEodS_Recoredtatus(SSN, FileName);
			        //this.EPP(SSN, FileName);
			  
			        this.Login(UserName,Password,StoreId);
			         BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			         Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			        //this.RegistrationPage(SSN, FileName);
			        this.NewLoan(SSN, FileName);
			        this.AgeStore(SSN, FileName, -5);
			        this.EPP(SSN, FileName); 			        
			        this.AgeStore(SSN, FileName, 0);		       
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName); 
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
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
		                        String screenshotPath = PDL_SmokeTest.getScreenshot(driver, result.getName());
		 //To add it in the extent report 
	      test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		 }else if(result.getStatus() == ITestResult.SKIP){
			 test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }else if(result.getStatus() == ITestResult.SUCCESS){
			 test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}
		// reports.endTest(test);
	     reports.flush();
	        
		 }
		 public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
			 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			 //TakesScreenshot ts = (TakesScreenshot) driver;
			 //File source = ts.getScreenshotAs(OutputType.FILE);	
			 
			 File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);		 
			                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
			 String destination = System.getProperty("user.dir") + "/ExecutionReports/FailedTestsScreenshots/"+screenshotName+dateName+".png";
			 File finalDestination = new File(destination);
			 FileUtils.copyFile(source, finalDestination);
			 return destination;
			 }
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



