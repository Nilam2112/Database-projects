/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.*;
/**
 *
 * @author ARCHITHA
 */
public class DataLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)   {
        try {
            // TODO code application logic here
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@omega:1521:CSE1", "axh7433", "Apple123");
            
            BufferedReader in=new BufferedReader( new FileReader("EMPLOYEE.txt"));
            //BufferedReader in1=new BufferedReader( new FileReader("DEPARTMENT.txt"));
            //BufferedReader in2=new BufferedReader( new FileReader("DEPT_LOCATIONS.txt"));
            //BufferedReader in3=new BufferedReader( new FileReader("PROJECT.txt"));
            //BufferedReader in4=new BufferedReader( new FileReader("WORKS_ON.txt"));
            //BufferedReader in5=new BufferedReader( new FileReader("DEPENDENT.txt"));
            
            String line="";
            //line1="",line2="",line3="",line4="",line5="";
            //employee
            while( (line=in.readLine())!=null)
            {
                //'James', 'E', 'Jordan', '888665151', '10-NOV-1927', '450 Stone,Houston,TX', 'M', 55000, null, 1
                
                line=line.replace("'","");
                String[] temp=line.split( ", ");
                
               
                String query="insert into EMPLOYEE values('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+Integer.parseInt(temp[3])+"','"+temp[4]+"','"+temp[5]+"','"+temp[6]+"','"+Integer.parseInt(temp[7])+"','"+temp[8]+"','"+Integer.parseInt(temp[9])+"')";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.executeUpdate(query);
                stmt.close();
           
            }
            
            in.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
