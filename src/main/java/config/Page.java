package config;


import Repository.PageUtils.mobile.Page_mobile_web_CommonUtils;
import Repository.PageUtils.mobile.Page_mobile_web_Home;
import Repository.PageUtils.mobile.Page_mobile_web_Login;
import Repository.PageUtils.web.*;


public class Page 
{
 
/**************define object ***************************/

	
// mobile	
public static Page_CommonUtils cmnUtils;
public static Page_mobile_web_Login mlogin;
public static Page_mobile_web_CommonUtils mCommonUtils;

// web

public static Page_web_Login dlogin;
/**************define object ***************************/

 
 
 
/**************Initialize page object ***************************/
public Page()
{

// mobile web objects
mlogin = new Page_mobile_web_Login();
mCommonUtils = new Page_mobile_web_CommonUtils();
// web objects

//mobile web objects
dlogin = new Page_web_Login();
cmnUtils = new Page_CommonUtils();
/**************Initialize page object ***************************/
}
 
 
 
}