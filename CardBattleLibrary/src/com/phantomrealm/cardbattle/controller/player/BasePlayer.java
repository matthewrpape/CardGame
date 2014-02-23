package com.phantomrealm.cardbattle.controller.player;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;

/**
 * Abstract class which provides the basic functionality shared by all player types.
 * 
 * @author matthewpape
 */
public abstract class BasePlayer implements Player {

	protected PlayerIdentity mIdentity;
	protected Deck mDeck;
	
	/**
	 * Creates a base player with a given identity and deck
	 * @param identity
	 * @param deck
	 */
	public BasePlayer(PlayerIdentity identity, Deck deck) {
		mIdentity = identity;
		mDeck = deck;
	}
	
	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getIdentity()
	 */
	@Override
	public PlayerIdentity getIdentity() {
		return mIdentity;
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getDeck()
	 */
	@Override
	public Deck getDeck() {
		return mDeck;
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getMove(com.phantomrealm.cardbattle.model.board.Board, com.phantomrealm.cardbattle.model.deck.Deck)
	 */
	@Override
	public abstract Position getMove(Board board, Deck opponentDeck);

}
