/**
 *
 * MySQL
 * mysql.stud.idi.ntnu.no:3306
 * Databasenavn andrtoln
 * Kontonavn andrtoln
 * Passord GVAXiZju
 *
 */

package oving19;

import java.sql.*;

public class Database {

    private Connection con;
    private Statement st;
    private final String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
    private String databaseUrl;
    private String databasebaseNavn;
    private String databasePw;
    private ResultSet rs;
    private PreparedStatement preparedSt;

    public Database(String databasebaseUrl, String databasebaseNavn, String databasePw){
        this.databasePw = "&password=" + databasePw;
        this.databasebaseNavn = "/?user=" + databasebaseNavn;
        this.databaseUrl = databasebaseUrl + this.databasebaseNavn + this.databasePw;
        startConnection();
    }

    private void startConnection(){
        try{
            Class.forName(databaseDriver);
            con = DriverManager.getConnection(databaseUrl);
            st = con.createStatement();
        } catch(ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException thrown");
            cnfe.printStackTrace();
        } catch(SQLException sqle){
            System.out.println("SQLException thrown");
            sqle.printStackTrace();
        }
    }


    public void lukkForbindelse(){
        try{
            st.close();
            con.close();
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }



    }

    public boolean regNyBok(Bok nyBok){
        try{
            con.setAutoCommit(false);
            String boktittelSelectQuery = "SELECT isbn FROM boktittel";
            rs = st.executeQuery(boktittelSelectQuery);

            while(rs.next()){
                String isbn = rs.getString("isbn");
                if(isbn.equals(nyBok.getIsbn())){
                    System.out.println("Finnes bok med dette ISBN nmummeret");
                    //Popup?
                    rs.close();
                    con.commit();
                    return false;
                }
            }
            rs.close();
            con.commit();

            String boktittelInsertQuery = "insert into boktittel(isbn, forfatter, tittel) values(?, ?, ?)";
            preparedSt = con.prepareStatement(boktittelInsertQuery);
            preparedSt.setString(1, nyBok.getIsbn());
            preparedSt.setString(2, nyBok.getForfatter());
            preparedSt.setString(3, nyBok.getTittel());
            preparedSt.execute();

            String eksemplarInsertQuery = "insert into eksemplar(isbn, eks_nr) values (?, 1);";
            preparedSt = con.prepareStatement(eksemplarInsertQuery);
            preparedSt.setString(1, nyBok.getIsbn());
            preparedSt.execute();
            preparedSt.close();
            System.out.println("Boken " + nyBok.getTittel() + " ble registrert");
        } catch(SQLException sqle){
            Opprydder.rullTilbake(con);
            sqle.printStackTrace();
        } finally{
            Opprydder.lukkPStatement(preparedSt);
            Opprydder.setAutoCommit(con);
            Opprydder.lukkResSet(rs);
        }
        return true;
    }

    public int regNyttEksemplar(String isbn){
        String eksemplarSelectQuery = "SELECT MAX(eks_nr) FROM eksemplar";
        try{
            rs = st.executeQuery(eksemplarSelectQuery);
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }





        return 0;
    }

    public boolean laantUtEksemplar(String isbn, String navn, int eksNr){

        return false;
    }




}
