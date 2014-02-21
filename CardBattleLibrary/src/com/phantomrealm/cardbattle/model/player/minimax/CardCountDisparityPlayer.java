package com.phantomrealm.cardbattle.model.player.minimax;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

public class CardCountDisparityPlayer extends MiniMaxPlayer {

	public CardCountDisparityPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	} 
	
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
