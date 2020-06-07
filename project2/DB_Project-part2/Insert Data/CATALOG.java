import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import oracle.jdbc.driver.*;
import java.sql.*;
import java.lang.*;

public class CATALOG {
	public static void main(String[] args)   {
		try {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection DB_1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "admin"); 
		String tuple_value="";			
		BufferedReader read=new BufferedReader( new FileReader("E:\\UTA\\DB\\DB_Project2\\Insert Data\\CATALOG.CSV"));
		while( (tuple_value=read.readLine())!=null)
		{          
     	String[] value_in=tuple_value.split( ",");
		String command="insert into CATLOG values('"+value_in[0]+"',"+Integer.parseInt(value_in[1])+","+Integer.parseInt(value_in[2])+",'"+value_in[3]+"','"+value_in[4]+"')";
		PreparedStatement ps = DB_1.prepareStatement(command);
		ps.executeUpdate(command);
		ps.close();
		}
		read.close();
		DB_1.close();
		} catch (Exception Err_Table)
		{
		Err_Table.printStackTrace();
		}
		System.out.println("DATA INSERTED SUCCESSFULLY");
		}    


}
