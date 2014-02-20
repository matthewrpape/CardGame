package com.phantomrealm.cardbattle.model.player;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;

/**
 * Interface that must be implemented by any AI or human controlled player in
 *  order to participate in the game
 * 
 * @author matthewpape
 */
public interface Player {

	/**
	 * Get the identity of the given player (left or right)
	 * @return
	 */
	public PlayerIdentity getIdentity();
	
	/**
	 * Get the deck of the given player
	 * @return
	 */
	public Deck getDeck();
	
	/**
	 * Get the position on the board where the player wishes to place his next card
	 * @param board
	 * @param opponentDeck
	 * @return
	 */
	public Position getMove(Board board, Deck opponentDeck);
	
}
