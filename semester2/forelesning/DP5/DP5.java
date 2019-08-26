import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.*;

class Database{
	public static void main(String[]args){
		System.out.println("Start");
		Scanner s = new Scanner(System.in);
		String password = s.nextLine();
		String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/?user=andrtoln&password=" + password;
		Connection con = DriverManager.getConnection(url);

		Statement stmt = com.createStatement();
		stmt.executeQuery("SELECT * FROM person ORDER BY fornavn");
		ResultSet res = start.executeQuery();

		while(res.next()){
			System.out.println("Personnummer = " + res.getInt("persnr"));
			System.out.println("Etternavn = " + res.getString("etternavn"));
		}



	}
}