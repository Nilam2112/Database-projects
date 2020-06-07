import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.*;

 public class RenewMembership{
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	
	 	
	
public static void main(String args[])throws IOException,SQLException
 { 
   RenewMembership rm=new RenewMembership();
    Scanner sc=new Scanner(System.in);
	System.out.print("Enter the Membership Card No : " );
	int CARD_NO=sc.nextInt();
	sc.nextLine();
	int choice=rm.memberdetails(sc,CARD_NO);
	sc.nextLine();
	if(choice!=0)
	rm.renewmember(sc,CARD_NO);
	
 }
 
public static int memberdetails(Scanner sc,int CARD_no) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		//System.out.println("Here");
        String sql = "Select * FROM BORROWER WHERE CARD_NO=?";  
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setInt(1, CARD_no);
             
            ResultSet rs = pstmt.executeQuery();
		 	while(rs.next()){
			int CARD_NO=rs.getInt("CARD_NO");	
            String NAME= rs.getString("NAME");
            String MEMBERSHIP_VALIDITY = rs.getString("MEMBERSHIP_VALIDITY");
            System.out.println("Membership Card No : "+CARD_NO+"\n"+"NAME: "+NAME+"\n"+"MEMBERSHIP_VALIDITY: "+MEMBERSHIP_VALIDITY); 
		      
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
		System.out.println("Confirm the MEMBER and Proced  : \n1.Yes \n2.No");
		int choice=sc.nextInt();
		if(choice==1)
		return CARD_no;
	    else 
        return 0;			
		}

 
public static void renewmember(Scanner sc, int Card_No) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		 
        String sql =" Update Borrower Set membership_validity=TO_DATE(?,'DD-MM-YYYY') Where Card_No=?";
        System.out.println("Enter the new validity period (Format DD-MM-YYYY): ");
		String newDate=sc.nextLine();
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setString(1,newDate);
			pstmt.setInt(2,Card_No);
             
            ResultSet rs = pstmt.executeQuery();
			System.out.println("Membership  renewed succesfuly");
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
