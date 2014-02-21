package com.phantomrealm.cardbattle.model.player.minimax;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * A player who favors the move that results in the highest disparity between the number of
 *  their own cards on the board and opponents cards on the board.
 * 
 * @author matthewpape
 */
public class CardCountDisparityPlayer extends MiniMaxPlayer {

	/**
	 * Creates a CardCountDisparityPlayer with a default depth
	 * @param identity
	 * @param deck
	 */
	public CardCountDisparityPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}
	
	/**
	 * Creates a CardCountDisparityPlayer with a given depth
	 * @param identity
	 * @param deck
	 * @param depth
	 */
	public CardCountDisparityPlayer(PlayerIdentity identity, Deck deck, int depth) {
		super(identity, deck, depth);
	}
	
	@Override
	protected int evaluate(Board board) {
		int rank = 1 + board.getHeight() * board.getWidth();
		for (int row = 0; row < board.getHeight(); ++row) {
			for (int col = 0; col < board.getWidth(); ++col) {
				PlayerIdentity owner = board.getBoardSlot(row, col).getOwner();
				if (owner == getIdentity()) {
					++rank;
				} else if (owner == (PlayerIdentity.not(getIdentity()))) {
					--rank;
				}
			}
		}
		return rank;
	}

}
