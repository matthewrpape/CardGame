package com.phantomrealm.cardbattle.model.player.algorithmic;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.BasePlayer;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Abstract representation of a player who uses an algorithm to pick its next move
 * 
 * @author matthewpape
 */
public abstract class AlgorithmicPlayer extends BasePlayer {

	protected PlayerIdentity mIdentity;
	protected Deck mDeck;
	
	public AlgorithmicPlayer(PlayerIdentity identity, Deck deck) {
		mIdentity = identity;
		mDeck = deck;
	}
	
	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getDeck()
	 */
	@Override
	public Deck getDeck() {
		return mDeck;
	}
	
	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getIdentity()
	 */
	@Override
	public PlayerIdentity getIdentity() {
		return mIdentity;
	}
	
	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getMove(com.phantomrealm.cardbattle.model.board.Board)
	 */
	@Override
	public Position getMove(Board board, Deck opponentDeck) {
		return generateMove(board);
	}

	/**
	 * Algorithmically generate a position to play the next card
	 * @return
	 */
	protected abstract Position generateMove(Board board);
		
}
