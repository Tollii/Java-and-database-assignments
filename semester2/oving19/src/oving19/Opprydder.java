package oving19;

import java.sql.*;

public class Opprydder {

    public static void lukkResSet(ResultSet rs){
        try {
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    public static void lukkSetning(Statement st){
        try{
            if(st != null && st.isClosed()){
                st.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void lukkConnection(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void setAutoCommit(Connection con){
        try{
            if(con != null && !con.isClosed() && !con.getAutoCommit()){
                con.setAutoCommit(true);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void rullTilbake(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.rollback();
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();

        }
    }

    public static void lukkPStatement(PreparedStatement ps){
        try{
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }


}
