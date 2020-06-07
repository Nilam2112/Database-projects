import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.*;
public class OverDueTrigger { 
private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	
	

 
public static void main(String[] args)throws SQLException {  
    OverDueTrigger mt=new OverDueTrigger();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy ");  
    Date date = new Date();   
     Scanner sc=new Scanner(System.in);
     System.out.println("Select the Time Period to check  Members with overdue books :  \n1. 5 days \n2. 10 days \n3. 15 days");
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
        String sql = "Select L.BOOK_ID,L.CARD_NO,M.NAME,B.TITLE,BL.ISBN,L.DATE_OUT,L.DUE_DATE FROM Loans L, BOOK B, Member M, BOOK_LOGS BL Where L.BOOK_ID=B.BOOK_ID AND L.CARD_NO=M.CARD_NO And L.BOOK_ID=BL.BOOK_ID And ?-L.DUE_DATE>=?";  
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setDate(1, sqldate);
			pstmt.setInt(2, choice);
             
            ResultSet rs = pstmt.executeQuery();
			
		 	while(rs.next()){
			int BOOK_ID=rs.getInt("BOOK_ID");	
			int CARD_NO=rs.getInt("CARD_NO");	
            String NAME= rs.getString("NAME");
			String TITLE=rs.getString("TITLE");
			String ISBN=rs.getString("ISBN");
			String DATE_OUT=rs.getString("DATE_OUT");
			String DUE_DATE=rs.getString("DUE_DATE");
            System.out.println("BOOK_ID : "+BOOK_ID+"\n"+"CARD_NO : "+CARD_NO+"\n"+"NAME: "+NAME+"\n"+"TITLE : "+TITLE+"\n"+"ISBN : "+ISBN+"\n"+"DATE_OUT : "+DATE_OUT+"\n"+"DUE_DATE : "+DUE_DATE); 
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
		if(count!=0){
		System.out.println("Following is the List of the Members with overdue Book :");	
		System.out.println("Notification For OverDue BOOK will be sent to the user saying \n --- \n Hello Sir, The Book issued is overdue from last "+choice+" days. \n Please Return the Book Or You will be charged with Late fee.\n Thank You.\n Library Staff \n---");
		}
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