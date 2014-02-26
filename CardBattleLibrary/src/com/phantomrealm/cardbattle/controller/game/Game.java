package com.phantomrealm.cardbattle.controller.game;

import com.phantomrealm.cardbattle.controller.player.Player;
import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;

/**
 * Controller class that implements the logic necessary to play the card game
 * 
 * @author matthewpape
 */
public class Game {

	/**
	 * Used to build a Game instance
	 * 
	 * @author matthewpape
	 */
	public static class Builder {
		
		private Player mLeftPlayer;
		private Player mRightPlayer;
		private Board mBoard;
		private GameControllerListener mListener;
		
		public Builder(Player leftPlayer, Player rightPlayer, Board board) {
			mLeftPlayer = leftPlayer;
			mRightPlayer = rightPlayer;
			mBoard = board;
		}
		
		public Builder setGameControllerListener(GameControllerListener listener) {
			mListener = listener;
			return this;
		}
		
		public Game build() {
			Game game = new Game(mLeftPlayer, mRightPlayer, mBoard);
			game.setGameControllerListener(mListener);
			return game;
		}
		
	}
	
	private Player mLeftPlayer;
	private Player mRightPlayer;
	private Board mBoard;
	private PlayerIdentity mCurrentPlayer;
	
	/**
	 * Creates a game instance with the given players and board
	 * @param leftPlayer must have identity set as LEFT_PLAYER
	 * @param rightPlayer must have identity set as RIGHT_PLAYER
	 * @param board
	 */
	private Game(Player leftPlayer, Player rightPlayer, Board board){
		mLeftPlayer = leftPlayer;
		mRightPlayer = rightPlayer;
		mBoard = board;
		mCurrentPlayer = PlayerIdentity.LEFT_PLAYER;
	}
	
	/**
	 * Sets the listener to be called on specific game events
	 * @param listener
	 */
	public void setGameControllerListener(GameControllerListener listener) {
		mBoard.setGameControllerListener(listener != null ? listener : new EmptyGameControllerListener());
	}

	/**
	 * The first step in each round of play.
	 *  Prompt the next player to move their next card onto the board
	 */
	public void takePlayerTurn() {
		// determine which player is taking a turn
		Player currentPlayer = mCurrentPlayer == PlayerIdentity.LEFT_PLAYER ? mLeftPlayer : mRightPlayer;
		Player opponent = mCurrentPlayer == PlayerIdentity.LEFT_PLAYER ? mRightPlayer : mLeftPlayer;

		// get the desired move from the player
		Position currentMove = currentPlayer.getMove(mBoard, opponent.getDeck());

		// execute the desired move on behalf of the player
		mBoard.executeMove(currentPlayer.getDeck().pop(), currentPlayer.getIdentity(), currentMove);
		
		// ensure the correct player will receive the next turn
		mCurrentPlayer = PlayerIdentity.not(mCurrentPlayer);
	}
	
	/**
	 * The second step in each round of play
	 *  Resolve all conflicts and, if necessary, stalemates on the board
	 */
	public void resolveBoard() {
		mBoard.resolveBoardConflicts();
		mBoard.resolveBoardStalemates();
	}
	
	/**
	 *  The third and final step of each round of play
	 *   Return the player who has won the game, either by controlling a majority
	 *   of rows or because their opponent is unable to take their turn because
	 *   they are out of cards. Returns null if no one has won yet.
	 * @return
	 */
	public PlayerIdentity getWinner() {
		// see if anyone controls a majority of the rows
		PlayerIdentity winner = mBoard.getWinner();
		
		if (winner == null) {
			// see if the next player is out of cards, and therefore forfeits
			Player nextPlayer = (mCurrentPlayer == PlayerIdentity.LEFT_PLAYER ? mLeftPlayer : mRightPlayer);
			if (nextPlayer.getDeck().isEmpty()) {
				winner = PlayerIdentity.not(mCurrentPlayer);
			}
		}
		
		return winner;
	}
	
}
