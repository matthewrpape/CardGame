package com.phantomrealm.cardbattle.model.player.algorithmic;

import java.util.List;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * A player who selects the first position of the possible choices for its next move
 * 
 * @author matthewpape
 */
public class OrderedPlayer extends AlgorithmicPlayer {

	public OrderedPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.algorithmic.AlgorithmicPlayer#generateMove()
	 */
	@Override
	protected Position generateMove(Board board) {
		List<Position> availableMoves = board.getPossibleMoves(getIdentity());
		return availableMoves.get(0);
	}

}
