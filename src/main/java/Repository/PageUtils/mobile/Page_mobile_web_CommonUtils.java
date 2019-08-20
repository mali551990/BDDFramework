package Repository.PageUtils.mobile;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import config.Common;
import config.Global;
import config.Setup;
import config.constants;

public class Page_mobile_web_CommonUtils extends Global
{

	public void readTestCase(String sheetname,String testCaseName) throws Exception
	{
		oGbl.excelutils.ReadandProcessData(sheetname, testCaseName);
	}

	public void trafficWrite(int IncedoJourney,int ControlJourney) throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("trafficSummary.txt"), "utf-8"))) 
		{
			int total=0;
			oGbl.report.log("INFO", "Incedo Journey : [ "+IncedoJourney+" ]", false);
			oGbl.report.log("INFO", "BAU Journey : [ "+ControlJourney+" ]", false);
			total=IncedoJourney+ControlJourney;
			oGbl.report.log("INFO", "Overall Traffic: [ "+total+" ]", false);
			writer.write("Incedo Journey:	" +	IncedoJourney 	+ String.format("%n") + "BAU Journey:	" +	ControlJourney + 	String.format("%n") + "Total Traffic:	" +		total);
			oGbl.report.log("PASS", "Successfully returned the journey count in trafficSummary", true);
		}
		
	}
	
	public void writeCSVCountinReport(int IncedoJourney,int ControlJourney)
	{
		int total=0;
		oGbl.report.log("INFO", "Incedo Journey : [ "+IncedoJourney+" ]", false);
		System.out.println("Incedo Journey" +IncedoJourney);
		oGbl.report.log("INFO", "Control Journey : [ "+ControlJourney+" ] ", false);
		System.out.println("BAU Journey" +IncedoJourney);
		total=IncedoJourney+ControlJourney;
		oGbl.report.log("INFO", "Overall Traffic: [ "+total+" ]", false);
	}
	
	
	public void verifyTagvalue(String pageName,String journey)
	{
		String pageSource=Setup.appiumdriver.getPageSource();
		String tagFromPageSource=oGbl.cmn.getTagValuefromPageSource(pageSource,"incedoVersion", 5);
		
		if(tagFromPageSource!="")
		{
			if(Common.iskillSwitchToggleEnabled)
			{
				verifyTagwhenkillSwitchTagisON(journey, tagFromPageSource, pageName);
			}
			else
			{
				verifyTagwhenKillswitchisOFF(journey, tagFromPageSource, pageName);
			}
		}
		else
		{
			oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tagname 'incedoVersion' is NOT present or tagvalue is empty  for "+journey, true);
		}
	}
	
	public void verifyTagwhenkillSwitchTagisON(String journey,String tagFromPageSource,String pageName)
	{
		switch (journey)
		{
		case "IncedoJourney" :
			if(tagFromPageSource.contains(constants.incdedoTag))
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag  ["+constants.incdedoTag+"] is present in actual ["+tagFromPageSource+"] tag for "+journey +" when kilswitch is ON", true);
			}
			else
			{
				oGbl.report.log("PASS", "Page: "+pageName+" tag ["+constants.incdedoTag+"] is NOT present in actual  ["+tagFromPageSource+"] for "+journey+" when kill switch is ON", true);

			}
			break; 
		case "ControlJourney" :
			if(tagFromPageSource.contains(constants.controlTag))
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag  ["+constants.controlTag+"] is present in actual ["+tagFromPageSource+"] tag for "+journey +" when kilswitch is ON", true);
			}
			else
			{
				oGbl.report.log("PASS", "Page: "+pageName+"tag ["+constants.controlTag+"] is NOT present in actual  ["+tagFromPageSource+"] for "+journey+" when kill switch is ON", true);
			}
			break; 

		case "HoldoutJourney" :
			if(tagFromPageSource.contains(constants.holdOutTag))
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag  ["+constants.holdOutTag+"] is present in actual ["+tagFromPageSource+"] tag for "+journey +" when kilswitch is ON", true);
			}
			else
			{
				oGbl.report.log("PASS", "Page: "+pageName+" tag ["+constants.holdOutTag+"] is NOT present in actual  ["+tagFromPageSource+"] for "+journey+" when kill switch is ON", true);
			}
			break; 

		case "BAUJourney" :
			if(tagFromPageSource.contains(constants.bauTag))
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag  ["+constants.bauTag+"] is present in actual ["+tagFromPageSource+"] tag for "+journey +"when kilswitch is ON", true);
			}
			else
			{
				oGbl.report.log("PASS", "Page: "+pageName+" tag ["+constants.bauTag+"] is NOT present in actual  ["+tagFromPageSource+"] for "+journey+" when kill switch is ON", true);
			}
			break;

		default:
			oGbl.report.log("FAIL", "unknown Journey to verify, journey := "+journey, true);
			break;

		}

	}
	
	public void verifyTagwhenKillswitchisOFF(String journey,String tagFromPageSource,String pageName)
	{
		switch (journey)
		{
		case "IncedoJourney" :
			if(tagFromPageSource.contains(constants.incdedoTag))
			{
				oGbl.report.log("PASS", "Page: "+pageName+"   expected tag  ["+constants.incdedoTag+"] is present in actual tag ["+tagFromPageSource+"] for "+journey, true);
			}
			else
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag  ["+constants.incdedoTag+"] is NOT present in actual ["+tagFromPageSource+"] tag for "+journey, true);
			}
			break; 
		case "ControlJourney" :
			if(tagFromPageSource.contains(constants.controlTag))
			{
				oGbl.report.log("PASS", "Page: "+pageName+"expected ["+constants.controlTag+"] is present in actual  ["+tagFromPageSource+"] for "+journey, true);
			}
			else
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag ["+constants.controlTag+"] is NOT present in actual ["+tagFromPageSource+"] for "+journey, true);
			}
			break; 

		case "HoldoutJourney" :
			if(tagFromPageSource.contains(constants.holdOutTag))
			{
				oGbl.report.log("PASS", "Page "+pageName+" expected tag ["+constants.holdOutTag+"] is present in actual tag ["+tagFromPageSource+"] for "+journey, true);
			}
			else
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag ["+constants.holdOutTag+"] is NOT present in actual ["+tagFromPageSource+"] for "+journey, true);
			}
			break; 

		case "BAUJourney" :
			if(tagFromPageSource.contains(constants.bauTag))
			{
				oGbl.report.log("PASS", "Page "+pageName+" expected tag ["+constants.bauTag+"] is present in actual tag ["+tagFromPageSource+"] for "+journey, true);
			}
			else
			{
				oGbl.report.log("FAIL_CONTINUE", "Page: "+pageName+" tag ["+constants.bauTag+"] is NOT present in actual ["+tagFromPageSource+"] for "+journey, true);
			}
			break;

		default:
			oGbl.report.log("FAIL", "unknown Journey to verify, journey := "+journey, true);
			break;

		}

	}
	
	
}
