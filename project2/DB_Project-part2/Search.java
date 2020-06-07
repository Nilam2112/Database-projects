import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.*;

 public class Search{
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	
	 	
	
public static void main(String args[])throws IOException,SQLException
{ 
   Search search=new Search();
    
   Scanner sc=new Scanner(System.in);
	System.out.println("Search a book .\n Select the option to search a Book \n1: Author \n2: Title \n3: ISBN \n4: Subject Area");
	int choice=sc.nextInt();
	sc.nextLine();
	switch(choice)
	{
		case 1: System.out.print("Enter the Author of the book : ");
				String author=sc.nextLine();
				search.searchBookAuthor(author);
				break;
	    case 2: System.out.print("Enter the Title of the book : ");
				String title=sc.nextLine();
				search.searchBookTitle(title);
				break;
				
		case 3: System.out.print("Enter the ISBN of the book : ");
				String ISBN=sc.nextLine();
				search.searchBookISBN(ISBN);
				break;
		case 4: System.out.print("Enter the Subject Area of the book : ");
				String subjectArea=sc.nextLine();
				search.searchBookSA(subjectArea);
				break;
	}		
	}		
				
				
	public static void searchBookAuthor(String author) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "SELECT B.BOOK_ID,B.TITLE,B.AUTHOR,B.PUBLISHER,C.ISBN,C.NO_OF_COPIES_AVAILABLE,C.SUBJECT_AREA,C.DESCRIPTION FROM BOOK B,CATLOG C ,BOOK_LOGS BL WHERE BL.BOOK_ID=B.BOOK_ID AND BL.ISBN=C.ISBN AND B.AUTHOR like ?";
                            
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setString(1, author);
             
            ResultSet rs = pstmt.executeQuery();
			System.out.println("BOOK_ID          TITLE            AUTHOR             PUBLISHER               ISBN             COPIES            SUBJECT_AREA            DESCRIPTION");
			while(rs.next()){				
            int BOOK_ID = rs.getInt("BOOK_ID");
            String TITLE = rs.getString("TITLE");
            String AUTHOR = rs.getString("AUTHOR");
			String PUBLISHER = rs.getString("PUBLISHER");
			String ISBN = rs.getString("ISBN");
			String COPIES = rs.getString("NO_OF_COPIES_AVAILABLE");
			String SUBJECTAREA = rs.getString("SUBJECT_AREA");
			String DESCRIPTION = rs.getString("DESCRIPTION");
            System.out.println(BOOK_ID+"            "+TITLE+"            "+AUTHOR+"            "+PUBLISHER+"            "+ISBN+"            "+COPIES+"            "+SUBJECTAREA+"            "+DESCRIPTION);
		      
			//System.out.println("BOOK_ID: "+BOOK_ID+"\n"+"TITLE: "+TITLE+"\n"+"AUTHOR: "+AUTHOR+"\n"+"PUBLISHER: "+PUBLISHER+"\n"+"ISBN: "+ISBN+"NO OF COPIES: "+COPIES+"\n"+"SUBJECT AREA: "+SUBJECTAREA+"\n"+"DESCRIPTION: "+DESCRIPTION+"\n");
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
		
		}
	

public static void searchBookTitle(String title) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "SELECT B.BOOK_ID,B.TITLE,B.AUTHOR,B.PUBLISHER,C.ISBN,C.NO_OF_COPIES_AVAILABLE,C.SUBJECT_AREA,C.DESCRIPTION FROM BOOK B,CATLOG C ,BOOK_LOGS BL WHERE BL.BOOK_ID=B.BOOK_ID AND BL.ISBN=C.ISBN AND B.TITLE like ?";
                            
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setString(1, title);
             
            ResultSet rs = pstmt.executeQuery();
			System.out.println("BOOK_ID          TITLE            AUTHOR             PUBLISHER               ISBN             COPIES            SUBJECT_AREA            DESCRIPTION");
			 
			while(rs.next()){				
            int BOOK_ID = rs.getInt("BOOK_ID");
            String TITLE = rs.getString("TITLE");
            String AUTHOR = rs.getString("AUTHOR");
			String PUBLISHER = rs.getString("PUBLISHER");
			String ISBN = rs.getString("ISBN");
			String COPIES = rs.getString("NO_OF_COPIES_AVAILABLE");
			String SUBJECTAREA = rs.getString("SUBJECT_AREA");
			String DESCRIPTION = rs.getString("DESCRIPTION");
            System.out.println(BOOK_ID+"            "+TITLE+"            "+AUTHOR+"            "+PUBLISHER+"            "+ISBN+"            "+COPIES+"            "+SUBJECTAREA+"            "+DESCRIPTION);
		      
			//System.out.println("BOOK_ID: "+BOOK_ID+"\n"+"TITLE: "+TITLE+"\n"+"AUTHOR: "+AUTHOR+"\n"+"PUBLISHER: "+PUBLISHER+"\n"+"ISBN: "+ISBN+"NO OF COPIES: "+COPIES+"\n"+"SUBJECT AREA: "+SUBJECTAREA+"\n"+"DESCRIPTION: "+DESCRIPTION+"\n");
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
		
		}


public static void searchBookISBN(String isbn) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "SELECT B.BOOK_ID,B.TITLE,B.AUTHOR,B.PUBLISHER,C.ISBN,C.NO_OF_COPIES_AVAILABLE,C.SUBJECT_AREA,C.DESCRIPTION FROM BOOK B,CATLOG C ,BOOK_LOGS BL WHERE BL.BOOK_ID=B.BOOK_ID AND BL.ISBN=C.ISBN AND C.ISBN like ?";
                            
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setString(1, isbn);
             
            ResultSet rs = pstmt.executeQuery();
			System.out.println("BOOK_ID          TITLE            AUTHOR             PUBLISHER               ISBN             COPIES            SUBJECT_AREA            DESCRIPTION");
			 
			while(rs.next()){				
            int BOOK_ID = rs.getInt("BOOK_ID");
            String TITLE = rs.getString("TITLE");
            String AUTHOR = rs.getString("AUTHOR");
			String PUBLISHER = rs.getString("PUBLISHER");
			String ISBN = rs.getString("ISBN");
			String COPIES = rs.getString("NO_OF_COPIES_AVAILABLE");
			String SUBJECTAREA = rs.getString("SUBJECT_AREA");
			String DESCRIPTION = rs.getString("DESCRIPTION");
            System.out.println(BOOK_ID+"            "+TITLE+"            "+AUTHOR+"            "+PUBLISHER+"            "+ISBN+"            "+COPIES+"            "+SUBJECTAREA+"            "+DESCRIPTION);
		      
			//System.out.println("BOOK_ID: "+BOOK_ID+"\n"+"TITLE: "+TITLE+"\n"+"AUTHOR: "+AUTHOR+"\n"+"PUBLISHER: "+PUBLISHER+"\n"+"ISBN: "+ISBN+"NO OF COPIES: "+COPIES+"\n"+"SUBJECT AREA: "+SUBJECTAREA+"\n"+"DESCRIPTION: "+DESCRIPTION+"\n");
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
		
		}


		public static void searchBookSA(String subjectArea) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "SELECT B.BOOK_ID,B.TITLE,B.AUTHOR,B.PUBLISHER,C.ISBN,C.NO_OF_COPIES_AVAILABLE,C.SUBJECT_AREA,C.DESCRIPTION FROM BOOK B,CATLOG C ,BOOK_LOGS BL WHERE BL.BOOK_ID=B.BOOK_ID AND BL.ISBN=C.ISBN AND C.SUBJECT_AREA like ?";
                            
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            
			pstmt.setString(1, subjectArea);
             
            ResultSet rs = pstmt.executeQuery();
			System.out.println("BOOK_ID          TITLE            AUTHOR             PUBLISHER               ISBN             COPIES            SUBJECT_AREA            DESCRIPTION");
			 
			while(rs.next()){				
            int BOOK_ID = rs.getInt("BOOK_ID");
            String TITLE = rs.getString("TITLE");
            String AUTHOR = rs.getString("AUTHOR");
			String PUBLISHER = rs.getString("PUBLISHER");
			String ISBN = rs.getString("ISBN");
			String COPIES = rs.getString("NO_OF_COPIES_AVAILABLE");
			String SUBJECTAREA = rs.getString("SUBJECT_AREA");
			String DESCRIPTION = rs.getString("DESCRIPTION");
            System.out.println(BOOK_ID+"            "+TITLE+"            "+AUTHOR+"            "+PUBLISHER+"            "+ISBN+"            "+COPIES+"            "+SUBJECTAREA+"            "+DESCRIPTION);
		      
			//System.out.println("BOOK_ID: "+BOOK_ID+"\n"+"TITLE: "+TITLE+"\n"+"AUTHOR: "+AUTHOR+"\n"+"PUBLISHER: "+PUBLISHER+"\n"+"ISBN: "+ISBN+"NO OF COPIES: "+COPIES+"\n"+"SUBJECT AREA: "+SUBJECTAREA+"\n"+"DESCRIPTION: "+DESCRIPTION+"\n");
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