package Tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utilities.ExtentReports.Excel;

public class Driver {

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
		reports = new ExtentReports(System.getProperty("user.dir") + "/My/BorrowerRegistration_NewLoan/"+filename, true);
	}
	@Test (priority=0)
	
	public void Execute() throws Exception
	{
	
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/Driver.xls");  		
		int lastrow=TestData.getLastRow("DriverScripts");
		String sheetName="DriverScripts";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			System.out.println(RunFlag);
			System.out.println(row);
			System.out.println(lastrow);
		if(RunFlag.equals("Y"))
		{	
				String TestName = TestData.getCellData(sheetName,"TestName",row);
				 		
				if(TestName.equals("BorrowerRegistration_NewLoan"))
				{
					   test = reports.startTest("BorrowerRegistration_NewLoan_", "New Loan");			
					BorrowerRegistration_NewLoan BRN = new BorrowerRegistration_NewLoan();
					BRN.RegistrationTest();
					
				}
				if(TestName.equals("VoidLoan"))
				{					
					VoidLoan VL = new VoidLoan();
					VL.RegistrationTest();
				
				}
				
			}
		}
	}
	
	
	
}
