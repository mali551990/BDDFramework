package Repository.PageUtils.web;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import config.Global;

public class Page_CommonUtils extends Global
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
	public void trafficWrite(int IncedoJourney,int BAUJourney,int HoldOutJourney, int ControlJourney) throws Exception
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("trafficSummary.txt"), "utf-8"))) 
		{
			int total=0;
			oGbl.report.log("INFO", "Incedo Journey : [ "+IncedoJourney+" ]", false);
			oGbl.report.log("INFO", "BAU Journey : [ "+BAUJourney+" ]", false);
			oGbl.report.log("INFO", "HoldOut Journey : [ "+HoldOutJourney+" ]", false);		
			oGbl.report.log("INFO", "Control Journey : [ "+ControlJourney+" ]", false);			
			total=IncedoJourney+BAUJourney+HoldOutJourney+ControlJourney;
			oGbl.report.log("INFO", "Overall Traffic: [ "+total+" ]", false);
			writer.write("Incedo Journey:	" +	IncedoJourney 	+ String.format("%n") + "BAU Journey:	" +	BAUJourney +String.format("%n") +"Holdout Journey : " +HoldOutJourney+ String.format("%n")+"Control Journey:	" +	ControlJourney +String.format("%n") +  "Total Traffic:	" +		total);
			oGbl.report.log("PASS", "Successfully returned the journey count in trafficSummary", true);
		}
		catch(Exception e)
		{
			oGbl.report.log("INFO", "Error in Writing Traffic Summary.txt "+e.getLocalizedMessage(), false);	
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
		oGbl.report.log("INFO", "Total Journey Count : [ "+total+" ] ", false);
		System.out.println("Overall Traffic" +total);
	}
}
