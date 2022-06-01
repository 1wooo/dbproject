package DBPR;
import java.awt.*;
import java.sql.*;
	
public class Movie {

	public static void main(String args[]) {
		
		DB db = new DB();
		String upDateDB = "";
		
		for (int i=1; i<=10; i++) {
			for (int j=1; j<=15; j++) {
				upDateDB = "INSERT INTO Seat VALUES(" + i + "," + j + "," + "'사용가능')";
				db.inSert(upDateDB);
			}
		}
	}
}

class DB {
	Connection con = null;
	Statement stmt = null;
	ResultSet res = null;
	
	String username = "madang";
	String password = "madang";
	String url = "jdbc:mysql://localhost:3306/madang";
	
	public DB() {

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("성공");

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
	}
	
	public void inSert(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}