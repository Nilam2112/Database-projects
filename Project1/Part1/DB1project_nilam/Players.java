package Info.DBload;

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

public class Players{
public static void main(String[] args)   {
try {
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection DB_1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "abcd123"); 
String tuple_value="";			
BufferedReader read=new BufferedReader( new FileReader("src/Info/DBload/Players.csv"));
while( (tuple_value=read.readLine())!=null)
{          
tuple_value=tuple_value.replace("'","");
String[] value_in=tuple_value.split( ",");
String command="insert into players values('"+Integer.parseInt(value_in[0])+"','"+value_in[1]+"','"+value_in[2]+"','"+value_in[3]+"','"+value_in[4]+"','"+value_in[5]+"','"+Integer.parseInt(value_in[6])+"','"+value_in[7]+"','"+value_in[8]+"','"+Integer.parseInt(value_in[9])+"','"+value_in[10]+"')";
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
}    
}
