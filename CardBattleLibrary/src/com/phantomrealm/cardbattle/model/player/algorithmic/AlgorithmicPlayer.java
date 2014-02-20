package com.phantomrealm.cardbattle.model.player.algorithmic;

import java.util.ArrayList;
import java.util.List;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.Player;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Abstract representation of a player who uses an algorithm to pick its next move
 * 
 * @author matthewpape
 */
public abstract class AlgorithmicPlayer implements Player {

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
	public Position getMove(Board board) {
		return generateMove(board);
	}

	/**
	 * Algorithmically generate a position to play the next card
	 * @return
	 */
	protected abstract Position generateMove(Board board);
	
	/**
	 * Get a list of all the possible positions where the next card could be placed
	 * 
	 * @param board
	 * @return a list (possibly empty but never null)
	 */
	protected List<Position> getPossibleMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
		int begin = getIdentity() == PlayerIdentity.LEFT_PLAYER ? 0 : board.getWidth() - 1;
		int end = getIdentity() == PlayerIdentity.LEFT_PLAYER ? board.getWidth() - 1 : 0;
		int increment = getIdentity() == PlayerIdentity.LEFT_PLAYER ? 1 : -1;
		for (int row = 0; row < board.getHeight(); ++row) {
			for (int col = begin; getIdentity() == PlayerIdentity.LEFT_PLAYER ? col <= end : col >= end; col += increment) {
				if (board.getBoardSlot(row, col).getOwner() == null) {
					moves.add(new Position(row, col));
					break;
				}
			}
		}
		return moves;
	}
		
}
