import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.*;



public class Country{
public static void main(String[] args)   {
try {
Class.forName(new oracle.jdbc.driver.OracleDriver());
Connection myDb = Class.getConnection("jdbc:oracle:thin:@omega:1521:CSE1", "nbm3836", "birdy2112"); 
String row="";			
BufferedReader read=new BufferedReader( new FileReader("Country.txt"));
while( (row=read.readLine())!=null)
{                
row=row.replace("'","");
String[] data=row.split( ", ");
String command="insert into COUNTRY values('"+data[0]+"','"+Integer.parseInt(data[1])+"','"+Integer.parseInt(data[2])+"','"+data[3]+"')";
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