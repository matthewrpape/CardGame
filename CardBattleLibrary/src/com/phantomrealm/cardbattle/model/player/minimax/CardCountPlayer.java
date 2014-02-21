package com.phantomrealm.cardbattle.model.player.minimax;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * A player who favors the move that results in having the most of their own cards on the board
 * 
 * @author matthewpape
 */
public class CardCountPlayer extends MiniMaxPlayer {

	/**
	 * Creates a CardCountPlayer with a default depth
	 * @param identity
	 * @param deck
	 */
	public CardCountPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}
	
	/**
	 * Creates a CardCountPlayer with a given depth
	 * @param identity
	 * @param deck
	 * @param depth
	 */
	public CardCountPlayer(PlayerIdentity identity, Deck deck, int depth) {
		super(identity, deck, depth);
	}

	@Override
	protected int evaluate(Board board) {
		int cards = 0;
		for (int row = 0; row < board.getHeight(); ++row) {
			for (int col = 0; col < board.getWidth(); ++col) {
				if (board.getBoardSlot(row, col).getOwner() == getIdentity()) {
					++cards;
				}
			}
		}
		return cards;
	}

}
