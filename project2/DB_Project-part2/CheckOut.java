import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.*;

 public class CheckOut{
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	
	 	
	
public static void main(String args[])throws IOException,SQLException
{ 
   CheckOut checkout=new CheckOut();
    Scanner sc=new Scanner(System.in);
   
	System.out.println("Rent(C/O) a book");
	System.out.print("Enter the Book ID : ");
    int book=sc.nextInt();
	sc.nextLine();
	int BOOK_ID=checkout.searchBookID(sc,book);
	sc.nextLine();
	if(BOOK_ID!=0)
	{
		System.out.println("Enter You Card No : ");
		String Card_no=sc.nextLine();
		System.out.println("Date Out(Format DD-MM-YYYY) : ");
		String Date_Out=sc.nextLine();
		System.out.println("Due Date(Format DD-MM-YYYY) : ");
		String Due_Date=sc.nextLine();
	    checkout.insertLoans(BOOK_ID,Card_no,Date_Out,Due_Date);   
	}
	 		
	}


public static int searchBookID(Scanner sc,int book) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "SELECT B.BOOK_ID,B.TITLE,B.AUTHOR,B.PUBLISHER,C.ISBN,C.NO_OF_COPIES_AVAILABLE,C.SUBJECT_AREA,C.DESCRIPTION FROM BOOK B,CATLOG C ,BOOK_LOGS BL WHERE BL.BOOK_ID=B.BOOK_ID AND BL.ISBN=C.ISBN AND B.BOOK_ID like ?";
                            
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setInt(1, book);
             
            ResultSet rs = pstmt.executeQuery();
			//System.out.println("BOOK_ID          TITLE            AUTHOR             PUBLISHER               ISBN             COPIES            SUBJECT_AREA            DESCRIPTION");
			while(rs.next()){				
            int BOOK_ID = rs.getInt("BOOK_ID");
            String TITLE = rs.getString("TITLE");
            String AUTHOR = rs.getString("AUTHOR");
			String PUBLISHER = rs.getString("PUBLISHER");
			String ISBN = rs.getString("ISBN");
			String COPIES = rs.getString("NO_OF_COPIES_AVAILABLE");
			String SUBJECTAREA = rs.getString("SUBJECT_AREA");
			String DESCRIPTION = rs.getString("DESCRIPTION");
            //System.out.println(BOOK_ID+"            "+TITLE+"            "+AUTHOR+"            "+PUBLISHER+"            "+ISBN+"            "+COPIES+"            "+SUBJECTAREA+"            "+DESCRIPTION);
		      
			System.out.println("BOOK_ID: "+BOOK_ID+"\n"+"TITLE: "+TITLE+"\n"+"AUTHOR: "+AUTHOR+"\n"+"PUBLISHER: "+PUBLISHER+"\n"+"ISBN: "+ISBN+"\n"+"NO OF COPIES: "+COPIES+"\n"+"SUBJECT AREA: "+SUBJECTAREA+"\n"+"DESCRIPTION: "+DESCRIPTION+"\n");
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
		System.out.println("Confirm the Book and Proced to Check out : \n1.Yes \n2.No");
		int choice=sc.nextInt();
		if(choice==1)
		return book;
	    else 
        return 0;			
		}

public static void insertLoans( int BOOK_ID, String Card_no, String Date_Out , String Due_Date) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "INSERT INTO LOANS VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'))";
 
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, BOOK_ID);
			pstmt.setString(2, Card_no);
            pstmt.setString(3, Date_Out);
			pstmt.setString(4, Due_Date);
            ResultSet rs = pstmt.executeQuery();
			System.out.println("Book Rented Succefully");
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
 
