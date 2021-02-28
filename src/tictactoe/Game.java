/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author eslam
 */
public class Game {

    public int gameId;
    //public Instant game_date;
    LocalDate gameDate ;
    public int playerOneId;
    public int playerwoId;
    public volatile  int symbolSwitch = 0;
    public volatile String [][]gameBoard = {{"", "", ""},
    {"", "", ""},
    {"", "", ""}};
    boolean firstPlay = true;
    String record = "";
    public  static ComputerTurn ai ;
    

    public Game(int firstPlayer,int secondPlayer) {
        gameDate = LocalDate.now(); 
        playerOneId = firstPlayer;
        playerwoId = secondPlayer;
    }
    
    public  static int  findBestMove(String [][]gb)
    {
        ai = new ComputerTurn();
        
        ComputerTurn.Move bestMove = ai.findBestMove(gb);
        
        return ((bestMove.row*3)+bestMove.col);
    }
    public String getGameDate()
    {
        return gameDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     *
     * @param gameBoard
     * @return
     */
    public Pair<Integer, Integer> getRandomMove() {
        List<Pair<Integer, Integer>> avlCells = new ArrayList<Pair<Integer, Integer>>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == "") {
                    avlCells.add(new Pair<>(i, j));
                }
            }
        }
        return avlCells.get(new Random().nextInt(avlCells.size()));
    }

    //returns the winner sympol or DRAW 
    public String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0].equals(gameBoard[i][1]) && gameBoard[i][0].equals(gameBoard[i][2]) && !gameBoard[i][0].equals("")) {
                return gameBoard[i][0];
            }
            if (gameBoard[0][i].equals(gameBoard[1][i]) && gameBoard[0][i].equals(gameBoard[2][i]) && !gameBoard[0][i].equals("")) {
                return gameBoard[0][i];
            }
        }
        if (gameBoard[0][0].equals( gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2]) &&  !gameBoard[0][0].equals("")) {
            return gameBoard[0][0];
        }
        if (gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals(gameBoard[2][0]) &&  !gameBoard[0][2].equals("")) {
            return gameBoard[0][2];
        }

        int avl_places = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j].equals("")) {
                    avl_places++;
                }

            }
        }
        if (avl_places == 0) {
            return "d";
        }
        return "_";
    }
}
