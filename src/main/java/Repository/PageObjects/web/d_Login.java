package Repository.PageObjects.web;


public class d_Login
{

	// **OBJECT CONVERTER **/
	public static  String [] OC(String ObjectType,String LocatorValue,String nameofObject)
	{
	String [] object=new String[3];
	object[0]=ObjectType;
	object[1]=LocatorValue;
	object[2]=nameofObject;
	return object;
	}
	// **OBJECT CONVERTER **/

	
	//VPN
	public static String [] TXT_VPN_Login =OC("css","input[name='login']","VPN Username Text box");
	public static String [] TXT_VPN_PWD =OC("css","input[name='passwd']","VPN Password Text box");
	public static String [] BUTTON_VPN_Logon =OC("id","Log_On","Login Button");
	
    // login 
	
	public static String [] TopSigninButton =OC("xpath","//*[@id='gnavAccountMenu' or id='accountMenuOpenBtn']","Sign in Menu");
	public static String [] Menu_Myaccount =OC("xpath","//a[text()='My Account']","My Account Menu");
	public static String [] TXT_Login =OC("css","input[placeholder^='Mobile Number']","Text Login");
	public static String [] TXT_Password =OC("css","input[placeholder='Password']","Text Password");
	public static String [] Button_Submit =OC("id","login-submit","Sign In Button");

	public static String [] Txt_SecretQn =OC("css","input[aria-label='Secret Question']","Secret QN txtbox");
	public static String [] Button_Secret_Continue_Btn =OC("xpath","//*[@id='continueButton' or id='otherButton']","Continue Button Secret box");
	
	

	
}
