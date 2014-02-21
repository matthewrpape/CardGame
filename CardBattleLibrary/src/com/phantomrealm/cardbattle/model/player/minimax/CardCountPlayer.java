package com.phantomrealm.cardbattle.model.player.minimax;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

public class CardCountPlayer extends MiniMaxPlayer {

	public CardCountPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}
	
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
