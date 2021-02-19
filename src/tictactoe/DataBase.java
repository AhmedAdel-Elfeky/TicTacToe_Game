/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.sql.*;

public class DataBase {

    Connection c = null;
    Statement stmt;
    ResultSet rs;
    PreparedStatement ls;
    PreparedStatement dr;
    PreparedStatement ur;
    final static String URL = "jdbc:postgresql://localhost:5432/tic_tac_toe";

    public DataBase() {
        try {
            c = DriverManager.getConnection(URL, "postgres", "postgres");
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
            statment = c.prepareStatement(" select player_id , player_name d , password from players where player_name= ? and password = ?; ",
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

            statment = c.prepareStatement(" select count(player_name) counter from players where player_name= ?;",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setString(1, name);
            result = statment.executeQuery();

            if (result.next()) {
                if (result.getInt(1) == 0) {
                    statment = c.prepareStatement("insert into players (player_name,password) values (?,?)",
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
    
}
