package Tests.PDL;


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
import org.openqa.selenium.support.ui.WebDriverWait;
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
import Utilities.ExtentReports.Excel;


public class Loan_Deposit_FullPrePayment_Clear_Refund {

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
					
			String filename="Loan_Deposit_FullPrePayment_Clear_Refund"+timestamp+".html";
			//System.out.print(filename);
			reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/PDL/Loan_Deposit_FullPrePayment_Clear_Refund/"+filename, true);
			//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/PDL/ShortListedScenarios.html", true);
		}

		@BeforeTest
		public void setup() throws IOException {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
			
			driver = new InternetExplorerDriver();		
		}

		//@BeforeTest
		public void Login (String username,String password,String storenumber) {
			
			
			
			//Launch URL
			driver.get(appUrl);
			test.log(LogStatus.INFO, "CSR Application is launched with ::" +appUrl);
			driver.manage().window().maximize();
			String usenameId = "loginRequestBean.userId";
		    String passwordId = "loginRequestBean.password";
		    String StoreId = "loginRequestBean.locNbr";
		    String Login = "login";
		 
		   // String username= "CSR353";
		   // String password= "1234";
		   // String storenumber= "353";
		    
		  //Enter Username(Email)
	        //writeText(By.name(usenameId),username);
		    driver.findElement(By.name(usenameId)).clear();
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
			
			
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);     	
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
					/*this.Login(UserName,Password,StoreId);
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
				    driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");*/
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 //	Selection of Product based on the Name provided in Test Data
					
					 //if(driver.findElement(By.id("LoanButtonId")).isEnabled())
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
							////							*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("TNPDL all coll"))
						{								////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							driver.findElement(By.name("prodSel")).click();
							//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
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
						//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
					   /* WebElement htmltable=driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table"));
					    													//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table
					    
						List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
						
						int count=0;							
						 count=driver.findElements(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr")).size();	
						 									//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[1]
						 System.out.println("current row num "+count);	
						 System.out.println(" rows num "+ rows.size());
						for(int rnum=1;rnum<=count;rnum++)
						{
							System.out.println("current row num "+rnum);						
						List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
						
						System.out.println("columns Count "+columns.size());
							
						for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
						{					
							String product_name=columns.get(cnum).getText();						
							System.out.println(product_name);	
								
							if(product_name.equals(stateProduct))
							{
									
								if(ProductID.equals("PDL"))
								{					
									rnum=rnum+1;														
									driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[2]/input")).click();								
									
								}
							}
							if(stateProduct.equals("MO PDL"))
							{
									
								if(ProductID.equals("PDL"))
								{					
									rnum=rnum+1;														
									driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input")).click();														
								}
							}
							
								if(ProductID.equals("ILP")||ProductID.equals("TLP"))							
								{	
									
									System.out.println("IN ILP/TLP");
									String Pname=driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[2]")).getText();
									System.out.println("current row of table"+Pname);
									if(Pname.equals(stateProductType))
									{
										if(Term.equals("Term1"))
										{
										driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[5]/table/tbody/tr/td[2]/table[1]/tbody/tr[1]/td/b/input")).click();								
										}
										if(Term.equals("Term2"))
										{
											driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[5]/table/tbody/tr/td[2]/table[2]/tbody/tr[1]/td/b/input")).click();									
										}
									}																
									
								}																														
						}							 			
						}
						if(ProductID.equals("PDL"))
						{
							test.log(LogStatus.PASS, "Product selected as "+stateProduct);
						}
						if(ProductID.equals("ILP")||ProductID.equals("TLP"))
						{
							test.log(LogStatus.PASS, "Product selected as "+stateProductType+" Term Selected as "+Term);
						}*/
						driver.findElement(By.name("ShareScreenBtn")).click();
						test.log(LogStatus.PASS, "ShareScreen Button clicked");
						for( String winHandle1 : driver.getWindowHandles())

                       {

                           driver.switchTo().window(winHandle1);

                       }
						Thread.sleep(1000);
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
							test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
							driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
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
					/*		driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
							test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
							WebDriverWait wait = new WebDriverWait(driver, 1000);	
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
							driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
							test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);*/
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
						//	for( String winHandle1 : driver.getWindowHandles())
						//	{
						//	    driver.switchTo().window(winHandle1);
						//	}			
							// driver.switchTo().defaultContent();
							// driver.switchTo().frame("mainFrame");
							// driver.switchTo().frame("main");
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
							Thread.sleep(8000);
							//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
							/*for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");*/
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
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
							Thread.sleep(5000);
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
						else
						{
						test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
						}
				}
			}

		}
	
	public void AgeStore(String SSN,String FileName,int Days) throws Exception
	{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
			String DueDate=null;
			
			                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
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
			
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);
			
			String DDueDate[] =DueDate.split("/");
			//String date = DDueDate[1];
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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}




		}
	}
	}
	

public void DrawerDeassign(String SSN,String FileName) throws Exception{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);		
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

public void EODProcessing(String SSN,String FileName) throws Exception{



Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);

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

public void NACHADeposit_EODProcessing(String SSN,String FileName,int Days) throws Exception{



Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);

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

String SSN3 = SSN.substring(5,9);/*
String TxnType=TestData.getCellData(sheetName,"TxnType",row);
String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
String ProductID=TestData.getCellData(sheetName,"ProductID",row);
String UserName = TestData.getCellData(sheetName,"UserName",row);
String Password = TestData.getCellData(sheetName,"Password",row);*/
String StoreID = TestData.getCellData(sheetName,"StoreID",row);
String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

CSRLoginpage login = new CSRLoginpage();

login.Login(UserName, Password, StoreId, driver, AppURL, test);
System.out.println(AdminURL);
test.log(LogStatus.INFO, "Scheduler-Store Aging");

System.out.println(ProductID);	
//String AppURL = TestData.getCellData(sheetName,"AppURL",row);
appUrl = AppURL;

/*String SSN1 = SSN.substring(0, 3);
String SSN2 = SSN.substring(3,5);
String SSN3 = SSN.substring(5,9);*/
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
String DueDate=null;

                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
System.out.print(DueDate);	

CSRLoginpage login1 = new CSRLoginpage();
login1.Login(UserName, Password, StoreId, driver, AppURL, test);
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
this.Login(UserName,Password,StoreID);
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
driver.findElement(By.linkText("Daily Jobs")).click();
test.log(LogStatus.PASS, "Clicked on Daily Jobs");
Thread.sleep(5000);

String DDueDate[] =DueDate.split("/");
//String date = DDueDate[1];
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

/*	driver.findElement(By.name("storeCode")).click();
//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
driver.findElement(By.name("storeCode")).sendKeys(StoreID);
test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
Thread.sleep(5000);*/
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


public void Safeassign(String SSN,String FileName) throws Exception{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
			 //CSRLoginpage login = new CSRLoginpage();
			 //login.Login(UserName, Password, StoreId, driver, AppURL, test);
		     Thread.sleep(5000);
			//driver.switchTo().defaultContent();				
			//driver.switchTo().frame("topFrame");
			//driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
			//test.log(LogStatus.PASS, "Clicked on Cash Management");
			//Thread.sleep(1000);
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			//driver.findElement(By.cssSelector("li[id='911101']")).click();	
			//driver.findElement(By.linkText("Drawer")).click();
			//test.log(LogStatus.PASS, "Clicked on Drawer");	
			//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
			//driver.findElement(By.linkText("Drawer")).click();
			
			//driver.findElement(By.linkText("Assign")).click();
			//test.log(LogStatus.PASS, "Clicked on Assign");
			
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
				
			//driver.findElement(By.name("previous")).click();
			
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			
			
			//driver.findElement(By.name("yes")).click();
			
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			
			//driver.close();
		CSRLoginpage login = new CSRLoginpage();
		login.Login(UserName, Password, StoreId, driver, AppURL, test);
			 //Thread.sleep(5000);
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
			
										    
}

}
}

/*public void Drawerassign(String SSN,String FileName) throws Exception{



Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/TLP/"+FileName);

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
//****
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

//***


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

}
*/
/*public void Drawerassign(String SSN,String FileName) throws Exception{


Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
	 //CSRLoginpage login = new CSRLoginpage();
	 //login.Login(UserName, Password, StoreId, driver, AppURL, test);
    Thread.sleep(5000);
	//driver.switchTo().defaultContent();				
	//driver.switchTo().frame("topFrame");
	//driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
	//test.log(LogStatus.PASS, "Clicked on Cash Management");
	//Thread.sleep(1000);
	//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	//driver.switchTo().defaultContent();
	//driver.switchTo().frame("mainFrame");
	//driver.switchTo().frame("main");
	//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	//driver.findElement(By.cssSelector("li[id='911101']")).click();	
	//driver.findElement(By.linkText("Drawer")).click();
	//test.log(LogStatus.PASS, "Clicked on Drawer");	
	//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
	//driver.findElement(By.linkText("Drawer")).click();
	
	//driver.findElement(By.linkText("Assign")).click();
	//test.log(LogStatus.PASS, "Clicked on Assign");
	
	//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	//driver.switchTo().defaultContent();
	//driver.switchTo().frame("mainFrame");
	//driver.switchTo().frame("main");
		
	//driver.findElement(By.name("previous")).click();
	
	//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	//driver.switchTo().defaultContent();
	//driver.switchTo().frame("mainFrame");
	//driver.switchTo().frame("main");
	
	
	//driver.findElement(By.name("yes")).click();
	
	//driver.switchTo().defaultContent();
	//driver.switchTo().frame("mainFrame");
	//driver.switchTo().frame("main");
	
	//driver.close();
CSRLoginpage login = new CSRLoginpage();
login.Login(UserName, Password, StoreId, driver, AppURL, test);
	 //Thread.sleep(5000);
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
	
								    
}

}
}
*/
public void StoreInfo(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
//action.perform();
//driver.findElement(By.cssSelector("li[id='101020']")).click();
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

public void CustomerEodS_Recoredtatus(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
	/*	if(ProductID.equals("LOC"))
		{*/
			//*[@id="go_Button"]
			driver.findElement(By.xpath("//*[@id='go_Button']")).click();
			//driver.findElement(By.name("button")).click(); 
		//}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String CheckStaus=null;
	//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
		CheckStaus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[5]")).getText();
		//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
		if((CheckStaus).contains("Deposit"))
		{
			test.log(LogStatus.PASS,"CustomerEOD Record is  Displayed as::"+CheckStaus);
		}
		else
		{
			test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
		}

	}
}
}

public void Customer_CheckStatus(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
	/*	if(ProductID.equals("LOC"))
		{*/
			//*[@id="go_Button"]
			driver.findElement(By.xpath("//*[@id='go_Button']")).click();
			//driver.findElement(By.name("button")).click(); 
		//}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String CheckStaus=null;
	//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
		CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
		//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
		/*if((CheckStaus).contains("Deposit"))
		{*/
			test.log(LogStatus.PASS,"CheckStatus is  Displayed as::"+CheckStaus);
	/*	}
		else
		{
			test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
		}*/

	}
}
}

public void PreACH_Deposit(String SSN,String FileName,int Days) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
	String DueDate=null;
	
	                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
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
	driver.findElement(By.linkText("Payday Loan")).click();
	test.log(LogStatus.PASS, "Clicked on PaydayLoan");
	Thread.sleep(5000);
	driver.findElement(By.linkText("Process Pre ACH Deposit")).click();
	test.log(LogStatus.PASS, "Clicked on Process Pre ACH Deposit");
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
	driver.findElement(By.linkText("Daily Jobs")).click();
	test.log(LogStatus.PASS, "Clicked on Daily Jobs");
	Thread.sleep(5000);
	
	String DDueDate[] =DueDate.split("/");
	//String date = DDueDate[1];
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

/*	driver.findElement(By.name("storeCode")).click();
	//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
	driver.findElement(By.name("storeCode")).sendKeys(StoreID);
	test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
	Thread.sleep(5000);*/
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
		test.log(LogStatus.PASS, "Process Pre ACH Deposite is updated successfully.");
	    driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
	}
	else
	{
		test.log(LogStatus.FAIL, "Process Pre ACH Deposite is not updated successfully.");
	}




}
}
}

//Green Bank NACHA File

public void NACHA(String SSN,String FileName,int Days) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
	String DueDate=null;
	
	                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
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
	driver.findElement(By.linkText("Daily Jobs")).click();
	test.log(LogStatus.PASS, "Clicked on Daily Jobs");
	Thread.sleep(5000);
	
	String DDueDate[] =DueDate.split("/");
	//String date = DDueDate[1];
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

/*	driver.findElement(By.name("storeCode")).click();
	//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
	driver.findElement(By.name("storeCode")).sendKeys(StoreID);
	test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
	Thread.sleep(5000);*/
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

public void ACHDeposit(String SSN,String FileName,int Days) throws Exception

{


Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName); 

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
String DueDate=null;

                                  //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
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
driver.findElement(By.linkText("Daily Jobs")).click();
test.log(LogStatus.PASS, "Clicked on Daily Jobs");
Thread.sleep(5000);

String DDueDate[] =DueDate.split("/");
//String date = DDueDate[1];
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

/*	driver.findElement(By.name("storeCode")).click();
//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
driver.findElement(By.name("storeCode")).sendKeys(StoreID);
test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
Thread.sleep(5000);*/
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

test.log(LogStatus.PASS, "Green Bank updated successfully");

}

else

{

test.log(LogStatus.FAIL, "Green Bank Not updated successfully.");

} 


// driver.switchTo().defaultContent();

//driver.switchTo().frame("mainFrame");

// driver.switchTo().frame("main");


/*String DueDate0[] =DueDate.split("/");


String DueDate1 = DueDate0[0];

String DueDate2 = DueDate0[1];

String DueDate3 = DueDate0[2];


driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

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

// WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));

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



//PreACHDeposit 


driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click(); 

test.log(LogStatus.PASS, "Clicked on Transactions"); 


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

driver.findElement(By.linkText("LOC Pre ACH Deposit")).click();

test.log(LogStatus.PASS, "Clicked on Loc pre ACH Deposit");





driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

// Date DDueDate = df.parse(DueDate);

//Calendar cal = Calendar.getInstance();

// cal.setTime(DDueDate);

// cal.add(Calendar.DATE,0);

//Date DDueDateminus1= cal.getTime();



//String DueDateminus1 =df.format(DDueDateminus1);

//String DueDate0[] =DueDateminus1.split("/");

//String DueDate0[] =DueDate.split("/");


// String DueDate1 = DueDate0[0];

// String DueDate2 = DueDate0[1];

// String DueDate3 = DueDate0[2];


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

driver.findElement(By.name("btnPreview")).click();

test.log(LogStatus.PASS, "Clicked on submit button");

test.log(LogStatus.PASS, "Completed ACH Deposit");
*/


}

}

}
/*
		ACH Pre-Payment



*/

public void ACH_PrePayment(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


		/*if(ProductID.equals("PDL"))
		{*/
			
			Thread.sleep(3000);
								//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
		/*	                          //   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
		}*/
		//  driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("ACH Pre-Payment");
	/*	if(ProductID.equals("LOC"))
		{*/
			//*[@id="go_Button"]
			driver.findElement(By.xpath("//*[@id='go_Button']")).click();
			test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
			//driver.findElement(By.name("button")).click(); 
		//}
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
		
		driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
		test.log(LogStatus.PASS, "TenderType Select as ::Cash");
		
		String PmtAmt = driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");
		
		driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PmtAmt);
		test.log(LogStatus.PASS, "TenderAmount Entered is::"+PmtAmt);
		driver.findElement(By.name("requestBean.password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Passwor Entered as::"+Password);
		driver.findElement(By.name("Submit22")).click();
		test.log(LogStatus.PASS, "Clicked on Finish ACH PrePayment Button");
		
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
		
		if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).isDisplayed())
		{
		driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).click();
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

public void ACH_Clear(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


		if(ProductID.equals("PDL"))
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
		//name="transactionDataBean.tenderTypeFirst"
	/*	driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
		test.log(LogStatus.PASS, "TenderType Select as ::Cash");*/
//name="CmdReturnPosting"	
		Thread.sleep(3000);
		//name="requestBean.chkName
		//html/body/table/tbody/tr/td/table/tbody/tr/td/form/div/a[1]
		//driver.findElement(By.xpath("//input[@name='requestBean.chkName' and @type='checkbox']")).click();
		driver.findElement(By.name("requestBean.chkName")).click();
		//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/div/a[1]")).click();
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

public void ACH_ReFund(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


		if(ProductID.equals("PDL"))
		{
			
			//driver.findElement(By.name("button")).click();
			///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
		driver.findElement(By.name("transactionList")).sendKeys("CustomerRefund");
	/*	if(ProductID.equals("LOC"))
		{*/
			//*[@id="go_Button"]
			driver.findElement(By.xpath("//*[@id='go_Button']")).click();
			test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
			//driver.findElement(By.name("button")).click(); 
		//}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}	
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
		//name="transactionDataBean.tenderTypeFirst"
		//name="transactionDataBean.tenderTypeFirst"
		driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
		test.log(LogStatus.PASS, "TenderType Select as ::Cash");
//name="CmdReturnPosting"	
		Thread.sleep(3000);

		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Clicked on Finish ACH ReFund Button");
		
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
		
		if(driver.findElement(By.name("checkyes")).isDisplayed())
		{
			driver.findElement(By.name("checkyes")).click();
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


public void ACH_ReFund_History(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


		if(ProductID.equals("PDL"))
		{
			
			//driver.findElement(By.name("button")).click();
			///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
	/*	if(ProductID.equals("LOC"))
		{*/
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

		//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

		String RefundRecord=null;
	
		RefundRecord = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[6]/td[5]")).getText();
		
		if((RefundRecord).contains("Refund"))
		{
			test.log(LogStatus.PASS,"Refund Record is  Displayed " );
		}
		else
		{
			test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
		}
         String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
         
         test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);
         
        String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
         
         test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
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

		/*



ACH Clear
name="requestBean.chkName" checkbox
name="CmdReturnPosting"    button  Finish ACH Clear

/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]   Confirm Yes button




CustomerRefund


name="transactionDataBean.tenderTypeFirst"   Cash    DisbType DropDown

name="finish"   Finish Refund Button
name="checkyes"   Confirm Yes Button


History

//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]    LoanStatus
//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]    CheckStatus

/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[6]/td[5]     Refund Confirmation
		*/
		@Test (priority=0)
		
		 public void RegistrationTest() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "Loan_Deposit_FullPrePayment_Clear_Refund_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);   
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
			       test = reports.startTest("Loan_Deposit_FullPrePayment_Clear_Refund"+Header, "Loan_Deposit_Pre Payment full _Clear_Refund	");
			        appUrl = AppURL; 
			        
			 
			        
			       this.Login(UserName,Password,StoreId);
			       BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			       Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
			       this.NewLoan(SSN, FileName);
			       this.AgeStore(SSN, FileName, 0);
			     
			        this.NACHADeposit_EODProcessing(SSN, FileName, 0);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.ACH_PrePayment(SSN, FileName);
			      /*  this.ACH_Clear(SSN, FileName);
			        this.ACH_ReFund(SSN, FileName);
			        this.ACH_ReFund_History(SSN, FileName);*/
			}
			}
			//this.Login("CSR353","1234","353");
		
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
