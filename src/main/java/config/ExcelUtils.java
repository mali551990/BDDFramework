package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils 
{

public String filepath =System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"ExternalDataProvider"+File.separator+"DataSheet.xls";	
public static Hashtable <String,String> TD=new Hashtable<String,String>();
public Hashtable <String,String> TDList= new Hashtable<String,String>();
public FileInputStream fis;
public HSSFWorkbook workbook;
public HSSFSheet sheet;
public static int LabelCount=0;

public static String HC_filePath=System.getProperty("user.dir")+File.separator+"TestResults.xls";
public static Workbook HC_Workbook = null; 
public static Sheet HC_sheet = null;
public static int HC_currentRowIndex=1,HC_currentColumnIndex=2;
public static HashMap<String,String> mtn=new HashMap<String,String>();
public List<String> getDataSet(int Row,int Column, String sheetName)
{
	List Li = new ArrayList<String>();
 return null;
}

public String getRowData(String columnName)
{
	 return null;

}
 
public void initializeHC_WorkBook()
{
	 HC_Workbook = new HSSFWorkbook(); 
     HC_sheet = HC_Workbook.createSheet("WorkFlow");
     HC_currentRowIndex=1;
}
public void createSheet()
{
 
}
 
public void closeFile()
{
 
}
 
public void HC_SetLastColumn(int lastColCount)
{
	HC_currentColumnIndex=lastColCount;
}
public static void writeExcel(List<String> cellList) throws Exception
{
	LinkedHashMap<String,String> failedlist=new LinkedHashMap <String,String> ();
	int F_colindex=0;
	String failDateStamp="",PageName="";
    Workbook wb = new HSSFWorkbook(); 

    String Rowdata="";

    for(String eachCell:cellList)
    {
    	Rowdata=Rowdata+","+eachCell;
    	//System.out.println(eachCell);
    }
    
    String [] row=Rowdata.split("~");
    int Mapcounter=0;
    Sheet sheet = wb.createSheet("Output");
    Row hssfrow =null;
    Cell cell =null;
    for(int eachRow=1;eachRow<row.length;eachRow++)
    {
        String [] column=row[eachRow].split(",");
		hssfrow = sheet.createRow(eachRow); 
        for(int eachColumn=1;eachColumn<column.length;eachColumn++)
        {
        	    cell = hssfrow.createCell(eachColumn); 
        	    CellStyle style=wb.createCellStyle();
        	    style.setBorderBottom(BorderStyle.THIN);
        	    style.setBorderLeft(BorderStyle.THIN);
        	    style.setBorderRight(BorderStyle.THIN);
        	    style.setBorderTop(BorderStyle.THIN);
        	    cell.setCellStyle(style);
        	    cell.setCellType(CellType.STRING);
        	    cell.setCellValue(""+column[eachColumn]);
        	    if(column[eachColumn].equals("FAIL"))
        	    {
        	    	F_colindex=eachColumn;
        	    	failDateStamp=column[eachColumn+1];
        	    	PageName=column[1];
        	    	failedlist.put(""+Mapcounter, failDateStamp);
        	    	Mapcounter++;
        	    }
        	    
        }
        if(failedlist.size()==1)
        {
    	    cell = hssfrow.createCell(column.length); 
    	    CellStyle style=wb.createCellStyle();
    	    style.setBorderBottom(BorderStyle.THIN);
    	    style.setBorderLeft(BorderStyle.THIN);
    	    style.setBorderRight(BorderStyle.THIN);
    	    style.setBorderTop(BorderStyle.THIN);
    	    cell.setCellStyle(style);

    	    cell.setCellType(CellType.STRING);
    	    cell.setCellValue(PageName+ "  FAIL at "+failDateStamp);
        }
        else if(failedlist.size()>1)
        {
        	int totalminutes=0;

        	for(int eachDate=0;eachDate<failedlist.size();eachDate++) 
        	{
        		totalminutes=totalminutes+Common.compareTimeDifference(failedlist.get(""+eachDate), failedlist.get(""+(eachDate+1)));

        	}
        	cell = hssfrow.createCell(column.length); 
    	    CellStyle style=wb.createCellStyle();
    	    style.setBorderBottom(BorderStyle.THIN);
    	    style.setBorderLeft(BorderStyle.THIN);
    	    style.setBorderRight(BorderStyle.THIN);
    	    style.setBorderTop(BorderStyle.THIN);
    	    cell.setCellStyle(style);

        	cell.setCellType(CellType.STRING);
        	cell.setCellValue(PageName+ "   FAIL at "+failDateStamp+ "since last" +totalminutes+ " minutes");
        }
       failedlist.clear();
        
    }
    
    
    
    
    OutputStream os = new FileOutputStream(System.getProperty("user.dir")+File.separator+"testResults_Output.xls"); 

    wb.write(os); 
    os.close();
}
 
public void openFile(String sheetName) throws Exception
{
	try
	{
		  fis = new FileInputStream(filepath);
		  workbook = new HSSFWorkbook(fis);
		  sheet = workbook.getSheet(sheetName);	
	}
	catch(Exception e)
	{
		
	}
}
 
public void getTestCaseIDList() throws Exception
{
	 int rowLength = sheet.getLastRowNum();
	 for (int eachRow=0;eachRow<rowLength;eachRow++)
	 {
		 	 Row row =sheet.getRow(eachRow);
		 	 Cell cell = null;
		 	 try
		 	 {
				  cell =row.getCell(1);
					 String cellvalue=cell.getStringCellValue(); 
					 
					 if(cellvalue!="")
					 {
						 TDList.put(""+eachRow, cellvalue);						 
					 }

		 	 }
		 	 catch(Exception e)
		 	 {
		 		 
		 	 }
			 
	 }
	 
	
}


public int getTestDataRowIndex(String TestCaseName) throws Exception
{
	String key="",value="";
	boolean testCaseFound=false;
    for (Map.Entry<String,String> entry : TDList.entrySet())  
    {
    	value=entry.getValue();
    	
    	if(value.equals(TestCaseName))
    	{
    		key=entry.getKey();
    		testCaseFound=true;
    		break;
    	}
    	
    }
    
	if(testCaseFound==false)
	{
		throw new Exception (" Test data "+TestCaseName+" not Found");
	}
	
	return Integer.parseInt(key);
	
}

public void readandStoreTestCase(int index)
{
	 Row testCaseHeader =sheet.getRow(index);
	 Row testCaseData =sheet.getRow(index+1);

	for (int eachColumn=3;eachColumn<100;eachColumn++)
	{
	 	 Cell celltestCaseHeader = null;
	 	 Cell celltestCaseData=null;
	 	 try
	 	 {
	 		  celltestCaseHeader =testCaseHeader.getCell(eachColumn);
	 		  celltestCaseData = testCaseData.getCell(eachColumn);
			  String Header=celltestCaseHeader.getStringCellValue(); 
			  testCaseData.getCell(eachColumn).setCellType(CellType.STRING);
			  String testData =celltestCaseData.getStringCellValue();
				 TD.put(Header, testData);
	 	 }
	 	 catch(Exception e)
	 	 {
	 		 break;
	 	 }

	}
	
	
	
	
}


public static String TD(String key) throws Exception
{
	String data="";
	try
	{
		data=TD.get(key);
	}
	catch(Exception e)
	{
			throw new Exception ("Data missing for := > "+key);
	}
	return data;
	
}

public void close() throws Exception
{
	try
	{
		workbook.close();
	}
	catch(Exception e)
	{
		throw new Exception ("File not Closed : "+e.getMessage());
	}
}


public  void ReadandProcessData(String SheetName,String TestCaseID) throws Exception
{
	try
	{
		openFile(SheetName);
		getTestCaseIDList();
		int index=getTestDataRowIndex(TestCaseID);
		readandStoreTestCase(index);
		close();	
	}
	catch(Exception e)
	{
		System.out.println("exception in Reading the file "+e.getMessage());
	}
}

public  List<String> readdataFromExcel(String filePath) throws Exception
{
	File file = new File(filePath);
	FileInputStream fis= new FileInputStream(file);
	HSSFWorkbook workbook = new HSSFWorkbook(fis);
	HSSFSheet sheet =workbook.getSheetAt(0);
	HSSFRow row=sheet.getRow(1);
	int colCount = row.getLastCellNum();
	int rowCount = sheet.getLastRowNum() + 1;
	List<String> celllist=new ArrayList<String>();
	 for (int eachRow=1;eachRow<rowCount;eachRow++)
	 {
		 row =sheet.getRow(eachRow);
		 Cell cell = null;
		 celllist.add("~");
		 for(int eachColumn=0;eachColumn<colCount;eachColumn++)
		 {
			 cell =row.getCell(eachColumn);
			 if(cell!=null)
			 {
				 switch(cell.getCellType())
				 {
				 case NUMERIC:
					 
					 cell.setCellType(CellType.NUMERIC);
					 Date dblcellvalue=cell.getDateCellValue();
					 celllist.add(""+dblcellvalue);
	                 break;
				 case STRING:
					 cell.setCellType(CellType.STRING);
					 String cellvalue=cell.getStringCellValue(); 
					 celllist.add(cellvalue);
	                 break; 
				default:
					break;
				 }

			 }
			
		 }
		 System.out.println();
	 }
	 return celllist;
}


public void WriteStatusinExcel(String status,String timeStamp)
{
    Row hcRow =null;
    Cell cell =null;
    for(int eachStatus=0;eachStatus<2;eachStatus++)
    {
	    if(eachStatus==0)
	    {
			hcRow = HC_sheet.getRow(HC_currentRowIndex); 
		    cell = hcRow.createCell(HC_currentColumnIndex); 
		    CellStyle style=HC_Workbook.createCellStyle();
		    style.setBorderBottom(BorderStyle.THIN);
		    style.setBorderLeft(BorderStyle.THIN);
		    style.setBorderRight(BorderStyle.THIN);
		    style.setBorderTop(BorderStyle.THIN);
		    cell.setCellStyle(style);
		    cell.setCellType(CellType.STRING);

			cell.setCellValue(status);
	    }
	    else
	    {
			hcRow = HC_sheet.getRow(HC_currentRowIndex); 
		    cell = hcRow.createCell(HC_currentColumnIndex+1); 
		    CellStyle style=HC_Workbook.createCellStyle();
		    style.setBorderBottom(BorderStyle.THIN);
		    style.setBorderLeft(BorderStyle.THIN);
		    style.setBorderRight(BorderStyle.THIN);
		    style.setBorderTop(BorderStyle.THIN);
		    cell.setCellStyle(style);
		    cell.setCellType(CellType.STRING);
			cell.setCellValue(timeStamp);
	    }
    }
    HC_currentRowIndex++;

}

public void saveExcelData() throws IOException
{
    OutputStream os = new FileOutputStream(System.getProperty("user.dir")+File.separator+"TestResults.xls"); 
    HC_Workbook.write(os); 
    HC_Workbook.close();
    os.close();
}

public int copydataFromExistingSheet(List<String> excelDataList)
{
	int columnsize=0;
    String Rowdata="";

    for(String eachCell:excelDataList)
    {
    	Rowdata=Rowdata+","+eachCell;
    }
    
    String [] row=Rowdata.split("~");
    Row hssfrow =null;
    Cell cell =null;
    for(int eachRow=1;eachRow<row.length;eachRow++)
    {
        String [] column=row[eachRow].split(",");
        columnsize=column.length;
		hssfrow = HC_sheet.createRow(eachRow); 
        for(int eachColumn=1;eachColumn<column.length;eachColumn++)
        {
        	    cell = hssfrow.createCell(eachColumn); 
        	    CellStyle style=HC_Workbook.createCellStyle();
        	    style.setBorderBottom(BorderStyle.THIN);
        	    style.setBorderLeft(BorderStyle.THIN);
        	    style.setBorderRight(BorderStyle.THIN);
        	    style.setBorderTop(BorderStyle.THIN);
        	    cell.setCellStyle(style);
        	    cell.setCellType(CellType.STRING);
        	    cell.setCellValue(""+column[eachColumn]);
        }
    }
    
    return columnsize;
}




public  int getMtnDetails() 
{
	int rowcount=0;
	int counter=1;
	try
	{
		openFile("MTN");
		HSSFRow row=sheet.getRow(0);
		Cell cell =null;
		 rowcount =sheet.getPhysicalNumberOfRows()-1;
		 
		 for (int eachRow=1;eachRow<=rowcount;eachRow++)
		 {
			 row =sheet.getRow(eachRow);
			 for(int eachColumn=1;eachColumn<row.getLastCellNum();eachColumn++)
			 {
				 cell =row.getCell(eachColumn);
				 if(cell!=null)
				 {
					 if(eachColumn==1)
					 {
						 cell.setCellType(CellType.STRING);
						 mtn.put("mtn_"+counter,cell.getStringCellValue()); 
					 }
					 else if(eachColumn==2)

					 {
						 cell.setCellType(CellType.STRING);
						 mtn.put("mtn_type_"+counter,cell.getStringCellValue()); 
					 }
				 }
				 counter++;
			 }
		 }	 
		close();
	
	}
	catch (Exception e)
	{
		
	}
	return rowcount;
}





}
