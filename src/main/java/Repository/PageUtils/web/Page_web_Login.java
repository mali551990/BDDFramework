package Repository.PageUtils.web;

import java.io.FileNotFoundException;
import java.io.IOException;

import Repository.PageObjects.mobile.m_Login;
import Repository.PageObjects.web.d_Login;
import config.Environment;
import config.Global;
import config.Setup;
import config.constants;

public class Page_web_Login extends Global {

	
	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to navigate to VZ URL
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/

	public void navigatetoVerizonURL()
	{
		if(Environment.TestEnvironment.equals("TEST"))
		{
			oGbl.actionKey.navigateToURL("https://vzwqa8.verizonwireless.com");
		}
		else if(Environment.TestEnvironment.equals("PROD"))
		{
			oGbl.actionKey.navigateToURL("https://www.verizonwireless.com");			
		}
	}
	
	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to navigate to VZ URL based on Environment Chosen from Test Suite
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/

	public void navigatetoVerizonURL_BasedonEnvironment(String Environment)
	{
		if(Environment.equals("QA8"))
		{
			oGbl.actionKey.navigateToURL(constants.qA8_URL);
		}
		else if(Environment.equals("QA2"))
		{
			oGbl.actionKey.navigateToURL(constants.qA2_URL);			
		}
		else if(Environment.equals("QA4"))
		{
			oGbl.actionKey.navigateToURL(constants.qA4_URL);			
		}
		Setup.appiumdriver.manage().window().maximize();
	}

	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to login into netScalar GW
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/

	public String loginintonetScalarGateway(String experiment) throws Exception
	{
		oGbl.actionKey.waitforObjectToLoad(d_Login.TXT_VPN_Login, 60);
		oGbl.actionKey.mobile_type(d_Login.TXT_VPN_Login, constants.vpn_Uname);
		oGbl.actionKey.mobile_type(d_Login.TXT_VPN_PWD, constants.vpn_Pwd);
		oGbl.actionKey.clickbyJS(d_Login.BUTTON_VPN_Logon);
		oGbl.actionKey.sleep(5000);
		String traffic = oGbl.actionKey.setFlag(experiment);
		System.out.println("Journey on launch of VZ is " + traffic);
		return traffic;
	}
	
	
	
	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to login into My Account
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/

	public void navigatetoMyAccount()
	{
		oGbl.actionKey.waitforObjectToLoad(d_Login.TopSigninButton, 60);
		oGbl.actionKey.mobile_tap(d_Login.TopSigninButton);
		oGbl.actionKey.sleep(2500);
		if(oGbl.actionKey.isElementDisplayed(d_Login.Menu_Myaccount))
		{
			oGbl.actionKey.mobile_tap(d_Login.Menu_Myaccount);
		}
		oGbl.actionKey.sleep(3000);
		if(!oGbl.actionKey.isElementDisplayed(m_Login.TXT_Login))
		{
			oGbl.actionKey.navigateBack();
			oGbl.actionKey.mobile_tap(d_Login.TopSigninButton);
			oGbl.actionKey.sleep(2500);
			//oGbl.actionKey.mobile_tap(d_Login.Menu_Myaccount);
		}
	}
	
	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to login into VZ using args uname,pwd
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/

	public void logintoVerizon(String uname,String pwd)
	{
		oGbl.actionKey.sleep(5000);
		boolean isTxtLogindisplayed=oGbl.actionKey.mobile_isPageDisplayed(d_Login.TXT_Login,10);
		if(!isTxtLogindisplayed)
		{
			oGbl.actionKey.switchToFrame(0);
		}
		oGbl.actionKey.mobile_type(d_Login.TXT_Login, uname);
		oGbl.actionKey.mobile_type(d_Login.TXT_Password, pwd);
		oGbl.actionKey.mobile_tap(d_Login.Button_Submit);
		oGbl.actionKey.sleep(5000);
		if(oGbl.actionKey.isElementDisplayed(d_Login.Txt_SecretQn))
		{
			oGbl.actionKey.mobile_type(d_Login.Txt_SecretQn,constants.qA8_secretPwd);
			oGbl.actionKey.mobile_tap(d_Login.Button_Secret_Continue_Btn);
		}
		/*
		 * Setup.appiumdriver.navigate().back(); // work around for login issue to be
		 * disabled once login issue is fixed Setup.appiumdriver.navigate().back();//
		 * work around for login issue to be disabled once login issue is fixed
		 */		
		oGbl.report.log("PASS", "Successfully logged in to Verizon with MDN: " +uname, true);
	}
	
	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to navigate to PDP
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/
	
		public void launchPDPURL()
		{
			oGbl.actionKey.navigateToURL("https://vzwqa8.verizonwireless.com/od/tablets/samsung-galaxy-tab-s4/?sku=sku3100339&cmp=CSE-C-HQ-NON-R-AC-NONE-NONE-2C0PX0-PX-PLA-SMT837VZKA");
		}
		
		
		
		
}
