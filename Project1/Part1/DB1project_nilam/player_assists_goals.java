package Info.DBload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.*;
import java.sql.*;
import java.lang.*;

public class player_assists_goals{
public static void main(String[] args)   {
try {
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection DB_1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "abcd123"); 
String tuple_value="";			
BufferedReader read=new BufferedReader( new FileReader("src/Info/DBload/Player_Assists_Goals.csv"));
while( (tuple_value=read.readLine())!=null)
{          
tuple_value=tuple_value.replace("'","");
String[] value_in=tuple_value.split( ",");
String command="insert into PLAYER_ASSISTS_GOALS values('"+Integer.parseInt(value_in[0])+"','"+Integer.parseInt(value_in[1])+"','"+Integer.parseInt(value_in[2])+"','"+Integer.parseInt(value_in[3])+"','"+Integer.parseInt(value_in[4])+"')";
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