package config;

public class Global 
{
public static Global oGbl=new Global();

public static int counter=0; 
public ExcelUtils excelutils;
public Common cmn;
public Actionkey actionKey;
public Report report;
 
	public Global()
	{
		if(counter==0)
		{
			excelutils=new ExcelUtils();
			cmn=new Common();
			actionKey= new Actionkey();
			report= new Report();
		}
		counter++;
	}
}
 