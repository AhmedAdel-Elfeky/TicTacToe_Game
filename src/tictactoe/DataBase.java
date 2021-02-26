/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.sql.*;
import java.time.LocalDate;

public class DataBase {

    Connection c = null;
    Statement stmt;
    ResultSet rs;
    PreparedStatement ls;
    PreparedStatement dr;
    PreparedStatement ur;
    final static String URL = "jdbc:postgresql://localhost:5432/tictac";

    public DataBase() {
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tictac", "postgres", "lilhack");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                c.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public int checkLoginUser(String name, String pass) {

        ResultSet result;
        PreparedStatement statment;
        int player_id;
        try {
            statment = c.prepareStatement(" select id , name  , pass from player where name= ? and pass = ?; ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setString(1, name);
            statment.setString(2, pass);
            result = statment.executeQuery();
            if (result.next()) {
                player_id = result.getInt(1);
                statment.close();
                return player_id;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return -1;
    }

    public int RegisterNewUser(String name, String pass, int avatarId) {
        ResultSet result;
        PreparedStatement statment;
        int player_id;
        try {

            statment = c.prepareStatement(" select count(name) from player where name= ?;",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setString(1, name);
            result = statment.executeQuery();

            if (result.next()) {
                if (result.getInt(1) == 0) {
                    statment = c.prepareStatement("insert into player (name,pass) values (?,?)",
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                    statment.setString(1, name);
                    statment.setString(2, pass);
                    statment.executeUpdate();
                    statment.close();
                    return 1;
                }
            } else {
                statment.close();
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }
    
    public int addNewGame(String gameDate )
    {
        ResultSet result;
        PreparedStatement statment;
        int id=-1;
        try {
                Date gDate=Date.valueOf(gameDate);//converting string into sql date 
                statment = c.prepareStatement("insert into match (mdate) values (?)",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
               
                statment.setDate(1, gDate);
                statment.executeUpdate();
                
                statment = c.prepareStatement("select max(match_id) from match;",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                result = statment.executeQuery();
                while ( result.next() )
                {
                     id = result.getInt(1);
                }
                statment.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return id;
        
    }
    
}
