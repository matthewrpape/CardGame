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
	
	public AlgorithmicPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
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
