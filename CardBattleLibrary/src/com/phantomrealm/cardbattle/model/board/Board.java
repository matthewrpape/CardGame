package com.phantomrealm.cardbattle.model.board;

import java.util.ArrayList;
import java.util.List;

import com.phantomrealm.cardbattle.controller.game.EmptyGameControllerListener;
import com.phantomrealm.cardbattle.controller.game.GameControllerListener;
import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Model object used to represent the board on which the game is played
 * 
 * @author matthewpape
 */
public class Board {

	private static final int DEFAULT_BOARD_WIDTH = 5;
	private static final int DEFAULT_BOARD_HEIGHT = 5;
	private static final int MIN_BOARD_WIDTH = 2;
	private static final int MIN_BOARD_HEIGHT = 1;
	
	private GameControllerListener mListener;
	private BoardSlot[][] mBoardSlots;
	
	/**
	 * Create a board of default size (5x5)
	 */
	public Board() {
		this(DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
	}
	
	/**
	 * Creates a board of given size. Width may be no less than 2. Height may be no less
	 *  than 1. It should be noted than boards with excessively large area will result in
	 *  opponents decking out rather than winning through standard means. This is highly
	 *  unfair to the player who begins, and so such boards should be avoided.
	 * @param boardWidth minimum of 2
	 * @param boardHeight minimum of 1
	 */
	public Board(int boardWidth, int boardHeight) {
		mListener = new EmptyGameControllerListener();
		boardWidth = Math.max(boardWidth, MIN_BOARD_WIDTH);
		boardHeight = Math.max(boardWidth, MIN_BOARD_HEIGHT);
		mBoardSlots = new BoardSlot[boardHeight][boardWidth];
		for (int row = 0; row < boardHeight; ++row) {
			for (int col = 0; col < boardWidth; ++col) {
				mBoardSlots[row][col] = new BoardSlot();
			}
		}
	}
	
	/**
	 * Creates a copy of an existing board
	 */
	public Board clone() {
		int boardWidth = getWidth();
		int boardHeight = getHeight();
		Board clonedBoard = new Board(boardWidth, boardHeight);
		for (int row = 0; row < boardHeight; ++row) {
			for (int col = 0; col < boardWidth; ++col) {
				BoardSlot boardSlot = getBoardSlot(row, col);
				BoardSlot clonedSlot = clonedBoard.getBoardSlot(row, col);
				clonedSlot.setSlotOwner(boardSlot.getOwner());
				Card card = boardSlot.getCard();
				if (card != null) {
				 clonedSlot.setCard(card.clone());
				}
			}
		}
		return clonedBoard;
	}
	
	/**
	 * Sets the listener to be called on specific game events
	 * @param listener
	 */
	public void setGameControllerListener(GameControllerListener listener) {
		mListener = listener != null ? listener : new EmptyGameControllerListener();
	}

	
	/**
	 * Returns the width of the board
	 * @return
	 */
	public int getWidth() {
		return mBoardSlots[0].length;
	}
	
	/**
	 * Returns the height of the board
	 * @return
	 */
	public int getHeight() {
		return mBoardSlots.length;
	}
	
	/**
	 * Retrieves the given row of BoardSlots
	 * @param row
	 * @return
	 */
	public BoardSlot[] getBoardRow(int row) {
		return mBoardSlots[row];
	}
	
	/**
	 * Retrieves the BoardSlot at the given position
	 * @param row
	 * @param column
	 * @return
	 */
	public BoardSlot getBoardSlot(int row, int column) {
		return mBoardSlots[row][column];
	}
	
	/**
	 * Gets the (x, y) coordinates of a particular BoardSlot
	 * @param boardSlot
	 * @return null in the event that the given boardslot was not found
	 */
	public Position getPosition(BoardSlot boardSlot) {
		for (int row = 0; row < mBoardSlots.length; ++row) {
			for (int col = 0; col < getBoardRow(row).length; ++col) {
				if (mBoardSlots[row][col] == boardSlot) {
					return new Position(row, col);
				}
			}
		}
		return null;
	}
	
	/**
	 * Checks if every single slot on the board is owned
	 * @return
	 */
	public boolean isFull() {
		for (int row = 0; row < mBoardSlots.length; ++row) {
			for (int col = 0; col < getBoardRow(row).length; ++col) {
				if (getBoardSlot(row, col).getOwner() == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Updates the board by placing a given card from a given player in the given position,
	 *  signaling the callback for a card being moved.
	 * @param player
	 * @param move
	 */
	public void executeMove(Card card, PlayerIdentity cardOwner, Position move) {
		if (move == null) { 
			System.out.println("NULL MOVE");
		}
		mListener.onCardMoved(cardOwner, move);
		BoardSlot position = getBoardSlot(move.getRow(), move.getColumn());
		position.setCard(card);
		position.setSlotOwner(cardOwner);
	}
	
	/**
	 * Settles conflicts anywhere on the board, and updates the board accordingly
	 */
	public void resolveBoardConflicts() {
		for (int row = 0; row < getHeight(); ++row) {
			resolveRow(getBoardRow(row));
		}
	}
	
	/**
	 * Settles conflicts in a given row, and updates the row accordingly
	 * @param boardRow
	 */
	private void resolveRow(BoardSlot[] boardRow) {
		PlayerIdentity previousOwner = boardRow[0].getOwner();
		for (int col = 1; col < boardRow.length; ++col) {
			PlayerIdentity currentOwner = boardRow[col].getOwner();
			if (previousOwner != null && currentOwner != null && previousOwner != currentOwner) {
				resolveConflict(boardRow[col -1], boardRow[col]);
				return;
			}
			previousOwner = currentOwner;
		}
	}
	
	/**
	 * Resolves a conflict between two given positions on the board
	 * @param leftSlot controlled by the left player
	 * @param rightSlot controlled by the right player
	 */
	private void resolveConflict(BoardSlot leftSlot, BoardSlot rightSlot) {
		Card leftCard = leftSlot.getCard();
		Card rightCard = rightSlot.getCard();
		boolean leftDead = rightCard.getAttack() > leftCard.getDefenseForAttackType(rightCard.getAttackType());
		boolean rightDead = leftCard.getAttack() > rightCard.getDefenseForAttackType(leftCard.getAttackType());

		//TODO: handle BonusEffects
		
		if (leftDead) {
			killSlot(leftSlot, rightCard.getAttackType());
		}
		if (rightDead) {
			killSlot(rightSlot, leftCard.getAttackType());
		}
	}
	
	/**
	 * Kills every single card on the board
	 */
	private void killBoard() {
		for (int row = 0; row < getHeight(); ++row) {
			for (int col = 0; col < getWidth(); ++ col) {
				killSlot(getBoardSlot(row, col), AttackType.MAGICAL);
			}
		}
	}
	
	/**
	 * Removes a card and owner from a given slot, signaling the callback for a card being defeated
	 * @param boardSlot
	 * @param attackType
	 */
	private void killSlot(BoardSlot boardSlot, AttackType attackType) {
		mListener.onCardDefeated(getPosition(boardSlot), attackType);
		emptySlot(boardSlot);
	}
	
	/**
	 * Emptys a given slot by removing the card and assigning the owner to NONE
	 * @param boardSlot
	 */
	private void emptySlot(BoardSlot boardSlot) {
		boardSlot.setCard(null);
		boardSlot.setSlotOwner(null);
	}
	
	/**
	 * Clears out all stalemated cards if the board is full
	 */
	public void resolveBoardStalemate() {
		if (isFull()) {
			for (int row = 0; row < getHeight(); ++row) {
				unstalemateRow(getBoardRow(row));
			}
		}
	}
	
	/**
	 * Updates a given row of the game board by removing any stalemated cards
	 * @param boardRow
	 */
	private void unstalemateRow(BoardSlot[] boardRow) {
		PlayerIdentity previousOwner = boardRow[0].getOwner();
		for (int col = 1; col < boardRow.length; ++col) {
			PlayerIdentity currentOwner = boardRow[col].getOwner();
			if (previousOwner != null && currentOwner != null && previousOwner != currentOwner) {
				unstalemateConflict(boardRow[col -1], boardRow[col]);
				return;
			}
		}
	}
	
	/**
	 * Removes a stalemate between two given slots from the board
	 * @param leftSlot controlled by the left player
	 * @param rightSlot controlled by the right player
	 */
	private void unstalemateConflict(BoardSlot leftSlot, BoardSlot rightSlot) {
		Card leftCard = leftSlot.getCard();
		Card rightCard = rightSlot.getCard();
		
		killSlot(leftSlot, rightCard.getAttackType());
		killSlot(rightSlot, leftCard.getAttackType());
	}
	
	/**
	 * The fourth and final step of each round of play
	 *  Returns the player who has won the game, either by controlling a majority
	 *  of rows or by being the last player left with cards in their deck, or
	 *  null if no player has yet won.
	 * @return
	 */
	public PlayerIdentity getWinner() {
		int majority = getHeight() / 2 + 1;
		int rightRows = 0;
		int leftRows = 0;
		for (int row = 0; row < getHeight(); ++row) {
			BoardSlot[] boardRow = getBoardRow(row);
			if (boardRow[0].getOwner() == PlayerIdentity.RIGHT_PLAYER) {
				++rightRows;
			} else if (boardRow[getWidth()-1].getOwner() == PlayerIdentity.LEFT_PLAYER) {
				++leftRows;
			}
		}
		if (rightRows >= majority) {
			return PlayerIdentity.RIGHT_PLAYER;
		} else if (leftRows >= majority) {
			return PlayerIdentity.LEFT_PLAYER;
		}
		
		// see if we have super stalemated by each controlling 1/2 of the board rows
		if (leftRows + rightRows == getHeight()) {
			killBoard();
		}
		
		return null;
	}
	
	/**
	 * Returns a list of all the possible positions on the board where a given player
	 *  could currently place a card
	 *  
	 * @param player
	 * @return
	 */
	public List<Position> getPossibleMoves(PlayerIdentity player) {
		List<Position> moves = new ArrayList<Position>();
		int begin = player == PlayerIdentity.LEFT_PLAYER ? 0 : getWidth() - 1;
		int end = player == PlayerIdentity.LEFT_PLAYER ? getWidth() - 1 : 0;
		int increment = player == PlayerIdentity.LEFT_PLAYER ? 1 : -1;
		for (int row = 0; row < getHeight(); ++row) {
			for (int col = begin; player == PlayerIdentity.LEFT_PLAYER ? col <= end : col >= end; col += increment) {
				if (getBoardSlot(row, col).getOwner() == null) {
					moves.add(new Position(row, col));
					break;
				}
			}
		}
		return moves;
	}
}