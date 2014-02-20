package com.phantomrealm.cardbattle.controller.game;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.player.Player;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

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
	
	private Game(Player leftPlayer, Player rightPlayer, Board board){
		mLeftPlayer = leftPlayer;
		mRightPlayer = rightPlayer;
		mBoard = board;
		mCurrentPlayer = PlayerIdentity.LEFT_PLAYER;
	}
	
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

		// get the desired move from the player
		Position currentMove = currentPlayer.getMove(mBoard);

		// execute the desired move on behalf of the player
		mBoard.executeMove(currentPlayer.getDeck().pop(), currentPlayer.getIdentity(), currentMove);
		
		// ensure the correct player will receive the next turn
		mCurrentPlayer = mCurrentPlayer == PlayerIdentity.LEFT_PLAYER ? PlayerIdentity.RIGHT_PLAYER : PlayerIdentity.LEFT_PLAYER;
	}
	
	/**
	 * The second step in each round of play
	 *  Resolve all conflicts and, if necessary, stalemates on the board
	 */
	public void resolveBoard() {
		mBoard.resolveBoardConflicts();
		mBoard.resolveBoardStalemate();
	}
	
	/**
	 * The third and final step of each round of play
	 *  Return the player who has won the game, either by controlling a majority
	 *  of rows or by being the last player left with cards in their deck, or
	 *  null if no player has yet won.
	 * @return
	 */
	public PlayerIdentity getWinner() {
		// see if anyone controls a majority of the rows
		PlayerIdentity winner = mBoard.getWinner();
		
		if (winner == null) {
			// see if anyone lost be running out of cards
			if (mLeftPlayer.getDeck().isEmpty()) {
				winner = PlayerIdentity.RIGHT_PLAYER;
			} else if (mRightPlayer.getDeck().isEmpty()) {
				winner = PlayerIdentity.LEFT_PLAYER;
			}
		}
		
		return winner;
	}
	
}
