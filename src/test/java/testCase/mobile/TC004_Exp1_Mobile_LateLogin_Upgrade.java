package testCase.mobile;

import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.Actionkey;
import config.Environment;
import config.ExcelUtils;
import config.Global;
import config.Page;
import config.TestBase;
public class TC004_Exp1_Mobile_LateLogin_Upgrade extends TestBase
{

	@Parameters({"experiment"})

		@Test(priority=1,description ="Upgrade a Device as Signed in User",invocationCount=1)
		public void upgradeMobileDevice(String experiment)
		{
			try
			{
				ExtentReport.testcaseName(""+this.getClass().getSimpleName());
				Page.cmnUtils.readTestCase("TestData", "TC_Mobile_UpgradeDevice");
				Page.mlogin.navigatetoVerizonURL();
				Page.mlogin.loginintonetScalarGateway(experiment);
			}
			catch(Exception e)
			{
				if(Actionkey.getExceptionMessage!="")
				{
					ExtentReport.log("FAIL", "Test Case ["+this.getClass().getSimpleName()+ " ] Failed due to Selenium Exception :"+ Actionkey.getExceptionMessage, true);
				}
				else
				{
					ExtentReport.log("FAIL", "Test Case ["+this.getClass().getSimpleName()+ " ] Failed due to Java Exception :"+ e.getLocalizedMessage(), true);
				}
			}
		}
		

	
	
}

	

