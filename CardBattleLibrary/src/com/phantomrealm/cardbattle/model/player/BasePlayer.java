/**
 * 
 */
package com.phantomrealm.cardbattle.model.player;

import java.util.ArrayList;
import java.util.List;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.deck.Deck;

/**
 * @author mpape
 *
 */
public abstract class BasePlayer implements Player {

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getIdentity()
	 */
	@Override
	public PlayerIdentity getIdentity() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getDeck()
	 */
	@Override
	public Deck getDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.Player#getMove(com.phantomrealm.cardbattle.model.board.Board, com.phantomrealm.cardbattle.model.deck.Deck)
	 */
	@Override
	public abstract Position getMove(Board board, Deck opponentDeck);
	
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
