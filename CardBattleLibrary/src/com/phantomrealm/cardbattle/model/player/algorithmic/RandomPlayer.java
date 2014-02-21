package com.phantomrealm.cardbattle.model.player.algorithmic;

import java.util.List;
import java.util.Random;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * A player who selects a psuedo-random position of the possible choices for its next move
 * 
 * @author matthewpape
 */
public class RandomPlayer extends AlgorithmicPlayer {

	/**
	 * Creates a RandomPlayer with a given identity and deck
	 * @param identity
	 * @param deck
	 */
	public RandomPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.algorithmic.AlgorithmicPlayer#generateMove()
	 */
	@Override
	protected Position generateMove(Board board) {
		List<Position> availableMoves = board.getPossibleMoves(getIdentity());
		Random randomGenerator = new Random();
		return availableMoves.get(randomGenerator.nextInt(availableMoves.size()));
	}

}
