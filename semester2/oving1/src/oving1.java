import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;

class Database{

   public ArrayList<Person> getAllPersons(){
       ArrayList persons = new ArrayList();
       Scanner s = new Scanner(System.in);
       String password = s.nextLine();
       String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/andrtoln?user=andrtoln&password=" + password;

       try(Connection con = DriverManager.getConnection(url)) {

           Statement stmt = con.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM person ORDER BY fornavn");

           while (res.next()) {
               Person pers = new Person(res.getString("fornavn"), res.getString("etternavn"), res.getInt("persNr"));
               persons.add(pers);
           }

           s.close();
       } catch(SQLException sql){
           System.out.println("SQL feil" + sql);
           sql.printStackTrace();
       }

        return persons;
   }


    public static void main(String[]args){
        Database dat = new Database();
        System.out.println("Start");
        ArrayList<Person> pers = dat.getAllPersons();

    }
}

class Person{

    private String fornavn;
    private String etternavn;
    private int persNr;

    public Person(String fornavn, String etternavn, int persNr) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.persNr = persNr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public int getPersNr() {
        return persNr;
    }

    public static void main(String[]args){
        System.out.println("Test");
    }

}