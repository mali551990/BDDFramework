package config;


import java.io.File;

public interface Environment 
{
String userDir=System.getProperty("user.dir");
Object separator=File.separator; 
 
 
public final String filePath=userDir+separator+"src"+separator+"main"+separator+"java"+separator+"Repository"+separator+"Drivers"+separator;
public final static String csvPath = System.getProperty("user.dir")+separator+"src"+separator+"main"+separator+"java"+separator+"ExternalDataProvider"+separator+"MockTraffic.csv";
public final static String reportPath = System.getProperty("user.dir")+separator+"Output";
public final static String outputArchivePath = System.getProperty("user.dir")+separator+"OutputArchive";

public final String GeckoDriver=filePath+"geckodriver.exe";
public final String Chrome_Windows_X86=filePath+"chromedriver32_Windows.exe";
public final String Chrome_Linux_X64=filePath+"chromedriver64_Linux";
public final String Chrome_mac=filePath+"chromedriver_mac";
public final String ChromeX64=filePath+"chromeDriver64.exe";
public final String PhantomJS=filePath+"phantomGhost.exe";
public final String IEDriver=filePath+"IEDriverServer.exe";


public final String driver="CHROME";
public final String TestEnvironment="TEST";

}

