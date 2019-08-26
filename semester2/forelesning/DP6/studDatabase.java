import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;

/*

studnr, nasvn, adresse, postnr, poststed, fagkode, fagnavn, karakter (kun en karakter pr. student og fag)

*/


class studDatabase {
	public static void main(String[]args){
		Scanner s = new Scanner(System.in);
	    String password = s.nextLine();
	    String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/andrtoln?user=andrtoln&password=" + password;


	    try(Connection con = DriverManager.getConnection(url)) {

	           Statement stmt = con.createStatement();
	           ResultSet res = stmt.executeQuery("SELECT * FROM person ORDER BY fornavn");

	           s.close();
	       } catch(SQLException sql){
	           System.out.println("SQL feil" + sql);
	           sql.printStackTrace();
	       }



	}
	


}