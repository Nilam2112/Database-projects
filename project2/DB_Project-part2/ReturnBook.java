import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.*;

 public class ReturnBook{
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	
	 	
	
public static void main(String args[])throws IOException,SQLException
{ 
   ReturnBook rb=new ReturnBook();
    Scanner sc=new Scanner(System.in);
   
	System.out.println("Return a book");
	System.out.print("Enter the Book ID : ");
    int book=sc.nextInt();
	sc.nextLine();
	int BOOK_ID=rb.searchBookID(sc,book);
	sc.nextLine();
	if(BOOK_ID!=0)
	{
	 rb.returnLoans(BOOK_ID);   
	}
	 		
	}


public static int searchBookID(Scanner sc,int book) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		//System.out.println("Here");
        String sql = "Select L.CARD_NO,B.BOOK_ID,B.TITLE,B.AUTHOR,B.PUBLISHER,C.ISBN,C.SUBJECT_AREA,L.Date_out ,L.Due_date FROM BOOK B,CATLOG C ,LOANS L, BOOK_LOGS BL WHERE BL.BOOK_ID=B.BOOK_ID AND BL.ISBN=C.ISBN AND L.BOOK_ID=B.BOOK_ID AND B.BOOK_ID Like ? ";
                            
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setInt(1, book);
             
            ResultSet rs = pstmt.executeQuery();
			//System.out.println("BOOK_ID          TITLE            AUTHOR             PUBLISHER               ISBN             COPIES            SUBJECT_AREA            DESCRIPTION");
			while(rs.next()){
			int CARD_NO=rs.getInt("CARD_NO");	
            int BOOK_ID = rs.getInt("BOOK_ID");
            String TITLE = rs.getString("TITLE");
            String AUTHOR = rs.getString("AUTHOR");
			String PUBLISHER = rs.getString("PUBLISHER");
			String ISBN = rs.getString("ISBN");
			String SUBJECT_AREA = rs.getString("SUBJECT_AREA");
			String DATE_OUT = rs.getString("DATE_OUT");
			String DUE_DATE = rs.getString("DUE_DATE");
            //System.out.println(BOOK_ID+"            "+TITLE+"            "+AUTHOR+"            "+PUBLISHER+"            "+ISBN+"            "+COPIES+"            "+SUBJECTAREA+"            "+DESCRIPTION);
		      
			System.out.println("Membership Card No : "+CARD_NO+"\n"+"BOOK_ID: "+BOOK_ID+"\n"+"TITLE: "+TITLE+"\n"+"AUTHOR: "+AUTHOR+"\n"+"PUBLISHER: "+PUBLISHER+"\n"+"ISBN: "+ISBN+"\n"+"SUBJECT AREA: "+SUBJECT_AREA+"\n"+"Date Borrowed "+DATE_OUT+"\n"+"Date Of Returned :"+DUE_DATE+"\n");
			}
		      
			
			//System.out.println("Member Added succesfuly");
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
		System.out.println("Confirm the Book and Proced the return : \n1.Yes \n2.No");
		int choice=sc.nextInt();
		if(choice==1)
		return book;
	    else 
        return 0;			
		}

public static void returnLoans(int BOOK_ID) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "DELETE FROM LOANS WHERE BOOK_ID like ?";
 
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, BOOK_ID);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Book Returned Succefully");
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
 
