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

    public PlayerInformation checkLoginUser(String name, String pass) {

        ResultSet result;
        PreparedStatement statment;
        int player_id;
        PlayerInformation inf=new PlayerInformation();
        try {
            statment = c.prepareStatement(" select player_id , player_name d , password , avatar_id from players where player_name= ? and password = ?; ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setString(1, name);
            statment.setString(2, pass);
            result = statment.executeQuery();
            if (result.next()) {
                inf.playerId = result.getInt(1);
                inf.playerName = result.getString(2);
                inf.playerAvatar = result.getInt(4);
                statment.close();
                return inf;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
         inf.playerId = -1;
        return inf;
    }

    public PlayerInformation RegisterNewUser(String name, String mail,String pass, int avatarId) {
        ResultSet result;
        PreparedStatement statment;
        PlayerInformation pInfo = new PlayerInformation();
        try {

            statment = c.prepareStatement(" select count(player_name) counter from players where player_name= ?;",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setString(1, name);
            result = statment.executeQuery();
             pInfo.playerId=-1;
            if (result.next()) {
                if (result.getInt(1) == 0) {
                    statment = c.prepareStatement("insert into players (player_name,password,avatar_id,email) values (?,?,?,?)",
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                    statment.setString(1, name);
                    statment.setString(2, pass);
                    statment.setInt(3, avatarId);
                    statment.setString(4, mail);
                    statment.executeUpdate();
                    pInfo.playerName = name;
                    pInfo.playerAvatar = avatarId;
                    statment = c.prepareStatement(" select player_id counter from players where player_name= ?;",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                    statment.setString(1, name);
                    result = statment.executeQuery();
                    result.next();
                    pInfo.playerId = result.getInt(1);
                    
                    statment.close();
                    return pInfo;
                }
            } else {
                statment.close();
                return pInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return pInfo;
    }
    
    public String getSavedGames(int id)
    {
        ResultSet result;
        PreparedStatement statment;
        String matchList="";
        try {
            statment = c.prepareStatement(" select match_id  from play where playeroneid = ? and saved = true; ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setInt(1, id);
            
            result = statment.executeQuery();
            while (result.next()) {
                matchList += result.getInt(1)+"_";
            }
            statment.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return matchList;
    
    }

    public String getUsername(int id )
    {
        ResultSet result;
        PreparedStatement statment;
        String uname;
        try {
            statment = c.prepareStatement(" select player_name from players where player_id = ?; ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statment.setInt(1, id);
            
            result = statment.executeQuery();
            if (result.next()) {
                uname = result.getString(1);
                statment.close();
                return uname;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return "";
    }
    
    public int addNewGame(String gameDate )
    {
        ResultSet result;
        PreparedStatement statment;
        int id=-1;
        try {
                Date gDate=Date.valueOf(gameDate);//converting string into sql date 
                statment = c.prepareStatement("insert into matches (match_date) values (?)",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
               
                statment.setDate(1, gDate);
                statment.executeUpdate();
                
                statment = c.prepareStatement("select max(match_id) from matches;",
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
    
    public String simulate(int id) {
        ResultSet result;
        PreparedStatement statment;
        try {
   
            statment = c.prepareStatement("select moves from matches where match_id = ?");
            statment.setInt(1, id);
            result = statment.executeQuery();
            //System.out.println(result.getString(1));
            //sim.close();
            result.next();
            statment.close();
            System.err.println("database" + result.getString(1));
            return result.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return "null";
    }

    public void saveMatch(int id, String record) {
        PreparedStatement statment;
        try {           
            statment = c.prepareStatement("update matches set moves = ? where match_id = ?");
            statment.setString(1, record);
            statment.setInt(2, id);
            statment.executeUpdate();
            statment.close();
            //System.err.println("database" + result.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void savedGameResult(int matchID, int playerID, String winOrLoseOrDraw, boolean saved, char symbol) {
        try {
            ResultSet result;
            Statement stmt = c.createStatement();
            String queryString;
            if (winOrLoseOrDraw == "d") {
                queryString = new String("insert into play values('"+playerID+"','"+matchID+"', 'd','"+symbol+"', '"+saved+"')");
            }
            else if(winOrLoseOrDraw == "w") {
                queryString = new String("insert into play values('"+playerID+"','"+matchID+"', 'w','"+symbol+"', '"+saved+"')");
            }
            else{
                queryString = new String("insert into play values('"+playerID+"','"+matchID+"', 'l','"+symbol+"', '"+saved+"')");
            }
            stmt.executeUpdate(queryString);
            stmt.close();
            //System.err.println("database" + result.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public int getMatches(int id , String b)
    {
       
        ResultSet result;
        PreparedStatement statment;
        int n;
        try {
            if(b.equals("w"))
            {
                statment = c.prepareStatement(" select count(playeroneid) from play  where match_result = 'w' and playeroneid =?; ",
                              ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            }
            else if (b.equals("l"))
            {
                statment = c.prepareStatement(" select count(playeroneid) from play  where match_result = 'l' and playeroneid =?; ",
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            } 
            else 
            {
                statment = c.prepareStatement(" select count(playeroneid) from play  where match_result = 'd' and playeroneid =?; ",
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            }
            

            statment.setInt(1, id);
         
            result = statment.executeQuery();
            if (result.next()) {
                n = result.getInt(1);
                statment.close();
                return n;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return -1;
    }
    
    
    
}
