import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.*;
public class MemberTrigger { 
private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	
	

 
public static void main(String[] args)throws SQLException {  
    MemberTrigger mt=new MemberTrigger();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy ");  
    Date date = new Date();  
      
     Scanner sc=new Scanner(System.in);
     System.out.println("Select the Time Period to check  Members who need Membership validity(Enter Days eg 10) :  \n1. 10 days \n2. 30 days \n3. 50 days");
	 int choice=sc.nextInt();
	 sc.nextLine();
	 mt.memberTrigger(date,choice);
}  


public static void memberTrigger(Date date,int choice) throws SQLException 
	{
		int count=0;
		
		java.sql.Date sqldate = new java.sql.Date(date.getTime());
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		//System.out.println("Here");
        String sql = "Select * FROM BORROWER WHERE MEMBERSHIP_VALIDITY-?<=?";  
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setDate(1, sqldate);
			pstmt.setInt(2, choice);
             
            ResultSet rs = pstmt.executeQuery();
		 	while(rs.next()){
			int CARD_NO=rs.getInt("CARD_NO");	
            String NAME= rs.getString("NAME");
            String MEMBERSHIP_VALIDITY = rs.getString("MEMBERSHIP_VALIDITY");
            System.out.println("Membership Card No : "+CARD_NO+"\n"+"NAME: "+NAME+"\n"+"MEMBERSHIP_VALIDITY: "+MEMBERSHIP_VALIDITY); 
		    System.out.println("\n");  
			count++;
			}
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
			if (pstmt!= null) {
				pstmt.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		
		}
		if(count!=0)
		System.out.println("Notification Renewal will be sent to the user saying \n --- \n Hello Sir, Your Membership validity is expiring in "+choice+" days. Please Renew Your Membership Before it ends.\n Thank You.\n Library Staff \n---");
		else
		System.out.println("No Records Found");		
		}



private static Connection getDBConnection() 
	{
		Connection dbConnection = null;
			try {
				Class.forName(DB_DRIVER);
				} 
			catch (ClassNotFoundException e) 
			{
				System.out.println(e.getMessage());
			}

			try {
					dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return dbConnection;

		}

} 