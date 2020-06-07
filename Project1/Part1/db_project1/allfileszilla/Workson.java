import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.*;

public class Workson {
public static void main(String[] args)   {
try {
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection myDb = DriverManager.getConnection("jdbc:oracle:thin:@omega:1521:CSE1", "sxn6051", "Apple123"); 
String row="";			
BufferedReader read=new BufferedReader( new FileReader("Works_on.txt"));
while( (row=read.readLine())!=null)
{                
row=row.replace("'","");
String[] data=row.split( ", ");
String command="insert into WORKS_ON values('"+data[0]+"','"+Integer.parseInt(data[1])+"','"+Float.parseFloat(data[2])+"')";
PreparedStatement ps = myDb.prepareStatement(command);
ps.executeUpdate(command);
ps.close();
}
read.close();
myDb.close();
} catch (Exception tableEx) 
{
tableEx.printStackTrace();
}
}    
}