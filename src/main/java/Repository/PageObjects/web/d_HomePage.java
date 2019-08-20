package Repository.PageObjects.web;


public class d_HomePage
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

	
	public static String [] Button_Shop =OC("css","a[aria-label='Shop']","Shop Button");
	public static String [] Button_ShopAllDevices =OC("xpath","(//a[@sitecat-cta='Shop all phones'])[2]","Shop All Devices");

	public static String [] Menu_Shop =OC("xpath","//*[text()='Shop']","Shop Menu");
	public static String [] Menu_Smartphones =OC("link","Smartphones","Smartphones Menu");

	

	
}
