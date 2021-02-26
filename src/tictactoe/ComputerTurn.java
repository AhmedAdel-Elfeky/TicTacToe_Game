/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author eslam
 */
// Java program to find the 
// next optimal move for a player

public class ComputerTurn
{
	static final String player = "X";
	static final String opponent = "O";
	
	class Move
	{
		int row; 
		int col; 
	};


	private static Boolean isMovesLeft(String board[][])
	{
		for (int rowCntr = 0; rowCntr < 3; rowCntr++)
		{
			for (int colCntr = 0; colCntr < 3; colCntr++)
			{
				if (board[rowCntr][colCntr] == "")
					return true;
			}
		}
		return false;
	}

	/**********************************************************
		This is the evaluation function as discussed
	    in the previous article ( http://goo.gl/sJgv68 )
	**********************************************************/
	public static int evaluate(String board[][])
	{
		// Checking for Rows for X or O victory.
		for (int rowCntr = 0; rowCntr < 3; rowCntr++)
		{
			if (board[rowCntr][0] ==board[rowCntr][1] &&
				board[rowCntr][1] == board[rowCntr][2])
			{
				if (board[rowCntr][0] == player)
					return +10;
				else if (board[rowCntr][0] == opponent)
					return -10;
			}
		}

		// Checking for Columns for X or O victory.
		for (int colCntr = 0; colCntr < 3; colCntr++)
		{
			if (board[0][colCntr] == board[1][colCntr] &&
				board[1][colCntr] == board[2][colCntr])
			{
				if (board[0][colCntr] == player)
					return +10;
				
				else if (board[0][colCntr] == opponent)
					return -10;
			}
		}

		// Checking for Diagonals for X or O victory.
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
		{
			if (board[0][0] == player)
				return +10;
			else if (board[0][0] == opponent)
				return -10;
		}
 
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
		{
			if (board[0][2] == player)
				return +10;
			else if (board[0][2] == opponent)
				return -10;
		}

		// Else if none of them have won then return 0
		return 0;
	}

	/*******************************************************************
		This is the minimax function. It considers all
		the possible ways the game can go and returns
		the value of the board
	*******************************************************************/
	private static int minimax(String board[][],int depth, Boolean isMax)
	{
		int score = evaluate(board);
		if (score == 10)
			return score;

		// If Minimizer has won the game 
		// return his/her evaluated score
		if (score == -10)
			return score;

		// If there are no more moves and 
		// no winner then it is a tie
		if (isMovesLeft(board) == false)
			return 0;

		// If this maximizer's move
		if (isMax)
		{
			int best = -1000;

			// Traverse all cells
			for (int rowCntr = 0; rowCntr < 3; rowCntr++)
			{
				for (int colCntr = 0; colCntr < 3; colCntr++)
				{
					// Check if cell is empty
					if (board[rowCntr][colCntr]=="")
					{
						// Make the move
						board[rowCntr][colCntr] = player;

						// Call minimax recursively and choose
						// the maximum value
						best = Math.max(best, minimax(board, 
										depth + 1, !isMax));

						// Undo the move
						board[rowCntr][colCntr] = "";
					}
				}
			}
			return best;
		}

		// If this minimizer's move
		else
		{
			int best = 1000;

			// Traverse all cells
			for (int rowCntr = 0; rowCntr < 3; rowCntr++)
			{
				for (int colCntr = 0; colCntr < 3; colCntr++)
				{
					// Check if cell is empty
					if (board[rowCntr][colCntr] == "")
					{
						// Make the move
						board[rowCntr][colCntr] = opponent;

						// Call minimax recursively and choose
						// the minimum value
						best = Math.min(best, minimax(board, 
										depth + 1, !isMax));

						// Undo the move
						board[rowCntr][colCntr] = "";
					}
				}
			}
			return best;
		}
	}

	/*****************************************************
		This will return the best possible
	    move for the player
	*****************************************************/
	public Move findBestMove(String board[][])
	{
		int bestVal = -1000;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;

		// Traverse all cells, evaluate minimax function 
		// for all empty cells. And return the cell 
		// with optimal value.
		for (int rowCntr = 0; rowCntr < 3; rowCntr++)
		{
			for (int colCntr = 0; colCntr < 3; colCntr++)
			{
				// Check if cell is empty
				if (board[rowCntr][colCntr] == "")
				{
					// Make the move
					board[rowCntr][colCntr] = opponent;

					// compute evaluation function for this
					// move.
					int moveVal = minimax(board,7, false);

					// Undo the move
					board[rowCntr][colCntr] = "";

					// If the value of the current move is
					// more than the best value, then update
					// best/
					if (moveVal > bestVal)
					{
						bestMove.row = rowCntr;
						bestMove.col = colCntr;
						bestVal = moveVal;
					}
				}
			}
		}
		return bestMove;
	}

}

