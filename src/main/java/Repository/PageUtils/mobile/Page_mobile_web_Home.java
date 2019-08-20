package Repository.PageUtils.mobile;

import Repository.PageObjects.mobile.m_HomePage;
import config.Global;

public class Page_mobile_web_Home extends Global
{

	
	/**
	 * Author : Mahalingam
	 * Creation Date : 11-03-2019
	 * Description : Method to navigate to Smartphone Page
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 **/

	public void NavigatetoSmartPhonePage() throws Exception
	{
		Thread.sleep(3500);
		if(oGbl.actionKey.isElementDisplayed(m_HomePage.Button_Shop))
		{
			oGbl.actionKey.mobile_tap(m_HomePage.Button_Shop);
		}
		else
		{
			
		//	oGbl.actionKey.mouseHover(m_HomePage.Button_ShopAllDevices);;
		//	oGbl.actionKey.mobile_tap(m_HomePage.Button_ShopAllDevices);
			navigatetoSmartPhonefromHamburgerMenu();
		}
	}
	
	
	/**
	 * Author : Mahalingam
	 * Creation Date : 05-04-2019
	 * Description : Method to navigate to Smartphone Page from Hamburger Menu
	 * Date of Modification : 
	 * Modified By
	 * Reason for Modification: 
	 * @throws Exception 
	 * @throws Exception 
	 **/
	
	public void navigatetoSmartPhonefromHamburgerMenu() throws Exception
	{
		Thread.sleep(2500);
		oGbl.actionKey.mobile_tap(m_HomePage.Menu_Shop);
		Thread.sleep(2500);
		oGbl.actionKey.mobile_tap(m_HomePage.Menu_Smartphones);
		
		oGbl.report.log("PASS", "Successfully navigated to grid wall from Hamburger Menu", true);
	}
}
