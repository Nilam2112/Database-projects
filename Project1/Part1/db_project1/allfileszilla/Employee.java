import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.*;

public class Employee {
public static void main(String[] args)   {
try {
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection myDb = DriverManager.getConnection("jdbc:oracle:thin:@omega:1521:CSE1", "sxn6051", "Apple123"); 
String row="";			
BufferedReader read=new BufferedReader( new FileReader("Employee.txt"));
while( (row=read.readLine())!=null)
{                
row=row.replace("'","");
String[] data=row.split( ", ");
String command="insert into EMPLOYEE values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+Integer.parseInt(data[3])+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+Integer.parseInt(data[7])+"','"+data[8]+"','"+Integer.parseInt(data[9])+"')";
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