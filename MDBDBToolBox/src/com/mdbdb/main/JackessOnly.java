package com.mdbdb.main;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

public class JackessOnly 
{
	public static void readDBTroughJackess(String pathToMDBFile, String tableName)
	{
		try 
		{
			com.healthmarketscience.jackcess.Database db = DatabaseBuilder.open(new File(pathToMDBFile));
			
			Table table = db.getTable("tableName");
			  for(com.healthmarketscience.jackcess.Row row : table) 
			  {
				    System.out.println("Look ma, a row: " + row);
				  
			  }
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
