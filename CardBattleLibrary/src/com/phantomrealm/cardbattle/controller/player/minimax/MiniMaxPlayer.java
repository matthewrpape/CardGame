package com.phantomrealm.cardbattle.controller.player.minimax;

import com.phantomrealm.cardbattle.controller.player.BasePlayer;
import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.deck.Deck;

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
	
	/**
	 * Gets the depth to be used for the minimax algorithm
	 * @return
	 */
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

	/**
	 * Returns the rank of the worst possible move that can be made, accounting for
	 *  alternatively calling maximize and minimize until depth has been reached
	 * @param board
	 * @param playerDeck
	 * @param opponentDeck
	 * @param depth
	 * @return
	 */
	private int minimize(Board board, Deck playerDeck, Deck opponentDeck, int depth) {
		if (depth == 0) {
			return evaluate(board);
		} else if (opponentDeck.isEmpty()) {
			return evaluateWin(board);
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
	
	/**
	 * Returns the rank of the best possible move that can be made, accounting for
	 *  alternatively calling minimize and maximize until depth has been reached
	 * @param board
	 * @param playerDeck
	 * @param opponentDeck
	 * @param depth
	 * @return
	 */
	private int maximize(Board board, Deck playerDeck, Deck opponentDeck, int depth) {
		if (depth == 0) {
			return evaluate(board);
		} else if (playerDeck.isEmpty() ){
			return evaluateLoss(board);
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
	
	/**
	 * Returns a rank for the board after updating it, by placing the given card in the given position,
	 *  assigning the given player as the owner, and resolving conflicts and stalemates on the board.
	 * @param board
	 * @param card
	 * @param player
	 * @param move
	 * @return -1 represents an inconclusive rank and should be ignored.
	 */
	private int updateBoard(Board board, Card card, PlayerIdentity player, Position move) {
		board.executeMove(card, player, move);
		board.resolveBoardConflicts();
		board.resolveBoardStalemates();
		PlayerIdentity winner = board.getWinner();
		if (winner == getIdentity()) { 
			return evaluateWin(board);
		} else if (winner == PlayerIdentity.not(getIdentity())) {
			return evaluateLoss(board);
		} else {
			return -1;
		}
	}
	
	/**
	 * Gets a numerical ranking of how desirable a given board is. Note that this
	 *  rank should be positive and should be less than max int for the minimax
	 *  algorithm to work properly.
	 * @param board
	 * @return
	 */
	protected abstract int evaluate(Board board);
	
	/**
	 * Gets a numerical ranking of how desirable a given board in the particular
	 *  case where the player will have win. Default behavior is to evaluate the
	 *  same as any other case, but a different behavior can be achieved by
	 *  overriding this function (for instance to return a much higher rank).
	 * @param board
	 * @return
	 */
	protected int evaluateWin(Board board) {
		return evaluate(board);
	}
	
	/**
	 * Gets a numerical ranking of how desirable a given board in the particular
	 *  case where the player will have lost. Default behavior is to evaluate the
	 *  same as any other case, but a different behavior can be achieved by
	 *  overriding this function (for instance to return a much lower rank).
	 * @param board
	 * @return
	 */
	protected int evaluateLoss(Board board) {
		return evaluate(board);
	}

}
