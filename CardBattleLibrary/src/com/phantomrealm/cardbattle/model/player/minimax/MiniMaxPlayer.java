package com.phantomrealm.cardbattle.model.player.minimax;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.BasePlayer;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Abstract representation of a player who uses the minimax algorithm to pick its next move
 * 
 * @author matthewpape
 */
public abstract class MiniMaxPlayer extends BasePlayer {
	
	private static final int DEFAULT_DEPTH = 1;
	private int mDepth;
	
	/**
	 * Creates a new minimax player with a default depth
	 * @param identity
	 * @param deck
	 */
	public MiniMaxPlayer(PlayerIdentity identity, Deck deck) {
		this(identity, deck, DEFAULT_DEPTH);
	}
	
	/**
	 * Creates a new minimax player with a given depth
	 * @param identity
	 * @param deck
	 * @param depth must be >= 1
	 */
	public MiniMaxPlayer(PlayerIdentity identity, Deck deck, int depth) {
		super(identity, deck);
		mDepth = Math.max(depth, DEFAULT_DEPTH);
	}
	
	private int getDepth() {
		return mDepth;
	}

	/* (non-Javadoc)
	 * @see com.phantomrealm.cardbattle.model.player.BasePlayer#getMove(com.phantomrealm.cardbattle.model.board.Board, com.phantomrealm.cardbattle.model.deck.Deck)
	 */
	@Override
	public Position getMove(Board board, Deck opponentDeck) {
		Position bestMove = null;
		int bestRank = -1;
		for (Position move : board.getPossibleMoves(getIdentity())) {
			Board newBoard = board.clone();
			Deck newDeck = getDeck().clone();
			Card newCard = newDeck.pop();
			int rank = updateBoard(newBoard, newCard, getIdentity(), move);
			if (rank == -1) {
				rank = minimize(newBoard, newDeck, opponentDeck, getDepth() - 1);
			}
			if (rank > bestRank) {
				bestRank = rank;
				bestMove = move;
			}
		}
		return bestMove;
	}

	private int minimize(Board board, Deck playerDeck, Deck opponentDeck, int depth) {
		if (depth == 0) {
			return evaluate(board);
		} else if (opponentDeck.isEmpty()) {
			return Integer.MAX_VALUE - 1;
		} else {
			int worstRank = Integer.MAX_VALUE;
			PlayerIdentity opponent = PlayerIdentity.not(getIdentity());
			for (Position move : board.getPossibleMoves(opponent)) {
				Board newBoard = board.clone();
				Deck newDeck = opponentDeck.clone();
				Card newCard = newDeck.pop();
				int rank = updateBoard(newBoard, newCard, opponent, move);
				if (rank == -1) {
					rank = maximize(newBoard, playerDeck, newDeck, depth - 1);
				}
				if (rank < worstRank) {
					worstRank = rank;
				}
			}
			return worstRank;
		}
	}
	
	private int maximize(Board board, Deck playerDeck, Deck opponentDeck, int depth) {
		if (depth == 0) {
			return evaluate(board);
		} else if (playerDeck.isEmpty() ){
			return 0;
		} else {
			int bestRank = -1;
			for (Position move : board.getPossibleMoves(getIdentity())) {
				Board newBoard = board.clone();
				Deck newDeck = playerDeck.clone();
				Card newCard = newDeck.pop();
				int rank = updateBoard(newBoard, newCard, getIdentity(), move);
				if (rank == -1) {
					rank = minimize(newBoard, newDeck, opponentDeck, depth - 1);
				}
				if (rank > bestRank) {
					bestRank = rank;
				}
			}
			return bestRank;
		}
	}
	
	private int updateBoard(Board board, Card card, PlayerIdentity player, Position move) {
		board.executeMove(card, player, move);
		board.resolveBoardConflicts();
		board.resolveBoardStalemate();
		PlayerIdentity winner = board.getWinner();
		if (winner == getIdentity()) { 
			return Integer.MAX_VALUE - 1;
		} else if (winner == PlayerIdentity.not(getIdentity())) {
			return 0;
		} else {
			return -1;
		}
	}
	
	protected abstract int evaluate(Board board);

}
