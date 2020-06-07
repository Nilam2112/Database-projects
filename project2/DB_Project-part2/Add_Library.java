import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
    public class Add_Library{
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "admin";	

	
public static void main(String args[])throws IOException,SQLException
{ 
   Add_Library test=new Add_Library();
   int choice;
   Scanner sc=new Scanner(System.in);
   System.out.println("Please Select the operation to perform ? \n 1) Add a member \n 2) Add a book \n ");
   choice=sc.nextInt();
   switch(choice)
   {
	   case 1: System.out.println("Fill up the following details To add a member");
				System.out.print("Enter the Card_No : ");
				double Card_no=sc.nextDouble();
				sc.nextLine();
				System.out.print("Name : ");
				String name=sc.nextLine();
				System.out.print("Phone : ");
				String Phone=sc.nextLine();
				System.out.print("SSN : ");
				String SSN=sc.nextLine();
				System.out.print("Campus Address : ");
				String Campus_Address=sc.nextLine();;
				System.out.print("Home Address : ");
				String Home_Address=sc.nextLine();
				System.out.print("Member Type : ");
				String Member_Type=sc.nextLine();	
				test.insertMember(Card_no,name,Phone,SSN,Campus_Address,Home_Address,Member_Type);
		          break;
		case 2: System.out.println("Fill Up the Following Details To add a Book");
			     System.out.print("Enter the Book_Id : ");
				 double Book_Id=sc.nextDouble();
				 sc.nextLine();
				 System.out.print("Enter the Title of the Book : ");
				 String title=sc.nextLine();
				 System.out.print("Enter the Author of the Book : ");
				 String author=sc.nextLine();
				 System.out.print("Enter the Publisher of the Book : ");
				 String publisher=sc.nextLine();
				 System.out.print("Enter the ISBN of the Book : ");
				 String ISBN=sc.nextLine();
				 System.out.print("No Of copies of the Book : ");
				 double copies=sc.nextDouble();
				 sc.nextLine();
				 double rented=0;
				 System.out.print("Enter the Subject Area of the Book : ");
				 String subjectArea=sc.nextLine();
				 System.out.println("Enter the Description of the Book : ");
				 String description=sc.nextLine();
				 test.insertBook(Book_Id,title,author,publisher);
				 test.insertCatlog(ISBN,copies,rented,subjectArea,description);
				 test.insertBook_logs(Book_Id,ISBN);
				 break;
		
		
				
   }
   
}
public static void insertMember( double Card_no, String name , String Phone ,String SSN ,String Campus_Address ,String Home_Address , String Member_Type) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?)";
 
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            pstmt.setDouble(1, Card_no);
			pstmt.setString(2, name);
            pstmt.setString(3, Phone);
			pstmt.setString(4, SSN);
			pstmt.setString(5, Campus_Address);
			pstmt.setString(6, Home_Address);
			pstmt.setString(7, Member_Type);
            ResultSet rs = pstmt.executeQuery();
			System.out.println("Member Added succesfuly");
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
	
	public static void insertBook( double Book_Id, String title , String author ,String publisher) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "INSERT INTO BOOK VALUES(?,?,?,?)";
 
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            pstmt.setDouble(1, Book_Id);
			pstmt.setString(2, title);
            pstmt.setString(3, author);
			pstmt.setString(4, publisher);
            ResultSet rs = pstmt.executeQuery();
			//System.out.println("Rows Inserted succesfuly");
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
	
public static void insertCatlog( String ISBN, double copies, double rented , String subjectArea, String description) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "INSERT INTO CATLOG VALUES(?,?,?,?,?)";
 
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, ISBN);
			pstmt.setDouble(2, copies);
            pstmt.setDouble(3, rented);
			pstmt.setString(4, subjectArea);
			pstmt.setString(5, description);
            ResultSet rs = pstmt.executeQuery();
			//System.out.println("Book Added succesfuly");
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
	
public static void insertBook_logs(double Book_Id,String ISBN) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
        String sql = "INSERT INTO BOOK_LOGS VALUES(?,?)";
 
        try {
			dbConnection = getDBConnection();
			pstmt = dbConnection.prepareStatement(sql);
            pstmt.setDouble(1, Book_Id);
			pstmt.setString(2, ISBN);
			 ResultSet rs = pstmt.executeQuery();
			System.out.println("BOOK Added succesfuly");
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
