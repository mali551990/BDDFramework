package testCase.web;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.Actionkey;
import config.ExcelUtils;
import config.Global;
import config.Page;
import config.TestBase;
import config.constants;
public class TC001_Exp2_web_EarlyLogin_Upgrade_BAU extends TestBase 
{
	@Parameters({"experiment","environment"})

		@Test(priority=1,description ="Upgrade a Device as Signed in User",invocationCount=1)
		public void EUP_EarlyLoginFlow_BAU(String experiment,String environment)
		{
			try
			{
				ExtentReport.testcaseName(""+this.getClass().getSimpleName());
				Page.cmnUtils.readTestCase("TestData", "TC_web_UpgradeDevice");
				Page.dlogin.navigatetoVerizonURL_BasedonEnvironment(environment);
				Page.dlogin.loginintonetScalarGateway(experiment);
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

	

